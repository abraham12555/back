package la.kosmos.app

import grails.converters.JSON
import java.awt.image.BufferedImage
import java.text.SimpleDateFormat
import javax.imageio.ImageIO
import java.util.Date;
import java.util.Random
import org.apache.commons.codec.binary.Base64
import javax.xml.bind.DatatypeConverter
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair
import groovy.json.JsonSlurper
import groovy.time.TimeCategory
import static java.lang.Double.parseDouble

//import org.apache.commons.io.FileUtils

class SolicitudController {
    def ephesoftService
    def solicitudService
    def documentSenderService
    def saltEdgeService
    def buroDeCreditoService
    def motorDeDecisionService
    def urlShortenerService
    def smsService
    def emailSenderService
    def perfiladorService
	
    int timeWait = 500
    def maximumAttempts = 100
    def formatoFecha = "dd-MM-yyyy"
    def razonSocial = "Kosmos Soluciones Digitales JS, SA de CV"
		                       
    def jsonSlurper = new JsonSlurper()
    grails.gsp.PageRenderer groovyPageRenderer
    Random rand = new Random() 

	
	
    def index() {
        if(session.cotizador) {
            session.respuestaEphesoft = null
            session.tiposDeDocumento = null
            session.identificadores = null
            session.datosLogin = null
            session.yaUsoLogin = null
            session.pasosDelCliente = null
            session.motorDeDecisionConsultado = null
            session["pasoFormulario"] = null
            session["consultaBancos"] = null
            session["consultaBuro"] = null
            session.shortUrl = null
            session.token = null
            session.cargarImagen = null
            session.archivoTemporal = null
            session.estadoRecuperacion = null
            session.contadorOcr = 0
            //redirect action: "formulario"
            redirect action: "formulario"
        } else {
            redirect controller: "cotizador", action: "index"
        }
    }
	
    def login(){
        session["datosPaso1"] = null
        session["datosPaso2"] = null
        session["datosPaso3"] = null
        session["datosPaso4"] = null
        session["datosPaso5"] = null
        session["datosPaso6"] = null
        session.respuestaEphesoft = null
        session.tiposDeDocumento = null
        session.identificadores = null
        session.datosLogin = null
        session.yaUsoLogin = null
    }

	
    def authenticate() {
        SolicitudDeCredito solicitud = SolicitudDeCredito.get(session.identificadores.idSolicitud)
        if(solicitud.vinculacionBanco != null){
            borrarVinculacion(SolicitudDeCredito.get(session.identificadores.idSolicitud))
        }
        def customer = null
        def request = JSON.parse(params.data)
        def response = [:]
        try {
            println "Creando Usuario"
            int attempt = 0
            while (customer == null && attempt <= maximumAttempts) {
                customer = saltEdgeService.createUser()
                attempt++
                if (customer.error_class == null) {
                    request.customer_id = customer.data.id
                    session["customer_id"] = request.customer_id
                    session["secuenciaSe"] = customer.data.identifier
                }
            }
            if (!request.containsKey("customer_id")) {
                response.error_class = "Existe un problema de Comunicacion, Favor de Volver a intentarlo."
                render response as JSON
            }
	
            def login = fixJson(saltEdgeService.findLogin(request.customer_id))
            def credentials = [:]
            request.provider_code = request.provider_code + "_mx"
				
            println "PARAMETROS:"+request
            switch (request.provider_code) {
            case "bancomer_mx":
                credentials.account = request.login
                credentials.password = request.password
                break;
            case "hsbc_mx":
                credentials.login = request.login
                credentials.password = request.password
                credentials.login_method = request.login_method
                if(request.login_method == "Sin OTP"){
                    credentials.memorable = request.memorable	
                }
                break;
            default:
                credentials.login = request.login
                credentials.password = request.password
                break;
            }
			
            //println "CREDENCIALES-------"+credentials
			
            if (login.data == null) {
                println "Creando Login"
                login = saltEdgeService.createLogin(request.customer_id, request.provider_code, credentials)
                if (login.error_class) {
                    def deleteUser = saltEdgeService.deleteUser(request.customer_id)
                    response.error_class = "Existe un problema de Comunicacion, Favor de Volver a intentarlo."
                    borrarVinculacion(SolicitudDeCredito.get(session.identificadores.idSolicitud))
						
                    render response as JSON
                }
            }
            VinculacionBanco vinculacion = new VinculacionBanco()
            vinculacion.customer_id_SE = session["customer_id"]
            vinculacion.banco = request.provider_code
            vinculacion.secuenciaSe = session["secuenciaSe"]
            vinculacion.login_id_SE = login.data.id
            vinculacion.status = StatusSaltEdge.findByNombre("success")
				
				
            println "Esperando a conectar con la cuenta"
            login = requestWait("connect_account",request.customer_id)
            println "Cuenta Conectada... Interactive:" + login.data.last_attempt.interactive + " Name:" + login.data.last_attempt.last_stage.name
            if (login.data.last_attempt.interactive == true) {
                def reconnect = saltEdgeService.reconnectLogin(login.data.id, credentials)
                def fields = [:]
                println "Consultado login Interactive"
                login = requestWait("login_interactive",request.customer_id)
                response.loginInteractiveHtml = login.data.last_attempt.last_stage.interactive_html
                login.data.last_attempt.last_stage.interactive_fields_names.collect { component -> fields.put("${component}", "${component}") }
                if (fields.size() > 0) {
                    response.interactiveFieldsName = fields
                    response.customer_id = request.customer_id
                    vinculacion.save(flush:true)
                    solicitud.vinculacionBanco = vinculacion
                    solicitud.save(flush:true)
                    render response as JSON
                } else {
                    response.params = params
                    def deleteUser = saltEdgeService.deleteUser(request.customer_id)
                    borrarVinculacion(SolicitudDeCredito.get(session.identificadores.idSolicitud))
                    session["login_id"] = null
                    session["customer_id"] = null
                    response.error_class = "No se pudo obtener Token de Acceso."
                    render response as JSON
                }
            } else {
                //Esperamos para realizar la consulta de Login
                println "Consultando Cuenta..."
                login = requestWait("check_account",request.customer_id)
                response.login_id = login.data.id
                session["login_id"] = login.data.id
                vinculacion.save(flush:true)
                solicitud.vinculacionBanco = vinculacion
                solicitud.save(flush:true)
                response.accounts_resume = accountsResume(login.data.id)
                render response as JSON
            }
        } catch (Exception e) {
            println "Exception:" + e
            def deleteUser = saltEdgeService.deleteUser(request.customer_id)
            borrarVinculacion(SolicitudDeCredito.get(session.identificadores.idSolicitud))
            session["login_id"] = null
            session["customer_id"] = null
            session["secuenciaSe"] = null
            response.error_class = "Error al Autentificar al usuario. Favor de intentarlo mas tarde."
            render response as JSON
        }
    }
	
