
package localhost._8080;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "Services", targetNamespace = "http://localhost:8080")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Services {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "oi", targetNamespace = "http://localhost:8080", className = "localhost._8080.Oi")
    @ResponseWrapper(localName = "oiResponse", targetNamespace = "http://localhost:8080", className = "localhost._8080.OiResponse")
    @Action(input = "http://localhost:8080/Services/oiRequest", output = "http://localhost:8080/Services/oiResponse")
    public String oi(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

}