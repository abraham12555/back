package la.kosmos.app

import grails.converters.JSON
import grails.transaction.Transactional
import groovy.json.JsonSlurper
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient
import java.security.MessageDigest
import org.apache.commons.lang.RandomStringUtils

@Transactional
class UrlShortenerService {

    def acortarUrl(def cadenaSolicitud, def configuracionEntidadFinanciera) {
        def respuesta = [:]
        def configuracion = ConfiguracionKosmos.get(1)
        def referencia = generarToken(cadenaSolicitud + generarCadenaAleatoria(32) + "|" + (new Date().toString()))
        println "Referencia del Token: " + referencia
        def urlLarga = configuracionEntidadFinanciera.urlDominio + "solicitud/verificacion?token=" + referencia
        def urlGoogleUrlShortener = "https://www.googleapis.com/urlshortener/v1/url?key=${configuracion.googleApiKey}" 
        respuesta.token = referencia
        def jsonRequest = [:]
        println urlGoogleUrlShortener
        jsonRequest.longUrl = urlLarga           
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(urlGoogleUrlShortener);
        StringEntity jsonEntity = new StringEntity((jsonRequest as JSON).toString());
        httpPost.setEntity(jsonEntity);
        httpPost.addHeader("Content-Type", "application/json");
        HttpResponse response = httpClient.execute(httpPost);
        respuesta.statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream responseStream = entity.getContent();
            BufferedReader reader = new BufferedReader(new InputStreamReader(responseStream));
            StringBuilder sb = new StringBuilder();
            String line = null;
            try {
                if(respuesta.statusCode == 200){
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                } else {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                }
            } catch (RuntimeException ex) {
                println "Algo murio"
            } finally {
                responseStream.close();
            }
            respuesta.jsonGoogle = new JsonSlurper().parseText(sb?.toString())
        }
        println "Respuesta Preliminar: " + respuesta
        return respuesta;
    }
    
    def generarToken(String s){
        MessageDigest.getInstance("SHA-256").digest(s.bytes).encodeHex().toString()
    }
    
    def generarCadenaAleatoria(longitud){
        int randomStringLength = longitud
        String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        randomString
    }
}
