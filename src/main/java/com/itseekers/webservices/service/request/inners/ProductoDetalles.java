/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.request.inners;


/**
 *
 * @author PabloSagoz pablo.samperio@it-seekers.com
 */
public class ProductoDetalles {    
        private Producto producto;
        private int cantidad;
        private TipoLeche tipoLeche;
        private Tamanio tamanio;
        private String descripcion;
        private double total;

    public ProductoDetalles() {
    }

    public ProductoDetalles(Producto producto, int cantidad, TipoLeche tipoLeche, Tamanio tamanio, String descripcion, double total) {
        this.producto = producto;
        this.cantidad = cantidad;
        this.tipoLeche = tipoLeche;
        this.tamanio = tamanio;
        this.descripcion = descripcion;
        this.total = total;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public TipoLeche getTipoLeche() {
        return tipoLeche;
    }

    public void setTipoLeche(TipoLeche tipoLeche) {
        this.tipoLeche = tipoLeche;
    }

    public Tamanio getTamanio() {
        return tamanio;
    }

    public void setTamanio(Tamanio tamanio) {
        this.tamanio = tamanio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "\nProductoDetalles{" + "producto=" + producto + ", cantidad=" + cantidad + ",\n tipoLeche=" + tipoLeche + ",\n tamanio=" + tamanio + ", descripcion=" + descripcion + ", total=" + total + '}';
    }
        
}
