package la.kosmos.rest

import java.sql.Timestamp

class SmsMessage {

    String bodyMessage
    Timestamp dateCreated
    Integer errorCode
    String errorMessage
    String fromPhone
    String sid
    String status
    String toPhone
    String randomCode

    static constraints = {
        bodyMessage nullable: false
        dateCreated nullable: false
        errorCode nullable: true
        errorMessage nullable: true
        fromPhone nullable: false
        sid nullable: false
        status nullable: true
        toPhone nullable: false
        randomCode nullable: false
    }
    
        static mapping = {
        id generator: 'sequence', column: 'id_sms_message', params:[sequence:'sms_message_id_seq']
    }
}
