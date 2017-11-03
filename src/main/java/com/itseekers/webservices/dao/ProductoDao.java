/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao;

import com.itseekers.webservices.dto.Producto;
import com.itseekers.webservices.dto.ProductoSubMenu;
import com.itseekers.webservices.dto.SubMenu;
import com.itseekers.webservices.dto.Tamano;
import com.itseekers.webservices.service.response.wrapper.ProductosWithPrecio;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public interface ProductoDao {
    /**
     * Metodo que obetine los Sub Menú de un Menú en la base de datos
     * @param menuId
     * @return 
     */
    List<SubMenu> obtenerSubMenu(long menuId);
    
    /**
     * obtiene todos los productos de un tipo de producto
     * @param tipoProductoId
     * @return 
     */
    List<ProductosWithPrecio> obtenerProductos(long tipoProductoId);
    
    /**
     * Obtiene un producto en particular
     * @param idProducto
     * @return 
     */
    ProductosWithPrecio obtenerProducto(long idProducto);
   
    //SimpleMappingExceptionResolver
    
     List<Producto> obtenerProductos();
     
     ProductosWithPrecio obtenerProducto(long idSubMenu, long idProducto, long idTipoProducto);
     
     ProductoSubMenu obtenerProductoSubMenu(long idSubMenu, long idProducto, long idTipoProducto);
     
     List<ProductoSubMenu> getAllProductoSubMenu();
     
     Tamano getTamano(long id);
}
