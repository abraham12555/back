package la.kosmos.app

class OpcionMedioDeContacto implements Serializable{

    MedioDeContacto medioDeContacto
    String nombre
    boolean activo = true
    
    static constraints = {
        medioDeContacto (nullable: false)
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_opcion_medio_de_contacto', params:[sequence:'opcion_medio_de_contacto_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
