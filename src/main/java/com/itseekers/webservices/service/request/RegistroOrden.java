/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request;
;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public class RegistroOrden {

    private long idCliente;
    private float total;
    private long idFranquiciaProgramada;
    private int tiempoLlegada;
    private List<ProductoAdd> productos;

    public RegistroOrden() {
    }
    
    public RegistroOrden(long idCliente, float total, long idFranquiciaProgramada,int tiempoLlegada, List<ProductoAdd> productos) {
        this.idCliente = idCliente;
        this.total = total;
        this.idFranquiciaProgramada = idFranquiciaProgramada;
        this.tiempoLlegada = tiempoLlegada;
        this.productos = productos;
    }
    
    public long getIdCliente() {
        return idCliente;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
    
    public List<ProductoAdd> getProductos() {
        return productos;
    }

    public long getIdFranquiciaProgramada() {
        return idFranquiciaProgramada;
    }

    public int getTiempoLlegada() {
        return tiempoLlegada;
    }
    
    
   
}
