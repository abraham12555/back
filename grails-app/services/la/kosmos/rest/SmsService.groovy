package la.kosmos.rest

import grails.transaction.Transactional
import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.type.PhoneNumber
import com.twilio.exception.ApiException
import org.apache.commons.lang.RandomStringUtils

import java.sql.Timestamp

//MIKE Agregar este servicio, no requiere cambios
@Transactional
public class SmsService {

    public static final String ACCOUNT_SID = "AC39bc0b4384b11a8ca04fc63e6a585ec4"
    public static final String AUTH_TOKEN = "29e92c58d2eae04752dff3ce075799c9"
    public static final String PHONE_NUMBER = "+13152194197"

    public String sendSMS(String to) {
        def respuesta
        try{
            String randomCode = getRandomCode()
            Twilio.init(ACCOUNT_SID, AUTH_TOKEN)
            Message message = Message.creator(
                new PhoneNumber("+52" + to),
                new PhoneNumber(PHONE_NUMBER),
                "Libertad SF - Tu Codigo de Verificacion: " + randomCode)
            .create()

            persistSms(message, randomCode)
            println(message)
            respuesta = message.getSid()
        }catch(ApiException e){
            respuesta = null
        } finally{
            respuesta
        }
    }

    private String getRandomCode() {
        def result = RandomStringUtils.randomAlphanumeric(5).toUpperCase()
        result
    }

    private boolean persistSms(Message message, String randomCode) {
        SmsMessage sms = new SmsMessage()

        sms.bodyMessage = message.getBody()
        sms.dateCreated = new Timestamp(message.getDateCreated().getMillis())
        sms.errorCode = message.getErrorCode()
        sms.errorMessage = message.getErrorMessage()
        sms.fromPhone = message.getFrom().toString()
        sms.sid = message.getSid()
        sms.status = message.getStatus().toString()
        sms.toPhone = message.getTo().toString()
        sms.randomCode = randomCode

        sms.save(flush: true)

    }

    public boolean verify(String sid, String randomCodeTmp) {
        boolean result = false
        def mySms = SmsMessage.findBySid(sid)
        println "mySms.toPhone -> " + mySms.getToPhone()
        println "mySms.randomCode -> " + mySms.getRandomCode()
        if (mySms.getRandomCode() == randomCodeTmp.trim()) {
            result = true
        }
        result
    }
}