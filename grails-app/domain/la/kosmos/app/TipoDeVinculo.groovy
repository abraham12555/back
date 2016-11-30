package la.kosmos.app

class TipoDeVinculo implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_vinculo', params:[sequence:'tipo_de_vinculo_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
