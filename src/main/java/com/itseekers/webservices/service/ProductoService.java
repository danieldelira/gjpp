/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service;

import com.itseekers.webservices.dto.SubMenu;
import com.itseekers.webservices.service.response.ProductoResponse;
import com.itseekers.webservices.service.response.ProductoSubMenuResponse;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author IT-Seekers
 */
public interface ProductoService {

    /**
     * Metodo para obtener los Sub Menús de un menú
     *
     * @param menuId
     * @return
     */
    ResponseEntity<List<SubMenu>> obtenerSubMenu(long menuId);

    
    /**
     * obtiene todos los productos de un menu
     * @param tipoProductoId
     * @return 
     */
    ResponseEntity<ProductoResponse> obtenerProductos(long tipoProductoId);
    
    /**
     * se obtiene un producto en particular
     * @param idProducto
     * @return 
     */
    ResponseEntity<ProductoResponse> obtenerProducto(long idProducto);
    
    
    ResponseEntity<ProductoResponse> obtenerProductos();
    
    ResponseEntity<ProductoResponse> obtenerProducto(int subMenu, long idProducto, long idTipo);
    
    ResponseEntity<ProductoSubMenuResponse> obtenerProductoDelSubMenu();
}
