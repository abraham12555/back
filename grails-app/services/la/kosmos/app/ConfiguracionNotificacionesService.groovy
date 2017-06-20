package la.kosmos.app

import grails.gorm.DetachedCriteria
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
import org.springframework.transaction.annotation.Propagation


@Transactional
class ConfiguracionNotificacionesService {

    def quartzScheduler

    def startScheduler(){
        def criteria = NotificacionesConfiguracion.createCriteria()
        def cronList = criteria.list{
            createAlias('notificacionesCron', 'nc')
            createAlias('nc.configuracionEntidadFinanciera', 'cef')
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

    @Transactional(propagation = Propagation.MANDATORY)
    def stopScheduler(NotificacionesPlantilla plantilla) {
        def criteria = NotificacionesConfiguracion.createCriteria()
        def templateList = criteria.list {
            eq ('notificacionesPlantilla', plantilla)
        }

        this.deleteConfiguration(templateList)

        criteria = new DetachedCriteria(NotificacionesConfiguracion).build {
            eq ('notificacionesPlantilla', plantilla)
        }
        criteria.deleteAll()
    }

    @Transactional(propagation = Propagation.MANDATORY)
    def stopScheduler(NotificacionesCron cron) {
        def criteria = NotificacionesConfiguracion.createCriteria()
        def cronList = criteria.list {
            eq ('notificacionesCron', cron)
        }

        this.deleteConfiguration(cronList)

        criteria = new DetachedCriteria(NotificacionesConfiguracion).build {
            eq ('notificacionesCron', cron)
        }
        criteria.deleteAll()
    }

    private void rescheduleJob(NotificacionesConfiguracion notificacion){
        def trigger = buildTrigger(notificacion)

        def id = notificacion.id.toString()
        TriggerKey triggerKey = TriggerKey.triggerKey(id);
        this.quartzScheduler.rescheduleJob(triggerKey, trigger)
    }

    @Transactional(propagation = Propagation.MANDATORY)
    def addJob (NotificacionesPlantilla plantilla){
        def criteria = NotificacionesCron.createCriteria()
        def cronList = criteria.list {
            eq ('configuracionEntidadFinanciera', plantilla.configuracionEntidadFinanciera)
        }

        if (cronList != null && !cronList?.empty) {
            cronList.each {
                this.saveConfiguration(plantilla, it)
            }
        }
    }

    @Transactional(propagation = Propagation.MANDATORY)
    def addJob(NotificacionesCron cron) {
        def criteria = NotificacionesPlantilla.createCriteria()
        def templateList = criteria.list {
            eq ('configuracionEntidadFinanciera', cron.configuracionEntidadFinanciera)
        }
        if (templateList != null && !templateList?.empty) {
            templateList.each {
                this.saveConfiguration(it, cron)
            }
        }
    }

    def updateJob(NotificacionesPlantilla plantilla){
        def criteria = NotificacionesConfiguracion.createCriteria()
        def templateList = criteria.list {
            eq ('notificacionesPlantilla', plantilla)
        }

        if (templateList == null || templateList?.empty) {
            this.addJob(plantilla)
        } else {
            templateList.each {
                this.rescheduleJob(it)
            }
        }
    }

    def updateJob(NotificacionesCron cron){
        def criteria = NotificacionesConfiguracion.createCriteria()
        def cronList = criteria.list {
            eq ('notificacionesCron', cron)
        }

        if (cronList == null || cronList?.empty) {
            this.addJob(cron)
        } else {
            cronList.each {
                this.rescheduleJob(it)
            }
        }
    }

    def buildTrigger (NotificacionesConfiguracion notificacion){
        CronTrigger trigger = TriggerBuilder.newTrigger()
        .withIdentity(notificacion.id.toString())
        .withSchedule(CronScheduleBuilder.cronSchedule(notificacion.notificacionesCron.cron))
        .build();

        return trigger
    }

    private void saveConfiguration(NotificacionesPlantilla plantilla, NotificacionesCron cron){
        NotificacionesConfiguracion nc = new NotificacionesConfiguracion(plantilla, cron)
        nc.save(insert: Boolean.TRUE, validate: Boolean.FALSE, flush: Boolean.FALSE)

        def trigger = buildTrigger(nc)
        NotificacionesJob.schedule(trigger)
    }

    private void deleteConfiguration(list){
        if (list != null && !list?.empty) {
            list.each {
                def id = it.id.toString()
                
                TriggerKey triggerKey = TriggerKey.triggerKey(id);
                this.quartzScheduler.unscheduleJob(triggerKey)
            }
        }
    }
}
