/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao.impl;

import com.itseekers.webservices.dao.ClienteDao;
import com.itseekers.webservices.dao.OrdenDao;
import com.itseekers.webservices.dao.ProductoDao;
import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.DetalleOrden;
import com.itseekers.webservices.dto.Franquicia;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.Orden;
import com.itseekers.webservices.dto.PrecioTamanoProducto;
import com.itseekers.webservices.dto.ProductoSubMenu;
import com.itseekers.webservices.dto.RedSocial;
import com.itseekers.webservices.dto.RelProductoTopping;
import com.itseekers.webservices.dto.Status;
import com.itseekers.webservices.dto.TipoMovimiento;
import com.itseekers.webservices.dto.Topping;
import com.itseekers.webservices.service.request.Comanda;
import com.itseekers.webservices.service.request.ProductoAdd;
import com.itseekers.webservices.service.request.inners.ProductoDetalles;
import com.itseekers.webservices.service.response.wrapper.OrdenWithDetalle;
import com.itseekers.webservices.service.response.wrapper.ToppingAdd;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IT-Seekers
 */
@Repository
@Transactional
public class OrdenDaoImpl implements OrdenDao {

    @Autowired
    SessionFactory sessionFactory;
    
    @Autowired
    ProductoDao productoDao;
    
    @Autowired
    ClienteDao clienteDao;

