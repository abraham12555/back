package la.kosmos.app

class TipoDePasoSolicitud implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_paso_solicitud', params:[sequence:'tipo_de_paso_solicitud_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
