package la.kosmos.app

class TipoDeSeguro implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_seguro', params:[sequence:'tipo_de_seguro_id_seq']
    }
}
