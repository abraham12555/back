package la.kosmos.app

import grails.transaction.Transactional

@Transactional
class EmailSenderService {

    def mailService
    
    def envioCorreo(def datosGenerales, def correos, def datosSolicitud) {
        println "******************************** EmailSenderService - BEGIN ***************************************"
        println "Correo obtenido  " + correos
        println "Nombre del cliente  " + datosGenerales
        println "Datos de la Solicitud " + datosSolicitud
        try {
            mailService.sendMail {
                from "confirmaciones@micreditolibertad.com"
                to correos.emailPersonal
                subject (".:" + datosSolicitud?.configuracion?.nombreComercial + ":. Detalles de tu Crédito Libertad ")
                html (view:"/solicitud/correoConfirmacion", model: [configuracion: datosSolicitud?.configuracion, productoSolicitud: datosSolicitud?.productoSolicitud, resultadoMotorDeDecision: datosSolicitud?.resultadoMotorDeDecision, pasoActual: datosSolicitud?.pasoActual])
            }
        } catch(Exception e){
            println "[mailService] Ocurrio un problema al enviar el correo electrónico... " + e.getMessage() 
        }
        println "******************************** EmailSenderService - END ***************************************"
    }
}
