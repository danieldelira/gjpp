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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "REL_PRODUCTO_SUBMENU")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoSubMenu implements Serializable {

    private int id;
    private SubMenu subMenu;
    private Producto producto;
    private TipoProducto tipoProducto;
    private String urlImage;
    private String descripcion;
//    private List<PrecioTamanoProducto> precioTamanoProductos = new ArrayList<>();

    public ProductoSubMenu() {
    }

    @Id
    @GeneratedValue
    @Column(name = "REL_PRODUCTO_SUBMENU_ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SUBMENU_ID")
    public SubMenu getSubMenu() {
        return subMenu;
    }

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCTO_ID")
    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_PRODUCTO_ID")
    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    @Column(name = "URL_IMAGE")
    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    @Column(name = "DESCRIPCION", nullable = false)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "productoSubMenu")
//    public List<PrecioTamanoProducto> getPrecioTamanoProductos() {
//        return precioTamanoProductos;
//    }
//
//    public void setPrecioTamanoProductos(List<PrecioTamanoProducto> precioTamanoProductos) {
//        this.precioTamanoProductos = precioTamanoProductos;
//    }

    @Override
    public String toString() {
        return "ProductoSubMenu{" + "id=" + id + ", subMenu=" + subMenu + ", producto=" + producto + ", tipoProducto=" + tipoProducto + ", urlImage=" + urlImage + ", descripcion=" + descripcion + '}';
    }

    
}
