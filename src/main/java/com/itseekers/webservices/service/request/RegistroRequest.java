/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request;

/**
 *
 * @author IT-Seekers
 */
public class RegistroRequest {

    private String idReferencia;
    private String gender;
    private String name;
    private String email;
    private String firstName;
    private String lastName;
    private String descRedSocial;
    private String pass;
    private long tipoCliente;

    public RegistroRequest() {
    }

    public String getIdReferencia() {
        return idReferencia;
    }

    public void setIdReferencia(String idReferencia) {
        this.idReferencia = idReferencia;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescRedSocial() {
        return descRedSocial;
    }

    public void setDescRedSocial(String descRedSocial) {
        this.descRedSocial = descRedSocial;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public long getTipoCliente() {
        return tipoCliente;
    }

    public void setTipoCliente(long tipoCliente) {
        this.tipoCliente = tipoCliente;
    }
   
    
}
