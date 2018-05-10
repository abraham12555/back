package la.kosmos.app

import grails.converters.JSON
class ConfiguracionBuroCreditoController {

    def save() {
        
    }
    
    def edit() {
        
    }
    
    def update() {
        println params
        def respuesta = [:]
        if(params.valorTipoConsulta == '0'){
            ConfiguracionBuroCredito configuracion = ConfiguracionBuroCredito.findById(Integer.parseInt(params.id_configuracion_buro_Autenticador))
            configuracion.encabezadoClaveUsuario = params.encabezadoClaveUsuarioAutenticador
            configuracion.encabezadoPassword = params.encabezadoPasswordAutenticador
//            configuracion.encabezadoProductoRequerido = params.encabezadoProductoRequeridoAutenticador
//            configuracion.urlBuroCredito = params.urlBuroCreditoAutenticador
            configuracion.reintentos = Integer.parseInt(params.reintentosAutenticador)
            configuracion.fechaActualizacion = new Date()
            if(configuracion.save(flush:true)){
                respuesta.exito = true
                respuesta.mensaje = "La configuracion de buro de credito fue registrada correctamente."
                respuesta.entidad = configuracion
            } else {
                if (configuracion.hasErrors()) {
                    configuracion.errors.allErrors.each {
                        println it
                    }
                }
            }
        }else if(params.valorTipoConsulta == '1'){
            ConfiguracionBuroCredito configuracion = ConfiguracionBuroCredito.findById(Integer.parseInt(params.id_configuracion_buro_Tradicional))
            configuracion.encabezadoClaveUsuario = params.encabezadoClaveUsuarioTradicional
            configuracion.encabezadoPassword = params.encabezadoPasswordTradicional
//            configuracion.encabezadoProductoRequerido = params.encabezadoProductoRequeridoTradicional
//            configuracion.ipBuroCredito = params.ipBuroCreditoTradicional
//            configuracion.portBuroCredito = params.puertoTradicional
            configuracion.reintentos = Integer.parseInt(params.reintentosTradicional)
            configuracion.fechaActualizacion = new Date()
            if(configuracion.save(flush:true)){
                respuesta.exito = true
                respuesta.mensaje = "La configuracion de buro de credito fue registrada correctamente."
                respuesta.entidad = configuracion
            } else {
                if (configuracion.hasErrors()) {
                    configuracion.errors.allErrors.each {
                        println it
                    }
                }
            }
        }
        println respuesta
        render respuesta as JSON
    }
}
