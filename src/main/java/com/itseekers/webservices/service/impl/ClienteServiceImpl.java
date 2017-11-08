/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.service.impl;

import com.itseekers.webservices.service.response.CodigoResponse;
import com.itseekers.webservices.dao.ClienteDao;
import com.itseekers.webservices.dto.Cliente;
import com.itseekers.webservices.dto.Movimiento;
import com.itseekers.webservices.dto.RedSocial;
import com.itseekers.webservices.service.ClienteService;
import com.itseekers.webservices.service.request.AgregarSaldo;
import com.itseekers.webservices.service.request.RegistroRequest;
import com.itseekers.webservices.service.response.ClienteResponse;
import com.itseekers.webservices.service.response.SaldoRespose;
import com.itseekers.webservices.utileria.mercadopago.MercadoPagoAPIHandler;
import com.itseekers.webservices.utileria.mercadopago.PaymentMercadoPago;
import com.itseekers.webservices.utileria.paypal.PayPayAPIHandler;
import com.itseekers.webservices.utileria.paypal.PaymentPayPal;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author cheve360
 */
@Transactional
@Service("ClienteServiceImpl")
public class ClienteServiceImpl extends CodigoResponse implements ClienteService {

    private static final Logger logger = Logger.getLogger(ClienteServiceImpl.class);
    private static final String TAG = "ClienteServiceImpl: - ";
    
    @Autowired
    ClienteDao clienteDao;

    @Override
    public ResponseEntity<ClienteResponse> AgregarCliente(RegistroRequest registroRequest) {
//        validar parametros
        if (registroRequest == null) {
            return new ResponseEntity<>(new ClienteResponse(this.Error, "Error de parametros"), HttpStatus.OK);
        }
        RedSocial redSocial = clienteDao.getCliente(registroRequest.getIdReferencia());
//        validar si ya existe el cliente
        if (redSocial != null) {
            return new ResponseEntity<>(new ClienteResponse(this.ClienteYaExiste, "El cliente ya existe"), HttpStatus.OK);
        }
//        agregar cliente al sistema
        redSocial = clienteDao.agregarCliente(registroRequest);
//        validando que se agregara el cliente
        if (redSocial == null) {
            return new ResponseEntity<>(new ClienteResponse(this.Error, "Algo salio mal"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new ClienteResponse(redSocial, this.Succes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ClienteResponse> GetRedSocialCliente(String email) {
        if (email == null) {
            return new ResponseEntity<>(new ClienteResponse(this.Error, "Error de parametros"), HttpStatus.OK);
        }

        RedSocial redSocial = clienteDao.getCliente(email);

        if (redSocial == null) {
            return new ResponseEntity<>(new ClienteResponse(this.Error, "El cliente no existe"), HttpStatus.OK);
        }
        return new ResponseEntity<>(new ClienteResponse(redSocial, this.Succes), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<SaldoRespose> GetSaldo(long idCliente) {
        Cliente cliente = clienteDao.getClienteById(idCliente);
        if (cliente == null) {
            return new ResponseEntity<>(new SaldoRespose(this.ClienteNoExiste, "El cliente no existe"), HttpStatus.OK);
        }
        Movimiento movimiento = clienteDao.getSaldoByIdCliente(idCliente);
        if (movimiento == null) {
            return new ResponseEntity<>(new SaldoRespose(this.ClienteSinPuntos, "El cliente no tiene recargas"), HttpStatus.OK);
        }

        return new ResponseEntity<>(new SaldoRespose(movimiento, this.Succes), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<SaldoRespose> AddSaldoByIdCliente(AgregarSaldo agregarSaldo) {
//        obtenermos el cliente para revisar que exista
        Cliente cliente = clienteDao.getClienteById(agregarSaldo.getClienteId());
//        evaluamos que el cliente exista
        if (cliente == null) {
            return new ResponseEntity<>(new SaldoRespose(this.ClienteNoExiste, "El cliente no existe"), HttpStatus.OK);
        }
        Movimiento recarga = new Movimiento();
//        evaluamos que la compra sea por PayPal
        if (agregarSaldo.getPaymentMethod().equals("PayPal")) {
//        ejecutamos la compra de paypal
            PaymentPayPal payment = PayPayAPIHandler.executePaymentPaypal(agregarSaldo.getPaypalData().getLinks(), agregarSaldo.getPaypalData().getPayerID());
//        evaluamos la compra este correcta
            if (!payment.getState().equalsIgnoreCase("approved")) {
                return new ResponseEntity<>(new SaldoRespose(this.Error, "Error con la compra de paypal"), HttpStatus.OK);
            }
            recarga.setTicket(payment.getId());
            recarga.setUrl(payment.getLinks().get(0).getHref());
            recarga.setCantidad(Float.parseFloat(payment.getTransactions().get(0).getAmount().getTotal()));
            recarga.setTipoPago(payment.getPayer().getPayment_method());
        }
//        evaluamos que la compra sea por MercadoPago
        if (agregarSaldo.getPaymentMethod().equals("MercadoPago")) {
            PaymentMercadoPago payment = MercadoPagoAPIHandler.executePaymentMercadoPago(agregarSaldo.getPayOrder());
//           evaluamos la compra este correcta
                if ((payment==null)||!payment.getStatus().equalsIgnoreCase("approved")) {
                    return new ResponseEntity<>(new SaldoRespose(this.Error, "Error con la compra de paypal"), HttpStatus.OK);
                }
            recarga.setTicket(String.valueOf(payment.getId()));
            recarga.setCantidad(payment.getTransaction_amount());
            recarga.setTipoPago(payment.getPayment_method_id());
        }
        recarga.setProveedor(agregarSaldo.getPaymentMethod());
        recarga.setCliente(cliente);

        recarga = clienteDao.addSaldoByIdCliente(recarga);
        return new ResponseEntity<>(new SaldoRespose(recarga, this.Succes), HttpStatus.CREATED);
    }

}
