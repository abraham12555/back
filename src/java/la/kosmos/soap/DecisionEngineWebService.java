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
import mx.ksms.engine.cl.EngineService;
import mx.ksms.engine.cl.EngineServiceService;
import org.apache.log4j.Logger;

public class DecisionEngineWebService {

    private EngineService port;
    private final Logger log = Logger.getLogger(this.getClass());

    public DecisionEngineWebService() {

    }

    public DecisionEngineWebService(String URL) {
        try {
            EngineServiceService service = new EngineServiceService(new URL(URL));
            port = service.getEngineServicePort();
            ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.connectionTimeout", 50000);
            ((BindingProvider) port).getRequestContext().put("javax.xml.ws.client.receiveTimeout", 10000);
        } catch (MalformedURLException e) {
            log.error("Error creating EngineServiceService", e);
        }
    }

    public HashMap doGetScore(String URL, Long solicitudId, String riesgoGeografico, Integer plazo, String periodicidad, String riesgoOcupacion, Integer edad, String estadoCivil, String productoServicio, Integer antiguedadVivienda, Double ingresosFijosMensuales, Double ingresosVariablesMensuales, Double otrosIngresos, Integer dependientesEconomicos, Double gastoRenta, Double cuotaMensualCredito, Integer tipoDeVivienda, boolean asalariado, String cadenaBuroDeCredito) {
        mx.ksms.engine.cl.EngineDataOutput resultado = null;
        HashMap respuesta = null;
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            resultado = port.calculateProb(solicitudId.toString(), riesgoGeografico, plazo, periodicidad, riesgoOcupacion, edad, estadoCivil, productoServicio, antiguedadVivienda, ingresosFijosMensuales, ingresosVariablesMensuales, otrosIngresos, dependientesEconomicos, gastoRenta, cuotaMensualCredito, tipoDeVivienda, asalariado, cadenaBuroDeCredito);

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

    public List<HashMap> getDictamenteDePoliticas(String URL, Long solicitudId, String listaDeServicios, Integer edad) {
        List<HashMap> respuesta = new ArrayList();
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.calculateDictamenDePoliticas(solicitudId.toString(), listaDeServicios, edad);
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

    public HashMap getDictamenteDePerfil(String URL, Long solicitudId, String riesgoGeografico, Integer plazo, String periodicidad, String riesgoOcupacion, Integer edad, String estadoCivil, String claveServicio, Integer antigVivienda, Double ingresosFijosMensuales, Double ingresosVariablesMensuales, Double otrosIngresos, Double cuotaCredito) {
        HashMap respuesta = null;
        try {
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            String resultado = port.calculateDictamenDePerfil(solicitudId.toString(), riesgoGeografico, plazo, periodicidad, riesgoOcupacion, edad, estadoCivil, claveServicio, antigVivienda, ingresosFijosMensuales, ingresosVariablesMensuales, otrosIngresos, cuotaCredito);
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
