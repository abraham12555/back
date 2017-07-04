package la.kosmos.app

import grails.converters.JSON
import groovy.json.*
import static java.util.Calendar.*

import java.text.SimpleDateFormat
import la.kosmos.app.bo.Pager
import la.kosmos.app.bo.User
import org.springframework.web.multipart.MultipartHttpServletRequest
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder

class DashboardController {

    def detalleSegmentoService
    def dashboardService
    def passwordEncoder
    def springSecurityService
    def cotizadorService
    def solicitudService
    def perfiladorService
    def smsService
    def notificacionesService
    def userService
    def reporteService

    def index() {
        def solicitudes = dashboardService.listaGeneralDeSolicitudes()
        def temporales = dashboardService.listaDeSolicitudesTemporales()
        def entidadFinanciera = session.usuario.entidadFinanciera

        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
        //linea agregada para obtener el usuario
        session.usuarioNombre= springSecurityService.currentUser.nombre
        session.configuracion = configuracion
        session.solicitudesPendientes = dashboardService.obtenerCantidadDeSolicitudesPendientes()
        solicitudes.addAll(temporales)
        solicitudes = solicitudes.sort { it.fechaDeSolicitud }
        println "Regresando: " + solicitudes?.size() + " Solicitudes"
        [solicitudes: solicitudes, temporales: temporales]
    }

