/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author Leandro
 */
@WebService(targetNamespace="http://localhost:8080")
public class Services {
    
    @WebMethod(operationName = "oi")
    public String hello(@WebParam String nome)
    {
        return "oi "+ nome+ "!";
    }
    
}
