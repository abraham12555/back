
package mx.ksms.engine.ce;

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
@WebServiceClient(name = "EngineCEServiceService", targetNamespace = "http://ce.engine.ksms.mx/", wsdlLocation = "http://192.168.0.183:8080/decision-engine-ws/services/engineCE?wsdl")
public class EngineCEServiceService
    extends Service
{

    private final static URL ENGINECESERVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException ENGINECESERVICESERVICE_EXCEPTION;
    private final static QName ENGINECESERVICESERVICE_QNAME = new QName("http://ce.engine.ksms.mx/", "EngineCEServiceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.0.183:8080/decision-engine-ws/services/engineCE?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        ENGINECESERVICESERVICE_WSDL_LOCATION = url;
        ENGINECESERVICESERVICE_EXCEPTION = e;
    }

    public EngineCEServiceService() {
        super(__getWsdlLocation(), ENGINECESERVICESERVICE_QNAME);
    }

    public EngineCEServiceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), ENGINECESERVICESERVICE_QNAME, features);
    }

    public EngineCEServiceService(URL wsdlLocation) {
        super(wsdlLocation, ENGINECESERVICESERVICE_QNAME);
    }

    public EngineCEServiceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, ENGINECESERVICESERVICE_QNAME, features);
    }

    public EngineCEServiceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public EngineCEServiceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns EngineCEService
     */
    @WebEndpoint(name = "EngineCEServicePort")
    public EngineCEService getEngineCEServicePort() {
        return super.getPort(new QName("http://ce.engine.ksms.mx/", "EngineCEServicePort"), EngineCEService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns EngineCEService
     */
    @WebEndpoint(name = "EngineCEServicePort")
    public EngineCEService getEngineCEServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ce.engine.ksms.mx/", "EngineCEServicePort"), EngineCEService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (ENGINECESERVICESERVICE_EXCEPTION!= null) {
            throw ENGINECESERVICESERVICE_EXCEPTION;
        }
        return ENGINECESERVICESERVICE_WSDL_LOCATION;
    }

}
