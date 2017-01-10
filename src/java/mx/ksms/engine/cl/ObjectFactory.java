
package mx.ksms.engine.cl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.ksms.engine.cl package. 
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

    private final static QName _CalculateProb_QNAME = new QName("http://cl.engine.ksms.mx/", "calculateProb");
    private final static QName _CalculateProbResponse_QNAME = new QName("http://cl.engine.ksms.mx/", "calculateProbResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.ksms.engine.cl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateProbResponse }
     * 
     */
    public CalculateProbResponse createCalculateProbResponse() {
        return new CalculateProbResponse();
    }

    /**
     * Create an instance of {@link CalculateProb }
     * 
     */
    public CalculateProb createCalculateProb() {
        return new CalculateProb();
    }

    /**
     * Create an instance of {@link EngineDataOutput }
     * 
     */
    public EngineDataOutput createEngineDataOutput() {
        return new EngineDataOutput();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateProb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cl.engine.ksms.mx/", name = "calculateProb")
    public JAXBElement<CalculateProb> createCalculateProb(CalculateProb value) {
        return new JAXBElement<CalculateProb>(_CalculateProb_QNAME, CalculateProb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateProbResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://cl.engine.ksms.mx/", name = "calculateProbResponse")
    public JAXBElement<CalculateProbResponse> createCalculateProbResponse(CalculateProbResponse value) {
        return new JAXBElement<CalculateProbResponse>(_CalculateProbResponse_QNAME, CalculateProbResponse.class, null, value);
    }

}
