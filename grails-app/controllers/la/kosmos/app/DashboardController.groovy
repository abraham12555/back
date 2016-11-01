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
        session.solicitudesPendientes = dashboardService.obtenerCantidadDeSolicitudesPendientes()
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
    
    def administracion(){
        [entidadesList: EntidadFinanciera.list()]
    }
    
    def editarPerfil(){
        
    }
    
    def subirImagen(){
        println params
        def fileNames = request.getFileNames()
        def listaDeArchivos = []
        def mapa
        fileNames.each { fileName ->
            def uploadedFile = request.getFile(fileName)
            def fileLabel = ".${uploadedFile.originalFilename.split("\\.")[-1]}"
            println uploadedFile
            println "File name :"+ uploadedFile.originalFilename
            println "File size :"+ uploadedFile.size
            println "File label :"+ fileLabel
            InputStream inputStream = uploadedFile.inputStream
            mapa = [:]
            mapa.archivo = inputStream
            mapa.nombreDelArchivo = uploadedFile.originalFilename
            mapa.extension = fileLabel.toLowerCase()
            listaDeArchivos << mapa
        }
        def respuesta = dashboardService.subirImagen(params.imgType, listaDeArchivos)
        render respuesta as JSON
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
    
    def descargarArchivo(){
        println params
        if(params.id){
            def archivo = DocumentoSolicitud.get(params.id as long)
            println "Ruta: " + archivo.rutaDelArchivo
            def file = new File(archivo.rutaDelArchivo)
            if (file.exists()){
                response.setContentType("application/octet-stream") // or or image/JPEG or text/xml or whatever type the file is
                response.setHeader("Content-disposition", "attachment;filename=\""+archivo.tipoDeDocumento+"-" + archivo.solicitud.id + ".pdf\"")
                response.outputStream << file.bytes
            } else {
                render "Error!"
            }
        }
    }
}
