/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.impl;

import com.itseekers.webservices.dao.FranquiciaDAO;
import com.itseekers.webservices.dto.Franquicia;
import com.itseekers.webservices.service.FranquiciaService;
import com.itseekers.webservices.service.response.FranquiciaResponse;
import java.util.List;
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
@Service("FranquiciaServiceImpl")
public class FranquiciaServiceImpl implements FranquiciaService{

    @Autowired
    FranquiciaDAO franquiciaDAO;
    
    @Override
    public ResponseEntity<FranquiciaResponse> getFranquicias() {
         List<Franquicia> frnquicias = franquiciaDAO.getFranquicias();
        if (frnquicias.isEmpty()) {
            return new ResponseEntity<>(new FranquiciaResponse("No se encontraron ordenes"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new FranquiciaResponse(frnquicias), HttpStatus.OK);
    }
    
}
