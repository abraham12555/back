package la.kosmos.app

class SucursalEntidadFinanciera implements Serializable{

    EntidadFinanciera entidadFinanciera
    String nombre
    String ubicacion
    String horario
    String responsable
    String numeroDeSucursal
    CodigoPostal codigoPostal
    
    boolean activo = true
    
    static constraints = {
        entidadFinanciera (nullable: false)
        nombre (blank: false)
        ubicacion (blank: false)
        horario (blank: false)
        responsable (blank: false)
        numeroDeSucursal (blank: false)
        codigoPostal (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_sucursal_entidad_financiera', params:[sequence:'sucursal_entidad_financiera_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
