package la.kosmos.app

class Profesion implements Serializable{

    String nombre
    String codigo
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
        codigo (blank:false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_profesion', params:[sequence:'profesion_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
