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
public class Payer {
        public String type;
        public String id;
        public String email;
        public Identification identification;
        public Phone phone;
        public Address address;
        public String first_name;
        public String last_name;
        public Object entity_type;

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public Identification getIdentification() {
        return identification;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public Object getEntity_type() {
        return entity_type;
    }
        
        
}
