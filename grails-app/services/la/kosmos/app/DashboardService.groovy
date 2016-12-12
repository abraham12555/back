package la.kosmos.app

import grails.transaction.Transactional

@Transactional
class DashboardService {

    def springSecurityService
    
    def listaGeneralDeSolicitudes() {
        def respuesta = []
        def usuario = springSecurityService.currentUser
        def query = "SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.entidadFinanciera.id = " + usuario.entidadFinanciera.id
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
        } else if ("noDictaminadas") {
            query += "NOT IN (5,7) " 
        }
        query += "AND ps.solicitud.fechaDeSolicitud BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"
        println "Query: " + query
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
            respuesta.empleoCliente = EmpleoCliente.findWhere(cliente: respuesta.cliente)
            respuesta.telefonosCliente = TelefonoCliente.findAllWhere(cliente: respuesta.cliente, vigente: true)
            respuesta.emailCliente = EmailCliente.findAllWhere(cliente: respuesta.cliente, vigente: true)
            respuesta.documentosSolicitud = DocumentoSolicitud.findAllWhere(solicitud: respuesta.solicitud)
            respuesta.productoSolicitud = ProductoSolicitud.findWhere(solicitud: respuesta.solicitud)
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
        println rango
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
        println rango
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
    
    def subirImagen(tipoDeImagen, listaDeArchivos){
        def respuesta = [:]
        def usuario = springSecurityService.currentUser
        listaDeArchivos.each { archivo ->
            def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: usuario.entidadFinanciera)
            def nombreEmpresa = usuario.entidadFinanciera.nombre
            def ruta = "/var/uploads/kosmos/config/" + nombreEmpresa 
            nombreEmpresa = nombreEmpresa.replaceAll("[^a-zA-Z0-9]+","")
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
        return respuesta
    }
    
    def guardarUsuario(params){
        def respuesta = [:]
        def usuario = new Usuario();
        usuario.username = params.username
        usuario.password = params.password
        usuario.enabled = true
        usuario.accountExpired = false
        usuario.accountLocked = false
        usuario.passwordExpired = false
        usuario.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera
        usuario.nombre = params.nombre
        usuario.apellidoPaterno = params.apellidoPaterno
        usuario.apellidoMaterno = params.apellidoMaterno
        usuario.email = params.email
        if(usuario.save()){
            def rol = Rol.get(params.rol.id as long)
            def rolUsuario = new UsuarioRol()
            rolUsuario.usuario = usuario
            rolUsuario.rol = rol
            if(rolUsuario.save()){
                respuesta.usuario = usuario
            } else {
                if (rolUsuario.hasErrors()) {
                    rolUsuario.errors.allErrors.each {
                        println it
                    }
                }
                respuesta.error = true
                respuesta.mensaje = "Ocurrio un problema al asignar el rol al usuario. Intente nuevamente más tarde."
            }
        } else {
            if (usuario.hasErrors()) {
                usuario.errors.allErrors.each {
                    println it
                }
            }
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un problema al guardar el usuario. Intente nuevamente más tarde."
        }
        respuesta
    }
}
