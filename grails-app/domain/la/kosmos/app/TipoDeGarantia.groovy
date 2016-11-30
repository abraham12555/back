package la.kosmos.app

class TipoDeGarantia implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_garantia', params:[sequence:'tipo_de_garantia_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
