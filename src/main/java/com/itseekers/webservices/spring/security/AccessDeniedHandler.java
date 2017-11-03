/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.spring.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.stereotype.Service;

/**
 *
 * @author Allusers-PC
 */
@Service
public class AccessDeniedHandler extends AccessDeniedHandlerImpl {

    private static final Logger logger = Logger.getLogger(AccessDeniedHandler.class);
    
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
         //setErrorPage("/accessDenied");
        logger.info("############### Access Denied Handler!");
        super.handle(request, response, accessDeniedException); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
