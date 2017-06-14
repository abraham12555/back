package la.kosmos.app

import grails.transaction.Transactional
import groovy.sql.Sql
import java.util.Calendar

@Transactional
class DashboardService {

    def springSecurityService
    def geocoderService
    def dataSource
    def userService

    def listaGeneralDeSolicitudes() {
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def query = "SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.entidadFinanciera.id = " + usuario.entidadFinanciera.id + " order by ps.solicitud.fechaDeSolicitud"
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

    def listaDeSolicitudesTemporales() {
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def query = "SELECT s FROM SolicitudTemporal s WHERE s.entidadFinanciera.id = " + usuario.entidadFinanciera.id + " order by s.fechaDeSolicitud"
        def resultados = SolicitudTemporal.executeQuery(query)
        resultados?.each {
            def solicitud = [:]
            solicitud.id = it.id
            solicitud.folio = "-"
            solicitud.nombreCliente = it.nombreDelCliente
            solicitud.statusDeSolicitud = "LLENADO DE SOLICITUD"
            solicitud.puntoDeVenta = ""
            solicitud.autenticadoMediante = "Plataforma"
            solicitud.producto = ((it.producto) ?: (it.colorModelo.modelo.producto.toString()))
            solicitud.fechaDeSolicitud = it.fechaDeSolicitud
            solicitud.montoCredito = it.montoDelCredito
            respuesta << solicitud
        }
        return respuesta
    }

    def obtenerSolicitudesPorStatus(def opcion, def temporalidad, def fechaInicio, def fechaFinal){
        def respuesta = []
        def usuario = springSecurityService.currentUser
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
        query += "AND ps.solicitud.fechaDeSolicitud BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"
        //println "Query: " + query
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
        def query = "SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.entidadFinanciera.id = " + usuario.entidadFinanciera.id + " AND ps.solicitud.statusDeSolicitud.id NOT IN (5,7)"
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

    def guardarUsuario(params, EntidadFinanciera entidadFinanciera){
        def respuesta = [:]
        def id = (params.id).toBigInteger()
        
        if(id == 0) {
            def usuario = new Usuario();
            usuario.username = params.username
            usuario.password = params.password
            usuario.enabled = Boolean.TRUE
            usuario.accountExpired = Boolean.FALSE
            usuario.accountLocked = Boolean.FALSE
            usuario.passwordExpired = Boolean.TRUE
            usuario.entidadFinanciera = entidadFinanciera
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
            
            params.roles.each {
                Rol rol = Rol.get(it)
                UsuarioRol.create(usuario, rol)
            }
        } else {
            Usuario usuario = Usuario.get(params.id)
            if(usuario == null){
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema. La operación es inválida"
                return respuesta
            } else if (usuario.entidadFinanciera != entidadFinanciera){
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
            
            def deletedRoles = []
            def exists
            usuario.authorities.each {
                def authority = it
                exists = Boolean.FALSE
                params.roles.each {
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
            params.roles.each {
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
            
            if(!usuario.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema al guardar los datos del usuario. Intente nuevamente más tarde."
                return respuesta
            }
        }
        
        return respuesta
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
}
