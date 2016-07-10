package la.kosmos.app

class SeguroProducto implements Serializable{
    
    Aseguradora aseguradora
    Modelo modelo
    TipoDeSeguro tipoDeSeguro
    Periodicidad periodicidad
    Plazo plazo
    float importe

    static constraints = {
        aseguradora (nullable: false)
        modelo (nullable: false)
        tipoDeSeguro (nullable: false)
        periodicidad (nullable: false)
        plazo (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_seguro_producto', params:[sequence:'seguro_producto_id_seq']
    }    
}
