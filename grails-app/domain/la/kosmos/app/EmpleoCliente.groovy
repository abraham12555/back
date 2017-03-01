package la.kosmos.app

class EmpleoCliente implements Serializable{
    
    String puesto
    String actividad
    String explicacionActividad
    Profesion profesion
    TipoDeContrato tipoDeContrato
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
    Ocupacion ocupacion
    String fechaIngreso
    float ingresosFijos
    float ingresosVariables
    float gastos
    boolean vigente = true
    
    static constraints = {

        puesto (nullable: true)
        actividad (nullable: true)
        explicacionActividad (nullable: true)
        profesion (nullable: true)
        giroEmpresarial (nullable: true)
        nombreDeLaEmpresa (blank: false)
        nombreDelJefeInmediato (nullable: true)
        temporalidad (nullable: true)
        telefono (nullable: true, maxSize: 14)
        calle (nullable: true)
        numeroExterior (nullable: true)
        numeroInterior (nullable: true)
        codigoPostal (nullable: true)
        colonia (nullable: true)
        ciudad (nullable: true)
        cliente (nullable: true)
        tipoDeContrato (nullable: true)
        ocupacion (nullable: true)
        fechaIngreso (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_empleo', params:[sequence:'empleo_id_seq']
    }
}
