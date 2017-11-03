/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
@Table(name = "TC_FRANQUICIA")
@JsonInclude(Include.NON_NULL)
public class Franquicia implements Serializable {

    
    private Long id;    
    private String nombre;    
    private String municipio;    
    private String estado;    
    private String cp;    
    private String calle;    
    private String numExt;    
    private String numInt;    
    private String colFrac;   
    private Long latitud;    
    private Long longitud;
    private List<Orden> ordenes = new ArrayList<>();
    
    @Id
    @GeneratedValue
    @Column(name = "FRANQUICIA_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOMBRE")
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "MUNICIPIO")
    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    @Column(name = "ESTADO")
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Column(name = "CP")
    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Column(name = "CALLE")
    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Column(name = "NUM_EXT")
    public String getNumExt() {
        return numExt;
    }

    public void setNumExt(String numExt) {
        this.numExt = numExt;
    }

    @Column(name = "NUM_INT")
    public String getNumInt() {
        return numInt;
    }

    public void setNumInt(String numInt) {
        this.numInt = numInt;
    }

    @Column(name = "COL_FRAC")
    public String getColFrac() {
        return colFrac;
    }

    public void setColFrac(String colFrac) {
        this.colFrac = colFrac;
    }

     @Column(name = "LATITUD")
    public Long getLatitud() {
        return latitud;
    }

    public void setLatitud(Long latitud) {
        this.latitud = latitud;
    }

    @Column(name = "LONGITUD")
    public Long getLongitud() {
        return longitud;
    }

    public void setLongitud(Long longitud) {
        this.longitud = longitud;
    }

//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "franquicia")
//    public List<Orden> getOrdenes() {
//        return ordenes;
//    }
//
//    public void setOrdenes(List<Orden> ordenes) {
//        this.ordenes = ordenes;
//    }

   
}
