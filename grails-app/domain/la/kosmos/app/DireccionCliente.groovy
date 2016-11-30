package la.kosmos.app

class DireccionCliente implements Serializable{

    String calle
    String numeroExterior
    String numeroInterior
    CodigoPostal codigoPostal
    String colonia
    String ciudad
    TipoDeVivienda tipoDeVivienda
    Temporalidad temporalidad
    Cliente cliente
    int tiempoDeResidencia
    float latitud
    float longitud
    boolean vigente = true
    String tiempoDeVivienda
    String tiempoDeEstadia
    
    static constraints = {
        calle (blank: false)
        numeroExterior (blank: false)
        numeroInterior (nullable: true)
        codigoPostal (nullable: false)
        ciudad (nullable: true)
        colonia (nullable: false)
        tipoDeVivienda (nullable: true)
        temporalidad (nullable: true)
        cliente (nullable: false)
        tiempoDeVivienda (nullable: true)
        tiempoDeEstadia (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_direccion_cliente', params:[sequence:'direccion_cliente_id_seq']
    }
}
