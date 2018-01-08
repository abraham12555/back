package la.kosmos.soap

import grails.transaction.Transactional
import la.kosmos.app.ConfiguracionEntidadFinanciera

@Transactional
class MotorDeDecisionService {

    /*
     * Cliente Nuevos
     */
    
    def obtenerScore(def entidadFinanciera, def datos) {
        def proxy = new DecisionEngineWebService()
        println("Entrando a obtenerScore... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision)
        println("Invoncando servicio...")
        def result= proxy.doGetScore(datos.solicitudId, datos.riesgoGeografico, datos.plazo, datos.periodicidad, datos.riesgoOcupacion, datos.edad, datos.estadoCivil, datos.productoServicio, datos.antiguedadVivienda, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.dependientesEconomicos, datos.gastoRenta, datos.cuotaMensualCredito, datos.tipoDeVivienda, datos.asalariado, datos.cadenaBuroDeCredito,datos.porcentajeDeDescuento)
        println "Respuesta Recibida: " + result
        return result
    }
    
    def enviarCadenaDeBuro(def entidadFinanciera, def datos){
        def proxy = new DecisionEngineWebService()
        println("Entrando a enviarCadenaDeBuro... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision)
        println("Invoncando servicio...")
        def result= proxy.enviarCadenaDeBuro(datos.solicitudId, datos.cadenaBuroDeCredito)
        println "Respuesta Recibida: " + result
        return result
    }
    
    def obtenerDictamenteDePoliticas(def entidadFinanciera, def datos){
        def proxy = new DecisionEngineWebService()
        println("Entrando a obtenerDictamenteDePoliticas... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision)
        println("Invoncando servicio...")
        def result= proxy.getDictamenteDePoliticas(datos.solicitudId, datos.listaDeServicios, datos.edad)
        println "Respuesta Recibida: " + result
        return result
    }
    
    def obtenerDictamenteDePerfil(def entidadFinanciera, def datos){
        def proxy = new DecisionEngineWebService()
        println("Entrando a obtenerDictamenteDePerfil... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision)
        println("Invoncando servicio...")
        def result= proxy.getDictamenteDePerfil(datos.solicitudId, datos.riesgoGeografico, datos.plazo, datos.periodicidad, datos.riesgoOcupacion, datos.edad, datos.estadoCivil, datos.productoServicio, datos.antiguedadVivienda, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.cuotaMensualCredito)
        println "Respuesta Recibida: " + result
        return result
    }
    
    /*
     * Cliente Existentes
     */
    
    def obtenerScoreClienteExistente(def entidadFinanciera, def datos) {
        def proxy = new DecisionEngineCeWebService()
        println("Entrando a obtenerScoreClienteExistente... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE)
        println("Invoncando servicio...")
        def result= proxy.doGetScore(datos.solicitudId, datos.riesgoGeografico, datos.renovacion, datos.rdifmspwultcpt12, datos.revoPagos3, datos.propMontoLiberado, datos.fecAntigCliCred, datos.edad, datos.antiguedadEmpleo, datos.riesgoOcupacion, datos.estadoCivil, datos.rcobsldpas12, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.cantidadIntegrantesFamilia, datos.gastosDeAlquiler, datos.cuotaCredito, datos.tipoDeVivienda, datos.asalariado, datos.experienciaCrediticia, datos.creditosLiquidados, datos.cadenaBuroDeCredito,datos.porcentajeDeDescuento)
        println "Respuesta Recibida: " + result
        return result
    }
    
    def enviarCadenaDeBuroClienteExistente(def entidadFinanciera, def datos){
        def proxy = new DecisionEngineCeWebService()
        println("Entrando a enviarCadenaDeBuroClienteExistente... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE)
        println("Invoncando servicio...")
        def result= proxy.enviarCadenaDeBuro(datos.solicitudId, datos.cadenaBuroDeCredito)
        println "Respuesta Recibida: " + result
        return result
    }
    
    def obtenerDictamenteDePoliticasClienteExistente(def entidadFinanciera, def datos){
        def proxy = new DecisionEngineCeWebService()
        println("Entrando a obtenerDictamenteDePoliticasClienteExistente... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE)
        println("Invoncando servicio...")
        def result= proxy.getDictamenteDePoliticas(datos.solicitudId, datos.listaDeServicios, datos.edad, datos.experienciaCrediticia, datos.creditosLiquidados)
        println "Respuesta Recibida: " + result
        return result
    }
    
    def obtenerDictamenteDePerfilClienteExistente(def entidadFinanciera, def datos){
        def proxy = new DecisionEngineCeWebService()
        println("Entrando a obtenerDictamenteDePerfilClienteExistente... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecisionCE)
        println("Invoncando servicio...")  //[solicitudId:50, riesgoGeografico:GEOGRAFICOIII, riesgoOcupacion:NOINFORMADO, edad:0, estadoCivil:SOLTERO, renovacion:N, rdifmspwultcpt12:0.0, revoPagos3:0.0, propMontoLiberado:0.0, fecAntigCliCred:0, antiguedadEmpleo:0, rcobsldpas12:0.0]
        def result= proxy.getDictamenteDePerfil(datos.solicitudId, datos.riesgoGeografico, datos.renovacion, datos.rdifmspwultcpt12, datos.revoPagos3, datos.propMontoLiberado, datos.fecAntigCliCred, datos.edad, datos.antiguedadEmpleo, datos.riesgoOcupacion, datos.estadoCivil, datos.productoServicio, datos.rcobsldpas12)
        println "Respuesta Recibida: " + result
        return result
    }
}
