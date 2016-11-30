package la.kosmos.app

class GarantiaProducto implements Serializable{

    TipoDeGarantia tipoDeGarantia
    Producto producto
    String descripcion
    float cantidadMinima
    float cantidadMaxima
    
    static constraints = {
        tipoDeGarantia (nullable: false)
        producto (nullable: false)
        descripcion (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_garantia_producto', params:[sequence:'garantia_producto_id_seq']
    }
}
