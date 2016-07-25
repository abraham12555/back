package la.kosmos.app

class Colonia implements Serializable{

    String nombre
    Municipio municipio
    
    static constraints = {
        nombre (blank: false)
        municipio (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_colonia', params:[sequence:'colonia_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
