package la.kosmos.app

class MedioDeContacto implements Serializable{

    EntidadFinanciera entidadFinanciera
    String nombre
    boolean activo = true
    
    static constraints = {
        entidadFinanciera (nullable: false)
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_medio_de_contacto', params:[sequence:'medio_de_contacto_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
