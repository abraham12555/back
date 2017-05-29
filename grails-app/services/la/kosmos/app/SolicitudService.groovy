package la.kosmos.app

import grails.transaction.Transactional
import groovy.sql.Sql
import la.kosmos.rest.SolicitudRest
import java.nio.file.Files;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils
import org.apache.commons.lang.StringUtils

@Transactional
class SolicitudService {
    
    def dataSource
    def buroDeCreditoService

    def construirDatosTemporales(def datosPreguardados, def params, def pasoEnviado, def identificadores, def entidadFinanciera, def token, def shortUrl, def usuario) {
        println "Contenido del Mapa: " + datosPreguardados + " - Parametros Enviados:" + params +" - Paso Enviado: " +pasoEnviado + " - Identificadores: " + identificadores
	def cliente
        def clienteNuevo = false
        def datosPaso = [:]
        def guardadoCorrecto = true
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
                cliente.nombre = (datosPaso.cliente.segundoNombre ? (datosPaso.cliente.nombre + " " + datosPaso.cliente.segundoNombre) : datosPaso.cliente.nombre)
                cliente.apellidoPaterno = datosPaso.cliente.apellidoPaterno
                cliente.apellidoMaterno = (datosPaso.cliente.apellidoMaterno.toUpperCase() == "NA" ? "" : datosPaso.cliente.apellidoMaterno) 
                cliente.lugarDeNacimiento = (datosPaso.cliente.lugarDeNacimiento ? Estado.get(datosPaso.cliente.lugarDeNacimiento) : null)
                if(datosPaso.cliente.nacionalidad){
                    cliente.nacionalidad = Nacionalidad.get(datosPaso.cliente.nacionalidad as long)
                } else if(cliente.lugarDeNacimiento){
                    if(cliente.lugarDeNacimiento?.id > 32){
                        cliente.nacionalidad = Nacionalidad.get(2 as long)
                    } else {
                        cliente.nacionalidad = Nacionalidad.get(1 as long)
                    }
                }
                cliente.fechaDeNacimiento = ((datosPaso.cliente.fechaDeNacimiento?.dia && datosPaso.cliente.fechaDeNacimiento?.mes && datosPaso.cliente.fechaDeNacimiento?.anio) ? (new Date().parse("dd/MM/yyyy", (datosPaso.cliente.fechaDeNacimiento.dia + "/" + datosPaso.cliente.fechaDeNacimiento.mes + "/" + datosPaso.cliente.fechaDeNacimiento.anio))) : null)
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
                    } else if(!clienteNuevo && datosPaso.telefonoCliente){
                        def telefonoCasa
                        def telefonoCelular
                        def telefonosCliente = TelefonoCliente.findAllWhere(cliente: cliente, vigente: true)
                        telefonosCliente?.each {
                            if(it.tipoDeTelefono.id == 1) {
                                if(datosPaso.telefonoCliente.telefonoCasa && (datosPaso.telefonoCliente.telefonoCasa != it.numeroTelefonico)){
                                    it.vigente = false
                                    it.save(flush:true)
                                    telefonoCasa = new TelefonoCliente()
                                    telefonoCasa.cliente = cliente
                                    telefonoCasa.numeroTelefonico = datosPaso.telefonoCliente.telefonoCasa
                                    telefonoCasa.vigente = true
                                    telefonoCasa.tipoDeTelefono = TipoDeTelefono.get(1)
                                    telefonoCasa.save(flush: true)
                                }
                            } else if(it.tipoDeTelefono.id == 2) {
                                if(datosPaso.telefonoCliente.telefonoCelular && (datosPaso.telefonoCliente.telefonoCelular != it.numeroTelefonico)){
                                    it.vigente = false
                                    it.save(flush:true)
                                    telefonoCelular = new TelefonoCliente()
                                    telefonoCelular.cliente = cliente
                                    telefonoCelular.numeroTelefonico = datosPaso.telefonoCliente.telefonoCelular
                                    telefonoCelular.vigente = true
                                    telefonoCelular.tipoDeTelefono = TipoDeTelefono.get(2)
                                    telefonoCelular.save(flush: true)
                                }
                            }
                        }
                    }
                    if(clienteNuevo && datosPaso.emailCliente) {
                        def correoExistente = EmailCliente.findWhere(cliente: cliente, direccionDeCorreo: datosPaso.emailCliente.emailPersonal.toLowerCase())
                        if(datosPaso.emailCliente?.emailPersonal && !correoExistente) {
                            EmailCliente.executeUpdate("update EmailCliente set vigente = false where cliente.id = :idCliente",[idCliente: cliente.id])
                            def emailPersonal = new EmailCliente()
                            emailPersonal.cliente = cliente
                            emailPersonal.direccionDeCorreo = datosPaso.emailCliente.emailPersonal.toLowerCase()
                            emailPersonal.tipoDeEmail = TipoDeEmail.get(1);
                            emailPersonal.vigente = true
                            emailPersonal.save(flush: true)
                        } else if (correoExistente) {
                            correoExistente.vigente = true
                            correoExistente.save(flush: true)
                        }
                    } else if(!clienteNuevo && datosPaso.emailCliente) {
                        def correoExistente = EmailCliente.findWhere(cliente: cliente, direccionDeCorreo: datosPaso.emailCliente.emailPersonal.toLowerCase())
                        if(datosPaso.emailCliente?.emailPersonal && !correoExistente) {
                            EmailCliente.executeUpdate("update EmailCliente set vigente = false where cliente.id = :idCliente",[idCliente: cliente.id])
                            def emailPersonal = new EmailCliente()
                            emailPersonal.cliente = cliente
                            emailPersonal.direccionDeCorreo = datosPaso.emailCliente.emailPersonal.toLowerCase()
                            emailPersonal.tipoDeEmail = TipoDeEmail.get(1);
                            emailPersonal.vigente = true
                            emailPersonal.save(flush: true)
                        } else if (correoExistente) {
                            correoExistente.vigente = true
                            correoExistente.save(flush: true)
                        }
                    }
                    if(identificadores?.idSolicitud) {
                        println "El cliente " + cliente.id + " ya tiene asociada la solicitud " + identificadores?.idSolicitud
                        def solicitud = SolicitudDeCredito.get(identificadores?.idSolicitud as long)
                        if(datosPaso.direccionCliente?.sucursal){
                            solicitud.sucursal = SucursalEntidadFinanciera.get(datosPaso.direccionCliente?.sucursal as long)
                            if(solicitud.save(flush: true)){
                                println "La sucursal se ha registrado correctamente..."
                            } else {
                                println "La sucursal no se ha registrado..."
                            }
                        }
                    } else {
                        def sql = new Sql(dataSource)
                        def solicitudDeCredito = new SolicitudDeCredito()
                        solicitudDeCredito.fechaDeSolicitud = new Date()
                        solicitudDeCredito.statusDeSolicitud = StatusDeSolicitud.get(1)
                        solicitudDeCredito.entidadFinanciera = entidadFinanciera
                        //solicitudDeCredito.folio = new Long(sql.firstRow("select nextval('folios_entidad_" + (solicitudDeCredito.entidadFinanciera.id) + "')").nextval)
                        solicitudDeCredito.cliente = cliente
                        solicitudDeCredito.token = token
                        solicitudDeCredito.shortUrl = shortUrl
                        solicitudDeCredito.registradaPor = usuario
                        if(usuario) {
                            solicitudDeCredito.sucursal = usuario.sucursal
                            solicitudDeCredito.medioDeContacto = MedioDeContacto.get(1)
                            solicitudDeCredito.opcionMedioDeContacto = usuario.sucursal.id
                        }
                        if(identificadores?.idSolicitudTemporal) {
                            def solicitudTemporal = SolicitudTemporal.get(identificadores?.idSolicitudTemporal as long)
                            println("Si trae solicitud temporal, tiene folio? " + solicitudTemporal.folio)
                            if(solicitudTemporal.folio) {
                                solicitudDeCredito.folio = solicitudTemporal.folio
                            } else {
                                solicitudDeCredito.folio = generarFolioAlfanumerico(7);
                                while(verificarExistenciaDeFolio(solicitudDeCredito.folio, solicitudDeCredito.entidadFinanciera, 1)) {
                                    solicitudDeCredito.folio = generarFolioAlfanumerico(7);
                                }
                            }
                        } else {
                            println("No trae solicitud temporal ...")
                            solicitudDeCredito.folio = generarFolioAlfanumerico(7);
                            while(verificarExistenciaDeFolio(solicitudDeCredito.folio, solicitudDeCredito.entidadFinanciera, 1)) {
                                solicitudDeCredito.folio = generarFolioAlfanumerico(7);
                            }
                        }
                        if(solicitudDeCredito.save(flush: true)){
                            println "Si se guardo la solicitud: " + solicitudDeCredito?.id
                            datosPaso.cliente.idSolicitud = solicitudDeCredito.id
                            def temporal = SolicitudTemporal.findWhere(token: token)
                            if(temporal){
                                temporal.delete()
                            }
                        } else {
                            guardadoCorrecto = false
                            println ":( no se guardo la solicitud"
                            if (solicitudDeCredito.hasErrors()) {
                                solicitudDeCredito.errors.allErrors.each {
                                    println it
                                }
                            }
                        }
                    }
                } else {
                    guardadoCorrecto = false
                    if (cliente.hasErrors()) {
                        cliente.errors.allErrors.each {
                            println it
                        }
                    }
                }
            }
            if(datosPaso.direccionCliente) {
                if(identificadores?.idCliente || (usuario && datosPaso?.cliente?.idCliente)) {
                    def idCliente = (identificadores?.idCliente ?: datosPaso?.cliente?.idCliente)
                    cliente = Cliente.get(idCliente as long)
                    def direccionCliente
                    if(identificadores?.idDireccion){
                        direccionCliente = DireccionCliente.get(identificadores.idDireccion as long)
                    } else {
                        DireccionCliente.executeUpdate("update DireccionCliente set vigente = false where cliente.id = :idCliente",[idCliente: cliente.id])
                        direccionCliente = new DireccionCliente()
                    }
                    direccionCliente.calle = datosPaso.direccionCliente.calle
                    direccionCliente.ciudad = datosPaso.direccionCliente.ciudad
                    direccionCliente.numeroExterior = datosPaso.direccionCliente.numeroExterior
                    direccionCliente.numeroInterior = datosPaso.direccionCliente.numeroInterior
                    direccionCliente.codigoPostal = (datosPaso.direccionCliente.codigoPostal ? CodigoPostal.findByCodigo(datosPaso.direccionCliente.codigoPostal?.replaceFirst('^0+(?!$)', '')) : null)
                    direccionCliente.colonia = datosPaso.direccionCliente.colonia
                    direccionCliente.ciudad  = (datosPaso.direccionCliente.municipio ? Municipio.get(datosPaso.direccionCliente.municipio) : null)
                    direccionCliente.tipoDeVivienda = (datosPaso.direccionCliente.tipoDeVivienda ? TipoDeVivienda.get(datosPaso.direccionCliente.tipoDeVivienda as long) : null)
                    direccionCliente.temporalidad = (datosPaso.direccionCliente.temporalidad ? Temporalidad.get(datosPaso.direccionCliente.temporalidad as long) : null)
                    direccionCliente.cliente = cliente
                    direccionCliente.tiempoDeResidencia = (datosPaso.direccionCliente.tiempo ? (datosPaso.direccionCliente.tiempo as int) : 0)
                    direccionCliente.latitud = 0
                    direccionCliente.longitud = 0
                    direccionCliente.tiempoDeEstadia = ((datosPaso.direccionCliente.tiempoDeResidencia?.mes && datosPaso.direccionCliente.tiempoDeResidencia?.anio) ? ((datosPaso.direccionCliente.tiempoDeResidencia?.mes?.toString()?.padLeft(2, '0')) + "/" + datosPaso.direccionCliente.tiempoDeResidencia?.anio) : null)
                    direccionCliente.tiempoDeVivienda = ((datosPaso.direccionCliente.tiempoDeVivir?.mes && datosPaso.direccionCliente.tiempoDeVivir?.anio) ? ((datosPaso.direccionCliente.tiempoDeVivir?.mes?.toString()?.padLeft(2, '0')) + "/" + datosPaso.direccionCliente.tiempoDeVivir?.anio) : null)
                    direccionCliente.montoDeLaRenta = ( datosPaso.direccionCliente.montoDeLaRenta ? datosPaso.direccionCliente.montoDeLaRenta as float : 0)
                    if(direccionCliente.save(flush:true)){
                        println("La dirección se ha registrado correctamente")
                        datosPaso.direccionCliente.idDireccion = direccionCliente.id
                    } else {
                        guardadoCorrecto = false
                        if (direccionCliente.hasErrors()) {
                            direccionCliente.errors.allErrors.each {
                                println it
                            }
                        }
                    }
                }
            }
            if (datosPaso.empleoCliente){
                if(identificadores?.idCliente || (usuario && datosPaso?.cliente?.idCliente)) {
                    def idCliente = (identificadores?.idCliente ?: datosPaso?.cliente?.idCliente)
                    cliente = Cliente.get(idCliente as long)
                    def empleoCliente
                    if(identificadores?.idEmpleo){
                        empleoCliente = EmpleoCliente.get(identificadores.idEmpleo as long)
                    } else {
                        EmpleoCliente.executeUpdate("update EmpleoCliente set vigente = false where cliente.id = :idCliente",[idCliente: cliente.id])
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
                    empleoCliente.fechaIngreso = ((datosPaso.empleoCliente.antiguedad?.mes && datosPaso.empleoCliente.antiguedad?.anio) ? ((datosPaso.empleoCliente.antiguedad?.mes?.toString()?.padLeft(2, '0')) + "/" + datosPaso.empleoCliente.antiguedad?.anio) : null)
                    empleoCliente.ingresosFijos = (datosPaso.empleoCliente.ingresosFijos ? datosPaso.empleoCliente.ingresosFijos as float : 0)
                    empleoCliente.ingresosVariables = (datosPaso.empleoCliente.ingresosVariables ? datosPaso.empleoCliente.ingresosVariables as float : 0)
                    empleoCliente.gastos = (datosPaso.empleoCliente.gastos ? datosPaso.empleoCliente.gastos as int : 0)
                    if(empleoCliente.save(flush:true)){
                        println("El empleo se ha registrado correctamente")
                        datosPaso.empleoCliente.idEmpleo = empleoCliente.id
                    } else {
                        guardadoCorrecto = false
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
            if(!datosPaso.consultaBancaria){
                datosPaso.consultaBancaria = [:]
            }
            datosPaso.consultaBancaria.depositoPromedio = (params.depositos ? params.depositos :null)
            datosPaso.consultaBancaria.retiroPromedio = (params.retiros ? params.retiros :null)
            datosPaso.consultaBancaria.saldoPromedio = (params.saldo ? params.saldo :null)
            datosPaso.consultaBancaria.login_id = params.login_id
            datosPaso.consultaBancaria.depositoCorrecto=(params.depositoCorrecto ? params.depositoCorrecto:null)
            datosPaso.consultaBancaria.retiroCorrecto=(params.retiroCorrecto ? params.retiroCorrecto:null)
            datosPaso.consultaBancaria.saldoCorrecto=(params.saldoCorrecto ? params.saldoCorrecto:null)
        } else if (pasoEnviado.tipoDePaso.nombre == "consultaBuro"){
            println "Entra a guardar datos del buro!!!"
            println params.tCredito
            println params.creditoA
            println params.creditoH
            println params.numeroTarjeta
            if(!datosPaso.consultaBuro) {
                datosPaso.consultaBuro = [:]
            }
            datosPaso.consultaBuro.tCredito = (params.tCredito ? params.tCredito : null)
            datosPaso.consultaBuro.creditoA = (params.creditoA ? params.creditoA : null)
            datosPaso.consultaBuro.creditoH = (params.creditoH ? params.creditoH : null)
            datosPaso.consultaBuro.numeroTarjeta = (params.numeroTarjeta ? params.numeroTarjeta : null)
            
            def parametroConsultaBuro
            def solicitud = SolicitudDeCredito.get(identificadores?.idSolicitud as long)
            if(identificadores?.idParametroConsultaBuro) {
                parametroConsultaBuro = ParametroConsultaBuro.get(identificadores?.idParametroConsultaBuro as long)
            } else {
                parametroConsultaBuro = new ParametroConsultaBuro()
            }
            parametroConsultaBuro.solicitud = solicitud
            parametroConsultaBuro.numeroDeTarjeta = datosPaso.consultaBuro.numeroTarjeta
            parametroConsultaBuro.tieneTarjeta = ((datosPaso.consultaBuro.tCredito == "SI") ? true : false )
            parametroConsultaBuro.tieneCreditoAutomotriz = ((datosPaso.consultaBuro.creditoA == "SI") ? true : false )
            parametroConsultaBuro.tieneCreditoHipotecario = ((datosPaso.consultaBuro.creditoH == "SI") ? true : false )
            if(parametroConsultaBuro.save(flush: true)) {
                println("Los parametros de consulta a buro se han registrado correctamente")
                datosPaso.consultaBuro.idParametroConsultaBuro = parametroConsultaBuro.id
            } else {
                if (parametroConsultaBuro.hasErrors()) {
                    parametroConsultaBuro.errors.allErrors.each {
                        println it
                    }
                }
            }
            
        } else if (pasoEnviado.tipoDePaso.nombre == "resumen"){
            if(identificadores?.idSolicitud && params.opcionMedioDeContacto && params.medioDeContacto) {
                def solicitud = SolicitudDeCredito.get(identificadores?.idSolicitud as long)
                solicitud.medioDeContacto = MedioDeContacto.get(params.medioDeContacto as long)
                solicitud.opcionMedioDeContacto = (params.opcionMedioDeContacto as long)
                solicitud.save(flush: true)
            }
        }
        datosPaso.guardadoCorrecto = guardadoCorrecto
        return datosPaso
    }
    
    def controlDeDocumentos(listaDeDoctos, tipoDeDocumentoEnviado){
        if(!listaDeDoctos){
            listaDeDoctos = [:]
            listaDeDoctos.comprobanteDeDomicilio = false
            listaDeDoctos.identificacion = false
        }
        if(tipoDeDocumentoEnviado == "reciboCfe" || tipoDeDocumentoEnviado == "reciboTelmex"){
            listaDeDoctos.comprobanteDeDomicilio = true
        } else if (tipoDeDocumentoEnviado == "Pasaportes" || tipoDeDocumentoEnviado == "Identicaciones"){
            listaDeDoctos.identificacion = true
        } else if(tipoDeDocumentoEnviado == "ComprobanteDeIngresos"){
            listaDeDoctos."$tipoDeDocumentoEnviado" = true
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
            documento.rutaDelArchivo = "/var/uploads/kosmos/documentos/" + solicitud.entidadFinanciera.nombre + "/" + solicitud.folio + "/" + archivo.nombreDelArchivo
            if(tipoDeDocumento == "reciboCfe"){
                documento.rutaDelArchivo += ".pdf"
                documento.tipoDeDocumento = TipoDeDocumento.get(1)
            } else if(tipoDeDocumento == "reciboTelmex"){
                documento.rutaDelArchivo += ".pdf"
                documento.tipoDeDocumento = TipoDeDocumento.get(10)
            } else if(tipoDeDocumento == "Identicaciones"){
                documento.tipoDeDocumento = TipoDeDocumento.get(2)
            } else if(tipoDeDocumento == "Pasaportes"){
                documento.tipoDeDocumento = TipoDeDocumento.get(11)
            } else {
                documento.tipoDeDocumento = TipoDeDocumento.findByNombreMapeo(tipoDeDocumento)
            }
            if(documento.save(flush:true)){
                def subdir = new File("/var/uploads/kosmos/documentos/" + solicitud.entidadFinanciera.nombre + "/" + solicitud.folio)
                subdir.mkdir()
                println (documento.rutaDelArchivo)
                if(tipoDeDocumento == "reciboCfe" || tipoDeDocumento == "reciboTelmex"){
                    def fis = new FileInputStream("/tmp/BCC_Doc" + solicitud.id + ".pdf")
                    def fos = new FileOutputStream(documento.rutaDelArchivo)
                    fos << fis
                    fis.close()
                    fos.close()
                } else {
                    File file = new File(documento.rutaDelArchivo)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                }
                respuesta.idArchivo = documento.id
                respuesta.exito = true
                respuesta.mensaje = "El archivo se ha registrado exitosamente."
            } else {
                respuesta.nombreArchivo = archivo.nombreDelArchivo
                respuesta.exito = false
                respuesta.mensaje = "Ocurrio un error al registrar el documento. Intentelo nuevamente."
            }
        }
        return respuesta 
    }
    
    def generarFolioAlfanumerico(def longitud){
        int randomStringLength = longitud
        String charset = (('a'..'k') + ('m'..'z') + ('A'..'H') + ('J'..'N') + ('P'..'Z') + ('0'..'9')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        randomString
    }
    
    def guardarDocumentoTemporal(def archivo, def tipoDeDocumento, def ephesoft){
        def respuesta = null
        if(archivo){
            def documento = new DocumentoTemporal()
            documento.fechaDeSubida = new Date()
            if(tipoDeDocumento == "reciboCfe"){
                documento.tipoDeDocumento = TipoDeDocumento.get(1)
            } else if(tipoDeDocumento == "reciboTelmex"){
                documento.tipoDeDocumento = TipoDeDocumento.get(10)
            } else if(tipoDeDocumento == "Identicaciones"){
                documento.tipoDeDocumento = TipoDeDocumento.get(2)
            } else if(tipoDeDocumento == "Pasaportes"){
                documento.tipoDeDocumento = TipoDeDocumento.get(11)
            }
            documento.rutaDelArchivo = "/var/uploads/kosmos/temporales" + "/" + archivo.nombreDelArchivo + (ephesoft ? ".pdf" : "")
            if(documento.save(flush:true)){
                def subdir = new File("/var/uploads/kosmos/temporales/")
                subdir.mkdir()
                println (documento.rutaDelArchivo)
                if(ephesoft){
                    println "Moviendo documento temporal de Ephesoft..."
                    def fis = new FileInputStream("/tmp/" + (ephesoft ? "BCC_Doc0" + generarFolioAlfanumerico(2) +".pdf" : archivo.nombreDelArchivo ))
                    def fos = new FileOutputStream(documento.rutaDelArchivo)
                    fos << fis
                    fis.close()
                    fos.close()
                } else {
                    File file = new File(documento.rutaDelArchivo)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << archivo.archivo
                        }
                    }
                }
                respuesta = documento.id
            } else {
                respuesta = null
            }
        }
        return respuesta 
    }
    
    def moverDocumento(def archivoTemporalId, def solicitudId){
        def respuesta = false
        def documentoTemporal = DocumentoTemporal.get(archivoTemporalId as long)
        if(documentoTemporal){
            def solicitud =  SolicitudDeCredito.get(solicitudId as long)
            def documento = new DocumentoSolicitud()
            def nombreDelArchivo = (documentoTemporal.rutaDelArchivo).replace("/var/uploads/kosmos/temporales/","")
            documento.fechaDeSubida = new Date()
            documento.solicitud = solicitud
            documento.rutaDelArchivo = "/var/uploads/kosmos/documentos/" + solicitud.entidadFinanciera.nombre + "/" + solicitud.folio + "/" + nombreDelArchivo
            documento.tipoDeDocumento = documentoTemporal.tipoDeDocumento
            if(documento.save(flush:true)){
                def subdir = new File("/var/uploads/kosmos/documentos/" + solicitud.entidadFinanciera.nombre + "/" + solicitud.folio)
                subdir.mkdir()
                println ("Moviendo el documento de: " + documentoTemporal.rutaDelArchivo + " a: " + documento.rutaDelArchivo)
                def fis = new FileInputStream(documentoTemporal.rutaDelArchivo)
                def fos = new FileOutputStream(documento.rutaDelArchivo)
                fos << fis
                fis.close()
                fos.close()
                respuesta = true
            } else {
                respuesta = false
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
                productoSolicitud.producto = Producto.get(datosCotizador.producto as long)
                productoSolicitud.modelo = Modelo.get(datosCotizador.modelo as long)
                productoSolicitud.colorModelo = ColorModelo.get(datosCotizador.color as long)
                productoSolicitud.seguro = SeguroProducto.get(datosCotizador.seguro as long)
            }
            productoSolicitud.enganche = datosCotizador.enganche as float
            productoSolicitud.periodicidad = Periodicidad.get(datosCotizador.periodo as long)
            productoSolicitud.plazos = datosCotizador.plazo as int
            productoSolicitud.solicitud = solicitud
            if(productoSolicitud.save(flush:true)){
                println("El producto se ha registrado correctamente")
                return productoSolicitud.id
            } else {
                println("[Guardado-ProductoSolicitud] No se guardo nada")
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
    
    def registrarSolicitudTemporal (def datosCotizador, def token, def shortUrl, def entidadFinanciera){
        println "Datos del Cotizador: " + datosCotizador
        def respuesta = null
        if(datosCotizador && token && shortUrl){
            def solicitud =  new SolicitudTemporal()
            if(datosCotizador.rubro){
                solicitud.rubroDeAplicacion = RubroDeAplicacionDeCredito.get(datosCotizador.rubro as long)
                solicitud.producto = Producto.get(datosCotizador.producto as long)
                solicitud.documentoElegido = TipoDeDocumento.get(datosCotizador.documento as long);
                solicitud.montoDelCredito = datosCotizador.montoCredito as float
                solicitud.montoDelSeguroDeDeuda = datosCotizador.montoSeguro as float
                solicitud.montoDelPago = datosCotizador.pagos as float
                solicitud.haTenidoAtrasos = datosCotizador.atrasos
                solicitud.seguroFinanciado = true
                solicitud.nombreDelCliente = datosCotizador.nombreCliente
                solicitud.emailCliente = datosCotizador.emailCliente
                solicitud.telefonoCliente = datosCotizador.telefonoCliente
            } else {
                solicitud.montoDelPago = datosCotizador.pagos as float
                solicitud.montoDelSeguroDeDeuda = 0
                solicitud.colorModelo = ColorModelo.get(datosCotizador.color)
                solicitud.seguroFinanciado = false
                solicitud.montoDelCredito = (productoSolicitud.colorModelo.modelo.precio - productoSolicitud.enganche)
                solicitud.producto = Producto.get(datosCotizador.producto as long)
                solicitud.modelo = Modelo.get(datosCotizador.modelo as long)
                solicitud.colorModelo = ColorModelo.get(datosCotizador.color as long)
                solicitud.seguro = SeguroProducto.get(datosCotizador.seguro as long)
            }
            solicitud.fechaDeSolicitud = new Date()
            solicitud.entidadFinanciera = entidadFinanciera
            solicitud.token = token
            solicitud.shortUrl = shortUrl
            solicitud.enganche = datosCotizador.enganche as float
            solicitud.periodicidad = Periodicidad.get(datosCotizador.periodo as long)
            solicitud.plazos = datosCotizador.plazo as int
            solicitud.folio = generarFolioAlfanumerico(7);
            while(verificarExistenciaDeFolio(solicitud.folio, solicitud.entidadFinanciera, 0)) {
                solicitud.folio = generarFolioAlfanumerico(7);
            }
            if(solicitud.save(flush:true)){
                println("La solicitud temporal se ha registrado correctamente")
                return solicitud.id
            } else {
                println("No se guardo nada")
                if (solicitud.hasErrors()) {
                    solicitud.errors.allErrors.each {
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
        def folioSolicitud
        def query = "SELECT s FROM SolicitudDeCredito s WHERE s.entidadFinanciera.id = " + auth.entidadFinanciera.id
        if(tipoDeConsulta && (tipoDeConsulta as int) == 0) {
            query += " AND s.statusDeSolicitud.id NOT IN (1,2,3)"
        } else if (tipoDeConsulta && (tipoDeConsulta as int) == 1) {
            query += " AND s.statusDeSolicitud.id IN (1,2,3)" 
        }
        
        try {
            folioSolicitud = (folio as int)
        } catch(Exception e){
            folioSolicitud = folio
        }
        
        switch(opcion){
        case 1: //obtenerUltimasSolicitudes / index
            query += " AND s.solicitudEnviada = false"
            break
        case 2: //obtenerSolicitudesPorfecha / list
            query += " AND s.fechaDeSolicitud BETWEEN TO_TIMESTAMP('" + fechaInicio + " 00:00','dd/mm/yyyy hh24:mi') AND TO_TIMESTAMP('" + fechaFinal + " 23:59','dd/mm/yyyy hh24:mi')"     
            break
        case 3: //obtenerSolicitudPorFolio / show
            query += " AND s.folio = '" + folioSolicitud + "' " 
            break
        default:
            break
        }        
        println "Query: " + query
        def resultados = SolicitudDeCredito.executeQuery(query)
        resultados.each { solicitud ->
            
            def telefonos
            def emails
            def datosSolicitud = [:]
            datosSolicitud.productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud)
            datosSolicitud.direccionCliente = DireccionCliente.findWhere(cliente: solicitud.cliente, vigente: true)
            datosSolicitud.empleoCliente = EmpleoCliente.findWhere(cliente: solicitud.cliente)
            datosSolicitud.telefonosCliente = TelefonoCliente.findAllWhere(cliente: solicitud.cliente, vigente: true)
            datosSolicitud.emailCliente = EmailCliente.findAllWhere(cliente: solicitud.cliente, vigente: true)
            datosSolicitud.documentosSolicitud = DocumentoSolicitud.findAllWhere(solicitud: solicitud)
            datosSolicitud.resultadoMotorDeDecision = ResultadoMotorDeDecision.findWhere(solicitud: solicitud)
            
            if(datosSolicitud.telefonosCliente){
                telefonos = [:]
                datosSolicitud.telefonosCliente.each{
                    if(it.tipoDeTelefono.id == 1) {
                        telefonos.telefonoCasa = it.numeroTelefonico
                    } else if(it.tipoDeTelefono.id == 2) {
                        telefonos.telefonoCelular = it.numeroTelefonico
                    }
                }
            }
            
            if(datosSolicitud.emailCliente){
                emails = [:]
                datosSolicitud.emailCliente.each{
                    if(it.tipoDeEmail.id == 1) {
                        emails.emailPersonal = it.direccionDeCorreo
                    } else if(it.tipoDeEmail.id == 2) {
                        emails.emailLaboral = it.direccionDeCorreo
                    }
                }
            }
            
            def datosBuroDeCredito = [:]
            datosBuroDeCredito.reporte = solicitud.reporteBuroCredito
            datosBuroDeCredito.consultas = ConsultasBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.creditos = CreditoClienteBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.direcciones = DireccionBuroDeCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.empleos = EmpleoBuroDeCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.refCred = RefCredBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.sintetiza = SintetizaBuroCredito.findAllWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.resumen = ResumenBuroCredito.findWhere(reporteBuroCredito: solicitud.reporteBuroCredito)
            datosBuroDeCredito.score = ScoreBuroCredito.findWhere(reporteBuroCredito: solicitud.reporteBuroCredito, codigoScore: "007")
            
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
            solicitudRest.solicitud.buroDeCredito = ""
        
            solicitudRest.solicitud.datosSolicitud.fechaDeCreacion = (solicitud.fechaDeSolicitud).format('dd/MM/yyyy HH:mm')
            solicitudRest.solicitud.datosSolicitud.usuarioQueRegistro = "Solicitante" //Temporal
            solicitudRest.solicitud.datosSolicitud.status = solicitud.statusDeSolicitud.nombre
            solicitudRest.solicitud.datosSolicitud.folio = ("" + solicitud.folio).padLeft(6, '0')
            solicitudRest.solicitud.datosSolicitud.puntoDeVenta = (solicitud.sucursal ? solicitud.sucursal.nombre : "")
            solicitudRest.solicitud.datosSolicitud.puntajeScore = (datosBuroDeCredito.score ? (datosBuroDeCredito.score as int) : 0)
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore = "" //[:]
            /*if(datosSolicitud.resultadoMotorDeDecision) {
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.dictamenCapacidadDePago = (datosSolicitud.resultadoMotorDeDecision.dictamenCapacidadDePago ?: "")
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.dictamenConjunto = (datosSolicitud.resultadoMotorDeDecision.dictamenConjunto ?: "")
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.dictamenDePerfil = (datosSolicitud.resultadoMotorDeDecision.dictamenDePerfil ?: "")
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.dictamenDePoliticas = (datosSolicitud.resultadoMotorDeDecision.dictamenDePoliticas ?: "")
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.dictamenFinal = (datosSolicitud.resultadoMotorDeDecision.dictamenFinal ?: "")
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.probabilidadDeMora = (datosSolicitud.resultadoMotorDeDecision.probabilidadDeMora ?: 0)
            solicitudRest.solicitud.datosSolicitud.resultadoDelScore.razonDeCobertura = (datosSolicitud.resultadoMotorDeDecision.razonDeCobertura ?: 0)
            }*/
            solicitudRest.solicitud.datosSolicitud.estadoDeDictaminacion = ""//(tipoDeConsulta || tipoDeConsulta == 0 ?"Autorizado" : "")
            solicitudRest.solicitud.datosSolicitud.usuarioDictaminador = ""//(tipoDeConsulta || tipoDeConsulta == 0 ? "Usuario Dictaminador" : "")
            solicitudRest.solicitud.datosSolicitud.fechaDeDictaminacion = ""//(tipoDeConsulta || tipoDeConsulta == 0 ? (new Date()).format('dd/MM/yyyy HH:mm') : "")
        
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
            
            def primerNombre
            def segundoNombre
            def nombreCompleto = solicitud.cliente.nombre?.split()
            primerNombre = nombreCompleto?.getAt(0)
            if(nombreCompleto.size() > 1) {
                nombreCompleto = nombreCompleto[1..nombreCompleto.size()-1]
                segundoNombre = nombreCompleto.join(' ')
            }
            
            solicitudRest.solicitud.generales.nombre = primerNombre?.toUpperCase()
            solicitudRest.solicitud.generales.segundoNombre = (segundoNombre ?: "")?.toUpperCase()
            solicitudRest.solicitud.generales.apellidoPaterno = solicitud.cliente.apellidoPaterno?.toUpperCase()
            solicitudRest.solicitud.generales.apellidoMaterno = solicitud.cliente.apellidoMaterno?.toUpperCase()
            solicitudRest.solicitud.generales.numeroCelular = (telefonos?.telefonoCelular ?: "")
            solicitudRest.solicitud.generales.numeroFijo = (telefonos?.telefonoCasa ?: "")
            solicitudRest.solicitud.generales.correoElectronico = (emails?.emailPersonal ?: "")
            solicitudRest.solicitud.generales.sexo = String.valueOf(solicitud.cliente.genero.clave)
            solicitudRest.solicitud.generales.fechaDeNacimiento = (solicitud.cliente.fechaDeNacimiento).format('dd/MM/yyyy HH:mm')
            solicitudRest.solicitud.generales.lugarDeNacimiento = solicitud.cliente?.lugarDeNacimiento?.numeroOficial
            solicitudRest.solicitud.generales.nacionalidad = solicitud.cliente.nacionalidad?.nombre
            solicitudRest.solicitud.generales.rfc = solicitud.cliente.rfc
            solicitudRest.solicitud.generales.curp = solicitud.cliente.curp
            solicitudRest.solicitud.generales.estadoCivil = String.valueOf(solicitud.cliente.estadoCivil?.clave)
            solicitudRest.solicitud.generales.regimenDeBienes = ((solicitud.cliente.regimenMatrimonial) ? String.valueOf(solicitud.cliente.regimenMatrimonial.clave) : "")
            solicitudRest.solicitud.generales.dependientesEconomicos = solicitud.cliente.dependientesEconomicos
            
            solicitudRest.solicitud.conyugue.nombre = ((solicitud.cliente.nombreDelConyugue) ? solicitud.cliente.nombreDelConyugue : "")?.trim()?.toUpperCase()
            solicitudRest.solicitud.conyugue.segundoNombre = ""
            solicitudRest.solicitud.conyugue.apellidoPaterno = ((solicitud.cliente.apellidoPaternoDelConyugue) ? solicitud.cliente.apellidoPaternoDelConyugue : "")?.trim()?.toUpperCase()
            solicitudRest.solicitud.conyugue.apellidoMaterno = ((solicitud.cliente.apellidoMaternoDelConyugue) ? solicitud.cliente.apellidoMaternoDelConyugue : "")?.trim()?.toUpperCase()
            solicitudRest.solicitud.conyugue.fechaDeNacimiento = ((solicitud.cliente.fechaDeNacimientoDelConyugue) ? (solicitud.cliente.fechaDeNacimientoDelConyugue).format('dd/MM/yyyy HH:mm') : "")
            solicitudRest.solicitud.conyugue.lugarDeNacimiento = ((solicitud.cliente.lugarDeNacimientoDelConyugue) ? solicitud.cliente.lugarDeNacimientoDelConyugue.id : "")
            solicitudRest.solicitud.conyugue.nacionalidad = ((solicitud.cliente.nacionalidadDelConyugue) ? solicitud.cliente.nacionalidadDelConyugue.nombre : "")
            solicitudRest.solicitud.conyugue.rfc = ((solicitud.cliente.rfcDelConyugue) ? solicitud.cliente.rfcDelConyugue : "")
            solicitudRest.solicitud.conyugue.curp = ((solicitud.cliente.curpDelConyugue) ? solicitud.cliente.curpDelConyugue : "")
            
            solicitudRest.solicitud.direccion.calle = ((datosSolicitud.direccionCliente?.calle) ? datosSolicitud.direccionCliente.calle : "")
            solicitudRest.solicitud.direccion.numeroExterior = ((datosSolicitud.direccionCliente?.numeroExterior) ? datosSolicitud.direccionCliente?.numeroExterior : "")
            solicitudRest.solicitud.direccion.numeroInterior = ((datosSolicitud.direccionCliente?.numeroInterior) ? datosSolicitud.direccionCliente?.numeroInterior : "")
            solicitudRest.solicitud.direccion.codigoPostal = ((datosSolicitud.direccionCliente?.codigoPostal?.codigo) ? datosSolicitud.direccionCliente?.codigoPostal?.codigo : "")
            solicitudRest.solicitud.direccion.colonia = ((datosSolicitud.direccionCliente?.colonia) ? datosSolicitud.direccionCliente?.colonia : "")
            solicitudRest.solicitud.direccion.municipio = ((datosSolicitud.direccionCliente?.codigoPostal?.municipio) ? datosSolicitud.direccionCliente?.codigoPostal?.municipio?.id : "")
            solicitudRest.solicitud.direccion.ciudad = ((datosSolicitud.direccionCliente?.ciudad) ? datosSolicitud.direccionCliente?.ciudad : "")
            solicitudRest.solicitud.direccion.estado = ((datosSolicitud.direccionCliente?.codigoPostal?.municipio?.estado) ? datosSolicitud.direccionCliente?.codigoPostal?.municipio?.estado?.numeroOficial : "")
            
            solicitudRest.solicitud.vivienda.tipoDeVivienda = ((datosSolicitud.direccionCliente?.tipoDeVivienda) ? String.valueOf(datosSolicitud.direccionCliente.tipoDeVivienda.clave) : "")
            solicitudRest.solicitud.vivienda.montoDeRenta = (datosSolicitud.direccionCliente?.montoDeLaRenta ?: 0)
            def periodoVivienda = (datosSolicitud.direccionCliente?.tiempoDeVivienda)?.split("/");
            solicitudRest.solicitud.vivienda.mesInicioVivienda = (periodoVivienda ? periodoVivienda.getAt(0) : "")
            solicitudRest.solicitud.vivienda.anioInicioVivienda = (periodoVivienda ? periodoVivienda.getAt(1) : "")
            def periodoResidencia = (datosSolicitud.direccionCliente?.tiempoDeEstadia)?.split("/");
            solicitudRest.solicitud.vivienda.mesInicioResidencia = (periodoResidencia ? periodoResidencia.getAt(0) : "")
            solicitudRest.solicitud.vivienda.anioInicioResidencia = (periodoResidencia ? periodoResidencia.getAt(1) : "")
            
            solicitudRest.solicitud.empleo.profesion = ((datosSolicitud.empleoCliente?.profesion) ? datosSolicitud.empleoCliente?.profesion?.codigo : "")
            solicitudRest.solicitud.empleo.ocupacion = ((datosSolicitud.empleoCliente?.ocupacion) ? datosSolicitud.empleoCliente?.ocupacion?.codigo : "")
            solicitudRest.solicitud.empleo.empresa = ((datosSolicitud.empleoCliente?.nombreDeLaEmpresa) ? datosSolicitud.empleoCliente?.nombreDeLaEmpresa : "")
            def fechaIngreso = (datosSolicitud.empleoCliente?.fechaIngreso)?.split("/");
            solicitudRest.solicitud.empleo.mesInicioEmpleo = (fechaIngreso ? fechaIngreso.getAt(0) : "")
            solicitudRest.solicitud.empleo.anioInicioEmpleo = (fechaIngreso ? fechaIngreso.getAt(1) : "")
            solicitudRest.solicitud.empleo.ingresosFijos = ((datosSolicitud.empleoCliente?.ingresosFijos) ? datosSolicitud.empleoCliente?.ingresosFijos : 0)
            solicitudRest.solicitud.empleo.ingresosVariables = ((datosSolicitud.empleoCliente?.ingresosVariables) ? datosSolicitud.empleoCliente?.ingresosVariables : 0)
            solicitudRest.solicitud.empleo.ingresosTotales = solicitudRest.solicitud.empleo.ingresosFijos + solicitudRest.solicitud.empleo.ingresosVariables
            solicitudRest.solicitud.empleo.gastosMensuales = ((datosSolicitud.empleoCliente?.gastos) ? datosSolicitud.empleoCliente?.gastos : 0)
            
            //solicitudRest.solicitud.documentos << [tipoDeDocumento: "Comprobante De Domicilio", contenidoBase64: "TEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOIC0gTEEgSU1BR0VOLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4gLSBMQSBJTUFHRU4="]
            
            datosSolicitud.documentosSolicitud?.each { documento ->
                try{
                    def mapaDocto = [:]
                    mapaDocto.tipoDeDocumento = documento.tipoDeDocumento.codigo
                    mapaDocto.contenidoBase64 = generarBase64(new File(documento.rutaDelArchivo))
                    solicitudRest.solicitud.documentos << mapaDocto
                }catch(Exception e){
                    println ("[REST] Excepción ocurrida: " + e.getMessage())
                    println "[REST] Ocurrio un problema con el archivo con ruta $documento.rutaDelArchivo, verifiquelo por favor..."
                }
            }
            
            if(!datosBuroDeCredito?.reporte || datosBuroDeCredito?.reporte?.tipoErrorBuroCredito) {
                solicitudRest.solicitud.buroDeCredito = ""
            } else {
                def bitacoraDeBuro = BitacoraBuroCredito.executeQuery("Select b from BitacoraBuroCredito b Where b.solicitud.id = " + solicitud.id + "  Order by b.fechaRespuesta desc")
            
                if(bitacoraDeBuro){
                    solicitudRest.solicitud.buroDeCredito = buroDeCreditoService.generarCadenaBC(bitacoraDeBuro.getAt(0)?.respuesta)
                }
            }
            
            /*solicitudRest.solicitud.buroDeCredito.apellidoPaterno = (datosBuroDeCredito.reporte?.apellidoPaterno ? datosBuroDeCredito.reporte?.apellidoPaterno : "")
            solicitudRest.solicitud.buroDeCredito.apellidoMaterno = (datosBuroDeCredito.reporte?.apellidoMaterno ? datosBuroDeCredito.reporte?.apellidoMaterno : "")
            solicitudRest.solicitud.buroDeCredito.apellidoAdicional = (datosBuroDeCredito.reporte?.apellidoAdicional ? datosBuroDeCredito.reporte?.apellidoAdicional : "")
            solicitudRest.solicitud.buroDeCredito.primerNombre = (datosBuroDeCredito.reporte?.primerNombre ? datosBuroDeCredito.reporte?.primerNombre : "")
            solicitudRest.solicitud.buroDeCredito.segundoNombre = (datosBuroDeCredito.reporte?.segundoNombre ? datosBuroDeCredito.reporte?.segundoNombre : "")
            solicitudRest.solicitud.buroDeCredito.fechaDeNacimiento = (datosBuroDeCredito.reporte?.fechaNacimiento ? datosBuroDeCredito.reporte?.fechaNacimiento : "")
            solicitudRest.solicitud.buroDeCredito.rfc = (datosBuroDeCredito.reporte?.rfc ? datosBuroDeCredito.reporte?.rfc : "")
            solicitudRest.solicitud.buroDeCredito.prefijoProfesional = (datosBuroDeCredito.reporte?.prefijoProfesinal ? datosBuroDeCredito.reporte?.prefijoProfesinal : "")
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
            def mapaDireccion = [:]
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
            solicitudRest.solicitud.buroDeCredito.direcciones << mapaDireccion
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
             */
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
    
    def construirDatosMotorDeDecision(def identificadores){
        def datos = [:]
        println ("Identificadores: " + identificadores)
        def solicitud = SolicitudDeCredito.get(identificadores.idSolicitud)
        def productoSolicitud = ProductoSolicitud.get(identificadores.idProductoSolicitud)
        def direccion = DireccionCliente.get(identificadores.idDireccion)
        def empleo = EmpleoCliente.get(identificadores.idEmpleo)
        def bitacoraDeBuro = BitacoraBuroCredito.executeQuery("Select b from BitacoraBuroCredito b Where b.solicitud.id = " + solicitud.id + "  Order by b.fechaRespuesta desc")
        datos.solicitudId = solicitud.id
        datos.riesgoGeografico = solicitud.sucursal?.riesgoGeografico?.nombre?.toUpperCase()
        datos.plazo = productoSolicitud.plazos
        datos.periodicidad = productoSolicitud.periodicidad.nombre.toUpperCase()
        datos.riesgoOcupacion = empleo.ocupacion.riesgoDeOcupacion?.nombre?.toUpperCase()
        datos.edad = calcularTiempoTranscurrido(solicitud.cliente.fechaDeNacimiento)
        datos.estadoCivil = solicitud.cliente.estadoCivil.nombre.toUpperCase()
        datos.productoServicio = productoSolicitud.producto.claveDeProducto
        datos.antiguedadVivienda = calcularTiempoTranscurrido(new Date().parse("dd/MM/yyyy", ("01/" + direccion.tiempoDeVivienda)))
        datos.ingresosFijosMensuales = new Double(empleo.ingresosFijos)
        datos.ingresosVariablesMensuales = new Double(empleo.ingresosVariables)
        datos.otrosIngresos = new Double(0)
        datos.dependientesEconomicos = solicitud.cliente.dependientesEconomicos
        datos.gastoRenta = new Double(direccion.montoDeLaRenta)
        datos.cuotaMensualCredito = new Double(productoSolicitud.montoDelPago)
        datos.tipoDeVivienda = direccion.tipoDeVivienda.id.intValue()
        datos.asalariado = (productoSolicitud.documentoElegido.tipoDeIngresos.id == 1 ? true : false)
        if(bitacoraDeBuro){
            datos.cadenaBuroDeCredito = buroDeCreditoService.generarCadenaBC(bitacoraDeBuro.getAt(0)?.respuesta)
        } else {
            datos.cadenaBuroDeCredito = ""
        }
        println "Regresando: " + datos
        datos
    }
    
    def calcularTiempoTranscurrido(def fechaAComparar){
        def aniosTranscurridos = 0
        use(groovy.time.TimeCategory) {
            def duration = (new Date()) - fechaAComparar
            println("Han transcurrido ${duration.years}...")
            aniosTranscurridos = duration.years
        }
        aniosTranscurridos
    }
    
    def continuarSolicitud(def solicitud){
        def datosSolicitud = [:]
        def cliente = solicitud.cliente
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: solicitud.entidadFinanciera)
        def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud)
        def direccionCliente = DireccionCliente.findWhere(cliente: solicitud.cliente, vigente: true)
        def empleoCliente = EmpleoCliente.findWhere(cliente: solicitud.cliente)
        def telefonosCliente = TelefonoCliente.findAllWhere(cliente: solicitud.cliente, vigente: true)
        def emailCliente = EmailCliente.findAllWhere(cliente: solicitud.cliente, vigente: true)
        def documentosSolicitud = DocumentoSolicitud.findAllWhere(solicitud: solicitud)
        def consultaBuro = ParametroConsultaBuro.findWhere(solicitud: solicitud)
        
        datosSolicitud.identificadores = [:]
        datosSolicitud.pasoFormulario = [:]
        datosSolicitud.cotizador = [:]
        datosSolicitud.token = solicitud.token
        datosSolicitud.shortUrl = solicitud.shortUrl
        datosSolicitud.ultimoPaso = solicitud.ultimoPaso
        datosSolicitud.entidadFinanciera = solicitud.entidadFinanciera
        datosSolicitud.configuracion = configuracion
        datosSolicitud.identificadores.idSolicitud = solicitud.id
        datosSolicitud.identificadores.idCliente = cliente.id
        datosSolicitud.identificadores.idProductoSolicitud = productoSolicitud.id
        
        datosSolicitud.llenado = [:]
        if(cliente){
            datosSolicitud.llenado.paso1 = true
        }
        if(direccionCliente){
            datosSolicitud.llenado.paso2 = true
        }
        if(empleoCliente){
            datosSolicitud.llenado.paso3 = true
        }
        
        datosSolicitud.pasoFormulario.cliente = [:]
        datosSolicitud.pasoFormulario.cliente.nombre = cliente.nombre
        datosSolicitud.pasoFormulario.cliente.apellidoPaterno = cliente.apellidoPaterno
        datosSolicitud.pasoFormulario.cliente.apellidoMaterno =  cliente.apellidoMaterno
        datosSolicitud.pasoFormulario.cliente.lugarDeNacimiento = cliente.lugarDeNacimiento.id
        datosSolicitud.pasoFormulario.cliente.nacionalidad = cliente.nacionalidad.id
        datosSolicitud.pasoFormulario.cliente.fechaDeNacimiento = [:]
        datosSolicitud.pasoFormulario.cliente.fechaDeNacimiento.dia = (cliente.fechaDeNacimiento.format("dd") as int)
        datosSolicitud.pasoFormulario.cliente.fechaDeNacimiento.mes = (cliente.fechaDeNacimiento.format("MM") as int)
        datosSolicitud.pasoFormulario.cliente.fechaDeNacimiento.anio = (cliente.fechaDeNacimiento.format("yyyy") as int)
        datosSolicitud.pasoFormulario.cliente.curp = cliente.curp
        datosSolicitud.pasoFormulario.cliente.genero = cliente.genero.id
        datosSolicitud.pasoFormulario.cliente.rfc = cliente.rfc
        datosSolicitud.pasoFormulario.cliente.estadoCivil = (cliente.estadoCivil ? cliente.estadoCivil.id : null)
        datosSolicitud.pasoFormulario.cliente.nivelEducativo = (cliente.nivelEducativo ? cliente.nivelEducativo.id : null)
        datosSolicitud.pasoFormulario.cliente.dependientesEconomicos = (cliente.dependientesEconomicos ? cliente.dependientesEconomicos as int : 0)
        datosSolicitud.pasoFormulario.cliente.nombreDelConyugue = cliente.nombreDelConyugue
        datosSolicitud.pasoFormulario.cliente.regimenMatrimonial = (cliente.regimenMatrimonial ? cliente.regimenMatrimonial.id : null)
        datosSolicitud.pasoFormulario.cliente.apellidoPaternoDelConyugue = cliente.apellidoPaternoDelConyugue
        datosSolicitud.pasoFormulario.cliente.apellidoMaternoDelConyugue = cliente.apellidoMaternoDelConyugue
        
        datosSolicitud.cotizador.rubro = (productoSolicitud.rubroDeAplicacion ? productoSolicitud.rubroDeAplicacion.id : null)
        datosSolicitud.cotizador.producto = productoSolicitud.producto.id
        datosSolicitud.cotizador.documento = (productoSolicitud.documentoElegido ? productoSolicitud.documentoElegido.id : null)
        datosSolicitud.cotizador.montoCredito = productoSolicitud.montoDelCredito
        datosSolicitud.cotizador.montoSeguro = productoSolicitud.montoDelSeguroDeDeuda
        datosSolicitud.cotizador.pagos = productoSolicitud.montoDelPago
        datosSolicitud.cotizador.atrasos = productoSolicitud.haTenidoAtrasos
        datosSolicitud.cotizador.color = (productoSolicitud.colorModelo ? productoSolicitud.colorModelo.id : null)
        datosSolicitud.cotizador.enganche = productoSolicitud.enganche
        datosSolicitud.cotizador.periodo = (productoSolicitud.periodicidad ? productoSolicitud.periodicidad.id : null)
        datosSolicitud.cotizador.plazo = productoSolicitud.plazos
        
        if(cliente.fechaDeNacimientoDelConyugue){
            datosSolicitud.pasoFormulario.cliente.fechaDeNacimientoDelConyugue = [:]
            datosSolicitud.pasoFormulario.cliente.fechaDeNacimientoDelConyugue.dia = (cliente.fechaDeNacimientoDelConyugue.format("dd") as int)
            datosSolicitud.pasoFormulario.cliente.fechaDeNacimientoDelConyugue.mes = (cliente.fechaDeNacimientoDelConyugue.format("MM") as int)
            datosSolicitud.pasoFormulario.cliente.fechaDeNacimientoDelConyugue.anio = (cliente.fechaDeNacimientoDelConyugue.format("yyyy") as int)
        }
        datosSolicitud.pasoFormulario.cliente.rfcDelConyugue = cliente.rfcDelConyugue
        datosSolicitud.pasoFormulario.cliente.curpDelConyugue = cliente.curpDelConyugue
        datosSolicitud.pasoFormulario.cliente.lugarDeNacimientoDelConyugue = (cliente.lugarDeNacimientoDelConyugue ? cliente.lugarDeNacimientoDelConyugue.id : null)
        datosSolicitud.pasoFormulario.cliente.nacionalidadDelConyugue = (cliente.nacionalidadDelConyugue ? cliente.nacionalidadDelConyugue.id : null )
        if(telefonosCliente){
            datosSolicitud.pasoFormulario.telefonoCliente = [:]
            telefonosCliente.each{
                if(it.tipoDeTelefono.id == 1) {
                    datosSolicitud.pasoFormulario.telefonoCliente.telefonoCasa = it.numeroTelefonico
                } else if(it.tipoDeTelefono.id == 2) {
                    datosSolicitud.pasoFormulario.telefonoCliente.telefonoCelular = it.numeroTelefonico
                }
            }
        }
        if(direccionCliente) {
            datosSolicitud.identificadores.idDireccion = direccionCliente.id
            datosSolicitud.pasoFormulario.direccionCliente = [:]
            datosSolicitud.pasoFormulario.direccionCliente.calle = direccionCliente.calle
            datosSolicitud.pasoFormulario.direccionCliente.ciudad = direccionCliente.ciudad
            datosSolicitud.pasoFormulario.direccionCliente.numeroExterior = direccionCliente.numeroExterior
            datosSolicitud.pasoFormulario.direccionCliente.numeroInterior = direccionCliente.numeroInterior
            datosSolicitud.pasoFormulario.direccionCliente.codigoPostal = direccionCliente.codigoPostal?.codigo
            datosSolicitud.pasoFormulario.direccionCliente.colonia = direccionCliente.colonia 
            datosSolicitud.pasoFormulario.direccionCliente.delegacion = direccionCliente.codigoPostal?.municipio?.id
            datosSolicitud.pasoFormulario.direccionCliente.estado = direccionCliente.codigoPostal?.municipio?.estado?.id
            datosSolicitud.pasoFormulario.direccionCliente.tipoDeVivienda = direccionCliente.tipoDeVivienda.id
            datosSolicitud.pasoFormulario.direccionCliente.temporalidad = (direccionCliente.temporalidad ? direccionCliente.temporalidad.id : null)
            datosSolicitud.pasoFormulario.direccionCliente.tiempo = direccionCliente.tiempoDeResidencia
            datosSolicitud.pasoFormulario.direccionCliente.sucursal = productoSolicitud.solicitud?.sucursal?.id
            datosSolicitud.pasoFormulario.direccionCliente.montoDeLaRenta = (direccionCliente.montoDeLaRenta ? direccionCliente.montoDeLaRenta : 0)
            if(direccionCliente.tiempoDeEstadia){
                def tiempoDeEstadia = direccionCliente.tiempoDeEstadia.split("/")
                datosSolicitud.pasoFormulario.direccionCliente.tiempoDeResidencia = [:]
                datosSolicitud.pasoFormulario.direccionCliente.tiempoDeResidencia?.mes = (tiempoDeEstadia[0] as int)
                datosSolicitud.pasoFormulario.direccionCliente.tiempoDeResidencia?.anio = (tiempoDeEstadia[1] as int)
            }
            if(direccionCliente.tiempoDeVivienda){
                def tiempoDeVivienda = direccionCliente.tiempoDeVivienda.split("/")
                datosSolicitud.pasoFormulario.direccionCliente.tiempoDeVivir = [:]
                datosSolicitud.pasoFormulario.direccionCliente.tiempoDeVivir?.mes = (tiempoDeVivienda[0] as int)
                datosSolicitud.pasoFormulario.direccionCliente.tiempoDeVivir?.anio = (tiempoDeVivienda[1] as int)
            }
        }
        if(empleoCliente){
            datosSolicitud.identificadores.idEmpleo = empleoCliente.id
            datosSolicitud.pasoFormulario.empleoCliente = [:]
            datosSolicitud.pasoFormulario.empleoCliente.puesto = empleoCliente.puesto
            datosSolicitud.pasoFormulario.empleoCliente.actividad = empleoCliente.actividad
            datosSolicitud.pasoFormulario.empleoCliente.explicacionActividad = empleoCliente.explicacionActividad
            datosSolicitud.pasoFormulario.empleoCliente.profesion = (empleoCliente.profesion ? empleoCliente.profesion.id : null)
            datosSolicitud.pasoFormulario.empleoCliente.contrato = (empleoCliente.tipoDeContrato ? empleoCliente.tipoDeContrato.id : null)
            datosSolicitud.pasoFormulario.empleoCliente.giroEmpresarial = (empleoCliente.giroEmpresarial ? empleoCliente.giroEmpresarial.id : null)
            datosSolicitud.pasoFormulario.empleoCliente.empresa = empleoCliente.nombreDeLaEmpresa
            datosSolicitud.pasoFormulario.empleoCliente.jefeInmediato = empleoCliente.nombreDelJefeInmediato
            datosSolicitud.pasoFormulario.empleoCliente.periodo = empleoCliente.antiguedad
            datosSolicitud.pasoFormulario.empleoCliente.plazo = (empleoCliente.temporalidad ? empleoCliente.temporalidad.id : null)
            datosSolicitud.pasoFormulario.empleoCliente.telefono = empleoCliente.telefono
            datosSolicitud.pasoFormulario.empleoCliente.calle = empleoCliente.calle = 
            datosSolicitud.pasoFormulario.empleoCliente.noExterior = empleoCliente.numeroExterior
            datosSolicitud.pasoFormulario.empleoCliente.noInterior = empleoCliente.numeroInterior
            datosSolicitud.pasoFormulario.empleoCliente.codigoPostal = empleoCliente.codigoPostal?.codigo
            datosSolicitud.pasoFormulario.empleoCliente.colonia = empleoCliente.colonia
            datosSolicitud.pasoFormulario.empleoCliente.delegacion = empleoCliente.codigoPostal?.municipio?.id
            datosSolicitud.pasoFormulario.empleoCliente.estado = empleoCliente.codigoPostal?.municipio?.estado?.id
            datosSolicitud.pasoFormulario.empleoCliente.ocupacion = (empleoCliente.ocupacion ? empleoCliente.ocupacion.id : null)
            if(empleoCliente.fechaIngreso){
                def fechaIngreso = empleoCliente.fechaIngreso.split("/")
                datosSolicitud.pasoFormulario.empleoCliente.antiguedad = [:]
                datosSolicitud.pasoFormulario.empleoCliente.antiguedad.mes = (fechaIngreso[0] as int)
                datosSolicitud.pasoFormulario.empleoCliente.antiguedad.anio = (fechaIngreso[1] as int)
            }
            datosSolicitud.pasoFormulario.empleoCliente.ingresosFijos = empleoCliente.ingresosFijos
            datosSolicitud.pasoFormulario.empleoCliente.ingresosVariables = empleoCliente.ingresosVariables
            datosSolicitud.pasoFormulario.empleoCliente.ingresosTotales = (empleoCliente.ingresosFijos + empleoCliente.ingresosVariables)
            datosSolicitud.pasoFormulario.empleoCliente.gastos = empleoCliente.gastos
        }
        if(emailCliente){
            datosSolicitud.pasoFormulario.emailCliente = [:]
            emailCliente.each{
                if(it.tipoDeEmail.id == 1) {
                    datosSolicitud.pasoFormulario.emailCliente.emailPersonal = it.direccionDeCorreo
                } else if(it.tipoDeEmail.id == 2) {
                    datosSolicitud.pasoFormulario.emailCliente.emailLaboral = it.direccionDeCorreo
                }
            }
        }
        if(documentosSolicitud){
            datosSolicitud.tiposDeDocumento = [:]
            def tiposDeDocumento = TipoDeDocumento.findAllWhere(activo: true)
            tiposDeDocumento = tiposDeDocumento.sort{ it.id }
            tiposDeDocumento.each {
                datosSolicitud.tiposDeDocumento."$it.nombreMapeo" = false
            }
            documentosSolicitud.each {
                datosSolicitud.tiposDeDocumento."$it.tipoDeDocumento.nombreMapeo" = true
            }
        }
        
        if(consultaBuro){
            datosSolicitud.identificadores.idParametroConsultaBuro = consultaBuro.id
            datosSolicitud.consultaBuro = [:]
            datosSolicitud.consultaBuro.tCredito = consultaBuro.tieneTarjeta
            datosSolicitud.consultaBuro.creditoA = consultaBuro.tieneCreditoAutomotriz
            datosSolicitud.consultaBuro.creditoH = consultaBuro.tieneCreditoHipotecario
            datosSolicitud.consultaBuro.numeroTarjeta = consultaBuro.numeroDeTarjeta
        }
        datosSolicitud
    }
    
    def armarDatosTemporales(def solicitud){
        def respuesta = [:]
        respuesta.cotizador = [:]
        respuesta.identificadores = [:]
        if(solicitud.rubroDeAplicacion){
            respuesta.cotizador.rubro = solicitud.rubroDeAplicacion.id
            respuesta.cotizador.documento = solicitud.documentoElegido.id
            respuesta.cotizador.montoSeguro = solicitud.montoDelSeguroDeDeuda
            respuesta.cotizador.atrasos = solicitud.haTenidoAtrasos
            respuesta.cotizador.nombreCliente = solicitud.nombreDelCliente
            respuesta.cotizador.emailCliente = solicitud.emailCliente
            respuesta.cotizador.telefonoCliente = solicitud.telefonoCliente
            respuesta.prefilled = [:]
            respuesta.prefilled.telefonoCliente = [:]
            respuesta.prefilled.telefonoCliente.telefonoCelular = solicitud.telefonoCliente
            respuesta.prefilled.emailCliente = [:]
            respuesta.prefilled.emailCliente.emailPersonal = solicitud.emailCliente
        } else {
            respuesta.cotizador.color = solicitud.colorModelo.id
            respuesta.cotizador.modelo = solicitud.modelo.id
            respuesta.cotizador.seguro = solicitud.seguro.id
        }
        respuesta.cotizador.producto = solicitud.producto.id
        respuesta.cotizador.pagos = solicitud.montoDelPago
        respuesta.cotizador.montoCredito = solicitud.montoDelCredito
        respuesta.cotizador.seguroFinanciado = solicitud.seguroFinanciado
        respuesta.cotizador.enganche = solicitud.enganche
        respuesta.cotizador.periodo = solicitud.periodicidad.id
        respuesta.cotizador.plazo = solicitud.plazos
        respuesta.identificadores.idSolicitudTemporal = solicitud.id
        respuesta.configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: solicitud.entidadFinanciera)
        respuesta.ef = solicitud.entidadFinanciera
        respuesta.token = solicitud.token
        respuesta.shortUrl = solicitud.shortUrl
        respuesta.cargarImagen = solicitud.cargarImagen
        respuesta.ultimoPaso = 1
        return respuesta
    }
    
    def verificarExistenciaDeFolio(def folio, def entidadFinanciera, def tipoDeSolicitud){
        boolean existe = false
        def cantidad = 0
        def c
        c = SolicitudTemporal.createCriteria()
        cantidad += c.get {
            eq("folio", folio)
            and {
                eq("entidadFinanciera", entidadFinanciera)
            }
            projections {
                countDistinct "id"
            }
        }
        c = SolicitudDeCredito.createCriteria()
        cantidad += c.get {
            eq("folio", folio)
            and {
                eq("entidadFinanciera", entidadFinanciera)
            }
            projections {
                countDistinct "id"
            }
        }
        if(cantidad > 0){
            println "El folio " + folio + " ya está registrado en otra solicitud"
            existe = true
        }
        return existe
    }
    
    def verificarSolicitudExistente(def telefono, def nombreCompleto, def email) {
        def respuesta = [:]
        def listaDeClientes = []
        def cliente = TelefonoCliente.executeQuery("Select tc.cliente From TelefonoCliente tc Where replace(tc.numeroTelefonico,'-','') = :telefono OR replace(tc.numeroTelefonico,' ','') = :telefono OR replace(tc.numeroTelefonico,' ','') = :telefono044 OR replace(tc.numeroTelefonico,' ','') = :telefono045", [telefono: telefono, telefono044: ('044' + telefono), telefono045: ('045' + telefono)])
        if(cliente) {
            respuesta = buscarSolicitudFormalExisten(cliente, telefono, nombreCompleto, email)
        } else {
            respuesta = buscarSolicitudInformalExistente(telefono, nombreCompleto, email)
        }
        return respuesta
    }
    
    def buscarSolicitudFormalExisten(def cliente, def telefono, def nombreCompleto, def email) {
        def respuesta = [:]
        def listaDeClientes = []
        println "Cliente(s) encontrado(s): " + cliente
        if(cliente.size() > 1) {
            def x = 0
            respuesta.encontrado = false
            email = email?.replaceAll("\\s+", " ")?.toLowerCase()
            def emailRegistrado = EmailCliente.executeQuery("Select ec.cliente from EmailCliente ec Where lower(ec.direccionDeCorreo) = :mail", [mail: email])
            while(respuesta.encontrado == false && x < cliente.size()) {
                if( (valorCoincide(normalizarCadena(cliente[x].toString()), normalizarCadena(nombreCompleto))) || (emailRegistrado && emailRegistrado?.contains(cliente[x])) ){
                    def solicitudFormalExistente = SolicitudDeCredito.executeQuery("Select sc from SolicitudDeCredito sc Where sc.cliente.id = :idCliente and sc.statusDeSolicitud.id in (1,2,3) and sc.ultimoPaso != 6 Order by sc.fechaDeSolicitud",[idCliente: cliente[x].id])
                    if(solicitudFormalExistente) {
                        respuesta.shortUrl = "https://micreditolibertad.com/solicitud/resume?token=" + solicitudFormalExistente[0].token //solicitudFormalExistente[0].shortUrl
                        respuesta.encontrado = true
                    }
                }
                x++
            }
            if(!respuesta.encontrado) {
                respuesta = buscarSolicitudInformalExistente(telefono, nombreCompleto, email)
            }
            if(listaDeClientes.size() > 0 && !respuesta.shortUrl){
                respuesta.multiplesClientes = true   
            }     
        } else {
            def solicitudFormalExistente = SolicitudDeCredito.executeQuery("Select sc from SolicitudDeCredito sc Where sc.cliente.id = :idCliente and sc.statusDeSolicitud.id in (1,2,3) and sc.ultimoPaso != 6 Order by sc.fechaDeSolicitud",[idCliente: cliente[0].id])
            if(solicitudFormalExistente) {
                respuesta.shortUrl = "https://micreditolibertad.com/solicitud/resume?token=" + solicitudFormalExistente[0].token //solicitudFormalExistente[0].shortUrl
                respuesta.encontrado = true
            } else {
                respuesta = buscarSolicitudInformalExistente(telefono, nombreCompleto, email)
            }
        }
        println ("Regresando: " + respuesta)
        respuesta
    }
    
    def buscarSolicitudInformalExistente(def telefono, def nombreCompleto, def email){
        def respuesta = [:]
        def listaDeClientes = []
        def cliente = SolicitudTemporal.executeQuery("Select st From SolicitudTemporal st Where replace(st.telefonoCliente,'-','') = :telefono OR replace(st.telefonoCliente,' ','') = :telefono OR replace(st.telefonoCliente,' ','') = :telefono044 OR replace(st.telefonoCliente,' ','') = :telefono045 Order by st.fechaDeSolicitud", [telefono: telefono, telefono044: ('044' + telefono), telefono045: ('045' + telefono)])
        if(cliente) {
            println "Solicitudes Temporales encontradas: " + cliente
            if(cliente.size() > 1) {
                def x = 0
                respuesta.encontrado = false
                while(respuesta.encontrado == false && x < cliente.size()) {
                    if(valorCoincide(normalizarCadena(cliente[x].nombreDelCliente),normalizarCadena(nombreCompleto))){
                        respuesta.shortUrl = "https://micreditolibertad.com/solicitud/resume?token=" + cliente[x].token //cliente[x].shortUrl
                        respuesta.encontrado = true
                    } else if(cliente[x].emailCliente.toLowerCase() == email?.toLowerCase()){
                        respuesta.shortUrl = "https://micreditolibertad.com/solicitud/resume?token=" + cliente[x].token //cliente[x].shortUrl
                        respuesta.encontrado = true
                    } else {
                        listaDeClientes << cliente[x]
                    }
                    x++
                }
                if(listaDeClientes.size() > 0 && !respuesta.shortUrl){
                    respuesta.multiplesClientes = true   
                }
            } else {
                respuesta.shortUrl = "https://micreditolibertad.com/solicitud/resume?token=" + cliente[0].token //cliente[0].shortUrl
                respuesta.encontrado = true
            }
        } else {
            respuesta.encontrado = false
        }
        println ("Regresando: " + respuesta)
        respuesta
    }
    
    def normalizarCadena(def cadena) {
        def original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇ";
        def ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcC";
        def cadenaNormalizada = cadena;
        for (int i=0; i<original.length(); i++) {
            cadenaNormalizada = cadenaNormalizada.replace(original.charAt(i), ascii.charAt(i));
        }
        cadenaNormalizada = cadenaNormalizada?.replaceAll("\\s+", " ")
        cadenaNormalizada = cadenaNormalizada?.toUpperCase()
        return cadenaNormalizada;
    }
    
    def valorCoincide(def nombreGuardado, def nombreEnviado){
        def diferencia = StringUtils.getLevenshteinDistance(nombreGuardado, nombreEnviado)
        def proporcion = (1 -(diferencia/nombreGuardado.length())) as float
        println (nombreGuardado + " vs " + nombreEnviado + " - Coincidencia del " + (proporcion * 100) + " %")
        if(proporcion >= 0.9) {
            return true
        } else {
            return false
        }
    }
}
