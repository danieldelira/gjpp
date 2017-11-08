/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.paypal;

import com.itseekers.webservices.utileria.paypal.Link;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public class PaypalData {
    private String paymentId;
    private String token;
    private String payerID;
    private List<Link> links; 

    public String getPaymentId() {
        return paymentId;
    }

    public String getToken() {
        return token;
    }

    public String getPayerID() {
        return payerID;
    }

    public List<Link> getLinks() {
        return links;
    }

    @Override
    public String toString() {
        return "PaypalData{" + "paymentId=" + paymentId + ", token=" + token + ", payerID=" + payerID + ", links=" + links + '}';
    }
    
}
