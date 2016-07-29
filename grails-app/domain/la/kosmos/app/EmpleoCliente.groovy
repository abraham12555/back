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
    CodigoPostal codigoPostal
    String colonia
    String ciudad
    Cliente cliente
    
    static constraints = {

        puesto (blank: false)
        actividad (nullable: true)
        profesion (nullable: true)
        giroEmpresarial (nullable: true)
        nombreDeLaEmpresa (blank: false)
        nombreDelJefeInmediato (nullable: true)
        temporalidad (nullable: false)
        telefono (nullable: true, maxSize: 10)
        calle (nullable: true)
        numeroExterior (nullable: true)
        numeroInterior (nullable: true)
        codigoPostal (nullable: true)
        ciudad (nullable: true)
        cliente (nullable: true)

    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_empleo', params:[sequence:'empleo_id_seq']
    }
}
