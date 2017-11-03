/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request;

import com.itseekers.webservices.service.response.wrapper.ToppingAdd;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public class ProductoAdd {
    @JsonProperty("producto")
    private long idRelPrecioTamanoProducto;
    private int cantidad;
    @JsonProperty("toppings")
    private List<ToppingAdd> toppings;

    public long getIdRelPrecioTamanoProducto() {
        return idRelPrecioTamanoProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public List<ToppingAdd> getToppings() {
        return toppings;
    }
    
    
}
