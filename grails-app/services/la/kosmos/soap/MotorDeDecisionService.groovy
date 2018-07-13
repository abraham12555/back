package la.kosmos.soap

import grails.util.Holders
import grails.transaction.Transactional
import javax.annotation.PostConstruct
import la.kosmos.app.ConfiguracionEntidadFinanciera
import la.kosmos.soap.DecisionEngineCeWebService
import la.kosmos.soap.DecisionEngineWebService
import org.springframework.context.ApplicationContext
import org.codehaus.groovy.grails.commons.GrailsApplication

@Transactional
class MotorDeDecisionService {

    def decisionEngineWebService
    def decisionEngineCeWebService

    @PostConstruct
    def initializeService() {
        ApplicationContext ctx = Holders.getGrailsApplication().getMainContext()
        decisionEngineWebService = (DecisionEngineWebService) ctx.getBean('decisionEngineWebService')
        decisionEngineCeWebService = (DecisionEngineCeWebService) ctx.getBean('decisionEngineCeWebService')
    }

    /*
     * Cliente Nuevos
     */

    def obtenerScore(def entidadFinanciera, def datos)throws Exception {
        println("Entrando a obtenerScore... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision
        def result= decisionEngineWebService.doGetScore(url, datos.solicitudId, datos.riesgoGeografico, datos.plazo, datos.periodicidad, datos.riesgoOcupacion, datos.edad, datos.estadoCivil, datos.productoServicio, datos.antiguedadVivienda, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.dependientesEconomicos, datos.gastoRenta, datos.cuotaMensualCredito, datos.tipoDeVivienda, datos.asalariado, datos.cadenaBuroDeCredito, datos.porcentajeDeDescuento)
        println "Respuesta Recibida: " + result
        return result
    }

    def enviarCadenaDeBuro(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a enviarCadenaDeBuro... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision
        def result= decisionEngineWebService.enviarCadenaDeBuro(url, datos.solicitudId, datos.cadenaBuroDeCredito)
        println "Respuesta Recibida: " + result
        return result
    }

    def obtenerDictamenteDePoliticas(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerDictamenteDePoliticas... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision
        def result= decisionEngineWebService.getDictamenteDePoliticas(url, datos.solicitudId, datos.listaDeServicios, datos.edad, datos.porcentajeDeDescuento)
        println "Respuesta Recibida Politicas SolicitudId: " +datos.solicitudId+ " "+ result
        return result
    }
    def obtenerDictamenteDePoliticasCasoExtraordinario(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerDictamenteDePoliticasCasoExtraordinario... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision
        def result= decisionEngineWebService.getDictamenteDePoliticasCasoExtraordinario(url, datos.solicitudId, datos.listaDeServicios, datos.edad, datos.porcentajeDeDescuento, datos.asalariado)
        println "Respuesta Recibida Politicas Caso extraordinario  SolicitudId: " +datos.solicitudId+ " "+ result
        return result
    }
    
    def obtenerDictamenteDePerfil(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerDictamenteDePerfil... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision
        def result= decisionEngineWebService.getDictamenteDePerfil(url, datos.solicitudId, datos.riesgoGeografico, datos.plazo, datos.periodicidad, datos.riesgoOcupacion, datos.edad, datos.estadoCivil, datos.productoServicio, datos.antiguedadVivienda, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.cuotaMensualCredito)
        println "Respuesta Recibida Dictamen de Perfil Solicitud: "+ datos?.solicitudId+" Producto: "+datos?.productoServicio +" Plazo: "+datos?.plazo+ " "+result
        return result
    }

    /*
     * Cliente Existentes
     */

    def obtenerScoreClienteExistente(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerScoreClienteExistente... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE
        def result= decisionEngineCeWebService.doGetScore(url, datos.solicitudId, datos.riesgoGeografico, datos.renovacion, datos.rdifmspwultcpt12, datos.revoPagos3, datos.propMontoLiberado, datos.fecAntigCliCred, datos.edad, datos.antiguedadEmpleo, datos.riesgoOcupacion, datos.estadoCivil, datos.rcobsldpas12, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.cantidadIntegrantesFamilia, datos.gastosDeAlquiler, datos.cuotaCredito, datos.tipoDeVivienda, datos.asalariado, datos.experienciaCrediticia, datos.creditosLiquidados, datos.cadenaBuroDeCredito,datos.clienteCredVigente,datos.renovacion1,datos.ultimaFechaCredito,datos.avanceCapital1,datos.avanceCapital2,datos.clienteConRenovacion,datos.atrasoPago,datos.malaFe,datos.porcentajeDeDescuento,datos.listaNegra)
        println "Respuesta Recibida: " + result
        return result
    }

    def enviarCadenaDeBuroClienteExistente(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a enviarCadenaDeBuroClienteExistente... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE
        def result= decisionEngineCeWebService.enviarCadenaDeBuro(url, datos.solicitudId, datos.cadenaBuroDeCredito)
        println "Respuesta Recibida: " + result
        return result
    }

    def obtenerDictamenteDePoliticasClienteExistente(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerDictamenteDePoliticasClienteExistente... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE
        def result= decisionEngineCeWebService.getDictamenteDePoliticas(url, datos.solicitudId, datos.listaDeServicios, datos.edad, datos.experienciaCrediticia, datos.creditosLiquidados,datos.clienteCredVigente,datos.renovacion1,datos.ultimaFechaCredito,datos.avanceCapital1,datos.avanceCapital2,datos.clienteConRenovacion,datos.atrasoPago,datos.malaFe,datos.porcentajeDeDescuento,datos.listaNegra) 
        //List<HashMap> respuesta = new ArrayList()
        println "Respuesta Recibida Politicas SolicitudId: " +datos.solicitudId+ " "+ result
        return result
    }
    
     def obtenerDictamenteDePoliticasCasoExtraordinarioCE(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerDictamenteDePoliticasCasoExtraordinarioCE... Configurando servicio...")
        println "entidad financiera"+entidadFinanciera
        println "datos"+datos
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE
        def result= decisionEngineCeWebService.getDictamenteDePoliticasCasoExtraordinarioCE(url, datos.solicitudId, datos.listaDeServicios, datos.edad, datos.experienciaCrediticia, datos.creditosLiquidados,datos.clienteCredVigente,datos.renovacion1,datos.ultimaFechaCredito,datos.avanceCapital1,datos.avanceCapital2,datos.clienteConRenovacion,datos.atrasoPago,datos.malaFe,datos.porcentajeDeDescuento,datos.asalariado,datos.listaNegra) 
        //List<HashMap> respuesta = new ArrayList()
        println "Respuesta Recibida Politicas Caso extraordinario CE SolicitudId: " +datos.solicitudId+ " "+ result
        return result
    }

    def obtenerDictamenteDePerfilClienteExistente(def entidadFinanciera, def datos)throws Exception{
        println("Entrando a obtenerDictamenteDePerfilClienteExistente... Configurando servicio...")
        String url = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE
        def result= decisionEngineCeWebService.getDictamenteDePerfil(url, datos.solicitudId, datos.riesgoGeografico, datos.renovacion, datos.rdifmspwultcpt12, datos.revoPagos3, datos.propMontoLiberado, datos.fecAntigCliCred, datos.edad, datos.antiguedadEmpleo, datos.riesgoOcupacion, datos.estadoCivil, datos.productoServicio, datos.rcobsldpas12)
        println "Respuesta Recibida Dictamen de Perfil Solicitud: "+ datos?.solicitudId+" Producto: "+datos?.productoServicio +"  "+result
        return result
    }

    def getValues() {
        def map = [:]
        map.decisionEngineWebService = decisionEngineWebService.hashCode()
        map.decisionEngineCeWebService = decisionEngineCeWebService.hashCode()
        return map
    }
}