    def loginInteractive() {
        println "LOGIN INTERACTIVE"
        def request = JSON.parse(params.data)
        def response = [:]
        def login = fixJson(saltEdgeService.findLogin(request.customer_id))
        def credentials = [:]
        login.data.last_attempt.last_stage.interactive_fields_names.collect { component ->
            credentials.put("$component", request.get("${component}"))
        }
        def interactive = saltEdgeService.interactiveLogin(login.data.id, credentials)
        login = requestWait("check_account",request.customer_id)
        if (login.data.last_attempt.fail_message == null) {
            response.login_id = login.data.id
            session["login_id"] = login.data.id
            response.accounts_resume = accountsResume(login.data.id)
        } else {
            def deleteLogin = saltEdgeService.deleteLogin(login.data.id)
            def deleteUser = saltEdgeService.deleteUser(request.customer_id)
            borrarVinculacion(SolicitudDeCredito.get(session.identificadores.idSolicitud))
            response.error_class = login.data.last_attempt.fail_message
        }
		
        //println response as JSON
        render response as JSON
    }
	
	
    def borrarVinculacion(SolicitudDeCredito solicitud){
        if(solicitud?.vinculacionBanco != null){
            List<CuentaSaltEdge> cuentas= CuentaSaltEdge.findAllByVinculacion(solicitud.vinculacionBanco)
            if(cuentas.size() > 0){
                cuentas.each { cuenta ->
                    List<TransaccionSaltEdge> transacciones= TransaccionSaltEdge.findAllByAccount(cuenta)
                    if(transacciones.size()>0){
                        transacciones.each { transaccion ->
                            transaccion.delete(flush:true)
                        }
                    }
                    cuenta.delete(flush:true)
                }
            }
            VinculacionBanco vinculacionBanco =  solicitud.vinculacionBanco
            solicitud.vinculacionBanco = null
            solicitud.save(flush:true)
            vinculacionBanco.delete(flush:true)
        }
    }
	
	
    def accountsResume(def login_id){
        SolicitudDeCredito solicitud = SolicitudDeCredito.get(session.identificadores.idSolicitud)
        def accounts_resume = []
        def accounts = saltEdgeService.accounts(login_id)
        int index=0
        accounts.data.collect { account ->
            CuentaSaltEdge cuenta = new CuentaSaltEdge()
            cuenta.nature= account.nature
            cuenta.name = account.name
            cuenta.currency_code = account.currency_code
            try{
                cuenta.balance = new Double("${account.balance}")
            }catch(Exception e){
                println "Exception accountsResume.solicitudController" +e
            }
            cuenta.vinculacion = solicitud.vinculacionBanco
            cuenta.save(flush:true)
            def account_temp = 	transactions(account.id,cuenta)
            def account_resume = [:]
            def datosPaso = [:]
            account_resume.depositoPromedio = account_temp.depositoPromedio
            account_resume.retiroPromedio = account_temp.retiroPromedio
            //OBTENEMOS EL SALDO DE LA CUENTA
            //account_resume.saldoPromedio = account_temp.saldoPromedio
            account_resume.saldoPromedio = account.balance
            account_resume.nature = account.nature
            if(account.balance != 0 && (account.nature == "account" || account.nature == "checking") ){ 
                //println "SEL CUENTA::"+ account.balance + " TIPO " + account.nature
                datosPaso.depositoPromedio = account_resume.depositoPromedio
                datosPaso.retiroPromedio = account_resume.retiroPromedio
                datosPaso.saldoPromedio = account_resume.saldoPromedio
                datosPaso.login_id=login_id
                session["datosPaso4"] = datosPaso
                account_resume.select=index
            }
            accounts_resume.add(account_resume)
            index++
			
            /*if(account_temp.depositoPromedio != 0){  //Verificar Al Momento solo es una cuenta que no este en 0
            datosPaso.depositoPromedio = account_resume.depositoPromedio
            datosPaso.retiroPromedio = account_resume.retiroPromedio
            datosPaso.saldoPromedio = account_resume.saldoPromedio
            datosPaso.login_id=login_id
            session["datosPaso4"] = datosPaso
            }*/
        }
        return accounts_resume
    }
	
    def requestWait(def tipo, def customer_id){
        int attempt = 0
        def login = fixJson(saltEdgeService.findLogin(customer_id))
        switch(tipo){
        case "connect_account":
            while ((login.data.last_attempt.last_stage.name.equalsIgnoreCase("start") || login.data.last_attempt.last_stage.name.equalsIgnoreCase("connect") || login.data.last_attempt.last_stage.name.equalsIgnoreCase("fetch_accounts")) && attempt <= maximumAttempts) {
                Thread.sleep(timeWait)
                login = fixJson(saltEdgeService.findLogin(customer_id))
                attempt++
            }	
            break;
        case "login_interactive":
            while (login.data.last_attempt.last_stage.name != "interactive" && attempt <= maximumAttempts) {
                Thread.sleep(timeWait)
                login = fixJson(saltEdgeService.findLogin(customer_id))
                attempt++
            }
            break;
        case "check_account":
            while (login.data.last_attempt.finished == false && attempt <= maximumAttempts) {
                Thread.sleep(timeWait)
                login = fixJson(saltEdgeService.findLogin(customer_id))
                attempt++
            }
            break;
        }
        return login
    }
		
    def fixJson(def json) {
        def element = [:]
        element.data = null
        json.data.collect { component -> element.data = component }
        return element
    }
		
    def transactions(def account_id,CuentaSaltEdge cuenta) {
        def formatDate = "yyyy-MM-dd"
        def today = new Date()
        def from_date = new Date() - 90
        def transactions = saltEdgeService.transactions(account_id,from_date.format(formatDate),today.format(formatDate))
        def toDate
        def month
        def monthName
        def monthsList = []
        def monthsNames = []
        def dataList = [:]
        def accountResume = [:]
        transactions.data.collect { component ->
            TransaccionSaltEdge transaccion = new TransaccionSaltEdge()
            transaccion.idTransaction = component.id
            if(component.made_on){
                try{
                    transaccion.madeOn = new Date().parse('yyyy-MM-dd', "${component.made_on}")
                }catch(Exception e){
                    println "Exception transactions.solicitudController" +e
                }
            }
            transaccion.category = component.category
            transaccion.description = component.description
            if(component.amount){
                try{
                    transaccion.amount = new Double("${component.amount}")
                }catch(Exception e){
                    println "Exception transactions.solicitudController" +e
                }
            }
            transaccion.currency = component.currency
            transaccion.account = cuenta
            println "TRANSACCION A GUARDAR " + transaccion.toString()
            transaccion.save(flush:true)
			
            def transactionDate  = new Date().parse('yyyy-MM-dd', "${component.made_on}")
            switch(transactionDate[Calendar.MONTH]){
            case 0:
                month=0
                monthName = "Enero"
                break;
            case 1:
                month=1
                monthName = "Febrero"
                break;
            case 2:
                month=2
                monthName = "Marzo"
                break;
            case 3:
                month=3
                monthName = "Abril"
                break;
            case 4:
                month=4
                monthName = "Mayo"
                break;
            case 5:
                month=5
                monthName = "Junio"
                break;
            case 6:
                month=6
                monthName = "Julio"
                break;
            case 7:
                month=7
                monthName = "Agosto"
                break;
            case 8:
                month=8
                monthName = "Septiembre"
                break;
            case 9:
                month=9
                monthName = "Octubre"
                break;
            case 10:
                month=10
                monthName = "Noviembre"
                break;
            case 11:
                month=11
                monthName = "Diciembre"
                break;
            }
            monthsList.add(month);
            monthsNames.add(monthName);
        }
        monthsList = monthsList.unique()
        monthsNames = monthsNames.unique()
        def depositosPromedioList = []
        def retirosPromedioList = []
        def saldoPromedioList = []
        def depositoPromedio =  new BigDecimal("0.0")
        def retiroPromedio =  new BigDecimal("0.0")
        def saldoPromedio = new BigDecimal("0.0")
        int noElementosDepositos=0
        int noElementosRetiros=0
		
        monthsList.collect { mes ->
            BigDecimal depositosPromedio = new BigDecimal("0.0")
            BigDecimal retirosPromedio = new BigDecimal("0.0")
            BigDecimal saldosPromedio = new BigDecimal("0.0")
            def amount
            transactions.data.collect { transaction ->
                def transactionDate  = new Date().parse('yyyy-MM-dd', "${transaction.made_on}")
                if(transactionDate[Calendar.MONTH] == mes){
                    amount =  new BigDecimal("${transaction.amount}")
                    if(amount < 0){
                        retirosPromedio   += amount
                        noElementosRetiros++
                    }else{
                        depositosPromedio += amount
                        noElementosDepositos++
                    }
                }
            }
	
            if(depositosPromedio< 0){
                depositosPromedio = depositosPromedio * -1
            }
            if(retirosPromedio< 0){
                retirosPromedio = retirosPromedio * -1
            }
	
            dataList.depositosPromedio = depositosPromedio
            dataList.retirosPromedio = retirosPromedio
            saldosPromedio = dataList.depositosPromedio - dataList.retirosPromedio
			
            depositoPromedio +=  depositosPromedio
            retiroPromedio +=  retirosPromedio
            saldoPromedio += depositosPromedio - retirosPromedio
				
            saldoPromedioList.add(saldosPromedio)
            depositosPromedioList.add(dataList.depositosPromedio)
            retirosPromedioList.add(dataList.retirosPromedio)
            accountResume.transactions = transactions
        }
        accountResume.monthsList = monthsNames
        accountResume.depositosPromedioList = depositosPromedioList
        accountResume.retirosPromedioList = retirosPromedioList
        accountResume.saldoPromedioList = saldoPromedioList
        if(depositoPromedio == 0){
            accountResume.depositoPromedio = depositoPromedio
        }else{
            accountResume.depositoPromedio = depositoPromedio/noElementosDepositos
        }
        if(retiroPromedio == 0){
            accountResume.retiroPromedio= retiroPromedio
        }else{
            accountResume.retiroPromedio= retiroPromedio/noElementosRetiros
        }
        accountResume.saldoPromedio= saldoPromedio
        println "accountResume:::>" +accountResume
        return accountResume 
    }
	
