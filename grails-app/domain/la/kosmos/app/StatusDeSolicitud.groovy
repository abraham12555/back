package la.kosmos.app

class StatusDeSolicitud implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_satus_de_solicitud', params:[sequence:'status_de_solicitud_id_seq']
    }
}
