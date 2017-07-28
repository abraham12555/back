package la.kosmos.app

import grails.converters.JSON
import java.net.URL

class CotizadorController {
    
    def cotizadorService
    def smsService

    def index() {
        println params
        session.cotizador = null
        session.ef = null
        session.configuracion = null
        session.respuestaEphesoft = null
        session.tiposDeDocumento = null
        session.identificadores = null
        session.datosLogin = null
        session.yaUsoLogin = null
        session.pasosDelCliente = null
        session.archivoTemporal = null
        session.ofertas = null
        session.perfil = null
        session["pasoFormulario"] = null
        session["consultaBancos"] = null
        session["consultaBuro"] = null
        session.shortUrl = null
        session.token = null
        session.sid = null
        session.cargarImagen = null
        session.estadoRecuperacion = null
        session.contadorOcr = 0
        String domain = new URL(request.getRequestURL().toString()).getHost();
        println "Dominio: " + domain
        //if(params.ef){
        def respuesta = cotizadorService.cargarCatalogos(params)
        session.ef = respuesta.entidadFinanciera
        session.configuracion = respuesta.configuracion
        
        respuesta
        //}
    }

    def obtenerPasos() {
        def respuesta = cotizadorService.cargarCatalogos(params)
        def pasosCotizador = RubroDeAplicacionPasoCotizador.executeQuery('Select r.paso from RubroDeAplicacionPasoCotizador r Where r.rubro.id = :rubro', [rubro: (params.rubroId as long)])
        pasosCotizador = pasosCotizador.sort { it.numeroDePaso }
        respuesta.pasosCotizador = pasosCotizador
        render ( template: 'stepTemplate', model: respuesta)
    }
    
    def obteneTiposDeProducto() {//Falta filtrar por Entidad Financiera
        def entidadFinanciera = EntidadFinanciera.get(1) 
        def tipoDeProducto = TipoDeProducto.get(params.tipoDeProductoId as long)
        def productos = Producto.findAllWhere(activo: true, entidadFinanciera: entidadFinanciera, tipoDeProducto: tipoDeProducto)
        render ( template: 'productosList', model: [productos: productos])
    }
    
    def obtenerProducto() {//Falta filtrar por Entidad Financiera
        Producto producto = Producto.get(params.id as long)
        JSON.use('deep') {
            render(producto as JSON)
        }
    }

    def obtenerModelos () { //Falta filtrar por Entidad Financiera
        def modelos = Modelo.findAllByProductoAndActivo(Producto.get(params.productoId as long), true)
        modelos = modelos.sort{ it.id }
        render ( template: 'modelosList', model: [modelosList: modelos])
    }

    def obtenerColores() {//Falta filtrar por Entidad Financiera
        def colores = ColorModelo.findAllByModeloAndActivo(Modelo.get(params.modeloId as long), true)
        colores = colores.sort{ it.id }
        render ( template: 'coloresList', model: [coloresList: colores])
    }
    
    def obtenerSeguros(){
        def seguros = SeguroProducto.findAllWhere(modelo: Modelo.get(params.modeloId as long))
        render ( template: 'segurosList', model: [segurosList: seguros])
    }
    
    def identificarProducto(){
        println params
        def respuesta = [:]
        if(params.rubroId && params.documentoId){
            def rubro = RubroDeAplicacionDeCredito.get(params.rubroId as long)
            def documento = TipoDeDocumento.get(params.documentoId as long)
            def haTenidoAtrasos = false
            if(params.atrasoEnPagos != null && params.atrasoEnPagos != 'null'){
                haTenidoAtrasos = params.atrasoEnPagos.toBoolean()
            }
            def rubroProducto = RubroDeAplicacionProducto.findWhere(rubro: rubro, tipoDeIngresos: documento.tipoDeIngresos, haTenidoAtrasos: haTenidoAtrasos)
            respuesta.exito = true
            respuesta.idProducto = rubroProducto.producto.id
            respuesta.nombreDelProducto = rubroProducto.producto.tituloEnCotizador
            respuesta.idTipoDeIngresos = documento.tipoDeIngresos.id
            respuesta.montoMaximo = rubroProducto.producto.montoMaximo
            respuesta.montoMinimo = rubroProducto.producto.montoMinimo
            respuesta.tasaDeInteres = rubroProducto.producto.tasaDeInteres
        } else {
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un error al consultar el producto solicitado. Intente nuevamente en unos momentos."
        }
        println respuesta
        render respuesta as JSON
    }
    
