package la.kosmos.app

class Municipio implements Serializable{

    String nombre
    Estado estado
    
    static constraints = {
        nombre (blank: false)
        estado (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_municipio', params:[sequence:'municipio_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
