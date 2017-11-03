/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao.impl;

import com.itseekers.webservices.dao.ClienteDao;
import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.RedSocial;
import com.itseekers.webservices.dto.Rol;
import com.itseekers.webservices.dto.TipoMovimiento;
import com.itseekers.webservices.service.request.RegistroRequest;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cheve360
 */
@Repository
@Transactional
public class ClienteDaoImpl implements ClienteDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RedSocial getCliente(String identificador) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            RedSocial redSocial = (RedSocial) session.createCriteria(RedSocial.class)
                    .add(Restrictions.eq("idReferencia", identificador)).uniqueResult();
            return redSocial;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    private Cliente FusionarClientes(RegistroRequest registroRequest) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            RedSocial redSocial = (RedSocial) session.createCriteria(RedSocial.class)
                    .add(Restrictions.eq("email", registroRequest.getEmail()))
                    .uniqueResult();
            if (redSocial == null) {
                return null;
            }
            return redSocial.getCliente();
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
    public RedSocial agregarCliente(RegistroRequest registroRequest) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            Cliente cliente = FusionarClientes(registroRequest);
            if (cliente == null) {
                cliente = new Cliente(registroRequest.getFirstName(), registroRequest.getLastName(), 0.0f);
            }
            Rol rol = (Rol) session.get(Rol.class, registroRequest.getTipoCliente());
            RedSocial redSocial = new RedSocial(registroRequest.getIdReferencia(),
                    registroRequest.getDescRedSocial(), registroRequest.getEmail(), registroRequest.getPass(), cliente, rol, null);

            session.saveOrUpdate(cliente);
            session.save(redSocial);
            tx.commit();
            return redSocial;
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
    public Cliente getClienteById(long id) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Cliente clienteDto = (Cliente) session.get(Cliente.class, id);
            if (clienteDto != null) {
                return clienteDto;
            }
            return null;
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
    public Movimiento getSaldoByIdCliente(long idCliente) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            Movimiento movimiento = (Movimiento) session.createQuery("from Movimiento where CLIENTE_ID=:idCliente order by MOVIMIENTO_ID DESC")
                    .setParameter("idCliente", idCliente)
                    .setMaxResults(1)
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
    public Movimiento addSaldoByIdCliente(Movimiento recarga) {
        Session session = null;
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            TipoMovimiento tipoMovimiento = (TipoMovimiento) session.get(TipoMovimiento.class, 1L);
            Cliente cliente = (Cliente) session.get(Cliente.class, recarga.getCliente().getId());
            Movimiento ultimoMovimiento = getSaldoByIdCliente(recarga.getCliente().getId());
            Movimiento movimiento;
            if (ultimoMovimiento == null) {
                movimiento = new Movimiento(Calendar.getInstance().getTime(), recarga.getCantidad(), 0.0f, recarga.getCantidad(),
                        recarga.getTicket(), cliente, tipoMovimiento, recarga.getUrl(), recarga.getTipoPago(), recarga.getProveedor());
            } else {
                movimiento = new Movimiento(Calendar.getInstance().getTime(), recarga.getCantidad(), ultimoMovimiento.getSaldo(),
                        ultimoMovimiento.getSaldo() + recarga.getCantidad(), recarga.getTicket(), cliente, tipoMovimiento, recarga.getUrl(), recarga.getTipoPago(), recarga.getProveedor());
            }
            session.save(movimiento);
            cliente.setSaldo(movimiento.getSaldo());
            session.update(cliente);
            tx.commit();
            return movimiento;
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
    public RedSocial getCliente(Cliente cliente) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            RedSocial redSocial = (RedSocial) session.createCriteria(RedSocial.class)
                    .add(Restrictions.eq("Cliente", cliente)).uniqueResult();
            return redSocial;
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.clear();
                session.close();
            }
        }
    }

    @Override
    public RedSocial getClientebyIdRs(long idRS) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            
            RedSocial clienteDto = (RedSocial) session.createCriteria(RedSocial.class)
                    .add(Restrictions.eq("id", idRS)).uniqueResult();
            return clienteDto;
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
}
