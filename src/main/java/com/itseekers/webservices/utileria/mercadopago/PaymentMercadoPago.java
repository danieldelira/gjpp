/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.mercadopago;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonRootName(value = "Payment")
public class PaymentMercadoPago {

    private int id;
    private String date_created;
    private String date_approved;
    private String date_last_updated;
    private String money_release_date;
    private String operation_type;
    private String issuer_id;
    private String payment_method_id;
    private String payment_type_id;
    private String status;
    private String status_detail;
    private String currency_id;
    private String description;
    private boolean live_mode;
    private Object sponsor_id;
    private Object authorization_code;
    private int collector_id;
    private Payer payer;
    private Metadata metadata;
    private Order order;
    private Object external_reference;
    private float transaction_amount;
    private int transaction_amount_refunded;
    private int coupon_amount;
    private Object differential_pricing_id;
    private Object deduction_schema;
    private TransactionDetails transaction_details;
    private List<FeeDetail> fee_details;
    private boolean captured;
    private boolean binary_mode;
    private Object call_for_authorize_id;
    private String statement_descriptor;
    private int installments;
    private Card card;
    private Object notification_url;
    private List<Object> refunds;

    public int getId() {
        return id;
    }

    public String getDate_created() {
        return date_created;
    }

    public String getDate_approved() {
        return date_approved;
    }

    public String getDate_last_updated() {
        return date_last_updated;
    }

    public String getMoney_release_date() {
        return money_release_date;
    }

    public String getOperation_type() {
        return operation_type;
    }

    public String getIssuer_id() {
        return issuer_id;
    }

    public String getPayment_method_id() {
        return payment_method_id;
    }

    public String getPayment_type_id() {
        return payment_type_id;
    }

    public String getStatus() {
        return status;
    }

    public String getStatus_detail() {
        return status_detail;
    }

    public String getCurrency_id() {
        return currency_id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isLive_mode() {
        return live_mode;
    }

    public Object getSponsor_id() {
        return sponsor_id;
    }

    public Object getAuthorization_code() {
        return authorization_code;
    }

    public int getCollector_id() {
        return collector_id;
    }

    public Payer getPayer() {
        return payer;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public Order getOrder() {
        return order;
    }

    public Object getExternal_reference() {
        return external_reference;
    }

    public float getTransaction_amount() {
        return transaction_amount;
    }

    public int getTransaction_amount_refunded() {
        return transaction_amount_refunded;
    }

    public int getCoupon_amount() {
        return coupon_amount;
    }

    public Object getDifferential_pricing_id() {
        return differential_pricing_id;
    }

    public Object getDeduction_schema() {
        return deduction_schema;
    }

    public TransactionDetails getTransaction_details() {
        return transaction_details;
    }

    public List<FeeDetail> getFee_details() {
        return fee_details;
    }

    public boolean isCaptured() {
        return captured;
    }

    public boolean isBinary_mode() {
        return binary_mode;
    }

    public Object getCall_for_authorize_id() {
        return call_for_authorize_id;
    }

    public String getStatement_descriptor() {
        return statement_descriptor;
    }

    public int getInstallments() {
        return installments;
    }

    public Card getCard() {
        return card;
    }

    public Object getNotification_url() {
        return notification_url;
    }

    public List<Object> getRefunds() {
        return refunds;
    }

}
