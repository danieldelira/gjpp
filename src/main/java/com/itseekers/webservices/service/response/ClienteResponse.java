/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.RedSocial;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteResponse extends BaseResponse{
    
    private Cliente cliente;
    private RedSocial redSocial;
    private List<RedSocial> redSocials;

    public ClienteResponse(int codigo, String mensaje) {
        super(codigo, mensaje);
    }
    
    
    public ClienteResponse(Cliente cliente, int codigo) {
        super(codigo);
        this.cliente = cliente;
    }

    public ClienteResponse(RedSocial redSocial, int codigo) {
        super(codigo);
        this.redSocial = redSocial;
    }

    public ClienteResponse(List<RedSocial> redSocials, int codigo) {
        super(codigo);
        this.redSocials = redSocials;
    }

    public List<RedSocial> getRedSocials() {
        return redSocials;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public RedSocial getRedSocial() {
        return redSocial;
    }
 
}
