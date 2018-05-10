package la.kosmos.app

class EnviosProgramados {
    String folio
    Date fechaExpiracion 
    NotificacionesPlantilla notificacionesPlantilla
    static constraints = {
    }
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_enviosprogramados', params:[sequence:'enviosprogramados_id_seq']
    }
}