    def solicitudes() {
        def solicitudesDictaminadas = dashboardService.obtenerSolicitudesPorStatus("dictaminadas", 1, null, null)
        def solicitudesNoDictaminadas = dashboardService.obtenerSolicitudesPorStatus("noDictaminadas", 1, null, null)
        def solicitudesConComplemento = dashboardService.obtenerSolicitudesPorStatus("complementoSolicitado", 1, null, null)
        println "Regresando: solicitudesDictaminadas -> " + solicitudesDictaminadas?.size()
        println "Regresando: solicitudesNoDictaminadas -> " + solicitudesNoDictaminadas?.size()
        println "Regresando: solicitudesConComplemento -> " + solicitudesConComplemento?.size()
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
        if(params.id && !params.temporal){
            datosSolicitud = dashboardService.obtenerDatosDeLaSolicitud(params.id)
            segmentoHistorialDeCredito = detalleSegmentoService.historialDeCredito(params.id)
            documentos = TipoDeDocumento.findAllWhere(activo: true)
            complementoSolicitado = ComplementoSolicitud.findAllWhere(solicitud: datosSolicitud.solicitud, pendiente: true)
        } else if(params.id && params.temporal?.toBoolean()){
            println("Si es temporal");
            datosSolicitud = dashboardService.obtenerDatosDeLaSolicitudTemporal(params.id)
            println datosSolicitud
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
        def entidadFinanciera = session.usuario.entidadFinanciera
        def roles = dashboardService.getRoles()
        def sucursales = dashboardService.getSucursalesByEntidadFinanciera(entidadFinanciera)
        def productos = Producto.findAllWhere(entidadFinanciera: entidadFinanciera)
        def tipoDeIngresos = TipoDeIngresos.getAll()
        def tipoDeDocumento = TipoDeDocumento.findAll();
        def listaTipoDeAsentamiento = TipoDeAsentamiento.findAll();
        def listaTipoDeVivienda = TipoDeVivienda.findAll();
        def listaTipoDeTasaDeInteres = TipoDeTasaDeInteres.findAll();

        def configuracionBuroCredito = ConfiguracionEntidadFinanciera.findByEntidadFinanciera(entidadFinanciera).configuracionBuroCredito

        println tipoDeIngresos
        [   listaDeTiposDeIngresos:tipoDeIngresos,
            listaDeRoles: roles,
            listaSucursales: sucursales,
            listaDeProductos: productos,
            configuracionBuroCredito:configuracionBuroCredito,
            tipoDeDocumento:tipoDeDocumento,tipoDeIngresos:tipoDeIngresos,
            listaTipoDeAsentamiento:listaTipoDeAsentamiento,
            listaTipoDeVivienda:listaTipoDeVivienda,listaTipoDeTasaDeInteres:listaTipoDeTasaDeInteres
        ]
    }

    def administracion(){
        [entidadesList: EntidadFinanciera.list()]
    }

    def editarPerfil(){
        def allowedSize = userService.getProfilePictureSize()
        def allowedContentTypes = userService.getProfilePictureContentTypes()
        [allowedContentTypes : allowedContentTypes, allowedSize: allowedSize]
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

    def guardarUsuario(){
        def entidadFinanciera = session.usuario.entidadFinanciera
        def respuesta = dashboardService.guardarUsuario(params, entidadFinanciera)
        render respuesta as JSON
    }

    def registrarProducto(){
        def respuesta = dashboardService.guardarProducto(params)
        redirect action: "configuracion"
    }
    def registrarTipoDeDocumento(){
        def respuesta = dashboardService.guardarTipoDeDocumento(params)
        redirect action: "configuracion"
    }
    def registrarTipoDeAsentamiento(){
        def respuesta = dashboardService.guardarTipoDeAsentamiento(params)
        redirect action: "configuracion"
    }
    def registrarTipoDeVivienda(){
        def respuesta = dashboardService.guardarTipoDeVivienda(params)
        redirect action: "configuracion"
    }
    def registrarTipoDeTasaDeInteres(){
        def respuesta = dashboardService.guardarTipoDeTasaDeInteres(params)
        redirect action: "configuracion"
    }
    def guardarTipoDeAsentamiento(){
        def respuesta = dashboardService.updateTipoDeAsentamiento(params)
        redirect action: "configuracion"
    }
    def guardarTipoDeVivienda(){
        def respuesta = dashboardService.updateTipoDeVivienda(params)
        redirect action: "configuracion"
    }
    def guardarTipoDeTasaDeInteres(){
        def respuesta = dashboardService.updateTipoDeTasaDeInteres(params)
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

    def getEstadisticas() {
        println params
        def estadisticas = []
        if(params.temporalidad && params.grafica) {
            estadisticas = dashboardService.obtenerEstadisticasPorGrafica(params.grafica, (params.temporalidad as int) , params.fechaInicio, params.fechaFinal)
        }
        render estadisticas as JSON
    }

    def perfilarCliente(){
        session.ofertas = null
        session.pasoFormulario = null
        session.identificadores = null
        session.perfil = null
        def hoy = new Date()
        [razonSocial:session.configuracion?.razonSocial, fechaActual: (hoy[DATE] + " de " + hoy.format("MMMM") + " de " + hoy[YEAR])]
    }

    def solicitarCodigo() {
        println params
        def respuesta = [:]
        if(params.telefonoCelular){
            def toPhone = params.telefonoCelular.replaceAll('-', '').replaceAll(' ', '').trim()
            String sid = smsService.sendSMS(toPhone, session.configuracion)
            if(sid){
                session.sid = sid
                respuesta.mensajeEnviado = true
            } else {
                respuesta.mensajeEnviado = false
            }
        } else {
            respuesta.mensajeEnviado = false
        }
        render respuesta as JSON
    }

    def resultadoVerificacion() {
        def respuesta = [:]
        if(params.codigoConfirmacion){
            String sid = session.sid
            String randomCodeTmp = params.codigoConfirmacion
            println "session.sid -> " + sid
            println "randomCodeTmp -> " + randomCodeTmp
            boolean result = smsService.verify(sid, randomCodeTmp)
            respuesta.resultado = result
        } else {
            respuesta.incorrecto = true
        }
        render respuesta as JSON
    }

    def estructurarSolicitud(){
        println params
        def respuesta = [:]
        def ultimoPaso = [:]
        def entidadFinanciera = session.usuario.entidadFinanciera
        ultimoPaso.tipoDePaso = [:]
        ultimoPaso.tipoDePaso.nombre = "pasoFormulario"
        session["pasoFormulario"] = solicitudService.construirDatosTemporales(session["pasoFormulario"], params, ultimoPaso, session.identificadores, entidadFinanciera, null, null, springSecurityService.currentUser)
        if(session["pasoFormulario"]?.cliente?.clienteGuardado){
            if(!session.identificadores){
                session.identificadores = [:]
            }
            if(!session.identificadores.idCliente) {
                session.identificadores.idCliente = session["pasoFormulario"]?.cliente?.idCliente
                session.identificadores.idSolicitud = session["pasoFormulario"]?.cliente?.idSolicitud
                session.identificadores.idDireccion = session["pasoFormulario"]?.direccionCliente?.idDireccion
                session.identificadores.idEmpleo = session["pasoFormulario"]?.empleoCliente?.idEmpleo
            }
        }
        println session.identificadores
        render respuesta as JSON
    }

    def obtenerOfertas(){
        println params
        def respuesta
        session.ofertas = null
        respuesta = perfiladorService.obtenerPropuestas("perfilador", session.identificadores, session["pasoFormulario"]?.cliente?.tipoDeDocumento, session["pasoFormulario"]?.cliente?.clienteExistente, session.perfil)
        println "Propuestas Obtenidas: " + respuesta
        session.ofertas = respuesta
        render respuesta as JSON
        //render(template: "perfilador/ofertas", model: [ofertas: respuesta])
    }

    def recalcularOferta() {
        println params
        def respuesta = [:]
        respuesta = perfiladorService.recalcularOferta(session.ofertas, params)
        render respuesta as JSON
    }

    def seleccionarOferta() {
        println params
        def respuesta
        respuesta = perfiladorService.guardarOferta(session.ofertas, session.pasoFormulario, session.identificadores , params)
        session.ofertas = null
        session.pasoFormulario = null
        session.identificadores = null
        session.perfil = null
        render(template: "perfilador/confirmacion", model: [ofertaSeleccionada: respuesta])
    }

    def buscarPerfilExistente(){
        println params
        def respuesta
        respuesta = perfiladorService.buscarPerfilExistente(params.rfcClienteExistente)
        if(respuesta.encontrado){
            session.perfil = respuesta.perfil
        }
        render respuesta as JSON
    }

    def getUsers() {
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def users = userService.getUsers(entidadFinanciera, pager)

        def response = [:]
        response.usuarios = users
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }

    def getUserDetails(){
        def response = userService.getUserDetails(params.idUser)
        render response as JSON
    }

    def validUsername(){
        User usuario = new User(request.JSON)

        def response = [:]
        response.estatus = userService.validUsername(usuario)
        render response as JSON
    }

    def validEmail(){
        User usuario = new User(request.JSON)

        def response = [:]
        response.estatus = userService.validEmail(usuario)
        render response as JSON
    }

    def autenticarUsuario(){
        def response = [:]

        Usuario usuario = springSecurityService.currentUser
        if (usuario == null){
            response.estatus = Boolean.FALSE
            response.message = "Error. Los datos del usuario son inválidos"
        } else {
            boolean validation = userService.userAuthentication(usuario, params)
            response.estatus = validation
            if(!validation){
                response.message = "La contraseña es incorrecta"
            }
        }

        render response as JSON
    }

    def validNoEmpleado(){
        User usuario = new User(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera

        def response = [:]
        response.estatus = userService.validNoEmpleado(usuario, entidadFinanciera)
        render response as JSON
    }

    def getProfile(){
        Usuario usuario = springSecurityService.currentUser
        def response = userService.getUserDetails(usuario.id)
        render response as JSON
    }

    def saveProfile(){
        Usuario usuario = springSecurityService.currentUser

        def response = userService.saveProfile(usuario, params)
        render response as JSON
    }

    def updatePassword(){
        Usuario usuario = springSecurityService.currentUser

        def response = userService.updateProfilePassword(usuario, params)
        render response as JSON
    }

    def reportes () {
        def reporteSolicitudes = reporteService.obtenerReporte(null,null,null,null)
        def reporteMitek = reporteService.obtenerReporte(null,null,null,null)
        def contactoClientes = reporteService.obtenerReporte(null,null,null,null) 
        [reporteSolicitudes: reporteSolicitudes, reporteMitek: reporteMitek, contactoClientes: contactoClientes]
    }

    def descargarContactos(){
        def reporte  = reporteService.obtenerReporte("clientes",session.usuario.entidadFinanciera,null,null)
        if(reporte) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=\"" + "Reporte_Contacto_Clientes" + ".xlsx\"")
            response.outputStream << reporte.bytes
        } else {
            flash.error = "No se encontraron registros correspondientes al criterio de búsqueda."
            redirect action: "reportes"
        }
    }

    def  descargarReporteMitek(){
        def reporte  = reporteService.obtenerReporte("mitek",session.usuario.entidadFinanciera,null,null)
        if(reporte) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=\"" + "Reporte_Consultas_Mitek" + ".xlsx\"")
            response.outputStream << reporte.bytes
        } else {
            flash.error = "No se encontraron registros correspondientes al criterio de búsqueda."
            redirect action: "reportes"
        }
    }

