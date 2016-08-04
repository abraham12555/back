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
    
    def loginYoddle = null
    def providers = null
    def coblogin = null
    def responseAdd = null
    def responseForSite= null
    def respondeForMFA = null
    def responseRefreshInfo = null
    def getSiteAccounts1 = null
    def getItemSummaryForItem1 = null
    def getSiteLoginForm = null
    def executeUserSearchRequest = null
    def getUserTransactions = null
    /*Parametros Obtenidos Interfaz*/
    def bancoSel = null
    def itemId = null
    def ephesoftService
	
	
    /*Parametros Configuracion Yoddle*/
    def parametros = ['cobrandLogin'   : 'sbCobtazvoit',
		'cobrandPassword': '07db7354-17c9-4248-850f-7e2f98646e31',
		'server'         : 'https://rest.developer.yodlee.com/services/srest/restserver/',
		'authenticate'          : 'v1.0/authenticate/',
		'providers'             : 'v1.0/jsonsdk/SiteTraversal/searchSite',
		'addAccount'            : 'v1.0/jsonsdk/SiteAccountManagement/addSiteAccount1',
		'getMFAResponseForSite' : 'v1.0/jsonsdk/Refresh/getMFAResponseForSite',
		'putMFARequestForSite'  : 'v1.0/jsonsdk/Refresh/putMFARequestForSite',
		'getSiteRefreshInfo'    : 'v1.0/jsonsdk/Refresh/getSiteRefreshInfo',
		'getSiteAccounts1'      : 'v1.0/jsonsdk/SiteAccountManagement/getSiteAccounts1', 
		'getItemSummaryForItem1': 'v1.0/jsonsdk/DataService/getItemSummaryForItem1',
		'getSiteLoginForm'      : 'v1.0/jsonsdk/SiteAccountManagement/getSiteLoginForm',
		'getUserTransactions'   : 'v1.0/jsonsdk/TransactionSearchService/getUserTransactions',
		'executeUserSearchRequest' : 'v1.0/jsonsdk/TransactionSearchService/executeUserSearchRequest'
		                       
    ]
    def usuarios = ["login1":"sbMemtazvoit2","password1":"sbMemtazvoit2#123"]
    def bancos = ["banamex":"20762","bancomer":"16627","hsbc":"20159","santander":"20119","banorte":"20763"]
    def tiempoEsperaRequest = 2000 //Milisegundos
    def maxIntentos = 100  //Numero Maximo de Reintentos Peticiones Yoddle
	
    grails.gsp.PageRenderer groovyPageRenderer
    Random rand = new Random() 
    def formatoFecha = "dd-MM-yyyy"

    def index() { }
	
    def login(){}

	
    def consultarLoginBancos() {
        /**
         * Flujo de Consulta.
         */
        coblogin = restRequestYoddle("coblogin",null)
        loginYoddle = restRequestYoddle("login",coblogin)
        bancoSel = params.banco
        getSiteLoginForm = restRequestYoddle("getSiteLoginForm", bancos.get(bancoSel))
        render getSiteLoginForm as JSON
    }
	
    def flujoConsultaBancos(){
        int intentos=0
        println "PARAMETROS RECIBIDOS" + params
        switch(params.paso){
        case "addAccount":
            println("Peticion para Agregar Cuenta....")
            responseAdd = restRequestYoddle("addAccount",JSON.parse(params.data))
            boolean retry=true
            intentos=0
            if("${responseAdd.siteRefreshInfo.siteRefreshStatus.siteRefreshStatus}" == "REFRESH_TRIGGERED"){
                println "Respuesta::::::::::::::::"
                if("${responseAdd.siteRefreshInfo.siteRefreshMode.refreshMode}" == "MFA"){
                    while(retry == true && intentos<=maxIntentos){
                        responseForSite = restRequestYoddle("getMFAResponseForSite",null)
                        if("${responseForSite.retry}" == "false"){
                            retry = false
                        }else{
                            intentos++
                        }
                        Thread.sleep(tiempoEsperaRequest)
                    }
                    render responseForSite as JSON
                    break
                }else{
                    println("Se registra satisfactoriamente ${responseAdd}")
                    executeUserSearchRequest = restRequestYoddle("executeUserSearchRequest", null)
                    calcularSaldosPromedios()
                    brek
                }
            }else{
                println("Se registra satisfactoriamente ${responseAdd}")
                executeUserSearchRequest = restRequestYoddle("executeUserSearchRequest", null)
                calcularSaldosPromedios()
                break
            }
            break
			
        case "mfaLogin":
            println("Peticion MFA login ......")
            respondeForMFA = restRequestYoddle("putMFARequestForSite",JSON.parse(params.data))
            boolean retry=true
            intentos=0
            if("${respondeForMFA.primitiveObj}"=="true"){
                while(retry==true  && intentos <= maxIntentos){
                    responseForSite = restRequestYoddle("getMFAResponseForSite",null)
                    if("${responseForSite.retry}"=="false"){
                        retry=false
                    }else{
                        intentos++
                    }
                    println "Reintentando....."
                    Thread.sleep(tiempoEsperaRequest)
                }
                if(retry == false){
                    //println "Actualizando....."
                    responseRefreshInfo = restRequestYoddle("getSiteRefreshInfo",null)
                    //println "Consultar la Cuenta......."
                    //getSiteAccounts1 = restRequestYoddle("getSiteAccounts1",null)
                    println "Consultando Resumen......."
                    //getItemSummaryForItem1 = restRequestYoddle("getItemSummaryForItem1",null)
                    executeUserSearchRequest = restRequestYoddle("executeUserSearchRequest", null)
                    //getUserTransactions = restRequestYoddle("getUserTransactions", null)
                    calcularSaldosPromedios()
                    break
                }else{
                    println "No se obtuvo respuesta Favor de Intentarlo mas Tarde."
                    render responseForSite as JSON	
                    break
                }
            }else{
                println "No se actualizo Correctamente"
                render respondeForMFA as JSON
                break
            }
            break
        }
		
    }
	
    def calcularSaldosPromedios(){
        def respuesta = [:]
        BigDecimal depositosPromedio = new BigDecimal("0.0")
        BigDecimal retirosPromedio = new BigDecimal("0.0")
        BigDecimal saldoPromedio =  new BigDecimal("0.0")
        println "RETIROS EJECUNTANDO SALDO PROMEDIOS" + executeUserSearchRequest.numberOfHits
        if(executeUserSearchRequest.numberOfHits > 0){
            executeUserSearchRequest.searchResult.transactions.collect{ transaction  ->
                println "TRANSACCIONES " + transaction
                if(transaction.transactionType == "credit"){           //ABONO
                    try{
                        depositosPromedio+= new BigDecimal("${transaction.amount.amount}")
                    }catch(Exception e){
                        depositosPromedio+= new BigDecimal("0.0")
                    }
                }else if (transaction.transactionType == "debit"){  // RETIRO
                    try{
                        retirosPromedio+= new BigDecimal("${transaction.amount.amount}")
                    }catch(Exception e){
                        retirosPromedio+= new BigDecimal("0.0")
                    }
                    try{
                        saldoPromedio+= new BigDecimal("${transaction.account[0].accountBalance.amount}")
                    }catch(Exception e){
                        saldoPromedio+= new BigDecimal("0.0")
                    }	
                }
            }
            try{
                saldoPromedio = new BigDecimal("${executeUserSearchRequest.creditTotalOfTxns}")
            }catch(Exception e){
                saldoPromedio = new BigDecimal("8500.0")
            }
        }else{
            println "No se encontro informacion en la cuenta...."
            depositosPromedio =  rand.nextInt(18000+1)
            retirosPromedio =  rand.nextInt(10000+1)
            saldoPromedio = rand.nextInt(8000+1)
        }
        println "retirosPromedio::" +retirosPromedio
        println "depositosPromedio::" +depositosPromedio
        println "saldoPromedio::" +saldoPromedio
		
        //if(retirosPromedio != 0){retirosPromedio = retirosPromedio/90 }
        //if(depositosPromedio != 0){depositosPromedio = depositosPromedio/90 }
        //if(saldoPromedio !=0 ){saldoPromedio = saldoPromedio/90 }
		
        respuesta.depositosPromedio =  depositosPromedio
        respuesta.retirosPromedio =  retirosPromedio
        respuesta.saldoPromedio = saldoPromedio
		
        render respuesta as JSON
    }
	
	
    def restRequestYoddle(def tipo,def responseJson){
        HttpClient client = new DefaultHttpClient();
        HttpPost post;
        String resp = "";
        //List nameValuePairs = new ArrayList(1);
        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        switch(tipo){
        case "coblogin":
            post = new HttpPost("${parametros.server}${parametros.authenticate}coblogin")
            postParameters.add(new BasicNameValuePair("cobrandLogin", "${parametros.cobrandLogin}"));
            postParameters.add(new BasicNameValuePair("cobrandPassword", "${parametros.cobrandPassword}"));
            break
        case "login":
            post = new HttpPost("${parametros.server}${parametros.authenticate}login")
            postParameters.add(new BasicNameValuePair("cobSessionToken", "${responseJson.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("login", "${usuarios.login1}"))
            postParameters.add(new BasicNameValuePair("password", "${usuarios.password1}"))
            break
        case "getContentServiceInfoByRoutingNumber"	:
            post = new HttpPost("${parametros.server}${parametros.getContentServiceInfoByRoutingNumber}")
            postParameters.add(new BasicNameValuePair("cobSessionToken", "${responseJson.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("routingNumber", ""))
            postParameters.add(new BasicNameValuePair("notrim", "${usuarios.password1}"))
			
            break
        case "providers":
            post = new HttpPost("${parametros.server}${parametros.providers}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
				
            postParameters.add(new BasicNameValuePair("siteSearchString", "${responseJson}"))
            break
			
        case "getSiteLoginForm":
            post = new HttpPost("${parametros.server}${parametros.getSiteLoginForm}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("siteId", "${responseJson}"))
            break
			
				
        case "addAccount":
            post = new HttpPost("${parametros.server}${parametros.addAccount}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("credentialFields.enclosedType", "${getSiteLoginForm.componentList[0].fieldInfoType}"))
            postParameters.add(new BasicNameValuePair("siteId", bancos.get(bancoSel)))
            /*Parametros de Configuracion*/
            int index =0
            getSiteLoginForm.componentList.collect{ component  ->
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].displayName", "${component.displayName}"))
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].fieldType.typeName", "${component.fieldType.typeName}"))
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].name", "${component.name}"))
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].valueIdentifier", "${component.valueIdentifier}"))
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].valueMask", "${component.valueMask}"))
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].isEditable", "${component.isEditable}"))
                //Campo que trae el valor ingresado por el usuario.
                postParameters.add(new BasicNameValuePair("credentialFields["+index+"].value", responseJson.get(component.name))) 
                index++
            }
            break
				
        case "getMFAResponseForSite":
            post = new HttpPost("${parametros.server}${parametros.getMFAResponseForSite}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("memSiteAccId", "${responseAdd.siteRefreshInfo.memSiteAccId}"))
            break
				
        case "putMFARequestForSite":
            post = new HttpPost("${parametros.server}${parametros.putMFARequestForSite}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("memSiteAccId", "${responseAdd.siteRefreshInfo.memSiteAccId}"))
            postParameters.add(new BasicNameValuePair("userResponse.objectInstanceType", "com.yodlee.core.mfarefresh.MFATokenResponse"))
            postParameters.add(new BasicNameValuePair("userResponse.token", "${responseJson.token}"))
            break
        case "getSiteRefreshInfo":
            post = new HttpPost("${parametros.server}${parametros.getSiteRefreshInfo}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("memSiteAccId", "${responseAdd.siteRefreshInfo.memSiteAccId}"))
            break
        case "getSiteAccounts1":
            post = new HttpPost("${parametros.server}${parametros.getSiteAccounts1}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("siteAccountFilter.memSiteAccIds[1]", "${responseAdd.siteRefreshInfo.memSiteAccId}"))
            postParameters.add(new BasicNameValuePair("siteAccountFilter.itemSummaryRequired", "2"))
	
            break
				
        case "getItemSummaryForItem1":
            post = new HttpPost("${parametros.server}${parametros.getItemSummaryForItem1}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("itemId", "10215968"))
            postParameters.add(new BasicNameValuePair("dex.startLevel", "0"))
            postParameters.add(new BasicNameValuePair("dex.endLevel", "0"))
            postParameters.add(new BasicNameValuePair("dex.extentLevels[0]", "4"))
            postParameters.add(new BasicNameValuePair("dex.extentLevels[1]", "4"))
            break
				
        case "getUserTransactions":
            post = new HttpPost("${parametros.server}${parametros.getUserTransactions}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("searchFetchRequest.searchIdentifier.identifier", "${executeUserSearchRequest.searchIdentifier.identifier}")) // VARIABLE
            postParameters.add(new BasicNameValuePair("searchFetchRequest.searchResultRange.startNumber", "1"))
            postParameters.add(new BasicNameValuePair("searchFetchRequest.searchResultRange.endNumber", "250"))
            break
				
        case "executeUserSearchRequest":
            post = new HttpPost("${parametros.server}${parametros.executeUserSearchRequest}")
            postParameters.add(new BasicNameValuePair("cobSessionToken",  "${loginYoddle.userContext.cobrandConversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("userSessionToken", "${loginYoddle.userContext.conversationCredentials.sessionToken}"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.containerType", "all"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.higherFetchLimit", "500"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.lowerFetchLimit", "1"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.resultRange.endNumber", "500"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.resultRange.startNumber", "1"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchClients.clientId", "1"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchClients.clientName", "DataSearchService"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchFilter.currencyCode", "MXN"))

				
            //println "DATOS DE LA CUENTA ${getItemSummaryForItem1.itemData.accounts[0].itemAccountId} "
            def today = new Date()
            def yesterday = new Date() - 700 //No muestra informacion con el rango de 90 dias.
            def toDate = today.format(formatoFecha)
            def fromDate = yesterday.format(formatoFecha)
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchFilter.postDateRange.fromDate", fromDate)) //VARIABLE
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchFilter.postDateRange.toDate", toDate))   //VARIABLE
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchFilter.transactionSplitType", "ALL_TRANSACTION"))
            postParameters.add(new BasicNameValuePair("transactionSearchRequest.ignoreUserInput", "true"))
            //postParameters.add(new BasicNameValuePair("transactionSearchRequest.searchFilter.itemAccountId.identifier", "${getItemSummaryForItem1.itemData.accounts[0].itemAccountId}"))      //VARIABLE 10257724
            break			
        }
        post.setEntity(new UrlEncodedFormEntity(postParameters));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = '';
        while ((line = rd.readLine()) != null) {resp=resp+line;}
        println("TIPO "+tipo+" JSON::>"+resp)
        return JSON.parse(resp)
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
            if(params.datosFb.toString()!= 'null'){
                println(":: Se inicio Sesión con FB ::" + params.datosFb.toString())
                datosLogin = params.datosFb.toString()
                tipoLogin="FB";
                params.remove("datosFb")
            }else if(params.datosGoogle.toString()!= 'null'){
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
                    paso:paso]
                break;
            case 2:
                println "paso2"
                modelo = [estadoList:Estado.findAll(),
                    municipioList:Municipio.findAll(),
                    tiposDeVivienda: TipoDeVivienda.findAllWhere(activo:true),
                    temporalidadList:Temporalidad.findAll(),
                    paso:paso]
                break;
            case 3:
                println "paso3"
                modelo = [tipoDeContratoList: TipoDeContrato.list(), paso:paso]
                break;
            case 4:
                println "paso4"
                modelo = [paso:paso]
                break;
            case 5:
                println "paso5"
                modelo = [paso:paso]
                break;
            case 6:
                println "paso6"
                modelo = [paso:paso]
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
            if(paso == 1){
                modelo = [estadoList:Estado.findAll(),
                    estadoCivilList:EstadoCivil.findAll(),
                    temporalidadList:Temporalidad.findAll(),
                    escolaridadList:NivelEducativo.findAll(),
                    generoList: Genero.list(),
                    datosLogin:datosLogin,
                    tipoLogin:tipoLogin,
                    paso:paso]
            } else if(paso == 2){
                modelo = [estadoList:Estado.findAll(),
                    municipioList:Municipio.findAll(),
                    tiposDeVivienda: TipoDeVivienda.findAllWhere(activo:true),
                    temporalidadList:Temporalidad.findAll(),
                    paso:paso]
            } else if(paso == 3){
                modelo = [tipoDeContratoList: TipoDeContrato.list(), paso:paso]
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
