package la.kosmos.app

class Plazo implements Serializable{

    String nombre
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_plazo', params:[sequence:'plazo_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
