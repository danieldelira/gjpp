/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_TOPPING")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Topping implements Serializable {

    private Long id;
    private String descripcion;
    private Float precio;
    private FamiliaTopping familiaTopping;
//    private List<DetalleOrden> detalleOrdens = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TOPPING_ID", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "PRECIO")
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FAMILIA_TOPPING_ID", nullable = false)
    public FamiliaTopping getFamiliaTopping() {
        return familiaTopping;
    }

    public void setFamiliaTopping(FamiliaTopping familiaTopping) {
        this.familiaTopping = familiaTopping;
    }

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "toppings")
//    public List<DetalleOrden> getDetalleOrdens() {
//        return detalleOrdens;
//    }
//
//    public void setDetalleOrdens(List<DetalleOrden> detalleOrdens) {
//        this.detalleOrdens = detalleOrdens;
//    }
}
