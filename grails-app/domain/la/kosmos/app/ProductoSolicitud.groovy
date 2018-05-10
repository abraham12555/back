package la.kosmos.app

class ProductoSolicitud implements Serializable{

    ColorModelo colorModelo
    Modelo modelo
    Producto producto
    RubroDeAplicacionDeCredito rubroDeAplicacion
    Periodicidad periodicidad
    Plazo plazo
    TipoDeDocumento documentoElegido
    float enganche
    boolean seguroFinanciado
    SeguroProducto seguro
    SolicitudDeCredito solicitud
    float montoDelCredito
    float montoDelSeguroDeDeuda
    float montoDelPago
    int plazos
    boolean haTenidoAtrasos
    float tasaDeInteres
    float montoDeServicioDeAsistencia = 0
    float cat = 0
    static constraints = {
        colorModelo (nullable: true)
        modelo (nullable: true)
        producto (nullable: true)
        rubroDeAplicacion (nullable: true)
        documentoElegido (nullable: true)
        periodicidad (nullable: false)
        plazo (nullable: true)
        seguro (nullable: true)
        solicitud (nullable: false)
        cat (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_producto_solicitud', params:[sequence:'producto_solicitud_id_seq']
    }    
}
