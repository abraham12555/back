package la.kosmos.app

import grails.transaction.Transactional
import groovy.sql.Sql
import java.util.Calendar
import java.nio.file.Files;
import java.text.SimpleDateFormat
import la.kosmos.app.bo.Pager

@Transactional
class DashboardService {

    def springSecurityService
    def geocoderService
    def dataSource
    def userService
    def grailsApplication

    def listaGeneralDeSolicitudes(def params) {
        println params
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def query = "SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.entidadFinanciera.id = " + usuario.entidadFinanciera.id 
        def roles = springSecurityService.principal?.authorities*.authority
        if( roles.contains('ROLE_EJECUTIVO') || roles.contains('ROLE_SUCURSAL')){
            query += " And ps.solicitud.sucursal.id = " + usuario.sucursal.id
            if (roles.contains('ROLE_EJECUTIVO')) {
                query += " And ps.solicitud.registradaPor.id = " + usuario.id
            } else {
                query += " And ps.solicitud.registradaPor IS NOT NULL "
            }
        }
        query += " order by ps.solicitud.fechaDeSolicitud"
        if (params.offset==null && params.max==null) { 
            println "vacios params"
            def resultado = ProductoSolicitud.executeQuery(query, [offset:0, max:5])
            resultado?.each {
                def solicitud = [:]
                solicitud.id = it.solicitud.id
                solicitud.folio = it.solicitud.folio
                solicitud.nombreCliente = it.solicitud.cliente.toString()
                solicitud.statusDeSolicitud = it.solicitud.statusDeSolicitud.toString()
                solicitud.puntoDeVenta = it.solicitud.puntoDeVenta?.toString()
                solicitud.autenticadoMediante = "Plataforma"
                solicitud.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
                solicitud.fechaDeSolicitud = it.solicitud.fechaDeSolicitud
                solicitud.montoCredito = it.montoDelCredito
                respuesta << solicitud
            }  
        } else {
            println "llenos params"
            def resultado = ProductoSolicitud.executeQuery(query, [offset:params.offset, max:params.max])
            resultado?.each {
                def solicitud = [:]
                solicitud.id = it.solicitud.id
                solicitud.folio = it.solicitud.folio
                solicitud.nombreCliente = it.solicitud.cliente.toString()
                solicitud.statusDeSolicitud = it.solicitud.statusDeSolicitud.toString()
                solicitud.puntoDeVenta = it.solicitud.puntoDeVenta?.toString()
                solicitud.autenticadoMediante = "Plataforma"
                solicitud.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
                solicitud.fechaDeSolicitud = it.solicitud.fechaDeSolicitud
                solicitud.montoCredito = it.montoDelCredito
                respuesta << solicitud
            }                 
        }
        return respuesta
    }

