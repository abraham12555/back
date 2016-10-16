package la.kosmos.app

import grails.converters.JSON
import groovy.json.*
import java.text.SimpleDateFormat

class DashboardController {

	def detalleSegmentoService
	
	def index() { }

	def solicitudes() { }
	
	def detalleSolicitud(){
		def segmentoHistorialDeCredito = detalleSegmentoService.historialDeCredito(params.id)
		[segmentoHistorialDeCredito:segmentoHistorialDeCredito] 
		
	}


	def analiticas() { }

	def verificaciones() { }

	def detalleVerificacion(){ }

	def configuracion() { }

	def editarPerfil(){

	}

	def autorizarSolicitud(){

	}

	def cambiarEstadoSolicitud(){

	}
}
