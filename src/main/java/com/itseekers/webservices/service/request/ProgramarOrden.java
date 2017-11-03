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
public class ProgramarOrden {
    private long idCliente;
    private long idOrden;
    private long idFranquicia;
    private int tiempoLlegada;

    public ProgramarOrden() {
    }

    public long getIdCliente() {
        return idCliente;
    }

    public long getIdOrden() {
        return idOrden;
    }

    public long getIdFranquicia() {
        return idFranquicia;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }
    
}
