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
		if(params.id_configuracion_buro && params.encabezadoClaveUsuario && params.encabezadoPassword && params.encabezadoProductoRequerido && params.urlBuroCredito && params.reintentos) {
                        ConfiguracionBuroCredito configuracion = ConfiguracionBuroCredito.findById(params.id_configuracion_buro)
			configuracion.encabezadoClaveUsuario = params.encabezadoClaveUsuario
			configuracion.encabezadoPassword = params.encabezadoPassword
			configuracion.encabezadoProductoRequerido = params.encabezadoProductoRequerido
			configuracion.urlBuroCredito = params.urlBuroCredito
			configuracion.reintentos = Integer.parseInt(params.reintentos)
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
		} else {
			respuesta.error = true
			respuesta.mensaje = "Favor de Verificar, todos los campos son requeridos."
		}
		render respuesta as JSON
    }
}
