/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Allusers-PC
 */
@Entity
@Table(name = "TR_MOVIMIENTO")
@JsonInclude(Include.NON_NULL)
public class Movimiento implements Serializable {

    private Long idMovimiento;
    private Date fechaMovimiento;
    private String url;
    private Float cantidad;
    private String tipoPago;
    private String proveedor;
    private Float saldoAnterior;
    private Float saldo;
    private String ticket;
    private Orden orden;
    private Cliente cliente;
    private TipoMovimiento tipoMovimiento;

    /**
     * constructor para cuando es una compra
     *
     * @param fechaMovimiento
     * @param cantidad
     * @param saldoAnterior
     * @param saldo
     * @param orden
     * @param cliente
     * @param tipoMovimiento
     */
    public Movimiento(Date fechaMovimiento, Float cantidad, Float saldoAnterior, Float saldo, Orden orden, Cliente cliente, TipoMovimiento tipoMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
        this.cantidad = cantidad;
        this.saldoAnterior = saldoAnterior;
        this.saldo = saldo;
        this.orden = orden;
        this.cliente = cliente;
        this.tipoMovimiento = tipoMovimiento;
    }

    /**
     * constructor para cuando es una recarga
     *
     * @param fechaMovimiento
     * @param cantidad
     * @param saldoAnterior
     * @param saldo
     * @param ticket
     * @param cliente
     * @param tipoMovimiento
     * @param url
     * @param tipoPago
     * @param proveedor
     */
    public Movimiento(Date fechaMovimiento, Float cantidad, Float saldoAnterior, Float saldo, String ticket, Cliente cliente, TipoMovimiento tipoMovimiento, String url,
            String tipoPago, String proveedor) {
        this.fechaMovimiento = fechaMovimiento;
        this.cantidad = cantidad;
        this.url = url;
        this.saldoAnterior = saldoAnterior;
        this.saldo = saldo;
        this.ticket = ticket;
        this.cliente = cliente;
        this.tipoMovimiento = tipoMovimiento;
        this.proveedor = proveedor;
        this.tipoPago = tipoPago;
    }

    public Movimiento() {
    }

    @Id
    @GeneratedValue
    @Column(name = "MOVIMIENTO_ID", nullable = false)
    public Long getIdMovimiento() {
        return idMovimiento;
    }

    public void setIdMovimiento(Long idMovimiento) {
        this.idMovimiento = idMovimiento;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "FECHA_MOVIMIENTO", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    @Column(name = "URL")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(name = "CANTIDAD", nullable = false)
    public Float getCantidad() {
        return cantidad;
    }

    public void setCantidad(Float cantidad) {
        this.cantidad = cantidad;
    }

    @Column(name = "TIPO_PAGO")
    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    @Column(name = "PROVEEDOR")
    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    @Column(name = "SALDO_ANTERIOR", nullable = false)
    public Float getSaldoAnterior() {
        return saldoAnterior;
    }

    public void setSaldoAnterior(Float saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    @Column(name = "SALDO", nullable = false)
    public Float getSaldo() {
        return saldo;
    }

    public void setSaldo(Float saldo) {
        this.saldo = saldo;
    }

    @Column(name = "TICKET")
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ORDEN_ID")
    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CLIENTE_ID", nullable = false)
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TIPO_MOVIMIENTO_ID", nullable = false)
    public TipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(TipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

}
