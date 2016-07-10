package la.kosmos.app

class Temporalidad implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_temporalidad', params:[sequence:'temporalidad_id_seq']
    }
}
