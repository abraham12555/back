package la.kosmos.app

import grails.transaction.Transactional
import la.kosmos.app.NotificacionesJob
import org.quartz.CronScheduleBuilder
import org.quartz.CronTrigger
import org.quartz.JobBuilder
import org.quartz.JobDetail
import org.quartz.Scheduler
import org.quartz.Trigger
import org.quartz.TriggerBuilder
import org.quartz.TriggerKey
import org.quartz.impl.StdSchedulerFactory


@Transactional
class ConfiguracionNotificacionesService {

    def quartzScheduler

    def startScheduler(){
        def criteria = ConfiguracionNotificaciones.createCriteria()
        def cronList = criteria.list{
            createAlias('entidadFinanciera', 'ef')
            isNotNull ('cron')
            eq ('ef.activa', Boolean.TRUE)
        }

        if (cronList != null && !cronList?.empty) {
            cronList.each {
                def trigger = buildTrigger(it)
                NotificacionesJob.schedule(trigger);
            }
        }
    }

    def stopScheduler(idNotificacion) {
        def id = idNotificacion.toString()

        TriggerKey triggerKey = TriggerKey.triggerKey(id);
        this.quartzScheduler.unscheduleJob(triggerKey)
    }

    def rescheduleJob(notificacion){
        def trigger = buildTrigger(notificacion)

        def id = notificacion.id.toString()
        TriggerKey triggerKey = TriggerKey.triggerKey(id);
        this.quartzScheduler.rescheduleJob(triggerKey, trigger)
    }

    def addJob (notificacion){
        def trigger = buildTrigger(notificacion)
        NotificacionesJob.schedule(trigger)
    }

    def buildTrigger (ConfiguracionNotificaciones notificacion){       
        CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(notificacion.id.toString())
        .withSchedule(CronScheduleBuilder.cronSchedule(notificacion.cron))
        .usingJobData("idEntidadFinanciera", notificacion.entidadFinanciera.id)
        .usingJobData("smsTemplate", notificacion.contenidoSms)
        .build();

        return trigger
    }
}
