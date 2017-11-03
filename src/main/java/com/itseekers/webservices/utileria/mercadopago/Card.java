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
public class Card {

    private Object id;
    private String first_six_digits;
    private String last_four_digits;
    private int expiration_month;
    private int expiration_year;
    private String date_created;
    private String date_last_updated;
    private Cardholder cardholder;

    public Object getId() {
        return id;
    }

    public String getFirst_six_digits() {
        return first_six_digits;
    }

    public String getLast_four_digits() {
        return last_four_digits;
    }

    public int getExpiration_month() {
        return expiration_month;
    }

    public int getExpiration_year() {
        return expiration_year;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getDate_last_updated() {
        return date_last_updated;
    }

    public Cardholder getCardholder() {
        return cardholder;
    }

}
