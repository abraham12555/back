package la.kosmos.soap;

/**
 *
 * @author miklex
 */
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import mx.ksms.engine.cl.EngineDataOutput;
import mx.ksms.engine.cl.EngineService;
import mx.ksms.engine.cl.EngineServiceService;

public class DecisionEngineWebService {

    String path;

    public void setURL(String url) {
        path = url;
    }

    public String getURL() {
        return path;
    }

    public EngineDataOutput doGetScore(String riesgoGeografico, Integer plazo, String periodicidad, Double cuoIngfu, String riesgoOcupacion, Integer edad, Double cobSldPas12, String estadoCivil, String productoServicio, String indicaPasNoVista, Integer antiguedadVivienda, Double hist12Hist24, Double mopPeorSis, Double dsptoCon, String conoe1ConD1, Double conoe1conOe, Double ingresosFijosMensuales, Double ingresosVariablesMensuales, Double gastoMensuales, Double cuotaMensualCredito) {
        String URL = getURL();
        mx.ksms.engine.cl.EngineDataOutput resultado = null;
        try {
            URL url = new URL(URL);
            QName qname1 = new QName("http://cl.engine.ksms.mx/", "EngineServiceService");
            EngineServiceService service = new EngineServiceService(url, qname1);
            EngineService port = service.getEngineServicePort();
            ((BindingProvider) port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, URL);
            System.out.println("Si lo alcanza o no? " + riesgoGeografico + ", " + plazo + ", " + periodicidad + ", " + cuoIngfu + ", " + riesgoOcupacion + ", " + edad + ", " + cobSldPas12 + ", " + estadoCivil + ", " + productoServicio + ", " + indicaPasNoVista + ", " + antiguedadVivienda + ", " + hist12Hist24 + ", " + mopPeorSis + ", " + dsptoCon + ", " + conoe1ConD1 + ", " + conoe1conOe + ", " + ingresosFijosMensuales + ", " + ingresosVariablesMensuales + ", " + gastoMensuales + ", " + cuotaMensualCredito);
            resultado = port.calculateProb(riesgoGeografico, plazo, periodicidad, cuoIngfu, riesgoOcupacion, edad, cobSldPas12, estadoCivil, productoServicio, indicaPasNoVista, antiguedadVivienda, hist12Hist24, mopPeorSis, dsptoCon, conoe1ConD1, conoe1conOe, ingresosFijosMensuales, ingresosVariablesMensuales, gastoMensuales, cuotaMensualCredito);
            System.out.println("dictamenDePerfil: " + resultado.getDictamenDePerfil());
            System.out.println("dictamenCapacidadDePago: " + resultado.getDictamenCapacidadDePago());
            System.out.println("dictamenConjunto: " + resultado.getDictamenConjunto());
            System.out.println("probabilidadDeMora: " + resultado.getProbabilidadDeMora());
            System.out.println("razonDeCobertura: " + resultado.getRazonDeCobertura());
            System.out.println("riesgoGeografico: " + resultado.getRiesgogeografico());
            System.out.println("agPlazo: " + resultado.getAgPlazo());
            System.out.println("agPeriodicidad: " + resultado.getAgPeriodicidad());
            System.out.println("rcuoingfu: " + resultado.getRCUOINGFU());
            System.out.println("agRiesgoocupacion: " + resultado.getAgRiesgoocupacion());
            System.out.println("edad: " + resultado.getEDAD());
            System.out.println("rcobsldpas12: " + resultado.getRCOBSLDPAS12());
            System.out.println("agEstadocivil: " + resultado.getAgEstadocivil());
            System.out.println("agServicio: " + resultado.getAgServicio());
            System.out.println("indicapasnovista: " + resultado.getINDICAPASNOVISTA());
            System.out.println("antigVivienda: " + resultado.getANTIGVIVIENDA());
            System.out.println("agHist12Hist24: " + resultado.getAgHist12Hist24());
            System.out.println("agMopPeorSis: " + resultado.getAgMopPeorSis());
            System.out.println("rdsptoconsis: " + resultado.getRDSPTOCONSIS());
            System.out.println("d1RConoe1ConOe: " + resultado.getD1RConoe1ConOe());
            System.out.println("rconoe1CONOE: " + resultado.getRCONOE1CONOE());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Ocurrio un error al consumir el ws");
        } finally {
            return resultado;
        }
    }
}
