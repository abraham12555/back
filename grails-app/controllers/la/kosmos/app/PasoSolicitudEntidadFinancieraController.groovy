package la.kosmos.app
import grails.converters.JSON

class PasoSolicitudEntidadFinancieraController {
    def springSecurityService

    def index() { }
    
    def obtenerDetallePasoSolicitud(PasoSolicitudEntidadFinanciera pasoSolicitudEntidadFinanciera) {
        println params
        def faltanTipos = []
        def tipoDePasoSolicitud = TipoDePasoSolicitud.findAll()
         tipoDePasoSolicitud.each{
           if (!pasoSolicitudEntidadFinanciera.tipoDePaso.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltanTipos << respuesta
           }
         }
        render(template: "/dashboard/configuracion/pasoSolicitudEntidadFinanciera/detallePasoSolicitud", model: [pasoSolicitudEntidadFinanciera: pasoSolicitudEntidadFinanciera,faltanTipos:faltanTipos])
    }
    def update (){
        println params
        def respuesta = [:]
        def pasoSolicitudEntidadFinanciera = PasoSolicitudEntidadFinanciera.get(params.idPasoSolicitudEntidadFinanciera)
        pasoSolicitudEntidadFinanciera.titulo = params.titulo
        pasoSolicitudEntidadFinanciera.subtitulo = params.subtitulo
        pasoSolicitudEntidadFinanciera.numeroDePaso = params.numeroDePaso as long
        pasoSolicitudEntidadFinanciera.ponderacion  = params.ponderacion as long
        pasoSolicitudEntidadFinanciera.modoDeDespliegue = params.modoDeDespliegue
        pasoSolicitudEntidadFinanciera.mostrarEnBarra = params.mostrarEnBarra.toBoolean()
        pasoSolicitudEntidadFinanciera.requiereSubirComprobante = params.requiereSubirComprobante.toBoolean()
        pasoSolicitudEntidadFinanciera.requiereSubirIdentificacion = params.requiereSubirIdentificacion.toBoolean()
        pasoSolicitudEntidadFinanciera.ultimoPaso = params.ultimoPaso.toBoolean()
        pasoSolicitudEntidadFinanciera.mostrarEnBarra = params.mostrarEnBarra
        pasoSolicitudEntidadFinanciera.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        def tipoDePasoSolicitud = TipoDePasoSolicitud.get(params.tipoDePasoId)
        pasoSolicitudEntidadFinanciera.tipoDePaso = tipoDePasoSolicitud
        
        if(pasoSolicitudEntidadFinanciera.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "Paso Guardado Correctamente."
            respuesta.pasoSolicitudEntidadFinanciera = pasoSolicitudEntidadFinanciera
            respuesta.pasoSolicitudEntidadFinancieraTipoDePaso = pasoSolicitudEntidadFinanciera.tipoDePaso

        }
         else {
                    if (pasoSolicitudEntidadFinanciera.hasErrors()) {
                        pasoSolicitudEntidadFinanciera.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un problema al asignar pasoSolicitudEntidadFinanciera. Intente nuevamente más tarde."
                }
        render respuesta as JSON        
    }
    def eliminar (PasoSolicitudEntidadFinanciera pasoSolicitudEntidadFinanciera){
        println params
        println pasoSolicitudEntidadFinanciera
        def respuesta = [:]
        if(CampoPasoSolicitud.findWhere(pasoSolicitud : pasoSolicitudEntidadFinanciera)){
            respuesta.error = true
            respuesta.mensaje = "No se puede eliminar existe relacion con otra Tabla"
        }else{
        pasoSolicitudEntidadFinanciera.delete flush:true
        respuesta.ok= true
        respuesta.mensaje = " El Tipo de Vivienda se eliminó Correctamente."
           }

       render respuesta as JSON
        
    }
}
