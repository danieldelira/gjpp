/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request;

import com.itseekers.webservices.service.request.inners.ProductoDetalles;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public class Comanda {
    
    private String nombreCliente;
    private String ubicacion;
    private String tipoPago;
    private long franquiciaID;

    public Comanda() {
    }
    
    public Comanda(String nombreCliente, String ubicacion, String tipoPago, long franquiciaID, List<ProductoDetalles> productoDetallesList) {
        this.nombreCliente = nombreCliente;
        this.ubicacion = ubicacion;
        this.tipoPago = tipoPago;
        this.franquiciaID = franquiciaID;
        this.productoDetallesList = productoDetallesList;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public long getFranquiciaID() {
        return franquiciaID;
    }

    public void setFranquiciaID(long franquiciaID) {
        this.franquiciaID = franquiciaID;
    }   
    
    private List<ProductoDetalles> productoDetallesList;

    public Comanda(List<ProductoDetalles> productoDetallesList) {
        this.productoDetallesList = productoDetallesList;
    }

    @Override
    public String toString() {
        return "Comanda{" + "nombreCliente=" + nombreCliente + ", ubicacion=" + ubicacion + ", tipoPago=" + tipoPago + ", franquiciaID=" + franquiciaID + ",\n productoDetallesList=" + productoDetallesList + '}';
    }


    public List<ProductoDetalles> getProductoDetallesList() {
        return Collections.unmodifiableList(productoDetallesList);
    }

    public void setProductoDetallesList(List<ProductoDetalles> productoDetallesList) {
        this.productoDetallesList = productoDetallesList;
     }
}
