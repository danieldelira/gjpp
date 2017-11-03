/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.response;

import java.util.Set;

/**
 *
 * @author IT-Seekers
 */
public class CodigoResponse {
    public int Error = 1000;
    public String msgError = "Parametros Incorrectos";
    public int Succes = 200;
    
    
    public int ClienteYaExiste = 201;
    public int ClienteNoExiste = 202;
    public int ClientePuntosInsuficientes = 203;
    public int ClienteSinOrdenes = 204;
    public int SinSaldo = 205;
    public int SinProductos = 206;
    public int ClienteSinPuntos = 207;
    
    public int FranquiciaNoValida = 300;
    public int FranquiciaNoExiste= 301;
    
    public int OrdenNoExiste = 400;
    public String msgOrdenNoExiste ="La orden no existe";
    public int OrdenNoProgramada = 401;
    public int OrdenNoPerteneceCliente = 402;
     public int OrdenNoEntregar = 403;
     public String msgOrdenNoEntregar ="La orden esta: ";
    public int NoSeEncontraronOrdenes = 404;
}
