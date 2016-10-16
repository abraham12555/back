package la.kosmos.app

class ProductoSolicitud implements Serializable{

    ColorModelo colorModelo
    Periodicidad periodicidad
    Plazo plazo
    float enganche
    float mensualidad
    boolean seguroFinanciado
    SeguroProducto seguro
    SolicitudDeCredito solicitud
    float montoDelCredito
    
    static constraints = {
        colorModelo (nullable: false)
        periodicidad (nullable: false)
        plazo (nullable: false)
        seguro (nullable: true)
        solicitud (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_producto_solicitud', params:[sequence:'producto_solicitud_id_seq']
    }    
}
