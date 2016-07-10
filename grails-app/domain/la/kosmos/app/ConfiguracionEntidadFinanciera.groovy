package la.kosmos.app

class ConfiguracionEntidadFinanciera implements Serializable{

    String nombreComercial
    String rutaLogotipo
    String slogan
    EntidadFinanciera entidadFinanciera
    
    static constraints = {
        nombreComercial (blank:false)
        rutaLogotipo (blank: false)
        slogan (blank: false)
        entidadFinanciera (nullable: false, unique: true)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_configuracion_entidad_financiera', params:[sequence:'configuracion_entidad_financiera_id_seq']
    }
}
