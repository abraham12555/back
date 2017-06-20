package la.kosmos.app

import la.kosmos.app.vo.Constants.TipoPlantilla

class NotificacionesPlantilla implements Serializable {

    ConfiguracionEntidadFinanciera configuracionEntidadFinanciera
    String plantilla
    TipoPlantilla tipoPlantilla
    Integer status
    String asunto

    NotificacionesPlantilla(){

    }

    NotificacionesPlantilla(ConfiguracionEntidadFinanciera configuracionEntidadFinanciera, String plantilla, TipoPlantilla tipoPlantilla, Integer status, String asunto){
        this.configuracionEntidadFinanciera = configuracionEntidadFinanciera
        this.plantilla = plantilla
        this.tipoPlantilla = tipoPlantilla
        this.status = status
        this.asunto = asunto
    }

    static constraints = {
        configuracionEntidadFinanciera (nullable: false)
        plantilla maxSize:300
        tipoPlantilla (nullable: false)
        status (nullable: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_notificaciones_plantilla', params:[sequence:'notificaciones_plantilla_id_seq']
        tipoPlantilla (enumType:"ordinal")
    }
}
