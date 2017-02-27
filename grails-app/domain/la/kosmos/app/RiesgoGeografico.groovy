package la.kosmos.app

class RiesgoGeografico implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_riesgo_geografico', params:[sequence:'riesgo_geografico_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
