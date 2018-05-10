package la.kosmos.rest

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.io.FileType
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import org.apache.commons.codec.binary.Base64

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLContext;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.BasicConfigurator;
import java.nio.file.Files;

import la.kosmos.app.ConfiguracionKosmos
import la.kosmos.app.CodigoPostal
import la.kosmos.app.SolicitudDeCredito
import la.kosmos.app.SolicitudTemporal
import java.security.MessageDigest
import org.awaitility.groovy.AwaitilityTrait
import org.awaitility.core.ConditionTimeoutException
import static org.awaitility.Awaitility.await
import static java.util.concurrent.TimeUnit.SECONDS
import org.codehaus.groovy.grails.io.support.IOUtils

@Transactional
class DocumentSenderService implements AwaitilityTrait{
    
    def bitacoraMitekService
    def generarBase64(def archivo) {
        def base64 = Base64.encodeBase64String(archivo)
        return base64
    }
    
    def send(def listaDeArchivos,def folio){
        send(listaDeArchivos, 1, null,null,folio)
    }
    
    def send(def listaDeArchivos, def total, def dossierId, def referencia, def folio) throws ClientProtocolException, IOException,
    NoSuchAlgorithmException, KeyManagementException {
        BasicConfigurator.configure();
        def configuracion = ConfiguracionKosmos.get(1)
        def datosArchivo = listaDeArchivos?.getAt(0);
        
        def bytesDelDocumento = IOUtils.copyToByteArray(datosArchivo.archivo)
        String API_KEY = configuracion.apiKeyMitek
        String USERNAME = configuracion.usernameMitek
        String URL = configuracion.urlMitek
        String CallBack_URL = configuracion.callbackUrlMitek
        if(dossierId){
            URL +="?dossierId=" + dossierId
            println "Sending reverse of idCard..."
        }
        
        Date now = new Date();
        
        if(referencia == null){
            referencia = generarReferencia(datosArchivo.nombreDelArchivo + "-" + (now.toString()))
        }
        
        def jsonRequest = [:]
        jsonRequest.ClientReference = referencia
        jsonRequest.CallbackUri = CallBack_URL
        jsonRequest.TotalNumberOfParts = total
        jsonRequest.CrossReferenceData = "null"
        jsonRequest.ImageData =  generarBase64(bytesDelDocumento)
        jsonRequest.IncludeImagesInCallback = "true"
        jsonRequest.IncludeAdditionalImagesInCallback = ["Signature"]
        jsonRequest.StrongIDBasic = "true" 
        jsonRequest.StrongIDExpert = "false"
        jsonRequest.StrongIDPlusAuto = "false"
        jsonRequest.StrongIDPlusManual = "false"
        jsonRequest.StrongIDPlusManualVerifications = [Proofreading : "true", VerificationPhotoIntegration : "false",VerificationFont : "false",VerificationData : "true"]
        
        def respuesta = [:]
        respuesta.referencia = referencia;
        respuesta.dossierId = "";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        format.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formattedDate = format.format(now) + "Z";

        String stringToSign = "POST" + "\n" + formattedDate + "\n" + USERNAME;
        byte[] utfStringToSign = stringToSign.getBytes("UTF8");
        byte[] utfAPIKey = API_KEY.getBytes("UTF8");       

        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(URL);

        SSLContext sslContext = SSLContext.getInstance("SSL");

        X509TrustManager tm = new TrustEveryThing();

        X509HostnameVerifier verifier = new TrustAllNames();

        // set up a TrustManager that trusts everything
        sslContext.init(null, [tm] as TrustManager[], new SecureRandom());

        SSLSocketFactory sf = new SSLSocketFactory(sslContext, verifier);
        Scheme sch = new Scheme("https", 443, sf);
        httpClient.getConnectionManager().getSchemeRegistry().register(sch);

        String signature = HMAC_SHA256(utfAPIKey, utfStringToSign);

        httpPost.addHeader("Timestamp", formattedDate);
        httpPost.addHeader("Authentication", USERNAME + ":" + signature);
        httpPost.addHeader("Content-Type", "application/json");

        for (Header h : httpPost.getAllHeaders()) {
            System.out.println(h.getName() + " : " + h.getValue());
        }

        StringEntity jsonEntity = new StringEntity((jsonRequest as JSON).toString());

        httpPost.setEntity(jsonEntity);

        HttpResponse response = httpClient.execute(httpPost);

        respuesta.statusCode = response.getStatusLine().getStatusCode();

        HttpEntity entity = response.getEntity();
        respuesta.html = "";
        if (entity != null) {
            InputStream responseStream = entity.getContent();

            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder sb = new StringBuilder();

            String line = null;
            try {
                if(respuesta.statusCode == 200){
                    bitacoraMitekService.registrarBitacoraMitek(folio,false,false,"NINGUNO","DOCUMENTO ENVIADO")
                    sb.append(reader.readLine()?.replaceAll("\"",""))
                } else {
                    bitacoraMitekService.registrarBitacoraMitek(folio,false,true,"REGRESO UN CODIGO DIFERENTE A 200","DOCUMENTO ENVIADO")
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                }
            } catch (RuntimeException ex) {
                    bitacoraMitekService.registrarBitacoraMitek(folio,false,true,ex.getStackTrace().toString(),"DOCUMENTO ENVIADO")
            } finally {
                responseStream.close();
            }
            respuesta.dossierId = sb.toString();
        }
        println "Respuesta Preliminar: " + respuesta
        datosArchivo.archivo = new ByteArrayInputStream(bytesDelDocumento)
        return respuesta;
    }

