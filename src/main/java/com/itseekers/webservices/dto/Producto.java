/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_PRODUCTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Producto implements Serializable {

    private Long id;
    private String descripcion;
//    private List<ProductoSubMenu> productoSubMenus = new ArrayList<>();

    public Producto() {
    }

    public Producto(String descripcion) {
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue
    @Column(name = "PRODUCTO_ID", unique = true, nullable = false)
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

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
//    public List<ProductoSubMenu> getProductoSubMenus() {
//        return productoSubMenus;
//    }
//
//    public void setProductoSubMenus(List<ProductoSubMenu> productoSubMenus) {
//        this.productoSubMenus = productoSubMenus;
//    }

    @Override
    public String toString() {
        return "Producto{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }
    
    
}
