package la.kosmos.app

class DireccionCliente implements Serializable{

    String calle
    String numeroExterior
    String numeroInterior
    Colonia colonia
    Municipio municipio
    String codigoPostal
    String ciudad
    Estado estado
    TipoDeVivienda tipoDeVivienda
    Temporalidad temporalidad
    Cliente cliente
    int tiempoDeResidencia
    float latitud
    float longitud
    boolean vigente = true
    
    static constraints = {
        calle (blank: false)
        numeroExterior (blank: false)
        numeroInterior (nullable: true)
        colonia (nullable: false)
        municipio (nullable: false)
        codigoPostal (blank: false, maxSize: 5)
        ciudad (nullable: true)
        estado (nullable: false)
        tipoDeVivienda (nullable: true)
        temporalidad (nullable: true)
        cliente (nullable: false)

    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_direccion_cliente', params:[sequence:'direccion_cliente_id_seq']
    }
}
