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
        def temporales = dashboardService.listaDeSolicitudesTemporales()
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: springSecurityService.currentUser.entidadFinanciera)
        session.configuracion = configuracion
        session.solicitudesPendientes = dashboardService.obtenerCantidadDeSolicitudesPendientes()
        solicitudes.addAll(temporales)
        solicitudes = solicitudes.sort { it.fechaDeSolicitud }
        println "Regresando: " + solicitudes
        [solicitudes: solicitudes, temporales: temporales]
    }
    
    def solicitudes() { 
        def solicitudesDictaminadas = dashboardService.obtenerSolicitudesPorStatus("dictaminadas", 1, null, null)
        def solicitudesNoDictaminadas = dashboardService.obtenerSolicitudesPorStatus("noDictaminadas", 1, null, null)
        def solicitudesConComplemento = dashboardService.obtenerSolicitudesPorStatus("complementoSolicitado", 1, null, null)
        println "Regresando: solicitudesDictaminadas -> " + solicitudesDictaminadas
        println "Regresando: solicitudesNoDictaminadas -> " + solicitudesNoDictaminadas
        println "Regresando: solicitudesConComplemento -> " + solicitudesConComplemento
        [solicitudesDictaminadas: solicitudesDictaminadas, solicitudesNoDictaminadas: solicitudesNoDictaminadas, solicitudesConComplemento: solicitudesConComplemento]
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
        } else if (params.template == "complementoSolicitado"){
            render(template: "solicitudesConComplementoSolicitado", model: [solicitudesConComplemento: solicitudes])
        } else {
            render solicitudes as JSON
        }
    }
    
    def detalleSolicitud(){
        println params
        def datosSolicitud
        def segmentoHistorialDeCredito
        def documentos
        def complementoSolicitado
        if(params.id){
            datosSolicitud = dashboardService.obtenerDatosDeLaSolicitud(params.id)
            segmentoHistorialDeCredito = detalleSegmentoService.historialDeCredito(params.id)
            documentos = TipoDeDocumento.findAllWhere(activo: true)
            complementoSolicitado = ComplementoSolicitud.findAllWhere(solicitud: datosSolicitud.solicitud, pendiente: true)
        }
        [datosSolicitud: datosSolicitud,segmentoHistorialDeCredito:segmentoHistorialDeCredito,documentos:documentos, complementoSolicitado: complementoSolicitado]
    }
    
    def analiticas() { }
    
    def verificaciones() {
        def solicitudesPorVerificar = dashboardService.obtenerSolicitudesPorVerificar()
        [solicitudesPorVerificar: solicitudesPorVerificar, solicitudesJSON: (solicitudesPorVerificar as JSON)]
    }
    
    def detalleVerificacion(){
        println params
        if(params.id){
            def respuesta = dashboardService.obtenerDatosSolicitud(params.id)
            def preguntas = PreguntaVerificacionSolicitud.findAllWhere(solicitud: respuesta.productoSolicitud.solicitud)
            if(respuesta) {
                [respuesta: respuesta, preguntas: preguntas]
            } else {
                [error: true, mensaje: "La solicitud indicada no existe. Verifique sus datos e intente nuevamente."]
            }
        } else {
            [error: true, mensaje: "No se especifico la solicitud a verificar. Intente nuevamente en unos momentos."]
        }
    }
    
    def configuracion() {
        def usuarios = Usuario.findAllWhere(entidadFinanciera: springSecurityService.currentUser.entidadFinanciera)
        def roles = Rol.list()
        def productos = Producto.findAllWhere(entidadFinanciera: springSecurityService.currentUser.entidadFinanciera)
        def configuracionBuroCredito = ConfiguracionEntidadFinanciera.findByEntidadFinanciera(springSecurityService.currentUser.entidadFinanciera).configuracionBuroCredito
        [listaDeUsuarios: usuarios, listaDeRoles: roles, listaDeProductos: productos, configuracionBuroCredito:configuracionBuroCredito]
    }
    
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
        def respuesta = dashboardService.subirImagen(params.imgType, params.origen, params.solicitudId, listaDeArchivos)
        render respuesta as JSON
    }
    
    def autorizarSolicitud(){
        println params
        def usuario = springSecurityService.currentUser
        if (passwordEncoder.isPasswordValid(usuario.password, params.claveUsuario, null)) {
            def solicitud = SolicitudDeCredito.get(params.solicitudId as long)
            solicitud.statusDeSolicitud = StatusDeSolicitud.get(5)
            if(solicitud.save(flush:true)){
                println("La solicitud ha sido aprobada correctamente")
                flash.message = "La solicitud ha sido aprobada correctamente"
                session.solicitudesPendientes = dashboardService.obtenerCantidadDeSolicitudesPendientes()
            } else {
                println("Ocurrio un problema al aprobar la solicitud. Intente nuevamente en unos minutos")
                flash.error = "Ocurrio un problema al aprobar la solicitud. Intente nuevamente en unos minutos"
            }
        } else {
            println("La contraseña introducida es incorrecta")
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
                } else if ((params.status as int) == 6) {
                    params["complemento[]"]?.each{
                        def complemento = new ComplementoSolicitud()
                        complemento.solicitud = solicitud
                        complemento.fechaDeSolicitud = new Date()
                        complemento.usuarioSolicitante = springSecurityService.currentUser
                        complemento.documentoSolicitado = TipoDeDocumento.get(it as long)
                        complemento.save(flush:true)
                    }
                } else if ((params.status as int) == 8) {
                    session.preguntas.each {
                        def preguntaSolicitud = new PreguntaVerificacionSolicitud()
                        preguntaSolicitud.fechaDeSubida = new Date()
                        preguntaSolicitud.pregunta = it.texto
                        preguntaSolicitud.solicitud = solicitud
                        preguntaSolicitud.usuario = springSecurityService.currentUser
                        preguntaSolicitud.save(flush:true)
                    }
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
            def extension = (archivo.rutaDelArchivo.substring(archivo.rutaDelArchivo.lastIndexOf(".") + 1)).toLowerCase()
            println "Ruta: " + archivo.rutaDelArchivo
            def file = new File(archivo.rutaDelArchivo)
            if (file.exists()){
                response.setContentType("application/octet-stream") // or or image/JPEG or text/xml or whatever type the file is
                response.setHeader("Content-disposition", "attachment;filename=\""+archivo.tipoDeDocumento+"-" + archivo.solicitud.id + "." + extension + "\"")
                response.outputStream << file.bytes
            } else {
                render "Error!"
            }
        }
    }
    
    def registrarUsuario(){
        def respuesta = dashboardService.guardarUsuario(params)
        redirect action: "configuracion"
    }
    
    def agregarPregunta(){
        println params
        def respuesta
        if(params.pregunta){
            respuesta = [:]
            if(!session.preguntas){
                session.preguntas = []
            }
            respuesta.texto = params.pregunta
            session.preguntas << respuesta
        } else {
            respuesta = [:]
        }
        def x = 0
        session.preguntas.each{ 
            x++
            it.id = x
        }
        render session.preguntas as JSON
    }
    
    def eliminarPregunta(){
        println params
        def respuesta
        if(params.idPregunta){
            if(session.preguntas && session.preguntas != null && session.preguntas?.size() > 0){
                session.preguntas.removeAll { it.id == (params.idPregunta as int) }
            }
        }
        def x = 0
        session.preguntas.each{ 
            x++
            it.id = x
        }
        render session.preguntas as JSON
    }
    
    def registrarVisitaOcular(){
        println params
        def exito = dashboardService.registrarVerificacion(params)
        if(exito) {
            redirect action: "detalleSolicitud", params: [id: params.solicitudId]
        } else {
            redirect action: "detalleVerificacion", params: [id: params.solicitudId]
        }
    }
    
    def mostrarFotografia() {
        println params
        if(params.id){
            def fotografia = FotografiaSolicitud.get(params.id as long)
            String ruta = fotografia.rutaDelArchivo
            String imagen = fotografia.nombreDelArchivo
            def extension = (fotografia.nombreDelArchivo.substring(fotografia.nombreDelArchivo.lastIndexOf(".") + 1)).toLowerCase()
            println extension
            File imageFile = new File(ruta + "/" + imagen);
            byte[] imageInByte = imageFile.bytes
            response.setHeader('Content-length', imageInByte.length.toString())
            response.contentType = 'image/' + extension
            response.outputStream << imageInByte 
            response.outputStream.flush()
        } else {
            def respuesta = [:]
            respuesta.mensaje = "No cuenta con fotografia."
            render respuesta as JSON
        }
    }
    
    def previsualizarDocumento() {
        println params
        if(params.id){
            try {
                def documento = DocumentoSolicitud.get(params.id as long)
                String ruta = documento.rutaDelArchivo
                def extension = (documento.rutaDelArchivo.substring(documento.rutaDelArchivo.lastIndexOf(".") + 1)).toLowerCase()
                println extension
                if(extension == "pdf") {
                
                } else {
                    File imageFile = new File(ruta);
                    if (imageFile.exists()){
                        byte[] imageInByte = imageFile.bytes
                        response.setHeader('Content-length', imageInByte.length.toString())
                        response.contentType = 'image/' + extension
                        response.outputStream << imageInByte 
                        response.outputStream.flush()
                    } else {
                        println "El archivo " + ruta + " NO  existe..."
                    }
                }
            } catch(Exception e) {
                println "Ocurrio un error al consultar la imagen del documento"
                e.printStackTrace()
            }
        } else {
            def respuesta = [:]
            respuesta.mensaje = "No cuenta con fotografia."
            render respuesta as JSON
        }
    }
    
}
