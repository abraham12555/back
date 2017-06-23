package la.kosmos.app


import grails.transaction.Transactional
import la.kosmos.app.NotificacionesJob
import org.quartz.CronScheduleBuilder
import org.quartz.CronTrigger
import org.quartz.TriggerBuilder
import org.quartz.TriggerKey

@Transactional
class ConfiguracionNotificacionesService {

    def quartzScheduler

    def startScheduler(){
        def criteria = NotificacionesCron.createCriteria()
        def cronList = criteria.list{
            createAlias('configuracionEntidadFinanciera', 'cef')
            createAlias('cef.entidadFinanciera', 'ef')
            eq ('ef.activa', Boolean.TRUE)
        }

        if (cronList != null && !cronList?.empty) {
            cronList.each {
                def trigger = buildTrigger(it)
                NotificacionesJob.schedule(trigger);
            }
        }
    }

    def stopScheduler(NotificacionesCron cron) {
        def id = cron.id.toString()

        TriggerKey triggerKey = TriggerKey.triggerKey(id);
        this.quartzScheduler.unscheduleJob(triggerKey)
    }

    def rescheduleJob(NotificacionesCron cron){
        def trigger = buildTrigger(cron)

        def id = cron.id.toString()
        TriggerKey triggerKey = TriggerKey.triggerKey(id);
        this.quartzScheduler.rescheduleJob(triggerKey, trigger)
    }

    def addJob(NotificacionesCron cron) {
        def trigger = buildTrigger(cron)
        NotificacionesJob.schedule(trigger)
    }

    def buildTrigger (NotificacionesCron notificacion){
        CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(notificacion.id.toString())
        .withSchedule(CronScheduleBuilder.cronSchedule(notificacion.cron))
        .build();

        return trigger
    }
}
