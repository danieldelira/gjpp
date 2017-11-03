/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.restcontroller;

import com.itseekers.webservices.service.ClienteService;
import com.itseekers.webservices.service.request.AgregarSaldo;
import com.itseekers.webservices.service.request.RegistroRequest;
import com.itseekers.webservices.service.response.ClienteResponse;
import com.itseekers.webservices.service.response.SaldoRespose;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author cheve360
 */
@RestController
@Produces({MediaType.APPLICATION_JSON})
public class ClienteRestController {
    
    @Autowired
    ClienteService clienteService;

     /**
     * peticion que hace un GetRedSocialCliente del Cliente
     *
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public ResponseEntity<ClienteResponse> loginByUser() {
        String identificador = SecurityContextHolder.getContext().getAuthentication().getName();
       return clienteService.GetRedSocialCliente(identificador);
    }
    
    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "cliente/agregar", method = RequestMethod.POST)
    public ResponseEntity<ClienteResponse> addUser(@RequestBody RegistroRequest registroRequest) {
                return clienteService.AgregarCliente(registroRequest);
             
    }
    
    @Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "cliente/saldo/agregar", method = RequestMethod.POST)
    public ResponseEntity<SaldoRespose> addSaldo(@RequestBody AgregarSaldo agregarSaldo) {
        return clienteService.AddSaldoByIdCliente(agregarSaldo);
    }
    
@Consumes({MediaType.APPLICATION_JSON})
    @RequestMapping(value = "cliente/{idCliente}/saldo", method = RequestMethod.GET)
    public ResponseEntity<SaldoRespose> getSaldo(@PathVariable long idCliente) {
        return clienteService.GetSaldo(idCliente);
    }
}
