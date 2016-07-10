package la.kosmos.app

class Genero implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_genero', params:[sequence:'genero_id_seq']
    }
}
