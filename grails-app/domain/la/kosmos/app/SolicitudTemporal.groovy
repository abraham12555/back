package la.kosmos.app

class SolicitudTemporal {

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
    float montoDelCredito
    float montoDelSeguroDeDeuda
    float montoDelPago
    int plazos
    boolean haTenidoAtrasos
    Date fechaDeSolicitud
    String nombreDelCliente
    String emailCliente
    String telefonoCliente
    EntidadFinanciera entidadFinanciera
    String token
    String shortUrl
    int ultimoPaso = 1
    boolean cargarImagen = false
    String folio
    
    static constraints = {
        colorModelo (nullable: true)
        modelo (nullable: true)
        producto (nullable: false)
        rubroDeAplicacion (nullable: true)
        documentoElegido (nullable: true)
        periodicidad (nullable: false)
        plazo (nullable: true)
        seguro (nullable: true)
        fechaDeSolicitud (nullable: false)
        nombreDelCliente (nullable: true)
        emailCliente  (nullable: true)
        telefonoCliente  (nullable: true)
        entidadFinanciera (nullable: false)
        token (nullable: false)
        shortUrl (nullable: false)
        folio (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_solicitud_temporal', params:[sequence:'solicitud_temporal_id_seq']
    }  
}
