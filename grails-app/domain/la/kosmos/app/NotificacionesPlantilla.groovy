package la.kosmos.app

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import la.kosmos.app.vo.Constants.TipoPlantilla

class NotificacionesPlantilla implements Serializable {

    ConfiguracionEntidadFinanciera configuracionEntidadFinanciera
    String plantilla
    TipoPlantilla tipoPlantilla
    Integer status
    String asunto
    String nombrePlantilla
    Integer tipoDeEnvio


    static constraints = {
        configuracionEntidadFinanciera (nullable: false)
        plantilla maxSize:500
        tipoPlantilla (nullable: false)
        tipoDeEnvio (nullable: true)
        status (nullable: false)
        nombrePlantilla (nullable: true)
        asunto maxSize:100
    }

    static mapping = {
        id generator: 'sequence', column: 'id_notificaciones_plantilla', params:[sequence:'notificaciones_plantilla_id_seq']
        tipoPlantilla (enumType:"ordinal")
    }

    Set<NotificacionesCron> getCronExpressions () {
        NotificacionesConfiguracion.findAllByNotificacionesPlantilla(this)*.notificacionesCron
    }
}
