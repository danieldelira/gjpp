/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.restcontroller;

import com.itseekers.webservices.service.FranquiciaService;
import com.itseekers.webservices.service.response.FranquiciaResponse;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author IT-Seekers
 */
@RestController
@Produces({MediaType.APPLICATION_JSON})
public class FranquiciaRestController {
    
    @Autowired
    FranquiciaService franquiciaService;
    
    @RequestMapping(value = "franquicias", method = RequestMethod.GET)
    public ResponseEntity<FranquiciaResponse> loginByUser() {
        return franquiciaService.getFranquicias();
    }
}
