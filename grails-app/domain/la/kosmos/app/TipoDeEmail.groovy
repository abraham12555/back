package la.kosmos.app

class TipoDeEmail implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_email', params:[sequence:'tipo_de_email_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
