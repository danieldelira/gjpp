/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service;

import com.itseekers.webservices.service.request.Comanda;
import com.itseekers.webservices.service.request.RegistroOrden;
import com.itseekers.webservices.service.response.OrdenResponse;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author IT-Seekers
 */
public interface OrdenService {
    ResponseEntity<OrdenResponse> registrarOrden(RegistroOrden registroOrden);
    ResponseEntity<OrdenResponse> registrarOrden(Comanda comanda, Integer idCliente);
    ResponseEntity<OrdenResponse> getOrdenesByCliente(long idCliente);
     ResponseEntity<OrdenResponse> DetalleDeOrden(long idOrden,long idCliente);
      ResponseEntity<OrdenResponse> getOrdenesByFranquicia();
    ResponseEntity<OrdenResponse> cancelarOrdenProgramada(long idCliente,long idOrden);
    ResponseEntity<OrdenResponse> programarOrden(long idCliente,long idOrden,long idFranquicia,int tiempoLlegada);
    ResponseEntity<OrdenResponse> obtenerOrdenByTicket(String ticket);
    ResponseEntity<OrdenResponse> entregarOrdenById(int idOrden);
    ResponseEntity<OrdenResponse> obtenerOrdenesParaReporte(String desde, String hasta, String franquicia, String status);
    ResponseEntity<OrdenResponse> entregarOrdenById(int idOrden, int idFranquicia);
}
