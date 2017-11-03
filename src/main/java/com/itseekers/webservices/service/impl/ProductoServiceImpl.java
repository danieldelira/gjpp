/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.impl;

import com.itseekers.webservices.dao.ClienteDao;
import com.itseekers.webservices.dao.ProductoDao;
import com.itseekers.webservices.dto.Producto;
import com.itseekers.webservices.dto.ProductoSubMenu;
import com.itseekers.webservices.dto.SubMenu;
import com.itseekers.webservices.service.response.wrapper.ProductosWithPrecio;
import com.itseekers.webservices.service.ProductoService;
import com.itseekers.webservices.service.response.CodigoResponse;
import com.itseekers.webservices.service.response.ProductoResponse;
import com.itseekers.webservices.service.response.ProductoSubMenuResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IT-Seekers
 */
@Transactional
@Service("ProductoServiceImpl")
public class ProductoServiceImpl extends CodigoResponse implements ProductoService {

    @Autowired
    ProductoDao productoDao;
    @Autowired
    ClienteDao clienteDao;
    
    @Override
    public ResponseEntity<List<SubMenu>> obtenerSubMenu(long menuId) {
        //validamos que el id sea diferente de cero (0)
        if (menuId == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        //realizamos la peticion a la interfaz para obtener el submenú
        List<SubMenu> subMenus = productoDao.obtenerSubMenu(menuId);

        //validomos que la lista no este vacia
        if (subMenus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(subMenus, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerProductos(long tipoProductoId) {
        //validamos que el id sea diferente de cero (0)
        if (tipoProductoId == 0) {
            return new ResponseEntity<>(new ProductoResponse(this.Error,"Parametros incorrectos"),HttpStatus.OK);
        }
        //realizamos la peticion a la interfaz para obtener todos los productos de un menu
        List<ProductosWithPrecio> productos = productoDao.obtenerProductos(tipoProductoId);
        //validomos que la lista no este vacia
        if (productos.isEmpty()) {
            return new ResponseEntity<>(new ProductoResponse(this.SinProductos,"No se encontraron productos"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProductoResponse(productos,this.Succes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerProducto(long idProducto) {
       //validamos que el id sea diferente de cero (0)
        if (idProducto == 0) {
            return new ResponseEntity<>(new ProductoResponse(this.Error,"Parametros incorrectos"),HttpStatus.OK);
        }
        //realizamos la peticion a la interfaz para obtener todos los productos de un menu
        ProductosWithPrecio producto = productoDao.obtenerProducto(idProducto);
        //validomos que la lista no este vacia
        if (producto == null) {
            return new ResponseEntity<>(new ProductoResponse(this.SinProductos,"No se encontraron productos"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProductoResponse(producto,this.Succes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerProductos() {
        //realizamos la peticion a la interfaz para obtener todos los productos de un menu
        List<Producto> productos = productoDao.obtenerProductos();
        //validomos que la lista no este vacia
        if (productos.isEmpty()) {
            return new ResponseEntity<>(new ProductoResponse(this.SinProductos,"No se encontraron productos"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProductoResponse(productos,this.Succes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoResponse> obtenerProducto(int subMenu, long idProducto, long idTipo) {
        //validamos que el id sea diferente de cero (0)
        if (idProducto == 0) {
            return new ResponseEntity<>(new ProductoResponse(this.Error,"Parametros incorrectos"),HttpStatus.OK);
        }
        //realizamos la peticion a la interfaz para obtener todos los productos de un menu
        ProductosWithPrecio producto = productoDao.obtenerProducto(subMenu, idProducto,idTipo);
        //validomos que la lista no este vacia
        if (producto == null) {
            return new ResponseEntity<>(new ProductoResponse(this.SinProductos,"No se encontraron productos"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProductoResponse(producto,this.Succes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ProductoSubMenuResponse> obtenerProductoDelSubMenu() {
        //realizamos la peticion a la interfaz para obtener todos los productos de un menu
        List<ProductoSubMenu> allProductoSubMenu = productoDao.getAllProductoSubMenu();
        //validomos que la lista no este vacia
        if (allProductoSubMenu.isEmpty()) {
            return new ResponseEntity<>(new ProductoSubMenuResponse(this.SinProductos,"No se encontraron productos"),HttpStatus.OK);
        }
        return new ResponseEntity<>(new ProductoSubMenuResponse(allProductoSubMenu,this.Succes), HttpStatus.OK);
    }

    
}
