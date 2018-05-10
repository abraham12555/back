package la.kosmos.app

import la.kosmos.app.vo.Constants.CronConfig

class NotificacionesCron implements Serializable {

    ConfiguracionEntidadFinanciera configuracionEntidadFinanciera
    CronConfig configCron
    String cron
    Integer dia
    Integer hora
    Integer minuto
    Long milisegundos

    static constraints = {
        configuracionEntidadFinanciera (nullable: false)
        configCron (nullable: false)
        cron (nullable: false)
        dia (nullable: true)
        hora (nullable: true)
        minuto (nullable: true)
        milisegundos (nullable: true)

    }

    static mapping = {
        id generator: 'sequence', column: 'id_notificaciones_cron', params:[sequence:'notificaciones_cron_id_seq']
        configCron (enumType:"ordinal")
    }

    Set<NotificacionesPlantilla> getTemplates() {
        NotificacionesConfiguracion.findAllByNotificacionesCron(this)*.notificacionesPlantilla
    }
}
