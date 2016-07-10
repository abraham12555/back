package la.kosmos.app

class EstadoCivil implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_estado_civil', params:[sequence:'estado_civil_id_seq']
    }
}
