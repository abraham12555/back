package la.kosmos.rest

class DossierWarningIssue implements Serializable {

    DossierSummary dossierSummary
    DossierProcessingWarning dossierProcessingWarning
    
    static constraints = {
        dossierSummary (nullable: false)
        dossierProcessingWarning (nullable: false)
    }
    
    static mapping = {
        id composite : ['dossierSummary', 'dossierProcessingWarning']
    }
}
