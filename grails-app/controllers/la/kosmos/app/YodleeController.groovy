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


class YodleeController {

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
            depositosPromedio =  0
            retirosPromedio =  0
            saldoPromedio = 0
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
}
