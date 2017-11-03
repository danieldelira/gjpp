/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.impl;

import com.itseekers.webservices.dao.ClienteDao;
import com.itseekers.webservices.dao.FranquiciaDAO;
import com.itseekers.webservices.dao.OrdenDao;
import com.itseekers.webservices.dao.ProductoDao;
import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.Franquicia;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.Orden;
import com.itseekers.webservices.dto.ProductoSubMenu;
import com.itseekers.webservices.dto.RedSocial;
import com.itseekers.webservices.service.OrdenService;
import com.itseekers.webservices.service.request.Comanda;
import com.itseekers.webservices.service.request.RegistroOrden;
import com.itseekers.webservices.service.request.inners.ProductoDetalles;
import com.itseekers.webservices.service.response.CodigoResponse;
import com.itseekers.webservices.service.response.OrdenResponse;
import com.itseekers.webservices.service.response.wrapper.OrdenWithDetalle;
import com.itseekers.webservices.service.response.wrapper.ProductosWithPrecio;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author IT-Seekers
 */
@Transactional
@Service("OrdenServiceImpl")
public class OrdenServiceImpl extends CodigoResponse implements OrdenService {

    private static int ONE_MINUTE_IN_MILLIS = 60000;

    @Autowired
    ClienteDao clienteDao;

    @Autowired
    OrdenDao ordenDao;

    @Autowired
    FranquiciaDAO franquiciaDAO;

    @Autowired
    ProductoDao productoDao;

    @Override
    public ResponseEntity<OrdenResponse> registrarOrden(RegistroOrden registroOrden) {
//        validamos que el objeto a registrar no este nulo
        if (registroOrden == null) {

            return new ResponseEntity<>(new OrdenResponse(this.Error, this.msgError), HttpStatus.OK);
        }
        //        validamos que el objeto a registrar no este nulo
        if (registroOrden.getProductos().isEmpty()) {

            return new ResponseEntity<>(new OrdenResponse(this.Error, "Agrege al menos 1 producto a su carrito"), HttpStatus.OK);
        }
//        obtenermos el cliente para revisar que exista
        Cliente cliente = clienteDao.getClienteById(registroOrden.getIdCliente());
//        evaluamos que el cliente exista
        if (cliente == null) {
            return new ResponseEntity<>(new OrdenResponse(this.SinProductos, "El cliente no existe"), HttpStatus.OK);
        }

//        evaluamos si va a programar la orden o no
        Franquicia franquicia;
        if (registroOrden.getIdFranquiciaProgramada() != 0) {
//            el susuario escogio una franquicia
            franquicia = franquiciaDAO.getFranquicia(registroOrden.getIdFranquiciaProgramada());
        } else {
            franquicia = franquiciaDAO.getFranquicia(1);
            if (registroOrden.getTiempoLlegada() > 0) {
                return new ResponseEntity<>(new OrdenResponse(this.FranquiciaNoValida, "No se puede programar esta orden"), HttpStatus.OK);
            }
        }
//        revisamos que la franquicia exista
        if (franquicia == null) {
            return new ResponseEntity<>(new OrdenResponse(this.FranquiciaNoExiste, "La franquicia no existe"), HttpStatus.OK);
        }

//        obenemos el ultimo movimiento del cliente para saber su saldo
        Movimiento movimiento = clienteDao.getSaldoByIdCliente(cliente.getId());
//        evaluamod que tenga un movimiento 
        if (movimiento == null) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteSinPuntos, "El cliente no tiene puntos"), HttpStatus.OK);
        }
        registroOrden.setTotal(ordenDao.obtenerTotal(registroOrden.getProductos()));
