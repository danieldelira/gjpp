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
public class Item {

    public String id;
    public String title;
    public String picture_url;
    public String description;
    public String category_id;
    public int quantity;
    public int unit_price;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory_id() {
        return category_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnit_price() {
        return unit_price;
    }

}
