/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itseekers.webservices.dto.Movimiento;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SaldoRespose extends BaseResponse{
    private Movimiento movimiento;

    public SaldoRespose(int codigo, String mensaje) {
        super(codigo, mensaje);
    }

    public SaldoRespose(Movimiento movimiento, int codigo) {
        super(codigo);
        this.movimiento = movimiento;
    }

    public Movimiento getMovimiento() {
        return movimiento;
    }

    
   
}