//        evaluamos que tenga los puntos suficientes para realizar la compra
        if (registroOrden.getTotal() > movimiento.getSaldo()) {
            return new ResponseEntity<>(new OrdenResponse(this.ClientePuntosInsuficientes, "Puntos insuficientes"), HttpStatus.OK);
        }

        Orden orden = ordenDao.registrarOrden(registroOrden.getTotal(), cliente, franquicia, registroOrden.getProductos(), registroOrden.getTiempoLlegada());
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrden(orden);
        return new ResponseEntity<>(ordenResponse, HttpStatus.CREATED);

    }

     @Override
    public ResponseEntity<OrdenResponse> getOrdenesByFranquicia() {
        List<Orden> ordens = ordenDao.ObtenerOrdenesPorFranquicia();
        if (ordens.isEmpty()) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteSinOrdenes, "La franquicia no tiene ordenes"), HttpStatus.OK);
        }
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrdens(ordens);
        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity<OrdenResponse> getOrdenesByCliente(long idCliente) {
//        obtenermos el cliente para revisar que exista
        Cliente cliente = clienteDao.getClienteById(idCliente);
//        evaluamos que el cliente exista
        if (cliente == null) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteNoExiste, "El cliente no existe"), HttpStatus.OK);
        }

        List<Orden> ordens = ordenDao.ObtenerOrdenesPorCliente(idCliente);
        if (ordens.isEmpty()) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteSinOrdenes, "El cliente no tiene ordenes"), HttpStatus.OK);
        }
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrdens(ordens);
        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> DetalleDeOrden(long idOrden, long idCliente) {
        //        validamos que el objeto a registrar no este nulo
        if (idOrden == 0 || idCliente == 0) {

            return new ResponseEntity<>(new OrdenResponse(this.Error, this.msgError), HttpStatus.OK);
        }
//        obtenermos el cliente para revisar que exista
        Cliente cliente = clienteDao.getClienteById(idCliente);
//        evaluamos que el cliente exista
        if (cliente == null) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteNoExiste, "El cliente no existe"), HttpStatus.OK);
        }
//       obtenemos la orden para revisar que exista
        Orden orden = ordenDao.getOrdenById(idOrden);
//        evaluamos que la orden exisra
        if (orden == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoExiste, "La orden no existe"), HttpStatus.OK);
        }
//        obtenemos la orden para revisar que le pertenesca al cliente
        orden = ordenDao.getOrdenByCliente(idCliente, idOrden);
//        evaluamos que la orden le pertenesca al cliente
        if (orden == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoPerteneceCliente, "La orden no le pertenece al cliente"), HttpStatus.OK);
        }
        List<OrdenWithDetalle> d = ordenDao.DetalleDeOrden(idOrden);
        Orden ordenById = ordenDao.getOrdenById(idOrden);
        OrdenResponse ordenResponse = new OrdenResponse(200);
        ordenResponse.setOrdenWithDetalles(d);
        ordenResponse.setOrden(orden);
        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> cancelarOrdenProgramada(long idCliente, long idOrden) {
//        obtenermos el cliente para revisar que exista
        Cliente cliente = clienteDao.getClienteById(idCliente);
//        evaluamos que el cliente exista
        if (cliente == null) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteNoExiste, "El cliente no existe"), HttpStatus.OK);
        }
//       obtenemos la orden para revisar que exista
        Orden orden = ordenDao.getOrdenById(idOrden);
//        evaluamos que la orden exisra
        if (orden == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoExiste, this.msgOrdenNoExiste), HttpStatus.OK);
        }
//        las ordenes en franquicia 1 no son ordenes progrmadas
        if (orden.getFranquicia().getId() == 1) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoProgramada, "Esta orden no esta programada"), HttpStatus.OK);
        }
//        evaluamos que se pueda cancelar por la regla de los 6 min antes

//        obtenemos la orden para revisar que le pertenesca al cliente
        orden = ordenDao.getOrdenByCliente(idCliente, idOrden);
//        evaluamos que la orden le pertenesca al cliente
        if (orden == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoPerteneceCliente, "La orden no le pertenece al cliente"), HttpStatus.OK);
        }
