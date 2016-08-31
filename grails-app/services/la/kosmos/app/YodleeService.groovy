package la.kosmos.app

import grails.transaction.Transactional
import org.apache.http.HttpResponse;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.aspectj.apache.bcel.classfile.annotation.NameValuePair;

@Transactional
class YodleeService {
	
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
	
	def usuarios = ["login1":"sbMemtazvoit2","password1":"sbMemtazvoit2#123"]
	def bancos = ["banamex":"20762","bancomer":"16627","hsbc":"20159","santander":"20119","banorte":"20763"]
	def formatoFecha = "dd-MM-yyyy"

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
}
