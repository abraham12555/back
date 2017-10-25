package la.kosmos.soap;

/**
 *
 * @author miklex
 */
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import javax.xml.ws.BindingProvider;
import mx.ksms.engine.ce.EngineCEService;
import mx.ksms.engine.ce.EngineCEServiceService;
import org.apache.log4j.Logger;

public class DecisionEngineCeWebService {

    private EngineCEService port;
    private final Logger log = Logger.getLogger(this.getClass());

    public DecisionEngineCeWebService() {

    }

    public DecisionEngineCeWebService(String URL) {
        try {
            EngineCEServiceService service = new EngineCEServiceService(new URL(URL));
            port = service.getEngineCEServicePort();
            ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.connectionTimeout", 50000);
            ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.receiveTimeout", 10000);
        } catch (MalformedURLException e) {
            log.error("Error creating EngineCEServiceService", e);
        }
    }

    public HashMap doGetScore(String URL, Long solicitudId, String riesgoGeografico, String renovacion, Double rdifmspwultcpt12, Double revoPagos3, Double propMontoLiberado, Double fecAntigCliCred, Integer edad, Integer antiguedadEmpleo, String riesgoOcupacion, String estadoCivil, String claveServicio, Double rcobsldpas12, Double ingresosFijosMensuales, Double ingresosVariablesMensuales, Double otrosIngresos, Integer cantidadIntegrantesFamilia, Double gastosDeAlquiler, Double cuotaCredito, Integer tipoDeVivienda, boolean asalariado, boolean experienciaCrediticia, Integer creditosLiquidados, String cadenaBuroDeCredito) {

        mx.ksms.engine.ce.EngineCEDataOutput resultado = null;
        HashMap respuesta = null;
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            resultado = port.calculateProb(solicitudId.toString(), riesgoGeografico, renovacion, rdifmspwultcpt12, revoPagos3, propMontoLiberado, fecAntigCliCred, edad, antiguedadEmpleo, riesgoOcupacion, estadoCivil, claveServicio, rcobsldpas12, ingresosFijosMensuales, ingresosVariablesMensuales, otrosIngresos, cantidadIntegrantesFamilia, gastosDeAlquiler, cuotaCredito, tipoDeVivienda, asalariado, experienciaCrediticia, creditosLiquidados, cadenaBuroDeCredito);
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
            log.error("Ocurrio un error al consumir el ws", ex);
        } finally {
            return respuesta;
        }
    }

    public boolean enviarCadenaDeBuro(String URL, Long solicitudId, String cadenaBuroDeCredito) {
        boolean enviadoCorrectamente = false;
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.saveCadenaBc(solicitudId.toString(), cadenaBuroDeCredito);
            Long valorDevuelto = Long.parseLong(resultado);
            if (valorDevuelto.equals(solicitudId)) {
                enviadoCorrectamente = true;
            }
        } catch (Exception e) {
            log.error("Ocurrio un error al consumir el ws enviarCadenaDeBuro", e);
        } finally {
            return enviadoCorrectamente;
        }
    }

    public List<HashMap> getDictamenteDePoliticas(String URL, Long solicitudId, String listaDeServicios, Integer edad, boolean experienciaCrediticia, Integer creditosLiquidados) {
        List<HashMap> respuesta = new ArrayList();
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.calculateDictamenDePoliticas(solicitudId.toString(), listaDeServicios, edad, experienciaCrediticia, creditosLiquidados);
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
            log.error("Ocurrio un error al consumir el ws getDictamenteDePoliticas", e);
        } finally {
            return respuesta;
        }
    }

    public HashMap getDictamenteDePerfil(String URL, Long solicitudId, String riesgoGeografico, String renovacion, Double rdifmspwultcpt12, Double revoPagos3, Double propMontoLiberado, Double fecAntigCliCred, Integer edad, Integer antiguedadEmpleo, String riesgoOcupacion, String estadocivil, String claveServicio, Double rcobsldpas12) {
        HashMap respuesta = null;
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.calculateDictamenDePerfil(solicitudId.toString(), riesgoGeografico, renovacion, rdifmspwultcpt12, revoPagos3, propMontoLiberado, fecAntigCliCred, edad, antiguedadEmpleo, riesgoOcupacion, estadocivil, claveServicio, rcobsldpas12);
            StringTokenizer dictamen = new StringTokenizer(resultado, "|");
            if (dictamen.hasMoreElements()) {
                respuesta = new HashMap();
                respuesta.put("dictamen", dictamen.nextElement().toString());
                respuesta.put("probabilidadDeMora", Double.parseDouble(dictamen.nextElement().toString()));
            }
        } catch (Exception e) {
            log.error("Ocurrio un error al consumir el ws getDictamenteDePerfil", e);
        } finally {
            return respuesta;
        }
    }
}
