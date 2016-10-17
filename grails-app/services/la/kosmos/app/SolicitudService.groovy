package la.kosmos.app

import grails.transaction.Transactional
import groovy.sql.Sql

@Transactional
class SolicitudService {
    
    def dataSource

    def construirDatosTemporales(def params, def pasoEnviado, def identificadores) {
        println "Parametros Enviados: "+ params +" - Paso Enviado: " +pasoEnviado + " - Para el Cliente: " + identificadores?.idCliente
	def cliente
        def clienteNuevo = false
        def datosPaso = [:]
        if(pasoEnviado == 1){
            datosPaso.nombre = params.nombre
            datosPaso.apellidoPaterno = params.apellidoPaterno
            datosPaso.apellidoMaterno = params.apellidoMaterno
            datosPaso.sexo =  (params.sexo ? params.sexo as long : null)
            datosPaso.dia = (params.dia ? params.dia as int : null)
            datosPaso.mes = (params.mes ? params.mes as int : null)
            datosPaso.anio = (params.anio ? params.anio as int : null)
            datosPaso.entidad = (params.estado ? params.estado as long : null)
            datosPaso.estadoCivil = (params.estadoCivil ? params.estadoCivil as long : null)
            datosPaso.nivelEscolar = (params.nivelEscolar ? params.nivelEscolar as long : null)
            datosPaso.numeroCasa = params.telefono
            datosPaso.numeroCelular = params.celular
            datosPaso.rfc = params.rfc
            datosPaso.curp = params.curp
            datosPaso.conyugue = params.nombreConyugue
            datosPaso.dependientes = (params.dependientes ? params.dependientes as int : 0)
            if(identificadores?.idCliente){
                cliente = Cliente.get(identificadores.idCliente as long)
            } else {
                cliente = Cliente.findByCurp(datosPaso.curp)
                if(!cliente) {
                    cliente = Cliente.findByRfc(datosPaso.rfc)
                    if(!cliente) {
                        cliente = new Cliente()
                        clienteNuevo = true
                    }
                }
            }
            cliente.nombre = datosPaso.nombre
            cliente.apellidoPaterno = datosPaso.apellidoPaterno
            cliente.apellidoMaterno = datosPaso.apellidoMaterno
            cliente.nacionalidad =  ( datosPaso.entidad ? ((datosPaso.entidad < 33) ? Nacionalidad.get(1) : Nacionalidad.get(2)) : null)
            cliente.fechaDeNacimiento = ((datosPaso.dia && datosPaso.mes && datosPaso.anio) ? (new Date().parse("dd/MM/yyyy", (datosPaso.dia + "/" + datosPaso.mes + "/" + datosPaso.anio))) : null)
            cliente.lugarDeNacimiento = (datosPaso.entidad ? Estado.get(datosPaso.entidad) : null)
            cliente.curp = datosPaso.curp
            cliente.genero = (datosPaso.sexo ? Genero.get(datosPaso.sexo) : null)
            cliente.rfc = datosPaso.rfc
            cliente.estadoCivil = (datosPaso.estadoCivil ? EstadoCivil.get(datosPaso.estadoCivil) : null)
            cliente.nivelEducativo = (datosPaso.nivelEscolar ? NivelEducativo.get(datosPaso.nivelEscolar) : null)
            cliente.dependientesEconomicos = datosPaso.dependientes
            cliente.nombreDelConyugue = datosPaso.conyugue
            if(cliente.save(flush:true)){
                println ("El cliente ha sigo guardo correctamente con el id " + cliente.id)
                if(clienteNuevo){
                    def telefonoCasa = new TelefonoCliente()
                    def telefonoCelular = new TelefonoCliente()
                    def solicitudDeCredito = new SolicitudDeCredito()
                    def sql = new Sql(dataSource)
                    telefonoCasa.cliente = cliente
                    telefonoCasa.numeroTelefonico = datosPaso.numeroCasa
                    telefonoCasa.vigente = true
                    telefonoCasa.tipoDeTelefono = TipoDeTelefono.get(1)
                    telefonoCelular.cliente = cliente
                    telefonoCelular.numeroTelefonico = datosPaso.numeroCelular
                    telefonoCelular.vigente = true
                    telefonoCelular.tipoDeTelefono = TipoDeTelefono.get(2)
                    solicitudDeCredito.fechaDeSolicitud = new Date()
                    solicitudDeCredito.statusDeSolicitud = StatusDeSolicitud.get(1)
                    solicitudDeCredito.entidadFinanciera = EntidadFinanciera.get(1)
                    solicitudDeCredito.folio = new Long(sql.firstRow("select nextval('folios_entidad_" + (solicitudDeCredito.entidadFinanciera.id) + "')").nextval)
                    solicitudDeCredito.cliente = cliente
                    telefonoCasa.save(flush: true)
                    telefonoCelular.save(flush: true)
                    solicitudDeCredito.save(flush: true)
                    datosPaso.clienteGenerado = true
                    datosPaso.idCliente = cliente.id
                    datosPaso.idSolicitud = solicitudDeCredito.id
                } else {
                    def telefonosCliente = TelefonoCliente.findAllWhere(cliente: cliente, vigente: true)
                    telefonosCliente?.each {
                        if(it.tipoDeTelefono.id == 1) {
                            it.numeroTelefonico = datosPaso.numeroCasa
                            it.save(flush:true)
                        } else if(it.tipoDeTelefono.id == 2) {
                            it.numeroTelefonico = datosPaso.numeroCelular
                            it.save(flush:true)
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
        } else if (pasoEnviado == 2){
            datosPaso.calle = params.calle
            datosPaso.noExterior = params.noExterior
            datosPaso.noInterior = params.noInterior
            datosPaso.colonia = params.colonia
            datosPaso.codigoPostal = params.codigoPostal
            datosPaso.tipoDelegacion = params.tipoDelegacion
            datosPaso.municipio = (params.municipio ? params.municipio as long : null)
            datosPaso.estado = (params.estado ? params.estado as long : null)
            datosPaso.tipoDeVivienda = (params.tipoDeVivienda ?  params.tipoDeVivienda as long : null)
            datosPaso.tiempo = (params.tiempo ? params.tiempo as int : 0)
            datosPaso.temporalidad = (params.temporalidad ? params.temporalidad as long : null)
            if(identificadores?.idCliente) {
                cliente = Cliente.get(identificadores.idCliente as long)
                def direccionCliente
                if(identificadores?.idDireccion){
                    direccionCliente = DireccionCliente.get(identificadores.idDireccion as long)
                } else {
                    direccionCliente = new DireccionCliente()
                }
                direccionCliente.calle = datosPaso.calle
                direccionCliente.numeroExterior = datosPaso.noExterior
                direccionCliente.numeroInterior = datosPaso.noInterior
                direccionCliente.codigoPostal = (datosPaso.codigoPostal ? CodigoPostal.findByCodigo(datosPaso.codigoPostal) : null)
                direccionCliente.colonia = datosPaso.colonia
                direccionCliente.ciudad  = (datosPaso.municipio ? Municipio.get(datosPaso.municipio) : null)
                direccionCliente.tipoDeVivienda = (datosPaso.tipoDeVivienda ? TipoDeVivienda.get(datosPaso.tipoDeVivienda) : null)
                direccionCliente.temporalidad = (datosPaso.temporalidad ? Temporalidad.get(datosPaso.temporalidad) : null)
                direccionCliente.cliente = cliente
                direccionCliente.tiempoDeResidencia = datosPaso.tiempo
                direccionCliente.latitud = 0
                direccionCliente.longitud = 0
                if(direccionCliente.save(flush:true)){
                    println("La dirección se ha registrado correctamente")
                    datosPaso.idDireccion = direccionCliente.id
                } else {
                    if (direccionCliente.hasErrors()) {
                        direccionCliente.errors.allErrors.each {
                            println it
                        }
                    }
                }
            }
        } else if (pasoEnviado == 3){
            datosPaso.profesion = params.profesion
            datosPaso.empresa = params.empresa
            datosPaso.puesto = params.puesto
            datosPaso.periodo = (params.noPeriodo ? params.noPeriodo : 0)
            datosPaso.plazo = (params.plazo ? params.plazo : null)
            datosPaso.contrato = (params.contrato ? params.contrato : null)
            datosPaso.jefeInmediato = params.jefeInmediato
            datosPaso.giroEmpresarial = (params.giroEmpresarial ? params.giroEmpresarial : null)
            datosPaso.actividad = params.actividad
            datosPaso.explicacionActividad = params.explicacionActividad
            datosPaso.calle = params.calle
            datosPaso.noExterior = params.noExterior
            datosPaso.noInterior = params.noInterior
            datosPaso.colonia = params.colonia
            datosPaso.codigoPostal = params.codigoPostal
            datosPaso.tipoDelegacion = params.tipoDelegacion
            datosPaso.municipio = (params.municipio ? params.municipio as long : null)
            datosPaso.estado = (params.estado ? params.estado as long : null)
            datosPaso.telefono = params.telefono
            datosPaso.referencia1NombreCompleto = params.referencia1NombreCompleto
            datosPaso.referencia1Email = params.referencia1Email
            datosPaso.referencia1Celular = params.referencia1Celular
            datosPaso.referencia1Particular = params.referencia1Particular
            datosPaso.referencia2NombreCompleto = params.referencia2NombreCompleto
            datosPaso.referencia2Email = params.referencia2Email
            datosPaso.referencia2Celular = params.referencia2Celular
            datosPaso.referencia2Particular = params.referencia2Particular
            datosPaso.referencia3NombreCompleto = params.referencia3NombreCompleto
            datosPaso.referencia3Email = params.referencia3Email
            datosPaso.referencia3Celular = params.referencia3Celular
            datosPaso.referencia3Particular = params.referencia3Particular
            if(identificadores?.idCliente) {
                cliente = Cliente.get(identificadores.idCliente as long)
                def empleoCliente
                if(identificadores?.idEmpleo){
                    empleoCliente = EmpleoCliente.get(identificadores.idEmpleo as long)
                } else {
                    empleoCliente = new EmpleoCliente()
                }
                empleoCliente.puesto = datosPaso.puesto
                empleoCliente.actividad = datosPaso.actividad
                empleoCliente.explicacionActividad = datosPaso.explicacionActividad
                empleoCliente.profesion = datosPaso.profesion
                empleoCliente.tipoDeContrato = (datosPaso.contrato ? TipoDeContrato.get(datosPaso.contrato) : null)
                empleoCliente.giroEmpresarial = (datosPaso.giroEmpresarial ? GiroEmpresarial.get(datosPaso.giroEmpresarial) : null)
                empleoCliente.nombreDeLaEmpresa = datosPaso.empresa
                empleoCliente.nombreDelJefeInmediato = datosPaso.jefeInmediato
                empleoCliente.antiguedad = datosPaso.periodo
                empleoCliente.temporalidad = (datosPaso.plazo ? Temporalidad.get(datosPaso.plazo) : null)
                empleoCliente.telefono = datosPaso.telefono
                empleoCliente.calle = datosPaso.calle
                empleoCliente.numeroExterior = datosPaso.noExterior
                empleoCliente.numeroInterior = datosPaso.noInterior
                empleoCliente.codigoPostal = (datosPaso.codigoPostal ? CodigoPostal.findByCodigo(datosPaso.codigoPostal) : null)
                empleoCliente.colonia = datosPaso.colonia
                empleoCliente.ciudad = (datosPaso.municipio ? Municipio.get(datosPaso.municipio) : null)
                empleoCliente.cliente = cliente
                if(empleoCliente.save(flush:true)){
                    println("El empleo se ha registrado correctamente")
                    datosPaso.idEmpleo = empleoCliente.id
                    def referencia1
                    if(identificadores?.idReferencia1){
                        referencia = ReferenciaPersonalCliente.get(identificadores.idReferencia1 as long)
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
                    referencia1.nombreCompleto = datosPaso.referencia1NombreCompleto
                    referencia1.emailPersonal = datosPaso.referencia1Email
                    referencia1.telefonoCelular = datosPaso.referencia1Celular
                    referencia1.telefonoParticular = datosPaso.referencia1Particular
                    referencia1.tipoDeReferencia = TipoDeReferenciaPersonal.get(1)
                    referencia1.cliente = cliente
                    referencia2.nombreCompleto = datosPaso.referencia2NombreCompleto
                    referencia2.emailPersonal = datosPaso.referencia2Email
                    referencia2.telefonoCelular = datosPaso.referencia2Celular
                    referencia2.telefonoParticular = datosPaso.referencia2Particular
                    referencia2.tipoDeReferencia = TipoDeReferenciaPersonal.get(2)
                    referencia2.cliente = cliente
                    referencia3.nombreCompleto = datosPaso.referencia3NombreCompleto
                    referencia3.emailPersonal = datosPaso.referencia3Email
                    referencia3.telefonoCelular = datosPaso.referencia3Celular
                    referencia3.telefonoParticular = datosPaso.referencia3Particular
                    referencia3.tipoDeReferencia = TipoDeReferenciaPersonal.get(2)
                    referencia3.cliente = cliente
                    if(referencia1.save(flush:true)){
                        datosPaso.idReferencia1 = referencia1.id
                    } else {
                        if (referencia1.hasErrors()) {
                            referencia1.errors.allErrors.each {
                                println it
                            }
                        }
                    }
                    if(referencia2.save(flush:true)){
                        datosPaso.idReferencia2 = referencia2.id
                    } else {
                        if (referencia2.hasErrors()) {
                            referencia2.errors.allErrors.each {
                                println it
                            }
                        }
                    }
                    if(referencia3.save(flush:true)){
                        datosPaso.idReferencia3 = referencia3.id
                    } else {
                        if (referencia3.hasErrors()) {
                            referencia3.errors.allErrors.each {
                                println it
                            }
                        }   
                    }
                } else {
                    if (empleoCliente.hasErrors()) {
                        empleoCliente.errors.allErrors.each {
                            println it
                        }
                    }
                }
            }
            
        } else if (pasoEnviado == 4){
            datosPaso.depositoPromedio = (params.depositos ? params.depositos :null)
            datosPaso.retiroPromedio = (params.retiros ? params.retiros :null)
            datosPaso.saldoPromedio = (params.saldo ? params.saldo :null)
            datosPaso.login_id = params.login_id
            datosPaso.depositoCorrecto=(params.depositoCorrecto ? params.depositoCorrecto:null)
            datosPaso.retiroCorrecto=(params.retiroCorrecto ? params.retiroCorrecto:null)
            datosPaso.saldoCorrecto=(params.saldoCorrecto ? params.saldoCorrecto:null)
        } else if (pasoEnviado == 5){
            datosPaso.tCredito=(params.tCredito ? params.tCredito:null)
            datosPaso.creditoA=(params.creditoA ? params.creditoA:null)
            datosPaso.creditoH=(params.creditoH ? params.creditoH:null)
        } else if (pasoEnviado == 6){
            
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
    
    def registrarProducto (def datosCotizador, def identificadores){
        if(datosCotizador){
            def solicitud = SolicitudDeCredito.get(identificadores.idSolicitud as long)
            def productoSolicitud =  new ProductoSolicitud()
            productoSolicitud.colorModelo = ColorModelo.get(datosCotizador.color)
            productoSolicitud.enganche = datosCotizador.enganche
            productoSolicitud.mensualidad = (datosCotizador.enganche / datosCotizador.plazo)
            productoSolicitud.periodicidad = Periodicidad.get(datosCotizador.periodo)
            productoSolicitud.plazo = Plazo.get(1)
            productoSolicitud.seguroFinanciado = false
            productoSolicitud.solicitud = solicitud
            productoSolicitud.montoDelCredito = (productoSolicitud.colorModelo.modelo.precio - productoSolicitud.enganche)
            if(productoSolicitud.save(flush:true)){
                println("El producto se ha registrado correctamente")
            } else {
                println("No se guardo nada")
                if (productoSolicitud.hasErrors()) {
                    productoSolicitud.errors.allErrors.each {
                        println it
                    }
                }
            }
        }
    }
}