//        revisar que la orden no sobrepase los 6 minutos antes de cancelar
        Calendar fechaActual = Calendar.getInstance();
        if (orden.getFechaProgramada().before(fechaActual.getTime())) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoPerteneceCliente, "La orden no se puede cancelar"), HttpStatus.OK);
        }
        orden = ordenDao.cancelarOrdenProgramada(orden);
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrden(orden);
        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> programarOrden(long idCliente, long idOrden, long idFranquicia, int tiempoLlegada) {
//        obtenermos el cliente para revisar que exista
        Cliente cliente = clienteDao.getClienteById(idCliente);
//        evaluamos que el cliente exista
        if (cliente == null) {
            return new ResponseEntity<>(new OrdenResponse(this.ClienteNoExiste, "El cliente no existe"), HttpStatus.OK);
        }
//       obtenemos la orden para revisar que exista
        Orden orden = ordenDao.getOrdenById(idOrden);
//        evaluamos que la orden exisra
        if (orden == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoExiste, "La orden no existe"), HttpStatus.OK);
        }
        Franquicia franquicia = franquiciaDAO.getFranquicia(idFranquicia);
//        evaluamos que la franquiia exista
        if (franquicia == null) {
            return new ResponseEntity<>(new OrdenResponse(this.FranquiciaNoExiste, "La franquicia no existe"), HttpStatus.OK);
        }
//        obtenemos la orden para revisar que le pertenesca al cliente
        orden = ordenDao.getOrdenByCliente(idCliente, idOrden);
//        evaluamos que la orden le pertenesca al cliente
        if (orden == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoPerteneceCliente, "La orden no le pertenece al cliente"), HttpStatus.OK);
        }

//        revisar que la orden no sobrepase los 6 minutos antes de modificar
        if (orden.getFechaProgramada() != null) {
            Calendar fechaActual = Calendar.getInstance();
            if (orden.getFechaProgramada().before(fechaActual.getTime())) {
                return new ResponseEntity<>(new OrdenResponse(this.OrdenNoPerteneceCliente, "La orden no se puede actualizar"), HttpStatus.OK);
            }
        }
        orden = ordenDao.programarOrden(orden, franquicia, tiempoLlegada);
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrden(orden);
        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> obtenerOrdenByTicket(String ticket) {
//        obtenermos el cliente para revisar que exista

        Movimiento movimiento = ordenDao.getOrdenByTicket(ticket);
//        evaluamos que la orden exisra
        if (movimiento == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoExiste, "La orden no existe"), HttpStatus.OK);
        }

        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setMovimiento(movimiento);
        List<OrdenWithDetalle> detalle = ordenDao.DetalleDeOrden(movimiento.getOrden().getId());
        ordenResponse.setOrdenWithDetalles(detalle);

        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> entregarOrdenById(int idOrden) {
//        buscamos que exista la orden 
        Orden ordenEncontrada = ordenDao.getOrdenById(idOrden);
        if (ordenEncontrada == null) {
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoExiste, "La orden no existe"), HttpStatus.OK);
        }
