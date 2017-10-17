package la.kosmos.rest

import grails.transaction.Transactional
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import com.twilio.exception.ApiException
import com.auronix.calixta.GatewayException;
import com.auronix.calixta.sms.SMSGateway;
import la.kosmos.app.ConfiguracionEntidadFinanciera
import la.kosmos.app.vo.Constants.StatusResponseCalixta
import org.apache.commons.lang.RandomStringUtils
import java.security.MessageDigest
import org.apache.commons.lang.RandomStringUtils
import java.sql.Timestamp
import org.springframework.transaction.annotation.Propagation

//MIKE Agregar este servicio, no requiere cambios
@Transactional
public class SmsService {

    public static final String ACCOUNT_SID = "AC39bc0b4384b11a8ca04fc63e6a585ec4"
    public static final String AUTH_TOKEN = "29e92c58d2eae04752dff3ce075799c9"
    public static final String PHONE_NUMBER = "+13152194197"

    public String sendSMS(String to, def configuracion) {
        def respuesta
        def message
        try{
            if(configuracion && to) {
                String randomCode = getRandomCode()
                String mensajeArmado = (configuracion.mensajeConfirmacionCelular + randomCode)
                if(configuracion?.usarTwilio) {
                    Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
                    message = Message.creator(
                        new PhoneNumber("+52" + to),
                        new PhoneNumber(PHONE_NUMBER),
                        mensajeArmado?.toUpperCase())
                    .create()                    
                    // ACTUALMENTE EL REGISTRO EN BD DE MENSAJES SOLO APLICA CON CALIXTA
                    // PARA GUARDAR EL REGISTRO DE MENSAJES ENVIADOS CON TWILIO SE DEBERÁ DIFERENCIAR EL TIPO DE ENVIO CALIXTA O TWILIO
                } else {
                    message = [:]
                    message.bodyMessage = mensajeArmado
                    message.dateCreated = new Date()
                    message.fromPhone = "unknown"
                    message.toPhone = to

                    SMSGateway smsGateway = new SMSGateway();

                    int idEnvio = smsGateway.sendMessageOL(to, mensajeArmado);
                    println("Estados Calixta -> [ 3 - Enviado al carrier | 6 - No móvil | 10 - Inválido | 101 - Falta de saldo | -1 - Error general ]")
                    println("Resultado del envio de SMS al " + to + " mediante Calixta: " + idEnvio)
                    if (idEnvio == StatusResponseCalixta.ENVIADO.value) {
                        message.errorCode = null
                        message.errorMessage = null
                        message.sid = ("SM" + generarToken(randomCode + generarCadenaAleatoria(32) + "|" + (new Date().toString())))
                        message.status = "complete"
                    } else {
                        message.errorCode = idEnvio
                        message.status = "error"
                    }

                    if(persistSms(message, randomCode, configuracion?.usarTwilio)){
                        println(message)
                        if (message.sid) {
                            respuesta = message.sid
                        }
                    }
                }
            }
        } catch (ApiException | Exception e) {
            message.errorCode = StatusResponseCalixta.ERROR_INTERNO.value
            message.status = "error"
            persistSms(message, randomCode, configuracion?.usarTwilio)

            respuesta = null            
        } finally{
            respuesta
        }
    }

    public boolean sendShortUrl(String to, String shortUrl, def configuracion){
        try {
            return this.sendSMS(to, configuracion.mensajeEnvioShortUrl + shortUrl, configuracion)
        } catch (Exception e) {
            return Boolean.FALSE
        }
    }

    public boolean sendSMS (String to, String contentMsg, ConfiguracionEntidadFinanciera configuracion) throws Exception {
        def message
        boolean saveError = Boolean.TRUE
        try {
            if(configuracion?.usarTwilio) {
                Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
                message = Message.creator(
                    new PhoneNumber("+52" + to),
                    new PhoneNumber(PHONE_NUMBER),
                    contentMsg)
                .create()
                // ACTUALMENTE EL REGISTRO EN BD DE MENSAJES SOLO APLICA CON CALIXTA
                // PARA GUARDAR EL REGISTRO DE MENSAJES ENVIADOS CON TWILIO SE DEBERÁ DIFERENCIAR EL TIPO DE ENVIO CALIXTA O TWILIO
                return Boolean.TRUE
            } else {
                message = [:]
                message.bodyMessage = contentMsg
                message.dateCreated = new Date()
                message.fromPhone = "unknown"
                message.toPhone = to

                SMSGateway smsGateway = new SMSGateway();
                int idEnvio = smsGateway.sendMessageOL(to, (contentMsg));                

                if(idEnvio == StatusResponseCalixta.ENVIADO.value) {
                    message.errorCode = null
                    message.errorMessage = null
                    message.sid = ("SM" + generarToken(randomCode + generarCadenaAleatoria(32) + "|" + (new Date().toString())))
                    message.status = "complete"
                } else {
                    message.errorCode = idEnvio
                    message.status = "error"
                }

                persistSms(message, randomCode, configuracion?.usarTwilio)

                if(idEnvio == StatusResponseCalixta.ENVIADO.value) {
                    return Boolean.TRUE
                } else if (idEnvio == StatusResponseCalixta.NO_SALDO.value){
                    saveError = Boolean.FALSE
                    throw new Exception("Error. No hay saldo para el envío de mensajes")
                } else if (idEnvio == StatusResponseCalixta.ERROR.value){
                    saveError = Boolean.FALSE
                    throw new Exception("Error desconocido")
                } else {
                    return Boolean.FALSE
                }
            }
        } catch(Exception e) {
            if (saveError) {
                persistSmsException(message, randomCode, configuracion)
            }
            throw e
        }
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
        boolean result = false
        def mySms = SmsMessage.findBySid(sid)
        if(mySms) {
            println "mySms.toPhone -> " + mySms.getToPhone()
            println "mySms.randomCode -> " + mySms.getRandomCode()
            if (mySms.getRandomCode() == randomCodeTmp.trim()) {
                result = true
            }
        }
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

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    private void persistSmsException(message, randomCode, configuracion) {
        message.errorCode = StatusResponseCalixta.ERROR_INTERNO.value
        message.status = "error"
        persistSms(message, randomCode, configuracion?.usarTwilio)
    }
}
