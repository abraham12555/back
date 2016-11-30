package la.kosmos.app

class RubroDeAplicacionProducto implements Serializable{
    
    RubroDeAplicacionDeCredito rubro
    Producto producto
    TipoDeIngresos tipoDeIngresos
    boolean haTenidoAtrasos
    
    static constraints = {
        rubro (nullable: false)
        producto (nullable: false)
        tipoDeIngresos (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_rubro_de_aplicacion_producto', params:[sequence:'rubro_de_aplicacion_producto_id_seq']
    }
}
