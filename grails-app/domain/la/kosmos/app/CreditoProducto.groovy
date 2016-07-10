package la.kosmos.app

class CreditoProducto implements Serializable{

    Modelo modelo
    Periodicidad periodicidad
    Plazo plazo
    float engancheMinimo
    float mensualidadMinima
    float tasaDeInteres
    boolean requiereSeguro
    
    static constraints = {
        modelo (nullable: false)
        periodicidad (nullable: false)
        plazo (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_credito_producto', params:[sequence:'credito_producto_id_seq']
    }    
}
