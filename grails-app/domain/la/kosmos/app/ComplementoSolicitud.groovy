package la.kosmos.app

class ComplementoSolicitud implements Serializable{

    SolicitudDeCredito solicitud
    Date fechaDeSolicitud
    Usuario usuarioSolicitante
    TipoDeDocumento documentoSolicitado
    boolean pendiente = true
    
    static constraints = {
        solicitud (nullable:false)
        fechaDeSolicitud (nullable:false)
        usuarioSolicitante (nullable:false)
        documentoSolicitado (nullable:true)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_complemento_solicitud', params:[sequence:'complemento_solicitud_id_seq']//Checar con Joseph si se puede pedir un documento multiples veces
    }
}
