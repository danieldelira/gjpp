/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response.wrapper;

import com.itseekers.webservices.dto.Topping;

/**
 *
 * @author IT-Seekers
 */
public class ToppingAdd extends Topping{
    private float cantidad;

    public ToppingAdd() {
    }

    public ToppingAdd(Topping topping) {
        this.setId(topping.getId());
        this.setPrecio(topping.getPrecio());
        this.setDescripcion(topping.getDescripcion());
        this.setFamiliaTopping(topping.getFamiliaTopping());
    }
    public float getTotal(){
        return cantidad * this.getPrecio();
    }

    public float getCantidad() {
        return cantidad;
    }
}
