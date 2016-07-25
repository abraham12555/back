package la.kosmos.app

class Aseguradora implements Serializable{

    String nombre
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_aseguradora', params:[sequence:'aseguradora_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
