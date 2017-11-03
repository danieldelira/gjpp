/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.paypal;

import com.fasterxml.jackson.annotation.JsonRootName;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonRootName(value = "Payment")
public class PaymentPayPal {

    private String id;
    private String intent;
    private String state;
    private Payer payer;
    private List<Transaction> transactions;
    private String create_time;
    private RedirectUrls redirect_urls;
    private List<Link> links;

    public String getId() {
        return id;
    }

    public String getIntent() {
        return intent;
    }

    public String getState() {
        return state;
    }

    public Payer getPayer() {
        return payer;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getCreate_time() {
        return create_time;
    }

    public RedirectUrls getRedirect_urls() {
        return redirect_urls;
    }

    public List<Link> getLinks() {
        return links;
    }
    
    
}
