
package mx.ksms.engine.cl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "EngineServiceService", targetNamespace = "http://cl.engine.ksms.mx/", wsdlLocation = "http://192.168.0.183:8080/decision-engine-ws/services/engine?wsdl")
public class EngineServiceService
    extends Service
{

    private final static URL ENGINESERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException ENGINESERVICESERVICE_EXCEPTION;
    private final static QName ENGINESERVICESERVICE_QNAME = new QName("http://cl.engine.ksms.mx/", "EngineServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.0.183:8080/decision-engine-ws/services/engine?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ENGINESERVICESERVICE_WSDL_LOCATION = url;
        ENGINESERVICESERVICE_EXCEPTION = e;
    }

    public EngineServiceService() {
        super(__getWsdlLocation(), ENGINESERVICESERVICE_QNAME);
    }

    public EngineServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENGINESERVICESERVICE_QNAME, features);
    }

    public EngineServiceService(URL wsdlLocation) {
        super(wsdlLocation, ENGINESERVICESERVICE_QNAME);
    }

    public EngineServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENGINESERVICESERVICE_QNAME, features);
    }

    public EngineServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EngineServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EngineService
     */
    @WebEndpoint(name = "EngineServicePort")
    public EngineService getEngineServicePort() {
        return super.getPort(new QName("http://cl.engine.ksms.mx/", "EngineServicePort"), EngineService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EngineService
     */
    @WebEndpoint(name = "EngineServicePort")
    public EngineService getEngineServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://cl.engine.ksms.mx/", "EngineServicePort"), EngineService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENGINESERVICESERVICE_EXCEPTION!= null) {
            throw ENGINESERVICESERVICE_EXCEPTION;
        }
        return ENGINESERVICESERVICE_WSDL_LOCATION;
    }

}