    def obtenerPlazosProducto(){
        println params
        def respuesta = [:]
        if(params.productoId && params.monto && params.documentoId){
            def periodicidad
            def documento = TipoDeDocumento.get(params.documentoId as long)
            if(documento.tipoDeIngresos.id == 1){
                periodicidad = 2 as long
            } else {
                periodicidad = 3 as long
            }
            def monto = params.monto as float
            def plazo = PlazoProducto.executeQuery('Select p from PlazoProducto p Where p.producto.id = :productoId and p.importeMinimo <= :monto and p.importeMaximo >= :monto and p.periodicidad.id = :periodicidadId', [productoId: params.productoId as long, periodicidadId: periodicidad, monto: monto])
            println "Plazo encontrado: " + plazo
            if(plazo){
                respuesta.exito = true
                respuesta.idPlazoProducto = plazo[0].id
                respuesta.importeMinimo = plazo[0].importeMinimo
                respuesta.importeMaximo = plazo[0].importeMaximo
                respuesta.periodicidad = plazo[0].periodicidad
                if(plazo[0].usarListaDePlazos){
                    respuesta.saltoSlider = 1
                    respuesta.plazoMinimo = 0
                    respuesta.plazoMaximo = ((plazo[0].plazosPermitidos ? ((plazo[0].plazosPermitidos.tokenize(',')).size() - 1) : 0))
                } else {
                    respuesta.plazoMinimo = plazo[0].plazoMinimo
                    respuesta.plazoMaximo = plazo[0].plazoMaximo
                    respuesta.saltoSlider = plazo[0].saltoSlider
                }
                respuesta.usarListaDePlazos = plazo[0].usarListaDePlazos
                respuesta.plazosPermitidos = ((plazo[0].plazosPermitidos ? (plazo[0].plazosPermitidos.tokenize(',')) : null))
            }
        } else {
            respuesta.error = true
            respuesta.mensaje = "Ocurrio un error al consultar los plazos de pago del producto. Intente nuevamente en unos momentos."
        }
        render respuesta as JSON
    }
    
    def calcularPagos(){
        println params
        def respuesta = [:]
        if(params.productoId){
            def montoSeguro = 0
            def montoAsistencia = 0
            def producto = Producto.get(params.productoId as long);
            def montoFinanciado = params.montoFinanciado as float
            def periodicidad = Periodicidad.get(params.periodicidadId as long)
            println "Plazo Elegido: " + params.plazoElegido
            println "Periodos Anuales: " + periodicidad.periodosAnuales
            def plazoAnual = (((params.plazoElegido as int)/(periodicidad.periodosAnuales)) as float).round()
            println "Plazo Anual: " + plazoAnual
            def seguro = SeguroSobreDeuda.executeQuery('Select s from SeguroSobreDeuda s Where s.entidadFinanciera.id = :entidadFinancieraId and ((s.montoInicial <= :monto and s.montoFinal >= :monto) or (s.montoInicial <= :monto and s.montoFinal = 0)) and s.plazoAnual = :plazoAnual',[entidadFinancieraId: (params.entidadFinancieraId as long), monto: montoFinanciado, plazoAnual: plazoAnual])
            if(seguro && seguro?.size() > 0){
                def seguroAplicable = seguro[0]
                if(seguroAplicable.porcentaje){
                    montoSeguro = (montoFinanciado * seguroAplicable.porcentajeSeguro) 
                } else {
                    montoSeguro = seguroAplicable.importeSeguro
                }
                montoSeguro = montoSeguro.round(3)
            }
            def asistencia = ServicioDeAsistencia.executeQuery('Select s from ServicioDeAsistencia s Where s.entidadFinanciera.id = :entidadFinancieraId and ((s.montoInicial <= :monto and s.montoFinal >= :monto) or (s.montoInicial <= :monto and s.montoFinal = 0)) and s.plazoAnual = :plazoAnual',[entidadFinancieraId: (params.entidadFinancieraId as long), monto: montoFinanciado, plazoAnual: plazoAnual])
            if(asistencia && asistencia?.size() > 0){
                def asistenciaAplicable = asistencia[0]
                println ("Encontrado: " + asistenciaAplicable.montoInicial + " - " + asistenciaAplicable.montoFinal)
                montoAsistencia = asistenciaAplicable.importeAsistencia
            }
            println "Seguro: " + montoSeguro
            println "Asistencia: " + montoAsistencia
            def tasaAnual = producto.tasaDeInteres * 12
            println "Tasa Anual: " + tasaAnual
            def tasaConI = tasaAnual*1.16
            println "Tasa con i: " + tasaConI
            def n = params.plazoElegido as int
            println "n: " + n
            def c = (montoFinanciado + montoSeguro + montoAsistencia)
            println "c: " + c
            def i = (tasaConI/periodicidad.periodosAnuales)
            println "i: " + i
            def renta =  (c / ((1-((1+i)**(-n)))/i))
            respuesta.montoAsistencia = montoAsistencia
            respuesta.montoSeguro = montoSeguro
            respuesta.nombrePeriodo = periodicidad.nombre
            respuesta.renta =  renta.round(2)
            respuesta.exito = true
        }
        render respuesta as JSON
    }
    
