package la.kosmos.app

class Ocupacion implements Serializable{

    String nombre
    String codigo
    boolean activo = true
    RiesgoDeOcupacion riesgoDeOcupacion
    
    static constraints = {
        nombre (blank: false)
        codigo (blank: false)
        riesgoDeOcupacion (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_ocupacion', params:[sequence:'ocupacion_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
