/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.mercadopago;

import java.util.List;

/**
 *
 * @author IT-Seekers
 */
public class AdditionalInfo {

    public List<Item> items;
    public Payer payer;
    public Shipments shipments;

    public List<Item> getItems() {
        return items;
    }

    public Payer getPayer() {
        return payer;
    }

    public Shipments getShipments() {
        return shipments;
    }

}
