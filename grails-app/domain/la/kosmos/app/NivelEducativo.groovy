package la.kosmos.app

class NivelEducativo implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_nivel_educativo', params:[sequence:'nivel_educativo_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
