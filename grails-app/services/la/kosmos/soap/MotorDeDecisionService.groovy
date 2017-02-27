package la.kosmos.soap

import grails.transaction.Transactional
import la.kosmos.app.ConfiguracionEntidadFinanciera

@Transactional
class MotorDeDecisionService {

    def obtenerScore(def entidadFinanciera, def datos) {
        def proxy = new DecisionEngineWebService()
        println("Entrando a obtenerScore... Configurando servicio...")
        proxy.setURL(ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)?.wsdlMotorDeDecision)
        println("Invoncando servicio...")
        def result= proxy.doGetScore(datos.solicitudId, datos.riesgoGeografico, datos.plazo, datos.periodicidad, datos.riesgoOcupacion, datos.edad, datos.estadoCivil, datos.productoServicio, datos.antiguedadVivienda, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.otrosIngresos, datos.dependientesEconomicos, datos.gastoRenta, datos.cuotaMensualCredito, datos.tipoDeVivienda, datos.cadenaBuroDeCredito)
        println "Respuesta Recibida: " + result
        return result
    }
}
