
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "REL_PRECIO_TAMANO_PRODUCTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PrecioTamanoProducto implements Serializable {

    private Long id;
    private ProductoSubMenu productoSubMenu;
    private Descuento descuento;
    private Tamano tamano;
    private Float precio;
//    private List<DetalleOrden> detalleOrdens = new ArrayList<>();
    
    public PrecioTamanoProducto() {
    }

    public PrecioTamanoProducto(ProductoSubMenu productoSubMenu, Descuento descuento, Tamano tamano, Float precio) {
        this.productoSubMenu = productoSubMenu;
        this.descuento = descuento;
        this.tamano = tamano;
        this.precio = precio;
    }

    @Id
    @GeneratedValue
    @Column(name = "REL_PRECIO_TAMANO_PRODUCTO_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REL_PRODUCTO_SUBMENU_ID")
    public ProductoSubMenu getProductoSubMenu() {
        return productoSubMenu;
    }

    public void setProductoSubMenu(ProductoSubMenu productoSubMenu) {
        this.productoSubMenu = productoSubMenu;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DESCUENTO_ID")
    public Descuento getDescuento() {
        return descuento;
    }

    public void setDescuento(Descuento descuento) {
        this.descuento = descuento;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TAMANO_ID")
    public Tamano getTamano() {
        return tamano;
    }

    public void setTamano(Tamano tamano) {
        this.tamano = tamano;
    }

    @Column(name = "PRECIO", nullable = false)
    public Float getPrecio() {
        return precio;
    }

    public void setPrecio(Float precio) {
        this.precio = precio;
    }

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "precioTamanoProducto")
//    public List<DetalleOrden> getDetalleOrdens() {
//        return detalleOrdens;
//    }
//
//    public void setDetalleOrdens(List<DetalleOrden> detalleOrdens) {
//        this.detalleOrdens = detalleOrdens;
//    }

    @Override
    public String toString() {
        return "PrecioTamanoProducto{" + "id=" + id + ", productoSubMenu=" + productoSubMenu + ", descuento=" + descuento + ", tamano=" + tamano + ", precio=" + precio + '}';
    }

    
}
