package la.kosmos.app
import grails.converters.JSON
import groovy.json.*
class SeguroSobreDeudaController {

    def index() { }
    
    
    def obtenerDetalleSeguroSobreDeuda(){
        println params
        def seguroSobreDeuda = SeguroSobreDeuda.get(params.idSeguroSobreDeuda as long)
        println seguroSobreDeuda

        render(template: "/dashboard/configuracion/seguroSobreDeuda/detalleSeguroSobreDeuda", model: [seguroSobreDeuda:seguroSobreDeuda])

    }
    
    def update(){
        println params
        def respuesta = [:]
        def seguroSobreDeuda = SeguroSobreDeuda.get(params.idSeguroSobreDeuda as long)
        seguroSobreDeuda.montoInicial = params.montoInicial as float
        seguroSobreDeuda.montoFinal = params.montoFinal as float
        seguroSobreDeuda.plazoAnual   = params.plazoAnual as float
        seguroSobreDeuda.importeSeguro = params.importeSeguro as float
        

        if(seguroSobreDeuda.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "Se Actualizo el Seguro Sobre Deuda Correctamente."
        }
        else {
            if (seguroSobreDeuda.hasErrors()) {
                seguroSobreDeuda.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el Producto. Intente nuevamente más tarde."
        }
        println respuesta
        render respuesta as JSON
    }
    
}