    def procesar() {
        println params
        session.cotizador = [:]
        if(session.configuracion){
            def configuracion = session.configuracion 
            if(configuracion.aplicacionVariable){
                session.cotizador.rubro = (params.txtRubro ? (params.txtRubro as long) : 0)
                session.cotizador.producto = (params.txtProducto ? (params.txtProducto as long) : 0)
                session.cotizador.documento = (params.txtDocumento ? (params.txtDocumento as long) : 0 )
                session.cotizador.montoCredito = (params.txtMontoCredito ? (params.txtMontoCredito as float) : 0)
                session.cotizador.montoSeguro = (params.txtMontoSeguro ? (params.txtMontoSeguro as float) : 0)
                session.cotizador.montoAsistencia = (params.txtMontoAsistencia ? (params.txtMontoAsistencia as float) : 0)
                session.cotizador.pagos = (params.txtPago ? (params.txtPago as float) : 0)
                session.cotizador.periodo = (params.txtPeriodo ? (params.txtPeriodo as long) : 0)
                session.cotizador.plazo = (params.txtPlazo ? (params.txtPlazo as int) : 0)
                session.cotizador.atrasos = params.txtTieneAtrasos
                session.cotizador.enganche = (params.txtEnganche ? (params.txtEnganche as float) : 0)
                session.cotizador.nombreCliente = params.nombre
                session.cotizador.emailCliente = params.email
                session.cotizador.telefonoCliente = params.telefonoCelular
            } else {
                session.cotizador.producto = (params.txtProducto ? (params.txtProducto as long) : 0)
                session.cotizador.periodo = (params.txtPeriodo ? (params.txtPeriodo as long) : 0)
                session.cotizador.pagos = (params.txtPago ? (params.txtPago as float) : 0)
                session.cotizador.modelo = (params.txtModelo ? params.txtModelo as long : 0)
                session.cotizador.color = (params.txtColor ? params.txtColor as long : 0)
                session.cotizador.enganche = (params.txtEnganche ? params.txtEnganche as float : 0)
                session.cotizador.plazo = (params.txtPlazo ? params.txtPlazo as long : 0)
                session.cotizador.periodo = (params.txtPeriodo ? params.txtPeriodo as long : 0)
                session.cotizador.seguro = (params.txtSeguro ? params.txtSeguro as long : 0)
            }
            println session.cotizador
            redirect(controller: "solicitud", action: "index")
        } else {
            redirect(controller: "cotizador", action: "index")
        }
    }
    
    def cargarImagen(){
        println params
        def respuesta = [:]
        if(params.rubroId){
            def rubro = RubroDeAplicacionDeCredito.get(params.rubroId as long)
            respuesta = cotizadorService.obtenerBase64Imagenes(rubro.urlImagen)
        } else if(params.productoId){
            def producto = Producto.get(params.productoId as long);
            respuesta = cotizadorService.obtenerBase64Imagenes(producto.rutaImagenDefault)
        }
        render respuesta as JSON
    }
    
    def solicitarCodigo() {
        println params
        def respuesta = [:]
        if(params.telefonoCelular){
            def toPhone = params.telefonoCelular.replaceAll('-', '').replaceAll(' ', '').trim()
            //respuesta = cotizadorService.verificarSolicitudExistente(toPhone, params.nombreCompleto, params.email)
            respuesta = cotizadorService.verificarSolicitudExistenteCotizador(params.telefonoCelular)

            println  "encontrado " + respuesta
            if(!respuesta.encontrado && !respuesta.multiplesClientes) {
                String sid = smsService.sendSMS(toPhone, session.configuracion)
                if(sid){
                    session.sid = sid
                    respuesta.mensajeEnviado = true
                } else {
                    respuesta.mensajeEnviado = false
                }
            }else if (respuesta.encontrado  == false && respuesta.multiplesClientes == true){
                respuesta.multiplesClientes == true
            }
        } else {
            respuesta.mensajeEnviado = false
        }
        render respuesta as JSON
    }
    
        
def solicitarCodigoShorUrl() {
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
        println params
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
}
