package la.kosmos.rest

import grails.transaction.Transactional
import com.auronix.calixta.GatewayException;
import com.auronix.calixta.sms.SMSGateway;
import la.kosmos.app.ConfiguracionEntidadFinanciera
import org.apache.commons.lang.RandomStringUtils
import java.security.MessageDigest
import org.apache.commons.lang.RandomStringUtils

import java.sql.Timestamp

@Transactional
public class SmsMockService {

    public String sendSMS(String to, def configuracion) {
        def respuesta  = 0000
        respuesta
    }

    public boolean sendShortUrl(String to, String shortUrl, def configuracion){
        try {
            return this.sendSMS(to, configuracion.mensajeEnvioShortUrl + shortUrl, configuracion)
        } catch (Exception e) {
            return Boolean.FALSE
        }
    }

    public boolean sendSMS (String to, String contentMsg, ConfiguracionEntidadFinanciera configuracion) throws Exception {
        return true
    }

    private String getRandomCode() {
        //def result = RandomStringUtils.randomAlphanumeric(5).toUpperCase()
        def result = RandomStringUtils.randomNumeric(5).toUpperCase()
        result
    }

    private boolean persistSms(def message, def randomCode, def usarTwilio) {
        SmsMessage sms = new SmsMessage()
        if(usarTwilio == true) {
            sms.bodyMessage = message?.getBody()
            sms.dateCreated = new Timestamp(message?.getDateCreated()?.getMillis())
            sms.errorCode = message?.getErrorCode()
            sms.errorMessage = message?.getErrorMessage()
            sms.fromPhone = message?.getFrom()?.toString()
            sms.sid = message?.getSid()
            sms.status = message?.getStatus()?.toString()
            sms.toPhone = message?.getTo()?.toString()
            sms.randomCode = randomCode
        } else {
            sms.bodyMessage = message?.bodyMessage
            sms.dateCreated = message?.dateCreated?.toTimestamp()
            sms.errorCode = message?.errorCode
            sms.errorMessage = message?.errorMessage
            sms.fromPhone = message?.fromPhone
            sms.sid = message?.sid
            sms.status = message?.status
            sms.toPhone = message?.toPhone
            sms.randomCode = randomCode
        }

        sms.save(flush: true)

    }

    public boolean verify(String sid, String randomCodeTmp) {
        boolean result = true
        result
    }

    def generarToken(String s){
        MessageDigest.getInstance("MD5").digest(s.bytes).encodeHex().toString()
    }

    def generarCadenaAleatoria(longitud){
        int randomStringLength = longitud
        String charset = (('a'..'z') + ('A'..'Z') + ('0'..'9')).join()
        String randomString = RandomStringUtils.random(randomStringLength, charset.toCharArray())
        randomString
    }
    public boolean sendFolio(String to, String folio, def configuracion){
        try {
            println "Enviando folio por sms : " + folio
            return this.sendSMS(to, folio , configuracion)
        } catch (Exception e) {
            return Boolean.FALSE
        }
    }
}
