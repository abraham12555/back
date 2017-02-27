package la.kosmos.rest

class DossierRejectReason implements Serializable{

    String etiqueta
    String descripcion
    
    static constraints = {
        etiqueta (nullable: false)
        descripcion (nullable: false)
    }
    
        static mapping = {
        id generator : 'sequence', column : 'id_dossier_reject_reason', params:[sequence:'dossier_reject_reason_id_seq']
    }
}
