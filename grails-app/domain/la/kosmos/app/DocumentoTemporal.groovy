package la.kosmos.app

class DocumentoTemporal implements Serializable{
    
    Date fechaDeSubida
    TipoDeDocumento tipoDeDocumento
    String rutaDelArchivo

    static constraints = {
        fechaDeSubida (nullable: false)
        tipoDeDocumento (nullable: false)
        rutaDelArchivo (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_documento_temporal', params:[sequence:'documento_temporal_id_seq']
    }
}
