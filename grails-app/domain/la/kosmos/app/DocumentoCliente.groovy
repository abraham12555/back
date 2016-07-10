package la.kosmos.app

class DocumentoCliente implements Serializable{
    
    Date fechaDeSubida
    TipoDeDocumento tipoDeDocumento
    String rutaDelArchivo
    Cliente cliente

    static constraints = {
        fechaDeSubida (nullable: false)
        tipoDeDocumento (nullable: false)
        rutaDelArchivo (blank: false)
        cliente (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_documento', params:[sequence:'documento_id_seq']
    }
}