    @Override
    public Orden registrarOrden(float total, Cliente cliente, Franquicia franquicia, List<ProductoAdd> productos, int tiempoLlegada) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Status status;
            if (franquicia.getId() == 1) {
                status = (Status) session.get(Status.class, 1L);
            } else {
                status = (Status) session.get(Status.class, 4L);
            }
            Orden orden = null;
            if (tiempoLlegada == 0) {
                orden = new Orden(Calendar.getInstance().getTime(), total, status, generarTicket(6), franquicia, null, null);
            } else {
                Calendar fechaProgramada = Calendar.getInstance();
                fechaProgramada.add(Calendar.MINUTE, tiempoLlegada - 6);
                orden = new Orden(Calendar.getInstance().getTime(), total, status, generarTicket(6), franquicia, tiempoLlegada, fechaProgramada.getTime());
            }
            session.save(orden);
            for (ProductoAdd producto : productos) {
                PrecioTamanoProducto relPrecioTamanoProducto = (PrecioTamanoProducto) session.get(PrecioTamanoProducto.class, producto.getIdRelPrecioTamanoProducto());
                DetalleOrden detalleOrden = new DetalleOrden(orden, relPrecioTamanoProducto, producto.getCantidad(), producto.getCantidad() * relPrecioTamanoProducto.getPrecio());
                session.save(detalleOrden);
                for (ToppingAdd toppingAdd : producto.getToppings()) {
                    Topping topping = (Topping) session.get(Topping.class, toppingAdd.getId());
                    RelProductoTopping relProductoTopping = new RelProductoTopping(detalleOrden, topping, toppingAdd.getCantidad(), topping.getPrecio());
                    session.save(relProductoTopping);
                }
            }
            TipoMovimiento tipoMovimiento = (TipoMovimiento) session.get(TipoMovimiento.class, 2L);
            Movimiento movimiento = new Movimiento(Calendar.getInstance().getTime(), total, cliente.getSaldo(), cliente.getSaldo() - total, orden, cliente, tipoMovimiento);
            session.save(movimiento);
            cliente.setSaldo(movimiento.getSaldo());
            session.update(cliente);
            tx.commit();
            orden = new Orden(orden.getFechaPedido(), total, status, orden.getTicket(), orden.getFranquicia(), orden.getTiempoLlegada(), orden.getFechaProgramada());
            return orden;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    String generarTicket(int longitud) {
        String ticket = "";
        long milis = new java.util.GregorianCalendar().getTimeInMillis();
        Random r = new Random(milis);
        int i = 0;
        while (i < longitud) {
            char c = (char) r.nextInt(255);
            if ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z')) {
                ticket += c;
                i++;
            }
        }
        return ticket;
    }

    @Override
    public List<Orden> ObtenerOrdenesPorCliente(long idCliente) {
        Session session = sessionFactory.openSession();
        List<Orden> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            TipoMovimiento tipoMovimiento = (TipoMovimiento) session.createCriteria(TipoMovimiento.class)
                    .add(Restrictions.eq("descripcion", "COMPRA")).uniqueResult();

//            buscamos la ordenes que estan pendientes o programadas
            List<Movimiento> movimientos = session.createCriteria(Movimiento.class)
                    .add(Restrictions.eq("cliente.id", idCliente))
                    .add(Restrictions.eq("tipoMovimiento.id", tipoMovimiento.getId()))
                    .createAlias("orden", "ordenRel")
                    .add(Restrictions.disjunction()
                            .add(Restrictions.eq("ordenRel.status.id", 1L))
                            .add(Restrictions.eq("ordenRel.status.id", 4L)))
                    .list();
            for (Movimiento movimiento : movimientos) {
                Orden ordenRe = new Orden(movimiento.getOrden().getFechaPedido(), movimiento.getOrden().getTotal(), movimiento.getOrden().getStatus(), movimiento.getOrden().getTicket(), movimiento.getOrden().getFranquicia(), movimiento.getOrden().getTiempoLlegada(), movimiento.getOrden().getFechaProgramada());
                ordenRe.setId(movimiento.getOrden().getId());
                list.add(ordenRe);
            }
            //buscamos las ultimas 5 ordenes Entregadas
            movimientos = session.createCriteria(Movimiento.class)
                    .add(Restrictions.eq("cliente.id", idCliente))
                    .createAlias("orden", "ordenRel")
                    .add(Restrictions.eq("ordenRel.status.id", 2L))
                    .setMaxResults(5)
                    .list();
            for (Movimiento movimiento : movimientos) {
                Orden ordenRe = new Orden(movimiento.getOrden().getFechaPedido(), movimiento.getOrden().getTotal(), movimiento.getOrden().getStatus(), movimiento.getOrden().getTicket(), movimiento.getOrden().getFranquicia(), movimiento.getOrden().getTiempoLlegada(), movimiento.getOrden().getFechaProgramada());
                ordenRe.setId(movimiento.getOrden().getId());
                list.add(ordenRe);
            }
            //buscamos las ultimas 5 ordenes Expiradas
            movimientos = session.createCriteria(Movimiento.class)
                    .add(Restrictions.eq("cliente.id", idCliente))
                    .createAlias("orden", "ordenRel")
                    .add(Restrictions.eq("ordenRel.status.id", 3L))
                    .setMaxResults(5)
                    .list();
            for (Movimiento movimiento : movimientos) {
                Orden ordenRe = new Orden(movimiento.getOrden().getFechaPedido(), movimiento.getOrden().getTotal(), movimiento.getOrden().getStatus(), movimiento.getOrden().getTicket(), movimiento.getOrden().getFranquicia(), movimiento.getOrden().getTiempoLlegada(), movimiento.getOrden().getFechaProgramada());
                ordenRe.setId(movimiento.getOrden().getId());
                list.add(ordenRe);
            }
            return list;
        } catch (Exception e) {

            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Orden getOrdenById(long idOrden) {
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            Orden orden = (Orden) session.get(Orden.class, idOrden);
            return orden;
        } catch (Exception e) {

            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Movimiento getOrdenByTicket(String ticket) {
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            Orden orden = (Orden) session.createCriteria(Orden.class)
                    .add(Restrictions.eq("ticket", ticket))
                    .uniqueResult();
            Movimiento movimiento = (Movimiento) session.createCriteria(Movimiento.class)
                    .createAlias("orden", "orden")
                    .add(Restrictions.eq("orden.ticket", ticket))
                    .uniqueResult();

            return movimiento;
        } catch (Exception e) {

            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Orden cancelarOrdenProgramada(Orden orden) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Franquicia franquicia = (Franquicia) session.get(Franquicia.class, 1L);
            Status status = (Status) session.get(Status.class, 1L);
            orden.setFranquicia(franquicia);
            orden.setTiempoLlegada(null);
            orden.setFechaProgramada(null);
            orden.setStatus(status);
            session.update(orden);
            tx.commit();
            orden = new Orden(orden.getId(), orden.getFechaPedido(), orden.getTotal(), status, orden.getTicket(), franquicia, orden.getTiempoLlegada(), orden.getFechaProgramada(), orden.getFechaEntrega());
            return orden;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Orden getOrdenByCliente(long idCliente, long idOreden) {
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            Movimiento movimiento = (Movimiento) session.createCriteria(Movimiento.class)
                    .add(Restrictions.eq("cliente.id", idCliente))
                    .add(Restrictions.eq("orden.id", idOreden))
                    .uniqueResult();
            if (movimiento == null) {
                return null;
            }
            Orden orden = movimiento.getOrden();
            return orden;

        } catch (Exception e) {

            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public List<OrdenWithDetalle> DetalleDeOrden(long idOrden) {
        Session session = sessionFactory.openSession();
        try {
            session = sessionFactory.openSession();
            List<DetalleOrden> detalleOrdens = session.createCriteria(DetalleOrden.class)
                    .add(Restrictions.eq("orden.id", idOrden))
                    .list();
            List<OrdenWithDetalle> detalles = new ArrayList<>();
            for (DetalleOrden detalleOrden : detalleOrdens) {
                List<RelProductoTopping> toppingsDB = new ArrayList<>(detalleOrden.getRelProductoToppings());
                List<RelProductoTopping> toppings = new ArrayList<>();
                for (RelProductoTopping relProductoTopping : toppingsDB) {
                    toppings.add(new RelProductoTopping(null, relProductoTopping.getTopping(),
                            relProductoTopping.getCantidad(), relProductoTopping.getPrecio()));
                }
                detalles.add(new OrdenWithDetalle(detalleOrden.getPrecioTamanoProducto().getProductoSubMenu().getUrlImage(),
                        detalleOrden.getPrecioTamanoProducto().getProductoSubMenu().getProducto().getDescripcion(),
                        detalleOrden.getCantidad(),
                        detalleOrden.getPrecioTamanoProducto().getTamano(),
                        toppings,detalleOrden.getComentarios()));

            }

            return detalles;
        } catch (Exception e) {

            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Orden programarOrden(Orden orden, Franquicia franquicia, int tiempoLlegada) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Calendar fechaProgramada = Calendar.getInstance();
            fechaProgramada.add(Calendar.MINUTE, tiempoLlegada - 6);
            Status status = (Status) session.get(Status.class, 4L);
            orden.setFranquicia(franquicia);
            orden.setTiempoLlegada(tiempoLlegada);
            orden.setFechaProgramada(fechaProgramada.getTime());
            orden.setStatus(status);
            session.update(orden);
            tx.commit();
            orden = new Orden(orden.getId(), orden.getFechaPedido(), orden.getTotal(), status, orden.getTicket(), franquicia, orden.getTiempoLlegada(), orden.getFechaProgramada(), orden.getFechaEntrega());
            return orden;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public float obtenerTotal(List<ProductoAdd> productos) {
        Session session = sessionFactory.openSession();
        float total = 0;
        try {

            for (ProductoAdd p : productos) {
                PrecioTamanoProducto r = (PrecioTamanoProducto) session.get(PrecioTamanoProducto.class, p.getIdRelPrecioTamanoProducto());
                total += p.getCantidad() * r.getPrecio();
                for (ToppingAdd t : p.getToppings()) {
                    Topping topping = (Topping) session.get(Topping.class, t.getId());
                    total += (t.getCantidad() * topping.getPrecio()) * p.getCantidad();
                }
            }
            return total;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

     // Cambios Axel
    @Override
    public List<Orden> ObtenerOrdenesPorFranquicia() {
        Session session = sessionFactory.openSession();
        List<Orden> list = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            TipoMovimiento tipoMovimiento = (TipoMovimiento) session.createCriteria(TipoMovimiento.class)
                    .add(Restrictions.eq("descripcion", "COMPRA")).uniqueResult();

//            buscamos la ordenes que estan pendientes o programadas
            List<Movimiento> movimientos = session.createCriteria(Movimiento.class)
                   // .add(Restrictions.eq("cliente.id", idFranquicia))
              //      .add(Restrictions.eq("tipoMovimiento.id", tipoMovimiento.getId()))
                    .createAlias("orden", "ordenRel")
                    .add(Restrictions.disjunction()
                            .add(Restrictions.eq("ordenRel.status.id", 1L))
                            .add(Restrictions.eq("ordenRel.status.id", 4L)))
                    .list();
            for (Movimiento movimiento : movimientos) {
                Orden ordenRe = new Orden(movimiento.getOrden().getFechaPedido(), movimiento.getOrden().getTotal(), movimiento.getOrden().getStatus(), movimiento.getOrden().getTicket(), movimiento.getOrden().getFranquicia(), movimiento.getOrden().getTiempoLlegada(), movimiento.getOrden().getFechaProgramada());
                ordenRe.setId(movimiento.getOrden().getId());
                list.add(ordenRe);
            }
            //buscamos las ultimas 5 ordenes Entregadas
          
            //buscamos las ultimas 5 ordenes Expiradas
           
            return list;
        } catch (Exception e) {

            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }
    
    @Override
    public void CancelarOrdenesNegocio() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        Map<Boolean, Long> ordenesProcedas = new HashMap<>();
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Status statusCancelado = (Status) session.createCriteria(Status.class)
                    .add(Restrictions.eq("descripcion", "EXPIRADA"))
                    .uniqueResult();

            Status statusPendiente = (Status) session.createCriteria(Status.class)
                    .add(Restrictions.eq("descripcion", "PENDIENTE"))
                    .uniqueResult();

            Status statusProgramada = (Status) session.createCriteria(Status.class)
                    .add(Restrictions.eq("descripcion", "PROGRAMADA"))
                    .uniqueResult();

            Date fechaActual = Calendar.getInstance().getTime();
            List<Orden> ordenesPorCancelar = session.createCriteria(Orden.class)
                    .add(Restrictions.disjunction()
                            .add(Restrictions.eq("status.id", statusPendiente.getId()))
                            .add(Restrictions.eq("status.id", statusProgramada.getId())))
                    .add(Restrictions.lt("fechaPedido", fechaActual))
                    .list();
            for (Orden orden : ordenesPorCancelar) {
                orden.setStatus(statusCancelado);
                session.update(orden);
                System.err.println(String.format("Orden %s %s", orden.getTicket(), statusCancelado.getDescripcion()));
            }
            tx.commit();

        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public Orden entregarOrden(Orden orden) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Calendar fechaEntrega = Calendar.getInstance();
            Status status = (Status) session.createCriteria(Status.class)
                    .add(Restrictions.eq("descripcion", "entregada").ignoreCase())
                    .uniqueResult();

            orden.setFechaEntrega(fechaEntrega.getTime());
            orden.setStatus(status);
            session.merge(orden);
            tx.commit();
            //orden = new Orden(orden.getId(), orden.getFechaPedido(), orden.getTotal(), status, orden.getTicket(), orden.getFranquicia(), orden.getTiempoLlegada(), orden.getFechaProgramada(), orden.getFechaEntrega());
           return orden;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public List<Orden> obtenerOrdenesParaReporte(String desde, String hasta, String franquicia, String status) {
        List<Orden> ordens = new ArrayList<>();
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            String QuerySQL = "SELECT \n" +
                                                "    tco.*\n" +
                                                "FROM\n" +
                                                "    tc_orden tco\n" +
                                                "        INNER JOIN\n" +
                                                "    tc_franquicia tcf\n" +
                                                "        INNER JOIN\n" +
                                                "    tc_status tcs\n" +
                                                "WHERE\n" +
                                                "    tco.FRANQUICIA_ID = tcf.FRANQUICIA_ID\n" +
                                                "        AND tco.STATUS_ID = tcs.STATUS_ID";
            if( (!desde.isEmpty()) && (!hasta.isEmpty()) ){
                QuerySQL += " AND tco.FECHA_PEDIDO between '"+desde+"' and '"+hasta+"'";
            }else if( !desde.isEmpty() ){
                QuerySQL += " AND tco.FECHA_PEDIDO >= '"+desde+"'";
            }else if( !hasta.isEmpty() ){
                QuerySQL += " AND tco.FECHA_PEDIDO <= '"+hasta+"'";
            }
            if( !( franquicia.isEmpty() ) ){
                QuerySQL += " AND tco.FRANQUICIA_ID IN ("+franquicia+")";
            }
            if( !( status.isEmpty() ) ){
                QuerySQL += " AND tco.STATUS_ID in ("+status+")";
            }
            QuerySQL += " ORDER BY tco.ORDEN_ID";
            Query setResultTransformer = session.createSQLQuery(QuerySQL).addEntity(Orden.class);   
            ordens = setResultTransformer.list();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
        return ordens;
    }

    @Override
    public Orden registrarOrden(Comanda comanda, Franquicia franquicia, RedSocial redSocial) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;

        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Status status = (Status) session.get(Status.class, 4L);// Progamanda
            
            float total = 0.0f;
             for (ProductoDetalles productoDetalles : comanda.getProductoDetallesList()) {
                 total += productoDetalles.getCantidad() * (productoDetalles.getTamanio().getPrecio()+productoDetalles.getTipoLeche().getPrecio());
            }
            
            Calendar fechaProgramada = Calendar.getInstance();
            fechaProgramada.add(Calendar.SECOND, 27);
            Orden orden = new Orden(Calendar.getInstance().getTime(), total, status, generarTicket(6), franquicia, 5, fechaProgramada.getTime(),comanda.getNombreCliente(),comanda.getUbicacion(),comanda.getTipoPago());
            session.save(orden);
            
            for (ProductoDetalles productoDetalles : comanda.getProductoDetallesList()) {
                Criteria productoCriteria = session.createCriteria(PrecioTamanoProducto.class)
                    .add(Restrictions.eq("id", (long)productoDetalles.getTamanio().getId()));
                PrecioTamanoProducto precioTamanoProducto = (PrecioTamanoProducto) productoCriteria.uniqueResult();
                float totalPorProducto = (float) (productoDetalles.getCantidad() * (productoDetalles.getTamanio().getPrecio()+productoDetalles.getTipoLeche().getPrecio()));
                DetalleOrden detalleOrden = new DetalleOrden(orden, precioTamanoProducto, productoDetalles.getCantidad(), totalPorProducto,productoDetalles.getDescripcion());
                session.save(detalleOrden);                
                if(productoDetalles.getTipoLeche().getId() >0) {
                    Topping topping = (Topping) session.get(Topping.class, (long)productoDetalles.getTipoLeche().getId() );
                    RelProductoTopping relProductoTopping = new RelProductoTopping(detalleOrden, topping, 1, topping.getPrecio());
                    session.save(relProductoTopping);
                }
            }
            TipoMovimiento tipoMovimiento = (TipoMovimiento) session.get(TipoMovimiento.class, 2L);
            Movimiento movimiento = new Movimiento(Calendar.getInstance().getTime(), total, 0.0f, 0.0f, orden, redSocial.getCliente(), tipoMovimiento);
            movimiento.setProveedor("KOMANDA GJC");
            movimiento.setTipoPago(comanda.getTipoPago().toUpperCase());
            session.save(movimiento);
            orden = new Orden(orden.getFechaPedido(), total, status, orden.getTicket(), orden.getFranquicia(), orden.getTiempoLlegada(), orden.getFechaProgramada());
            tx.commit();
            return orden;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }
}
