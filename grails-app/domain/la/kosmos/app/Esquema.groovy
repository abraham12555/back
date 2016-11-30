package la.kosmos.app

class Esquema implements Serializable{

    String nombre
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_esquema', params:[sequence:'esquema_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
