/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseResponse {
    private int codigo;
    private String mensaje;

    public BaseResponse(int codigo) {
        this.codigo = codigo;
    }
    
    public BaseResponse(int codigo, String mensaje) {
        this.codigo = codigo;
        this.mensaje = mensaje;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

}
