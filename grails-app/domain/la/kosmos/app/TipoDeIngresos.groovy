package la.kosmos.app

class TipoDeIngresos implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_ingresos', params:[sequence:'tipo_de_ingresos_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
