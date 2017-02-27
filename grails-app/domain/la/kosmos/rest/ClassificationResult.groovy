package la.kosmos.rest

import grails.rest.Resource
import la.kosmos.app.SolicitudDeCredito

@Resource(uri='/api/Callback/ClassificationResult', formats=['json','xml'])
class ClassificationResult implements Serializable{
    
    SolicitudDeCredito solicitud
    String givenname
    String surname
    String name
    String dateOfBirth
    String dateOfExpiry
    String address
    String address1
    String address2
    String gender
    String clave
    String issuingYear
    String yearOfExpiry
    String districtCode
    String curp
    String parentNameFather
    String parentNameMother
    String documentId
    String dossierId
    String reference
    String respuestaOriginal
   
    static constraints = {
        solicitud nullable: true
        givenname nullable: false
        surname nullable: true
        name nullable: true
        dateOfBirth nullable: true
        dateOfExpiry nullable: true
        address nullable: true
        address1 nullable: true
        address2 nullable: true
        gender nullable: true
        clave nullable: true
        issuingYear nullable: true
        yearOfExpiry nullable: true
        districtCode nullable: true
        curp nullable: true
        parentNameFather nullable: true
        parentNameMother nullable: true
        documentId nullable: true
        dossierId nullable: true
        respuestaOriginal nullable: true
    }
    
    static mapping = {
        id generator : 'sequence', column : 'id_classificaction_result', params:[sequence:'classificaction_result_id_seq']
    }
       
}
