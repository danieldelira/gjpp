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
public class Payer {
      private String payment_method;
        private boolean use_vendor_currency_conversion;

    public String getPayment_method() {
        return payment_method;
    }

    public boolean isUse_vendor_currency_conversion() {
        return use_vendor_currency_conversion;
    }
        
        
}
