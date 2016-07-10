package la.kosmos.app

class EmpleoCliente implements Serializable{
    
    String puesto
    String actividad
    String profesion
    GiroEmpresarial giroEmpresarial
    String nombreDeLaEmpresa
    String nombreDelJefeInmediato
    int antiguedad = 0
    Temporalidad temporalidad
    String telefono
    String calle
    String numeroExterior
    String numeroInterior
    Colonia colonia
    Municipio municipio
    String codigoPostal
    String ciudad
    Estado estado
    Cliente cliente
    
    static constraints = {
        puesto (blank: false)
        actividad (blank: false)
        profesion (blank: false)
        giroEmpresarial (nullable: false)
        nombreDeLaEmpresa (blank: false)
        nombreDelJefeInmediato (blank: false)
        temporalidad (nullable: false)
        telefono (blank: false, maxSize: 10)
        calle (blank: false)
        numeroExterior (blank: false)
        numeroInterior (nullable: true)
        colonia (nullable: false)
        municipio (nullable: false)
        codigoPostal (blank: false, maxSize: 5)
        ciudad (blank: false)
        estado (nullable: false)
        cliente (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_empleo', params:[sequence:'empleo_id_seq']
    }
}
