package la.kosmos.app

import grails.transaction.Transactional
import groovy.sql.Sql

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
}
