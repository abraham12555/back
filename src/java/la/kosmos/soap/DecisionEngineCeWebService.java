package la.kosmos.soap;

/**
 *
 * @author miklex
 */
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import mx.ksms.engine.ce.EngineCEDataOutput;
import mx.ksms.engine.ce.EngineCEService;
import mx.ksms.engine.ce.EngineCEServiceService;

public class DecisionEngineCeWebService {

    String path;

    public void setURL(String url) {
        path = url;
    }

    public String getURL() {
        return path;
    }

    public HashMap doGetScore(Long solicitudId, String riesgoGeografico, String renovacion, Double rdifmspwultcpt12, Double revoPagos3, Double propMontoLiberado, Double fecAntigCliCred, Integer edad, Integer antiguedadEmpleo, String riesgoOcupacion, String estadoCivil, String claveServicio, Double rcobsldpas12, Double ingresosFijosMensuales, Double ingresosVariablesMensuales, Double otrosIngresos, Integer cantidadIntegrantesFamilia, Double gastosDeAlquiler, Double cuotaCredito, Integer tipoDeVivienda, boolean asalariado, String cadenaBuroDeCredito) {
        String URL = getURL();
        mx.ksms.engine.ce.EngineCEDataOutput resultado = null;
        HashMap respuesta = null;
        try {
            URL url = new URL(URL);
            QName qname1 = new QName("http://ce.engine.ksms.mx/", "EngineCEServiceService");
            EngineCEServiceService service = new EngineCEServiceService(url, qname1);
            EngineCEService port = service.getEngineCEServicePort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            resultado = port.calculateProb(solicitudId.toString(), riesgoGeografico, renovacion, rdifmspwultcpt12, revoPagos3, propMontoLiberado, fecAntigCliCred, edad, antiguedadEmpleo, riesgoOcupacion, estadoCivil, claveServicio, rcobsldpas12, ingresosFijosMensuales, ingresosVariablesMensuales, otrosIngresos, cantidadIntegrantesFamilia, gastosDeAlquiler, cuotaCredito, tipoDeVivienda, asalariado, cadenaBuroDeCredito);
            System.out.println("dictamenDePerfil: " + resultado.getDictamenDePerfil());
            System.out.println("dictamenCapacidadDePago: " + resultado.getDictamenCapacidadDePago());
            System.out.println("dictamenConjunto: " + resultado.getDictamenConjunto());
            System.out.println("probabilidadDeMora: " + resultado.getProbabilidadDeMora());
            System.out.println("razonDeCobertura: " + resultado.getRazonDeCobertura());
            System.out.println("dictamenDePoliticas: " + resultado.getDictamenDePoliticas());
            System.out.println("log: " + resultado.getLog());
            respuesta = new HashMap();
            respuesta.put("dictamenDePerfil", resultado.getDictamenDePerfil());
            respuesta.put("dictamenCapacidadDePago", resultado.getDictamenCapacidadDePago());
            respuesta.put("dictamenConjunto", resultado.getDictamenConjunto());
            respuesta.put("probabilidadDeMora", resultado.getProbabilidadDeMora());
            respuesta.put("razonDeCobertura", resultado.getRazonDeCobertura());
            respuesta.put("dictamenDePoliticas", resultado.getDictamenDePoliticas());
            respuesta.put("dictamenFinal", resultado.getDictamenFinal());
            respuesta.put("log", resultado.getLog());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ocurrio un error al consumir el ws");
        } finally {
            return respuesta;
        }
    }

    public boolean enviarCadenaDeBuro(Long solicitudId, String cadenaBuroDeCredito) {
        boolean enviadoCorrectamente = false;
        String URL = getURL();
        try {
            URL url = new URL(URL);
            QName qname1 = new QName("http://ce.engine.ksms.mx/", "EngineCEServiceService");
            EngineCEServiceService service = new EngineCEServiceService(url, qname1);
            EngineCEService port = service.getEngineCEServicePort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.saveCadenaBc(solicitudId.toString(), cadenaBuroDeCredito);
            Long valorDevuelto = Long.parseLong(resultado);
            if (valorDevuelto.equals(solicitudId)) {
                enviadoCorrectamente = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al consumir el ws enviarCadenaDeBuro");
        } finally {
            return enviadoCorrectamente;
        }
    }

    public List<HashMap> getDictamenteDePoliticas(Long solicitudId, String listaDeServicios) {
        String URL = getURL();
        List<HashMap> respuesta = new ArrayList();
        try {
            URL url = new URL(URL);
            QName qname1 = new QName("http://ce.engine.ksms.mx/", "EngineCEServiceService");
            EngineCEServiceService service = new EngineCEServiceService(url, qname1);
            EngineCEService port = service.getEngineCEServicePort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.calculateDictamenDePoliticas(solicitudId.toString(), listaDeServicios);
            StringTokenizer servicios = new StringTokenizer(resultado, ",");
            while (servicios.hasMoreElements()) {
                StringTokenizer dictamen = new StringTokenizer(servicios.nextElement().toString(), "=");
                while (dictamen.hasMoreElements()) {
                    HashMap dictamenServicio = new HashMap();
                    dictamenServicio.put(dictamen.nextElement().toString(), dictamen.nextElement().toString());
                    respuesta.add(dictamenServicio);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al consumir el ws getDictamenteDePoliticas");
        } finally {
            return respuesta;
        }
    }

    public HashMap getDictamenteDePerfil(Long solicitudId, String riesgoGeografico, String renovacion, Double rdifmspwultcpt12, Double revoPagos3, Double propMontoLiberado, Double fecAntigCliCred, Integer edad, Integer antiguedadEmpleo, String riesgoOcupacion, String estadocivil, String claveServicio, Double rcobsldpas12) {
        String URL = getURL();
        HashMap respuesta = null;
        try {
            URL url = new URL(URL);
            QName qname1 = new QName("http://ce.engine.ksms.mx/", "EngineCEServiceService");
            EngineCEServiceService service = new EngineCEServiceService(url, qname1);
            EngineCEService port = service.getEngineCEServicePort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.calculateDictamenDePerfil(solicitudId.toString(), riesgoGeografico, renovacion, rdifmspwultcpt12, revoPagos3, propMontoLiberado, fecAntigCliCred, edad, antiguedadEmpleo, riesgoOcupacion, estadocivil, claveServicio, rcobsldpas12);
            StringTokenizer dictamen = new StringTokenizer(resultado, "|");
            if (dictamen.hasMoreElements()) {
                respuesta = new HashMap();
                respuesta.put("dictamen", dictamen.nextElement().toString());
                respuesta.put("probabilidadDeMora", Double.parseDouble(dictamen.nextElement().toString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrio un error al consumir el ws getDictamenteDePerfil");
        } finally {
            return respuesta;
        }
    }
}
