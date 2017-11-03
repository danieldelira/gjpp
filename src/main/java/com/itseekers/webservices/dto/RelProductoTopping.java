/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "REL_PRODUCTO_TOPPING")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RelProductoTopping  implements Serializable{
    
    private Long id;
    private Topping topping;
    private Float cantidad;
    private Float precio;
    private DetalleOrden detalleOrden;

    public RelProductoTopping() {
    }

    public RelProductoTopping(DetalleOrden detalleOrden,Topping topping, float cantidad, float precio) {
        this.detalleOrden = detalleOrden;
        this.topping = topping;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    @Id
    @GeneratedValue
    @Column(name = "REL_PRODUCTO_TOPPING_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TOPPING_ID")
    public Topping getTopping() {
        return topping;
    }

    public void setTopping(Topping topping) {
        this.topping = topping;
    }

    @Column(name = "CANTIDAD")
    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "PRECIO")
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @ManyToOne
    @JoinColumn(name = "TR_DETALLE_OREDEN_ID")
    public DetalleOrden getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }
}
