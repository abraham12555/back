package la.kosmos.rest

class DossierProcessingWarning implements Serializable{

    String etiqueta
    String descripcion
    
    static constraints = {
        etiqueta (nullable: false)
        descripcion (nullable: false)
    }
    
        static mapping = {
        id generator : 'sequence', column : 'id_dossier_processing_warning', params:[sequence:'dossier_processing_warning_id_seq']
    }
}
