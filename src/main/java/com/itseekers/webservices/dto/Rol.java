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
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_ROL")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Rol implements Serializable {

    private Long id;
    private String descripcion;
    private List<Permiso> permisos = new ArrayList<>();
//    private List<RedSocial> redSocials = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ROL_ID", unique = true, nullable = false)
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "REL_ROL_PERMISO",
            joinColumns = {
                @JoinColumn(name = "ROL_ID", nullable = false)},
            inverseJoinColumns = {
                @JoinColumn(name = "PERMISO_ID", nullable = false)})
    public List<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(List<Permiso> permisos) {
        this.permisos = permisos;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "rol")
//    public List<RedSocial> getRedSocials() {
//        return redSocials;
//    }
//
//    public void setRedSocials(List<RedSocial> redSocials) {
//        this.redSocials = redSocials;
//    }

}
