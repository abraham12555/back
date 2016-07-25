package la.kosmos.app

class TipoDeDocumento implements Serializable{
    
    String nombre
    String formatosPermitidos
    boolean activo

    static constraints = {
        nombre (blank: false)
        formatosPermitidos (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_documento', params:[sequence:'tipo_de_documento_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