    def test() {
        println params
        def tipoLogin;
        def datosLogin;
        def modelo = [:]
        if(params.origen == 'login'){
            if(params.datosFb && params.datosFb?.toString()!= 'null' && params.datosFb?.toString().length() > 0){
                println(":: Se inicio Sesión con FB ::" + params.datosFb.toString())
                datosLogin = params.datosFb.toString()
                tipoLogin="FB";
                params.remove("datosFb")
            }else if(params.datosGoogle && params.datosGoogle?.toString() != 'null' && params.datosGoogle?.toString().length() > 0){
                println(":: Se inicio Sesión con Google ::"+params.datosGoogle.toString())
                datosLogin = params.datosGoogle.toString();
                tipoLogin="Google";
                params.remove("datosGoogle")
            }
            session.yaUsoLogin=true
        } else if (params.origen == 'noLogin'){
            println(":: No se inicio Sesión ::");
            tipoLogin="none";
            session.yaUsoLogin=true
        }
        def paso
        def templateContent
        if(params.paso) {
            println "Paso a mostrar: " + params.paso
            paso = params.paso as int
        } else {
            println "No se recibio el parametro ...."
            paso = 1
        }
        switch (paso) {
        case 1:
            println "paso1"
            modelo = [estadoList:Estado.findAll(),
                estadoCivilList:EstadoCivil.findAll(),
                temporalidadList:Temporalidad.findAll(),
                escolaridadList:NivelEducativo.findAll(),
                generoList: Genero.list(),
                datosLogin:datosLogin,
                tipoLogin:tipoLogin,
                logueado: session.yaUsoLogin,
                paso:paso, generales:  (session[("datosPaso" + paso)]?:session.respuestaEphesoft)]
            break;
        case 2:
            println "paso2"
            modelo = [estadoList:Estado.findAll(),
                municipioList:Municipio.findAll(),
                tiposDeVivienda: TipoDeVivienda.findAllWhere(activo:true),
                temporalidadList:Temporalidad.findAll(),
                paso:paso, generales: (session[("datosPaso" + paso)]?:session.respuestaEphesoft)]
            break;
        case 3:
            println "paso3"
            modelo = [estadoList:Estado.findAll(),
                municipioList:Municipio.findAll(),
                tipoDeContratoList: TipoDeContrato.list(),
                giroEmpresarialList: GiroEmpresarial.list(),
                temporalidadList:Temporalidad.findAll(),
                paso:paso, generales: session[("datosPaso" + paso)]]
            break;
        case 4:
            println "paso4"
            modelo = [paso:paso, generales: session[("datosPaso" + paso)]]
            break;
        case 9://temporal
            println "paso5"
            modelo = [paso:paso, generales: session[("datosPaso" + paso)]]
            break;
        case 5://temporal
            println "paso6"
            modelo = [paso:paso, generales: session[("datosPaso" + paso)], documentosSubidos: session.tiposDeDocumento]
            break;
        case 7:
            println "paso7"
            modelo = [paso:paso]
            break;
        case 8:
            println "paso8"
            modelo = [paso:paso]
            break;
        }
        params.remove("origen")
        params.remove("paso")
        modelo
    }

