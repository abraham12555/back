package la.kosmos.app

class RiesgoDeOcupacion implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_riesgo_de_ocupacion', params:[sequence:'riesgo_de_ocupacion_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
