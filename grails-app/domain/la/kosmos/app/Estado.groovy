package la.kosmos.app

class Estado implements Serializable{

    String nombre
	String siglasrenapo
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_estado', params:[sequence:'estado_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
