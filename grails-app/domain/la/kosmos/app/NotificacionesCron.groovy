package la.kosmos.app

import la.kosmos.app.vo.Constants.CronConfig

class NotificacionesCron implements Serializable {

//    transient cronExpression
    
    ConfiguracionEntidadFinanciera configuracionEntidadFinanciera
    CronConfig configCron
    String cron

    NotificacionesCron(){

    }

    static constraints = {
        configuracionEntidadFinanciera (nullable: false)
        configCron (nullable: false)
        cron (nullable: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_notificaciones_cron', params:[sequence:'notificaciones_cron_id_seq']
        configCron (enumType:"ordinal")
    }
    
//    static transients = ['cronExpression']
}