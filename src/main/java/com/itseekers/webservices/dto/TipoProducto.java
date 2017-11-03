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
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_TIPO_PRODUCTO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TipoProducto implements Serializable {

   
    private Long id;
    private String descripcion;
//  private List<ProductoSubMenu> productoSubMenus = new ArrayList<>();
    
    

    public TipoProducto() {
    }

    public TipoProducto(String descripcion) {
        this.descripcion = descripcion;
    }

     @Id
    @GeneratedValue
    @Column(name = "TIPO_PRODUCTO_ID", unique = true, nullable = false)
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

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "tipoProducto")
//    public List<ProductoSubMenu> getProductoSubMenus() {
//        return productoSubMenus;
//    }
//
//    public void setProductoSubMenus(List<ProductoSubMenu> productoSubMenus) {
//        this.productoSubMenus = productoSubMenus;
//    }

    @Override
    public String toString() {
        return "TipoProducto{" + "id=" + id + ", descripcion=" + descripcion + '}';
    }


}
