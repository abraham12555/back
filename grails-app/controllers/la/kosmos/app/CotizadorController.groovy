package la.kosmos.app

import grails.converters.JSON
import java.net.URL
import org.apache.poi.ss.formula.eval.*;

class CotizadorController {
    
    def cotizadorService
    def smsService
    def urlShortenerService
    def solicitudService

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
            
            if(producto.esquema.id == 2){
                println "Seguro: " + montoSeguro
                println "Asistencia: " + montoAsistencia
                def montoMasSeguros = (montoFinanciado + montoSeguro + montoAsistencia)
                println "C"+ montoMasSeguros
                def c = montoMasSeguros
                def pagoCapital = (montoMasSeguros/((params.plazoElegido as int)))
                def a = pagoCapital*producto.plazoCondonado
                montoMasSeguros = montoMasSeguros-a
                def pagoIntereses = ((montoMasSeguros*(producto.tasaDeInteresAnual/12))/30)*15.20833
                def pagoIvaIntereses = pagoIntereses*0.16
                def pagoTotal= pagoCapital+pagoIntereses+pagoIvaIntereses
                respuesta.cat = catPagosVariables(c,montoMasSeguros,montoSeguro,pagoCapital,(params.plazoElegido as int),periodicidad.periodosAnuales,producto.plazoCondonado,producto.tasaDeInteresAnual)
                respuesta.montoAsistencia = montoAsistencia
                respuesta.montoSeguro = montoSeguro
                respuesta.nombrePeriodo = periodicidad.nombre
                respuesta.renta =  pagoTotal.round(2)
                respuesta.exito = true    
            }else if(producto.esquema.id ==1) {
                println "Seguro: " + montoSeguro
                println "Asistencia: " + montoAsistencia
                def tasaAnual = producto.tasaDeInteresAnual 
                println "Tasa Anual: " + tasaAnual
                def tasaConI = ((tasaAnual/12)*1.16)
                def tasaSinI = (tasaAnual/12) 
                println "Tasa con i: " + tasaConI
                def n = params.plazoElegido as int
                println "n: " + n
                def c = (montoFinanciado + montoSeguro + montoAsistencia)
                println "c: " + c
                def i = (tasaConI/30)*periodicidad.periodoDePago
                def ie = (tasaSinI/30)*periodicidad.periodoDePago
                println "i: " + i
                def renta =  (c / ((1-((1+i)**(-n)))/i))
                def renta2 = (c / ((1-((1+ie)**(-n)))/ie))
                respuesta.cat = catPagosFijos(c,montoSeguro,renta2,n,periodicidad.periodosAnuales)
                respuesta.montoAsistencia = montoAsistencia
                respuesta.montoSeguro = montoSeguro
                respuesta.nombrePeriodo = periodicidad.nombre
                respuesta.renta =  renta.round(2)
                respuesta.exito = true
            }
        }
        render respuesta as JSON
    }
    
    def procesar() {
        println params
        session.cotizador = [:]
        def respuesta = [:]
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
                session.cotizador.cat = (params.cat ? (params.cat as float) : 0)
                session.cotizador.medioDeContacto = ((params.comparaBien == "true") ? true : false)
                session.cotizador.opcionMedioDeContacto = ((params.comparaBien == "true") ? true : false)
                def resultadoShortener = urlShortenerService.acortarUrl((configuracion.entidadFinanciera.nombre + session.id), configuracion)
                if(resultadoShortener.statusCode == 200){
                    session.token = resultadoShortener.token
                    session.shortUrl = resultadoShortener.jsonGoogle.id
                    def solicitud = solicitudService.registrarSolicitudTemporal (session.cotizador, session.token, session.shortUrl, session.ef)
                    session.identificadores = [:]
                    session.identificadores.idSolicitudTemporal = solicitud.id
                    session.folio = solicitud.folio
                    session.respuestaEphesoft = [:]
                    session.respuestaEphesoft.telefonoCliente = [:]
                    session.respuestaEphesoft.telefonoCliente.telefonoCelular = session.cotizador.telefonoCliente
                    session.respuestaEphesoft.emailCliente = [:]
                    session.respuestaEphesoft.emailCliente.emailPersonal = session.cotizador.emailCliente
                    solicitudService.registrarEnviosProgramadosTemporales(solicitud.folio , 1)
                    respuesta = session.identificadores
                }else{
                    respuesta.error = true
                }
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
            render respuesta as JSON 
        } else {
            respuesta.sesionExpirada = true
            render respuesta as JSON
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
            if(session.configuracion){
                def toPhone = params.telefonoCelular.replaceAll('-', '').replaceAll(' ', '').trim()
                respuesta = cotizadorService.verificarSolicitudExistenteCotizador(params.telefonoCelular,session.configuracion)
                if(respuesta.encontrado && !respuesta.multiplesClientes && params.origen == "formulario"){
                    if(respuesta.folio){
                        String sid = smsService.sendFolio(toPhone,respuesta.folio, session.configuracion)
                        if(sid){
                            respuesta.mensajeEnviado = true
                        } else {
                            respuesta.mensajeEnviado = false
                        }
                    }
                }else if (!respuesta.encontrado && !respuesta.multiplesClientes && params.origen == "cotizador"){
                    println "Por enviar el SMS..."
                    String sid = smsService.sendSMS(toPhone, session.configuracion)
                    if(sid){
                        session.sid = sid
                        respuesta.mensajeEnviado = true
                    } else {
                        respuesta.mensajeEnviado = false
                    }                
                }
                else if (!respuesta.encontrado && !respuesta.multiplesClientes && params.origen == "formulario"){
                    respuesta.mensajeEnviado = false 
                    respuesta.solicitud = false
                }
            }else{
                respuesta.sesionExpirada = true
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
    
    def catPagosFijos(def montoDelCredito,def seguroDeDeuda,def pagosFijos, def plazos, def periodosAnuales){
        def lista = []
        double[] incomesList 
        double irr
        def cat
        def pagoCero = -(montoDelCredito - seguroDeDeuda)
        lista << pagoCero
            (1..plazos).each{
                lista << pagosFijos.round(2)
            }
            incomesList = lista
            irr = this.irr(incomesList);
            cat = Math.pow((1+irr),periodosAnuales)-1
            return cat
        
    }
    def catPagosVariables(def c, def montoDelCredito,def seguroDeDeuda,def pagoCapital, def plazos, def periodosAnuales,def plazosCondonados, def tasaDeInteres){
        def lista = []
        double[] incomesList 
        double irr
        def cat
        def pagoCero 
            pagoCero = -(c - seguroDeDeuda)
            lista << pagoCero
            (1..plazosCondonados).each{
                lista << pagoCapital
            }
            (plazosCondonados+1..plazos).each {
                def pagoIntereses = ((montoDelCredito*(tasaDeInteres/12))/30)*15.20833
                def pagoIvaIntereses = pagoIntereses*0.16
                def pagoTotal= pagoCapital+pagoIntereses+pagoIvaIntereses
                montoDelCredito = montoDelCredito - pagoCapital
                def flujoRecursos2 = pagoCapital + pagoIntereses
                lista << flujoRecursos2.round(2)
            }
            incomesList = lista
            irr = this.irr(incomesList);
            cat = Math.pow((1+irr),periodosAnuales)-1
            return cat
        
    }
     public static double irr(double[] income) {
        return irr(income, 0.1d);
    }
        public static double irr(double[] values, double guess) {
        int maxIterationCount = 20;
        double absoluteAccuracy = 1E-7;
        double x0 = guess;
        double x1;
        int i = 0;
        while (i < maxIterationCount) {
            // the value of the function (NPV) and its derivate can be calculated in the same loop
            double fValue = 0;
            double fDerivative = 0;
            for (int k = 0; k < values.length; k++) {
                fValue += values[k] / Math.pow(1.0 + x0, k);
                fDerivative += -k * values[k] / Math.pow(1.0 + x0, k + 1);
            }
            // the essense of the Newton-Raphson Method
            x1 = x0 - fValue/fDerivative;
            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                return x1;
            }
            x0 = x1;
            ++i;
        }
        if(i== 20){
            i = 0
            maxIterationCount = 100
            while (i < maxIterationCount) {
                // the value of the function (NPV) and its derivate can be calculated in the same loop
                double fValue = 0;
                double fDerivative = 0;
                for (int k = 0; k < values.length; k++) {
                    fValue += values[k] / Math.pow(1.0 + x0, k);
                    fDerivative += -k * values[k] / Math.pow(1.0 + x0, k + 1);
                }
                // the essense of the Newton-Raphson Method
                x1 = x0 - fValue/fDerivative;
                if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                    return x1;
                }
                x0 = x1;
                ++i;
            }
        }
        if(i== 100){
            i = 0
            maxIterationCount = 200
            while (i < maxIterationCount) {
                // the value of the function (NPV) and its derivate can be calculated in the same loop
                double fValue = 0;
                double fDerivative = 0;
                for (int k = 0; k < values.length; k++) {
                    fValue += values[k] / Math.pow(1.0 + x0, k);
                    fDerivative += -k * values[k] / Math.pow(1.0 + x0, k + 1);
                }
                // the essense of the Newton-Raphson Method
                x1 = x0 - fValue/fDerivative;
                if (Math.abs(x1 - x0) <= absoluteAccuracy) {
                    return x1;
                }
                x0 = x1;
                ++i;
            }
             return Double.NaN;
        }
    }

    def enviarFolio() {
        def respuesta = [:]
        if(params.folio && params.numeroTelefonico){
            if(session.configuracion){
                def toPhone = params.numeroTelefonico.replaceAll('-', '').replaceAll(' ', '').trim()
                String sid = smsService.sendFolio(toPhone,params.folio, session.configuracion)
                if(sid){
                    session.sid = sid
                    respuesta.mensajeEnviado = true
                } else {
                    respuesta.mensajeEnviado = false
                }
            }else{
                respuesta.sesionExpirada = true
            }
        } else {
            respuesta.mensajeEnviado = false
        }
        render respuesta as JSON
    }
    def buscarFolio() {
        def respuesta = [:]
        if(params.folio){
            if(session.configuracion){
                def solicitud = cotizadorService.buscarFolio(params.folio,session.configuracion)
                
                if(solicitud.encontrado && solicitud.shortUrl){
                    respuesta.encontrado = true
                    respuesta.shortUrl = solicitud.shortUrl
                } else {
                    respuesta.encontrado = false
                }
            }else{
                   respuesta.sesionExpirada = true
            }
        } else {
            respuesta.encontrado = false
        }
        render respuesta as JSON
    }
    
}