//        validamos que la orden este en estatus pendiente o programada para poder realizar la entrega
        if (ordenEncontrada.getStatus().getId() != 1L) {
            if (ordenEncontrada.getStatus().getId() != 4L) {
                return new ResponseEntity<>(new OrdenResponse(this.OrdenNoEntregar, this.msgOrdenNoEntregar+ordenEncontrada.getStatus().getDescripcion()), HttpStatus.OK);
            }
        }
        ordenEncontrada = ordenDao.entregarOrden(ordenEncontrada);
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrden(ordenEncontrada);
        return new ResponseEntity<>(ordenResponse,HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> obtenerOrdenesParaReporte(String desde, String hasta, String franquicia, String status) {        
        String _desde = (desde!=null)?((validaFecha(desde))?desde+" 00:00:00":""):"";
        String _hasta = (hasta!=null)?((validaFecha(hasta))?hasta+" 23:59:59":""):"";
        String _franquicia = (franquicia!=null)?validaSecuencia(franquicia.replace(" ", "")):"";
        String _status = (status!=null)?validaSecuencia(status.replace(" ", "")):"";        
        List<Orden> ordens = ordenDao.obtenerOrdenesParaReporte(_desde, _hasta, _franquicia, _status);
        if (ordens.isEmpty()) {
            return new ResponseEntity<>(new OrdenResponse(this.NoSeEncontraronOrdenes, "No Se Encontraron Ordenes Con Los Criterios Ingresados =( "), HttpStatus.OK);
        }
        OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
        ordenResponse.setOrdens(ordens);
        return new ResponseEntity<>(ordenResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<OrdenResponse> entregarOrdenById(int idOrden, int idFranquicia) {
        Orden ordenById = ordenDao.getOrdenById(idOrden);
        if(ordenById==null){
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoExiste, "No fue encontrada la orden con el id enviado :( "), HttpStatus.OK);
        }
        if(ordenById.getStatus().getDescripcion().equals("PENDIENTE")||ordenById.getStatus().getDescripcion().equals("PROGRAMADA")){
                Franquicia franquicia = (idFranquicia==ordenById.getFranquicia().getId())?ordenById.getFranquicia():franquiciaDAO.getFranquicia(idFranquicia);
                if(franquicia!=null){
                    ordenById.setFranquicia(franquicia);
                    ordenById = ordenDao.entregarOrden(ordenById);
                    OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
                    ordenResponse.setOrden(ordenById);
                    return new ResponseEntity<>(ordenResponse,HttpStatus.OK);
                }else{
                    return new ResponseEntity<>(new OrdenResponse(this.FranquiciaNoExiste, "No se encontró alguna franquicia con el id enviado :( ."), HttpStatus.OK);
                }
        }else{
            return new ResponseEntity<>(new OrdenResponse(this.OrdenNoEntregar, this.msgOrdenNoEntregar+ordenById.getStatus().getDescripcion()), HttpStatus.OK);
        }
    }
    
    private boolean validaFecha(String date){
        return Pattern.matches("\\d{4}-\\d{1,2}-\\d{1,2}", date);
    }
    private String validaSecuencia(String txt){
        return (Pattern.matches("\\d+(\\,\\d)?", txt))?txt:"";
    }

    @Override
    public ResponseEntity<OrdenResponse> registrarOrden(Comanda comanda, Integer idCliente) {
        if (comanda == null) {
            return new ResponseEntity<>(new OrdenResponse(this.Error, this.msgError), HttpStatus.OK);
        }
        if (comanda.getProductoDetallesList().isEmpty()) {
            return new ResponseEntity<>(new OrdenResponse(this.SinProductos, "No se recibio ningun producto dentro de la comanda"), HttpStatus.OK);
        }
        RedSocial redSocial = clienteDao.getClientebyIdRs(idCliente);  
        if(redSocial == null){
            return new ResponseEntity<>(new OrdenResponse(this.Error, "El cliente no cuenta con la configuración correcta"), HttpStatus.OK);
        }else if(!redSocial.getRol().getDescripcion().toUpperCase().equals("Mesero".toUpperCase())){
            return new ResponseEntity<>(new OrdenResponse(this.Error, "El cliente no cuenta con el perfil adecuado"), HttpStatus.OK);
        }else{
            Franquicia franquicia = franquiciaDAO.getFranquicia(comanda.getFranquiciaID());
             if (franquicia == null) {
                 return new ResponseEntity<>(new OrdenResponse(this.FranquiciaNoExiste, "La franquicia no existe"), HttpStatus.OK);
             }
             Orden orden = ordenDao.registrarOrden(comanda, franquicia,redSocial);
            OrdenResponse ordenResponse = new OrdenResponse(this.Succes);
            ordenResponse.setOrden(orden);
            return new ResponseEntity<>(ordenResponse, HttpStatus.CREATED);             
        }
    }
}
