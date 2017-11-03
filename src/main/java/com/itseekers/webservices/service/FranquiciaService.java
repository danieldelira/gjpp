/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service;

import com.itseekers.webservices.service.response.FranquiciaResponse;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author IT-Seekers
 */
public interface FranquiciaService {
    ResponseEntity<FranquiciaResponse> getFranquicias(); 
}
