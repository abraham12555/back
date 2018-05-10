package la.kosmos.app

import org.apache.commons.codec.binary.Base64
import java.nio.file.Files
import grails.converters.JSON
import groovy.json.*
import static java.util.Calendar.*

import java.text.SimpleDateFormat
import la.kosmos.app.bo.Pager
import la.kosmos.app.bo.User
import la.kosmos.app.exception.BusinessException
import la.kosmos.app.vo.Constants
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
    def bitacoraOfertasService
    
    def index() {
        //def solicitudes = dashboardService.listaGeneralDeSolicitudes()
        //def temporales = dashboardService.listaDeSolicitudesTemporales()
        def entidadFinanciera = session.usuario.entidadFinanciera
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
        //linea agregada para obtener el usuario
        session.usuarioNombre= springSecurityService.currentUser.nombre
        session.configuracion = configuracion
        session.solicitudesPendientes = dashboardService.obtenerCantidadDeSolicitudesPendientes()
        //solicitudes.addAll(temporales)
        //solicitudes = solicitudes.sort { it.fechaDeSolicitud }
        println "Regresando ..."
        session.configuracion
    }

    def obtenerBase64Imagenes(def ruta){
        def respuesta = [:]
        if(ruta){
            byte[] array = Files.readAllBytes(new File(ruta).toPath());
            respuesta.base64 = Base64.encodeBase64String(array)
            respuesta.extension =  ruta.split("\\.")[-1]
        }
        return respuesta
    }
    def consultaConfiguracion() {
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: session.usuario.entidadFinanciera)
        session.configuracion = configuracion
        render configuracion as JSON
    }

    def solicitudes() {
        //def solicitudesDictaminadas = dashboardService.obtenerSolicitudesPorStatus("dictaminadas", 1, null, null)
        //def solicitudesNoDictaminadas = dashboardService.obtenerSolicitudesPorStatus("noDictaminadas", 1, null, null)
        //def solicitudesConComplemento = dashboardService.obtenerSolicitudesPorStatus("complementoSolicitado", 1, null, null)
        //println "Regresando: solicitudesDictaminadas -> " + solicitudesDictaminadas?.size()
        //println "Regresando: solicitudesNoDictaminadas -> " + solicitudesNoDictaminadas?.size()
        //println "Regresando: solicitudesConComplemento -> " + solicitudesConComplemento?.size()
        //[solicitudesDictaminadas: solicitudesDictaminadas, solicitudesNoDictaminadas: solicitudesNoDictaminadas, solicitudesConComplemento: solicitudesConComplemento]
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

    def consultarInformes() {
        session.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        def informes = []
        if(params.periodoTiempo && params.template) {
            informes = reporteService.obtenerInformesAnalitica(params.template, (params.periodoTiempo ? (params.periodoTiempo as int) : null), params.fechaInicio, params.fechaFinal,session.entidadFinanciera)
        }
    
        if(params.template == "productosDiscriminados"){
            render(template: "informes/productosDiscriminados", model: [productosDiscriminados: informes])
        } else if(params.template == "mPSProducto"){ 
            render(template: "informes/mPSProducto", model: [mPSProducto: informes])
        } else if(params.template == "mPGI"){ 
            render(template: "informes/mPGI", model: [mPGI: informes])
        } else if(params.template == "iMPEG"){ 
            render(template: "informes/iMPEG", model: [iMPEG: informes])
        } else if(params.template == "contacto"){
            render(template: "informes/contacto", model: [contacto: informes])  
        }else if(params.template == "dFvsDP"){
            render(template: "informes/dFvsDP", model: [dFvsDP: informes])
        }else if(params.template == "causaRechazo"){ 
            render(template: "informes/causaRechazo", model: [causaRechazo: informes])
        }else if(params.template == "consultasBC"){
            render(template: "informes/consultasBC", model: [consultasBC: informes])
        }
    }

    def getSolicitudesBusqueda (){
        def fechaInicio
        def fechaFinal
        def pagina = request.JSON.page as long
        if(!request.JSON.fechaInicio && ! request.JSON.fechaFinal){
            fechaInicio = null
            fechaFinal = null
        }else{
            fechaInicio = request.JSON.fechaInicio
            fechaFinal = request.JSON.fechaFinal
        }
        def respuesta = [:]
        respuesta.page = pagina
        Pager pager = new Pager(respuesta)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def solicitudes = dashboardService.getSolicitudesBusqueda(entidadFinanciera, pager, request.JSON.template, request.JSON.temporalidad , fechaInicio, fechaFinal)
        def response = [:]
        response.solicitudes = solicitudes
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }
    
    def busquedaCriterio (){
        println params
        println request.JSON
        def respuesta = [:]
        if(request.JSON){
            respuesta.page = request.JSON.page
        }
        Pager pager = new Pager(respuesta)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def solicitudes = dashboardService.buscar(entidadFinanciera,pager,null,request.JSON)
        def response = [:]
        response.solicitudes = solicitudes
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
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
        println params
        session.nombre = [:]
        session.garantias = [:]
        session.tipoDeDocumentoId =[:]
        session.productos = [:]
        session.plazos = [:]
        session.pasoId = [:]
        session.tipoDeIngresos = [:]
        session.campoFormulario = []
        def usuarios = Usuario.findAllWhere(entidadFinanciera: session.usuario.entidadFinanciera)
        def roles = dashboardService.getRoles()
        def sucursales = dashboardService.getSucursalesByEntidadFinanciera(session.usuario.entidadFinanciera)
        def productos = Producto.findAllWhere(entidadFinanciera: session.usuario.entidadFinanciera)
        def tipoDeIngresos = TipoDeIngresos.getAll()
        def tipoDeDocumento = TipoDeDocumento.findAll();
        def listaTipoDeAsentamiento = TipoDeAsentamiento.findAll();
        def listaTipoDeVivienda = TipoDeVivienda.findAll();
        def listaTipoDeTasaDeInteres = TipoDeTasaDeInteres.findAll();
        def listaTipoDeCampo = TipoDeCampo.findAll();
        def listaTipoDeContrato = TipoDeContrato.findAll();
        def listaTipoDeFotografia = TipoDeFotografia.findAll();
        def listaTipoDeGarantia = TipoDeGarantia.findAll();
        def listaTipoDeIngresos = TipoDeIngresos.findAll();
        def campoFormulario = CampoFormulario.findAll();
        //TODO: CHANGE CONFIGURATION
        def configuracion = ConfiguracionEntidadFinanciera.findByEntidadFinanciera(session.usuario.entidadFinanciera)
        def configuracionBuroCreditoAutenticador = ConfiguracionBuroCredito.findAllByConfiguracionEntidadFinancieraAndTipoConsulta(configuracion,Constants.TipoConsulta.AUTENTICADOR)
        def configuracionBuroCreditoTradicional = ConfiguracionBuroCredito.findAllByConfiguracionEntidadFinancieraAndTipoConsulta(configuracion,Constants.TipoConsulta.TRADICIONAL)
        def listaPasoCotizador = PasoCotizadorEntidadFinanciera.findAllWhere(entidadFinanciera: session.usuario.entidadFinanciera)
        def listaPasosSolicitud = PasoSolicitudEntidadFinanciera.findAllWhere(entidadFinanciera: session.usuario.entidadFinanciera)
        def listaRubroDeAplicacionDeCredito = RubroDeAplicacionDeCredito.findAllWhere(entidadFinanciera: session.usuario.entidadFinanciera)
        campoFormulario = campoFormulario.sort { it.tipoDeCampo }
        listaPasoCotizador = listaPasoCotizador.sort { it.numeroDePaso }
        listaRubroDeAplicacionDeCredito = listaRubroDeAplicacionDeCredito.sort{it.posicion}
        listaPasosSolicitud = listaPasosSolicitud.sort { it.numeroDePaso }
        [listaPasosSolicitud:listaPasosSolicitud,listaPasoCotizador:listaPasoCotizador,listaDeUsuarios: usuarios,listaDeTiposDeIngresos:tipoDeIngresos, 
            listaDeRoles: roles, 
            listaSucursales: sucursales,
            listaDeProductos: productos, 
            configuracionBuroCreditoAutenticador:configuracionBuroCreditoAutenticador,
            configuracionBuroCreditoTradicional:configuracionBuroCreditoTradicional,
            campoFormulario:campoFormulario,
            tipoDeDocumento:tipoDeDocumento,tipoDeIngresos:tipoDeIngresos,
            listaTipoDeAsentamiento:listaTipoDeAsentamiento,
            listaTipoDeVivienda:listaTipoDeVivienda,listaTipoDeTasaDeInteres:listaTipoDeTasaDeInteres,
            configuracion:configuracion,
            listaTipoDeCampo:listaTipoDeCampo,listaTipoDeContrato:listaTipoDeContrato,
            listaTipoDeFotografia:listaTipoDeFotografia,listaTipoDeGarantia:listaTipoDeGarantia,
            listaTipoDeIngresos:listaTipoDeIngresos,listaRubroDeAplicacionDeCredito:listaRubroDeAplicacionDeCredito,mensaje:params.mensaje]
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

    def buscar (){
        println params
        def respuesta = [:]
        if(!request.JSON){
            respuesta.page = 1
        }
        else if (request.JSON){
            respuesta.page = request.JSON.page
        }
        Pager pager = new Pager(respuesta)
     
        def entidadFinanciera = session.usuario.entidadFinanciera
        def solicitudes = dashboardService.buscar(entidadFinanciera, pager,params,null)
        def response = [:]
        response.solicitudes = solicitudes
        response.totalPages = pager.totalPages
        response.page = pager.page
        [totalPages:pager.totalPages,page:pager.page,solicitudes: solicitudes,cuantos:pager.totalPages,nombre:params.nombre,folio:params.folio,nombre:params.nombre,apellidoPaterno:params.apellidoPaterno,apellidoMaterno:params.apellidoMaterno,rfc:params.rfc]
    }

    def getSegurosSobreDeuda (){
        //println params
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def segurosSobreDeuda = dashboardService.getSegurosSobreDeuda(entidadFinanciera, pager)
        //println segurosSobreDeuda
        def response = [:] 
        response.segurosSobreDeuda = segurosSobreDeuda
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
        
    }

    def getServicioDeAsistencia (){
        //println params
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def servicioDeAsistencia = dashboardService.getServicioDeAsistencia(entidadFinanciera, pager)
        def response = [:]
        response.servicioDeAsistencia = servicioDeAsistencia
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
        
    }

    def getPasosSolicitud() {
        println params
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def pasosSolicitud = dashboardService.getPasosSolicitud(entidadFinanciera, pager)
        def response = [:]
        response.pasosSolicitud = pasosSolicitud
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }

    def getPasosCotizador() {
        println params
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def pasosCotizador = dashboardService.getPasosCotizador(entidadFinanciera, pager)
        def response = [:]
        response.pasosCotizador = pasosCotizador
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }

    def getProductos() {
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def productos = dashboardService.getProductos(entidadFinanciera, pager)
        def response = [:]
        response.productos = productos
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }
    
    def getSolicitudes (){
        Pager pager = new Pager(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def solicitudes = dashboardService.getSolicitudes(entidadFinanciera, pager)
        def response = [:]
        response.solicitudes = solicitudes
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }

    def editarPerfilDeMarca(){
        println params
        def entidadFinanciera = EntidadFinanciera.get(params.idEntidadFinanciera)
        println entidadFinanciera
    }
    
    def registrarUsuario(){
        println params
        def respuesta = dashboardService.guardarUsuario(params)
        redirect action: "configuracion"
    }
    def updateUsuario(){
        println params
        def respuesta = dashboardService.updateUsuario(params)
        redirect action: "configuracion"
    }
    
    def registrarPasoCotizadorEntidadFinanciera (){
        println params
        def respuesta = dashboardService.guardarPasoCotizadorEntidadFinanciera (params)
        println "imprimiendo respuesta en controller"+respuesta
        render respuesta as JSON
        //redirect action: "configuracion"
    }
    
    def registrarPasoSolicitudEntidadFinanciera (){
        println params
        def respuesta = dashboardService.guardarPasoSolicitudEntidadFinanciera (params)
        println "imprimiendo respuesta en controller"+respuesta
        render respuesta as JSON
        //redirect action: "configuracion"
    }
    
    def registrarSeguroSobreDeuda(){
        def respuesta = dashboardService.guardarSeguroSobreDeuda(params)
        render respuesta as JSON
    }
    
    def registrarServicioDeAsistencia(){
        println params
        def respuesta = dashboardService.guardarServicioDeAsistencia(params)
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
    
    def registrarTipoDeCampo(){
        def respuesta = dashboardService.guardarTipoDeCampo(params)
        redirect action: "configuracion"
    }
    
    def registrarTipoDeContrato(){
        def respuesta = dashboardService.guardarTipoDeContrato(params)
        redirect action: "configuracion"
    }
    
    def registrarTipoDeFotografia(){
        def respuesta = dashboardService.guardarTipoDeFotografia(params)
        redirect action: "configuracion"
    }
    
    def registrarTipoDeGarantia(){
        def respuesta = dashboardService.guardarTipoDeGarantia(params)
        redirect action: "configuracion"
    }
    
    def registrarTipoDeIngresos(){
        def respuesta = dashboardService.guardarTipoDeIngresos(params)
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
    
    def guardarTipoDeCampo(){
        def respuesta = dashboardService.updateTipoDeCampo(params)
        redirect action: "configuracion"
    }
    
    def guardarTipoDeContrato(){
        def respuesta = dashboardService.updateTipoDeContrato(params)
        redirect action: "configuracion"
    }
    
    def guardarTipoDeFotografia(){
        def respuesta = dashboardService.updateTipoDeFotografia(params)
        redirect action: "configuracion"
    }
    
    def guardarTipoDeIngresos(){
        def respuesta = dashboardService.updateTipoDeIngresos(params)
        redirect action: "configuracion"
    }
    
    def guardarTipoDeGarantia(){
        def respuesta = dashboardService.updateTipoDeGarantia(params)
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

    def agregarCampoFormulario(){
        println params
        def respuesta
        if((params.tituloDelCampo) && (params.longitudDelCampo) && (params.numeroDeCampo) && (params.placeholder) 
            && (params.tieneAyuda)&& 
            (params.saltoDeLineaAlFinal)&& (params.mostrarAlInicio)&& (params.obligatorio)){
            
            respuesta = [:]
            if(params.tieneAyuda == false){
                params.textoAyuda = ""
            }
            if(!session.campoFormulario){
                session.campoFormulario = []
            }
            def campoFormulario = CampoFormulario.get(params.idCampoFormulario)
            respuesta.tipoDeCampo = campoFormulario.tipoDeCampo
            respuesta.idCampoFormulario = params.idCampoFormulario as int
            respuesta.tituloDelCampo = params.tituloDelCampo.toString()
            respuesta.longitudDelCampo = params.longitudDelCampo
            respuesta.numeroDeCampo = params.numeroDeCampo as int
            respuesta.placeholder = params.placeholder.toString()
            respuesta.textoAnterior = params.textoAnterior.toString()
            respuesta.textoPosterior = params.textoPosterior.toString()
            respuesta.tieneAyuda = params.tieneAyuda.toBoolean()
            respuesta.textoAyuda = params.textoAyuda.toString()
            respuesta.saltoDeLineaAlFinal = params.saltoDeLineaAlFinal.toBoolean()
            respuesta.mostrarAlInicio = params.mostrarAlInicio.toBoolean()
            respuesta.obligatorio = params.obligatorio.toBoolean()
            respuesta.parrafo = 1 
            //respuesta.urlImagen = params.urlImagen
            respuesta.ok = true
            respuesta.mensaje = "El Campo se añadido correctamente"
            session.campoFormulario << respuesta
        } else{
            //session.campoFormulario = []
            respuesta = [:]
            respuesta.ok= false
            respuesta.mensaje="Faltan campos por llenar"
        }
        def x = 0
        session.campoFormulario.each{ 
            x++
            it.id = x
        }
        session.campoFormulario.sort{it.numeroDeCampo}
        def result = [session:session.campoFormulario,respuesta:respuesta]
        render result as JSON
    }
    
    def updateCampoFormulario (){
        println params
        def respuesta
        if(params.idCampoFormulario){
            session.campoFormulario.findAll{(it.id) as int == (params.idCampoFormulario)as int }.each{
                respuesta = [:]

                it.tituloDelCampo = params.tituloDelCampo.toString()
                it.longitudDelCampo = params.longitudDelCampo
                it.numeroDeCampo = params.numeroDeCampo
                it.placeholder = params.placeholder.toString()
                it.textoAnterior = params.textoAnterior.toString()
                it.textoPosterior = params.textoPosterior.toString()
                it.tieneAyuda = params.tieneAyuda.toBoolean()
                it.textoAyuda = params.textoAyuda.toString()
                it.saltoDeLineaAlFinal = params.saltoDeLineaAlFinal.toBoolean()
                it.mostrarAlInicio = params.mostrarAlInicio.toBoolean()
                it.obligatorio = params.obligatorio.toBoolean()
                it.tieneAyuda = params.tieneAyuda.toBoolean()
                respuesta.ok = true
                respuesta.mensaje = "Se actualizo Correctamente."

            }
        }
        session.campoFormulario.sort{it.numeroDeCampo}
        def result = [session:session.campoFormulario,respuesta:respuesta]
        render result as JSON
        
    }
    
    
    def agregarRubro(){
        println params
        def respuesta
        if (params.urlImagen == "undefined"){
            respuesta = [:]
            respuesta.ok= false
            respuesta.mensaje="Selecciona una Imagen"
        }
        else if((params.nombre) && (params.posicion) && (params.descripcion) && (params.claseIconoPaso) && (params.tooltip)  && (params.urlImagen) && (params.activo)){
           
            println params
            respuesta = [:]
            if(!session.nombre){
                session.nombre = []
            }
            respuesta.texto = params.nombre
            respuesta.posicion = params.posicion as long
            respuesta.descripcion = params.descripcion
            respuesta.claseIconoPaso = params.claseIconoPaso
            respuesta.tooltip = params.tooltip.toBoolean()
            respuesta.textoTooltip = params.textoTooltip.toString()
            //respuesta.urlImagen = params.urlImagen
            def uploadedFile = params.urlImagen
            println"imprimiendo el file "+ uploadedFile 
            def fileLabel = ".${uploadedFile.originalFilename.split("\\.")[-1]}"
            println uploadedFile
            println "File name :"+ uploadedFile.originalFilename
            println "File size :"+ uploadedFile.size
            println "File label :"+ fileLabel
            
            InputStream inputStream = uploadedFile.inputStream
            respuesta.archivo = inputStream
            respuesta.nombreDelArchivo = uploadedFile.originalFilename
            respuesta.extension = fileLabel.toLowerCase()
            respuesta.ok = true
            respuesta.mensaje = "El rubro se añadido correctamente"
            respuesta.activo = params.activo.toBoolean()
            session.nombre << respuesta
        } else{
            respuesta = [:]
            respuesta.ok= false
            respuesta.mensaje="Faltan campos por llenar"
        }
        def x = 0
        session.nombre.each{ 
            def documentosLlenos = false
            def productosLlenos = false
            def vistasLlenas = false
            x++
            it.id = x
            
            session.tipoDeDocumentoId.findAll{(it.idRubroTemporal)as int  == x }.each{
                println "si hay lleno"
                documentosLlenos= true
            }
         
            session.productos.findAll{(it.idRubroTemporal) as int == x}.each{
                println "si hay lleno"
                productosLlenos= true
            }
            
            session.pasoId.findAll{(it.idRubroTemporal) as int == x }.each{
                println "si hay lleno"
                vistasLlenas= true
            }

            it.documentosLlenos = documentosLlenos
            it.productosLlenos = productosLlenos
            it.vistasLlenas = vistasLlenas
        }
        def result = [session:session.nombre,respuesta:respuesta]
        println "imprimiendo rubros"+ session.nombre
        render result as JSON
 
    }
   
    def agregarProducto(){
        println params
        def respuesta
        def respuesta2
        def respuesta3
        if(params.nombreDelProducto && params.idRubroTemp){ // ponerque los params vengan llenos
            respuesta = [:]
            respuesta3 = []
            if(!session.productos){
                session.productos = []
            }
            def bandera = true 
            session.productosVista = []
            respuesta.nombreDelProducto = params.nombreDelProducto.toUpperCase()
            
            def uploadedFile = params.rutaImagenDefault
            def fileLabel = ".${uploadedFile.originalFilename.split("\\.")[-1]}"
            InputStream inputStream = uploadedFile.inputStream
            respuesta.archivo2 = inputStream
            respuesta.rutaImagenDefault = uploadedFile.originalFilename
            respuesta.extension = fileLabel.toLowerCase()
            //respuesta.rutaImagenDefault = params.rutaImagenDefault
            respuesta.descripcionProducto = params.descripcionProducto
            respuesta.tituloEnCotizador = params.tituloEnCotizador
            respuesta.claveDeProducto = params.claveDeProducto
            respuesta.haTenidoAtrasos = params.haTenidoAtrasos.toBoolean()
            respuesta.cat = params.cat
            respuesta.activo = params.activo.toBoolean()
            respuesta.montoMaximo = params.montoMaximo
            respuesta.montoMinimo = params.montoMinimo
            respuesta.tasaDeInteres = params.tasaDeInteres
            def lista =  params.list('tipoDeIngresosId[]')
            lista.each{
                println it
                def tipoDeIngresos = TipoDeIngresos.get(it as long)
                println "tipoDeIngresos"+tipoDeIngresos
                respuesta3.add(tipoDeIngresos.nombre)
            }
            respuesta.nombreDelIngreso = respuesta3


            respuesta.tipoDeIngresosId = params["tipoDeIngresosId[]"]
            respuesta.idRubroTemporal = params.idRubroTemp 
            respuesta.ok=true 
            respuesta.mensaje="El Producto se ha añadido Correctamente"
            // aqui poner un key con la palabra idproducto temporal y asignarle el it.id
            respuesta.claseIconoPaso = params.claseIconoPaso 
            session.productos << respuesta
            def x = 0
            session.productos.each{ 
                x++
                it.id = x
                
            }
            session.productos.findAll{(it.idRubroTemporal) as int == (params.idRubroTemp)as int}.each{
                respuesta3 = []
                bandera= false
                println "imprimiendo lo que tiene "+ it 
                respuesta2 = [:]
                respuesta2.nombreDelProducto = it.nombreDelProducto
                respuesta2.descripcionProducto = it.descripcionProducto
                respuesta2.montoMaximo = it.montoMaximo
                respuesta2.montoMinimo = it.montoMinimo
                for (item in it.tipoDeIngresosId) {
                    def tipoDeIngreso = TipoDeIngresos.get(item)
                    respuesta3.add(tipoDeIngreso.nombre)
                }
                respuesta2.nombreDelIngreso = it.nombreDelIngreso
                respuesta2.tipoDeIngreso = respuesta3
                respuesta2.idRubroTemporal = it.idRubroTemporal 
                respuesta2.id = it.id
                session.productosVista << respuesta2
            }
            if(bandera == true ){
                session.productosVista = []
                respuesta2 = [:]
                respuesta = [:]
                respuesta3 = []
            }
 
        } else {
            respuesta = [:]
            respuesta.ok= false
            respuesta.mensaje="Faltan campos por llenar"
        }
        println "Imprimiendo toda la sessionproductos"+ session.productos 
        def result = [session:session.productosVista,respuesta:respuesta]
        render result as JSON
    }
    
    
    def agregarPlazo(){
        println params
        def respuesta
        def respuesta2
        if(params.idproductoTemp && params.importeMaximo){
            respuesta = [:]
            if(!session.plazos){
                session.plazos = []
            }
            session.plazosVista = []
            if (params.usarListaDePlazos.toBoolean() == true){
                respuesta.usarListaDePlazos = params.usarListaDePlazos.toBoolean()
                respuesta.plazosPermitidos = params.plazosPermitidos
                respuesta.plazoMaximo = 0
                respuesta.plazoMinimo = 0
                respuesta.saltoSlider = 0 as long
            }else {
                respuesta.usarListaDePlazos = params.usarListaDePlazos.toBoolean()
                respuesta.saltoSlider = params.saltoSlider as long
                respuesta.plazosPermitidos = "0"
                respuesta.plazoMaximo = params.plazoMaximo
                respuesta.plazoMinimo = params.plazoMinimo
            }
            respuesta.importeMaximo = params.importeMaximo
            respuesta.importeMinimo = params.importeMinimo
           
            //respuesta.plazosPermitidos = params.plazosPermitidos
            respuesta.periodicidadId = params.periodicidadId
            respuesta.idproductoTemp = params.idproductoTemp
            respuesta.ok = true 
            respuesta.mensaje = "El Plazo ha sido añadido Correctamente"
            session.plazos << respuesta
            def x = 0
            session.plazos.each{ 
                x++
                it.id = x
                
            }
            session.plazos.findAll{(it.idproductoTemp) as int == (params.idproductoTemp)as int}.each{
                respuesta2 = [:]
                respuesta2.importeMaximo = it.importeMaximo
                respuesta2.importeMinimo = it.importeMinimo
                respuesta2.plazoMaximo = it.plazoMaximo
                respuesta2.plazoMinimo = it.plazoMinimo
                respuesta2.plazosPermitidos = it.plazosPermitidos
                def periodicidad = Periodicidad.get(it.periodicidadId)
                respuesta2.periodicidadId = periodicidad.nombre
                respuesta2.idproductoTemp = it.idproductoTemp
                respuesta2.id = it.id
                session.plazosVista << respuesta2
                
            }
  
        } else {
            respuesta = [:]
            respuesta.ok= false
            respuesta.mensaje="Faltan campos por llenar"       
        }
        def result = [sessionPlazosVista:session.plazosVista,respuesta:respuesta]
        render result as JSON
    }
    
    def agregarGarantia(){
        println params
        def respuesta
        def respuesta2
        if( params.idproductoTemp && params.cantidadMaxima){
            respuesta = [:]
            if(!session.garantias){
                session.garantias = []
            }
            session.garantiasVista = []
            respuesta.cantidadMaxima = params.cantidadMaxima
            respuesta.cantidadMinima = params.cantidadMinima
            respuesta.descripcion = params.descripcionGarantia
            respuesta.tipoDeGarantiaId = params.tipoDeGarantiaId as long
            def tipoDeGarantia = TipoDeGarantia.get(params.tipoDeGarantiaId as long)
            respuesta.tipoDeGarantiaNombre = tipoDeGarantia.nombre
            //respuesta.plazosPermitidos = params.plazosPermitidos
            respuesta.idproductoTemp = params.idproductoTemp
            respuesta.ok = true 
            respuesta.mensaje = "La Garantía ha sido añadida Correctamente."
            session.garantias << respuesta
            def x = 0
            session.garantias.each{ 
                x++
                it.id = x
                println it
                
            }
            session.garantias.findAll{(it.idproductoTemp) as int == (params.idproductoTemp)as int}.each{
                respuesta2 = [:]
                respuesta2.cantidadMaxima = it.cantidadMaxima
                respuesta2.cantidadMinima = it.cantidadMinima
                respuesta2.descripcion = it.descripcion
                println tipoDeGarantia
                respuesta2.tipoDeGarantiaNombre = it.tipoDeGarantiaNombre
                respuesta2.idproductoTemp = 1
                respuesta2.id = it.id
                session.garantiasVista << respuesta2
                
            }
  
        } else {
            respuesta = [:]
            session.garantias =[]
            session.garantiasVista =[]
            respuesta.ok= false
            respuesta.mensaje="Faltan campos por llenar"       
        }
        def result = [sessionGarantiasVista:session.garantiasVista,respuesta:respuesta]
        println "result in agregargarantias"
        println result
        render result as JSON
    }

    def buscarDocumentos(){
        println params
        def respuesta2
        def respuesta
        if(params.idRubro){
            session.prueba = []
            session.rubro = []
            println "imprimiendo la session tipodedocumentoid"
            println session.tipoDeDocumentoId
            def bandera = true 
            respuesta2 = [:]
            respuesta = [:]
            session.tipoDeDocumentoId.findAll{(it.idRubroTemporal) as int == (params.idRubro)as int }.each{
                respuesta2.nombreDeDocumento= it.nombreDeDocumento
                respuesta2.tipoDeDocumentoId= it.tipoDeDocumentoId
                respuesta2.idRubroTemporal = it.idRubroTemporal
                respuesta2.id = it.id
                
                session.prueba << respuesta2
                println session.prueba as JSON
                bandera = false 
            }
            session.nombre.findAll{(it.id) as int == (params.idRubro)as int }.each{
                println it 
                respuesta.texto = it.texto
                respuesta.descripcion = it.descripcion
                respuesta.id = it.id
                respuesta.idDiv = params.idDiv
                session.rubro << respuesta
            }
            if(bandera == true){
                respuesta2 = [:]
                respuesta = [:]
                session.prueba = []
                def result = [sessionPrueba:session.prueba,sessionRubro:session.rubro]
                render result as JSON
            }
            def result = [sessionPrueba:session.prueba,sessionRubro:session.rubro]
            println result
            render result as JSON
            
        }
    }
    
    def buscarPlazo(){
        println params
        def respuesta2
        def respuesta3
        def respuesta4
        if(params.idRubroTemp && params.idProductoTemp){
            session.prueba = []
            session.prueba2 = []
            respuesta2 = [:]
            respuesta3= []
             
            session.productos.findAll{(it.id) as int == (params.idProductoTemp)as int }.each{
                respuesta2.nombreDelProducto= it.nombreDelProducto
                respuesta2.descripcionProducto= it.descripcionProducto
                respuesta2.montoMaximo = it.montoMaximo
                respuesta2.montoMinimo = it.montoMinimo
                respuesta2.tasaDeInteres = it.tasaDeInteres
                for (item in it.tipoDeIngresosId) {
                    def tipoDeIngreso = TipoDeIngresos.get(item)
                    respuesta3.add(tipoDeIngreso.nombre)
                }
                respuesta2.nombreDelIngreso = it.nombreDelIngreso
                respuesta2.tipoDeIngreso = respuesta3
                respuesta2.cat = it.cat
                session.prueba << respuesta2
                println session.prueba as JSON
                
            }
            session.plazos.findAll{ (it.idproductoTemp)as int == (params.idProductoTemp)as int}.each{
                respuesta4 = [:]
                respuesta4.importeMaximo = it.importeMaximo as int 
                respuesta4.importeMinimo = it.importeMinimo as int
                respuesta4.plazoMaximo = it.plazoMaximo as int
                respuesta4.plazoMinimo = it.plazoMinimo as int
                respuesta4.plazosPermitidos = it.plazosPermitidos
                respuesta4.saltoSlider = it.saltoSlider
                respuesta4.usarListaDePlazos = it.usarListaDePlazos
                def periodicidad = Periodicidad.get(it.periodicidadId)
                respuesta4.periodicidadId = periodicidad.nombre
                respuesta4.idproductoTemp = it.idproductoTemp as int
                respuesta4.id = it.id as int
                session.prueba2 << respuesta4
            }
            println "imprimiendo session plazos completa en buscarPlazo"+session.plazos 
            def result = [sessionPrueba:session.prueba,sessionPrueba2:session.prueba2]
            println result
            render result as JSON
        }
        else{
            respuesta2 = [:]
            session.prueba = []
            session.prueba2 = []
            def result = [sessionPrueba:session.prueba,sessionPrueba2:session.Prueba2]
            println "imprimineod el result en el else"+result
            render result as JSON
        }
    }
    
    def buscarGarantia(){
        println params
        def respuesta2
        def respuesta3
        def respuesta4
        if(params.idRubroTemp && params.idProductoTemp){
            session.prueba = []
            session.prueba2 = []
            respuesta2 = [:]
            respuesta3= []
             
            session.productos.findAll{(it.id) as int == (params.idProductoTemp)as int }.each{
                respuesta2.nombreDelProducto= it.nombreDelProducto
                respuesta2.descripcionProducto= it.descripcionProducto
                respuesta2.montoMaximo = it.montoMaximo
                respuesta2.montoMinimo = it.montoMinimo
                respuesta2.tasaDeInteres = it.tasaDeInteres
                for (item in it.tipoDeIngresosId) {
                    def tipoDeIngreso = TipoDeIngresos.get(item)
                    respuesta3.add(tipoDeIngreso.nombre)
                }
                respuesta2.tipoDeIngreso = respuesta3
                respuesta2.nombreDelIngreso = it.nombreDelIngreso
                respuesta2.cat = it.cat
                session.prueba << respuesta2
                println session.prueba as JSON
                
            }
            session.garantias.findAll{ (it.idproductoTemp)as int == (params.idProductoTemp)as int}.each{
                respuesta4 = [:]
                respuesta4.cantidadMaxima = it.cantidadMaxima as int 
                respuesta4.cantidadMinima = it.cantidadMinima as int
                respuesta4.descripcion = it.descripcion 
                respuesta4.tipoDeGarantiaId = it.tipoDeGarantiaId as int
                respuesta4.tipoDeGarantiaNombre = it.tipoDeGarantiaNombre
                respuesta4.idproductoTemp = it.idproductoTemp as int
                respuesta4.id = it.id as int
                session.prueba2 << respuesta4
            }
            println "imprimiendo session GARANTIAS completa en BUSCARGARANTIAS"+session.garantias 
            def result = [sessionPrueba:session.prueba,sessionPrueba2:session.prueba2]
            println result
            render result as JSON
        }
        else{
            respuesta2 = [:]
            session.prueba = []
            session.prueba2 = []
            def result = [sessionPrueba:session.prueba,sessionPrueba2:session.Prueba2]
            render result as JSON
        }
    }

    def buscarProducto(){
        println params
        def respuesta
        def respuesta2
        def respuesta3
        if(params.idRubroTemporal){
            session.productosVista = []
            session.rubro = []
            respuesta = [:]
            respuesta2= []
            def bandera = true
            session.productos.findAll{(it.idRubroTemporal) as int == (params.idRubroTemporal)as int }.each{
                println it
                bandera = false
                respuesta = [:]
                respuesta.nombreDelProducto= it.nombreDelProducto
                respuesta.descripcionProducto= it.descripcionProducto
                respuesta.montoMaximo = it.montoMaximo
                respuesta.montoMinimo = it.montoMinimo
                respuesta.idRubroTemporal = it.idRubroTemporal
               
                //respuesta.tasaDeInteres = it.tasaDeInteres
                println "imprimiendo "+session.productos.find{(it.id) as int == (it.id)as int}
                for (item in it.tipoDeIngresosId) {
                    def tipoDeIngreso = TipoDeIngresos.get(item)
                    respuesta2.add(tipoDeIngreso.nombre)
                }
                respuesta.id = it.id
                respuesta.nombreDelIngreso = it.nombreDelIngreso
                respuesta.tipoDeIngreso = respuesta2
                session.productosVista << respuesta
            }
            respuesta = [:]
            session.nombre.findAll{(it.id) as int == (params.idRubroTemporal)as int }.each{
                respuesta.texto = it.texto
                respuesta.descripcion = it.descripcion
                respuesta.id = it.id
                respuesta.idDiv = params.idDiv
                session.rubro << respuesta
            }
            if (bandera == true ){
                session.productosVista = []
                respuesta = [:]
                respuesta2 = []
                def result = [sessionProductosVista:session.productosVista,sessionRubro:session.rubro]
                println "imprimiendo result"+ result
                render result as JSON
            }
            def result = [sessionProductosVista:session.productosVista,sessionRubro:session.rubro]
            println "imprimiendo result1"+ result
            render result as JSON
        }
    }
    
    def buscarVistas(){
        println params
        def respuesta
        def respuesta2
        if(params.idRubroTemporal){
            session.vistaVistas = []
            session.rubro = []
            respuesta = [:]
            respuesta2= [:]
            def bandera = true
            session.pasoId.findAll{(it.idRubroTemporal) as int == (params.idRubroTemporal)as int }.each{
                bandera = false
                respuesta = [:]
                respuesta.nombreDePaso= it.nombreDePaso
                respuesta.pasoId= it.pasoId
                respuesta.idRubroTemporal = it.idRubroTemporal
                respuesta.id = it.id
                session.vistaVistas << respuesta
            }
            session.nombre.findAll{(it.id) as int == (params.idRubroTemporal)as int }.each{
                println it 
                respuesta.texto = it.texto
                respuesta.descripcion = it.descripcion
                respuesta.id = it.id
                respuesta.idDiv = params.idDiv
                session.rubro << respuesta
            }
            if (bandera == true ){
                session.vistaVistas = []
                respuesta = [:]
                respuesta2 = [:]
                def result = [sessionvistaVistas:session.vistaVistas,sessionRubro:session.rubro]
                render result as JSON
            }
            def result = [sessionvistaVistas:session.vistaVistas,sessionRubro:session.rubro]
            println result
            render result as JSON
        }
    }
    
    def buscarRubros(){
        println params
        def respuesta
        def respuesta2
        if(params.idRubroTemporal){
            session.rubro = []
            respuesta = [:]
            respuesta2= [:]
            session.nombre.findAll{(it.id) as int == (params.idRubroTemporal)as int }.each{
                respuesta.texto = it.texto
                respuesta.descripcion = it.descripcion
                respuesta.id = it.id
                respuesta.posicion = it.posicion
                respuesta.tooltip = it.tooltip
                respuesta.textoTooltip = it.textoTooltip
                respuesta.claseIconoPaso = it.claseIconoPaso
                respuesta.activo = it.activo
                session.rubro << respuesta
            }
            println session.rubro
            render session.rubro as JSON
        }
    }

    def modificarCampoFormulario (){
        println params
        def respuesta
        if(params.idCampoFormularioTemp){
            session.campoFormulario.findAll{(it.id) as int == (params.idCampoFormularioTemp)as int }.each{
                println "Si existe uno"
                respuesta = [:]
                respuesta.id= it.id
                respuesta.idCampoFormulario= it.idCampoFormulario
                respuesta.tipoDeCampo = it.tipoDeCampo
                respuesta.tituloDelCampo = it.tituloDelCampo
                respuesta.longitudDelCampo = it.longitudDelCampo
                respuesta.numeroDeCampo = it.numeroDeCampo
                respuesta.placeholder = it.placeholder
                respuesta.textoAnterior = it.textoAnterior
                respuesta.textoPosterior = it.textoPosterior
                respuesta.tieneAyuda = it.tieneAyuda
                respuesta.textoAyuda = it.textoAyuda
                respuesta.saltoDeLineaAlFinal = it.saltoDeLineaAlFinal
                respuesta.mostrarAlInicio = it.mostrarAlInicio
                respuesta.obligatorio = it.obligatorio
                //respuesta.clase = it.clase
                //session.vistaVistas << respuesta
            }
            println respuesta
            
        }
        render(template: "/dashboard/configuracion/producto/detalleCampoFormulario", model: [campoFormulario:respuesta])
    }
    
    def agregarDocumento(){
        println params
        def respuesta
        def respuesta3
        if ((params["tipoDeDocumentoId[]"])){
       
            println "llega tipoDeDocumentoId"
            respuesta = [:]
            respuesta3 = []
            if(!session.tipoDeDocumentoId){
                session.tipoDeDocumentoId = []
            }
            
            def lista =  params.list('tipoDeDocumentoId[]')
            lista.each{
                println it
                def tipoDeDocumento = TipoDeDocumento.get(it as long)
                println "tipoDeDocumento"+tipoDeDocumento
                respuesta3.add(tipoDeDocumento.nombre)
            }

            respuesta.nombreDeDocumento = respuesta3
            respuesta.tipoDeDocumentoId = params["tipoDeDocumentoId[]"]
            respuesta.idRubroTemporal= params.idRubroTemp
            respuesta.Documentosllenos = true
            respuesta.ok = true
            respuesta.mensaje = "Los Documentos se añadieron correctamente"
            session.tipoDeDocumentoId << respuesta
            //println session.tipoDeDocumentoId.findAll{it.idRubroTemporal = 1 } << respuesta
        }
        else{
            respuesta = [:]
            respuesta.ok= false
            respuesta.mensaje="Faltan campos por llenar"
        }
        def x = 0
        session.tipoDeDocumentoId.each{ 
            x++
            it.id = x
        }
        println "imprimiendo prueba "
       
        println "imprimiendo session tipoDeDocumentoId"+ session.tipoDeDocumentoId
        println "imprimiendo session rubros"+ session.nombre
        def result = [session:session.tipoDeDocumentoId,respuesta:respuesta]
        println result 
        render result as JSON
    }
    
    def agregarVistas(){
        println params
        def respuesta
        def respuesta3
        def respuesta2
        if ((params["pasoCotizadorId[]"])){
            println "llega pasoId"
            respuesta = [:]
            respuesta3 = []
            def bandera = true 
            if(!session.pasoId){
                session.pasoId = []
            }
            session.vistaVistas = []

            def lista =  params.list('pasoCotizadorId[]')
            lista.each{
                def pasoCotizadorEntidadFinanciera = PasoCotizadorEntidadFinanciera.get(it)
                println "pasoId"+pasoCotizadorEntidadFinanciera
                respuesta3.add(pasoCotizadorEntidadFinanciera.tituloResumen)
            }
            
            respuesta.nombreDePaso= respuesta3
            respuesta.pasoId = lista
            respuesta.idRubroTemporal= params.idRubroTemp
            respuesta.ok = true
            respuesta.mensaje = "Las Vistas se Añadieron Correctamente"
            session.pasoId << respuesta
            def x = 0
            session.pasoId.each{ 
                x++
                it.id = x
                
            }
            
            session.pasoId.findAll{(it.idRubroTemporal) as int == (params.idRubroTemp)as int}.each{
                bandera = false
                respuesta2 = [:]
                respuesta2.nombreDePaso = it.nombreDePaso   
                respuesta2.pasoId = it.pasoId
                respuesta2.idRubroTemporal = it.idRubroTemporal
                respuesta2.id = it.id
                session.vistaVistas << respuesta2
            }
            if(bandera==true){
                respuesta2 = [:]
                respuesta3 = []
                respuesta = [:]
                session.vistaVistas = []
            }
          
            //session.nombre.get((params.idRubroTemp as int)-1) << respuesta
        }
        else{
            respuesta = [:]
            session.vistaVistas = [:] 
            respuesta3 = []
            respuesta.ok = false
            respuesta.mensaje = "Faltan Campos por Llenar"
        }
        println "??????????????????????????????????????"
        println "imprimiendo session de nombres"+session.nombreDePaso
        println "IMPRIMIENDO SESSON DE PASOiD"+ session.pasoId
        def result = [sessionvistaVistas:session.vistaVistas,respuesta:respuesta]
        render result as JSON
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
    
    def eliminarRubro(){
        println params
        def respuesta
        if(params.idRubro){
            if(session.nombre && session.nombre != null && session.nombre?.size() > 0){
                session.nombre.removeAll { it.id == (params.idRubro as int) }
                if(session.tipoDeDocumentoId && session.tipoDeDocumentoId != null && session.tipoDeDocumentoId?.size() > 0){
                    session.tipoDeDocumentoId.removeIf {  (it.idRubroTemporal as int) == (params.idRubro as int) }
                    // session.pasoId.removeIf {  (it.idRubroTemporal as int) == (params.idRubro as int) }

                }
            }
        } 
        def index
        session.nombre.each{ 
            def documentosLlenos = false
            def productosLlenos = false
            def vistasLlenas = false
            index = it.id 
            session.tipoDeDocumentoId.findAll{(it.idRubroTemporal)as int  == index }.each{
                println "si hay lleno"
                documentosLlenos= true
            }
            session.productos.findAll{(it.idRubroTemporal) as int == index}.each{
                println "si hay lleno"
                productosLlenos= true
            }
            session.pasoId.findAll{(it.idRubroTemporal) as int == index }.each{
                println "si hay lleno"
                vistasLlenas= true
            }
            it.documentosLlenos = documentosLlenos
            it.productosLlenos = productosLlenos
            it.vistasLlenas = vistasLlenas
        }
      
        render session.nombre as JSON
    }
    
    def eliminarVistas(){
        println params
        def respuesta
        def bandera = true
        if(params.idPaso && params.idRubroTemp){
            if(session.pasoId && session.pasoId != null && session.pasoId?.size() > 0){
                session.pasoId.removeAll { it.id == (params.idPaso as int) }
            }
            def x = 0
            session.pasoId.each{ 
                x++
                it.id = x
            }
            session.vistaVistas = []
            session.pasoId.findAll{(it.idRubroTemporal) as int == (params.idRubroTemp)as int }.each{
                bandera = false
                respuesta = [:]
                respuesta.nombreDePaso = it.nombreDePaso
                respuesta.pasoId = it.pasoId
                respuesta.idRubroTemporal = it.idRubroTemporal
                respuesta.id = it.id
                session.vistaVistas << respuesta
            }
            if(bandera== true){
                respuesta = [:]
                session.vistaVistas = []
            }
        }else{
            respuesta = [:]
            session.vistaVistas = []
        }
        render session.vistaVistas as JSON
    }
    
    def eliminarProducto(){
        println params
        def respuesta
        if(params.idProducto && params.idRubroTemp){
            session.productosVista = []
            // verficcar como se elimina aqui
            if(session.productos && session.productos != null && session.productos?.size() > 0){
                session.productos.removeAll { it.id == (params.idProducto as int) }
                if(session.plazos && session.plazos != null && session.plazos?.size() > 0){
                    session.plazos.removeIf { (it.idproductoTemp as int) == (params.idProducto as int) }
                }
                if(session.garantias && session.garantias != null && session.garantias?.size() > 0){
                    session.garantias.removeIf { (it.idproductoTemp as int) == (params.idProducto as int) }
                }
            }
            session.productos.findAll{(it.idRubroTemporal) as int == (params.idRubroTemp)as int}.each{
                respuesta = [:]
                println it 
                respuesta.nombreDelProducto = it.nombreDelProducto
                respuesta.descripcionProducto = it.descripcionProducto
                respuesta.montoMaximo = it.montoMaximo
                respuesta.montoMinimo = it.montoMinimo
                //respuesta2.tipoDeIngresosId = it.tipoDeIngresosId
                respuesta.idRubroTemporal = it.idRubroTemporal 
                respuesta.id = it.id
                session.productosVista << respuesta
            }
        }
        println "imprimiendo session productos"+ session.productos
        println "imprimiendo session plazos"+ session.plazos
        render session.productosVista as JSON
    }
    
    def eliminarPlazo(){
        println params
        def respuesta
        def bandera = true
        if(params.idPlazo && params.idProductoTemp){
            session.plazosVista = []
            if(session.plazos && session.plazos != null && session.plazos?.size() > 0){
                session.plazos.removeAll { it.id == (params.idPlazo as int) }
            }
            def x = 0
            session.plazos.each{ 
                x++
                it.id = x
            }            
            session.plazos.findAll{(it.idproductoTemp) as int == (params.idProductoTemp)as int}.each{
                bandera= false
                respuesta = [:]
                println "aqui llenar la session para mandarla al render"
                println it 
                respuesta.importeMaximo = it.importeMaximo
                respuesta.importeMinimo = it.importeMinimo
                respuesta.plazoMaximo = it.plazoMaximo
                respuesta.plazoMinimo = it.plazoMinimo
                respuesta.plazosPermitidos = it.plazosPermitidos
                respuesta.periodicidadId = it.periodicidadId
                respuesta.idproductoTemp = it.idproductoTemp
                respuesta.id = it.id
                println "imprimiendo lo que trae respuesta"+respuesta
                session.plazosVista << respuesta
                
            }
            if(bandera== true){
                respuesta = [:]
                session.plazosVista = []
            }
        }
        println session.plazos
        render session.plazosVista as JSON
    }
    
    def eliminarGarantia (){
        println params
        def respuesta
        def bandera = true
        if(params.idGarantia && params.idProductoTemp){
            session.garantiasVista = []
            if(session.garantias && session.garantias != null && session.garantias?.size() > 0){
                session.garantias.removeAll { it.id == (params.idGarantia as int) }
            }
            def x = 0
            session.garantias.each{ 
                x++
                it.id = x
            }            
            session.garantias.findAll{(it.idproductoTemp) as int == (params.idProductoTemp)as int}.each{
                bandera= false
                respuesta = [:]
                println it 
                respuesta.cantidadMaxima = it.cantidadMaxima
                respuesta.cantidadMinima = it.cantidadMinima
                respuesta.descripcion = it.descripcion
                respuesta.tipoDeGarantiaNombre = it.tipoDeGarantiaNombre
                respuesta.idproductoTemp = it.idproductoTemp
                respuesta.id = it.id
                println "imprimiendo lo que trae respuesta"+respuesta
                session.garantiasVista << respuesta
                
            }
            if(bandera== true){
                respuesta = [:]
                session.garantiasVista = []
            }
        }
        println session.garantias
        render session.garantiasVista as JSON
        
    }
    
    def eliminarDocumento(){
        println params
        def respuesta
        def bandera = true
        if(params.idDocumento && params.idRubroTemp){
            if(session.tipoDeDocumentoId && session.tipoDeDocumentoId != null && session.tipoDeDocumentoId?.size() > 0){
                session.tipoDeDocumentoId.removeAll { it.id == (params.idDocumento as int) }
            }
            def x = 0
            session.tipoDeDocumentoId.each{ 
                x++
                it.id = x
            }
            session.tipoDeDocumentoIdVista = []
            session.tipoDeDocumentoId.findAll{(it.idRubroTemporal) as int == (params.idRubroTemp)as int}.each{
                bandera= false
                respuesta = [:]
                println "aqui llenar la session para mandarla al render"
                println it 
                respuesta.nombreDeDocumento = it.nombreDeDocumento
                respuesta.tipoDeDocumentoId = it.tipoDeDocumentoId
                respuesta.idRubroTemporal = it.idRubroTemporal
                respuesta.id = it.id
                println "imprimiendo lo que trae respuesta"+respuesta
                session.tipoDeDocumentoIdVista << respuesta
                
            }
            if(bandera== true){
                respuesta = [:]
                session.tipoDeDocumentoIdVista = []
            }
        }
        println "imprimiendo session.tipoDeDocumentoVista"+session.tipoDeDocumentoIdVista
        render session.tipoDeDocumentoIdVista as JSON
    }
    
    
    def eliminarCampoFormulario(){
        println params
        def respuesta
        if(params.idCampoFormulario ){
            session.plazosVista = []
            if(session.campoFormulario && session.campoFormulario != null && session.campoFormulario?.size() > 0){
                session.campoFormulario.removeAll { it.id == (params.idCampoFormulario as int) }
                respuesta = [:]
                respuesta.ok= true
                respuesta.mensaje= "El campo se ha eliminado."
            }
            def x = 0
            session.campoFormulario.each{ 
                x++
                it.id = x
            }                      
        }
       
        session.campoFormulario.sort{it.numeroDeCampo}
        def result = [session:session.campoFormulario,respuesta:respuesta]
        println "imprimiendo la session"+session.campoFormulario
        render result as JSON
    }
    
    def vistaPrevia(){
        println params
        if (session.nombre && session.nombre != null && session.nombre?.size() > 0) {
            render(template: "/dashboard/configuracion/vistaPreviaConfiguracionCotizador",model: [generales:session.nombre])
  
        }
    }
    
        
    def verificar(){
        println params
        def respuesta = [:]
        def bandera = false
        if ((session.nombre && session.nombre != null && session.nombre?.size() > 0) &&
            (session.tipoDeDocumentoId && session.tipoDeDocumentoId != null && session.tipoDeDocumentoId?.size() > 0) &&
            (session.productos && session.productos != null && session.productos?.size() > 0) &&
            (session.plazos && session.plazos != null && session.plazos?.size() > 0) &&
            (session.pasoId && session.pasoId != null && session.pasoId?.size() > 0)
        ){
            println "imprimiendo session de los rubros"
            println session.nombre
            
            println "imprimiendo session de los documentos"
            println session.tipoDeDocumentoId
            
            println "imprimiendo session de los productos"
            println session.productos
            
            
            println "imprimiendo session de los plazos"
            println session.plazos 
            
            println "imprimiendo session de los productos ingresos"
            println session.productos
            
       
            
            println "imprimiendo session de los plazos"
            println session.plazos
            println "imprimiendo session de las vistas"
            println session.pasoId
            def usuario = springSecurityService.currentUser
            def nombreEmpresa = session.usuario.entidadFinanciera.nombre
            nombreEmpresa = nombreEmpresa.replaceAll("[^a-zA-Z0-9]+","")
            println nombreEmpresa
            session.nombre.each {
                def rubroDeAplicacionDeCredito = new RubroDeAplicacionDeCredito();
                rubroDeAplicacionDeCredito.nombre = it.texto
                rubroDeAplicacionDeCredito.posicion = it.posicion as long
                rubroDeAplicacionDeCredito.descripcion = it.descripcion
                rubroDeAplicacionDeCredito.claseIconoPaso = it.claseIconoPaso
                rubroDeAplicacionDeCredito.tooltip = it.tooltip.toBoolean()
                rubroDeAplicacionDeCredito.textoTooltip = it.textoTooltip
                rubroDeAplicacionDeCredito.activo = it.activo.toBoolean()
                rubroDeAplicacionDeCredito.entidadFinanciera = session.usuario.entidadFinanciera
                def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa +"/imagenesCotizador"
                rubroDeAplicacionDeCredito.urlImagen =  ruta + "/" + it.nombreDelArchivo
                def idRubroTemporal = it.id as int
                if( rubroDeAplicacionDeCredito.save(flush:true)&& (bandera == false)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (rubroDeAplicacionDeCredito.urlImagen)
                    File file = new File(rubroDeAplicacionDeCredito.urlImagen)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << it.archivo
                        }
                    }
                  
                    session.tipoDeDocumentoId.each{
                        if ((it.idRubroTemporal as int)== idRubroTemporal ){
                            for (item in it.tipoDeDocumentoId) {
                                def tipoDeDocumento = TipoDeDocumento.get(item)
                                def rubroDeAplicacionTipoDeDocumento = new RubroDeAplicacionTipoDeDocumento();
                                
                                rubroDeAplicacionTipoDeDocumento.tipoDeDocumento = tipoDeDocumento
                                rubroDeAplicacionTipoDeDocumento.rubro = rubroDeAplicacionDeCredito
                                if (rubroDeAplicacionTipoDeDocumento.save(flush:true)&& (bandera == false)){
                                    println "documentos Guardados"
                                }
                                else {
                                    if (rubroDeAplicacionTipoDeDocumento.hasErrors()) {
                                        rubroDeAplicacionTipoDeDocumento.errors.allErrors.each {
                                            println it
                                        }
                                    }
                                    bandera = true
                                    respuesta.error = true
                                    respuesta.mensaje = "Ocurrio un problema al guardar el rubroDeAplicacionProducto. Intente nuevamente más tarde."
                                }
                            }
                        }
                    }
                    session.pasoId.each{
                        if ((it.idRubroTemporal as int)== idRubroTemporal ){
                            for (paso in it.pasoId) {
                                def pasoCotizadorEntidadFinanciera =  PasoCotizadorEntidadFinanciera.get(paso)
                                def rubroDeAplicacionPasoCotizador = new RubroDeAplicacionPasoCotizador ()
                                rubroDeAplicacionPasoCotizador.paso = pasoCotizadorEntidadFinanciera
                                rubroDeAplicacionPasoCotizador.rubro = rubroDeAplicacionDeCredito
                                if (rubroDeAplicacionPasoCotizador.save(flush:true) && (bandera == false)){
                                    println "Vistas Guardadas"
                                }
                                else {
                                    if (rubroDeAplicacionPasoCotizador.hasErrors()) {
                                        rubroDeAplicacionPasoCotizador.errors.allErrors.each {
                                            println it
                                        }
                                    }
                                    bandera = true
                                    respuesta.error = true
                                    respuesta.mensaje = "Ocurrio un problema al guardar el rubroDeAplicacionPasoCotizador. Intente nuevamente más tarde."
                                }
                            }
                        }
                        
                    }
                    
                    session.productos.each{
                        def idProductoTemp = it.id as int
                        println it
                        println it.idRubroTemporal
                        println it.nombreDelProducto
                        if((it.idRubroTemporal as int) == idRubroTemporal  ){
                            def producto = new Producto()
                            producto.nombreDelProducto = it.nombreDelProducto
                            producto.descripcion = it.descripcionProducto
                            producto.tituloEnCotizador = it.tituloEnCotizador.toUpperCase()
                            producto.claveDeProducto = it.claveDeProducto as int
                            producto.claseIconoPaso = it.claseIconoPaso
                            producto.cat = it.cat as float
                            producto.activo = it.activo.toBoolean()
                            producto.montoMaximo = it.montoMaximo as float
                            producto.montoMinimo = it.montoMinimo as float
                            producto.tasaDeInteres = (it.tasaDeInteres as float)/12
                            producto.tasaDeInteresAnual = it.tasaDeInteres as float
                            producto.entidadFinanciera = session.usuario.entidadFinanciera
                            def esquema = Esquema.get(1)
                            def marca = Marca.get(5)
                            producto.esquema = esquema
                            producto.marca = marca
                            def tipoDeTasaDeInteres = TipoDeTasaDeInteres.get(1)
                            def tipoDeProducto = TipoDeProducto.get(2)
                            producto.tipoDeTasa = tipoDeTasaDeInteres
                            producto.tipoDeProducto = tipoDeProducto 
                            producto.rutaImagenDefault =  ruta + "/" + it.rutaImagenDefault
                            if(producto.save(flush:true) && (bandera == false) ){
                                println "bien producto guardado"
                                def subdir2 = new File(ruta)
                                subdir2.mkdir()
                                println (producto.rutaImagenDefault)
                                File file2 = new File(producto.rutaImagenDefault)
                                if (file2.exists() || file2.createNewFile()) {
                                    file2.withOutputStream{fos->
                                        fos << it.archivo2
                                    }
                                }
                                def i
                                for (i = 0; i <it.tipoDeIngresosId.size(); i++) {
                                    def tipoDeIngresos = TipoDeIngresos.get(it.tipoDeIngresosId[i])
                                    def rubroDeAplicacionProducto = new RubroDeAplicacionProducto()
                                    rubroDeAplicacionProducto.haTenidoAtrasos = it.haTenidoAtrasos.toBoolean()
                                    rubroDeAplicacionProducto.producto = producto
                                    rubroDeAplicacionProducto.rubro= rubroDeAplicacionDeCredito
                                    rubroDeAplicacionProducto.tipoDeIngresos = tipoDeIngresos
                                    
                                    println tipoDeIngresos
                                    if(rubroDeAplicacionProducto.save(flush:true)&& (bandera == false)){
                                        println "se guardo bien todo"
                                    }
                                    else {
                                        if (rubroDeAplicacionProducto.hasErrors()) {
                                            rubroDeAplicacionProducto.errors.allErrors.each {
                                                println it
                                            }
                                        }
                                        respuesta.error = true
                                        respuesta.mensaje = "Ocurrio un problema al guardar el rubroDeAplicacionProducto. Intente nuevamente más tarde."
                                    }
                                }
                                session.garantias.each{
                                    if ((it.idproductoTemp as int)== idProductoTemp ){ 
                                        def garantiaProducto = new GarantiaProducto ()
                                        garantiaProducto.cantidadMaxima = it.cantidadMaxima as long
                                        garantiaProducto.cantidadMinima = it.cantidadMinima as long
                                        garantiaProducto.descripcion = it.descripcion
                                        def tipoDeGarantia = TipoDeGarantia.get(it.tipoDeGarantiaId as long)
                                        garantiaProducto.tipoDeGarantia = tipoDeGarantia
                                        garantiaProducto.producto = producto
                                        if(garantiaProducto.save(flush:true)&& (bandera == false)){
                                            println "se guardo bien la garantia"
                                        }
                                        else {
                                            if (garantiaProducto.hasErrors()) {
                                                garantiaProducto.errors.allErrors.each {
                                                    println it
                                                }
                                            }
                                            bandera = true
                                            respuesta.error = true
                                            respuesta.mensaje = "Ocurrio un problema al guardar la garantia. Intente nuevamente más tarde."
                                        }
                                    }
                                }
                                
                                session.plazos.each{
                                    if ((it.idproductoTemp as int)== idProductoTemp ){
                                        def plazoProducto = new PlazoProducto ()
                                        plazoProducto.importeMaximo = it.importeMaximo as long
                                        plazoProducto.importeMinimo = it.importeMinimo as long
                                        plazoProducto.plazoMaximo = it.plazoMaximo as long
                                        plazoProducto.plazoMinimo = it.plazoMinimo as long
                                        plazoProducto.plazosPermitidos = it.plazosPermitidos 
                                        plazoProducto.producto = producto
                                        plazoProducto.usarListaDePlazos = true
                                        plazoProducto.saltoSlider = it.saltoSlider
                                        plazoProducto.usarListaDePlazos = it.usarListaDePlazos
                                        def periodicidad = Periodicidad.get(it.periodicidadId as int)
                                        plazoProducto.periodicidad = periodicidad
                                        if(plazoProducto.save(flush:true)&& (bandera == false)){
                                            println "se guardo bien todo"
                                        }
                                        else {
                                            if (plazoProducto.hasErrors()) {
                                                plazoProducto.errors.allErrors.each {
                                                    println it
                                                }
                                            }
                                            bandera = true

                                            respuesta.error = true
                                            respuesta.mensaje = "Ocurrio un problema al guardar el rubroDeAplicacionProducto. Intente nuevamente más tarde."
                                        }
                                    }
                                } 
                                
                            }  else {
                                if (producto.hasErrors()) {
                                    producto.errors.allErrors.each {
                                        println it
                                    }
                                }
                                bandera = true

                                respuesta.error = true
                                respuesta.mensaje = "Ocurrio un problema al guardar el rubroDeAplicacionProducto. Intente nuevamente más tarde."
                            }
                        }
                    }
                   
                }
                else {
                    bandera = true
                    if (rubroDeAplicacionDeCredito.hasErrors()) {
                        rubroDeAplicacionDeCredito.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un problema al guardar el rubroDeAplicacionProducto. Intente nuevamente más tarde."
                }
                
            }
            session.nombre = [:]
            session.tipoDeDocumentoId =[:]
            session.productos = [:]
            session.plazos = [:]
            session.pasoId = [:]
            session.tipoDeIngresos = [:]

            
        }else{
            bandera = true
            respuesta.error = true
            respuesta.mensaje = "No estan llenos todos los pasos"
        }
        if(bandera == false){
            respuesta.ok = true
            respuesta.mensaje = "Configuracion Guardada"
        }
        println respuesta
        render respuesta as JSON
        
    }

    def obtenerTipoDeCampo (){
        def respuesta = [:]
        def tipoDeCampo = TipoDeCampo.get(params.idTipoDeCampo as long)
        def campoFormulario = CampoFormulario.findAllWhere(tipoDeCampo:tipoDeCampo)
        respuesta.campoFormulario = campoFormulario
        respuesta.claseAsociada = campoFormulario.claseAsociada
        render respuesta as JSON
        
    }
   
    def buscarPaso (){
        session.campoFormulario = []
        session.pasoSolicitudEntidadFinanciera = []
        def respuesta
        println params
        def parrafos
        def pasoSolicitudEntidadFinanciera =  PasoSolicitudEntidadFinanciera.get(params.idPasoSolicitud as long)
        def listaTipoDeCampo = TipoDeCampo.findAll();
        def campoPasoSolicitud = CampoPasoSolicitud.findAllWhere(pasoSolicitud:pasoSolicitudEntidadFinanciera)
        campoPasoSolicitud = campoPasoSolicitud.sort{it.numeroDeCampo}
        pasoSolicitudEntidadFinanciera.each{
            println it.titulo
            respuesta = [:]
            respuesta.titulo = it.titulo
            respuesta.subtitulo = it.subtitulo
            respuesta.modoDeDespliegue = it.modoDeDespliegue
            respuesta.numeroDePaso = it.numeroDePaso

            session.pasoSolicitudEntidadFinanciera << respuesta
        }
        campoPasoSolicitud.each{
            respuesta = [:]
            respuesta.campoPasoSolicitudId = it.id
            respuesta.tipoDeCampo = it.campo.tipoDeCampo
            respuesta.idCampoFormulario = it.campo.id
            respuesta.tituloDelCampo = it.tituloDelCampo
            respuesta.longitudDelCampo = it.longitudDelCampo
            respuesta.numeroDeCampo = it.numeroDeCampo as int
            respuesta.placeholder = it.placeholder.toString()
            respuesta.textoAnterior = it.textoAnterior.toString()
            respuesta.textoPosterior = it.textoPosterior.toString()
            respuesta.tieneAyuda = it.tieneAyuda.toBoolean()
            if (it.textoAyuda== null){
                respuesta.textoAyuda = ""
            }else{
                respuesta.textoAyuda = it.textoAyuda.toString()
            }
            respuesta.saltoDeLineaAlFinal = it.saltoDeLineaAlFinal.toBoolean()
            respuesta.mostrarAlInicio = it.mostrarAlInicio.toBoolean()
            respuesta.obligatorio = it.obligatorio.toBoolean()
            respuesta.parrafo = it.parrafo
            respuesta.ok = true
            respuesta.mensaje = "El Campo se añadido correctamente"
            session.campoFormulario  << respuesta
        }
        def x = 0 
        session.campoFormulario.each{
            x++
            it.id = x
        }
        session.campoFormulario.sort{it.numeroDeCampo}
        respuesta = [:]

        if(pasoSolicitudEntidadFinanciera.tipoDePaso.nombre == "pasoFormulario"){
            render(template: "/dashboard/configuracion/pasoSolicitudEntidadFinanciera/configurarPasoSolicitud", model: [pasoActual:pasoSolicitudEntidadFinanciera,listaTipoDeCampo:listaTipoDeCampo,campoFormulario:session.campoFormulario]) 

        }
        else if(pasoSolicitudEntidadFinanciera.tipoDePaso.nombre == "resumen"){
            respuesta.mensaje = "EL PASO RESUMEN NO ES CONFIGURABLE"
            respuesta.ok = true
            render respuesta as JSON
        }
        else if(pasoSolicitudEntidadFinanciera.tipoDePaso.nombre == "consultaBuro"){
            respuesta.mensaje = "EL PASO CONSULTA BURÓ NO ES CONFIGURABLE"
            respuesta.ok = true
            render respuesta as JSON
        }
        else if(pasoSolicitudEntidadFinanciera.tipoDePaso.nombre == "consultaBancaria"){
            respuesta.mensaje = "EL PASO CONSULTA BANCARIA NO ES CONFIGURABLE"
            respuesta.ok = true
            render respuesta as JSON
        }
        else if(pasoSolicitudEntidadFinanciera.tipoDePaso.nombre == "confirmacion"){
            respuesta.mensaje = "EL PASO CONFIRMACION NO ES CONFIGURABLE"
            respuesta.ok = true
            render respuesta as JSON
        }
        
    }
    
    def registrarConfiguracionPasoSolicitud (){
        
        def respuesta = [:]
        println params
        def pasoSolicitudEntidadFinanciera =  PasoSolicitudEntidadFinanciera.get(params.idPasoSolicitudEntidadFinanciera as long)
        def campo =  CampoPasoSolicitud.findAllWhere(pasoSolicitud:pasoSolicitudEntidadFinanciera )
        campo.each{ a ->
            a.delete(flush:true)
        }
        session.campoFormulario.each{
       
            def campoPasoSolicitud = new CampoPasoSolicitud ()
            def campoFormulario = CampoFormulario.get(it.idCampoFormulario as long) 
            campoPasoSolicitud.campo = campoFormulario
            campoPasoSolicitud.longitudDelCampo = it.longitudDelCampo
            campoPasoSolicitud.tituloDelCampo = it.tituloDelCampo
            campoPasoSolicitud.numeroDeCampo = it.numeroDeCampo as long
            campoPasoSolicitud.placeholder = it.placeholder
            campoPasoSolicitud.textoAnterior = it.textoAnterior
            campoPasoSolicitud.textoPosterior = it.textoPosterior
            campoPasoSolicitud.tieneAyuda = it.tieneAyuda.toBoolean()
            campoPasoSolicitud.textoAyuda = it.textoAyuda
            campoPasoSolicitud.saltoDeLineaAlFinal = it.saltoDeLineaAlFinal.toBoolean()
            campoPasoSolicitud.mostrarAlInicio = it.mostrarAlInicio.toBoolean()
            campoPasoSolicitud.obligatorio = it.obligatorio.toBoolean()
            campoPasoSolicitud.parrafo = it.parrafo
            campoPasoSolicitud.pasoSolicitud = pasoSolicitudEntidadFinanciera 
            // campoPasoSolicitud.dependeDe = 
            //campoPasoSolicitud.valorDeDependencia = 
            if (campoPasoSolicitud.save(flush:true)){
                respuesta.ok = true 
                respuesta.mensaje = "La Configuracion se Guardo Correctamente."
            }else{
                if (campoPasoSolicitud.hasErrors()) {
                    campoPasoSolicitud.errors.allErrors.each {
                        println it
                    }
                }
                respuesta.error = true
                respuesta.mensaje = "No se Pudo Guardar la Configuracion Intente Más Tarde"
            }
        }
        render respuesta as JSON
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
        if(session["pasoFormulario"]?.cliente?.clienteGuardado && 
            session["pasoFormulario"].direccionCliente && 
            session["pasoFormulario"].empleoCliente){
            if(!session.identificadores){
                session.identificadores = [:]
            }
            if(!session.identificadores.idCliente) {
                session.identificadores.idCliente = session["pasoFormulario"]?.cliente?.idCliente
                session.identificadores.idSolicitud = session["pasoFormulario"]?.cliente?.idSolicitud
                session.identificadores.idDireccion = session["pasoFormulario"]?.direccionCliente?.idDireccion
                session.identificadores.idEmpleo = session["pasoFormulario"]?.empleoCliente?.idEmpleo
                def solicitud = SolicitudDeCredito.get(session.identificadores.idSolicitud as long)
                respuesta.folio = solicitud.folio
            }
        } else {
            respuesta.error = Boolean.TRUE
        }
        println session.identificadores
        render respuesta as JSON
    }

    def obtenerOfertas(){
        println params
        def respuesta = [:]
        session.ofertas = null
	def  username = session.usuario
        try {
            def ofertas = perfiladorService.obtenerPropuestas("perfilador", session.identificadores, session["pasoFormulario"]?.cliente?.tipoDeDocumento, session["pasoFormulario"]?.cliente?.clienteExistente, session.perfil,  username)
            session.ofertas = ofertas
            respuesta.ofertas = ofertas
            bitacoraOfertasService.registrarBitacora(SolicitudDeCredito.get(session.identificadores.idSolicitud as long),'Abandonó','Ninguno')
        } catch (BusinessException ex) {
            respuesta.motivoRechazo = ex.message
        }

        render respuesta as JSON
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

    def validNewPassword(){
        User usuario = new User(request.JSON)
        
        def response = userService.validNewPassword(usuario)
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
            response.setHeader("Content-disposition", "attachment;filename=\"" + "Reporte_Centro_de_Contacto" + ".xlsx\"")
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

    def informes(){
    }
   
    def getInformes() {
        session.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        def informes = []
        if(params.temporalidad && params.grafica) {
            informes = reporteService.obtenerInformesAnalitica(params.grafica,  (params.temporalidad ? (params.temporalidad as int) : null) , params.fechaInicio, params.fechaFinal, session.entidadFinanciera)
        }
        render informes as JSON
    }

    def printReport(){
        def configuracion = session.configuracion
        def productoSolicitud = ProductoSolicitud.get(params.idProductoSolicitudPrintPerfilador as long)
        def resultadoMotorDeDecision = ResultadoMotorDeDecision.findWhere(solicitud: productoSolicitud.solicitud)
        def mapa = []
        def respuesta = [:]
        respuesta.folio = ("" + productoSolicitud?.solicitud?.folio).padLeft(6, '0')
        respuesta.montoDelCredito = productoSolicitud.montoDelCredito
        respuesta.plazos = productoSolicitud.plazos
        respuesta.nomenclatura = productoSolicitud.periodicidad.nomenclatura
        respuesta.montoDelSeguroDeDeuda = productoSolicitud.montoDelSeguroDeDeuda
        respuesta.nombreDelProducto = productoSolicitud.producto?.nombreDelProducto
        respuesta.cat =  (productoSolicitud?.cat) ? ((productoSolicitud?.cat * 100).round(1)) : 0 
        respuesta.sucursal = productoSolicitud?.solicitud?.sucursal
        respuesta.ubicacion = productoSolicitud?.solicitud?.sucursal.ubicacion
        respuesta.documentoElegidoCantidad = productoSolicitud?.documentoElegido?.cantidadSolicitada
        respuesta.documentoElegido = productoSolicitud?.documentoElegido?.nombre
        respuesta.nombreComercial = configuracion?.nombreComercial?.toUpperCase()
        if(resultadoMotorDeDecision){
            respuesta.dictamenFinal = resultadoMotorDeDecision?.dictamenFinal
        }else{
            respuesta.dictamenFinal = false
        }
        respuesta.tasaDeInteres =  (productoSolicitud?.tasaDeInteres) ? ((productoSolicitud?.tasaDeInteres * 100).round(2)) : 0 
        respuesta.liberasistencia = productoSolicitud?.montoDeServicioDeAsistencia
        respuesta.perfilador = true
        respuesta.nombreComercial = configuracion?.nombreComercial?.toUpperCase()
        respuesta.nombreCliente = productoSolicitud?.solicitud?.cliente
        respuesta.rutaLogotipoFormatoBuro = configuracion?.rutaLogotipoFormatoBuro
        respuesta.montoPagoBuro =  productoSolicitud?.solicitud?.montoPagoBuro
        respuesta.claveDeProducto =  productoSolicitud.producto?.claveDeProducto
        mapa << respuesta
        params._format= "PDF"
        chain(controller: "jasper", action: "index", model: [data: mapa], params:params)
    }
    
    def printBcFormat(){
        def configuracion = session.configuracion
        def cliente = Cliente.get(session.identificadores.idCliente as long)
        def direccion = DireccionCliente.get(session.identificadores.idDireccion as long)
        def telefonoCliente = TelefonoCliente.findWhere(cliente:cliente , tipoDeTelefono: TipoDeTelefono.get(2))
        def solicitud = SolicitudDeCredito.get(session.identificadores.idSolicitud as long)

        def mapa = []
        def respuesta = [:]
        SimpleDateFormat formato = new SimpleDateFormat("d 'de' MMMM 'de' yyyy", new Locale("es", "ES")); 
        String fecha = formato.format(new Date());
        respuesta.razonSocial = configuracion?.razonSocial
        respuesta.nombreCliente = cliente
        respuesta.rfc = cliente?.rfc?.toUpperCase()
        respuesta.calle = direccion?.calle
        respuesta.numeroExterior = "No. ${direccion?.numeroExterior}"
        respuesta.numeroInterior = (direccion?.numeroInterior ? direccion?.numeroInterior : " ")
        respuesta.colonia = direccion?.colonia
        respuesta.municipioDelegacion = direccion?.codigoPostal?.municipio?.nombre
        respuesta.estado = direccion?.codigoPostal?.municipio?.estado?.nombre
        respuesta.codigoPostal = direccion?.codigoPostal
        respuesta.telefono = telefonoCliente?.numeroTelefonico
        respuesta.lugar = "${(solicitud?.sucursal?.municipio?.nombre ? solicitud?.sucursal?.municipio?.nombre : "N/A")}, ${solicitud?.sucursal?.estado?.siglasrenapo} A ${fecha.toUpperCase()}"
        respuesta.rutaLogotipoFormatoBuro = configuracion?.rutaLogotipoFormatoBuro
        respuesta.nombreEntidadFinancieraFormatoBuro = configuracion?.nombreEntidadFinancieraFormatoBuro
        respuesta.codigoCalidad = configuracion?.codigoCalidad
        mapa << respuesta
        params._format= "PDF"
        chain(controller: "jasper", action: "index", model: [data: mapa], params:params)
    }

    def descargarReporteOperaciones(){
        def reporte  = reporteService.obtenerReporte("operaciones",session.usuario.entidadFinanciera,params.from2,params.to2)
        if(reporte) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=\"" + "Reporte_Operaciones" + ".xlsx\"")
            response.outputStream << reporte.bytes
        } else {
            flash.error = "No se encontraron registros correspondientes al criterio de búsqueda."
            redirect action: "reportes"
        }
    }
    def getUsersBusqueda() {
        def page = request.JSON.page as long
        def nombreUsuarioBusqueda
        def usernameUsuarioBusqueda
        def respuesta = [:]
        respuesta.page = page
       
        Pager pager = new Pager(respuesta)
        def entidadFinanciera = session.usuario.entidadFinanciera
        def users = userService.getUsersBusqueda(entidadFinanciera, pager,request.JSON.nombreUsuarioBusqueda,request.JSON.usernameUsuarioBusqueda)

        def response = [:]
        response.usuarios = users
        response.totalPages = pager.totalPages
        response.page = pager.page
        render response as JSON
    }
    
    def descargarReporteBitacoraMitek(){
        def reporte  = reporteService.obtenerReporte("bitacoraMitek",session.usuario.entidadFinanciera,params.from3,params.to3)
        if(reporte) {
            response.setContentType("application/octet-stream")
            response.setHeader("Content-disposition", "attachment;filename=\"" + "Reporte_BitacoraMitek" + ".xlsx\"")
            response.outputStream << reporte.bytes
        } else {
            flash.error = "No se encontraron registros correspondientes al criterio de búsqueda."
            redirect action: "reportes"
        }
    }
}
