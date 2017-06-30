package la.kosmos.app

import grails.transaction.Transactional
import java.text.DecimalFormat

@Transactional
class EmailSenderService {

    def mailService
    def emailService

    def envioCorreo(def datosGenerales, def correos, def datosSolicitud) {
        println "******************************** EmailSenderService - BEGIN ***************************************"
        println "Correo obtenido  " + correos
        println "Nombre del cliente  " + datosGenerales
        println "Datos de la Solicitud " + datosSolicitud
        def configuracion = datosSolicitud?.configuracion
        def productoSolicitud = datosSolicitud?.productoSolicitud
        def resultadoMotorDeDecision = datosSolicitud?.resultadoMotorDeDecision

        def asunto = ".:" + configuracion?.nombreComercial + ":. Detalles de tu Crédito Libertad "
        def email = correos.emailPersonal

        def map = [:]
        map << [colorBordeSuperior : configuracion?.colorBordeSuperior]
        map << [colorEncabezado : configuracion?.colorEncabezado]
        map << [urlDominio : configuracion?.urlDominio]
        map << [rutaLogotipo : configuracion?.rutaLogotipo]
        map << [clienteNombre : productoSolicitud?.solicitud?.cliente?.nombre]
        map << [clienteApellidoPaterno : productoSolicitud?.solicitud?.cliente?.apellidoPaterno]
        map << [clienteApellidoMaterno : productoSolicitud?.solicitud?.cliente?.apellidoMaterno]
        map << [resultadoMotorDeDecision : resultadoMotorDeDecision]
        map << [montoDelCredito : numberFormat(productoSolicitud?.montoDelCredito)]
        map << [plazos : productoSolicitud?.plazos]
        map << [nomenclatura : (productoSolicitud.periodicidad.nomenclatura).toUpperCase()]
        map << [montoDelSeguroDeDeuda : numberFormat(productoSolicitud?.montoDelSeguroDeDeuda)]
        map << [nombreDelProducto : productoSolicitud?.producto?.nombreDelProducto?.toUpperCase()]
        map << [cat : (productoSolicitud?.producto?.cat) ? ((productoSolicitud?.producto?.cat * 100).round(2)) : 0 ]
        map << [folio : (productoSolicitud?.solicitud?.folio).padLeft(6, '0')]
        map << [sucursal: (productoSolicitud?.solicitud?.sucursal)?(productoSolicitud?.solicitud?.sucursal?.toString()).toUpperCase(): ""]
        map << [sucursalUbicacion : (productoSolicitud?.solicitud?.sucursal)?(productoSolicitud?.solicitud?.sucursal?.ubicacion?.toUpperCase()) : ""]
        map << [documentoElegidoNombre : productoSolicitud?.documentoElegido?.nombre?.toUpperCase()]
        map << [documentoElegidoCantidadSolicitada : productoSolicitud?.documentoElegido?.cantidadSolicitada]
        map << [shortUrl : productoSolicitud?.solicitud?.shortUrl]
        map << [nombreComercial : configuracion?.nombreComercial?.toUpperCase()]
        map << [pasoActual: datosSolicitud?.pasoActual]

        try {
            if(configuracion.emailHost == null || configuracion.emailHost == "" ||
                configuracion.emailFrom == null || configuracion.emailFrom == "" ||
                configuracion.emailPort == null || configuracion.emailPort == "" ||
                configuracion.emailUsername == null || configuracion.emailUsername == "" ||
                configuracion.emailPassword == null || configuracion.emailPassword == "") {
                throw new Exception("No se ha configurado el remitente de correo electronico de la entidad financiera")
            }

            emailService.sendTemplate(configuracion, asunto, email, map)

        } catch(Exception e){
            println "[mailService] Ocurrio un problema al enviar el correo electrónico... " + e.getMessage()
        }
        println "******************************** EmailSenderService - END ***************************************"
    }

    private String numberFormat(amount){
        String format = ""

        if(amount != null){
            DecimalFormat df2 = new DecimalFormat( "###,###,###.##" );
            format =  "\$" + df2.format(amount);
        }
        return format
    }
}
