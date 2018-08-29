/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soapclient;

/**
 *
 * @author Leandro
 */
public class SoapClient {
    
    public static void main(String[] args) {
        System.out.println(oi("oi"));
        
        
    }

    private static String oi(String nome) {
        localhost._8080.ServicesService service = new localhost._8080.ServicesService();
        localhost._8080.Services port = service.getServicesPort();
        return port.oi(nome);
    }
   
    
}
