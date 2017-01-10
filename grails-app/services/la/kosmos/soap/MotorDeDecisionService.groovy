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
        def result= proxy.doGetScore(datos.riesgoGeografico, datos.plazo, datos.periodicidad, datos.cuoIngfu, datos.riesgoOcupacion, datos.edad, datos.cobSldPas12, datos.estadoCivil, datos.productoServicio, datos.indicaPasNoVista, datos.antiguedadVivienda, datos.hist12Hist24, datos.mopPeorSis, datos.dsptoCon, datos.conoe1ConD1, datos.conoe1conOe, datos.ingresosFijosMensuales, datos.ingresosVariablesMensuales, datos.gastoMensuales, datos.cuotaMensualCredito)
        println "Respuesta Recibida: " + result
        return result
    }
}
