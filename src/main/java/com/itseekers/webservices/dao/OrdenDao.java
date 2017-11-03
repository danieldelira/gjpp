/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao;

import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.Franquicia;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.Orden;
import com.itseekers.webservices.dto.RedSocial;
import com.itseekers.webservices.service.request.Comanda;
import com.itseekers.webservices.service.request.ProductoAdd;
import com.itseekers.webservices.service.response.wrapper.OrdenWithDetalle;
import java.util.Date;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public interface OrdenDao {

    Orden registrarOrden(float total,Cliente cliente,Franquicia franquicia,List<ProductoAdd> productos,int tiempoLlegada);
    Orden registrarOrden(Comanda comanda, Franquicia franquicia, RedSocial redSocial);
    List<Orden> ObtenerOrdenesPorCliente(long idCliente);
    List<OrdenWithDetalle> DetalleDeOrden(long idOrden);
    List<Orden> ObtenerOrdenesPorFranquicia();
    Orden getOrdenByCliente(long idCliente,long idOreden);
    Orden getOrdenById(long idOrden);
    Movimiento getOrdenByTicket(String ticket);
    Orden cancelarOrdenProgramada(Orden orden);
    Orden programarOrden(Orden orden,Franquicia franquicia,int tiempoLlegada);
    float obtenerTotal(List<ProductoAdd> productos);
    Orden entregarOrden(Orden orden);
    void CancelarOrdenesNegocio();
    List<Orden> obtenerOrdenesParaReporte(String desde, String hasta, String franquicia, String status);
}
