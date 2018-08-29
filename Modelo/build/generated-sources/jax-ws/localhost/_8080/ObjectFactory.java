
package localhost._8080;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the localhost._8080 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _OiResponse_QNAME = new QName("http://localhost:8080", "oiResponse");
    private final static QName _Oi_QNAME = new QName("http://localhost:8080", "oi");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: localhost._8080
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link OiResponse }
     * 
     */
    public OiResponse createOiResponse() {
        return new OiResponse();
    }

    /**
     * Create an instance of {@link Oi }
     * 
     */
    public Oi createOi() {
        return new Oi();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link OiResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8080", name = "oiResponse")
    public JAXBElement<OiResponse> createOiResponse(OiResponse value) {
        return new JAXBElement<OiResponse>(_OiResponse_QNAME, OiResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Oi }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://localhost:8080", name = "oi")
    public JAXBElement<Oi> createOi(Oi value) {
        return new JAXBElement<Oi>(_Oi_QNAME, Oi.class, null, value);
    }

}
