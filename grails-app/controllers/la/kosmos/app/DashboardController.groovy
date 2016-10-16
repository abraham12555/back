package la.kosmos.app

import grails.converters.JSON
import groovy.json.*
import java.text.SimpleDateFormat
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder

class DashboardController {

    def detalleSegmentoService
    def dashboardService
    def passwordEncoder
    def springSecurityService
    
    def index() {
        def solicitudes = dashboardService.listaGeneralDeSolicitudes()
        println "Regresando: " + solicitudes
        [solicitudes: solicitudes]
    }
    
    def solicitudes() { 
        def solicitudesDictaminadas = dashboardService.obtenerSolicitudesPorStatus("dictaminadas", 1, null, null)
        def solicitudesNoDictaminadas = dashboardService.obtenerSolicitudesPorStatus("noDictaminadas", 1, null, null)
        println "Regresando: solicitudesDictaminadas -> " + solicitudesDictaminadas
        println "Regresando: solicitudesNoDictaminadas -> " + solicitudesNoDictaminadas
        [solicitudesDictaminadas: solicitudesDictaminadas, solicitudesNoDictaminadas: solicitudesNoDictaminadas]
    }
    
    def consultarSolicitudes(){
        println params
        def solicitudes = []
        if(params.temporalidad && params.template) {
            solicitudes = dashboardService.obtenerSolicitudesPorStatus(params.template, (params.temporalidad ? (params.temporalidad as int) : null), params.fechaInicio, params.fechaFinal)
        }
        if(params.template == "dictaminadas"){
            render(template: "solicitudesDictaminadas", model: [solicitudesDictaminadas: solicitudes])
        } else if (params.template == "noDictaminadas"){
            render(template: "solicitudesNoDictaminadas", model: [solicitudesNoDictaminadas: solicitudes])
        } else {
            render solicitudes as JSON
        }
    }
    
    def detalleSolicitud(){
        println params
        def datosSolicitud
        def segmentoHistorialDeCredito
        if(params.id){
            datosSolicitud = dashboardService.obtenerDatosDeLaSolicitud(params.id)
            segmentoHistorialDeCredito = detalleSegmentoService.historialDeCredito(params.id)
        }
        [datosSolicitud: datosSolicitud,segmentoHistorialDeCredito:segmentoHistorialDeCredito]
    }
    
    def analiticas() { }
    
    def verificaciones() { }
    
    def detalleVerificacion(){ }
    
    def configuracion() { }
    
    def editarPerfil(){
        
    }
    
    def autorizarSolicitud(){
        println params
        def usuario = springSecurityService.currentUser
        if (passwordEncoder.isPasswordValid(usuario.password, params.claveUsuario, null)) {
            def solicitud = SolicitudDeCredito.get(params.solicitudId as long)
            solicitud.statusDeSolicitud = StatusDeSolicitud.get(5)
            if(solicitud.save(flush:true)){
                flash.message = "La solcitud ha sido aprobada correctamente"
            } else {
                flash.error = "Ocurrio un problema al aprobar la solicitud. Intente nuevamente en unos minutos"
            }
        } else {
            flash.error = "La contraseña introducida es incorrecta"
        }
        redirect action: "detalleSolicitud", params: [id: params.solicitudId]
    }
    
    def cambiarEstadoSolicitud(){
        println params
        def respuesta = [:]
        if(params.id){
            def solicitud = SolicitudDeCredito.get(params.id as long)
            solicitud.statusDeSolicitud = StatusDeSolicitud.get(params.status as long)
            if(solicitud.save(flush:true)){
                respuesta.ok = true
                respuesta.mensaje = "La solicitud se actualizó correctamente"
                if((params.status as int) == 7){
                    respuesta.bloquearOpciones = true
                }
            } else {
                respuesta.error = true
                respuesta.mensaje = "Ocurrio un error al actualizar la solicitud"
            }
        } else {
            respuesta.error = true
            respuesta.mensaje = "No se recibio el número de solicitud a actualizar"
        }
        render respuesta as JSON
    }
}
