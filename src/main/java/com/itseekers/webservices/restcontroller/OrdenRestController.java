/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.restcontroller;

import com.itseekers.webservices.service.OrdenService;
import com.itseekers.webservices.service.request.Comanda;
import com.itseekers.webservices.service.request.ProgramarOrden;
import com.itseekers.webservices.service.request.RegistroOrden;
import com.itseekers.webservices.service.response.OrdenResponse;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author IT-Seekers
 */
@RestController
@Produces({MediaType.APPLICATION_JSON})
public class OrdenRestController {

    @Autowired
    OrdenService ordenService;

    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "orden/agregar", method = RequestMethod.POST)
    public ResponseEntity<OrdenResponse> addOrden(@RequestBody RegistroOrden registroOrden) {
        return ordenService.registrarOrden(registroOrden);
    }
    
    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value="ingresar-comanda/{idCliente}",method = RequestMethod.POST)
    public ResponseEntity<OrdenResponse> ordenDesdeComanda(@PathVariable("idCliente") Integer idCliente, @RequestBody Comanda comanda){
        return  ordenService.registrarOrden(comanda, idCliente);
    }

    @RequestMapping(value = "cliente/{idCliente}/ordenes", method = RequestMethod.GET)
    public ResponseEntity<OrdenResponse> obtenerOrdenes(@PathVariable long idCliente) {
        return ordenService.getOrdenesByCliente(idCliente);
    }

    @RequestMapping(value = "cliente/{idCliente}/orden/{idOrden}/programada", method = RequestMethod.DELETE)
    public ResponseEntity<OrdenResponse> cancelarOrdenProgramada(@PathVariable long idCliente, @PathVariable long idOrden) {
        return ordenService.cancelarOrdenProgramada(idCliente, idOrden);
    }

    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "orden/programar", method = RequestMethod.PUT)
    public ResponseEntity<OrdenResponse> programarOrden(@RequestBody ProgramarOrden programarOrden) {
        return ordenService.programarOrden(programarOrden.getIdCliente(), programarOrden.getIdOrden(), programarOrden.getIdFranquicia(), programarOrden.getTiempoLlegada());
    }
    
    @RequestMapping(value = "cliente/ordenesPorFranquicia", method = RequestMethod.GET)
    public ResponseEntity<OrdenResponse> ObtenerOrdenesPorFranquicia() {
        return ordenService.getOrdenesByFranquicia();
    }
    
    @RequestMapping(value = "cliente/{idCliente}/orden/{idOrden}", method = RequestMethod.GET)
    public ResponseEntity<OrdenResponse> obtenerDetalleOrden(@PathVariable long idCliente, @PathVariable long idOrden) {
        return ordenService.DetalleDeOrden(idOrden, idCliente);
    }
    
    @RequestMapping(value = "orden/buscar", method = RequestMethod.GET)
    public ResponseEntity<OrdenResponse> obtenerOrden(@RequestParam("ticket") String ticket) {
        return ordenService.obtenerOrdenByTicket(ticket);
    }
    
    @RequestMapping(value = "orden/{idOrden}/entregar", method = RequestMethod.PUT)
    public ResponseEntity<OrdenResponse> entregarOrden(@PathVariable("idOrden") int idOrden) {
        return ordenService.entregarOrdenById(idOrden);
    }
    
    @RequestMapping(value="orden/reporteDeOrdenes",method = RequestMethod.POST)
    public ResponseEntity<OrdenResponse> reporteDeOrdenesConFiltro(HttpServletRequest request, HttpServletResponse response){
        String desde = request.getParameter("fdsd");
        String hasta = request.getParameter("fhst");
        String franquicia = request.getParameter("frnqc");
        String status = request.getParameter("stts");
        
        return ordenService.obtenerOrdenesParaReporte(desde, hasta, franquicia, status);
    }
    
    @RequestMapping(value="orden/{idOrden}/{idFranquicia}/entrega",method = RequestMethod.PUT)
    public ResponseEntity<OrdenResponse> entregaDeOrden(@PathVariable("idOrden") int idOrden, @PathVariable("idFranquicia") int idFranquicia){
        ResponseEntity<OrdenResponse> entregarOrdenById = ordenService.entregarOrdenById(idOrden, idFranquicia);
        return  entregarOrdenById;
    }
}
