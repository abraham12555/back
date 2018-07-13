package la.kosmos.app

class PlazoProducto implements Serializable{

    Periodicidad periodicidad
    Producto producto
    int plazoMinimo
    int plazoMaximo
    float importeMinimo
    float importeMaximo
    int saltoSlider = 1
    boolean usarListaDePlazos = false
    String plazosPermitidos
    boolean usarEnPerfilador = false
    boolean usarEnCotizador = true
    
    static constraints = {
        periodicidad (nullable: false)
        producto (nullable: false)
        plazosPermitidos (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_plazo_producto', params:[sequence:'plazo_producto_id_seq']
    }
}
