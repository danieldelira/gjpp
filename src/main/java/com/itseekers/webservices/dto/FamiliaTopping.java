/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author IT-Seekers
 */
@Entity
@Table(name = "TC_FAMILIA_TIPPING")
@JsonInclude(Include.NON_NULL)
public class FamiliaTopping implements Serializable {

    private Long id;
    private String descripcion;
    private boolean isMultiSelec;
//    private List<Topping> toppings = new ArrayList<>();

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "FAMILIA_TOPPING_ID", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "DESCRIPCION")
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Column(name = "IS_MULTI_SELECT")
    public boolean isIsMultiSelec() {
        return isMultiSelec;
    }

    public void setIsMultiSelec(boolean isMultiSelec) {
        this.isMultiSelec = isMultiSelec;
    }

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "familiaTopping")
//    public List<Topping> getToppings() {
//        return toppings;
//    }
//
//    public void setToppings(List<Topping> toppings) {
//        this.toppings = toppings;
//    }

}
