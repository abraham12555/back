package la.kosmos.app

class Periodicidad implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_periodicidad', params:[sequence:'periodicidad_id_seq']
    }
}
