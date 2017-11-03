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
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_PERMISO")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Permiso implements Serializable {

    private Long id;
    private String descripcion;
//    private List<Rol> rols = new ArrayList<>();

    public Permiso() {
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "PERMISO_ID", unique = true, nullable = false)
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

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "permisos")
//    public List<Rol> getRols() {
//        return rols;
//    }
//
//    public void setRols(List<Rol> rols) {
//        this.rols = rols;
//    }

}
