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
    int timeWait = 500
    def maximumAttempts = 100
    def formatoFecha = "dd-MM-yyyy"
		                       
    def jsonSlurper = new JsonSlurper()
    grails.gsp.PageRenderer groovyPageRenderer
    Random rand = new Random() 

	
    def index() { }
	
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
            default:
                credentials.login = request.login
                credentials.password = request.password
                break;
            }
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
	
    def paso_1(){
        //params.datos_fb="{\"id\": \"1273758305972971\",\"name\": \"Jorge Medina\",\"birthday\": \"04/24/1985\",\"education\": [{\"school\": {\"id\": \"329234880539960\",\"name\": \"Instituto Tecnológico de Toluca\"},\"type\": \"High School\",\"year\": {\"id\": \"141778012509913\",\"name\": \"2008\"},\"id\": \"360611733954304\"},{\"concentration\": [{\"id\": \"149668418423502\",\"name\": \"Sistemas Computacionales\"}],\"degree\": {\"id\": \"195120407185348\",\"name\": \"13/02/2009\"},\"school\": {\"id\": \"113846665298870\",\"name\": \"Instituto Tecnologico de Toluca\"},\"type\": \"College\",\"id\": \"208314902517322\"}],\"email\": \"tazvoit@hotmail.com\",\"first_name\": \"Jorge\",\"gender\": \"male\",\"last_name\": \"Medina\",\"picture\": {\"data\": {\"is_silhouette\": false,\"url\": \"https://scontent.xx.fbcdn.net/v/t1.0-1/p50x50/13100952_1327839547231513_1803219328987116718_n.jpg?oh=3d8f6deb02926a473ac77bebcf2ddb85&oe=57F1E029\"}},\"relationship_status\": \"Casado\"}";
        def tipo_login;
        def datos_login;
        if(params.datos_fb.toString()!= 'null'){
            System.out.println(":: Se inicio Sesión con FB ::"+params.datos_fb.toString());
            datos_login = params.datos_fb.toString()
            tipo_login="FB";
        }else if(params.datos_google.toString()!= 'null'){
            System.out.println(":: Se inicio Sesión con Google ::"+params.datos_google.toString());
            datos_login = params.datos_google.toString();
            tipo_login="Google";
        }else{
            System.out.println(":: No se inicio Sesión ::");
            tipo_login="none";
        }

        [nacionalidadList:Nacionalidad.findAll(),
            estadoCivilList:EstadoCivil.findAll(),
            estadoList:Estado.findAll(),
            municipioList:Municipio.findAll(),
            temporalidadList:Temporalidad.findAll(),
            datos_login:datos_login,
            tipo_login:tipo_login]
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
        } else if (params.origen == 'noLogin'){
            println(":: No se inicio Sesión ::");
            tipoLogin="none";
        }
        if(params.paso) {
            println "Paso a mostrar: " + params.paso
            def paso = params.paso as int
            def templateContent
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
        } else {
            println "No se recibio el parametro ...."
            modelo = [error: "La ruta especificada no existe"]
            render(template: "error", model: modelo)
        }
    }

    def paso1_1() {
        //DATOS DE USUARIO.
        Cliente cliente;
        if (session["cliente"] instanceof Cliente) {
            System.out.println("Variable en Sesion");
            cliente = session["cliente"]
        } else {
            System.out.println("Creando Variable");
            cliente = new Cliente();
        }

        String[] nombre = params.NOMBRE_USUARIO.toString().split(" ");
        int num = nombre.size()
        switch (num) {
        case 1:
            cliente.setNombre(nombre[0]);
            break;

        case 2:
            cliente.setNombre(nombre[0]);
            cliente.setApellidoPaterno(nombre[1]);
            break;

        case 3:
            cliente.setNombre(nombre[0]);
            cliente.setApellidoPaterno(nombre[1]);
            cliente.setApellidoMaterno(nombre[2]);
            break;

        }

        System.out.println("Nacionalidad" + params);
        String date_s = params.ANIO + "/" + params.MES + "/" + params.DIA;
        SimpleDateFormat dt = new SimpleDateFormat("yyyyy/mm/dd");
        Date fechaNacimiento = dt.parse(date_s);
        cliente.fechaDeNacimiento = fechaNacimiento;
        cliente.nacionalidad = Nacionalidad.findById(new Integer(params.nacionalidad))
        cliente.genero = Genero.findById(1);
        cliente.estadoCivil = EstadoCivil.findByNombre(params.ESTADO_CIVIL);
        session["cliente"] = cliente;

        render "paso1_1"
    }

    def paso1_2(){
        //DATOS COMPLEMENTARIOS CLIENTE
        ArrayList<TelefonoCliente> telefonos
        if( session["telefonosCliente"] instanceof ArrayList){
            telefonos = session["telefonosCliente"]
        }else{
            telefonos = new ArrayList<TelefonoCliente>();
        }

        TelefonoCliente  telefonoCliente = new TelefonoCliente()
        telefonoCliente.setNumeroTelefonico(params.telephone);
        telefonoCliente.setTipoDeTelefono(TipoDeTelefono.findById(1));
        //telefonoCliente.setCliente(cliente);
        telefonos.add(telefonoCliente);
        telefonoCliente = new TelefonoCliente();
        telefonoCliente.setNumeroTelefonico(params.cellphone);
        telefonoCliente.setTipoDeTelefono(TipoDeTelefono.findById(2));
        //telefonoCliente.setCliente(cliente);
        telefonos.add(telefonoCliente);

        session["telefonosCliente"] = telefonos;
        System.out.println("NUMEROS TELEFONICOS CLIENTE OK" +params);

        render "paso1_2"
    }

    def paso2_1(){
        //DIRECCION DEL CLIENTE
        DireccionCliente  direccion = new DireccionCliente();
        if( session["direccion"] instanceof DireccionCliente){
            System.out.println("Variable en Sesion");
            direccion =  session["direccion"];
        }else{
            direccion = new DireccionCliente();
        }

        direccion.setCalle(params.calle);
        direccion.setNumeroExterior(params.noexterior);
        direccion.setNumeroInterior(params.nointerior);
        direccion.setCodigoPostal(params.codigopostal);

        //Colonia colonia=Colonia.findById(new Integer(params.colonia));
        Municipio municipio = colonia.getMunicipio()
        direccion.setColonia(colonia);
        direccion.setMunicipio(municipio)
        direccion.setEstado(municipio.getEstado())
        //direccion.cliente=cliente;

        session["direccion"] = direccion;

        render "paso2_1"
    }

    def paso3_1(){
        EmpleoCliente empleoCliente =  new EmpleoCliente();
        if( session["empleoCliente"] instanceof EmpleoCliente){
            System.out.println("Variable en Sesion");
            empleoCliente  = session["empleoCliente"]
        }else{
            empleoCliente =  new EmpleoCliente();
        }
        empleoCliente.nombreDeLaEmpresa=params.empresa;
        empleoCliente.puesto=params.puesto;
        empleoCliente.antiguedad=new Integer(params.periodo);
        empleoCliente.temporalidad=Temporalidad.findById(new Integer(params.contrato));

        session["empleoCliente"] = empleoCliente;
        System.out.println( "EMPLEO CLIENTE OK" +params)
        redirect(action: "guardarCliente" )


    }

    def guardarCliente(){
        if( session["cliente"] instanceof Cliente){
            System.out.println("Variable en Sesion Final ");

            Cliente cliente = session["cliente"]
            cliente.save(flush:true);
            System.out.println("REGISTROS cliente" + cliente.toString());
            EmpleoCliente empleoCliente = session["empleoCliente"]
            empleoCliente.cliente=cliente;
            empleoCliente.save(flush:true);
            System.out.println("REGISTROS empleoCliente" + empleoCliente.toString());
            DireccionCliente  direccion = session["direccion"]
            direccion.cliente=cliente;
            direccion.save(flush:true);
            System.out.println("REGISTROS direccion" + direccion.toString());
            ArrayList<TelefonoCliente> telefonos = session["telefonosCliente"]
            for(int i=0;i<telefonos.size();i++){
                TelefonoCliente tel = telefonos.get(i);
                tel.cliente=cliente;
                tel.save(flush:true);
                System.out.println("REGISTROS tel" + tel.toString());
            }
            System.out.println("REGISTROS GUARDADO" + cliente.toString());
            redirect(action: "paso4_1")

        }else{
            System.out.println("ERROR NO SE PUE GUARDAR")
            redirect(action: "error")
        }
        render "paso3_1"

    }

    def cambiarPaso(){
	println params
        if(params.siguientePaso){
            def modelo = [:]
            def paso =  params.siguientePaso as int
            def pasoAnterior =  params.pasoAnterior as int
            session[("datosPaso" + pasoAnterior)] = solicitudService.construirDatosTemporales(params, pasoAnterior)
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
                modelo = [paso:paso, generales: session[("datosPaso" + paso)]]
            } else if(paso == 6){
                modelo = [paso:paso, generales: session[("datosPaso" + paso)], documentosSubidos: session.tiposDeDocumento]
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
        println params
        def respuesta = [:]
        int max = 10  
        def test = rand.nextInt(max+1)
        sleep(3000)
        if(test % 2 == 0){
            respuesta.status = 200
            render respuesta as JSON
        } else {
            //render(template: "/templates/solicitud/paso4/errorConsultaBancaria", model: [intentos: (params.intentos ? ((params.intentos as int) + 1) : (0))])
            respuesta.error = 500
            respuesta.intentos = (params.intentos ? ((params.intentos as int) + 1) : (0))
            render respuesta as JSON
        }
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
