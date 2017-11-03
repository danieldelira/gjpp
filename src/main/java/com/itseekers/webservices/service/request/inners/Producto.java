/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request.inners;

/**
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public class Producto {    
        private  int id;
        private String descripcion;
        private int idTipo;
        private String tipe;
        private int idSubMenu;

    public Producto() {
    }
        
        

    public Producto(int id, String descripcion, int idTipo, String tipe, int idSubMenu) {
        this.id = id;
        this.descripcion = descripcion;
        this.idTipo = idTipo;
        this.tipe = tipe;
        this.idSubMenu = idSubMenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getTipe() {
        return tipe;
    }

    public void setTipe(String tipe) {
        this.tipe = tipe;
    }

    public int getIdSubMenu() {
        return idSubMenu;
    }

    public void setIdSubMenu(int idSubMenu) {
        this.idSubMenu = idSubMenu;
    }

    @Override
    public String toString() {
        return "\tProducto{" + "id=" + id + ", descripcion=" + descripcion + ", idTipo=" + idTipo + ", tipe=" + tipe + ", idSubMenu=" + idSubMenu + '}';
    }
        
}
