package la.kosmos.app

class VerificacionSolicitud implements Serializable{

    SolicitudDeCredito solicitud
    Date fechaDeVerificacion
    Usuario usuarioVerificador
    String observaciones
    boolean resultadoPaso1 = false
    boolean resultadoPaso2 = false
    boolean resultadoPaso3 = false
    boolean resultadoPaso4 = false
    
    static constraints = {
        solicitud (nullable:false)
        fechaDeVerificacion (nullable:false)
        usuarioVerificador (nullable:false)
        observaciones (nullable:true)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_verificacion_solicitud', params:[sequence:'verificacion_solicitud_id_seq']
    }
}
