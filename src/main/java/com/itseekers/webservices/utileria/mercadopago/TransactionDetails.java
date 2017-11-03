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
public class TransactionDetails {

    private double net_received_amount;
    private int total_paid_amount;
    private int overpaid_amount;
    private Object external_resource_url;
    private int installment_amount;
    private Object financial_institution;
    private Object payment_method_reference_id;

    public double getNet_received_amount() {
        return net_received_amount;
    }

    public int getTotal_paid_amount() {
        return total_paid_amount;
    }

    public int getOverpaid_amount() {
        return overpaid_amount;
    }

    public Object getExternal_resource_url() {
        return external_resource_url;
    }

    public int getInstallment_amount() {
        return installment_amount;
    }

    public Object getFinancial_institution() {
        return financial_institution;
    }

    public Object getPayment_method_reference_id() {
        return payment_method_reference_id;
    }

}
