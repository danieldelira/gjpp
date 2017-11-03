/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_ORDEN")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Orden implements Serializable {

    private Long id;
    private Date fechaPedido;
    private Date fechaProgramada;
    private Float total;
    private Status status;
    private String ticket;
    private Franquicia franquicia;
    private Integer tiempoLlegada;
    private Date fechaEntrega;
//    private List<DetalleOrden> detalleOrdens = new ArrayList<>();
//    private Movimiento movimiento;
    private String nombreComprador;
    private String ubicacion;
    private String tipoPago;
    
    public Orden(Date fechaPedido, float total, Status status, String ticket, Franquicia franquicia,int tiempoLlegada,Date fechaProgramada, String nombreComprador, String ubicacion, String tipoPago) {
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.status = status;
        this.ticket = ticket;
        this.franquicia = franquicia;
        this.tiempoLlegada = tiempoLlegada;
        this.fechaProgramada = fechaProgramada;
        this.nombreComprador = nombreComprador;
        this.ubicacion = ubicacion;
        this.tipoPago = tipoPago;
    }
    
    @Column(name = "NOMBRE_COMPRADOR")
    public String getNombreComprador() {
        return nombreComprador;
    }

    public void setNombreComprador(String nombreComprador) {
        this.nombreComprador = nombreComprador;
    }

    @Column(name = "UBICACION")
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Column(name = "TIPO_PAGO")
    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }
    
    public Orden() {
    }

    /**
     * constructor para agregar una orden a base de datos
     *
     * @param fechaPedido
     * @param total
     * @param status
     * @param ticket
     * @param franquicia
     * @param tiempoLllegada
     */
    public Orden(Date fechaPedido, float total, Status status, String ticket, Franquicia franquicia,Integer tiempoLlegada,Date fechaProgramada) {
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.status = status;
        this.ticket = ticket;
        this.franquicia = franquicia;
        this.tiempoLlegada = tiempoLlegada;
        this.fechaProgramada = fechaProgramada;
    }

    public Orden(Long id, Date fechaPedido, Float total, Status status, String ticket, Franquicia franquicia,Integer tiempoLlegada, Date fechaProgramada, Date fechaEntrega) {
        this.id = id;
        this.fechaPedido = fechaPedido;
        this.total = total;
        this.status = status;
        this.ticket = ticket;
        this.franquicia = franquicia;
        this.tiempoLlegada = tiempoLlegada;
        this.fechaProgramada = fechaProgramada;
        this.fechaEntrega = fechaEntrega;
    }

    @Id
    @GeneratedValue
    @Column(name = "ORDEN_ID", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "FECHA_PEDIDO")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(Date fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    @Column(name = "TOTAL")
    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "STATUS_ID")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "TICKET")
    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FRANQUICIA_ID")
    public Franquicia getFranquicia() {
        return franquicia;
    }

    public void setFranquicia(Franquicia franquicia) {
        this.franquicia = franquicia;
    }

    @Column(name = "TIEMPO_LLEGADA")
    public Integer getTiempoLlegada() {
        return tiempoLlegada;
    }

    public void setTiempoLlegada(Integer tiempoLlegada) {
        this.tiempoLlegada = tiempoLlegada;
    }

    @Column(name = "FECHA_ENTREGA")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    @Column(name = "FECHA_PROGRAMADA")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ")
    public Date getFechaProgramada() {
        return fechaProgramada;
    }

    public void setFechaProgramada(Date fechaProgramada) {
        this.fechaProgramada = fechaProgramada;
    }

    @Override
    public String toString() {
        return "Orden{" + "id=" + id + ", fechaPedido=" + fechaPedido + ", fechaProgramada=" + fechaProgramada + ", total=" + total + ", status=" + status + ", ticket=" + ticket + ", franquicia=" + franquicia + ", tiempoLlegada=" + tiempoLlegada + ", fechaEntrega=" + fechaEntrega + '}';
    }
    
    
}
