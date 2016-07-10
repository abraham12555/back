package la.kosmos.app

class Nacionalidad implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_nacionalidad', params:[sequence:'nacionalidad_id_seq']
    }
}
