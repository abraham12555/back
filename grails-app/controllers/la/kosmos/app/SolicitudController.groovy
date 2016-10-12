package la.kosmos.app

import grails.converters.JSON
import java.awt.image.BufferedImage
import java.text.SimpleDateFormat
import javax.imageio.ImageIO
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
	
    def saltEdgeService
    def buroDeCreditoService
	
    int timeWait = 500
    def maximumAttempts = 100
    def formatoFecha = "dd-MM-yyyy"
    def razonSocial = "Kosmos Soluciones Digitales JS, SA de CV"
		                       
    def jsonSlurper = new JsonSlurper()
    grails.gsp.PageRenderer groovyPageRenderer
    Random rand = new Random() 

	
	
    def index() {
        session["datosPaso1"] = null
        session["datosPaso2"] = null
        session["datosPaso3"] = null
        session["datosPaso4"] = null
        session["datosPaso5"] = null
        session["datosPaso6"] = null
        session.respuestaEphesoft = null
        session.tiposDeDocumento = null
        redirect action: "formulario"
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
    }

	
    def authenticate() {
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
                }
            }
            if (!request.containsKey("customer_id")) {
                response.error_class = "Existe un problema de Comunicaci�n, Favor de Volver a intentarlo."
                render response as JSON
            }
	
            def login = fixJson(saltEdgeService.findLogin(request.customer_id))
            def credentials = [:]
            request.provider_code = request.provider_code + "_mx"
            switch (request.provider_code) {
            case "bancomer_mx":
                credentials.account = request.login
                credentials.password = request.password
                break;
            case "hsbc_mx":
                credentials.login = request.login
                credentials.password = request.password
                credentials.login_method = request.login_method
                break;
            default:
                credentials.login = request.login
                credentials.password = request.password
                break;
            }
			
            println "CREDENCIALES-------"+credentials
			
            if (login.data == null) {
                println "Creando Login"
                login = saltEdgeService.createLogin(request.customer_id, request.provider_code, credentials)
                if (login.error_class) {
                    def deleteUser = saltEdgeService.deleteUser(request.customer_id)
                    response.error_class = "Existe un problema de Comunicaci�n, Favor de Volver a intentarlo."
                    render response as JSON
                }
            }
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
                    render response as JSON
                } else {
                    response.params = params
                    def deleteUser = saltEdgeService.deleteUser(request.customer_id)
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
                response.accounts_resume = accountsResume(login.data.id)
                render response as JSON
            }
        } catch (Exception e) {
            println "Exception:" + e
            def deleteUser = saltEdgeService.deleteUser(request.customer_id)
            session["login_id"] = null
            session["customer_id"] = null
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
            response.error_class = login.data.last_attempt.fail_message
        }
		
        //println response as JSON
        render response as JSON
    }
	
    def accountsResume(def login_id){
        def accounts_resume = []
        def accounts = saltEdgeService.accounts(login_id)
        accounts.data.collect { account ->
            def account_temp = 	transactions(account.id)
            def account_resume = [:]
            def datosPaso = [:]
            account_resume.depositoPromedio = account_temp.depositoPromedio
            account_resume.retiroPromedio = account_temp.retiroPromedio
            account_resume.saldoPromedio = account_temp.saldoPromedio
            accounts_resume.add(account_resume)
            if(account_temp.depositoPromedio != 0){  //Verificar Al Momento solo es una cuenta que no este en 0
                datosPaso.depositoPromedio = account_resume.depositoPromedio
                datosPaso.retiroPromedio = account_resume.retiroPromedio
                datosPaso.saldoPromedio = account_resume.saldoPromedio
                datosPaso.login_id=login_id
                session["datosPaso4"] = datosPaso
            }
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
		
    def transactions(def account_id) {
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
	
    def formulario() {
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
            modelo = [tipoDeContratoList: TipoDeContrato.list(), paso:paso, generales: session[("datosPaso" + paso)]]
            break;
        case 4:
            println "paso4"
            modelo = [paso:paso, generales: session[("datosPaso" + paso)]]
            break;
        case 5:
            println "paso5"
            modelo = [paso:paso, generales: session[("datosPaso" + paso)]]
            break;
        case 6:
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
        if(params.siguientePaso){
            def modelo = [:]
            def paso =  params.siguientePaso as int
            def pasoAnterior =  params.pasoAnterior as int
            session[("datosPaso" + pasoAnterior)] = solicitudService.construirDatosTemporales(params, pasoAnterior, session.idCLiente)
            if(session[("datosPaso" + pasoAnterior)]?.clienteGenerado){
                session.idCLiente = session[("datosPaso" + pasoAnterior)]?.idCliente
            }
            if(paso == 1 || paso == 2){
                session[("datosPaso" + paso)]?.llenadoPrevio = session.respuestaEphesoft?.llenadoPrevio
            } 
            if(paso == 1){
                modelo = [estadoList:Estado.findAll(),
                    estadoCivilList:EstadoCivil.findAll(),
                    temporalidadList:Temporalidad.findAll(),
                    escolaridadList:NivelEducativo.findAll(),
                    generoList: Genero.list(),
                    datosLogin:session.datosLogin,
                    tipoLogin:session.tipoLogin,
                    logueado: session.yaUsoLogin,
                    paso:paso, generales: (session[("datosPaso" + paso)]?:session.respuestaEphesoft)]
            } else if(paso == 2){
                modelo = [estadoList:Estado.findAll(),
                    municipioList:Municipio.findAll(),
                    tiposDeVivienda: TipoDeVivienda.findAllWhere(activo:true),
                    temporalidadList:Temporalidad.findAll(),
                    paso:paso, generales: (session[("datosPaso" + paso)]?:session.respuestaEphesoft)]
            } else if(paso == 3){
                modelo = [tipoDeContratoList: TipoDeContrato.list(), paso:paso, generales: session[("datosPaso" + paso)]]
            } else if(paso == 4){
                modelo = [paso:paso, generales: session[("datosPaso" + paso)]]
            } else if(paso == 5){
                def municipio = null
                println "DATPS::"+session["datosPaso2"]
                if(session["datosPaso2"] != null && session["datosPaso2"].municipio){
                    municipio= Municipio.findById(session["datosPaso2"].municipio)
                }
                modelo = [paso:paso, generales: session[("datosPaso" + paso)], personales: session[("datosPaso" + 1)], direccion: session[("datosPaso" + 2)],municipio:municipio,razonSocial:razonSocial]
            } else if(paso == 6){
                modelo = [paso:paso, generales: session[("datosPaso" + paso)], documentosSubidos: session.tiposDeDocumento]
            }  else if(paso == 8){
                //TODO guardar todo
            }
            render(template: ("paso"+params.siguientePaso), model: modelo)
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
    
    def consultarEphesoft(){
        println params
        def fileNames = request.getFileNames()
        def listaDeArchivos = []
        def mapa
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
        def respuesta = ephesoftService.ocrClassifyExtract(listaDeArchivos,params.docType);
        if(respuesta?.vigente == true){
            session.respuestaEphesoft = respuesta
            session.tiposDeDocumento = solicitudService.controlDeDocumentos(session.tiposDeDocumento, params.docType)
        }
        println respuesta
        render respuesta as JSON
    }
    
    def consultarBuroDeCredito(){
        println "CONSULTA DE BURO DE CREDITO...." 
        def respuesta = buroDeCreditoService.callWebServicePersonasFisicas(params,session["datosPaso1"],session["datosPaso2"])
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
            def codigo = CodigoPostal.get(params.idCodigoPostal as long)
            respuesta.estado = codigo.municipio.estado
            respuesta.municipio =  codigo.municipio
        }
        println respuesta
        render respuesta as JSON
    }
}
