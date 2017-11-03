/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.itseekers.webservices.dao;

import com.itseekers.webservices.dto.Franquicia;
import java.util.List;

/**
 *
 * @author Cheve3601
 */
public interface FranquiciaDAO {

    Franquicia getFranquicia(long idFranquicica);
    List<Franquicia> getFranquicias();
    
}
