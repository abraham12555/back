
package mx.ksms.engine.cl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "EngineService", targetNamespace = "http://cl.engine.ksms.mx/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface EngineService {


    /**
     * 
     * @param servicios
     * @param solicitudId
     * @param asalariado
     * @param edad
     * @param porcentajeDeDescuento
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    @RequestWrapper(localName = "calculateDictamenDePoliticasCasoExtraordinario", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateDictamenDePoliticasCasoExtraordinario")
    @ResponseWrapper(localName = "calculateDictamenDePoliticasCasoExtraordinarioResponse", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateDictamenDePoliticasCasoExtraordinarioResponse")
    public String calculateDictamenDePoliticasCasoExtraordinario(
        @WebParam(name = "solicitudId", targetNamespace = "")
        String solicitudId,
        @WebParam(name = "servicios", targetNamespace = "")
        String servicios,
        @WebParam(name = "EDAD", targetNamespace = "")
        Integer edad,
        @WebParam(name = "porcentajeDeDescuento", targetNamespace = "")
        Double porcentajeDeDescuento,
        @WebParam(name = "asalariado", targetNamespace = "")
        Boolean asalariado);

    /**
     * 
     * @param agRiesgoocupacion
     * @param cantidadIntegrantesFamilia
     * @param cuotaCredito
     * @param solicitudId
     * @param ingresosFijosMensuales
     * @param gastosDeAlquiler
     * @param tipoDeVivienda
     * @param otrosIngresos
     * @param cadenaBc
     * @param agPlazo
     * @param edad
     * @param ingresosVariablesMensuales
     * @param riesgogeografico
     * @param asalariado
     * @param agEstadocivil
     * @param agServicio
     * @param agPeriodicidad
     * @param antigvivienda
     * @param porcentajeDeDescuento
     * @return
     *     returns mx.ksms.engine.cl.EngineDataOutput
     */
    @WebMethod
    @WebResult(name = "EngineDataOutput", targetNamespace = "")
    @RequestWrapper(localName = "calculateProb", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateProb")
    @ResponseWrapper(localName = "calculateProbResponse", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateProbResponse")
    public EngineDataOutput calculateProb(
        @WebParam(name = "solicitudId", targetNamespace = "")
        String solicitudId,
        @WebParam(name = "riesgogeografico", targetNamespace = "")
        String riesgogeografico,
        @WebParam(name = "ag_plazo", targetNamespace = "")
        Integer agPlazo,
        @WebParam(name = "ag_periodicidad", targetNamespace = "")
        String agPeriodicidad,
        @WebParam(name = "ag_riesgoocupacion", targetNamespace = "")
        String agRiesgoocupacion,
        @WebParam(name = "EDAD", targetNamespace = "")
        Integer edad,
        @WebParam(name = "ag_estadocivil", targetNamespace = "")
        String agEstadocivil,
        @WebParam(name = "ag_servicio", targetNamespace = "")
        String agServicio,
        @WebParam(name = "ANTIGVIVIENDA", targetNamespace = "")
        Integer antigvivienda,
        @WebParam(name = "ingresos_fijos_mensuales", targetNamespace = "")
        Double ingresosFijosMensuales,
        @WebParam(name = "ingresos_variables_mensuales", targetNamespace = "")
        Double ingresosVariablesMensuales,
        @WebParam(name = "otros_ingresos", targetNamespace = "")
        Double otrosIngresos,
        @WebParam(name = "cantidad_integrantes_familia", targetNamespace = "")
        Integer cantidadIntegrantesFamilia,
        @WebParam(name = "gastos_de_alquiler", targetNamespace = "")
        Double gastosDeAlquiler,
        @WebParam(name = "cuota_credito", targetNamespace = "")
        Double cuotaCredito,
        @WebParam(name = "tipo_de_vivienda", targetNamespace = "")
        Integer tipoDeVivienda,
        @WebParam(name = "asalariado", targetNamespace = "")
        boolean asalariado,
        @WebParam(name = "cadena_bc", targetNamespace = "")
        String cadenaBc,
        @WebParam(name = "porcentajeDeDescuento", targetNamespace = "")
        Double porcentajeDeDescuento);

    /**
     * 
     * @param solicitudId
     * @param cadenaBc
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "save_cadena_bc")
    @WebResult(name = "String", targetNamespace = "")
    @RequestWrapper(localName = "save_cadena_bc", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.SaveCadenaBc")
    @ResponseWrapper(localName = "save_cadena_bcResponse", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.SaveCadenaBcResponse")
    public String saveCadenaBc(
        @WebParam(name = "solicitudId", targetNamespace = "")
        String solicitudId,
        @WebParam(name = "cadena_bc", targetNamespace = "")
        String cadenaBc);

    /**
     * 
     * @param servicios
     * @param solicitudId
     * @param edad
     * @param porcentajeDeDescuento
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    @RequestWrapper(localName = "calculateDictamenDePoliticas", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateDictamenDePoliticas")
    @ResponseWrapper(localName = "calculateDictamenDePoliticasResponse", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateDictamenDePoliticasResponse")
    public String calculateDictamenDePoliticas(
        @WebParam(name = "solicitudId", targetNamespace = "")
        String solicitudId,
        @WebParam(name = "servicios", targetNamespace = "")
        String servicios,
        @WebParam(name = "EDAD", targetNamespace = "")
        Integer edad,
        @WebParam(name = "porcentajeDeDescuento", targetNamespace = "")
        Double porcentajeDeDescuento);

    /**
     * 
     * @param agRiesgoocupacion
     * @param cuotaCredito
     * @param solicitudId
     * @param ingresosFijosMensuales
     * @param otrosIngresos
     * @param agPlazo
     * @param edad
     * @param ingresosVariablesMensuales
     * @param riesgogeografico
     * @param agEstadocivil
     * @param agServicio
     * @param agPeriodicidad
     * @param antigvivienda
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "String", targetNamespace = "")
    @RequestWrapper(localName = "calculateDictamenDePerfil", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateDictamenDePerfil")
    @ResponseWrapper(localName = "calculateDictamenDePerfilResponse", targetNamespace = "http://cl.engine.ksms.mx/", className = "mx.ksms.engine.cl.CalculateDictamenDePerfilResponse")
    public String calculateDictamenDePerfil(
        @WebParam(name = "solicitudId", targetNamespace = "")
        String solicitudId,
        @WebParam(name = "riesgogeografico", targetNamespace = "")
        String riesgogeografico,
        @WebParam(name = "ag_plazo", targetNamespace = "")
        Integer agPlazo,
        @WebParam(name = "ag_periodicidad", targetNamespace = "")
        String agPeriodicidad,
        @WebParam(name = "ag_riesgoocupacion", targetNamespace = "")
        String agRiesgoocupacion,
        @WebParam(name = "EDAD", targetNamespace = "")
        Integer edad,
        @WebParam(name = "ag_estadocivil", targetNamespace = "")
        String agEstadocivil,
        @WebParam(name = "ag_servicio", targetNamespace = "")
        String agServicio,
        @WebParam(name = "ANTIGVIVIENDA", targetNamespace = "")
        Integer antigvivienda,
        @WebParam(name = "ingresos_fijos_mensuales", targetNamespace = "")
        Double ingresosFijosMensuales,
        @WebParam(name = "ingresos_variables_mensuales", targetNamespace = "")
        Double ingresosVariablesMensuales,
        @WebParam(name = "otros_ingresos", targetNamespace = "")
        Double otrosIngresos,
        @WebParam(name = "cuota_credito", targetNamespace = "")
        Double cuotaCredito);

}
