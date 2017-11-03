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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_SUBMENU")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubMenu implements Serializable {

    
    private Long id;
    private String descripcion;
    private String urlImage;
    private Menu menu;
//    private List<ProductoSubMenu> productoSubMenus = new ArrayList<>();

    public SubMenu() {
    }

    public SubMenu(String descripcion) {
        this.descripcion = descripcion;
    }

    @Id
    @GeneratedValue
    @Column(name = "SUBMENU_ID", unique = true, nullable = false)
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "MENU_ID")
    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    @Column(name = "URL_IMAGE")
    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "subMenu")
//    public List<ProductoSubMenu> getProductoSubMenus() {
//        return productoSubMenus;
//    }
//
//    public void setProductoSubMenus(List<ProductoSubMenu> productoSubMenus) {
//        this.productoSubMenus = productoSubMenus;
//    }

    @Override
    public String toString() {
        return "SubMenu{" + "id=" + id + ", descripcion=" + descripcion + ", urlImage=" + urlImage + ", menu=" + menu + '}';
    }

    
}
