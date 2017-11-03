/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.itseekers.webservices.dto.Franquicia;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class FranquiciaResponse {
    private String mensaje;
    private Franquicia franquicia;
    private List<Franquicia>franquicias;

    public FranquiciaResponse(String mensaje) {
        this.mensaje = mensaje;
    }

    public FranquiciaResponse(Franquicia franquicia) {
        this.franquicia = franquicia;
    }

    public FranquiciaResponse(List<Franquicia> franquicias) {
        this.franquicias = franquicias;
    }

    
    public String getMensaje() {
        return mensaje;
    }

    public Franquicia getFranquicia() {
        return franquicia;
    }

    public List<Franquicia> getFranquicias() {
        return franquicias;
    }
    
    
}
