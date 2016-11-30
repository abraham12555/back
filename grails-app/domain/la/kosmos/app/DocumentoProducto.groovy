package la.kosmos.app

class DocumentoProducto implements Serializable{

    TipoDeDocumento tipoDeDocumento
    Producto producto
    boolean activo = true
    boolean  obligatorio
    
    static constraints = {
        tipoDeDocumento (nullable: false)
        producto (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_documento_producto', params:[sequence:'documento_producto_id_seq']
    }
}
