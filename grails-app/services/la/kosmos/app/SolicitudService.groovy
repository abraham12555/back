package la.kosmos.app

import grails.transaction.Transactional
import groovy.sql.Sql
import la.kosmos.rest.SolicitudRest

@Transactional
class SolicitudService {
    
    def dataSource

    def construirDatosTemporales(def datosPreguardados, def params, def pasoEnviado, def identificadores, entidadFinanciera) {
        println "Contenido del Mapa: " + datosPreguardados + " - Parametros Enviados:" + params +" - Paso Enviado: " +pasoEnviado + " - Identificadores: " + identificadores
	def cliente
        def clienteNuevo = false
        def datosPaso = [:]
        if(datosPreguardados){
            datosPaso = datosPreguardados
        }
        if(pasoEnviado.tipoDePaso.nombre == "pasoFormulario") {
            def camposFormulario = CampoFormulario.list();
            camposFormulario.each { campo ->
                println("asdfad fasdfasdfsa: " + (campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo) + "------" + params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo)])
                if(params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo)]){
                    if(datosPaso."$campo.claseAsociada.nombre"){
                        datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo)]
                    } else {
                        datosPaso."$campo.claseAsociada.nombre" = [:]
                        datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo)]
                    }
                } else if((campo.nombreDelCampo == "fechaDeNacimiento" || campo.nombreDelCampo == "fechaDeNacimientoDelConyugue") && (params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_dia" )] && params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_mes" )] && params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_anio" )] )) {
                    if(!datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"){
                        datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo" = [:]
                    }
                    datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"?."dia" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_dia" )]
                    datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"?."mes" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_mes")]
                    datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"?."anio" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_anio")]
                } else if((campo.nombreDelCampo == "tiempoDeVivir" || campo.nombreDelCampo == "tiempoDeResidencia" || campo.nombreDelCampo == "antiguedad") && (params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_mes")] || params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_anio")])){
                    if(!datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"){
                        datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo" = [:]
                        datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"?."mes" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_mes")]
                        datosPaso."$campo.claseAsociada.nombre"."$campo.nombreDelCampo"?."anio" = params[(campo?.claseAsociada?.nombre + "_" + campo?.nombreDelCampo + "_anio")]
                    }
                } else if((campo.nombreDelCampo == "tiempoEnVivienda") && ((params[(campo?.claseAsociada?.nombre + "_tiempo")]) && (params[(campo?.claseAsociada?.nombre + "_temporalidad")]))){
                    datosPaso."$campo.claseAsociada.nombre"."tiempo" = params[(campo?.claseAsociada?.nombre + "_tiempo")]
                    datosPaso."$campo.claseAsociada.nombre"."temporalidad" = params[(campo?.claseAsociada?.nombre + "_temporalidad")]
                }
            }

            println "Datos Recuperados: " + datosPaso
            if(datosPaso.cliente){
                if(identificadores?.idCliente){
                    cliente = Cliente.get(identificadores.idCliente as long)
                } else {
                    cliente = Cliente.findByCurp(datosPaso.cliente.curp)
                    if(!cliente) {
                        cliente = Cliente.findByRfc(datosPaso.cliente.rfc)
                        if(!cliente) {
                            cliente = new Cliente()
                            clienteNuevo = true
                        }
                    }
                }
                cliente.nombre = datosPaso.cliente.nombre
                cliente.apellidoPaterno = datosPaso.cliente.apellidoPaterno
                cliente.apellidoMaterno = datosPaso.cliente.apellidoMaterno
                cliente.lugarDeNacimiento = (datosPaso.cliente.lugarDeNacimiento ? Estado.get(datosPaso.cliente.lugarDeNacimiento) : null)
                if(datosPaso.cliente.nacionalidad){
                    cliente.nacionalidad = Nacionalidad.get(datosPaso.cliente.nacionalidad as long)
                } else if(datosPaso.cliente.lugarDeNacimiento){
                    if(datosPaso.cliente.lugarDeNacimiento > 32){
                        cliente.nacionalidad = Nacionalidad.get(2 as long)
                    } else {
                        cliente.nacionalidad = Nacionalidad.get(1 as long)
                    }
                }
                cliente.fechaDeNacimiento = ((datosPaso.cliente.fechaDeNacimiento.dia && datosPaso.cliente.fechaDeNacimiento.mes && datosPaso.cliente.fechaDeNacimiento.anio) ? (new Date().parse("dd/MM/yyyy", (datosPaso.cliente.fechaDeNacimiento.dia + "/" + datosPaso.cliente.fechaDeNacimiento.mes + "/" + datosPaso.cliente.fechaDeNacimiento.anio))) : null)
                cliente.curp = datosPaso.cliente.curp
                cliente.genero = (datosPaso.cliente.genero ? Genero.get(datosPaso.cliente.genero) : null)
                cliente.rfc = datosPaso.cliente.rfc
                cliente.estadoCivil = (datosPaso.cliente.estadoCivil ? EstadoCivil.get(datosPaso.cliente.estadoCivil as long) : null)
                cliente.nivelEducativo = (datosPaso.cliente.nivelEducativo ? NivelEducativo.get(datosPaso.cliente.nivelEducativo as long) : null)
                cliente.dependientesEconomicos = (datosPaso.cliente.dependientesEconomicos ? (datosPaso.cliente.dependientesEconomicos as int) : 0)
                cliente.nombreDelConyugue = datosPaso.cliente.nombreDelConyugue
                cliente.regimenMatrimonial = (datosPaso.cliente.regimenMatrimonial ? RegimenMatrimonial.get(datosPaso.cliente.regimenMatrimonial as long) : null)
                cliente.apellidoPaternoDelConyugue = datosPaso.cliente.apellidoPaternoDelConyugue
                cliente.apellidoMaternoDelConyugue = datosPaso.cliente.apellidoMaternoDelConyugue
                cliente.fechaDeNacimientoDelConyugue = ((datosPaso.cliente.fechaDeNacimientoDelConyugue?.dia && datosPaso.cliente.fechaDeNacimientoDelConyugue?.mes && datosPaso.cliente.fechaDeNacimientoDelConyugue?.anio) ? (new Date().parse("dd/MM/yyyy", (datosPaso.cliente.fechaDeNacimientoDelConyugue.dia + "/" + datosPaso.cliente.fechaDeNacimientoDelConyugue.mes + "/" + datosPaso.cliente.fechaDeNacimientoDelConyugue.anio))) : null)
                cliente.rfcDelConyugue = datosPaso.cliente.rfcDelConyugue
                cliente.curpDelConyugue = datosPaso.cliente.curpDelConyugue
                cliente.lugarDeNacimientoDelConyugue = (datosPaso.cliente.lugarDeNacimientoDelConyugue ? Estado.get(datosPaso.cliente.lugarDeNacimientoDelConyugue) : null)
                cliente.nacionalidadDelConyugue = (datosPaso.cliente.nacionalidadDelConyugue ? (Nacionalidad.get(datosPaso.cliente.nacionalidadDelConyugue as long)) : null)
                if(cliente.save(flush:true)){
                    datosPaso.cliente.clienteGuardado = true
                    datosPaso.cliente.idCliente = cliente.id
                    println ("El cliente ha sigo guardo correctamente con el id " + cliente.id)
                    if(clienteNuevo && datosPaso.telefonoCliente){
                        def telefonoCasa
                        def telefonoCelular
                        if(datosPaso.telefonoCliente.telefonoCasa){
                            telefonoCasa = new TelefonoCliente()
                            telefonoCasa.cliente = cliente
                            telefonoCasa.numeroTelefonico = datosPaso.telefonoCliente.telefonoCasa
                            telefonoCasa.vigente = true
                            telefonoCasa.tipoDeTelefono = TipoDeTelefono.get(1)
                            telefonoCasa.save(flush: true)
                        }
                        if(datosPaso.telefonoCliente.telefonoCelular){
                            telefonoCelular = new TelefonoCliente()
                            telefonoCelular.cliente = cliente
                            telefonoCelular.numeroTelefonico = datosPaso.telefonoCliente.telefonoCelular
                            telefonoCelular.vigente = true
                            telefonoCelular.tipoDeTelefono = TipoDeTelefono.get(2)
                            telefonoCelular.save(flush: true)
                        }
                    } else {
                        def telefonosCliente = TelefonoCliente.findAllWhere(cliente: cliente, vigente: true)
                        telefonosCliente?.each {
                            if(it.tipoDeTelefono.id == 1) {
                                it.numeroTelefonico = datosPaso.telefonoCliente.telefonoCasa
                                it.save(flush:true)
                            } else if(it.tipoDeTelefono.id == 2) {
                                it.numeroTelefonico = datosPaso.telefonoCliente.telefonoCelular
                                it.save(flush:true)
                            }
                        }
                    }
                    if(identificadores?.idSolicitud) {
                        println "El cliente " + cliente.id + " ya tiene asociada la solicitud " + identificadores?.idSolicitud
                    } else {
                        def sql = new Sql(dataSource)
                        def solicitudDeCredito = new SolicitudDeCredito()
                        solicitudDeCredito.fechaDeSolicitud = new Date()
                        solicitudDeCredito.statusDeSolicitud = StatusDeSolicitud.get(1)
                        solicitudDeCredito.entidadFinanciera = entidadFinanciera
                        solicitudDeCredito.folio = new Long(sql.firstRow("select nextval('folios_entidad_" + (solicitudDeCredito.entidadFinanciera.id) + "')").nextval)
                        solicitudDeCredito.cliente = cliente
                        if(solicitudDeCredito.save(flush: true)){
                            println "Si se guardo la solicitud: " + solicitudDeCredito?.id
                            datosPaso.cliente.idSolicitud = solicitudDeCredito.id
                        } else {
                            println ":( no se guardo la solicitud"
                            if (solicitudDeCredito.hasErrors()) {
                                solicitudDeCredito.errors.allErrors.each {
                                    println it
                                }
                            }
                        }
                    }
                } else {
                    if (cliente.hasErrors()) {
                        cliente.errors.allErrors.each {
                            println it
                        }
                    }
                }
            }
            if(datosPaso.direccionCliente) {
                if(identificadores?.idCliente) {
                    cliente = Cliente.get(identificadores.idCliente as long)
                    def direccionCliente
                    if(identificadores?.idDireccion){
                        direccionCliente = DireccionCliente.get(identificadores.idDireccion as long)
                    } else {
                        direccionCliente = new DireccionCliente()
                    }
                    direccionCliente.calle = datosPaso.direccionCliente.calle
                    direccionCliente.ciudad = datosPaso.direccionCliente.ciudad
                    direccionCliente.numeroExterior = datosPaso.direccionCliente.numeroExterior
                    direccionCliente.numeroInterior = datosPaso.direccionCliente.numeroInterior
                    direccionCliente.codigoPostal = (datosPaso.direccionCliente.codigoPostal ? CodigoPostal.findByCodigo(datosPaso.direccionCliente.codigoPostal) : null)
                    direccionCliente.colonia = datosPaso.direccionCliente.colonia
                    direccionCliente.ciudad  = (datosPaso.direccionCliente.municipio ? Municipio.get(datosPaso.direccionCliente.municipio) : null)
                    direccionCliente.tipoDeVivienda = (datosPaso.direccionCliente.tipoDeVivienda ? TipoDeVivienda.get(datosPaso.direccionCliente.tipoDeVivienda as long) : null)
                    direccionCliente.temporalidad = (datosPaso.direccionCliente.temporalidad ? Temporalidad.get(datosPaso.direccionCliente.temporalidad as long) : null)
                    direccionCliente.cliente = cliente
                    direccionCliente.tiempoDeResidencia = (datosPaso.direccionCliente.tiempo ? (datosPaso.direccionCliente.tiempo as int) : 0)
                    direccionCliente.latitud = 0
                    direccionCliente.longitud = 0
                    direccionCliente.tiempoDeEstadia = ((datosPaso.direccionCliente.tiempoDeResidencia?.mes && datosPaso.direccionCliente.tiempoDeResidencia?.anio) ? (datosPaso.direccionCliente.tiempoDeResidencia?.mes + "/" + datosPaso.direccionCliente.tiempoDeResidencia?.anio) : null)
                    direccionCliente.tiempoDeVivienda = ((datosPaso.direccionCliente.tiempoDeVivir?.mes && datosPaso.direccionCliente.tiempoDeVivir?.anio) ? (datosPaso.direccionCliente.tiempoDeVivir?.mes + "/" + datosPaso.direccionCliente.tiempoDeVivir?.anio) : null)
                    if(direccionCliente.save(flush:true)){
                        println("La dirección se ha registrado correctamente")
                        datosPaso.direccionCliente.idDireccion = direccionCliente.id
                    } else {
                        if (direccionCliente.hasErrors()) {
                            direccionCliente.errors.allErrors.each {
                                println it
                            }
                        }
                    }
                }
            }
            if (datosPaso.empleoCliente){
                if(identificadores?.idCliente) {
                    cliente = Cliente.get(identificadores.idCliente as long)
                    def empleoCliente
                    if(identificadores?.idEmpleo){
                        empleoCliente = EmpleoCliente.get(identificadores.idEmpleo as long)
                    } else {
                        empleoCliente = new EmpleoCliente()
                    }
                    empleoCliente.puesto = (datosPaso.empleoCliente.puesto ?: null)
                    empleoCliente.actividad = (datosPaso.empleoCliente.actividad ?: null)
                    empleoCliente.explicacionActividad = (datosPaso.empleoCliente.explicacionActividad ?: null)
                    empleoCliente.profesion = (datosPaso.empleoCliente.profesion ? Profesion.get(datosPaso.empleoCliente.profesion as long) : null)
                    empleoCliente.tipoDeContrato = (datosPaso.empleoCliente.contrato ? TipoDeContrato.get(datosPaso.empleoCliente.contrato as long) : null)
                    empleoCliente.giroEmpresarial = (datosPaso.giroEmpresarial ? GiroEmpresarial.get(datosPaso.giroEmpresarial) : null)
                    empleoCliente.nombreDeLaEmpresa = datosPaso.empleoCliente.empresa
                    empleoCliente.nombreDelJefeInmediato = (datosPaso.empleoCliente.jefeInmediato)
                    empleoCliente.antiguedad = (datosPaso.empleoCliente.periodo ? datosPaso.empleoCliente.periodo as int : 0)
                    empleoCliente.temporalidad = (datosPaso.empleoCliente.plazo ? Temporalidad.get(datosPaso.empleoCliente.plazo) : null)
                    empleoCliente.telefono = (datosPaso.empleoCliente.telefono ?: null)
                    empleoCliente.calle = (datosPaso.empleoCliente.calle ?: null)
                    empleoCliente.numeroExterior = (datosPaso.empleoCliente.noExterior ?: null)
                    empleoCliente.numeroInterior = (datosPaso.empleoCliente.noInterior ?: null)
                    empleoCliente.codigoPostal = (datosPaso.empleoCliente.codigoPostal ? CodigoPostal.findByCodigo(datosPaso.empleoCliente.codigoPostal) : null)
                    empleoCliente.colonia = (datosPaso.empleoCliente.colonia ?: null)
                    empleoCliente.ciudad = (datosPaso.empleoCliente.municipio ? Municipio.get(datosPaso.empleoCliente.municipio) : null)
                    empleoCliente.cliente = cliente
                    empleoCliente.ocupacion = ( datosPaso.empleoCliente.ocupacion ? Ocupacion.get(datosPaso.empleoCliente.ocupacion as long) : null)
                    empleoCliente.fechaIngreso = ((datosPaso.empleoCliente.antiguedad?.mes && datosPaso.empleoCliente.antiguedad?.anio) ? (datosPaso.empleoCliente.antiguedad?.mes + "/" + datosPaso.empleoCliente.antiguedad?.anio) : null)
                    empleoCliente.ingresosFijos = (datosPaso.empleoCliente.ingresosFIjos ? datosPaso.empleoCliente.ingresosFijos as float : 0)
                    empleoCliente.ingresosVariables = (datosPaso.empleoCliente.ingresosVariables ? datosPaso.empleoCliente.ingresosVariables as float : 0)
                    empleoCliente.gastos = (datosPaso.empleoCliente.gastos ? datosPaso.empleoCliente.gastos as int : 0)
                    if(empleoCliente.save(flush:true)){
                        println("El empleo se ha registrado correctamente")
                        datosPaso.empleoCliente.idEmpleo = empleoCliente.id
                    } else {
                        if (empleoCliente.hasErrors()) {
                            empleoCliente.errors.allErrors.each {
                                println it
                            }
                        }
                    }
                }
            
            } else if(datosPaso.referenciaPersonalCliente){
                def referencia1
                if(identificadores?.idReferencia1){
                    referencia1 = ReferenciaPersonalCliente.get(identificadores.idReferencia1 as long)
                } else {
                    referencia1 = new ReferenciaPersonalCliente()
                }
                def referencia2
                if(identificadores?.idReferencia2){
                    referencia2 = ReferenciaPersonalCliente.get(identificadores?.idReferencia2 as long)
                } else {
                    referencia2 = new ReferenciaPersonalCliente()
                }
                def referencia3
                if(identificadores?.idReferencia3){
                    referencia3 = ReferenciaPersonalCliente.get(identificadores.idReferencia3 as long)
                } else {
                    referencia3 = new ReferenciaPersonalCliente()
                }
                referencia1.nombreCompleto = datosPaso.referenciaPersonalCliente.referencia1NombreCompleto
                referencia1.emailPersonal = datosPaso.referenciaPersonalCliente.referencia1Email
                referencia1.telefonoCelular = datosPaso.referenciaPersonalCliente.referencia1Celular
                referencia1.telefonoParticular = datosPaso.referenciaPersonalCliente.referencia1Particular
                referencia1.tipoDeReferencia = TipoDeReferenciaPersonal.get(1)
                referencia1.cliente = cliente
                referencia2.nombreCompleto = datosPaso.referenciaPersonalCliente.referencia2NombreCompleto
                referencia2.emailPersonal = datosPaso.referenciaPersonalCliente.referencia2Email
                referencia2.telefonoCelular = datosPaso.referenciaPersonalCliente.referencia2Celular
                referencia2.telefonoParticular = datosPaso.referenciaPersonalCliente.referencia2Particular
                referencia2.tipoDeReferencia = TipoDeReferenciaPersonal.get(2)
                referencia2.cliente = cliente
                referencia3.nombreCompleto = datosPaso.referenciaPersonalCliente.referencia3NombreCompleto
                referencia3.emailPersonal = datosPaso.referenciaPersonalCliente.referencia3Email
                referencia3.telefonoCelular = datosPaso.referenciaPersonalCliente.referencia3Celular
                referencia3.telefonoParticular = datosPaso.referenciaPersonalCliente.referencia3Particular
                referencia3.tipoDeReferencia = TipoDeReferenciaPersonal.get(2)
                referencia3.cliente = cliente
                if(referencia1.save(flush:true)){
                    datosPaso.referenciaPersonalCliente.idReferencia1 = referencia1.id
                } else {
                    if (referencia1.hasErrors()) {
                        referencia1.errors.allErrors.each {
                            println it
                        }
                    }
                }
                if(referencia2.save(flush:true)){
                    datosPaso.referenciaPersonalCliente.idReferencia2 = referencia2.id
                } else {
                    if (referencia2.hasErrors()) {
                        referencia2.errors.allErrors.each {
                            println it
                        }
                    }
                }
                if(referencia3.save(flush:true)){
                    datosPaso.referenciaPersonalCliente.idReferencia3 = referencia3.id
                } else {
                    if (referencia3.hasErrors()) {
                        referencia3.errors.allErrors.each {
                            println it
                        }
                    }   
                }
            }
        } else if (pasoEnviado.tipoDePaso.nombre == "consultaBancaria"){
            datosPaso.consultaBancaria.depositoPromedio = (params.depositos ? params.depositos :null)
            datosPaso.consultaBancaria.retiroPromedio = (params.retiros ? params.retiros :null)
            datosPaso.consultaBancaria.saldoPromedio = (params.saldo ? params.saldo :null)
            datosPaso.consultaBancaria.login_id = params.login_id
            datosPaso.consultaBancaria.depositoCorrecto=(params.depositoCorrecto ? params.depositoCorrecto:null)
            datosPaso.consultaBancaria.retiroCorrecto=(params.retiroCorrecto ? params.retiroCorrecto:null)
            datosPaso.consultaBancaria.saldoCorrecto=(params.saldoCorrecto ? params.saldoCorrecto:null)
        } else if (pasoEnviado.tipoDePaso.nombre == "consultaBuro"){
            datosPaso.consultaBuro.tCredito=(params.tCredito ? params.tCredito:null)
            datosPaso.consultaBuro.creditoA=(params.creditoA ? params.creditoA:null)
            datosPaso.consultaBuro.creditoH=(params.creditoH ? params.creditoH:null)
        } else if (pasoEnviado.tipoDePaso.nombre == "resumen"){
            
        }
        return datosPaso
    }
    
    def controlDeDocumentos(listaDeDoctos, tipoDeDocumentoEnviado){
        if(!listaDeDoctos){
            listaDeDoctos = [:]
            listaDeDoctos.comprobanteDeDomicilio = false
            listaDeDoctos.identificacion = false
        }
        if(tipoDeDocumentoEnviado == "UtilityBill"){
            listaDeDoctos.comprobanteDeDomicilio = true
        } else if (tipoDeDocumentoEnviado == "Pasaportes" || tipoDeDocumentoEnviado == "Identicaciones"){
            listaDeDoctos.identificacion = true
        }
        return listaDeDoctos
    }
    
        
    def guardarDocumento(def archivo, def solicitudId, def tipoDeDocumento){
        def respuesta = [:]
        if(solicitudId){
            def solicitud = SolicitudDeCredito.get(solicitudId as long)
            def documento = new DocumentoSolicitud()
            documento.fechaDeSubida = new Date()
            documento.solicitud = solicitud
            if(tipoDeDocumento == "UtilityBill"){
                documento.tipoDeDocumento = TipoDeDocumento.get(1)
            } else if(tipoDeDocumento == "Identicaciones" || tipoDeDocumento == "Pasaportes"){
                documento.tipoDeDocumento = TipoDeDocumento.get(2)
            } 
            documento.rutaDelArchivo = "/var/uploads/kosmos/documentos/" + solicitud.entidadFinanciera.nombre + "/" + solicitud.folio + "/" + archivo.nombreDelArchivo + ".pdf"
            if(documento.save(flush:true)){
                def subdir = new File("/var/uploads/kosmos/documentos/" + solicitud.entidadFinanciera.nombre + "/" + solicitud.folio)
                subdir.mkdir()
                println (documento.rutaDelArchivo)
                /*File file = new File(documento.rutaDelArchivo)
                if (file.exists() || file.createNewFile()) {
                file.withOutputStream{fos->
                fos << archivo.archivo
                }
                }*/
                def fis = new FileInputStream("/tmp/BCC_Doc0.pdf")
                def fos = new FileOutputStream(documento.rutaDelArchivo)
                fos << fis
                fis.close()
                fos.close()
                respuesta.idArchivo = documento.id
                respuesta.exito = true
                respuesta.mensaje = "El archivo se ha registrado exitosamente."
            } else {
                respuesta.nombreArchivo = archivoJuicio.nombreArchivo
                respuesta.exito = false
                respuesta.mensaje = "Ocurrio un error al registrar el documento. Intentelo nuevamente."
            }
        }
        return respuesta 
    }
    
    def guardarDocumentoTemporal(def archivo, def tipoDeDocumento){
        def respuesta = null
        if(archivo){
            def documento = new DocumentoTemporal()
            documento.fechaDeSubida = new Date()
            if(tipoDeDocumento == "UtilityBill"){
                documento.tipoDeDocumento = TipoDeDocumento.get(1)
            } else if(tipoDeDocumento == "Identicaciones" || tipoDeDocumento == "Pasaportes"){
                documento.tipoDeDocumento = TipoDeDocumento.get(2)
            } 
            documento.rutaDelArchivo = "/var/uploads/kosmos/temporales" + "/" + archivo.nombreDelArchivo + ".pdf"
            if(documento.save(flush:true)){
                def subdir = new File("/var/uploads/kosmos/temporales/")
                subdir.mkdir()
                println (documento.rutaDelArchivo)
                /*File file = new File(documento.rutaDelArchivo)
                if (file.exists() || file.createNewFile()) {
                file.withOutputStream{fos->
                fos << archivo.archivo
                }
                }*/
                def fis = new FileInputStream("/tmp/BCC_Doc0.pdf")
                def fos = new FileOutputStream(documento.rutaDelArchivo)
                fos << fis
                fis.close()
                fos.close()
                respuesta = documento.id
            } else {
                respuesta = null
            }
        }
        return respuesta 
    }
    
    def registrarProducto (def datosCotizador, def identificadores){
        println "Datos del Cotizador: " + datosCotizador
        def respuesta = null
        if(datosCotizador && !identificadores.idProductoSolicitud){
            def solicitud = SolicitudDeCredito.get(identificadores.idSolicitud as long)
            def productoSolicitud =  new ProductoSolicitud()
            if(datosCotizador.rubro){
                productoSolicitud.rubroDeAplicacion = RubroDeAplicacionDeCredito.get(datosCotizador.rubro as long)
                productoSolicitud.producto = Producto.get(datosCotizador.producto as long)
                productoSolicitud.documentoElegido = TipoDeDocumento.get(datosCotizador.documento as long);
                productoSolicitud.montoDelCredito = datosCotizador.montoCredito as float
                productoSolicitud.montoDelSeguroDeDeuda = datosCotizador.montoSeguro as float
                productoSolicitud.montoDelPago = datosCotizador.pagos as float
                productoSolicitud.haTenidoAtrasos = datosCotizador.atrasos
                productoSolicitud.seguroFinanciado = true
            } else {
                productoSolicitud.montoDelPago = datosCotizador.pagos as float
                productoSolicitud.montoDelSeguroDeDeuda = 0
                productoSolicitud.colorModelo = ColorModelo.get(datosCotizador.color)
                productoSolicitud.seguroFinanciado = false
                productoSolicitud.montoDelCredito = (productoSolicitud.colorModelo.modelo.precio - productoSolicitud.enganche)
            }
            productoSolicitud.enganche = datosCotizador.enganche as float
            productoSolicitud.periodicidad = Periodicidad.get(datosCotizador.periodo as long)
            productoSolicitud.plazos = datosCotizador.plazo as int
            productoSolicitud.solicitud = solicitud
            if(productoSolicitud.save(flush:true)){
                println("El producto se ha registrado correctamente")
                return productoSolicitud.id
            } else {
                println("No se guardo nada")
                if (productoSolicitud.hasErrors()) {
                    productoSolicitud.errors.allErrors.each {
                        println it
                    }
                }
                return null
            }
        } else {
            return null
        }
    }
    
    def generarMapa(def mapa, def lista, def valor, def x){
        println ("Enviando-> mapa: " + mapa + ", lista: " + lista + ", valor: " + valor + ", x: "+ x)
        if((x+1 == lista.size())){
            return mapa[(lista[x])] = ((x+1 == lista.size()) ? valor : generarMapa(mapa[(lista[x])], lista, valor, (x+1)))
        }
    }
    
    def combine( Map... m ) {
        m.collectMany { it.entrySet() }.inject( [:] ) { result, e ->
            println ("-" + e.key + " - " + e.value + "------" +result[ e.key ])
            result << [ (e.key):e.value ]
        }
    }
    
    def consultaSolicitudes (def auth, def opcion, def tipoDeConsulta, def fechaInicio, def fechaFinal, def folio){
        def respuesta = []
        def query = "SELECT s FROM SolicitudDeCredito s WHERE s.entidadFinanciera.id = " + auth.entidadFinanciera.id
        if(tipoDeConsulta && (tipoDeConsulta as int) == 0) {
            query += " AND s.statusDeSolicitud.id IN (5,7)"
        } else if (tipoDeConsulta && (tipoDeConsulta as int) == 1) {
            query += " AND s.statusDeSolicitud.id NOT IN (5,7)" 
        }
        
        switch(opcion){
        case 1: //obtenerUltimasSolicitudes / index
            query += " AND s.solicitudEnviada = false"
            break
        case 2: //obtenerSolicitudesPorfecha / list
            query += " AND s.fechaDeSolicitud BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"     
            break
        case 3: //obtenerSolicitudPorFolio / show
            query += " AND s.folio = '" + folio + "' " 
            break
        default:
            break
        }        
        println "Query: " + query
        def resultados = SolicitudDeCredito.executeQuery(query)
        resultados.each { solicitud ->
            
            def datosSolicitud = [:]
            datosSolicitud.productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud)
            datosSolicitud.direccionCliente = DireccionCliente.findWhere(cliente: solicitud.cliente, vigente: true)
            datosSolicitud.empleoCliente = EmpleoCliente.findWhere(cliente: solicitud.cliente)
            datosSolicitud.telefonosCliente = TelefonoCliente.findAllWhere(cliente: solicitud.cliente, vigente: true)
            datosSolicitud.emailCliente = EmailCliente.findAllWhere(cliente: solicitud.cliente, vigente: true)
            datosSolicitud.documentosSolicitud = DocumentoSolicitud.findAllWhere(solicitud: solicitud)
            
            def datosBuroDeCredito = [:]
            datosBuroDeCredito.reporte = solicitud.reporteBuroCredito
            datosBuroDeCredito.consultas = ConsultasBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.creditos = CreditoClienteBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.direcciones = DireccionBuroDeCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.empleos = EmpleoBuroDeCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.refCred = RefCredBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.sintetiza = SintetizaBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.resumen = ResumenBuroCredito.findWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            
            def solicitudRest = new SolicitudRest()
            solicitudRest.solicitud = [:]
            solicitudRest.solicitud.datosSolicitud = [:]
            solicitudRest.solicitud.productoSeleccionado = [:]
            solicitudRest.solicitud.generales = [:]
            solicitudRest.solicitud.conyugue = [:]
            solicitudRest.solicitud.direccion = [:]
            solicitudRest.solicitud.vivienda = [:]
            solicitudRest.solicitud.empleo = [:]
            solicitudRest.solicitud.documentos = []
            solicitudRest.solicitud.buroDeCredito = [:]
        
            solicitudRest.solicitud.datosSolicitud.fechaDeCreacion = (solicitud.fechaDeSolicitud).format('dd/MM/yyyy HH:mm')
            solicitudRest.solicitud.datosSolicitud.usuarioQueRegistro = "Solicitante" //Temporal
            solicitudRest.solicitud.datosSolicitud.status = solicitud.statusDeSolicitud.nombre
            solicitudRest.solicitud.datosSolicitud.folio = ("" + solicitud.folio).padLeft(6, '0')
            solicitudRest.solicitud.datosSolicitud.puntoDeVenta = "Metepec" //Temporal
            solicitudRest.solicitud.datosSolicitud.puntajeScore = 543
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore = (tipoDeConsulta || tipoDeConsulta == 0 ? "Autorizado" : "")
            solicitudRest.solicitud.datosSolicitud.estadoDeDictaminacion = (tipoDeConsulta || tipoDeConsulta == 0 ?"Autorizado" : "")
            solicitudRest.solicitud.datosSolicitud.usuarioDictaminador = (tipoDeConsulta || tipoDeConsulta == 0 ? "Usuario Dictaminador" : "")
            solicitudRest.solicitud.datosSolicitud.fechaDeDictaminacion = (tipoDeConsulta || tipoDeConsulta == 0 ? (new Date()).format('dd/MM/yyyy HH:mm') : "")
        
            solicitudRest.solicitud.productoSeleccionado.producto = datosSolicitud.productoSolicitud?.producto?.claveDeProducto
            solicitudRest.solicitud.productoSeleccionado.modelo = ((datosSolicitud.productoSolicitud?.modelo) ? datosSolicitud.productoSolicitud?.modelo.nombre : "")
            solicitudRest.solicitud.productoSeleccionado.color = ((datosSolicitud.productoSolicitud?.colorModelo) ? datosSolicitud.productoSolicitud.colorModelo?.nombre : "")
            solicitudRest.solicitud.productoSeleccionado.enganche = datosSolicitud.productoSolicitud?.enganche
            solicitudRest.solicitud.productoSeleccionado.montoDelPago = datosSolicitud.productoSolicitud?.montoDelPago
            solicitudRest.solicitud.productoSeleccionado.periodicidad = datosSolicitud.productoSolicitud?.periodicidad?.nombre
            solicitudRest.solicitud.productoSeleccionado.plazo = datosSolicitud.productoSolicitud?.plazos
            solicitudRest.solicitud.productoSeleccionado.seguro = datosSolicitud.productoSolicitud?.montoDelSeguroDeDeuda
            solicitudRest.solicitud.productoSeleccionado.tasaDeInteres =  datosSolicitud.productoSolicitud?.producto?.tasaDeInteres
            solicitudRest.solicitud.productoSeleccionado.montoDelCredito = datosSolicitud.productoSolicitud?.montoDelCredito
        
            solicitudRest.solicitud.generales.nombre = solicitud.cliente.nombre
            solicitudRest.solicitud.generales.segundoNombre = ""
            solicitudRest.solicitud.generales.apellidoPaterno = solicitud.cliente.apellidoPaterno
            solicitudRest.solicitud.generales.apellidoMaterno = solicitud.cliente.apellidoMaterno
            solicitudRest.solicitud.generales.numeroCelular = "7221232323"
            solicitudRest.solicitud.generales.numeroFijo = "7221323232"
            solicitudRest.solicitud.generales.correoElectronico = "sumail@mail.com"
            solicitudRest.solicitud.generales.sexo = solicitud.cliente.genero.nombre
            solicitudRest.solicitud.generales.fechaDeNacimiento = (solicitud.cliente.fechaDeNacimiento).format('dd/MM/yyyy HH:mm')
            solicitudRest.solicitud.generales.lugarDeNacimiento = solicitud.cliente?.lugarDeNacimiento?.nombre
            solicitudRest.solicitud.generales.nacionalidad = solicitud.cliente.nacionalidad?.nombre
            solicitudRest.solicitud.generales.rfc = solicitud.cliente.rfc
            solicitudRest.solicitud.generales.curp = solicitud.cliente.curp
            solicitudRest.solicitud.generales.estadoCivil = solicitud.cliente.estadoCivil?.nombre
            solicitudRest.solicitud.generales.regimenDeBienes = ((solicitud.cliente.regimenMatrimonial) ? solicitud.cliente.regimenMatrimonial.nombre : "")
            solicitudRest.solicitud.generales.dependientesEconomicos = solicitud.cliente.dependientesEconomicos
            
            solicitudRest.solicitud.conyugue.nombre = ((solicitud.cliente.nombreDelConyugue) ? solicitud.cliente.nombreDelConyugue : "")
            solicitudRest.solicitud.conyugue.segundoNombre = ""
            solicitudRest.solicitud.conyugue.apellidoPaterno = ((solicitud.cliente.apellidoPaternoDelConyugue) ? solicitud.cliente.apellidoPaternoDelConyugue : "")
            solicitudRest.solicitud.conyugue.apellidoMaterno = ((solicitud.cliente.apellidoMaternoDelConyugue) ? solicitud.cliente.apellidoMaternoDelConyugue : "")
            solicitudRest.solicitud.conyugue.fechaDeNacimiento = ((solicitud.cliente.fechaDeNacimientoDelConyugue) ? (solicitud.cliente.fechaDeNacimientoDelConyugue).format('dd/MM/yyyy HH:mm') : "")
            solicitudRest.solicitud.conyugue.lugarDeNacimiento = ((solicitud.cliente.lugarDeNacimientoDelConyugue) ? solicitud.cliente.lugarDeNacimientoDelConyugue.nombre : "")
            solicitudRest.solicitud.conyugue.nacionalidad = ((solicitud.cliente.nacionalidadDelConyugue) ? solicitud.cliente.nacionalidadDelConyugue.nombre : "")
            solicitudRest.solicitud.conyugue.rfc = ((solicitud.cliente.rfcDelConyugue) ? solicitud.cliente.rfcDelConyugue : "")
            solicitudRest.solicitud.conyugue.curp = ((solicitud.cliente.curpDelConyugue) ? solicitud.cliente.curpDelConyugue : "")
            
            solicitudRest.solicitud.direccion.calle = ((datosSolicitud.direccionCliente?.calle) ? datosSolicitud.direccionCliente.calle : "")
            solicitudRest.solicitud.direccion.numeroExterior = ((datosSolicitud.direccionCliente?.numeroExterior) ? datosSolicitud.direccionCliente?.numeroExterior : "")
            solicitudRest.solicitud.direccion.numeroInterior = ((datosSolicitud.direccionCliente?.numeroInterior) ? datosSolicitud.direccionCliente?.numeroInterior : "")
            solicitudRest.solicitud.direccion.codigoPostal = ((datosSolicitud.direccionCliente?.codigoPostal?.codigo) ? datosSolicitud.direccionCliente?.codigoPostal?.codigo : "")
            solicitudRest.solicitud.direccion.colonia = ((datosSolicitud.direccionCliente?.colonia) ? datosSolicitud.direccionCliente?.colonia : "")
            solicitudRest.solicitud.direccion.municipio = ((datosSolicitud.direccionCliente?.codigoPostal?.municipio?.nombre) ? datosSolicitud.direccionCliente?.codigoPostal?.municipio?.nombre : "")
            solicitudRest.solicitud.direccion.ciudad = ((datosSolicitud.direccionCliente?.ciudad) ? datosSolicitud.direccionCliente?.ciudad : "")
            solicitudRest.solicitud.direccion.estado = ((datosSolicitud.direccionCliente?.codigoPostal?.municipio?.estado?.nombre) ? datosSolicitud.direccionCliente?.codigoPostal?.municipio?.estado?.nombre : "")
            
            solicitudRest.solicitud.vivienda.tipoDeVivienda = ((datosSolicitud.direccionCliente?.tipoDeVivienda) ? datosSolicitud.direccionCliente.tipoDeVivienda.nombre : "")
            solicitudRest.solicitud.vivienda.montoDeRenta = 0
            def periodoVivienda = (datosSolicitud.direccionCliente?.tiempoDeVivienda)?.split("/");
            solicitudRest.solicitud.vivienda.mesInicioVivienda = (periodoVivienda ? periodoVivienda.getAt(0) : "")
            solicitudRest.solicitud.vivienda.anioInicioVivienda = (periodoVivienda ? periodoVivienda.getAt(1) : "")
            def periodoResidencia = (datosSolicitud.direccionCliente?.tiempoDeEstadia)?.split("/");
            solicitudRest.solicitud.vivienda.mesInicioResidencia = (periodoResidencia ? periodoResidencia.getAt(0) : "")
            solicitudRest.solicitud.vivienda.anioInicioResidencia = (periodoResidencia ? periodoResidencia.getAt(1) : "")
            
            solicitudRest.solicitud.empleo.profesion = ((datosSolicitud.empleoCliente?.profesion) ? datosSolicitud.empleoCliente?.profesion?.nombre : "")
            solicitudRest.solicitud.empleo.ocupacion = ((datosSolicitud.empleoCliente?.ocupacion) ? datosSolicitud.empleoCliente?.ocupacion?.nombre : "")
            solicitudRest.solicitud.empleo.empresa = ((datosSolicitud.empleoCliente?.nombreDeLaEmpresa) ? datosSolicitud.empleoCliente?.nombreDeLaEmpresa : "")
            def fechaIngreso = (datosSolicitud.empleoCliente?.fechaIngreso)?.split("/");
            solicitudRest.solicitud.empleo.mesInicioEmpleo = (fechaIngreso ? fechaIngreso.getAt(0) : "")
            solicitudRest.solicitud.empleo.anioInicioEmpleo = (fechaIngreso ? fechaIngreso.getAt(1) : "")
            solicitudRest.solicitud.empleo.ingresosFijos = ((datosSolicitud.empleoCliente?.ingresosFijos) ? datosSolicitud.empleoCliente?.ingresosFijos : 0)
            solicitudRest.solicitud.empleo.ingresosVariables = ((datosSolicitud.empleoCliente?.ingresosVariables) ? datosSolicitud.empleoCliente?.ingresosVariables : 0)
            solicitudRest.solicitud.empleo.ingresosTotales = solicitudRest.solicitud.empleo.ingresosFijos + solicitudRest.solicitud.empleo.ingresosVariables
            solicitudRest.solicitud.empleo.gastosMensuales = ((datosSolicitud.empleoCliente?.gastos) ? datosSolicitud.empleoCliente?.gastos : 0)
            
            solicitudRest.solicitud.documentos << [tipoDeDocumento: "Comprobante De Domicilio", contenidoBase64: "TEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4="]
            
            /*datosSolicitud.documentosSolicitud.each { documento ->
            def mapaDocto = [:]
            mapaDocto.tipoDeDocumento = documento.tipoDeDocumento.nombre
            mapaDocto.contenidoBase64 = generarBase64(new File(documento.rutaDelArchivo))
            }*/
            
            solicitudRest.solicitud.buroDeCredito.apellidoPaterno = (datosBuroDeCredito.reporte?.apellidoPaterno ? datosBuroDeCredito.reporte?.apellidoPaterno : "")
            solicitudRest.solicitud.buroDeCredito.apellidoMaterno = (datosBuroDeCredito.reporte?.apellidoMaterno ? datosBuroDeCredito.reporte?.apellidoMaterno : "")
            solicitudRest.solicitud.buroDeCredito.apellidoAdicional = (datosBuroDeCredito.reporte?.apellidoAdicional ? datosBuroDeCredito.reporte?.apellidoAdicional : "")
            solicitudRest.solicitud.buroDeCredito.primerNombre = (datosBuroDeCredito.reporte?.primerNombre ? datosBuroDeCredito.reporte?.primerNombre : "")
            solicitudRest.solicitud.buroDeCredito.segundoNombre = (datosBuroDeCredito.reporte?.segundoNombre ? datosBuroDeCredito.reporte?.segundoNombre : "")
            solicitudRest.solicitud.buroDeCredito.fechaDeNacimiento = (datosBuroDeCredito.reporte?.fechaDeNacimiento ? datosBuroDeCredito.reporte?.fechaDeNacimiento : "")
            solicitudRest.solicitud.buroDeCredito.rfc = (datosBuroDeCredito.reporte?.rfc ? datosBuroDeCredito.reporte?.rfc : "")
            solicitudRest.solicitud.buroDeCredito.prefijoProfesional = (datosBuroDeCredito.reporte?.prefijoProfesional ? datosBuroDeCredito.reporte?.prefijoProfesional : "")
            solicitudRest.solicitud.buroDeCredito.sufijoPersonal = (datosBuroDeCredito.reporte?.sufijoPersonal ? datosBuroDeCredito.reporte?.sufijoPersonal : "")
            solicitudRest.solicitud.buroDeCredito.nacionalidad = (datosBuroDeCredito.reporte?.nacionalidad ? datosBuroDeCredito.reporte?.nacionalidad : "")
            solicitudRest.solicitud.buroDeCredito.tipoResidencia = (datosBuroDeCredito.reporte?.tipoResidencia ? datosBuroDeCredito.reporte?.tipoResidencia : "")
            solicitudRest.solicitud.buroDeCredito.numeroLicenciaConducir = (datosBuroDeCredito.reporte?.numeroLicenciaConducir ? datosBuroDeCredito.reporte?.numeroLicenciaConducir : "")
            solicitudRest.solicitud.buroDeCredito.estadoCivil = (datosBuroDeCredito.reporte?.estadoCivil ? datosBuroDeCredito.reporte?.estadoCivil : "")
            solicitudRest.solicitud.buroDeCredito.genero = (datosBuroDeCredito.reporte?.genero ? datosBuroDeCredito.reporte?.genero : "")
            solicitudRest.solicitud.buroDeCredito.numeroCedulaProfesional = (datosBuroDeCredito.reporte?.numeroCedulaProfesional ? datosBuroDeCredito.reporte?.numeroCedulaProfesional : "")
            solicitudRest.solicitud.buroDeCredito.numeroIFE = (datosBuroDeCredito.reporte?.numeroIFE ? datosBuroDeCredito.reporte?.numeroIFE : "")
            solicitudRest.solicitud.buroDeCredito.curp = (datosBuroDeCredito.reporte?.curp ? datosBuroDeCredito.reporte?.curp : "")
            solicitudRest.solicitud.buroDeCredito.numeroDependientes = (datosBuroDeCredito.reporte?.numeroDependientes ? datosBuroDeCredito.reporte?.numeroDependientes : "")
            solicitudRest.solicitud.buroDeCredito.edadDependientes = (datosBuroDeCredito.reporte?.edadDependientes ? datosBuroDeCredito.reporte?.edadDependientes : "")
            solicitudRest.solicitud.buroDeCredito.fechaDefuncionCliente = (datosBuroDeCredito.reporte?.fechaDefuncionCliente ? datosBuroDeCredito.reporte?.fechaDefuncionCliente : "")
            solicitudRest.solicitud.buroDeCredito.fechaConsulta =  (datosBuroDeCredito.reporte?.fechaConsulta ? (datosBuroDeCredito.reporte?.fechaConsulta).format('dd/MM/yyyy HH:mm') : "")
            solicitudRest.solicitud.buroDeCredito.errorConsulta = (datosBuroDeCredito.reporte?.errorConsulta ? datosBuroDeCredito.reporte?.errorConsulta : "")
            
            solicitudRest.solicitud.buroDeCredito.consultas = []
            datosBuroDeCredito.consultas.each { consulta ->
                def mapaConsulta = [:]
                mapaConsulta.fechaConsulta = (consulta.fechaConsulta ?: "")
                mapaConsulta.claveDelUsuario = (consulta.claveDelUsuario ?: "")
                mapaConsulta.nombreDelUsuario = (consulta.nombreDelUsuario ?: "")
                mapaConsulta.numeroDeTelefono = (consulta.numeroDeTelefono ?: "")
                mapaConsulta.tipoDeContrato = (consulta.tipoDeContrato ?: "")
                mapaConsulta.monedaDelCredito = (consulta.monedaDelCredito ?: "")
                mapaConsulta.importeDelContrato = (consulta.importeDelContrato ?: "")
                mapaConsulta.tipoResponsabilidadCuenta = (consulta.tipoResponsabilidadCuenta ?: "")
                mapaConsulta.indicadorDeClienteNuevo = (consulta.indicadorDeClienteNuevo ?: "")
                solicitudRest.solicitud.buroDeCredito.consultas << mapaConsulta
            }
            
            solicitudRest.solicitud.buroDeCredito.creditos = []
            datosBuroDeCredito.creditos.each { credito ->
                def mapaCredito = [:]
                mapaCredito.fechaActualizacion = (credito.fechaActualizacion ?: "")
                mapaCredito.registroImpugnado = (credito.registroImpugnado ?: "")
                mapaCredito.claveUsuario = (credito.claveUsuario ?: "")
                mapaCredito.nombreUsuario = (credito.nombreUsuario ?: "")
                mapaCredito.numeroTelefonoUsuario = (credito.numeroTelefonoUsuario ?: "")
                mapaCredito.numeroDeCuenta = (credito.numeroDeCuenta ?: "")
                mapaCredito.tipoResponsabilidadCuenta = (credito.tipoResponsabilidadCuenta ?: "")
                mapaCredito.tipoDeCuenta = (credito.tipoDeCuenta ?: "")
                mapaCredito.tipoContratoProducto = (credito.tipoContratoProducto ?: "")
                mapaCredito.monedaCredito = (credito.monedaCredito ?: "")
                mapaCredito.importeDelAvaluo = (credito.importeDelAvaluo ?: "")
                mapaCredito.numeroPagos = (credito.numeroPagos ?: "")
                mapaCredito.frecuenciaDePagos = (credito.frecuenciaDePagos ?: "")
                mapaCredito.montoAPagar = (credito.montoAPagar ?: "")
                mapaCredito.fechaAperturaDeCuenta = (credito.fechaAperturaDeCuenta ?: "")
                mapaCredito.fechaUltimoPago = (credito.fechaUltimoPago ?: "")
                mapaCredito.fechaUltimaCompra = (credito.fechaUltimaCompra ?: "")
                mapaCredito.fechaCierre = (credito.fechaCierre ?: "")
                mapaCredito.fechaDeReporteDeInformacion = (credito.fechaDeReporteDeInformacion ?: "")
                mapaCredito.montoAReportar = (credito.montoAReportar ?: "")
                mapaCredito.ultimaFechaConSaldoEnCero = (credito.ultimaFechaConSaldoEnCero ?: "")
                mapaCredito.garantia = (credito.garantia ?: "")
                mapaCredito.creditoMaximoAutorizado = (credito.creditoMaximoAutorizado ?: "")
                mapaCredito.saldoActual = (credito.saldoActual ?: "")
                mapaCredito.limiteCredito = (credito.limiteCredito ?: "")
                mapaCredito.saldoVencido = (credito.saldoVencido ?: "")
                mapaCredito.numeroDePagosVencidos = (credito.numeroDePagosVencidos ?: "")
                mapaCredito.clasificacionPuntialidadPago = (credito.clasificacionPuntialidadPago ?: "")
                mapaCredito.historicoPagos = (credito.historicoPagos ?: "")
                mapaCredito.fechaMasRecienteHistoricoDePagos = (credito.fechaMasRecienteHistoricoDePagos ?: "")
                mapaCredito.fechaMasAntiguaHistoricoDePagos = (credito.fechaMasAntiguaHistoricoDePagos ?: "")
                mapaCredito.claveDeObservacion = (credito.claveDeObservacion ?: "")
                mapaCredito.totalPagosReportados = (credito.totalPagosReportados ?: "")
                mapaCredito.totalPagosConMop02 = (credito.totalPagosConMop02 ?: "")
                mapaCredito.totalPagosConMop03 = (credito.totalPagosConMop03 ?: "")
                mapaCredito.totalPagosConMop04 = (credito.totalPagosConMop04 ?: "")
                mapaCredito.totalPagosConMop05oMayor = (credito.totalPagosConMop05oMayor ?: "")
                mapaCredito.saldoEnlaMorosidadHistoricaMasAlta = (credito.saldoEnlaMorosidadHistoricaMasAlta ?: "")
                mapaCredito.fechaEnlaMorosidadHistoricaMasAlta = (credito.fechaEnlaMorosidadHistoricaMasAlta ?: "")
                mapaCredito.clasificacionPuntualidadPagoMopMorosidadMasAlta = (credito.clasificacionPuntualidadPagoMopMorosidadMasAlta ?: "")
                mapaCredito.fechaInicioRestructura = (credito.fechaInicioRestructura ?: "")
                mapaCredito.montoUltimoPago = (credito.montoUltimoPago ?: "")
                solicitudRest.solicitud.buroDeCredito.creditos << mapaCredito
            }
            
            solicitudRest.solicitud.buroDeCredito.direcciones = []
            datosBuroDeCredito.direcciones.each { direccion ->
                def maparaDireccion = [:]
                mapaDireccion.direccionPrimeraLinea = (direccion.direccionPrimeraLinea ?: "")
                mapaDireccion.direccionSegundaLinea = (direccion.direccionSegundaLinea ?: "")
                mapaDireccion.colonia = (direccion.colonia ?: "")
                mapaDireccion.municipio = (direccion.municipio ?: "")
                mapaDireccion.ciudad = (direccion.ciudad ?: "")
                mapaDireccion.estado = (direccion.estado ?: "")
                mapaDireccion.codigoPostal = (direccion.codigoPostal ?: "")
                mapaDireccion.fechaResidencia = (direccion.fechaResidencia ?: "")
                mapaDireccion.numeroTelefono = (direccion.numeroTelefono ?: "")
                mapaDireccion.extensionTelefono = (direccion.extensionTelefono ?: "")
                mapaDireccion.numeroFax = (direccion.numeroFax ?: "")
                mapaDireccion.tipoDomicilio = (direccion.tipoDomicilio ?: "")
                mapaDireccion.indicadorEspecialDomicilio = (direccion.indicadorEspecialDomicilio ?: "")
                solicitudRest.solicitud.buroDeCredito.direcciones << maparaDireccion
            }
            
            solicitudRest.solicitud.buroDeCredito.empleos = []
            datosBuroDeCredito.empleos.each { empleo ->
                def mapaEmpleo = [:]
                mapaEmpleo.razonSocial = (empleo.razonSocial ?: "")
                mapaEmpleo.direccionLinea1 = (empleo.direccionLinea1 ?: "")
                mapaEmpleo.direccionLinea2 = (empleo.direccionLinea2 ?: "")
                mapaEmpleo.colonia = (empleo.colonia ?: "")
                mapaEmpleo.municipio = (empleo.municipio ?: "")
                mapaEmpleo.ciudad = (empleo.ciudad ?: "")
                mapaEmpleo.estado = (empleo.estado ?: "")
                mapaEmpleo.codigoPostal = (empleo.codigoPostal ?: "")
                mapaEmpleo.numeroTelefonico = (empleo.numeroTelefonico ?: "")
                mapaEmpleo.extensionTelefonica = (empleo.extensionTelefonica ?: "")
                mapaEmpleo.numeroFax = (empleo.numeroFax ?: "")
                mapaEmpleo.cargo = (empleo.cargo ?: "")
                mapaEmpleo.fechaContratacion = (empleo.fechaContratacion ?: "")
                mapaEmpleo.claveMonedaPago = (empleo.claveMonedaPago ?: "")
                mapaEmpleo.sueldo = (empleo.sueldo ?: "")
                mapaEmpleo.periodoDePago = (empleo.periodoDePago ?: "")
                mapaEmpleo.numeroEmpleado = (empleo.numeroEmpleado ?: "")
                mapaEmpleo.fechaUltimoDiaEmpleo = (empleo.fechaUltimoDiaEmpleo ?: "")
                mapaEmpleo.fechaReporteEmpleo = (empleo.fechaReporteEmpleo ?: "")
                mapaEmpleo.fechaVerificacionEmpleo = (empleo.fechaVerificacionEmpleo ?: "")
                mapaEmpleo.modoVerificacion = (empleo.modoVerificacion ?: "")
                solicitudRest.solicitud.buroDeCredito.empleos << mapaEmpleo
            }
            
            solicitudRest.solicitud.buroDeCredito.refCred = []
            datosBuroDeCredito.refCred.each { ref ->
                def mapaRef = [:]
                mapaRef.numeroCuenta = (ref.numeroCuenta ?: "")
                mapaRef.claveUsuario = (ref.claveUsuario ?: "")
                mapaRef.nombreUsuario = (ref.nombreUsuario ?: "")
                solicitudRest.solicitud.buroDeCredito.refCred << mapaRef
            }
            
            solicitudRest.solicitud.buroDeCredito.sintetiza = []
            datosBuroDeCredito.sintetiza.each { sintesis ->
                def mapaSintesis = [:]
                mapaSintesis.plantillaSolicitada = (sintesis.plantillaSolicitada ?: "")
                mapaSintesis.identificador = (sintesis.identificador ?: "")
                mapaSintesis.numeroCaracteristica = (sintesis.numeroCaracteristica ?: "")
                mapaSintesis.valorCaracteristica = (sintesis.valorCaracteristica ?: "")
                mapaSintesis.codigoError = (sintesis.codigoError ?: "")
                solicitudRest.solicitud.buroDeCredito.refCred = [] << mapaSintesis
            }
            
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito = [:]
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.fechaIntegracion = (datosBuroDeCredito.resumen?.fechaIntegracion ? datosBuroDeCredito.resumen?.fechaIntegracion : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop00 = (datosBuroDeCredito.resumen?.numeroCuentaMop00 ? datosBuroDeCredito.resumen?.numeroCuentaMop00 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop01 = (datosBuroDeCredito.resumen?.numeroCuentaMop01 ? datosBuroDeCredito.resumen?.numeroCuentaMop01 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop02 = (datosBuroDeCredito.resumen?.numeroCuentaMop02 ? datosBuroDeCredito.resumen?.numeroCuentaMop02 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop03 = (datosBuroDeCredito.resumen?.numeroCuentaMop03 ? datosBuroDeCredito.resumen?.numeroCuentaMop03 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop04 = (datosBuroDeCredito.resumen?.numeroCuentaMop04 ? datosBuroDeCredito.resumen?.numeroCuentaMop04 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop05 = (datosBuroDeCredito.resumen?.numeroCuentaMop05 ? datosBuroDeCredito.resumen?.numeroCuentaMop05 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop06 = (datosBuroDeCredito.resumen?.numeroCuentaMop06 ? datosBuroDeCredito.resumen?.numeroCuentaMop06 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop07 = (datosBuroDeCredito.resumen?.numeroCuentaMop07 ? datosBuroDeCredito.resumen?.numeroCuentaMop07 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop96 = (datosBuroDeCredito.resumen?.numeroCuentaMop96 ? datosBuroDeCredito.resumen?.numeroCuentaMop96 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop97 = (datosBuroDeCredito.resumen?.numeroCuentaMop97 ? datosBuroDeCredito.resumen?.numeroCuentaMop97 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMop99 = (datosBuroDeCredito.resumen?.numeroCuentaMop99 ? datosBuroDeCredito.resumen?.numeroCuentaMop99 : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentaMopUR = (datosBuroDeCredito.resumen?.numeroCuentaMopUR ? datosBuroDeCredito.resumen?.numeroCuentaMopUR : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentas = (datosBuroDeCredito.resumen?.numeroCuentas ? datosBuroDeCredito.resumen?.numeroCuentas : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.cuentasPagosFijosHipotecarios = (datosBuroDeCredito.resumen?.cuentasPagosFijosHipotecarios ? datosBuroDeCredito.resumen?.cuentasPagosFijosHipotecarios : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.noCuentasRevolventes = (datosBuroDeCredito.resumen?.noCuentasRevolventes ? datosBuroDeCredito.resumen?.noCuentasRevolventes : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.noCuentasCerradas = (datosBuroDeCredito.resumen?.noCuentasCerradas ? datosBuroDeCredito.resumen?.noCuentasCerradas : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.noCuentasConMorosidadActual = (datosBuroDeCredito.resumen?.noCuentasConMorosidadActual ? datosBuroDeCredito.resumen?.noCuentasConMorosidadActual : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.noCuentasConHistorialMorosidad = (datosBuroDeCredito.resumen?.noCuentasConHistorialMorosidad ? datosBuroDeCredito.resumen?.noCuentasConHistorialMorosidad : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.noCuentasEnAclaracion = (datosBuroDeCredito.resumen?.noCuentasEnAclaracion ? datosBuroDeCredito.resumen?.noCuentasEnAclaracion : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.noSolicitudConsultas = (datosBuroDeCredito.resumen?.noSolicitudConsultas ? datosBuroDeCredito.resumen?.noSolicitudConsultas : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.nuevaDireccionEn60Dias = (datosBuroDeCredito.resumen?.nuevaDireccionEn60Dias ? datosBuroDeCredito.resumen?.nuevaDireccionEn60Dias : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.mensajesAlerta = (datosBuroDeCredito.resumen?.mensajesAlerta ? datosBuroDeCredito.resumen?.mensajesAlerta : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.declarativa = (datosBuroDeCredito.resumen?.declarativa ? datosBuroDeCredito.resumen?.declarativa : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.monedaCredito = (datosBuroDeCredito.resumen?.monedaCredito ? datosBuroDeCredito.resumen?.monedaCredito : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalCreditoMaximosCuentasRevol = (datosBuroDeCredito.resumen?.totalCreditoMaximosCuentasRevol ? datosBuroDeCredito.resumen?.totalCreditoMaximosCuentasRevol : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalLimiteCreditoCuentasRevol = (datosBuroDeCredito.resumen?.totalLimiteCreditoCuentasRevol ? datosBuroDeCredito.resumen?.totalLimiteCreditoCuentasRevol : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalSaldoActualCuentasRevol = (datosBuroDeCredito.resumen?.totalSaldoActualCuentasRevol ? datosBuroDeCredito.resumen?.totalSaldoActualCuentasRevol : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalSaldosVencidosCuentasRevol = (datosBuroDeCredito.resumen?.totalSaldosVencidosCuentasRevol ? datosBuroDeCredito.resumen?.totalSaldosVencidosCuentasRevol : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalImportePagoCuentasRevol = (datosBuroDeCredito.resumen?.totalImportePagoCuentasRevol ? datosBuroDeCredito.resumen?.totalImportePagoCuentasRevol : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.porcentajeLimiteCreditoCuentasRevol = (datosBuroDeCredito.resumen?.porcentajeLimiteCreditoCuentasRevol ? datosBuroDeCredito.resumen?.porcentajeLimiteCreditoCuentasRevol : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalCreditoMaximoCuentasPagosFijosHipo = (datosBuroDeCredito.resumen?.totalCreditoMaximoCuentasPagosFijosHipo ? datosBuroDeCredito.resumen?.totalCreditoMaximoCuentasPagosFijosHipo : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalSaldosActualesCuentasPagosFijosHipo = (datosBuroDeCredito.resumen?.totalSaldosActualesCuentasPagosFijosHipo ? datosBuroDeCredito.resumen?.totalSaldosActualesCuentasPagosFijosHipo : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalSaldosActualesCuentasPagosFijosHipo = (datosBuroDeCredito.resumen?.totalSaldoVencidoCuentasPagosFijosHipo ? datosBuroDeCredito.resumen?.totalSaldoVencidoCuentasPagosFijosHipo : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.totalImporteCuentasPagosFijosHipo = (datosBuroDeCredito.resumen?.totalImporteCuentasPagosFijosHipo ? datosBuroDeCredito.resumen?.totalImporteCuentasPagosFijosHipo : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.fechaAperturaCuentaMasAntigua = (datosBuroDeCredito.resumen?.fechaAperturaCuentaMasAntigua ? datosBuroDeCredito.resumen?.fechaAperturaCuentaMasAntigua : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.fechaAperturaCuentaMasReciente = (datosBuroDeCredito.resumen?.fechaAperturaCuentaMasReciente ? datosBuroDeCredito.resumen?.fechaAperturaCuentaMasReciente : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroSolicitudesInformeBuro = (datosBuroDeCredito.resumen?.numeroSolicitudesInformeBuro ? datosBuroDeCredito.resumen?.numeroSolicitudesInformeBuro : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.fechaConsultaMasReciente = (datosBuroDeCredito.resumen?.fechaConsultaMasReciente ? datosBuroDeCredito.resumen?.fechaConsultaMasReciente : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroCuentasEnDespachoCobranza = (datosBuroDeCredito.resumen?.numeroCuentasEnDespachoCobranza ? datosBuroDeCredito.resumen?.numeroCuentasEnDespachoCobranza : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.fechaAperturaMasRecienteDespachoCobranza = (datosBuroDeCredito.resumen?.fechaAperturaMasRecienteDespachoCobranza ? datosBuroDeCredito.resumen?.fechaAperturaMasRecienteDespachoCobranza : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.numeroSolicitudesInformeBuroPorDespachoCobranza = (datosBuroDeCredito.resumen?.numeroSolicitudesInformeBuroPorDespachoCobranza ? datosBuroDeCredito.resumen?.numeroSolicitudesInformeBuroPorDespachoCobranza : "")
            solicitudRest.solicitud.buroDeCredito.resumenBuroCredito.fechaConsultaMasRecientePorDespachoCobranza = (datosBuroDeCredito.resumen?.fechaConsultaMasRecientePorDespachoCobranza ? datosBuroDeCredito.resumen?.fechaConsultaMasRecientePorDespachoCobranza : "")
            
            respuesta << solicitudRest.solicitud
        }
        respuesta
    }
    
    def generarBase64(def newFile) {
        def base64
        byte[] array = Files.readAllBytes((newFile).toPath()); 
        base64 = Base64.encodeBase64String(array)
        return base64
    }
}