    def getUsers(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 25
        def userList = []
        def criteria = Usuario.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("username", "asc")
        }
        pager.totalRows = results.totalCount
        results.each{
            def users = [:]
            users.id = it.id
            users.username = it.username
            users.fullName = it.toString()
            users.email = it.email
            users.authorities = it.authorities
            userList << users
        }
        return userList
    }

    def getSegurosSobreDeuda(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 25
        def segurosSobreDeudaList = []
        def criteria = SeguroSobreDeuda.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("id", "asc")
        }
        pager.totalRows = results.totalCount
        results.each{
            def segurosSobreDeuda = [:]
            segurosSobreDeuda.id = it.id
            segurosSobreDeuda.montoInicial = it.montoInicial
            segurosSobreDeuda.montoFinal = it.montoFinal
            segurosSobreDeuda.importeSeguro = it.importeSeguro
            segurosSobreDeuda.plazoAnual = it.plazoAnual
            segurosSobreDeuda.porcentaje = it.porcentaje
            segurosSobreDeuda.porcentajeSeguro = it.porcentajeSeguro

            segurosSobreDeudaList << segurosSobreDeuda
        }
        return segurosSobreDeudaList
    }
    
    def getServicioDeAsistencia(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 25
        def servicioDeAsistenciaList = []
        def criteria = ServicioDeAsistencia.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("id", "asc")
        }
        pager.totalRows = results.totalCount
        results.each{
            def servicioDeAsistencia = [:]
            servicioDeAsistencia.id = it.id
            servicioDeAsistencia.montoFinal = it.montoFinal
            servicioDeAsistencia.montoInicial = it.montoInicial
            servicioDeAsistencia.importeAsistencia = it.importeAsistencia
            servicioDeAsistencia.plazoAnual = it.plazoAnual
            servicioDeAsistencia.plazoQuincenal = it.plazoQuincenal
            servicioDeAsistencia.plazoSemanal = it.plasoSemanal
            servicioDeAsistenciaList << servicioDeAsistencia
        }
        return servicioDeAsistenciaList
    }
    
    def getPasosSolicitud(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 5
        def pasosSolicitudList = []
        def criteria = PasoSolicitudEntidadFinanciera.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("numeroDePaso", "asc")
        }
        pager.totalRows = results.totalCount
        results.each{
            def pasosSolicitud = [:]
            pasosSolicitud.id = it.id
            pasosSolicitud.titulo = it.titulo
            pasosSolicitud.numeroDePaso = it.numeroDePaso
            pasosSolicitud.ponderacion = it.ponderacion
            pasosSolicitud.modoDeDespliegue = it.modoDeDespliegue
            pasosSolicitud.ultimoPaso = it.ultimoPaso
            pasosSolicitud.tipoDePaso = it.tipoDePaso
            pasosSolicitudList << pasosSolicitud
        }
        return pasosSolicitudList
    }
    def getPasosCotizador(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 5
        def pasosCotizadorList = []
        def criteria = PasoCotizadorEntidadFinanciera.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("numeroDePaso", "asc")
        }
        pager.totalRows = results.totalCount
        results.each{
            def pasosCotizador = [:]
            pasosCotizador.id = it.id
            pasosCotizador.tituloDelPaso = it.tituloDelPaso
            pasosCotizador.numeroDePaso = it.numeroDePaso
            pasosCotizador.idListaAjax = it.idListaAjax
            pasosCotizador.variableValorSeleccionado = it.variableValorSeleccionado
            pasosCotizadorList << pasosCotizador
        }
        return pasosCotizadorList
    }
    
    def getProductos(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 5
        def productosLista = []
        def criteria = Producto.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("tituloEnCotizador", "asc")
        }
        pager.totalRows = results.totalCount
        results.each{
            def productos = [:]
            productos.id = it.id
            productos.claveDeProducto = it.claveDeProducto
            productos.nombreDelProducto = it.nombreDelProducto
            productos.marca = it.marca.nombre
            productos.descripcion = it.descripcion
            productosLista << productos
        }
        return productosLista
    }
    def getSolicitudes(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = 25
        def sucursal = springSecurityService.currentUser.sucursal
        def id = springSecurityService.currentUser.id
        def usuario = Usuario.get(id as long)
        def solicitudesLista = []
        def criteria = ProductoSolicitud.createCriteria()
        def results 
        if(usuario.authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_DIRECTOR" ||  it.authority == "ROLE_MERCADOTECNIA" ||  it.authority == "ROLE_CENTRO_DE_CONTACTO"}){
            results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                solicitud{
                    eq("entidadFinanciera", entidadFinanciera)
                    order("fechaDeSolicitud", "desc")
                }
            }
        }
        else if(usuario.authorities.any { it.authority == "ROLE_EJECUTIVO" }){
            results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                solicitud{
                    eq("entidadFinanciera", entidadFinanciera)
                    eq("sucursal",sucursal)
                    eq("registradaPor",usuario)
                    order("fechaDeSolicitud", "desc")

                }
            }
        }
        else if(usuario.authorities.any { it.authority == "ROLE_SUCURSAL" || it.authority == "ROLE_CAJERO"}){
            results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                solicitud{
                    eq("entidadFinanciera", entidadFinanciera)
                    eq("sucursal",sucursal)
                    order("fechaDeSolicitud", "desc")

                }
            }
        }
        pager.totalRows = (results?.totalCount ?  results?.totalCount : 0)
        results.each{
            def solicitudes = [:]
            solicitudes.id = it.solicitud.id
            solicitudes.folio = it.solicitud.folio
            solicitudes.nombreCliente = it.solicitud.cliente.toString()
            solicitudes.statusDeSolicitud = it.solicitud.statusDeSolicitud.toString()
            solicitudes.puntoDeVenta = it.solicitud.puntoDeVenta?.toString()
            solicitudes.autenticadoMediante = "Plataforma"
            solicitudes.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
            solicitudes.fechaDeSolicitud = it.solicitud.fechaDeSolicitud
            solicitudes.montoCredito = it.montoDelCredito
            solicitudes.solicitud = it.solicitud
            solicitudesLista << solicitudes
        }
        return solicitudesLista
    }
    def buscar (EntidadFinanciera entidadFinanciera, Pager pager,params,requestJSON){
        def nombre
        def folio
        def apellidoPaterno
        def apellidoMaterno
        def rfc
        def id = springSecurityService.currentUser.id
        def usuario = Usuario.get(id as long)
        if(!params){
            nombre = requestJSON.nombre
            folio  = requestJSON.folio
            apellidoPaterno = requestJSON.apellidoPaterno
            apellidoMaterno = requestJSON.apellidoMaterno
            rfc = requestJSON.rfc

        }else if(params){
            nombre = params.nombre
            folio  = params.folio
            apellidoPaterno = params.apellidoPaterno
            apellidoMaterno = params.apellidoMaterno
            rfc = params.rfc
        }
        def solicitudesLista = []
        def sucursal = springSecurityService.currentUser.sucursal
        pager.rowsPerPage = 25
        def criteria = ProductoSolicitud.createCriteria()
        def results
        if(usuario.authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_DIRECTOR" ||  it.authority == "ROLE_MERCADOTECNIA" ||  it.authority == "ROLE_CENTRO_DE_CONTACTO" }){
            results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                solicitud{
                    eq("entidadFinanciera", entidadFinanciera)
                    ilike("folio","%"+folio+"%")
                    order("fechaDeSolicitud", "desc")
                    cliente{
                        ilike("nombre","%"+nombre+"%")
                        ilike("rfc","%"+rfc+"%")
                        ilike("apellidoPaterno","%"+apellidoPaterno+"%")
                        ilike("apellidoMaterno","%"+apellidoMaterno+"%")
                    }
                }
            }
                    
        }
        else if(usuario.authorities.any { it.authority == "ROLE_EJECUTIVO" }){
            
            results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                solicitud{
                    eq("entidadFinanciera", entidadFinanciera)
                    eq("sucursal",sucursal)
                    eq("registradaPor",usuario)

                    ilike("folio","%"+folio+"%")
                    order("fechaDeSolicitud", "desc")

                    cliente{
                        ilike("nombre","%"+nombre+"%")
                        ilike("rfc","%"+rfc+"%")
                        ilike("apellidoPaterno","%"+apellidoPaterno+"%")
                        ilike("apellidoMaterno","%"+apellidoMaterno+"%")
                    }
                }
            }
                    
        }
              else if(usuario.authorities.any { it.authority == "ROLE_SUCURSAL" || it.authority == "ROLE_CAJERO"}){

            results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                solicitud{
                    eq("entidadFinanciera", entidadFinanciera)
                    eq("sucursal",sucursal)
                    ilike("folio","%"+folio+"%")
                    order("fechaDeSolicitud", "desc")

                    cliente{
                        ilike("nombre","%"+nombre+"%")
                        ilike("rfc","%"+rfc+"%")
                        ilike("apellidoPaterno","%"+apellidoPaterno+"%")
                        ilike("apellidoMaterno","%"+apellidoMaterno+"%")
                    }
                }
            }
                    
        }

       
        
   
        pager.totalRows = (results?.totalCount ?  results?.totalCount : 0)
        results.each {
            def solicitudes = [:]
            solicitudes.id = it.solicitud.id
            solicitudes.folio = it.solicitud.folio
            solicitudes.nombreCliente = it.solicitud.cliente.toString()
            solicitudes.statusDeSolicitud = it.solicitud.statusDeSolicitud.toString()
            solicitudes.puntoDeVenta = it.solicitud.puntoDeVenta?.toString()
            solicitudes.autenticadoMediante = "Plataforma"
            solicitudes.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
            solicitudes.fechaDeSolicitud = it.solicitud.fechaDeSolicitud
            solicitudes.montoCredito = it.montoDelCredito
            solicitudes.solicitud = it.solicitud
            solicitudesLista << solicitudes
        }          
        
        return solicitudesLista
        
    }

    def listaDeSolicitudesTemporales() {
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def roles = springSecurityService.principal?.authorities*.authority
        if( !roles.contains('ROLE_EJECUTIVO') && !roles.contains('ROLE_SUCURSAL')) {
            def query = "SELECT s FROM SolicitudTemporal s WHERE s.entidadFinanciera.id = " + usuario.entidadFinanciera.id + " order by s.fechaDeSolicitud"
            def resultados = SolicitudTemporal.executeQuery(query)
            resultados?.each {
                def solicitud = [:]
                solicitud.id = it.id
                solicitud.folio = (it.folio ?: "-")
                solicitud.nombreCliente = it.nombreDelCliente
                solicitud.statusDeSolicitud = "LLENADO DE SOLICITUD"
                solicitud.puntoDeVenta = ""
                solicitud.autenticadoMediante = "Plataforma"
                solicitud.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
                solicitud.fechaDeSolicitud = it.fechaDeSolicitud
                solicitud.montoCredito = it.montoDelCredito
                solicitud.temporal = true
                respuesta << solicitud
            }
        }
        return respuesta
    }

    def obtenerSolicitudesPorStatus(def entidadFinanciera, def pager, def opcion, def temporalidad, def fechaInicio, def fechaFinal){
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def roles = springSecurityService.principal?.authorities*.authority
        if(temporalidad){
            switch(temporalidad){
            case 1:
                def fechaFormateada = (new Date()).format('dd/MM/yyyy')
                fechaInicio = (new Date()).format('dd/MM/yyyy')
                fechaFinal = (new Date()).format('dd/MM/yyyy')
                break;
            case 7:
                def rango = getFechasSemanaActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 31:
                def rango = getFechasMesActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 365:
                def anio = Calendar.getInstance().get(Calendar.YEAR)
                fechaInicio = "01/01/" + anio
                fechaFinal = "31/12/" + anio
                break;
            default:
                println "Entrando a DEFAULT"
                break;
            }
        }
        def query = "SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.entidadFinanciera.id = " + usuario.entidadFinanciera.id + " AND ps.solicitud.statusDeSolicitud.id "
        if(opcion == "dictaminadas") {
            query += "IN (5,7) "
        } else if (opcion == "noDictaminadas") {
            query += "NOT IN (5,6,7) "
        } else if (opcion == "complementoSolicitado") {
            query += "IN (6) " 
        }
        if( roles.contains('ROLE_EJECUTIVO') || roles.contains('ROLE_SUCURSAL')){
            query += " And ps.solicitud.sucursal.id = " + usuario.sucursal.id + " "
            if (roles.contains('ROLE_EJECUTIVO')) {
                query += " And ps.solicitud.registradaPor.id = " + usuario.id + " "
            }  else {
                query += " And ps.solicitud.registradaPor IS NOT NULL "
            }
        }
        query += "AND ps.solicitud.fechaDeSolicitud BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"
        //println "Query: " + query
        def solicitudesLista = []
        pager.rowsPerPage = 25
        def resultados = ProductoSolicitud.executeQuery(query)
        resultados?.each {
            def solicitud = [:]
            solicitud.id = it.solicitud.id
            solicitud.folio = it.solicitud.folio
            solicitud.nombreCliente = it.solicitud.cliente.toString()
            solicitud.statusDeSolicitud = it.solicitud.statusDeSolicitud.toString()
            solicitud.puntoDeVenta = it.solicitud.puntoDeVenta?.toString()
            solicitud.autenticadoMediante = "Plataforma"
            solicitud.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
            solicitud.fechaDeSolicitud = it.solicitud.fechaDeSolicitud
            solicitud.montoCredito = it.montoDelCredito
            respuesta << solicitud
        }
        return respuesta
    }

    def getSolicitudesBusqueda (EntidadFinanciera entidadFinanciera, Pager pager,def opcion, def temporalidad, def fechaInicio, def fechaFinal){
        def id = springSecurityService.currentUser.id
        def usuario = Usuario.get(id as long)
        if(temporalidad as long){
            switch(temporalidad as long){
            case 1:
                def fechaFormateada = (new Date()).format('dd/MM/yyyy')
                fechaInicio = (new Date()).format('dd/MM/yyyy')
                fechaFinal = (new Date()).format('dd/MM/yyyy')
                break;
            case 7:
                def rango = getFechasSemanaActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 31:
                def rango = getFechasMesActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 365:
                def anio = Calendar.getInstance().get(Calendar.YEAR)
                fechaInicio = "01/01/" + anio
                fechaFinal = "31/12/" + anio
                break;
            default:
                println "Entrando a DEFAULT"
                
                break;
            }
        }
        
        Date from =new Date().parse("dd/MM/yyyy HH:mm:ss", fechaInicio+" 00:00:00") 
        Date toDate =new Date().parse("dd/MM/yyyy HH:mm:ss", fechaFinal+" 23:59:59")
        pager.rowsPerPage = 25
        def solicitudesLista = []
        def sucursal = springSecurityService.currentUser.sucursal
        
        def criteria
        def results
        if (opcion == "noDictaminadas"){
            def ids = [5 as long, 6 as long, 7 as long]
            criteria = ProductoSolicitud.createCriteria()
            if(usuario.authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_DIRECTOR"  ||  it.authority == "ROLE_MERCADOTECNIA" ||  it.authority == "ROLE_CENTRO_DE_CONTACTO"}){

                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                            not {'in'("id",ids)}
                        }
                    }
                } 
            } 
            
            else if (usuario.authorities.any { it.authority == "ROLE_EJECUTIVO" }){
                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        eq("sucursal",sucursal)
                        eq("registradaPor",usuario)

                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                            not {'in'("id",ids)}
                        }
                    }
                } 
                
            }
             else if (usuario.authorities.any { it.authority == "ROLE_SUCURSAL" || it.authority == "ROLE_CAJERO"}){
                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        eq("sucursal",sucursal)
                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                            not {'in'("id",ids)}
                        }
                    }
                } 
                
            }
            pager.totalRows = (results?.totalCount ?  results?.totalCount : 0)
        }
        else if (opcion == "dictaminadas"){
            
            def ids2 = [5 as long,7 as long]
            criteria = ProductoSolicitud.createCriteria()
            if(usuario.authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_DIRECTOR" ||  it.authority == "ROLE_MERCADOTECNIA" ||  it.authority == "ROLE_CENTRO_DE_CONTACTO"}){

                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                    'in'("id",ids2) 
                        }
                    }
                }
            }
            else if(usuario.authorities.any { it.authority == "ROLE_EJECUTIVO" }){
                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        eq("sucursal",sucursal)
                        eq("registradaPor",usuario)

                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                    'in'("id",ids2) 
                        }
                    }
                }
            }
            else if(usuario.authorities.any { it.authority == "ROLE_SUCURSAL " || it.authority == "ROLE_CAJERO" }){
                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        eq("sucursal",sucursal)
                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                    'in'("id",ids2) 
                        }
                    }
                }
            }
            pager.totalRows = (results?.totalCount ?  results?.totalCount : 0)
            
        } else if (opcion == "complementoSolicitado"){
            def ids3 = [6 as long]
            criteria = ProductoSolicitud.createCriteria()
            if(usuario.authorities.any { it.authority == "ROLE_ADMIN" || it.authority == "ROLE_DIRECTOR" ||  it.authority == "ROLE_MERCADOTECNIA" ||  it.authority == "ROLE_CENTRO_DE_CONTACTO" }){

                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                    'in'("id",ids3) 
                        }
                    }
                }
            }
            else if(usuario.authorities.any { it.authority == "ROLE_EJECUTIVO" }){
                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        eq("sucursal",sucursal)
                        eq("registradaPor",usuario)

                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                    'in'("id",ids3) 
                        }
                    }
                }
            }
              else if(usuario.authorities.any { it.authority == "ROLE_SUCURSAL" || it.authority == "ROLE_CAJERO" }){
                results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
                    solicitud{
                        eq("entidadFinanciera", entidadFinanciera)
                        eq("sucursal",sucursal)
                        and{
                            between("fechaDeSolicitud", from,toDate)
                        }
                        order("fechaDeSolicitud", "desc")
                        statusDeSolicitud{
                    'in'("id",ids3) 
                        }
                    }
                }
            }
            pager.totalRows = (results?.totalCount ?  results?.totalCount : 0)
        }
        
        results.each{
            def solicitudes = [:]
            solicitudes.id = it.solicitud.id
            solicitudes.folio = it.solicitud.folio
            solicitudes.nombreCliente = it.solicitud.cliente.toString()
            solicitudes.statusDeSolicitud = it.solicitud.statusDeSolicitud.toString()
            solicitudes.puntoDeVenta = it.solicitud.puntoDeVenta?.toString()
            solicitudes.autenticadoMediante = "Plataforma"
            solicitudes.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
            solicitudes.fechaDeSolicitud = it.solicitud.fechaDeSolicitud
            solicitudes.montoCredito = it.montoDelCredito
            solicitudes.solicitud = it.solicitud
            solicitudesLista << solicitudes
        }
        return solicitudesLista
    }
    
    
    def obtenerSolicitudesPorVerificar(){
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def solicitudes = SolicitudDeCredito.findAllWhere(statusDeSolicitud: StatusDeSolicitud.get(8), entidadFinanciera: usuario.entidadFinanciera)
        solicitudes.each {
            def solicitud = [:]
            solicitud.id = it.id
            solicitud.folio = it.folio
            solicitud.cliente = it.cliente.toString()
            solicitud.direccion = (DireccionCliente.findWhere(cliente: it.cliente))?.toString()
            solicitud.coordenadas = geocoderService.obtenerCoordenadas([:],  solicitud.direccion)
            respuesta << solicitud
        }
        respuesta
    }

    def obtenerDatosSolicitud(def idSolicitud){
        def respuesta = [:]
        def solicitud = SolicitudDeCredito.get(idSolicitud as long)
        respuesta.productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud)
        respuesta.direccion = DireccionCliente.findWhere(cliente: respuesta.productoSolicitud.solicitud.cliente)
        respuesta.datosMapa = []
        def datos = [:]
        datos.id = solicitud.id
        datos.folio = solicitud.folio
        datos.cliente = solicitud.cliente.toString()
        datos.direccion = (respuesta.direccion)?.toString()
        datos.coordenadas = geocoderService.obtenerCoordenadas([:],  datos.direccion)
        respuesta.datosMapa << datos
        respuesta
    }

    def obtenerCantidadDeSolicitudesPendientes(){
        def respuesta = 0
        def usuario = springSecurityService.currentUser
        def roles = springSecurityService.principal?.authorities*.authority
        def query = "SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.entidadFinanciera.id = " + usuario.entidadFinanciera.id + " AND ps.solicitud.statusDeSolicitud.id NOT IN (5,7)"
        if( roles.contains('ROLE_EJECUTIVO') || roles.contains('ROLE_SUCURSAL')){
            query += " And ps.solicitud.sucursal.id = " + usuario.sucursal.id
            if (roles.contains('ROLE_EJECUTIVO')) {
                query += " And ps.solicitud.registradaPor.id = " + usuario.id
            } else {
                query += " And ps.solicitud.registradaPor IS NOT NULL "
            }
        }
        def resultados = ProductoSolicitud.executeQuery(query)
        respuesta = resultados.size()
        return respuesta
    }

    def obtenerDatosDeLaSolicitud(def id){
        def respuesta = [:]
        def solicitud = SolicitudDeCredito.get(id as long)
        if(solicitud){
            respuesta.cliente = solicitud.cliente
            respuesta.edadCliente = getAniosTranscurridos(respuesta.cliente.fechaDeNacimiento, new Date())
            respuesta.solicitud = solicitud
            respuesta.referenciasCliente = ReferenciaPersonalCliente.findAllWhere(cliente: respuesta.cliente)
            respuesta.direccionCliente = DireccionCliente.findWhere(cliente: respuesta.cliente, vigente: true)
            respuesta.empleoCliente = EmpleoCliente.findWhere(cliente: respuesta.cliente, vigente: true)
            respuesta.telefonosCliente = TelefonoCliente.findAllWhere(cliente: respuesta.cliente, vigente: true)
            respuesta.emailCliente = EmailCliente.findAllWhere(cliente: respuesta.cliente, vigente: true)
            respuesta.documentosSolicitud = DocumentoSolicitud.findAllWhere(solicitud: respuesta.solicitud)
            respuesta.productoSolicitud = ProductoSolicitud.findWhere(solicitud: respuesta.solicitud)
            respuesta.verificacion = VerificacionSolicitud.findWhere(solicitud: respuesta.solicitud)
            respuesta.resultadoMotor = ResultadoMotorDeDecision.findWhere(solicitud: respuesta.solicitud)
            respuesta.preguntas = PreguntaVerificacionSolicitud.findAllWhere(solicitud: respuesta.solicitud)
            respuesta.fotosFachada = FotografiaSolicitud.findAll("from FotografiaSolicitud fs where fs.solicitud.id = :idSolicitud And fs.tipoDeFotografia.referencia like 'fachada%'", [idSolicitud: solicitud.id])
            respuesta.fotosInterior = FotografiaSolicitud.findAll("from FotografiaSolicitud fs where fs.solicitud.id = :idSolicitud And fs.tipoDeFotografia.referencia like 'interior%'", [idSolicitud: solicitud.id])
        }
        return respuesta
    }
    
    def obtenerDatosDeLaSolicitudTemporal(def id){
        def respuesta = [:]
        def solicitud = SolicitudTemporal.get(id as long)
        if(solicitud){
            respuesta.cliente = solicitud.nombreDelCliente
            respuesta.telefonosCliente = [[tipoDeTelefono: [nombre: "TELÉFONO CELULAR"], numeroTelefonico: solicitud.telefonoCliente]]
            respuesta.emailCliente = solicitud.emailCliente
            respuesta.productoSolicitud = solicitud
            respuesta.solicitud = solicitud
            respuesta.temporal = true
        }
        return respuesta
    }
    
    def getFechasMesActual() {
        def rango = [:];
        Calendar calendar = getCalendar(new Date());
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH));
        rango.fechaInicio = calendar.getTime();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        rango.fechaFinal = calendar.getTime();
        //println rango
        return rango;
    }

    def getFechasSemanaActual() {
        def rango = [:];
        Calendar calendar = getCalendar(new Date());
        calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
        rango.fechaInicio = calendar.getTime();
        calendar.add(Calendar.WEEK_OF_YEAR, 1);
        calendar.add(Calendar.DATE, -1);
        rango.fechaFinal = calendar.getTime();
        //println rango
        return rango;
    }

    def getAniosTranscurridos(Date fechaInicial, Date fechaFinal) {
        Calendar a = getCalendar(fechaInicial);
        Calendar b = getCalendar(fechaFinal);
        int diff = b.get(Calendar.YEAR) - a.get(Calendar.YEAR);
        if (a.get(Calendar.MONTH) > b.get(Calendar.MONTH) || (a.get(Calendar.MONTH) == b.get(Calendar.MONTH) && a.get(Calendar.DATE) > b.get(Calendar.DATE))) {
            diff--;
        }
        return diff;
    }

    private static Calendar getCalendar(Date fecha) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(fecha);
        return calendar;
    }

    def subirImagen(def tipoDeImagen, def origen, def solicitudId, def listaDeArchivos){
        def respuesta = [:]
        def pasoOrigen = origen as int;
        def usuario = springSecurityService.currentUser
        listaDeArchivos.each { archivo ->
            def nombreEmpresa = usuario.entidadFinanciera.nombre
            nombreEmpresa = nombreEmpresa.replaceAll("[^a-zA-Z0-9]+","")
            if(pasoOrigen == 4){
                def fotografia = new FotografiaSolicitud()
                def tipoDeFotografia = TipoDeFotografia.findByReferencia(tipoDeImagen)
                def solicitud = SolicitudDeCredito.get(solicitudId as long)
                def ruta = "/var/uploads/kosmos/fotos/" + nombreEmpresa + "/" + solicitud.folio
                def subdir = new File(ruta)
                if (!subdir.exists()){
                    subdir.mkdir()
                }
                fotografia.solicitud = solicitud
                fotografia.fechaDeSubida = new Date()
                fotografia.rutaDelArchivo = ruta
                fotografia.nombreDelArchivo = archivo.nombreDelArchivo
                fotografia.tipoDeFotografia = tipoDeFotografia
                fotografia.usuario = usuario
                if(fotografia.save(flush:true)){
                    File file = new File((ruta + "/" + archivo.nombreDelArchivo))
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idFotografia = fotografia.id
                    respuesta.tipoDeFotografia = tipoDeImagen
                    respuesta.descripcion = tipoDeFotografia.nombre
                    respuesta.exito = true
                    respuesta.mensaje = "La fotografía se ha subido exitosamente."
                } else {
                    if (fotografia.hasErrors()) {
                        fotografia.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir el logotipo."
                }
            } else if (pasoOrigen==11) {
                def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: usuario.entidadFinanciera)
                def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa +"/imagenesCotizador"
             
                if(configuracion){
                    println ""
                    configuracion.imagenDefault = ruta + "/" + archivo.nombreDelArchivo
                } else {
                    configuracion = new ConfiguracionEntidadFinanciera()
                    configuracion.entidadFinanciera = usuario.entidadFinanciera
                    configuracion.nombreComercial = usuario.entidadFinanciera.nombre
                    configuracion.slogan = "Coloque su slogan aquí"
                    configuracion.imagenDefault = ruta + "/" + archivo.nombreDelArchivo
                }
                if(configuracion.save(flush:true)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (configuracion.imagenDefault)
                    File file = new File(configuracion.imagenDefault)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idConfig = configuracion.id
                    respuesta.exito = true
                    respuesta.mensaje = "El logo se ha subido exitosamente."
                } else {
                    if (configuracion.hasErrors()) {
                        configuracion.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir el logotipo."
                }
            }
            else if (pasoOrigen==10) {
                def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: usuario.entidadFinanciera)
                def ruta  = grailsApplication.mainContext.getResource("/images/" + nombreEmpresa +"").file.toString()
                println ruta
                def configuracionRutaLogotipoUrl
                if(configuracion){
                    println "si hay config"
                    configuracion.rutaLogotipo =  nombreEmpresa +"/" + archivo.nombreDelArchivo
                    configuracionRutaLogotipoUrl = ruta + "/" + archivo.nombreDelArchivo
                } else {
                    configuracion = new ConfiguracionEntidadFinanciera()
                    configuracion.entidadFinanciera = usuario.entidadFinanciera
                    configuracion.nombreComercial = usuario.entidadFinanciera.nombre
                    configuracion.slogan = "Coloque su slogan aquí"
                    configuracion.imagenDefault = ruta + "/" + archivo.nombreDelArchivo
                }
                if(configuracion.save(flush:true)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (configuracionRutaLogotipoUrl)
                    File file = new File(configuracionRutaLogotipoUrl)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idConfig = configuracion.id
                    respuesta.exito = true
                    respuesta.mensaje = "El logo se ha subido exitosamente."
                } else {
                    if (configuracion.hasErrors()) {
                        configuracion.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir el logotipo."
                }
            }
            else if (pasoOrigen==5) {
                def rubroDeAplicacionDeCredito = RubroDeAplicacionDeCredito.get(solicitudId)
                def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa +"/imagenesCotizador"
             
                if(rubroDeAplicacionDeCredito){
                    println ""
                    rubroDeAplicacionDeCredito.urlImagen = ruta + "/" + archivo.nombreDelArchivo
                } 
                if(rubroDeAplicacionDeCredito.save(flush:true)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (rubroDeAplicacionDeCredito.urlImagen)
                    File file = new File(rubroDeAplicacionDeCredito.urlImagen)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idConfig = rubroDeAplicacionDeCredito.id
                    respuesta.exito = true
                    respuesta.mensaje = "La imagen se ha actualizado exitosamente."
                } else {
                    if (rubroDeAplicacionDeCredito.hasErrors()) {
                        rubroDeAplicacionDeCredito.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir la imagen."
                }
            }else if (pasoOrigen==6) {
                def producto = Producto.get(solicitudId)
                def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa +"/imagenesCotizador"
             
                if(producto){
                    println ""
                    producto.rutaImagenDefault = ruta + "/" + archivo.nombreDelArchivo
                } 
                if(producto.save(flush:true)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (producto.rutaImagenDefault)
                    File file = new File(producto.rutaImagenDefault)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idConfig = producto.id
                    respuesta.exito = true
                    respuesta.mensaje = "La imagen se ha actualizado exitosamente."
                } else {
                    if (producto.hasErrors()) {
                        producto.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir la imagen."
                }
            }
            else if (pasoOrigen==12) {
                def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: usuario.entidadFinanciera)
                def ruta  = grailsApplication.mainContext.getResource("/css/" + nombreEmpresa +"").file.toString()
                def configuracionRutaCssUrl
                if(configuracion){
                    configuracion.rutaCss = "http://localhost:8080/css/" + nombreEmpresa +"/" + archivo.nombreDelArchivo
                    configuracionRutaCssUrl = ruta + "/" + archivo.nombreDelArchivo
                } else {
                    configuracion = new ConfiguracionEntidadFinanciera()
                    configuracion.entidadFinanciera = usuario.entidadFinanciera
                    configuracion.nombreComercial = usuario.entidadFinanciera.nombre
                    configuracion.slogan = "Coloque su slogan aquí"
                    configuracion.imagenDefault = ruta + "/" + archivo.nombreDelArchivo
                }
                if(configuracion.save(flush:true)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (configuracionRutaCssUrl)
                    File file = new File(configuracionRutaCssUrl)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idConfig = configuracion.id
                    respuesta.exito = true
                    respuesta.mensaje = "El logo se ha subido exitosamente."
                } else {
                    if (configuracion.hasErrors()) {
                        configuracion.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir el logotipo."
                }
            } else {
                def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: usuario.entidadFinanciera)
                def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa
                if(configuracion){
                    println ""
                    configuracion.rutaLogotipo = ruta + "/" + archivo.nombreDelArchivo
                } else {
                    configuracion = new ConfiguracionEntidadFinanciera()
                    configuracion.entidadFinanciera = usuario.entidadFinanciera
                    configuracion.nombreComercial = usuario.entidadFinanciera.nombre
                    configuracion.slogan = "Coloque su slogan aquí"
                    configuracion.rutaLogotipo = ruta + "/" + archivo.nombreDelArchivo
                }
                if(configuracion.save(flush:true)){
                    def subdir = new File(ruta)
                    subdir.mkdir()
                    println (configuracion.rutaLogotipo)
                    File file = new File(configuracion.rutaLogotipo)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                    respuesta.idConfig = configuracion.id
                    respuesta.exito = true
                    respuesta.mensaje = "El logo se ha subido exitosamente."
                } else {
                    if (configuracion.hasErrors()) {
                        configuracion.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un error al subir el logotipo."
                }
            }
        }
        return respuesta
    }

    def guardarPasoCotizadorEntidadFinanciera (params){
        println "numero de paso"+params.numeroDePaso
        def respuesta = [:]
        def pasoCotizadorEntidadFinanciera = new PasoCotizadorEntidadFinanciera ()
        pasoCotizadorEntidadFinanciera.tituloDelPaso = params.tituloDelPaso
        pasoCotizadorEntidadFinanciera.tituloResumen = params.tituloResumen
        pasoCotizadorEntidadFinanciera.numeroDePaso = params.numeroDePaso as long
        pasoCotizadorEntidadFinanciera.tieneAyuda  = params.tieneAyuda
        pasoCotizadorEntidadFinanciera.textoAyuda = params.textoAyuda
        pasoCotizadorEntidadFinanciera.cargaInicial = params.cargaInicial
        pasoCotizadorEntidadFinanciera.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera

        def tipoDePasoCotizador = TipoDePasoCotizador.get(params.tipoDePasoId)
        if (tipoDePasoCotizador.nombre == 'stepProducto'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepPlazos'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'periodicidadList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'pagoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepModelo'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepColor'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepEnganche'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'engancheBar'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'engancheElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepSeguro'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepMontoCredito'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'montoCreditoBar'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'montoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepDocumentos'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'documentosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'documentoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepDecision'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'atrasoOption'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'tieneAtraso'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepRegistro'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'registroCliente'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'datosRegistro'
            
        }
        
        pasoCotizadorEntidadFinanciera.tipoDePaso = tipoDePasoCotizador
        if(pasoCotizadorEntidadFinanciera.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "Paso Guardado Correctamente."
            respuesta.pasoCotizadorEntidadFinanciera = pasoCotizadorEntidadFinanciera
            
        }
        else {
            if (pasoCotizadorEntidadFinanciera.hasErrors()) {
                pasoCotizadorEntidadFinanciera.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al asignar pasoCotizadorEntidadFinanciera. Intente nuevamente más tarde."
        }
        respuesta
        
    }
    
    def guardarPasoSolicitudEntidadFinanciera (params){
        println params
        def respuesta = [:]
        def pasoSolicitudEntidadFinanciera = new PasoSolicitudEntidadFinanciera ()
        pasoSolicitudEntidadFinanciera.titulo = params.titulo
        pasoSolicitudEntidadFinanciera.subtitulo = params.subtitulo
        pasoSolicitudEntidadFinanciera.numeroDePaso = params.numeroDePaso as long
        pasoSolicitudEntidadFinanciera.ponderacion  = params.ponderacion as long
        pasoSolicitudEntidadFinanciera.modoDeDespliegue = params.modoDeDespliegue
        pasoSolicitudEntidadFinanciera.mostrarEnBarra = params.mostrarEnBarra.toBoolean()
        pasoSolicitudEntidadFinanciera.requiereSubirComprobante = params.requiereSubirComprobante.toBoolean()
        pasoSolicitudEntidadFinanciera.requiereSubirIdentificacion = params.requiereSubirIdentificacion.toBoolean()
        pasoSolicitudEntidadFinanciera.ultimoPaso = params.ultimoPaso
        pasoSolicitudEntidadFinanciera.mostrarEnBarra = params.mostrarEnBarra
        pasoSolicitudEntidadFinanciera.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        def tipoDePasoSolicitud = TipoDePasoSolicitud.get(params.tipoDePasoId)
        pasoSolicitudEntidadFinanciera.tipoDePaso = tipoDePasoSolicitud
        
        if(pasoSolicitudEntidadFinanciera.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "Paso Guardado Correctamente."
            respuesta.pasoSolicitudEntidadFinanciera = pasoSolicitudEntidadFinanciera
            respuesta.pasoSolicitudEntidadFinancieraTipoDePaso = pasoSolicitudEntidadFinanciera.tipoDePaso

        }
        else {
            if (pasoSolicitudEntidadFinanciera.hasErrors()) {
                pasoSolicitudEntidadFinanciera.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al asignar pasoSolicitudEntidadFinanciera. Intente nuevamente más tarde."
        }
        respuesta
        
    }


    def guardarUsuario(params, EntidadFinanciera entidadFinanciera){
        def respuesta = [:]
        def id = (params.id).toBigInteger()
        def paramsRol = params.roles
        def roles = null

        //Los roles se convierten explicitamente en una lista cuando contiene un solo elemento con un valor de dos digitos de lo contrario se toma cada digito como un elemento de la lista
        if(!(paramsRol.class.isArray())){
            def valueList = []
            roles = valueList + paramsRol
        }

        if(id == 0) {
            def usuario = new Usuario();
            usuario.username = params.username
            usuario.password = params.password
            usuario.enabled = Boolean.TRUE
            usuario.accountExpired = Boolean.FALSE
            usuario.accountLocked = Boolean.FALSE
            usuario.passwordExpired = Boolean.TRUE
            usuario.entidadFinanciera = entidadFinanciera
            usuario.sucursal = SucursalEntidadFinanciera.get(params.sucursal.id)
            usuario.numeroDeEmpleado = params.numeroDeEmpleado
            usuario.nombre = params.nombre
            usuario.apellidoPaterno = params.apellidoPaterno
            usuario.apellidoMaterno = params.apellidoMaterno
            usuario.email = params.email
            usuario.fechaPassword = Calendar.getInstance().getTime()

            if(!usuario.save()){
                if (usuario.hasErrors()) {
                    usuario.errors.allErrors.each {
                        log.error(it)
                    }
                }
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema al registrar al usuario. Intente nuevamente más tarde."
                return respuesta
            }

            roles.each {
                Rol rol = Rol.get(it)
                UsuarioRol.create(usuario, rol)
            }
        } else {
            Usuario usuario = Usuario.get(params.id)
            if(usuario == null){
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema. La operación es inválida"
                return respuesta
            } else if (usuario.entidadFinanciera.id != entidadFinanciera.id){
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema. No tiene permisos para modificar la información del usuario"
                return respuesta
            }

            usuario.nombre = params.nombre
            usuario.apellidoPaterno = params.apellidoPaterno
            usuario.apellidoMaterno = params.apellidoMaterno
            usuario.username = params.username
            usuario.email = params.email
            usuario.accountLocked = params.accountLocked.toBoolean()
            usuario.enabled = params.enabled.toBoolean()
            usuario.sucursal = SucursalEntidadFinanciera.get(params.sucursal.id)
            usuario.numeroDeEmpleado = params.numeroDeEmpleado

            boolean resetPassword = params.resetPassword.toBoolean()
            String password = params.newPassword
            def deletedRoles = []
            def exists
            usuario.authorities.each {
                def authority = it
                exists = Boolean.FALSE
                roles.each {
                    def newRol = (it).toBigInteger()
                    if (BigInteger.valueOf(authority.id).compareTo(newRol) == 0) {
                        exists = Boolean.TRUE
                    }
                }

                if(!exists) {
                    deletedRoles << authority
                }
            }

            def valueAddedRole = []
            roles.each {
                def newRol = (it).toBigInteger()
                exists = Boolean.FALSE
                usuario.authorities.each {
                    if (BigInteger.valueOf(it.id).compareTo(newRol) == 0) {
                        exists = Boolean.TRUE
                    }
                }

                if(!exists){
                    valueAddedRole << newRol
                }
            }

            //Add changes to log
            Usuario currentUser = springSecurityService.currentUser
            deletedRoles.each {
                userService.addUserInformationLog(currentUser, usuario, BitacoraMovimientos.ELIMINAR_PERFIL, it.authority)
            }

            valueAddedRole.each {
                Rol rol = Rol.get(it)
                userService.addUserInformationLog(currentUser, usuario, BitacoraMovimientos.AGREGAR_PERFIL, rol.authority)
            }

            //Remove all user roles
            deletedRoles.each {
                Rol rol = Rol.get(it.id)
                UsuarioRol.remove(usuario, rol)
            }

            //Add new roles
            valueAddedRole.each {
                Rol rol = Rol.get(it)
                UsuarioRol.create(usuario, rol)
            }

            if(resetPassword) {
                //Saving current password
                UsuarioPasswords userPassword = new UsuarioPasswords(usuario, Calendar.getInstance().getTime())

                //Updating password
                usuario.password = password
                usuario.fechaPassword = Calendar.getInstance().getTime()
                usuario.addToUserPasswords(userPassword)
                usuario.passwordExpired = Boolean.TRUE

                //Add changes to log
                userService.addUserInformationLog(currentUser, usuario, BitacoraMovimientos.CAMBIAR_PASSWORD, null)
            }

            if(!usuario.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema al guardar los datos del usuario. Intente nuevamente más tarde."
                return respuesta
            }
        }

        return respuesta
    }

    def guardarTipoDeDocumento(params){
        def respuesta = [:]
        def tipoDeDocumento = new TipoDeDocumento();
        tipoDeDocumento.nombre = params.nombre
        tipoDeDocumento.nombreMapeo = params.nombreMapeo
        tipoDeDocumento.formatosPermitidos = params.formatosPermitidos
        tipoDeDocumento.usoEnCotizador = true
        tipoDeDocumento.usoEnSolicitud = true
        tipoDeDocumento.activo = true
        tipoDeDocumento.nombreEnPlural = params.nombreEnPlural
        tipoDeDocumento.cantidadSolicitada = params.cantidadSolicitada as long
        tipoDeDocumento.codigo = params.codigo as long
        def tipodeIngresos=TipoDeIngresos.get(params.tipoDeIngresos.id as long)
        tipoDeDocumento.tipoDeIngresos = tipodeIngresos
        println params    
        if(tipoDeDocumento.save()){
            respuesta.tipoDeDocumento
        }
        else {
            if (tipoDeDocumento.hasErrors()) {
                tipoDeDocumento.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeDocumento. Intente nuevamente más tarde."
        }
        respuesta
                
            

    }
    
    def guardarTipoDeAsentamiento(params){
        def respuesta = [:]
        def tipoDeAsentamiento = new TipoDeAsentamiento();
        tipoDeAsentamiento.nombre = params.nombre
        println params    
        if(tipoDeAsentamiento.save()){
            respuesta.tipoDeAsentamiento
        }
        else {
            if (tipoDeAsentamiento.hasErrors()) {
                tipoDeAsentamiento.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeAsentamiento. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    def guardarTipoDeCampo(params){
        def respuesta = [:]
        def tipoDeCampo = new TipoDeCampo();
        tipoDeCampo.elementoDeEntrada = params.elementoDeEntrada
        tipoDeCampo.expresionRegular = params.expresionRegular
        tipoDeCampo.requiereValidacion = false

        println params    
        if(tipoDeCampo.save()){
            respuesta.tipoDeCampo
        }
        else {
            if (tipoDeCampo.hasErrors()) {
                tipoDeCampo.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeCampo. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    def guardarTipoDeContrato(params){
        def respuesta = [:]
        def tipoDeContrato = new TipoDeContrato();
        tipoDeContrato.nombre = params.nombre
        println params    
        if(tipoDeContrato.save()){
            respuesta.tipoDeContrato
        }
        else {
            if (tipoDeContrato.hasErrors()) {
                tipoDeContrato.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeContrato. Intente nuevamente más tarde."
        }
        respuesta
    }
    def guardarTipoDeGarantia(params){
        def respuesta = [:]
        def tipoDeGarantia = new TipoDeGarantia();
        tipoDeGarantia.nombre = params.nombre
        tipoDeGarantia.activo = true

        println params    
        if(tipoDeGarantia.save()){
            respuesta.tipoDeGarantia
        }
        else {
            if (tipoDeGarantia.hasErrors()) {
                tipoDeGarantia.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeGarantia. Intente nuevamente más tarde."
        }
        respuesta
    }
    def guardarTipoDeIngresos(params){
        def respuesta = [:]
        def tipoDeIngresos = new TipoDeIngresos();
        tipoDeIngresos.nombre = params.nombre
        tipoDeIngresos.activo = true
        println params    
        if(tipoDeIngresos.save()){
            respuesta.tipoDeIngresos
        }
        else {
            if (tipoDeIngresos.hasErrors()) {
                tipoDeIngresos.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeIngresos. Intente nuevamente más tarde."
        }
        respuesta
    }
    def guardarTipoDeFotografia(params){
        def respuesta = [:]
        def tipoDeFotografia = new TipoDeFotografia();
        tipoDeFotografia.nombre = params.nombre
        tipoDeFotografia.referencia = params.referencia
        tipoDeFotografia.activa = true

        println params    
        if(tipoDeFotografia.save()){
            respuesta.tipoDeFotografia
        }
        else {
            if (tipoDeFotografia.hasErrors()) {
                tipoDeFotografia.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeFotografia. Intente nuevamente más tarde."
        }
        respuesta
    }
    def guardarTipoDeVivienda(params){
        def respuesta = [:]
        def tipoDeVivienda = new TipoDeVivienda();
        tipoDeVivienda.nombre = params.nombre
        tipoDeVivienda.clave = params.clave

        println params    
        if(tipoDeVivienda.save()){
            respuesta.tipoDeVivienda
        }
        else {
            if (tipoDeVivienda.hasErrors()) {
                tipoDeVivienda.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeAsentamiento. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    def guardarSeguroSobreDeuda(params){
        def respuesta = [:]
        if((params.montoInicial) && (params.montoFinal) && (params.plazoAnual) &&(params.importeSeguro)){
            def seguroSobreDeuda = new SeguroSobreDeuda();
            seguroSobreDeuda.montoInicial = params.montoInicial as float
            seguroSobreDeuda.montoFinal = params.montoFinal as float
            seguroSobreDeuda.plazoAnual = params.plazoAnual as long
            seguroSobreDeuda.importeSeguro = params.importeSeguro as float
            seguroSobreDeuda.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera

            println params    
            if(seguroSobreDeuda.save()){
                respuesta.seguroSobreDeuda
                respuesta.ok = true
                respuesta.mensaje = "El Seguro sobre Deuda ha sido Registrado Correctamente"
            }
            else {
                if (seguroSobreDeuda.hasErrors()) {
                    seguroSobreDeuda.errors.allErrors.each {
                        println it
                    }
                }
                respuesta.error = true
                respuesta.mensaje = "Ocurrio un problema al guardar el Seguro Sobre Deuda. Intente nuevamente más tarde."
            }
        }
        else{
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el Seguro Sobre Deuda. Faltan Campos por llenar."
        }
        println respuesta
        respuesta
    }
    
    def guardarServicioDeAsistencia(params){
        def respuesta = [:]
        if((params.montoInicial) && (params.montoFinal) && (params.plazoAnual) &&(params.plazoQuincenal) && (params.plazoSemanal) && (params.importeAsistencia)){
            def servicioDeAsistencia = new ServicioDeAsistencia();
            servicioDeAsistencia.montoInicial = params.montoInicial as float
            servicioDeAsistencia.montoFinal = params.montoFinal as float
            servicioDeAsistencia.plazoAnual = params.plazoAnual as long
            servicioDeAsistencia.plazoQuincenal = params.plazoQuincenal as long
            servicioDeAsistencia.plasoSemanal = params.plazoSemanal as long
            servicioDeAsistencia.importeAsistencia = params.importeAsistencia as float
            servicioDeAsistencia.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera

            println params    
            if(servicioDeAsistencia.save()){
                respuesta.servicioDeAsistencia
                respuesta.ok = true
                respuesta.mensaje = "El Servicio de Asistencia ha sido Registrado Correctamente"
            }
            else {
                if (servicioDeAsistencia.hasErrors()) {
                    servicioDeAsistencia.errors.allErrors.each {
                        println it
                    }
                }
                respuesta.error = true
                respuesta.mensaje = "Ocurrio un problema al guardar el Servicio de Asistencia. Intente nuevamente más tarde."
            }
        }
        else{
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el Servicio de Asistencia. Faltan Campos por llenar."
        }
        println respuesta
        respuesta
    }
    
    
    def guardarTipoDeTasaDeInteres(params){
        def respuesta = [:]
        def tipoDeTasaDeInteres = new TipoDeTasaDeInteres();
        tipoDeTasaDeInteres.nombre = params.nombre

        println params    
        if(tipoDeTasaDeInteres.save()){
            respuesta.tipoDeTasaDeInteres
        }
        else {
            if (tipoDeTasaDeInteres.hasErrors()) {
                tipoDeTasaDeInteres.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeTasaDeInteres. Intente nuevamente más tarde."
        }
        respuesta
    }
    def updateTipoDeVivienda(params){
        def respuesta = [:]
        def tipoDeVivienda= TipoDeVivienda.get(params.tipoDeVivienda.id as long);
        tipoDeVivienda.nombre = params.nombre
        tipoDeVivienda.clave = params.clave
        println params    
        if(tipoDeVivienda.save()){
            respuesta.tipoDeVivienda
        }
        else {
            if (tipoDeVivienda.hasErrors()) {
                tipoDeVivienda.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeVivienda. Intente nuevamente más tarde."
        }
        respuesta
    }
    

    
    
    def updateTipoDeCampo(params){
        def respuesta = [:]
        def tipoDeCampo= TipoDeCampo.get(params.tipoDeCampoId as long);
        tipoDeCampo.elementoDeEntrada = params.elementoDeEntrada
        tipoDeCampo.expresionRegular = params.expresionRegular
        tipoDeCampo.requiereValidacion = false

        println params    
        if(tipoDeCampo.save()){
            respuesta.tipoDeCampo
        }
        else {
            if (tipoDeCampo.hasErrors()) {
                tipoDeCampo.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeCampo. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    def updateTipoDeContrato(params){
        def respuesta = [:]
        def tipoDeContrato= TipoDeContrato.get(params.tipoDeContratoId as long);
        tipoDeContrato.nombre = params.nombre
        println params    
        if(tipoDeContrato.save()){
            respuesta.tipoDeContrato
        }
        else {
            if (tipoDeContrato.hasErrors()) {
                tipoDeContrato.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeContrato. Intente nuevamente más tarde."
        }
        respuesta
    }
    def updateTipoDeIngresos(params){
        def respuesta = [:]
        def tipoDeIngresos= TipoDeIngresos.get(params.tipoDeIngresosId as long);
        tipoDeIngresos.nombre = params.nombre
        tipoDeIngresos.activo = true

        println params    
        if(tipoDeIngresos.save()){
            respuesta.tipoDeIngresos
        }
        else {
            if (tipoDeIngresos.hasErrors()) {
                tipoDeIngresos.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeIngresos. Intente nuevamente más tarde."
        }
        respuesta
    }

    def updateTipoDeGarantia(params){
        def respuesta = [:]
        def tipoDeGarantia= TipoDeGarantia.get(params.tipoDeGarantiaId as long);
        tipoDeGarantia.nombre = params.nombre
        tipoDeGarantia.activo = true

        println params    
        if(tipoDeGarantia.save()){
            respuesta.tipoDeGarantia
        }
        else {
            if (tipoDeGarantia.hasErrors()) {
                tipoDeGarantia.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeGarantia. Intente nuevamente más tarde."
        }
        respuesta
    }
    def updateTipoDeFotografia(params){
        def respuesta = [:]
        def tipoDeFotografia = TipoDeFotografia.get(params.tipoDeFotografiaId as long);
        tipoDeFotografia.nombre = params.nombre
        tipoDeFotografia.referencia = params.referencia
        tipoDeFotografia.activa = true

        println params    
        if(tipoDeFotografia.save()){
            respuesta.tipoDeFotografia
        }
        else {
            if (tipoDeFotografia.hasErrors()) {
                tipoDeFotografia.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeCampo. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    
    
    def updateTipoDeTasaDeInteres(params){
        def respuesta = [:]
        def tipoDeTasaDeInteres= TipoDeTasaDeInteres.get(params.tipoDeTasaDeInteres.id as long);
        tipoDeTasaDeInteres.nombre = params.nombre
        println params    
        if(tipoDeTasaDeInteres.save()){
            respuesta.tipoDeTasaDeInteres
        }
        else {
            if (tipoDeTasaDeInteres.hasErrors()) {
                tipoDeTasaDeInteres.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeTasaDeInteres. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    
    def updateTipoDeAsentamiento(params){
        def respuesta = [:]
        def tipoDeAsentamiento = TipoDeAsentamiento.get(params.tipoDeAsentamiento.id as long);
        tipoDeAsentamiento.nombre = params.nombre
        println params    
        if(tipoDeAsentamiento.save()){
            respuesta.tipoDeAsentamiento
        }
        else {
            if (tipoDeAsentamiento.hasErrors()) {
                tipoDeAsentamiento.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el tipoDeAsentamiento. Intente nuevamente más tarde."
        }
        respuesta
    }
    
    def guardarProducto(params){
        def respuesta = [:]
        def producto = new Producto();
        producto.nombreDelProducto = params.nombreDelProducto
        producto.rutaImagenDefault = params.rutaImagenDefault
        producto.descripcion = params.descripcion
        producto.tituloEnCotizador = params.tituloEnCotizador
        producto.claveDeProducto = params.claveDeProducto
        producto.claseIconoPaso = params.claseIconoPaso
        producto.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        producto.activo = true
        producto.cat = params.cat as double
        producto.montoMaximo = params.montoMaximo as long
        producto.montoMinimo = params.montoMinimo as long
        producto.tasaDeInteres = params.tasaDeInteres as double

        def marca=Marca.get(params.marca.id as long)
        producto.marca = marca          
        
        def tipoDeProducto=TipoDeProducto.get(params.tipoDeProducto.id as long)
        producto.tipoDeProducto = tipoDeProducto
        
        //filtrar por la lista enviada
        def tipoDeTasa=TipoDeTasaDeInteres.get(1)
        producto.tipoDeTasa = tipoDeTasa
        
        //filtrar por la lista enviada
        def esquema=Esquema.get(1)
        producto.esquema = esquema
        
        if(producto.save()){
            respuesta.producto
        }
        else {
            if (producto.hasErrors()) {
                producto.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el producto. Intente nuevamente más tarde."
        }
        respuesta
            

    }
    def registrarVerificacion(def params){
        def exito = false
        def x = 0;
        if(params.solicitudId){
            def verificacion = new VerificacionSolicitud()
            verificacion.solicitud = SolicitudDeCredito.get(params.solicitudId as long)
            verificacion.fechaDeVerificacion = new Date()
            verificacion.usuarioVerificador = springSecurityService.currentUser
            verificacion.observaciones = params.observaciones
            verificacion.resultadoPaso1 = ((params.resultadoVerificacionDireccion == "Si") ? true : false)
            verificacion.resultadoPaso2 = ((!params.cotejoIdentificacionResultado || params.cotejoIdentificacionResultado == "") ? true : false)
            verificacion.resultadoPaso3 = ((!params.cotejoComprobanteResultado || params.cotejoComprobanteResultado == "") ? true : false)
            verificacion.resultadoPaso4 = true
            if(verificacion.save(flush:true)){
                verificacion.solicitud.statusDeSolicitud = StatusDeSolicitud.get(4)
                if(verificacion.solicitud.save(flush:true)){
                    def preguntas = params.findAll{it.key.startsWith("pregunta")}
                    preguntas.each{ key, value ->
                        def preguntaSolicitud = PreguntaVerificacionSolicitud.get((key.minus('pregunta')) as long)
                        preguntaSolicitud.respuesta =value
                        if(preguntaSolicitud.save(flush: true)){
                            x++;
                        }
                    }
                    if(preguntas?.size() == x){
                        exito = true
                    }
                }
            }
        }
        return exito
    }

    def obtenerEstadisticasPorGrafica(def grafica, def temporalidad, def fechaInicio, def fechaFinal) {
        def respuesta = [:]
        def usuario = springSecurityService.currentUser
        def query
        if(temporalidad){
            switch(temporalidad){
            case 1:
                def fechaFormateada = (new Date()).format('dd/MM/yyyy')
                fechaInicio = (new Date()).format('dd/MM/yyyy')
                fechaFinal = (new Date()).format('dd/MM/yyyy')
                break;
            case 7:
                def rango = getFechasSemanaActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 31:
                def rango = getFechasMesActual()
                fechaInicio = rango.fechaInicio.format('dd/MM/yyyy')
                fechaFinal = rango.fechaFinal.format('dd/MM/yyyy')
                break;
            case 365:
                def anio = Calendar.getInstance().get(Calendar.YEAR)
                fechaInicio = "01/01/" + anio
                fechaFinal = "31/12/" + anio
                break;
            default:
                println "Entrando a DEFAULT"
                break;
            }
        }
        if(grafica == "general"){
            if(temporalidad == 1 || temporalidad == 7) {
                query = "SELECT * FROM solicitudes_por_dia WHERE fecha BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"
            } else if (temporalidad == 31 || temporalidad == 365){
                query = "SELECT * FROM solicitudes_por_mes WHERE fecha BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"
            } else {
                query = "SELECT DATE_TRUNC('month',fecha) AS fecha, SUM(solicitudes) AS solicitudes FROM solicitudes_por_dia WHERE fecha BETWEEN TO_TIMESTAMP('" + fechaInicio+ " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi') group by 1 order by 1"
            }
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.periodos = [:]
            respuesta.periodos.categories = []
            respuesta.periodos.crosshair = true

            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Solicitudes"
            datosEstadisticas.type = "column"
            datosEstadisticas.yAxis = 0
            datosEstadisticas.data = []
            datosEstadisticas.tooltip = [:]
            datosEstadisticas.tooltip.valuePrefix = ""

            resultados.each {
                respuesta.periodos.categories << ( (temporalidad == 1 || temporalidad == 7) ? it.fecha.format("dd/MM/yyyy") : (it.fecha.format("MMMM")))
                datosEstadisticas.data << it.solicitudes
            }
            respuesta.estadisticas << datosEstadisticas
        } else if (grafica == "status") {
            query = "SELECT status, sum(solicitudes) as solicitudes from solicitudes_por_estado_por_dia WHERE fecha BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi') GROUP BY 1 HAVING sum(solicitudes) > 0 ORDER BY 2 DESC"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Solicitudes por Estatus"
            datosEstadisticas.data = []

            resultados.each {
                datosEstadisticas.data << [it.status ,it.solicitudes]
            }
            respuesta.estadisticas << datosEstadisticas
        } else if (grafica == "productos") {
            query = "SELECT producto, sum(cantidad) as cantidad from producto_por_dia WHERE fecha BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi') GROUP BY 1 HAVING sum(cantidad) > 0 ORDER BY 2 DESC"
            def sql = new Sql(dataSource)
            def resultados = sql.rows(query)
            respuesta.estadisticas = []
            def datosEstadisticas = [:]
            datosEstadisticas.name = "Productos"
            datosEstadisticas.colorByPoint = true
            datosEstadisticas.data = []

            resultados.each {
                datosEstadisticas.data << [name: (it.producto + " ($it.cantidad)"), y: it.cantidad]
            }
            respuesta.estadisticas << datosEstadisticas
        }
        return respuesta
    }
    
    def getRoles(){
        Long id = (Long) Rol.ROLE_ADMIN
        def criteria = Rol.createCriteria()
        def results = criteria.list {
            ne ('id', id)
            order("authority", "asc")
        }
    }
    
    def getSucursalesByEntidadFinanciera(EntidadFinanciera entidad){
        def criteria = SucursalEntidadFinanciera.createCriteria()
        def results = criteria.list {
            eq ('entidadFinanciera', entidad)
            order("nombre", "asc")
        }
    }
}