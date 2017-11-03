/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itseekers.webservices.dto.ProductoSubMenu;
import java.util.List;

/**
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoSubMenuResponse  extends BaseResponse{
    
    @JsonProperty("submenu")
    private List<ProductoSubMenu> productoSubMenu;
    
    public ProductoSubMenuResponse(int codigo, String mensaje) {
        super(codigo, mensaje);
    }

    public ProductoSubMenuResponse(List<ProductoSubMenu> productoSubMenu, int codigo) {
        super(codigo);
        this.productoSubMenu = productoSubMenu;
    }

    public List<ProductoSubMenu> getProductoSubMenu() {
        return productoSubMenu;
    }

    public void setProductoSubMenu(List<ProductoSubMenu> productoSubMenu) {
        this.productoSubMenu = productoSubMenu;
    }
    
    
    
}
