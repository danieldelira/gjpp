/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.spring.security;

import com.itseekers.webservices.dao.ClienteDao;
import com.itseekers.webservices.dto.RedSocial;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    ClienteDao clienteDao;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String idIdentificador = authentication.getName();
        String password = authentication.getCredentials().toString();
        Authentication auth = null;
        boolean credencialesValidas = idIdentificador != null && password != null && !idIdentificador.isEmpty() && !password.isEmpty();
        try {
            if (credencialesValidas) {
                RedSocial redSocial = clienteDao.getCliente(idIdentificador);
                if (redSocial != null) {
                    if (redSocial.getPass().equals(password)) {
                        List<GrantedAuthority> grantedAuths = new ArrayList<>();
                        grantedAuths.add(new SimpleGrantedAuthority(idIdentificador));
                        auth = new UsernamePasswordAuthenticationToken(idIdentificador, password, grantedAuths);
                        return auth;
                    } else {
                    }
                }
            }
        } catch (Exception e) {
            logger.error(e);
        }
        return auth;
    }

    @Override
    public boolean supports(Class<?> type) {
        return true;
    }
}
