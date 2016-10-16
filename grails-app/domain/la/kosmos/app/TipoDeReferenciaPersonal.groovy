package la.kosmos.app

class TipoDeReferenciaPersonal implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_referencia_personal', params:[sequence:'tipo_de_referencia_personal_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}

