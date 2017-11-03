/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.paypal;

import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public class Transaction {

    private Amount amount;
    private String description;
    private List<Object> related_resources;

    public Amount getAmount() {
        return amount;
    }

    public String getDescription() {
        return description;
    }

    public List<Object> getRelated_resources() {
        return related_resources;
    }
    
}
