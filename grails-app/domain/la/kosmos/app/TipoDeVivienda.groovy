package la.kosmos.app

class TipoDeVivienda implements Serializable{

    String nombre
    char clave
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_vivienda', params:[sequence:'tipo_de_vivienda_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
