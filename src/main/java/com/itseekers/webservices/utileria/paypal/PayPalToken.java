/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.paypal;

/**
 *
 * @author IT-Seekers
 */
public class PayPalToken {
     private String scope;
        private String nonce;
        private String access_token;
        private String token_type;
        private String app_id;
        private int expires_in;

    public String getScope() {
        return scope;
    }

    public String getNonce() {
        return nonce;
    }

    public String getAccess_token() {
        return access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public String getApp_id() {
        return app_id;
    }

    public int getExpires_in() {
        return expires_in;
    }
        
        
}
