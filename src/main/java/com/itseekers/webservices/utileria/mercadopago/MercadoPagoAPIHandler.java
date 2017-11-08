/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.mercadopago;

import com.itseekers.webservices.utileria.paypal.*;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author IT-Seekers
 */
public class MercadoPagoAPIHandler {

    private static String clientId = "6413807072217680";
    private static String clientSecret = "TDDkUAk9N58JB6KHfrTxBs5VgUJvxvWf";
    //private static String access_token = "TEST-6413807072217680-072814-f2a7f33b190ea86b43a6b1085e1b1c9f__LC_LA__-264377148";
    private static String access_token = "APP_USR-6413807072217680-072814-ee1316a2667be92033e01e47900f4674__LC_LD__-264377148";
    private static String UrlApiSandbox = "https://api.mercadopago.com";


    private static String getAccessToken() {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        try {
            URL obj = new URL(UrlApiSandbox + "/oauth/token");
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            String urlParameters = String.format("grant_type=client_credentials"
                    + "&client_id=%s&client_secret=%s",clientId,clientSecret);
            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            con.setUseCaches(false);
            con.setDoInput(true);

            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //Deserializer string
            Gson gson = new Gson();
            AccessToken accessToken = gson.fromJson(response.toString(), AccessToken.class);
            return accessToken.getAccess_token();
        } catch (MalformedURLException ex) {
            Logger.getLogger(MercadoPagoAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MercadoPagoAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static PaymentMercadoPago executePaymentMercadoPago(PayOrder payOrder) {
        try {
            //Create connection
            String urlFinal = String.format("%s%s%s",UrlApiSandbox,"/v1/payments?access_token=",access_token);
            URL url = new URL(urlFinal);
               
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Change this to a valid token:
            connection.setRequestProperty("Content-Type", "application/json");
            //String jsonData = "{ \"intent\":\"sale\",\"redirect_urls\":{\"return_url\":\"http://example.com/your_redirect_url.html\",\"cancel_url\":\"http://example.com/your_cancel_url.html\"},\"payer\":{\"payment_method\":\"paypal\"},\"transactions\":[{\"amount\":{\"total\":\"7.47\",\"currency\":\"USD\"}}]}";
            //String jsonData = String.format("{ \"payer_id\":\"%s\"}", payerId);
            Gson gson = new Gson();
            String jsonData = gson.toJson(payOrder);
            
            try {
                // Post the data:
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());
                output.writeBytes(jsonData);
                output.close();
                InputStream inputStream = connection.getInputStream();
                // Read the response:
                BufferedReader in = new BufferedReader(new InputStreamReader( inputStream ));
                String inputLine;

                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                //Deserializer string
                PaymentMercadoPago payment = gson.fromJson(response.toString(), PaymentMercadoPago.class);
                return payment;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(MercadoPagoAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MercadoPagoAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
