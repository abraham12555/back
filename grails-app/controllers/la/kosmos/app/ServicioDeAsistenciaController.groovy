package la.kosmos.app
import grails.converters.JSON
import groovy.json.*
class ServicioDeAsistenciaController {

    def index() { }
    
    
    def obtenerDetalleServicioDeAsistencia(){
        println params
        def servicioDeAsistencia = ServicioDeAsistencia.get(params.idServicioDeAsistencia as long)
        println servicioDeAsistencia
        render(template: "/dashboard/configuracion/servicioDeAsistencia/detalleServicioDeAsistencia", model: [servicioDeAsistencia:servicioDeAsistencia])

    }
    
    def update(){
        println params
        def respuesta = [:]
        def servicioDeAsistencia = ServicioDeAsistencia.get(params.idServicioDeAsistencia as long)
        servicioDeAsistencia.montoInicial = params.montoInicial as float
        servicioDeAsistencia.montoFinal = params.montoFinal as float
        servicioDeAsistencia.plasoSemanal   = params.plazoSemanal as float
        servicioDeAsistencia.plazoQuincenal = params.plazoQuincenal as float
        servicioDeAsistencia.plazoAnual = params.plazoAnual as float
        servicioDeAsistencia.importeAsistencia = params.importeAsistencia as float

        if(servicioDeAsistencia.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "Se Actualizo el Servicio De Asistencia Correctamente."
        }
        else {
            if (servicioDeAsistencia.hasErrors()) {
                servicioDeAsistencia.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el Servicio De Asistencia. Intente nuevamente más tarde."
        }
        println respuesta
        render respuesta as JSON
    }
    

}
