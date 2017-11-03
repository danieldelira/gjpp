/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.itseekers.webservices.dto.Producto;
import com.itseekers.webservices.service.response.wrapper.ProductosWithPrecio;
import java.util.List;

/**
 *
 * @author IT-Seekers
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductoResponse extends BaseResponse{
    
    @JsonProperty("productos")
    private List<? extends Producto> productosWithPrecio;
    
    @JsonProperty("producto")
    private ProductosWithPrecio productoWithPrecio;

    public ProductoResponse(int codigo, String mensaje) {
        super(codigo, mensaje);
    }

    public ProductoResponse(List<? extends Producto> productosWithPrecio, int codigo) {
        super(codigo);
        this.productosWithPrecio = productosWithPrecio;
    }

    public ProductoResponse(ProductosWithPrecio productoWithPrecio, int codigo) {
        super(codigo);
        this.productoWithPrecio = productoWithPrecio;
    }

    public List<? extends Producto> getProductosWithPrecio() {
        if(productosWithPrecio instanceof ProductosWithPrecio){
            return (List<ProductosWithPrecio>) productosWithPrecio;
        }else{
            return (List<Producto>) productosWithPrecio;
        }
    }

    public ProductosWithPrecio getProductoWithPrecio() {
        return productoWithPrecio;
    }

}
