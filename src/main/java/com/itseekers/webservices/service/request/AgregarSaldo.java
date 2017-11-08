/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request;

import com.itseekers.webservices.utileria.mercadopago.PayOrder;
import com.itseekers.webservices.utileria.paypal.PaypalData;


/**
 *
 * @author IT-Seekers
 */
public class AgregarSaldo {

   private PayOrder payOrder;
   private PaypalData paypalData;
    private long clienteId;
    private String paymentMethod;

    public PayOrder getPayOrder() {
        return payOrder;
    }

    public PaypalData getPaypalData() {
        return paypalData;
    }

    public long getClienteId() {
        return clienteId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public String toString() {
        return "AgregarSaldo{" + "payOrder=" + payOrder + ", paypalData=" + paypalData + ", clienteId=" + clienteId + ", paymentMethod=" + paymentMethod + '}';
    }
    
}
