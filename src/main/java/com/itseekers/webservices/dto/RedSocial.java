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
@Table(name = "TC_RED_SOCIAL")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RedSocial implements Serializable {

    private Long id;
    private String idReferencia;
    private String descripcion;
    private String email;
    private String pass;
    private Cliente cliente;
    private Rol rol;
    private Integer franquiciaId;
    
    public RedSocial() {
    }

    public RedSocial(String idReferencia, String descripcion, String email, String pass, Cliente cliente, Rol rol, Integer franquiciaId) {
        this.idReferencia = idReferencia;
        this.descripcion = descripcion;
        this.email = email;
        this.pass = pass;
        this.cliente = cliente;
        this.rol = rol;
        this.franquiciaId = franquiciaId;
    }

    @Id
    @GeneratedValue
    @Column(name = "REDSOCIAL_ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "REFERENCIA_ID")
    public String getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "EMAIL")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "PASS")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENTE_ID")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ROL_ID")
    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    
    @Column(name = "FRANQUICIA_ID")
    public Integer getFranquiciaId() {
        return franquiciaId;
    }

    public void setFranquiciaId(Integer franquiciaId) {
        this.franquiciaId = franquiciaId;
    }
    
}
