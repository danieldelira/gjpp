/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_DESCUENTO")
@JsonInclude(Include.NON_NULL)
public class Descuento implements Serializable {

    private Long id;
    private Float cantidad;
    private String descripcion;
    private Boolean isActive;
//    private List<PrecioTamanoProducto> precioTamanoProductos = new ArrayList<>();

    public Descuento() {
    }

    @Id
    @GeneratedValue
    @Column(name = "DESCUENTO_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "CANTIDAD")
    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "ACTIVO",columnDefinition = "boolean default true")
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "descuento")
//    public List<PrecioTamanoProducto> getPrecioTamanoProductos() {
//        return precioTamanoProductos;
//    }
//
//    public void setPrecioTamanoProductos(List<PrecioTamanoProducto> precioTamanoProductos) {
//        this.precioTamanoProductos = precioTamanoProductos;
//    }

    

}
