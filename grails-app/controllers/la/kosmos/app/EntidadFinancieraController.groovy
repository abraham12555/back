package la.kosmos.app

import grails.converters.JSON

class EntidadFinancieraController {

    def save() {
        println params
        def respuesta = [:]
        if(params.nombreEntidad) {
            def entidadFinanciera = new EntidadFinanciera()
            entidadFinanciera.activa = true
            entidadFinanciera.fechaDeRegistro = new Date()
            entidadFinanciera.nombre = params.nombreEntidad
            if(entidadFinanciera.save(flush:true)){
                respuesta.exito = true
                respuesta.mensaje = "La entidad financiera fue registrada correctamente."
                respuesta.entidad = entidadFinanciera
            } else {
                if (entidadFinanciera.hasErrors()) {
                    entidadFinanciera.errors.allErrors.each {
                        println it
                    }
                }
            }
        } else {
            respuesta.error = true
            respuesta.mensaje = "No se recibio el nombre de la Entidad Financiera a registrar."
        }
        render respuesta as JSON
    }
    
    def edit() {
        
    }
    
    def update() {
        
    }
}
