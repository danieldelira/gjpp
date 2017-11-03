/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service;


import com.itseekers.webservices.service.request.AgregarSaldo;
import com.itseekers.webservices.service.request.RegistroRequest;
import com.itseekers.webservices.service.response.ClienteResponse;
import com.itseekers.webservices.service.response.SaldoRespose;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author cheve360
 */
public interface ClienteService {

    ResponseEntity<ClienteResponse> AgregarCliente(RegistroRequest registroRequest);

    ResponseEntity<ClienteResponse> GetRedSocialCliente(String idIdentificador);

    ResponseEntity<SaldoRespose> GetSaldo(long idCliente);

    ResponseEntity<SaldoRespose> AddSaldoByIdCliente(AgregarSaldo agregarSaldo);

}
