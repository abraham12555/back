package la.kosmos.app

class LimitePlazoProducto {

    int plazo
    Periodicidad periodicidad
    Producto producto
    float limiteMinimo
    float limiteMaximo
    
    static constraints = {
        periodicidad (nullable: false)
        producto (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_limite_plazo_producto', params:[sequence:'limite_plazo_producto_id_seq']
    }
}
