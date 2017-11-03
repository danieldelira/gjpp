/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TR_DETALLE_OREDEN")
@JsonInclude(Include.NON_NULL)
public class DetalleOrden implements Serializable{

    private Long id;
    private Orden orden;
    private PrecioTamanoProducto precioTamanoProducto;
    private List<RelProductoTopping> relProductoToppings = new ArrayList<>();
    private Integer cantidad;
    private Float total;
    private String comentarios;

    public DetalleOrden(Orden orden, PrecioTamanoProducto precioTamanoProducto, Integer cantidad, Float total, String comentarios) {
        this.orden = orden;
        this.precioTamanoProducto = precioTamanoProducto;
        this.cantidad = cantidad;
        this.total = total;
        this.comentarios = comentarios;
    }
    
    @Column(name = "COMENTARIOS")
    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
    

    public DetalleOrden() {
    }

    public DetalleOrden(Orden orden, PrecioTamanoProducto precioTamanoProducto, int cantidad, float total) {
        this.orden = orden;
        this.precioTamanoProducto = precioTamanoProducto;
        this.cantidad = cantidad;
        this.total = total;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TR_DETALLE_OREDEN_ID", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDEN_ID")
    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "REL_PRECIO_TAMANO_PRODUCTO_ID")
    @JsonProperty("producto")
    public PrecioTamanoProducto getPrecioTamanoProducto() {
        return precioTamanoProducto;
    }

    public void setPrecioTamanoProducto(PrecioTamanoProducto precioTamanoProducto) {
        this.precioTamanoProducto = precioTamanoProducto;
    }

    @OneToMany(mappedBy = "detalleOrden")
    @JsonProperty("toppings")
    public List<RelProductoTopping> getRelProductoToppings() {
        return relProductoToppings;
    }

    public void setRelProductoToppings(List<RelProductoTopping> relProductoToppings) {
        this.relProductoToppings = relProductoToppings;
    }

    @Column(name = "CANTIDAD")
    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "TOTAL")
    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }
}
