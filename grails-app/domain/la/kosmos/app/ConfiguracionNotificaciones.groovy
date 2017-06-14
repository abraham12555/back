package la.kosmos.app

import la.kosmos.app.vo.Constants.CronConfig

class ConfiguracionNotificaciones implements Serializable {

    EntidadFinanciera entidadFinanciera
    CronConfig configCron
    String cron
    String contenidoSms

    ConfiguracionNotificaciones(){
        
    }

    ConfiguracionNotificaciones(EntidadFinanciera entidadFinanciera, String contenidoSms){
        this.entidadFinanciera = entidadFinanciera
        this.contenidoSms = contenidoSms
    }

    static constraints = {
        entidadFinanciera (nullable: false, unique: true)
        contenidoSms maxSize:200
    }

    static mapping = {
        id generator: 'sequence', column: 'id_configuracion_notificaciones', params:[sequence:'configuracion_notificaciones_id_seq']
        configCron (enumType:"ordinal")
    }
}
