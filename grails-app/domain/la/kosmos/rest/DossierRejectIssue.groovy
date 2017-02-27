package la.kosmos.rest

class DossierRejectIssue implements Serializable {

    DossierSummary dossierSummary
    DossierRejectReason dossierRejectReason
    
    static constraints = {
        dossierSummary (nullable: false)
        dossierRejectReason (nullable: false)
    }
    
    static mapping = {
        id composite : ['dossierSummary', 'dossierRejectReason']
    }
}
