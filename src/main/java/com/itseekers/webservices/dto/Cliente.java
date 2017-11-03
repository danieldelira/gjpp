/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author cheve360
 */
@Entity
@Table(name = "TC_CLIENTE")
@JsonInclude(Include.NON_NULL)
public class Cliente implements Serializable {

    private Long id;
    private String nombreCliente;
    private String apellidoCliente;
    private Float saldo;
//    private List<Movimiento> movimientos = new ArrayList<>();
//    private List<RedSocial> redSocials = new ArrayList<>();

    public Cliente() {
    }

    public Cliente(String nombreCliente, String apellidoCliente, float saldo) {
        this.nombreCliente = nombreCliente;
        this.apellidoCliente = apellidoCliente;
        this.saldo = saldo;
    }

    @Id
    @GeneratedValue
    @Column(name = "CLIENTE_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "NOMBRE")
    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    @Column(name = "APELLIDO")
    public String getApellidoCliente() {
        return apellidoCliente;
    }

    public void setApellidoCliente(String apellidoCliente) {
        this.apellidoCliente = apellidoCliente;
    }

    @Column(name = "SALDO")
    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
//    public List<Movimiento> getMovimientos() {
//        return movimientos;
//    }
//
//    public void setMovimientos(List<Movimiento> movimientos) {
//        this.movimientos = movimientos;
//    }
//    @OneToMany(fetch = FetchType.LAZY,mappedBy = "cliente")
//    public List<RedSocial> getRedSocials() {
//        return redSocials;
//    }
//
//    public void setRedSocials(List<RedSocial> redSocials) {
//        this.redSocials = redSocials;
//    }
}
