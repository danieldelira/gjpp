/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.itseekers.webservices.utileria.paypal;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
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
public class PayPayAPIHandler {

    private static String ClientId = "Af9I3n0J1T-M83jwJw7SWM5ExVBjev-blpliCtH8FkDthVfBsF10kmbeWNFzd94gjHRpA4Nep3pEdD__";
    private static String ClientSecret = "EK7j_gUtLpq2Fxyl4_0QtsYRro7_hXonCjCQ98_IZHa1I2IkHFeVijoeoPWZyaIha3DJOg9z8a3dGupg";
    private static String UrlApiSandbox = "https://api.paypal.com";

    private static String getBasicAuthenticationEncoding() {
        String userPassword = ClientId + ":" + ClientSecret;
        userPassword = "Basic " + Base64.encodeBase64String(userPassword.getBytes());
        return userPassword;
    }

    private static String getAccessToken() {
        System.setProperty("https.protocols", "TLSv1,TLSv1.1,TLSv1.2");
        try {
            URL obj = new URL(UrlApiSandbox + "/v1/oauth2/token");
            HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
            String urlParameters = "grant_type=client_credentials";
            //add reuqest header
            con.setRequestMethod("POST");
            con.setRequestProperty("Accept-Language", "en-US");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestProperty("Authorization", getBasicAuthenticationEncoding());
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            con.setRequestProperty("Content-Length", Integer.toString(urlParameters.length()));

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
            PayPalToken palToken = gson.fromJson(response.toString(), PayPalToken.class);
            return palToken.getAccess_token();
        } catch (MalformedURLException ex) {
            Logger.getLogger(PayPayAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PayPayAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "";
    }

    public static PaymentPayPal executePaymentPaypal(List<Link> links, String payerId) {
        String token = getAccessToken();
        if (token.equals("")) {
            return null;
        }
        try {
            //Create connection
            URL url = null;
            Iterator<Link> iterator = links.iterator();
            while (iterator.hasNext()) {
                Link link = iterator.next();
                if (link.getRel().equals("execute")) {
                    url = new URL(link.getHref());
                }
            }
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);

            // Change this to a valid token:
            connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setRequestProperty("Content-Type", "application/json");
            //String jsonData = "{ \"intent\":\"sale\",\"redirect_urls\":{\"return_url\":\"http://example.com/your_redirect_url.html\",\"cancel_url\":\"http://example.com/your_cancel_url.html\"},\"payer\":{\"payment_method\":\"paypal\"},\"transactions\":[{\"amount\":{\"total\":\"7.47\",\"currency\":\"USD\"}}]}";
            String jsonData = String.format("{ \"payer_id\":\"%s\"}", payerId);

            try {
                // Post the data:
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());
                output.writeBytes(jsonData);
                output.close();

                // Read the response:
                BufferedReader in = new BufferedReader(new InputStreamReader(
                        connection.getInputStream()));
                String inputLine;

                StringBuffer response = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                //Deserializer string
                Gson gson = new Gson();
                PaymentPayPal payment = gson.fromJson(response.toString(), PaymentPayPal.class);
                return payment;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } catch (MalformedURLException ex) {
            Logger.getLogger(PayPayAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PayPayAPIHandler.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
