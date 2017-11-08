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
public class PayOrder {

    public float transaction_amount;
    public String token;
    public String description;
    public int installments;
    public String payment_method_id;
    public Payer payer;
    public String external_reference;
    public Metadata metadata;
    public String statement_descriptor;
    public String notification_url;
    public AdditionalInfo additional_info;

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    

    public String getDescription() {
        return description;
    }

    public int getInstallments() {
        return installments;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public Payer getPayer() {
        return payer;
    }

    public String getExternal_reference() {
        return external_reference;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public String getStatement_descriptor() {
        return statement_descriptor;
    }

    public String getNotification_url() {
        return notification_url;
    }

    public AdditionalInfo getAdditional_info() {
        return additional_info;
    }

    @Override
    public String toString() {
        return "PayOrder{" + "transaction_amount=" + transaction_amount + ", token=" + token + ", description=" + description + ", installments=" + installments + ", payment_method_id=" + payment_method_id + ", payer=" + payer + ", external_reference=" + external_reference + ", metadata=" + metadata + ", statement_descriptor=" + statement_descriptor + ", notification_url=" + notification_url + ", additional_info=" + additional_info + '}';
    }

    
}