    def HMAC_SHA256(final byte[] accessAPIKey, final byte[] s) {

        Mac sha256_HMAC = null;
        String result = "";
        try {
            sha256_HMAC = Mac.getInstance("HmacSHA256");

            final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(
                accessAPIKey, "HmacSHA256");
            sha256_HMAC.init(secret_key);

            final byte[] mac_data = sha256_HMAC.doFinal(s);

            result = new String(Base64.encodeBase64(mac_data));
            System.out.println("Result: " + result);

        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    
    def verificarRespuestaMitek(def referencia, def solicitud, def solicitudTemporal, def dossierId,def folio){
        def datosRecibidos = false
        def datosDocto = false
        def validacionesDocto = false
        def mapa = [:]
        def fechaInicial 
        def fechaFinal
        try {
            fechaInicial =  new Date()
            await().atMost(60, SECONDS).until { consultarResultadosMitek(referencia, dossierId) }
            datosRecibidos = true
        }catch(ConditionTimeoutException e){
            bitacoraMitekService.registrarBitacoraMitek(folio,false,true,"NO SE RECIBIO RESPUESTA EN 60 ","SIN RESPUESTA",60)
            mapa.error = "No se ha recibido respuesta en 60 seguros. Intente nuevamente por favor."
        }
        if (datosRecibidos) {
            fechaFinal = new Date()
            def tiempoEnContestar = ((fechaFinal.getTime() - fechaInicial.getTime())/1000)
            bitacoraMitekService.registrarBitacoraMitek(folio,true,false,"NINGUNO","RESPUESTA EXITOSA",tiempoEnContestar)
            def classificationResult = ClassificationResult.findByReference(referencia)
            def dossierSummary = DossierSummary.findByReference(referencia)
            if(classificationResult) {
                mapa.cliente = [:]
                mapa.cliente.nombre = classificationResult.givenname
                mapa.cliente.apellidoPaterno = classificationResult.parentNameFather
                mapa.cliente.apellidoMaterno = classificationResult.parentNameMother
                if(classificationResult.dateOfBirth) {                    
                    mapa.cliente.fechaDeNacimiento = [:]
                    mapa.cliente.fechaDeNacimiento.anio = classificationResult.dateOfBirth.substring(0,4)
                    mapa.cliente.fechaDeNacimiento.mes = classificationResult.dateOfBirth.substring(5,7)
                    mapa.cliente.fechaDeNacimiento.dia = classificationResult.dateOfBirth.substring(8,10)
                }
                mapa.cliente.genero = (classificationResult.gender == "Male" ? 1 : 2)
                mapa.direccionCliente = [:]
                mapa.direccionCliente.calle = classificationResult.address
                mapa.direccionCliente.numeroExterior = obtenerNumeroExterior(mapa.direccionCliente.calle)
                def direccion = (classificationResult.address1)?.replaceAll("\\.", "")
                direccion = (direccion)?.replaceAll("-", " ")
                direccion = direccion?.trim()
                if(direccion?.toUpperCase()?.contains("CP")){
                    direccion = direccion.substring(direccion.toUpperCase().indexOf("CP"), direccion.length())
                    def cadenas = direccion.tokenize(" ")
                    cadenas.each {
                        if(it ==~ /\d{5}/ ){
                            if(it.startsWith("0")){
                                it = it.replaceFirst("0", "")
                            }
                            mapa.direccionCliente.codigoPostal = it
                            def consulta = CodigoPostal.findByCodigo(mapa.direccionCliente.codigoPostal)
                            if(consulta){
                                if(!mapa.direccionCliente.colonia){
                                    mapa.direccionCliente.colonia = consulta.asentamiento
                                }
                                if(!mapa.direccionCliente.delegacion){
                                    mapa.direccionCliente.delegacion = consulta.municipio.nombre
                                }
                                if(!mapa.direccionCliente.estado){
                                    mapa.direccionCliente.estado = consulta.municipio.estado.id
                                }
                            }
                        }
                    }
                    println "Valor seleccionado para CP: " + mapa.direccionCliente.codigoPostal
                } else {
                    def cadenas = direccion?.tokenize(" ")
                    cadenas?.each {
                        if(it ==~ /\d{5}/ && !mapa.direccionCliente.codigoPostal){                            
                            if(it.startsWith("0")){
                                it = it.replaceFirst("0", "")
                            }
                            mapa.direccionCliente.codigoPostal = it
                            def consulta = CodigoPostal.findByCodigo(mapa.direccionCliente.codigoPostal)
                            if(consulta){
                                if(!mapa.direccionCliente.colonia){
                                    mapa.direccionCliente.colonia = consulta.asentamiento
                                }
                                if(!mapa.direccionCliente.delegacion){
                                    mapa.direccionCliente.delegacion = consulta.municipio.id
                                }
                                if(!mapa.direccionCliente.estado){
                                    mapa.direccionCliente.estado = consulta.municipio.estado.id
                                }
                            }
                        }
                    }
                }
                if(classificationResult.yearOfExpiry) {
                    int anioActual = Calendar.getInstance().get(Calendar.YEAR);
                    int anioDeExpiracion = (classificationResult.yearOfExpiry as int)
                    mapa.vigente = ( anioActual <= anioDeExpiracion ? true : false )
                } else if (classificationResult.dateOfExpiry){
                    def fechaDeExpiracion = Date.parse("yyyy-MM-dd", classificationResult.dateOfExpiry.substring(0,10))
                    def hoy = new Date()
                    hoy.clearTime()
                    mapa.vigente = ((fechaDeExpiracion >= hoy) ? true : false)
                }
                if(solicitud) {
                    classificationResult.solicitud = SolicitudDeCredito.get(solicitud as long)
                    classificationResult.folioSolicitud = classificationResult.solicitud?.folio
                    classificationResult.save(flush: true)
                } else if (solicitudTemporal) {
                    def temporalSolicitud = SolicitudTemporal.get(solicitudTemporal as long)
                    classificationResult.folioSolicitud = temporalSolicitud?.folio
                    classificationResult.save(flush: true)
                }
                datosDocto = true
                mapa.llenadoPrevio = true
            }
            if(dossierSummary) {
                if(dossierSummary.status == "Rejected") {
                    def motivos = DossierRejectIssue.findAllWhere(dossierSummary: dossierSummary)
                    mapa.motivosRechazo = (motivos*.dossierRejectReason)*.descripcion
                    bitacoraMitekService.registrarBitacoraMitek(folio,true,true,mapa.motivosRechazo.join(","),"RESPUESTA EXITOSA DOSSIER SUMMARY REJECTED",tiempoEnContestar)
                } else if (dossierSummary.status != "Rejected" && datosDocto == false) {
                    bitacoraMitekService.registrarBitacoraMitek(folio,true,true,"LA IMAGEN PROPORCIONADA NO CORRESPONDE A UNA IDENTIFICACION VÁLIDA O LA CALIDAD ES DEMASIADO BAJA","RESPUESTA EXITOSA SIN CLASSIFICATIONRESULT",tiempoEnContestar)
                    println "[OCR Warning] El análisis tiene respuesta, pero no viene el classificationResult"
                    mapa.motivosRechazo = ["La imagen proporcionada no corresponde a una identificación válida o la calidad de la misma es demasiado baja."]
                }
                if(solicitud) {
                    dossierSummary.solicitud = SolicitudDeCredito.get(solicitud as long)
                    dossierSummary.save(flush: true)
                }
                validacionesDocto = true
            }
            mapa.exito = true
        }
        println "Contenido del Mapa: " + mapa
        return mapa
    }
    
    def generarReferencia(String s){
        MessageDigest.getInstance("MD5").digest(s.bytes).encodeHex().toString()
    }
    
    def consultarResultadosMitek(def referencia, def dossierId){
        sleep(3000)
        println "Parametros recibidos: [referencia: " + referencia + ", dossierId: " + dossierId + "]"
        println "Intentando buscar la respuesta de Mitek..."
        def classificationResultRecibido = ClassificationResult.countByReference(referencia)
        def dossierSummaryRecibido = DossierSummary.countByReference(referencia)
        println "A punto de comparar [classificationResultRecibido: " + classificationResultRecibido + ", dossierSummaryRecibido: " + dossierSummaryRecibido + "]"
        if(classificationResultRecibido > 0 && dossierSummaryRecibido > 0){
            println ("****************** SI HAY RESPUESTA *******************")
            return true
        } else if (classificationResultRecibido == 0 && dossierSummaryRecibido > 0) {
            println ("****************** RESPUESTA PARCIAL *******************")
            return true
        } else {
            println ("++++++++++++++++++ AUN NO HAY RESPUESTA ++++++++++++++++++")
            return false
        }
    }
    
    def obtenerNumeroExterior(def direccion) {
        StringBuffer numeroExterior = new StringBuffer();
        def ban=0
        def contador=0
        if(direccion) {
            String[] numerosComoArray = direccion.split("\\s+");
            def tamano=numerosComoArray.length
            Pattern pat2 = Pattern.compile("[A-Z]*")
            Matcher mat2 = pat2.matcher(numerosComoArray[tamano-1]);
            Pattern pat3 = Pattern.compile("\\d*")
            Matcher mat3 = pat3.matcher(numerosComoArray[tamano-2]);
            Pattern pat4 = Pattern.compile("\\d*|(\\d*.[A-Z]*)")
            Matcher mat4 = pat4.matcher(numerosComoArray[tamano-1]);
        
            for (int i = 0; i < numerosComoArray.length; i++) {
                Pattern pat = Pattern.compile("(LT)|(INT)|(S/N)")
                Matcher mat = pat.matcher(numerosComoArray[i]);
                if(mat.matches()) {
                    ban=1
                    contador++
                    break;
                } else {
                    ban=0
                    contador++
                }
            }
        
            if(ban==1) {
                for (int y = contador-1; y < numerosComoArray.length; y++) {
                    numeroExterior = numeroExterior.append(numerosComoArray[y]+" "); 
                }
            } else {
                if(mat2.matches()) {
                    if(mat3.matches()) {
                        numeroExterior = numeroExterior.append(numerosComoArray[numerosComoArray.length-2]+" "+numerosComoArray[numerosComoArray.length-1]);
                    }
                } else if(mat4.matches()) {
                    numeroExterior = numeroExterior.append(numerosComoArray[numerosComoArray.length-1])
                }
            }
            return numeroExterior?.toString()
        } else {
            return null
        }
    }
}
