package la.kosmos.app


import grails.transaction.Transactional
import la.kosmos.app.NotificacionesJob
import la.kosmos.app.vo.Constants
import org.quartz.CronScheduleBuilder
import org.quartz.CronTrigger
import org.quartz.TriggerBuilder
import org.quartz.TriggerKey

@Transactional
class ConfiguracionNotificacionesService {

    def startScheduler(){
        def criteria = NotificacionesCron.createCriteria()
        def cronList = criteria.list{
            createAlias('configuracionEntidadFinanciera', 'cef')
            createAlias('cef.entidadFinanciera', 'ef')
            eq ('ef.activa', Boolean.TRUE)
        }

        if (cronList != null && !cronList?.empty) {
            cronList.each {
                def content = it
                it.templates.each{
                    if(it.tipoDeEnvio == 0){
                        stopScheduler(content)
                        addJob(content)
                    }
                    
                }                
            }
        }
    }

    def stopScheduler(NotificacionesCron cron) {
        def id = cron.id.toString()
        NotificacionesJob.unschedule(id, Constants.TRIGGER_GROUP_CONFIG)
    }

    def rescheduleJob(NotificacionesCron cron){
        def trigger = buildTrigger(cron)
        NotificacionesJob.reschedule(trigger)
    }

    def addJob(NotificacionesCron cron) {
        def trigger = buildTrigger(cron)
        NotificacionesJob.schedule(trigger)
    }

    def buildTrigger (NotificacionesCron notificacion){
        CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(notificacion.id.toString(), Constants.TRIGGER_GROUP_CONFIG)
        .withSchedule(CronScheduleBuilder.cronSchedule(notificacion.cron))
        .build()

        return trigger
    }
}
