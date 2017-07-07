package la.kosmos.app
import grails.converters.JSON
import groovy.json.*
class TipoDeViviendaController {

    def obtenerDetalleTipoDeVivienda(TipoDeVivienda tipoDeVivienda) { 
    
    render(template: "/dashboard/configuracion/tipoDeVivienda/detalleTipoDeVivienda", model: [tipoDeVivienda: tipoDeVivienda])

    }
    def obtener(){
        lenguajes= TipoDeVivienda.findAll();
        render languages*.nombre as JSON
 
        
    }
    
      def eliminar(TipoDeVivienda tipoDeVivienda){
        println params
        println tipoDeVivienda
        def respuesta = [:]
        if(DireccionCliente.findWhere(tipoDeVivienda : tipoDeVivienda)){
            respuesta.error = true
            respuesta.mensaje = "No se puede eliminar existe relacion con otra Tabla"
        }else{
        tipoDeVivienda.delete flush:true
        respuesta.ok= true
        respuesta.mensaje = " El Tipo de Vivienda se eliminó Correctamente."
           }

       render respuesta as JSON
       
        
    }
}
