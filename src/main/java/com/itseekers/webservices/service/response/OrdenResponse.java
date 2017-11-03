/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itseekers.webservices.dto.DetalleOrden;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.Orden;
import com.itseekers.webservices.service.response.wrapper.OrdenWithDetalle;
import java.util.List;
import javax.xml.soap.Detail;


/**
 *
 * @author ITS-Severiano
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrdenResponse extends BaseResponse{
   
    private Orden orden;
    private Movimiento movimiento;
    private DetalleOrden detalleOrden;
    private List<Orden>ordens;
    private List<OrdenWithDetalle> ordenWithDetalles;

    public OrdenResponse(int codigo, String mensaje) {
        super(codigo, mensaje);
    }

    public OrdenResponse(int codigo) {
        super(codigo);
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public void setOrdens(List<Orden> ordens) {
        this.ordens = ordens;
    }

    public Orden getOrden() {
        return orden;
    }

    public List<Orden> getOrdens() {
        return ordens;
    }

    public List<OrdenWithDetalle> getOrdenWithDetalles() {
        return ordenWithDetalles;
    }

    public void setOrdenWithDetalles(List<OrdenWithDetalle> ordenWithDetalles) {
        this.ordenWithDetalles = ordenWithDetalles;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(Movimiento movimiento) {
        this.movimiento = movimiento;
    }

    public DetalleOrden getDetalleOrden() {
        return detalleOrden;
    }

    public void setDetalleOrden(DetalleOrden detalleOrden) {
        this.detalleOrden = detalleOrden;
    }

    @Override
    public String toString() {
        return "OrdenResponse{" + "orden=" + orden + '}';
    }
    
}