    def cambiarPaso(){
	println params
        if(session.ef){
            if(params.siguientePaso){
                def modelo = [:]
                def siguientePaso =  params.siguientePaso as int
                def pasoAnterior =  params.pasoAnterior as int
                def ultimoPaso
                def pasosDeSolicitud
                def parrafos
                def pasoActual
                def entidadFinanciera = session.ef
                def aplicaPasoOpcional = false
                def configuracion = session.configuracion
                session.noChecarCamposLlenos = null
                if(session.pasosDelCliente){
                    pasosDeSolicitud = session.pasosDelCliente
                } else {
                    pasosDeSolicitud = PasoSolicitudEntidadFinanciera.findAllWhere(entidadFinanciera: entidadFinanciera);
                    pasosDeSolicitud = pasosDeSolicitud.sort { it.numeroDePaso }
                }
                pasosDeSolicitud.each { paso ->
                    if(paso.numeroDePaso == siguientePaso){
                        pasoActual = paso
                    } else if(paso.numeroDePaso == pasoAnterior){
                        ultimoPaso = paso
                    }
                }
                println("Paso a avanzar: " + pasoActual);
                if(pasoAnterior > 0) {
                    session[ultimoPaso.tipoDePaso.nombre] = solicitudService.construirDatosTemporales(session[ultimoPaso.tipoDePaso.nombre], params, ultimoPaso, session.identificadores, session.ef, session.token, session.shortUrl, null)
                    if(session["pasoFormulario"]?.cliente?.clienteGuardado || session.identificadores?.idSolicitud){
                        if(!session.identificadores){
                            session.identificadores = [:]
                        }
                        if(!session.estadoRecuperacion){
                            session.estadoRecuperacion = [:]
                        }
                        if(pasoAnterior == 1){
                            if(!session.identificadores.idCliente) {
                                session.identificadores.idCliente = session["pasoFormulario"]?.cliente?.idCliente
                            }
                            if(!session.identificadores.idSolicitud) {
                                session.identificadores.idSolicitud = session["pasoFormulario"]?.cliente?.idSolicitud
                            }
                            if(!session.identificadores.idProductoSolicitud) {
                                session.identificadores.idProductoSolicitud = solicitudService.registrarProducto(session.cotizador, session.identificadores)
                                session["pasoFormulario"].cliente.tipoDeDocumento = session.cotizador.documento
                            }
                            session.identificadores.idSolicitudTemporal = null
                            if(session.archivoTemporal){
                                def archivoMovido = solicitudService.moverDocumento(session.archivoTemporal, session.identificadores.idSolicitud)
                                if(archivoMovido){
                                    session.archivoTemporal = null
                                }
                            }
                            if(session.identificadores.idProductoSolicitud){
                                session.estadoRecuperacion.paso1 = true
                            }
                        } else if(pasoAnterior == 2){
                            if(!session.identificadores.idDireccion) {
                                session.identificadores.idDireccion = session["pasoFormulario"]?.direccionCliente?.idDireccion
                            }
                            if(session.identificadores.idDireccion){
                                session.estadoRecuperacion.paso2 = true
                            }
                        } else if(pasoAnterior == 3){
                            if(!session.identificadores.idEmpleo) {
                                session.identificadores.idEmpleo = session["pasoFormulario"]?.empleoCliente?.idEmpleo
                            }
                            session.identificadores.idReferencia1 = session["pasoFormulario"]?.referenciaPersonalCliente?.idReferencia1
                            session.identificadores.idReferencia2 = session["pasoFormulario"]?.referenciaPersonalCliente?.idReferencia2
                            session.identificadores.idReferencia3 = session["pasoFormulario"]?.referenciaPersonalCliente?.idReferencia3
                            if(session.identificadores.idEmpleo) {
                                session.estadoRecuperacion.paso3 = true
                            }
                        } else if (pasoAnterior == 4) {
                            if(!session.identificadores.idParametroConsultaBuro) {
                                println session["consultaBuro"]
                                session.identificadores.idParametroConsultaBuro = session["consultaBuro"]?.idParametroConsultaBuro
                            }
                        }
                    }
                }
                if(siguientePaso == 0){
                    session.invalidate()
                    render modelo as JSON
                } else {
                    if(params.opcional == "true" || session[ultimoPaso.tipoDePaso.nombre].guardadoCorrecto) {
                        def respuestaMotor
                        def pasoOpcional = PasoOpcionalEntidadFinanciera.findWhere(activo: true, entidadFinanciera: entidadFinanciera, pasoAnterior: pasoAnterior)
                        if(configuracion.ejecutarMotorEnPaso == pasoAnterior){
                            if(session.identificadores){
                                def solicitudAntesMotor = SolicitudDeCredito.get(session.identificadores.idSolicitud)
                                if(solicitudAntesMotor?.reporteBuroCredito && !solicitudAntesMotor?.reporteBuroCredito?.tipoErrorBuroCredito) {
                                    def datos = solicitudService.construirDatosMotorDeDecision(session.identificadores)
                                    respuestaMotor = motorDeDecisionService.obtenerScore(entidadFinanciera,datos)
                                    if(respuestaMotor){
                                        def resultadoMotorDeDecision = new ResultadoMotorDeDecision()
                                        resultadoMotorDeDecision.solicitud = solicitudAntesMotor
                                        resultadoMotorDeDecision.probabilidadDeMora = respuestaMotor.probabilidadDeMora
                                        resultadoMotorDeDecision.razonDeCobertura = respuestaMotor.razonDeCobertura
                                        resultadoMotorDeDecision.dictamenDePerfil = respuestaMotor.dictamenDePerfil
                                        resultadoMotorDeDecision.dictamenCapacidadDePago = respuestaMotor.dictamenCapacidadDePago
                                        resultadoMotorDeDecision.dictamenConjunto = respuestaMotor.dictamenConjunto
                                        resultadoMotorDeDecision.dictamenDePoliticas = respuestaMotor.dictamenDePoliticas
                                        resultadoMotorDeDecision.dictamenFinal = respuestaMotor.dictamenFinal
                                        resultadoMotorDeDecision.log = respuestaMotor.log
                                        if(resultadoMotorDeDecision.save(flush: true)) {
                                            session.identificadores.idMotor = resultadoMotorDeDecision.id
                                        } else {
                                            if (resultadoMotorDeDecision.hasErrors()) {
                                                resultadoMotorDeDecision.errors.allErrors.each {
                                                    println it
                                                }
                                            }
                                        }
                                        if(pasoOpcional.dependeDelMotor) {
                                            if(resultadoMotorDeDecision.dictamenFinal != "A") {
                                                pasoActual = pasoOpcional
                                                aplicaPasoOpcional = true
                                            }
                                        }
                                    } else {
                                        println("No se recibio respuesta del Motor de Decisión....")
                                    }
                                } else {
                                    println "No se invoca el motor porque no se tiene respuesta del buro o porque se tienen errores"
                                    println "Reporte Buro: " + solicitudAntesMotor?.reporteBuroCredito + " - " + solicitudAntesMotor?.reporteBuroCredito?.tipoErrorBuroCredito
                                }
                            }
                        }
                        def statusDeSolicitud
                        if(pasoActual.tipoDePaso.nombre == "pasoFormulario"){
                            def catalogos = [:]
                            def camposDelPaso = CampoPasoSolicitud.findAllWhere(pasoSolicitud: pasoActual)
                            camposDelPaso = camposDelPaso.sort { it.numeroDeCampo }
                            parrafos = camposDelPaso.groupBy({ campo -> campo.parrafo })
                            if(session["pasoFormulario"]?.direccionCliente?.colonia){
                                def cp = session["pasoFormulario"]?.direccionCliente?.codigoPostal?.replaceFirst('^0+(?!$)', '')
                                def codigo = CodigoPostal.findAllWhere(codigo: cp)
                                if(codigo){
                                    catalogos.colonia = codigo*.asentamiento as Set
                                    def estado = codigo.municipio.estado.getAt(0)
                                    catalogos.sucursal = SucursalEntidadFinanciera.findAllWhere(entidadFinanciera: session.ef, estado: estado)
                                }
                            }
                            println "Cambiar Paso: " + session.estadoRecuperacion
                            if(pasoAnterior > siguientePaso) {
                                session["pasoFormulario"]?.llenadoPrevio = true
                            } else if(session["pasoFormulario"] && session.estadoRecuperacion){
                                session["pasoFormulario"].llenadoPrevio = session.estadoRecuperacion."paso$siguientePaso"
                            } else {
                                session["pasoFormulario"]?.llenadoPrevio = session.respuestaEphesoft?.llenadoPrevio
                            }

                            statusDeSolicitud = StatusDeSolicitud.get(1)
                            modelo = [configuracion: configuracion,
                                logueado: session.yaUsoLogin,
                                pasosDeSolicitud: pasosDeSolicitud,
                                parrafos: parrafos,
                                pasoActual:pasoActual,
                                generales:  (session["pasoFormulario"]?:session.respuestaEphesoft),
                                catalogos: catalogos]
                        } else if(pasoActual.tipoDePaso.nombre == "consultaBancaria"){
                            statusDeSolicitud = StatusDeSolicitud.get(3)
                            modelo = [configuracion: configuracion,
                                pasosDeSolicitud: pasosDeSolicitud,
                                pasoActual:pasoActual, 
                                generales: session["consultaBancaria"]]
                        } else if(pasoActual.tipoDePaso.nombre == "consultaBuro"){
                            def municipio = null
                            if(session["pasoFormulario"] != null && session["pasoFormulario"].direccionCliente?.delegacion){
                                municipio= Municipio.findById(session["pasoFormulario"].direccionCliente?.delegacion as long)
                            }
                            statusDeSolicitud = StatusDeSolicitud.get(3)
                            def solicitud = SolicitudDeCredito.get(session.identificadores.idSolicitud)
                            modelo = [configuracion: configuracion,
                                logueado: session.yaUsoLogin,
                                pasosDeSolicitud: pasosDeSolicitud,
                                pasoActual:pasoActual,
                                generales: session["consultaBuro"],
                                personales: session["pasoFormulario"].cliente,
                                direccion: session["pasoFormulario"].direccionCliente,
                                municipio:municipio,
                                razonSocial:configuracion.razonSocial,
                                reporteBuroCredito:solicitud?.reporteBuroCredito?.id,
                                errorConsulta:solicitud?.reporteBuroCredito?.errorConsulta]
                        } else if (pasoActual.tipoDePaso.nombre == "ofertas") {
                            println "Si entraaaaa a evaluar la vista del perfilador!!!!!"
                            def respuesta
                            def propuestas
                            def clienteExistente = "NO"
                            session.ofertas = null
                            session.perfil = null
                            respuesta = perfiladorService.buscarPerfilExistente(session["pasoFormulario"]?.cliente?.rfc)
                            if(respuesta.encontrado){
                                session.perfil = respuesta.perfil
                                clienteExistente = "SI"
                            }
                            propuestas = perfiladorService.obtenerPropuestas("cotizador", session.identificadores, session["pasoFormulario"]?.cliente?.tipoDeDocumento, clienteExistente, session.perfil) 
                            println "Propuestas Obtenidas: " + propuestas*.producto
                            session.ofertas = propuestas
                            modelo = [configuracion: configuracion,
                                logueado: session.yaUsoLogin,
                                pasosDeSolicitud: pasosDeSolicitud,
                                pasoActual:pasoActual,
                                nombreTemplate: (pasoActual.rutaTemplate + pasoActual.tipoDePaso.nombre),
                                ofertas: propuestas]
                        } else if(pasoActual.tipoDePaso.nombre == "resumen"){
                            statusDeSolicitud = StatusDeSolicitud.get(2)
                            def solicitud = ((session.identificadores?.idSolicitud) ? SolicitudDeCredito.get(session.identificadores?.idSolicitud) : null)
                            def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud);
                            def mediosDeContacto = MedioDeContacto.findAllWhere(entidadFinanciera: session.ef, activo: true)
                            modelo = [configuracion: configuracion,
                                pasosDeSolicitud: pasosDeSolicitud,
                                pasoActual:pasoActual,
                                mediosDeContacto: mediosDeContacto,
                                productoSolicitud:productoSolicitud,
                                documentosSubidos: session.tiposDeDocumento]
                        }  else if(pasoActual.tipoDePaso.nombre == "confirmacion"){
                            statusDeSolicitud = StatusDeSolicitud.get(4)
                            def solicitud = ((session.identificadores?.idSolicitud) ? SolicitudDeCredito.get(session.identificadores?.idSolicitud) : null)
                            def resultadoMotorDeDecision = ResultadoMotorDeDecision.findWhere(solicitud: solicitud)
                            def sucursal
                            if(solicitud?.sucursal){
                                sucursal = [:]
                                sucursal.coordenadas = [:]
                                sucursal.id = solicitud.sucursal.id
                                sucursal.coordenadas.lat = solicitud.sucursal.latitud
                                sucursal.coordenadas.lng = solicitud.sucursal.longitud
                                sucursal.ubicacion = solicitud.sucursal.ubicacion
                                sucursal.numeroDeSucursal = solicitud.sucursal.numeroDeSucursal
                                sucursal.nombre = solicitud.sucursal.nombre
                            }
                            def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud);
                            modelo = [configuracion: configuracion,
                                productoSolicitud: productoSolicitud,
                                sucursal: sucursal as JSON,
                                resultadoMotorDeDecision: resultadoMotorDeDecision,
                                pasoActual:pasoActual]
                            if(configuracion?.enviarNotificacionesPorCorreo) {
                                emailSenderService.envioCorreo(session["pasoFormulario"].cliente, session["pasoFormulario"].emailCliente, modelo)
                            }
                        }
                        if(session.identificadores?.idSolicitud){
                            def solicitud = SolicitudDeCredito.get(session.identificadores?.idSolicitud)
                            solicitud.ultimoPaso = pasoActual.numeroDePaso
                            solicitud.statusDeSolicitud = statusDeSolicitud
                            solicitud.save(flush: true)
                        }
                        render(template: ( aplicaPasoOpcional ? "pasoOpcional" : pasoActual.tipoDePaso.nombre), model: modelo)
                    } else {
                        def respuesta = [:]
                        respuesta.error = true
                        respuesta.mensaje = "No se han registrado los datos enviados, verifica que se han llenado los campos requeridos."
                        respuesta.mensajeError= session[ultimoPaso.tipoDePaso.nombre].mensaje
                        render respuesta as JSON
                    }
                }
            } else {
                println ("Que segun no llego el parametro del paso siguiente :S")
                redirect action: "formulario"
            }
        } else {
            session.invalidate()
            def respuesta = [:]
            respuesta.sesionExpirada = true
            respuesta.mensaje = "Tu sesión ha expirado. Para continuar con tu solicitud da click en el siguiente botón."
            render respuesta as JSON
        }
    }
    
    def cargarControlDefault(){
        render(template: "/templates/solicitud/paso4/consultaBancariaDefault")
    }
    
    def consultaBancos(){
        println params
        def respuesta = [:]
        int max = 10  
        def test = rand.nextInt(max+1)
        sleep(3000)
        if(test % 2 == 0){
            respuesta.status = 200
            respuesta.depositosPromedio =  rand.nextInt(18000+1)
            respuesta.retirosPromedio =  rand.nextInt(10000+1)
            respuesta.saldoPromedio = rand.nextInt(8000+1)
            render respuesta as JSON
        } else {
            //render(template: "/templates/solicitud/paso4/errorConsultaBancaria", model: [intentos: (params.intentos ? ((params.intentos as int) + 1) : (0))])
            respuesta.error = 500
            respuesta.intentos = (params.intentos ? ((params.intentos as int) + 1) : (0))
            render respuesta as JSON
        }
    }
    
    def subirDocumento(){

    }
    
    def consultarOCR(){
        println params
        def fileNames = request.getFileNames()
        def listaDeArchivos = []
        def mapa
        def respuesta
        def ephesoft = false
        def ocr = true
        fileNames.each{ fileName ->
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
        if(!session.yaUsoLogin){
            session.yaUsoLogin = true
        }
        if(params.docType == "Pasaportes" || params.docType == "Identicaciones"){
            if(params.cara == "frente") {
                if(session.contadorOcr == 0) {
                    def respuestaPreliminar = documentSenderService.send(listaDeArchivos)
                    respuesta = documentSenderService.verificarRespuestaMitek(respuestaPreliminar.referencia, session.identificadores?.idSolicitud, session.identificadores?.idSolicitudTemporal, respuestaPreliminar.dossierId)
                    session.contadorOcr++
                }
            } else if (params.cara == "vuelta") {
                respuesta = [:]
                respuesta.vigente = true
                respuesta.exito = true
                ocr = false
            }
        }else if (params.docType == "reciboCfe" || params.docType == "reciboTelmex"){
            respuesta = ephesoftService.ocrClassifyExtract(listaDeArchivos, session.identificadores?.idSolicitud, "UtilityBill");
            ephesoft = true
        } else if (params.docType == "ComprobanteDeIngresos"){
            def solicitud = ((session.identificadores?.idSolicitud) ? SolicitudDeCredito.get(session.identificadores?.idSolicitud) : null)
            def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud);
            params.docType = productoSolicitud.documentoElegido.nombreMapeo
            respuesta = [:]
            respuesta.error = false
            ocr = false
        } else if (params.docType == "consentimientoConsultaBC") {
            respuesta = [:]
            respuesta.error = false
            ocr = false
        }
        if((!respuesta?.error || respuesta?.vigente == true) && !respuesta?.motivosRechazo) {
            if(ocr){
                session["pasoFormulario"] = respuesta
                if(session.cotizador?.emailCliente && session["pasoFormulario"]){
                    session["pasoFormulario"].emailCliente = [:]
                    session["pasoFormulario"].emailCliente.emailPersonal = session.cotizador.emailCliente
                }
                if(session.cotizador?.telefonoCliente && session["pasoFormulario"]){
                    session["pasoFormulario"].telefonoCliente = [:]
                    session["pasoFormulario"].telefonoCliente.telefonoCelular = session.cotizador.telefonoCliente
                }
            }
        }
        session.tiposDeDocumento = solicitudService.controlDeDocumentos(session.tiposDeDocumento, params.docType)
        if(session.identificadores?.idSolicitud){
            if(ocr){
                solicitudService.guardarDocumento(listaDeArchivos.getAt(0), session.identificadores.idSolicitud, params.docType)
            } else {
                respuesta = solicitudService.guardarDocumento(listaDeArchivos.getAt(0), session.identificadores.idSolicitud, params.docType)
            }
        } else {
            session.archivoTemporal = solicitudService.guardarDocumentoTemporal(listaDeArchivos.getAt(0), params.docType, ephesoft)
        }
        println respuesta
        render respuesta as JSON
    }
    
    def consultarBuroDeCredito(){
        //println URLDecoder.decode(params.cadenaDeBuro, "UTF-8")
        ConfiguracionBuroCredito configuracion =  ConfiguracionEntidadFinanciera.get(session.configuracion.id).configuracionBuroCredito
        println "CONSULTA DE BURO DE CREDITO EF...."+ configuracion
        println "session.identificadores: " + session.identificadores
        def respuesta
        if(params.intl && params.intl == "true") {
            respuesta = buroDeCreditoService.consultaINTL(params,session["pasoFormulario"]?.cliente,session["pasoFormulario"]?.direccionCliente,SolicitudDeCredito.get(session.identificadores.idSolicitud),configuracion)
        } else {
            respuesta = buroDeCreditoService.callWebServicePersonasFisicas(params,session["pasoFormulario"]?.cliente,session["pasoFormulario"]?.direccionCliente,SolicitudDeCredito.get(session.identificadores.idSolicitud),configuracion)
        }
        render respuesta as JSON
    }
    
    def cargaDeArchivos(){
        render(template: "/templates/solicitud/paso4/cargaDeIdentificaciones")
    }
    
    def guardarFoto(){
        //println params
        def respuesta = [:]
        def imagenOrigen = params.img_data;
        println "Longitud de la cadena: " + params.img_data.length()
        def tokens = imagenOrigen.split(",");
        String cadenaBase64 = URLEncoder.encode(tokens[1], "UTF-8");
        int max = 1000  
        def test = rand.nextInt(max+1)
        BufferedImage imagen = null;
        byte[] imagenEnBytes;
        try{
            //println cadenaBase64
            imagenEnBytes = Base64.decodeBase64(cadenaBase64)
            File file = new File("/var/uploads/kosmos/fotos/image" + test + ".png");
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));
            writer.write(imagenEnBytes);
            writer.flush();
            writer.close();
            /*ByteArrayInputStream bis = new ByteArrayInputStream(imagenEnBytes);
            imagen = ImageIO.read(bis);
            bis.close();
            File outputfile = new File("/var/uploads/kosmos/fotos/image" + test + ".png");
            ImageIO.write(imagen, "png", outputfile);*/
            respuesta.status = 200
        } catch(Exception e){
            respuesta.status = 500
            respuesta.error = "Error al guardar el archivo"
            e.printStackTrace();
        } finally {
            render respuesta as JSON
        }
    }
    
    def buscarCodigoPostal(){
        println params
        def respuesta = []
        if(params.query){
            def resultado
            resultado = CodigoPostal.findAll("from CodigoPostal c Where c.codigo like '" + params.query + "%'")
            def map
            resultado.each{
                map = [:]
                map.value = it.codigo
                map.id = it.id
                map.tokens = [it.codigo.toString()]
                respuesta << map
                map = null
            }
        }
        println respuesta
        render respuesta as JSON
    }
    
    def consultarCodigoPostal(){
        println params
        def respuesta = [:]
        if(params.idCodigoPostal){
            def cp = params.idCodigoPostal?.replaceFirst('^0+(?!$)', '')
            def codigo = CodigoPostal.findAllWhere(codigo: cp)
            if(codigo){
                respuesta.asentamientos = codigo*.asentamiento as Set
                respuesta.asentamientos = respuesta.asentamientos.sort{ it }
                respuesta.municipio =  codigo*.municipio[0]
                respuesta.estado = respuesta.municipio.estado
                def sucursales = SucursalEntidadFinanciera.findAllWhere(entidadFinanciera: session.ef, estado: respuesta.estado)
                if(sucursales) {
                    respuesta.sucursales = []
                    sucursales.each { sucursal ->
                        def registro = [:]
                        registro.coordenadas = [:]
                        registro.id = sucursal.id
                        registro.coordenadas.lat = sucursal.latitud
                        registro.coordenadas.lng = sucursal.longitud
                        registro.ubicacion = sucursal.ubicacion
                        registro.numeroDeSucursal = sucursal.numeroDeSucursal
                        registro.nombre = sucursal.nombre
                        respuesta.sucursales << registro
                    }
                } else {
                    respuesta.sucursales = [:]
                    respuesta.sucursales.noHaySucursales = true
                    respuesta.sucursales.mensaje = ""
                }
            } else {
                respuesta.sucursales = [:]
                respuesta.sucursales.noHaySucursales = true
                respuesta.sucursales.mensaje = ""
            }
        }
        println "Regresando: " + respuesta
        render respuesta as JSON
    }
    
    def obtenerOpciones(){
        println params
        def respuesta = [:]
        if(params.opcionElegida){
            def medio = MedioDeContacto.get(params.opcionElegida as long)
            if(medio?.nombre == "Sucursales"){
                respuesta = SucursalEntidadFinanciera.findAllWhere(entidadFinanciera: session.ef);
            } else {
                respuesta = OpcionMedioDeContacto.findAllWhere(medioDeContacto: medio)
                if(!respuesta){
                    respuesta = [:]
                }
            }
        }
        render respuesta as JSON
    }
    
    def formulario(){
        if(session.cotizador){
            def tipoLogin;
            def datosLogin;
            def modelo = [:]
            def avance = [:]
            if(params.origen == 'login'){
                if(params.datosFb && params.datosFb?.toString()!= 'null' && params.datosFb?.toString().length() > 0){
                    println(":: Se inicio Sesión con FB ::" + params.datosFb.toString())
                    datosLogin = params.datosFb.toString()
                    tipoLogin="FB";
                    params.remove("datosFb")
                }else if(params.datosGoogle && params.datosGoogle?.toString() != 'null' && params.datosGoogle?.toString().length() > 0){
                    println(":: Se inicio Sesión con Google ::"+params.datosGoogle.toString())
                    datosLogin = params.datosGoogle.toString();
                    tipoLogin="Google";
                    params.remove("datosGoogle")
                }
                session.yaUsoLogin=true
            } else if (params.origen == 'noLogin'){
                println(":: No se inicio Sesión ::");
                tipoLogin="none";
                session.yaUsoLogin=true
            }
            def pasoActual
            def siguientePaso
            def entidadFinanciera = session.ef//EntidadFinanciera.get((params.ef ? (params.ef as long) : 13))
            def pasosDeSolicitud
            def ponderaciones = [:]
            def parrafos
            def configuracion = session.configuracion//ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
			
            if(!session.shortUrl) {
                def resultadoShortener = urlShortenerService.acortarUrl((entidadFinanciera.nombre + session.id), configuracion)
                if(resultadoShortener.statusCode == 200){
                    session.token = resultadoShortener.token
                    session.shortUrl = resultadoShortener.jsonGoogle.id
                }
            }

            if(session.pasosDelCliente){
                pasosDeSolicitud = session.pasosDelCliente
            } else {
                pasosDeSolicitud = PasoSolicitudEntidadFinanciera.findAllWhere(entidadFinanciera: entidadFinanciera);
                pasosDeSolicitud = pasosDeSolicitud.sort { it.numeroDePaso }
            }
            if(params.paso){
                siguientePaso = params.paso as int
            } else {
                siguientePaso = 1
            }
            
            println "Test: " + session.estadoRecuperacion
            if(session["pasoFormulario"] && session.estadoRecuperacion){
                println ("---->" + "paso$siguientePaso" + " = " + session.estadoRecuperacion."paso$siguientePaso" )
                session["pasoFormulario"].llenadoPrevio = session.estadoRecuperacion."paso$siguientePaso"
            }
            
            pasosDeSolicitud.each { paso ->
                if(paso.numeroDePaso == siguientePaso){
                    pasoActual = paso
                }
                ponderaciones."paso$paso.numeroDePaso" = paso.ponderacion
                if((siguientePaso > 1) && (paso.numeroDePaso < siguientePaso)){
                    avance."paso$paso.numeroDePaso" = paso.ponderacion
                } else {
                    avance."paso$paso.numeroDePaso" = 0
                }
            }
            if(siguientePaso == 1 && session.shortUrl && session.token && !session.identificadores?.idSolicitud && !session.identificadores?.idSolicitudTemporal){
                if(!session.identificadores){
                    session.identificadores = [:]
                }
                session.identificadores.idSolicitudTemporal = solicitudService.registrarSolicitudTemporal (session.cotizador, session.token, session.shortUrl, session.ef)
                session.respuestaEphesoft = [:]
                session.respuestaEphesoft.telefonoCliente = [:]
                session.respuestaEphesoft.telefonoCliente.telefonoCelular = session.cotizador.telefonoCliente
                session.respuestaEphesoft.emailCliente = [:]
                session.respuestaEphesoft.emailCliente.emailPersonal = session.cotizador.emailCliente
            }
            def camposDelPaso = CampoPasoSolicitud.findAllWhere(pasoSolicitud: pasoActual)
            camposDelPaso = camposDelPaso.sort { it.numeroDeCampo }
            parrafos = camposDelPaso.groupBy({ campo -> campo.parrafo })
            if(pasoActual.tipoDePaso.nombre == "pasoFormulario"){
                if(siguientePaso == 1 && !session["pasoFormulario"]?.llenadoPrevio){
                    session.noChecarCamposLlenos = true
                } else {
                    session.noChecarCamposLlenos = null
                }
                def catalogos = [:]
                if(session["pasoFormulario"]?.direccionCliente?.colonia){
                    def cp = session["pasoFormulario"].direccionCliente?.codigoPostal?.replaceFirst('^0+(?!$)', '')
                    def codigo = CodigoPostal.findAllWhere(codigo: cp)
                    if(codigo){
                        catalogos.colonia = codigo*.asentamiento as Set
                        def estado = codigo.municipio.estado.getAt(0)
                        catalogos.sucursal = SucursalEntidadFinanciera.findAllWhere(entidadFinanciera: session.ef, estado: estado)
                    }
                }
                println ("PasoFormulario: " + session["pasoFormulario"] + ", OCR: " + session.respuestaEphesoft + " - Catalogos: " + catalogos)
                modelo = [configuracion: configuracion,
                    logueado: session.yaUsoLogin,
                    pasosDeSolicitud: pasosDeSolicitud,
                    parrafos: parrafos,
                    avance: avance as JSON,
                    ponderaciones: ponderaciones as JSON,
                    pasoActual:pasoActual,
                    generales:  (session["pasoFormulario"]?:session.respuestaEphesoft),
                    catalogos: catalogos]
            } else if(pasoActual.tipoDePaso.nombre == "consultaBancaria"){
                modelo = [configuracion: configuracion,
                    pasosDeSolicitud: pasosDeSolicitud,
                    pasoActual:pasoActual,
                    avance: avance as JSON,
                    ponderaciones: ponderaciones as JSON,
                    generales: session["consultaBancaria"]]
            } else if(pasoActual.tipoDePaso.nombre == "consultaBuro"){
                def municipio = null
                if(session["pasoFormulario"] != null && session["pasoFormulario"].direccionCliente?.delegacion){
                    municipio= Municipio.findById(session["pasoFormulario"].direccionCliente?.delegacion as long)
                }
                def solicitud = ((session.identificadores?.idSolicitud) ? SolicitudDeCredito.get(session.identificadores?.idSolicitud) : null)
                modelo = [configuracion: configuracion,
                    logueado: session.yaUsoLogin,
                    pasosDeSolicitud: pasosDeSolicitud,
                    pasoActual:pasoActual,
                    avance: avance as JSON,
                    ponderaciones: ponderaciones as JSON,
                    generales: session["consultaBuro"],
                    personales: session["pasoFormulario"]?.cliente,
                    direccion: session["pasoFormulario"]?.direccionCliente,
                    municipio:municipio,
                    razonSocial:configuracion.razonSocial,
                    reporteBuroCredito:solicitud?.reporteBuroCredito?.id,
                    errorConsulta:solicitud?.reporteBuroCredito?.errorConsulta]
            } else if(pasoActual.tipoDePaso.nombre == "resumen"){
                def solicitud = ((session.identificadores?.idSolicitud) ? SolicitudDeCredito.get(session.identificadores?.idSolicitud) : null)
                def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud);
                def mediosDeContacto = MedioDeContacto.findAllWhere(entidadFinanciera: session.ef, activo: true)
                modelo = [configuracion: configuracion,
                    pasosDeSolicitud: pasosDeSolicitud,
                    pasoActual:pasoActual,
                    avance: avance as JSON,
                    mediosDeContacto: mediosDeContacto,
                    ponderaciones: ponderaciones as JSON,
                    documentosSubidos: session.tiposDeDocumento,
                    productoSolicitud: productoSolicitud]
            }  else if(pasoActual.tipoDePaso.nombre == "confirmacion"){
                def solicitud = ((session.identificadores?.idSolicitud) ? SolicitudDeCredito.get(session.identificadores?.idSolicitud) : null)
                def sucursal
                if(solicitud?.sucursal){
                    sucursal = [:]
                    sucursal.coordenadas = [:]
                    sucursal.coordenadas.lat = solicitud.sucursal.latitud
                    sucursal.coordenadas.lng = solicitud.sucursal.longitud
                    sucursal.ubicacion = solicitud.sucursal.ubicacion
                    sucursal.numeroDeSucursal = solicitud.sucursal.numeroDeSucursal
                    sucursal.nombre = solicitud.sucursal.nombre
                }
                def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitud);
                modelo = [configuracion: configuracion,
                    productoSolicitud: productoSolicitud,
                    avance: avance as JSON,
                    ponderaciones: ponderaciones as JSON,
                    sucursal: sucursal as JSON,
                    pasoActual:pasoActual]
            }
            modelo
        } else{
            redirect action: "index"
        }
    }
    def verificarSolicitudSms(){
        println params
        def cliente
        def solicitud
        def temporal
        def respuesta = [:]
        if (params.telefonoCelular){
            cliente = TelefonoCliente.findAllWhere(numeroTelefonico : params.telefonoCelular,tipoDeTelefono: TipoDeTelefono.get(2),vigente : true)
                def solicitudTemp
                cliente.each{
                    solicitud = SolicitudDeCredito.findWhere(cliente : it.cliente,token: params.token,solicitudVigente:true)
                    if (solicitud){
                        respuesta.ok = true
                        respuesta.tipo = "credito"
                        respuesta.solicitud = solicitud.id
                    }                   
                }
            if(!respuesta.ok){
                temporal = SolicitudTemporal.findWhere(token: params.token,telefonoCliente:params.telefonoCelular,solicitudVigente:true)
                if(temporal){
                    respuesta.ok = true
                    respuesta.tipo = "temporal"
                    respuesta.solicitud = temporal.id
                }else{
                    respuesta.error = true
                    respuesta.mensaje = "No existe solicitud relacionada al telefono proporcionado, o la solicitud ya no esta Vigente"
                }
            }
        } 
        else{
            respuesta.error = true
            respuesta.mensaje = "Por favor Ingrese un Número Valido"
        }
        render respuesta as JSON
        
    }
    
    def verificacion (){
        if(params.token){
           [token:params.token]        
        }else{
               def entidadFinanciera = EntidadFinanciera.get(6)
                    def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
                    [entidadFinanciera: entidadFinanciera, configuracion: configuracion]
        }
    }
    def resume(){
        println params
        def cliente
        def emailCliente
        def telefonoCliente
        if(params.token){
            def solicitud
            if(params.fechaDeNacimiento){
                Date fechaDeNacimiento =new Date().parse("dd/MM/yyyy HH:mm:ss", params.fechaDeNacimiento+" 00:00:00") 
                cliente = Cliente.findWhere(fechaDeNacimiento:fechaDeNacimiento)
                if(cliente){
                    solicitud = SolicitudDeCredito.findWhere(cliente : cliente,token: params.token,solicitudVigente:true)
                }
            }else if (params.correoElectronico){
                cliente = EmailCliente.findWhere(direccionDeCorreo : params.correoElectronico,vigente : true)
                if(cliente){
                   solicitud = SolicitudDeCredito.findWhere(cliente : cliente.cliente,token: params.token,solicitudVigente:true)
                }
            }
            else if ((params.telefonoCelular && params.solicitudId) && params.tipo=="credito"){
                        solicitud = SolicitudDeCredito.findWhere(id:params.solicitudId as long,token:params.token)
            }
            if(solicitud){
                def datosRecuperados = solicitudService.continuarSolicitud(solicitud)
                def ultimoPaso = datosRecuperados.ultimoPaso
                session.yaUsoLogin = true
                session.ef = datosRecuperados.entidadFinanciera
                session.configuracion = datosRecuperados.configuracion
                session.cotizador = datosRecuperados.cotizador
                session.identificadores = datosRecuperados.identificadores
                session.tiposDeDocumento = datosRecuperados.tiposDeDocumento
                session["pasoFormulario"] = datosRecuperados.pasoFormulario
                session.token = datosRecuperados.token
                session.shortUrl = datosRecuperados.shortUrl
                session.estadoRecuperacion = datosRecuperados.llenado
                println "Estado: " + session.estadoRecuperacion
                params.keySet().asList().each { params.remove(it) }
                forward action:'formulario', params: [paso: ultimoPaso]
            } else {
                def temporal
                if((params.telefonoCelular && params.solicitudId) && params.tipo=="temporal"){
                   temporal = SolicitudTemporal.findWhere(token: params.token,id:params.solicitudId as long )
                }else if (params.correoElectronico){
                    temporal = SolicitudTemporal.findWhere(token: params.token,emailCliente:params.correoElectronico,solicitudVigente:true )
                }
                if(temporal){
                    def datosRecuperados = solicitudService.armarDatosTemporales(temporal)
                    println datosRecuperados
                    session.yaUsoLogin = false
                    session.ef = datosRecuperados.ef
                    session.cotizador = datosRecuperados.cotizador
                    session.identificadores = datosRecuperados.identificadores
                    session.configuracion = datosRecuperados.configuracion
                    session.token = datosRecuperados.token
                    session.shortUrl = datosRecuperados.shortUrl
                    session.cargarImagen = datosRecuperados.cargarImagen
                    session.respuestaEphesoft = datosRecuperados.prefilled
                    params.keySet().asList().each { params.remove(it) }
                    forward action:'formulario', params: [paso: datosRecuperados.ultimoPaso]
                } else {
                    def entidadFinanciera = EntidadFinanciera.get(6)
                    def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
                    [entidadFinanciera: entidadFinanciera, configuracion: configuracion]
                }
            }
        }
    }
    
    def obtenerSucursales(){
        println params
        def respuesta = [:]
        if(params.cp){
            def cp = params.cp?.replaceFirst('^0+(?!$)', '')
            def codigo = CodigoPostal.findWhere(codigo: cp)
            if(codigo){
                def sucursales = SucursalEntidadFinanciera.findAllWhere(entidadFinanciera: session.ef, estado: codigo.municipio.estado)
                if(sucursales) {
                    respuesta = []
                    sucursales.each { sucursal ->
                        def registro = [:]
                        registro.coordenadas = [:]
                        registro.id = sucursal.id
                        registro.coordenadas.lat = sucursal.latitud
                        registro.coordenadas.lng = sucursal.longitud
                        registro.ubicacion = sucursal.ubicacion
                        registro.numeroDeSucursal = sucursal.numeroDeSucursal
                        registro.nombre = sucursal.nombre
                        respuesta << registro
                    }
                } else {
                    respuesta.noHaySucursales = true
                    respuesta.mensaje = ""
                }
            } else {
                respuesta.noHaySucursales = true
                respuesta.mensaje = ""
            }
        } else {
            respuesta.error = true
            respuesta.mensaje = ""
        }
        render respuesta as JSON
    }
    
    def enviarShortUrl() {
        def respuesta = [:]
        def toPhone = (session.cotizador?.telefonoCliente ?: session["pasoFormulario"]?.telefonoCliente?.telefonoCelular)
        if(toPhone){
            toPhone = toPhone.replaceAll('-', '') 
            if(smsService.sendShortUrl(toPhone, session.shortUrl, session.configuracion)){
                respuesta.mensajeEnviado = true
                def solicitud = SolicitudTemporal.get(session.identificadores.idSolicitudTemporal)
                if(solicitud){
                    solicitud.cargarImagen = true
                    solicitud.save(flush:true)
                }
            } else {
                respuesta.mensajeEnviado = false
            }
        } else {
            respuesta.mensajeEnviado = false
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
        println session.pasoFormulario
        respuesta = perfiladorService.guardarOferta(session.ofertas, session.pasoFormulario, session.identificadores , params)
        render respuesta as JSON
    }
    
    def printReport(){
        println params
        def mapa = []
        def respuesta = [:]
        def configuracion = session.configuracion
        def productoSolicitud = ProductoSolicitud.get(params.idProductoSolicitudPrint as long)
        def resultadoMotorDeDecision = ResultadoMotorDeDecision.findWhere(solicitud: productoSolicitud.solicitud)
        respuesta.folio = ("" + productoSolicitud?.solicitud?.folio).padLeft(6, '0')
        respuesta.montoDelCredito = productoSolicitud.montoDelCredito
        respuesta.plazos = productoSolicitud.plazos
        respuesta.nomenclatura = productoSolicitud.periodicidad.nomenclatura
        respuesta.montoDelSeguroDeDeuda = productoSolicitud.montoDelSeguroDeDeuda
        respuesta.nombreDelProducto = productoSolicitud.producto?.nombreDelProducto
        respuesta.cat =  (productoSolicitud?.producto?.cat) ? ((productoSolicitud?.producto?.cat * 100).round(2)) : 0 
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
        respuesta.perfilador = false
        respuesta.origen= "solicitud"
        respuesta.nombreComercial = configuracion?.nombreComercial?.toUpperCase()
        respuesta.nombreCliente = productoSolicitud?.solicitud?.cliente
        respuesta.email = session.cotizador.emailCliente
        mapa << respuesta
        params._format= "PDF"
        chain(controller: "jasper", action: "index", model: [data: mapa], params:params)
    }
}
