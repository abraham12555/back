
package mx.ksms.engine.ce;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the mx.ksms.engine.ce package. 
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

    private final static QName _CalculateDictamenDePerfil_QNAME = new QName("http://ce.engine.ksms.mx/", "calculateDictamenDePerfil");
    private final static QName _CalculateDictamenDePoliticasResponse_QNAME = new QName("http://ce.engine.ksms.mx/", "calculateDictamenDePoliticasResponse");
    private final static QName _CalculateDictamenDePoliticas_QNAME = new QName("http://ce.engine.ksms.mx/", "calculateDictamenDePoliticas");
    private final static QName _CalculateProb_QNAME = new QName("http://ce.engine.ksms.mx/", "calculateProb");
    private final static QName _SaveCadenaBc_QNAME = new QName("http://ce.engine.ksms.mx/", "save_cadena_bc");
    private final static QName _CalculateDictamenDePerfilResponse_QNAME = new QName("http://ce.engine.ksms.mx/", "calculateDictamenDePerfilResponse");
    private final static QName _CalculateProbResponse_QNAME = new QName("http://ce.engine.ksms.mx/", "calculateProbResponse");
    private final static QName _SaveCadenaBcResponse_QNAME = new QName("http://ce.engine.ksms.mx/", "save_cadena_bcResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: mx.ksms.engine.ce
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CalculateDictamenDePerfilResponse }
     * 
     */
    public CalculateDictamenDePerfilResponse createCalculateDictamenDePerfilResponse() {
        return new CalculateDictamenDePerfilResponse();
    }

    /**
     * Create an instance of {@link CalculateProbResponse }
     * 
     */
    public CalculateProbResponse createCalculateProbResponse() {
        return new CalculateProbResponse();
    }

    /**
     * Create an instance of {@link SaveCadenaBcResponse }
     * 
     */
    public SaveCadenaBcResponse createSaveCadenaBcResponse() {
        return new SaveCadenaBcResponse();
    }

    /**
     * Create an instance of {@link CalculateDictamenDePoliticasResponse }
     * 
     */
    public CalculateDictamenDePoliticasResponse createCalculateDictamenDePoliticasResponse() {
        return new CalculateDictamenDePoliticasResponse();
    }

    /**
     * Create an instance of {@link CalculateDictamenDePoliticas }
     * 
     */
    public CalculateDictamenDePoliticas createCalculateDictamenDePoliticas() {
        return new CalculateDictamenDePoliticas();
    }

    /**
     * Create an instance of {@link CalculateDictamenDePerfil }
     * 
     */
    public CalculateDictamenDePerfil createCalculateDictamenDePerfil() {
        return new CalculateDictamenDePerfil();
    }

    /**
     * Create an instance of {@link CalculateProb }
     * 
     */
    public CalculateProb createCalculateProb() {
        return new CalculateProb();
    }

    /**
     * Create an instance of {@link SaveCadenaBc }
     * 
     */
    public SaveCadenaBc createSaveCadenaBc() {
        return new SaveCadenaBc();
    }

    /**
     * Create an instance of {@link EngineCEDataOutput }
     * 
     */
    public EngineCEDataOutput createEngineCEDataOutput() {
        return new EngineCEDataOutput();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateDictamenDePerfil }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "calculateDictamenDePerfil")
    public JAXBElement<CalculateDictamenDePerfil> createCalculateDictamenDePerfil(CalculateDictamenDePerfil value) {
        return new JAXBElement<CalculateDictamenDePerfil>(_CalculateDictamenDePerfil_QNAME, CalculateDictamenDePerfil.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateDictamenDePoliticasResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "calculateDictamenDePoliticasResponse")
    public JAXBElement<CalculateDictamenDePoliticasResponse> createCalculateDictamenDePoliticasResponse(CalculateDictamenDePoliticasResponse value) {
        return new JAXBElement<CalculateDictamenDePoliticasResponse>(_CalculateDictamenDePoliticasResponse_QNAME, CalculateDictamenDePoliticasResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateDictamenDePoliticas }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "calculateDictamenDePoliticas")
    public JAXBElement<CalculateDictamenDePoliticas> createCalculateDictamenDePoliticas(CalculateDictamenDePoliticas value) {
        return new JAXBElement<CalculateDictamenDePoliticas>(_CalculateDictamenDePoliticas_QNAME, CalculateDictamenDePoliticas.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateProb }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "calculateProb")
    public JAXBElement<CalculateProb> createCalculateProb(CalculateProb value) {
        return new JAXBElement<CalculateProb>(_CalculateProb_QNAME, CalculateProb.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCadenaBc }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "save_cadena_bc")
    public JAXBElement<SaveCadenaBc> createSaveCadenaBc(SaveCadenaBc value) {
        return new JAXBElement<SaveCadenaBc>(_SaveCadenaBc_QNAME, SaveCadenaBc.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateDictamenDePerfilResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "calculateDictamenDePerfilResponse")
    public JAXBElement<CalculateDictamenDePerfilResponse> createCalculateDictamenDePerfilResponse(CalculateDictamenDePerfilResponse value) {
        return new JAXBElement<CalculateDictamenDePerfilResponse>(_CalculateDictamenDePerfilResponse_QNAME, CalculateDictamenDePerfilResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CalculateProbResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "calculateProbResponse")
    public JAXBElement<CalculateProbResponse> createCalculateProbResponse(CalculateProbResponse value) {
        return new JAXBElement<CalculateProbResponse>(_CalculateProbResponse_QNAME, CalculateProbResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SaveCadenaBcResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ce.engine.ksms.mx/", name = "save_cadena_bcResponse")
    public JAXBElement<SaveCadenaBcResponse> createSaveCadenaBcResponse(SaveCadenaBcResponse value) {
        return new JAXBElement<SaveCadenaBcResponse>(_SaveCadenaBcResponse_QNAME, SaveCadenaBcResponse.class, null, value);
    }

}
