/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.mercadopago;

/**
 *
 * @author IT-Seekers
 */
public class AccessToken {

    private String access_token;
    private String refresh_token;
    private boolean live_mode;
    private int user_id;
    private String token_type;
    private int expires_in;
    private String scope;

    public String getAccess_token() {
        return access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public boolean isLive_mode() {
        return live_mode;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getToken_type() {
        return token_type;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public String getScope() {
        return scope;
    }

}
