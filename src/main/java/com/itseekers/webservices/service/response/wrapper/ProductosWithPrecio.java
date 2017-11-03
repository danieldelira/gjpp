/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itseekers.webservices.dto.Descuento;
import com.itseekers.webservices.dto.Producto;
import com.itseekers.webservices.dto.PrecioTamanoProducto;
import com.itseekers.webservices.dto.SubMenu;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductosWithPrecio extends Producto {

    private final String urlImage;
    private final SubMenu subMenu;
    private final Descuento descuento;
    private final List<PrecioTamanoProducto> precios;
    private final List<ToppingAdd> toppings;


    public ProductosWithPrecio(Producto producto,String urlImage, SubMenu subMenu, List<PrecioTamanoProducto> precios, List<ToppingAdd> toppings,Descuento descuento) {
        super(producto.getDescripcion());
        this.urlImage = urlImage;
        this.subMenu = subMenu;
        this.precios = precios;
        this.toppings = toppings;
        this.descuento = descuento;
    }
    
    public List<PrecioTamanoProducto> getPrecios() {
        return precios;
    }

    public List<ToppingAdd> getToppings() {
        return toppings;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public Descuento getDescuento() {
        return descuento;
    }
    
    
}
