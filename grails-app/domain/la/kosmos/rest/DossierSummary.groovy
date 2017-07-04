package la.kosmos.rest

import grails.rest.Resource
import la.kosmos.app.SolicitudDeCredito

@Resource(uri='/api/Callback/DossierSummary', formats=['json','xml'])
class DossierSummary implements Serializable{
    
    SolicitudDeCredito solicitud
    String status
    String value
    String source
    String documentId
    String nameOfCallback
    String dossierId
    String reference
    String respuestaOriginal
    String folioSolicitud
    String fechaDeRespuesta =  new Date()
    
    static constraints = {
        solicitud nullable: true
        status nullable: false
        value nullable: true
        source nullable: true
        documentId nullable: false
        nameOfCallback nullable: false
        dossierId nullable: false
        reference nullable: true
        respuestaOriginal nullable: false
        folioSolicitud nullable: true
        fechaDeRespuesta nullable: true
    }
    
    static mapping = {
        id generator : 'sequence', column : 'id_dossier_summary', params:[sequence:'dossier_summary_id_seq']
    }
}
