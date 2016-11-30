package la.kosmos.app

class SeguroProducto implements Serializable{
    
    Aseguradora aseguradora
    Modelo modelo
    TipoDeSeguro tipoDeSeguro
    Periodicidad periodicidad
    Plazo plazo
    String nombre
    String descripcion
    String cobertura
    String detalleImporte
    float importe

    static constraints = {
        aseguradora (nullable: false)
        modelo (nullable: false)
        tipoDeSeguro (nullable: false)
        periodicidad (nullable: false)
        plazo (nullable: false)
        nombre (blank: false)
        descripcion (blank: false)
        detalleImporte (blank: false)
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_seguro_producto', params:[sequence:'seguro_producto_id_seq']
    }    
}
