package la.kosmos.app

class SucursalEntidadFinanciera implements Serializable{

    EntidadFinanciera entidadFinanciera
    String nombre
    String ubicacion
    String horario
    String responsable
    String numeroDeSucursal
    Estado estado
    Double latitud
    Double longitud
    RiesgoGeografico riesgoGeografico
    
    boolean activo = true
    
    static constraints = {
        entidadFinanciera (nullable: false)
        nombre (blank: false)
        ubicacion (blank: false)
        horario (blank: false)
        responsable (blank: false)
        numeroDeSucursal (blank: false)
        estado (nullable: true)
        latitud (nullable: true)
        longitud (nullable: true)
        riesgoGeografico (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_sucursal_entidad_financiera', params:[sequence:'sucursal_entidad_financiera_id_seq']
    }
    
    String toString () {
        "Sucursal ${numeroDeSucursal} - ${nombre}"
    }
}
