package la.kosmos.app

class TipoDeAsentamiento implements Serializable{
    
    String nombre

    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_asentamiento', params:[sequence:'tipo_de_asentamiento_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