    def  descargarReporteSolicitudes(){
        def reporte  = reporteService.obtenerReporte("solicitudes",session.usuario.entidadFinanciera,params.from,params.to)
        if(reporte) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=\"" + "Reporte_Solicitudes_Generadas" + ".xlsx\"")
            response.outputStream << reporte.bytes
            response.outputStream << reporte.bytes
        } else {
            flash.error = "No se encontraron registros correspondientes al criterio de búsqueda."
            redirect action: "reportes"
        }
    }

    def downloadUserList(){
        def entidadFinanciera = session.usuario.entidadFinanciera
        def file = userService.exportUserList(entidadFinanciera)
        
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
        response.setHeader("Content-disposition", "attachment; filename=lista_usuarios.xlsx")
        response.outputStream << file
    }
    
    def saveProfilePicture(){
        def response = [:]
        
        if (!request instanceof MultipartHttpServletRequest){
            response.error = Boolean.TRUE
            response.message = "Error, la operación es inválida"
            response as JSON
        }
        
        Usuario usuario = springSecurityService.currentUser
        
        response = userService.saveProfilePicture(usuario, params)
        render response as JSON
    }
    
    def profilePicture() {
        Usuario usuario = springSecurityService.currentUser
        def response = userService.getProfilePicture(usuario)
        render response as JSON
    }
    
    def deleteProfilePicture(){
        Usuario usuario = springSecurityService.currentUser
        def response = userService.deleteProfilePicture(usuario)
        render response as JSON
    }    
}
