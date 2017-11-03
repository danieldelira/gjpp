/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request.inners;

/**
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public class Tamanio {    
        private double precio;
        private String descripcion;
        private String cantidad;
        private int id;

    public Tamanio() {
    }
        

    public Tamanio(double precio, String descripcion, String cantidad, int id) {
        this.precio = precio;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\tTamanio{" + "precio=" + precio + ", descripcion=" + descripcion + ", cantidad=" + cantidad + ", id=" + id + '}';
    }
        
}
