/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dao;

import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.RedSocial;
import com.itseekers.webservices.service.request.RegistroRequest;

/**
 *
 * @author cheve360
 */
public interface ClienteDao {

    RedSocial agregarCliente(RegistroRequest registroRequest);

    RedSocial getCliente(String idIdentificador);
    
    RedSocial getCliente(Cliente cliente);

    Cliente getClienteById(long idCliente);

    Movimiento getSaldoByIdCliente(long idCliente);
    
    Movimiento addSaldoByIdCliente(Movimiento recarga);
    
    RedSocial getClientebyIdRs(long idRS);
    
}
