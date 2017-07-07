package la.kosmos.app
import grails.converters.JSON
import groovy.json.*

class TipoDeAsentamientoController {

    def obtenerDetalleTipoDeAsentamiento(TipoDeAsentamiento tipoDeAsentamiento) { 
    
    render(template: "/dashboard/configuracion/tipoDeAsentamiento/detalleTipoDeAsentamiento", model: [tipoDeAsentamiento: tipoDeAsentamiento])

    }
    def eliminar(TipoDeAsentamiento tipoDeAsentamiento){
        println params
        println tipoDeAsentamiento
        def respuesta = [:]
        if(CodigoPostal.findWhere(tipoDeAsentamiento : tipoDeAsentamiento)){
            println "exite ne otro lado"
            respuesta.error = true
            respuesta.mensaje = "No se puede eliminar existe relacion con otra Tabla"
        }else{
        tipoDeAsentamiento.delete flush:true
        respuesta.ok= true
        respuesta.mensaje = " El Tipo de Asentamiento se eliminó Correctamente."
           }

       render respuesta as JSON
       
        
    }
}
