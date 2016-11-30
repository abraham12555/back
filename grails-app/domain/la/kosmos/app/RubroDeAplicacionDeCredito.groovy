package la.kosmos.app

class RubroDeAplicacionDeCredito implements Serializable{

    EntidadFinanciera entidadFinanciera
    String nombre
    String descripcion
    boolean activo = true
    
    static constraints = {
        entidadFinanciera (nullable: false)
        nombre (blank: false)
        descripcion (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_rubro_de_aplicacion_de_credito', params:[sequence:'rubro_de_aplicacion_de_credito_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
