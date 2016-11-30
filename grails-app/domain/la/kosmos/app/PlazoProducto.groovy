package la.kosmos.app

class PlazoProducto implements Serializable{

    Periodicidad periodicidad
    Producto producto
    int plazoMinimo
    int plazoMaximo
    float importeMinimo
    float importeMaximo
    int saltoSlider = 1
    
    static constraints = {
        periodicidad (nullable: false)
        producto (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_plazo_producto', params:[sequence:'plazo_producto_id_seq']
    }
}
