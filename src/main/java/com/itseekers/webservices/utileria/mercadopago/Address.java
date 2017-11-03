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
public class Address {

    public String street_name;
    public int street_number;
    public String zip_code;

    public String getStreet_name() {
        return street_name;
    }

    public int getStreet_number() {
        return street_number;
    }

    public String getZip_code() {
        return zip_code;
    }

}
