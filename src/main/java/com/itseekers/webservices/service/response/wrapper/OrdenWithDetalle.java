/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response.wrapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itseekers.webservices.dto.RelProductoTopping;
import com.itseekers.webservices.dto.Tamano;
import com.itseekers.webservices.dto.Topping;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenWithDetalle {
    private String urlImage;
    private String nombreProducto;
    private int cantidad;
    private Tamano tamano;
    private List<RelProductoTopping> toppings;
    private String ComentariosDelProducto;

    public OrdenWithDetalle(String urlImage, String nombreProducto, int cantidad, Tamano tamano, List<RelProductoTopping> toppings, String ComentariosDelProducto) {
        this.urlImage = urlImage;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.tamano = tamano;
        this.toppings = toppings;
        this.ComentariosDelProducto = ComentariosDelProducto;
    }

    public String getComentariosDelProducto() {
        return ComentariosDelProducto;
    }
    
    public OrdenWithDetalle(String urlImage, String nombreProducto, int cantidad,Tamano tamano) {
        this.urlImage = urlImage;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.tamano = tamano;
    }


    public String getUrlImage() {
        return urlImage;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public Tamano getTamano() {
        return tamano;
    }
    public List<RelProductoTopping> getRelProductoToppings() {
        return toppings;
    }
}
