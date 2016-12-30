package la.kosmos.app

class PreguntaVerificacionSolicitud implements Serializable{

    SolicitudDeCredito solicitud
    Date fechaDeSubida
    String pregunta
    String respuesta
    Usuario usuario
    
    static constraints = {
        solicitud (nullable:false)
        fechaDeSubida (nullable:false)
        pregunta (blank:false)
        respuesta  (nullable:true)
        usuario (nullable:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_pregunta_verificacion', params:[sequence:'pregunta_verificacion_id_seq']
    }
}
