/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.restcontroller;

import com.itseekers.webservices.dto.SubMenu;
import com.itseekers.webservices.service.ProductoService;
import com.itseekers.webservices.service.response.ProductoResponse;
import com.itseekers.webservices.service.response.ProductoSubMenuResponse;
import java.util.List;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author IT-Seekers
 */
@RestController
@Produces({MediaType.APPLICATION_JSON})
public class ProductoRestController {

    @Autowired
    ProductoService productoService;
       

    @RequestMapping(value = "submenu/{menuid}", method = RequestMethod.GET)
    public ResponseEntity<List<SubMenu>> obetenerSubMenu(@PathVariable("menuid") long menuId) {
        return productoService.obtenerSubMenu(menuId);
    }

    @RequestMapping(value = "productos/{tipoProductoId}", method = RequestMethod.GET)
    public ResponseEntity<ProductoResponse> obetenerProductos(@PathVariable("tipoProductoId") long tipoProductoId){
        return productoService.obtenerProductos(tipoProductoId);
    }
    
     @RequestMapping(value = "producto/{idProducto}", method = RequestMethod.GET)
    public ResponseEntity<ProductoResponse> obtenerProducto(@PathVariable("idProducto") long idProducto){
        return productoService.obtenerProducto(idProducto);
    }     
    
    @RequestMapping(value = "productos", method = RequestMethod.GET)
    public ResponseEntity<ProductoResponse> obtenerProducto(){
        return productoService.obtenerProductos();
    }
    
    @RequestMapping(value = "producto/{idSubMenu}/{idProducto}/{idTipo}", method = RequestMethod.GET)
    public ResponseEntity<ProductoResponse> obtenerProducto(@PathVariable("idSubMenu") int idSubMenu, @PathVariable("idProducto") long idProducto, @PathVariable("idTipo") long idTipo){
        return productoService.obtenerProducto(idSubMenu,idProducto,idTipo);
    }   
        
    @RequestMapping(value = "productos-del-submenu", method = RequestMethod.GET)
    public ResponseEntity<ProductoSubMenuResponse> obtenerProductosDelSubmenu(){
        return productoService.obtenerProductoDelSubMenu();
    }
}
