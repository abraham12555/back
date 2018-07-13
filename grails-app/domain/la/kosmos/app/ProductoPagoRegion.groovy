package la.kosmos.app

class ProductoPagoRegion {
    Producto producto
    Region region 
    float pagoAsalariado
    float pagoNoAsalariado
    boolean activo
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_producto_pago_region', params: [sequence: 'producto_pago_region_id_seq']
    }
    static constraints = {
    }
}
