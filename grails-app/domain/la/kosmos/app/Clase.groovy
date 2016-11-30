package la.kosmos.app

class Clase implements Serializable{

    String nombre
    String className
    String descripcion
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
        descripcion (blank:false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_clase', params:[sequence:'clase_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
