/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao.impl;

import com.itseekers.webservices.dao.ProductoDao;
import com.itseekers.webservices.dto.Producto;
import com.itseekers.webservices.dto.PrecioTamanoProducto;
import com.itseekers.webservices.dto.ProductoSubMenu;
import com.itseekers.webservices.dto.SubMenu;
import com.itseekers.webservices.dto.Tamano;
import com.itseekers.webservices.dto.TipoProducto;
import com.itseekers.webservices.dto.Topping;
import com.itseekers.webservices.service.response.wrapper.ProductosWithPrecio;
import com.itseekers.webservices.service.response.wrapper.ToppingAdd;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IT-Seekers
 */
@Repository
@Transactional
public class ProductoDaoImpl implements ProductoDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public List<SubMenu> obtenerSubMenu(long menuId) {
        Session session = null;
        List<SubMenu> subMenus = new ArrayList<>();
        try {
            session = sessionFactory.openSession();
            subMenus = session.createQuery("from SubMenu where MENU_ID =:menuID")
                    .setParameter("menuID", menuId).list();
            return subMenus;
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
    public List<ProductosWithPrecio> obtenerProductos(long tipoProductoId) {
        Session session = null;
        List<ProductosWithPrecio> productos = new ArrayList<>();
        try {
            session = sessionFactory.openSession();

            session = sessionFactory.openSession();
            Criteria productosCriteria = session.createCriteria(PrecioTamanoProducto.class)
                    .createAlias("productoSubMenu", "rel")
                    .add(Restrictions.eq("rel.tipoProducto.id", tipoProductoId))
                    .setProjection(Projections.projectionList()
                            .add(Projections.distinct(Projections.property("rel.producto.id"))));

            List idProductos = productosCriteria.list();
            List<ToppingAdd> toppings = session.createCriteria(Topping.class).list();
            Iterator ids = idProductos.iterator();
            while (ids.hasNext()) {
                long id = (long) ids.next();
                Producto pdt = (Producto) session.get(Producto.class, id);
                Criteria preciosCriteria = session.createCriteria(PrecioTamanoProducto.class)
                        .createAlias("productoSubMenu", "rel")
                        .add(Restrictions.eq("rel.tipoProducto.id", tipoProductoId))
                        .add(Restrictions.eq("rel.producto.id", pdt.getId()));
//                        .setProjection(Projections.projectionList()
//                                .add(Projections.property("id"), "id")
//                                .add(Projections.property("descuento"), "descuento")
//                                .add(Projections.property("tamano"), "tamano")
//                                .add(Projections.property("precio"), "precio"))
//                        .setResultTransformer(Transformers.aliasToBean(PrecioTamanoProducto.class));
                List<PrecioTamanoProducto> preciosDB = preciosCriteria.list();

                List<ProductosWithPrecio> pd = new ArrayList<>();
                ProductosWithPrecio p = new ProductosWithPrecio(pdt, preciosDB.get(0).getProductoSubMenu().getUrlImage(),
                        preciosDB.get(0).getProductoSubMenu().getSubMenu(), preciosDB, toppings,preciosDB.get(0).getDescuento());

                productos.add(p);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
        return productos;
    }

    @Override
    public ProductosWithPrecio obtenerProducto(long idProducto) {
        Session session = null;
        PrecioTamanoProducto productoBase = null;
        try {
            session = sessionFactory.openSession();
            session = sessionFactory.openSession();
            productoBase = (PrecioTamanoProducto) session.get(PrecioTamanoProducto.class, idProducto);
            Criteria preciosCriteria = session.createCriteria(PrecioTamanoProducto.class)
                    .createAlias("productoSubMenu", "rel")
                    .add(Restrictions.eq("rel.producto.id", productoBase.getProductoSubMenu().getProducto().getId()))
                    .add(Restrictions.eq("rel.tipoProducto.id", productoBase.getProductoSubMenu().getTipoProducto().getId()));

            List<PrecioTamanoProducto> precios = preciosCriteria.list();
            Producto pdt = (Producto) session.get(Producto.class, productoBase.getProductoSubMenu().getProducto().getId());
            List<ToppingAdd> toppingAdds = session.createCriteria(Topping.class).list();

            List<ToppingAdd> toppings = session.createCriteria(Topping.class).list();
            return new ProductosWithPrecio(pdt, precios.get(0).getProductoSubMenu().getUrlImage(),
                    precios.get(0).getProductoSubMenu().getSubMenu(), precios, toppings,precios.get(0).getDescuento());

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
    public List<Producto> obtenerProductos() {
              Session session = null;
        List<Producto> productos = new ArrayList<>();
        try {
            session = sessionFactory.openSession();

            session = sessionFactory.openSession();
            Criteria productoCriteria = session.createCriteria(Producto.class)
                    .add(Restrictions.ge("id", 0L));
                  productos = productoCriteria.list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
        return productos;
    }

    @Override
    public ProductosWithPrecio obtenerProducto(long idsubMenu, long idProducto,long idTipoProducto) {
        Session session = null; 
        try {
            session = sessionFactory.openSession();
            session = sessionFactory.openSession();
            SubMenu sm = (SubMenu) session.get(SubMenu.class, idsubMenu);
            Producto pdt = (Producto) session.get(Producto.class, idProducto);
            TipoProducto tp = (TipoProducto) session.get(TipoProducto.class, idTipoProducto);
            Criteria productoSubMenuCriteria = session.createCriteria(ProductoSubMenu.class).add(Restrictions.eq("producto", pdt)).add(Restrictions.eq("subMenu", sm)).add(Restrictions.eq("tipoProducto", tp));            
            ProductoSubMenu uniqueResult = (ProductoSubMenu) productoSubMenuCriteria.uniqueResult();
            Criteria preciosCriteria = session.createCriteria(PrecioTamanoProducto.class)
                    .createAlias("productoSubMenu", "rel")
                    .add(Restrictions.eq("rel.producto.id", idProducto))
                    .add(Restrictions.eq("rel.tipoProducto.id", idTipoProducto));
            List<PrecioTamanoProducto> precios = preciosCriteria.list();

            List<ToppingAdd> toppings = session.createCriteria(Topping.class).list();
            return new ProductosWithPrecio(pdt, precios.get(0).getProductoSubMenu().getUrlImage(),
                    precios.get(0).getProductoSubMenu().getSubMenu(), precios, toppings,precios.get(0).getDescuento());

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
    public List<ProductoSubMenu> getAllProductoSubMenu() {
        Session session = null;
        List<ProductoSubMenu> productoSubMenus = new ArrayList<>();
        try {
            session = sessionFactory.openSession();

            session = sessionFactory.openSession();
            Criteria productoCriteria = session.createCriteria(ProductoSubMenu.class);
                  productoSubMenus = productoCriteria.list();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
        return productoSubMenus;
    }

    @Override
    public ProductoSubMenu obtenerProductoSubMenu(long idSubMenu, long idProducto, long idTipoProducto) {
        Session session = null; 
        try {
            session = sessionFactory.openSession();
            session = sessionFactory.openSession();
            SubMenu sm = (SubMenu) session.get(SubMenu.class, idSubMenu);
            Producto pdt = (Producto) session.get(Producto.class, idProducto);
            TipoProducto tp = (TipoProducto) session.get(TipoProducto.class, idTipoProducto);
            Criteria productoSubMenuCriteria = session.createCriteria(ProductoSubMenu.class).add(Restrictions.eq("producto", pdt)).add(Restrictions.eq("subMenu", sm)).add(Restrictions.eq("tipoProducto", tp));            
            ProductoSubMenu uniqueResult = (ProductoSubMenu) productoSubMenuCriteria.uniqueResult();
            return uniqueResult;
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
    public Tamano getTamano(long id) {
       Session session = null;
        Tamano tamano ;
        try {

            session = sessionFactory.openSession();
            Criteria productoCriteria = session.createCriteria(Tamano.class)
                    .add(Restrictions.ge("id", id));
                  tamano = (Tamano) productoCriteria.uniqueResult();
        } catch (HibernateException e) {
            throw e;
        } finally {
            if (session != null && session.isOpen()) {
                session.flush();
                session.clear();
                session.close();
            }
        }
        return tamano;
    }
    

}
