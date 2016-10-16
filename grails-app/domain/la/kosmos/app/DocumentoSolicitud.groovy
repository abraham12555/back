package la.kosmos.app

class DocumentoSolicitud implements Serializable{
    
    Date fechaDeSubida
    TipoDeDocumento tipoDeDocumento
    String rutaDelArchivo
    SolicitudDeCredito solicitud

    static constraints = {
        fechaDeSubida (nullable: false)
        tipoDeDocumento (nullable: false)
        rutaDelArchivo (blank: false)
        solicitud (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_documento_solicitud', params:[sequence:'documento_solicitud_id_seq']
    }
}
