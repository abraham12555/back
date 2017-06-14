package la.kosmos.app

import grails.transaction.Transactional
import java.lang.reflect.Field
import java.sql.Timestamp
import java.util.Calendar
import java.util.concurrent.Callable
import java.util.concurrent.Future
import la.kosmos.app.bo.CronExpression
import la.kosmos.app.bo.EnvioNotificaciones
import la.kosmos.app.bo.PlantillaSolicitud
import la.kosmos.app.vo.Constants
import org.apache.commons.lang.text.StrSubstitutor
import org.apache.commons.logging.LogFactory


@Transactional
class NotificacionesService {
    def static final INSERT = "INSERT"
    def static final UPDATE = "UPDATE"
    def configuracionNotificacionesService
    def smsService
    def executorService
    private static final log = LogFactory.getLog(this)

    def buildNotification(idEntidadFinanciera, template) {
        def entidadFinanciera = EntidadFinanciera.get(idEntidadFinanciera)
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)

        //Envia notificaciones sms de solicitudes temporales
        this.notificacionesSolicitudTemporal(entidadFinanciera, configuracion, template)

        //Envia notificaciones sms de solicitudes de credito
        this.notificacionesSolicitudCredito(entidadFinanciera, configuracion, template);
    }

    private void notificacionesSolicitudTemporal(EntidadFinanciera entidadFinanciera, ConfiguracionEntidadFinanciera configuracion, String template){
        def criteria = SolicitudTemporal.createCriteria()
        def solicitudesTemporales = criteria.list{
            ne ('ultimoPaso', SolicitudTemporal.PASO_FINAL)
            eq ('entidadFinanciera', entidadFinanciera)
        }

        if (solicitudesTemporales != null && !solicitudesTemporales?.empty) {
            def map = [:]
            def exitosos = 0
            def erroneos = 0
            def pendientes = 0

            for(SolicitudTemporal solicitudTemporal : solicitudesTemporales){
                def origen = new PlantillaSolicitud(solicitudTemporal)

                def message = this.buildMessage(origen, map, template)
                if(message != null) {
                    def phoneNumber = solicitudTemporal.telefonoCliente
                    try {
                        (this.sendMessage(phoneNumber, message, configuracion)) ? exitosos ++ : erroneos ++
                    } catch (Exception ex){
                        erroneos ++
                        log.error("Ocurrio un error durante el envio de notificaciones de solicitudes temporales", ex)
                        break
                    }
                }
            }

            def total = solicitudesTemporales.size()
            if(total != exitosos){
                pendientes = total - exitosos - erroneos
                log.error("Envio de notificaciones s. temporales. Entidad Financiera: " + entidadFinanciera.id + ". Exitosos: " + exitosos + ". Erroneos " + erroneos + ". Pendientes: " + pendientes)
            }
        }
    }

    private void notificacionesSolicitudCredito(EntidadFinanciera entidadFinanciera, ConfiguracionEntidadFinanciera configuracion, String template){
        def criteria = SolicitudDeCredito.createCriteria()
        def solicitudesCredito = criteria.list{
            createAlias('entidadFinanciera', 'e')
            createAlias('cliente', 'c')
            createAlias('c.telefonosCliente', 'tc')
            createAlias('tc.tipoDeTelefono', 't')

            ne ('ultimoPaso', SolicitudTemporal.PASO_FINAL)
            eq ('entidadFinanciera', entidadFinanciera)
            eq ('tc.vigente', Boolean.TRUE)
            eq ('t.id', Constants.TipoTelefono.CELULAR.value)

            projections {
                property('fechaDeSolicitud', 'fechaDeSolicitud')
                property('c.nombre', 'cliente.nombre')
                property('c.apellidoPaterno', 'cliente.apellidoPaterno')
                property('c.apellidoMaterno', 'cliente.apellidoMaterno')
                property('e.nombre', 'entidadFinanciera.nombre')
                property('tc.numeroTelefonico', 'cliente.telefonoCliente.numeroTelefonico')
            }

            resultTransformer(new AliasToBeanNestedMultiLevelResultTransformer(SolicitudDeCredito.class))
        }

        if (solicitudesCredito != null && !solicitudesCredito?.empty) {
            def map = [:]
            def exitosos = 0
            def erroneos = 0
            def pendientes = 0

            for (SolicitudDeCredito solicitudDeCredito : solicitudesCredito ) {
                def origen = new PlantillaSolicitud(solicitudDeCredito)

                def message = this.buildMessage(origen, map, template)
                if(message != null) {
                    def phoneNumber = solicitudDeCredito.cliente.telefonoCliente.numeroTelefonico
                    try {
                        (this.sendMessage(phoneNumber, message, configuracion)) ? exitosos ++ : erroneos ++
                    } catch (Exception ex){
                        erroneos ++
                        log.error("Ocurrio un error durante el envio de notificaciones de solicitudes temporales", ex)
                        break
                    }
                }
            }

            def total = solicitudesCredito.size()
            if(total != exitosos){
                pendientes = total - exitosos - erroneos
                log.error("Envio de notificaciones s. credito. Entidad Financiera: " + entidadFinanciera.id + ". Exitosos: " + exitosos + ". Erroneos " + erroneos + ". Pendientes: " + pendientes)
            }
        }
    }

    private String buildMessage(Object obj, Map map, String template){
        try {
            Object someObject = obj;

            for (Field field : someObject.getClass().getDeclaredFields()) {
                field.setAccessible(Boolean.TRUE);
                Object value = field.get(someObject);
                if (value != null) {
                    if (value.getClass() == Timestamp.class) {
                        Timestamp timeStampValue = (Timestamp) value
                        Calendar calendar = Calendar.getInstance()
                        calendar.setTimeInMillis(timeStampValue.getTime())
                        def dateFormat = calendar.getTime().format('dd-MM-yyyy')
                        map.put(field.getName(), dateFormat);
                    } else {
                        map.put(field.getName(), value.toString());
                    }
                }
            }

            StrSubstitutor sub = new StrSubstitutor(map);
            return sub.replace(template);
        } catch(Exception e){
            log.error("Ocurrio un error al construir el mensaje sms", e)
            return null
        }
    }

    private boolean sendMessage(String phoneNumber, String message, ConfiguracionEntidadFinanciera configuracion) throws Exception {
        phoneNumber = phoneNumber.replaceAll('-', '')

        Future future = executorService.submit([call: {
                    boolean value = false
//                    this.smsService.sendSMS(phoneNumber, message, configuracion)
                    return value
                }] as Callable)
        boolean response = future.get()

        return response
    }

    def loadSMSTemplate(entidadFinanciera){
        def notificacion = ConfiguracionNotificaciones.findByEntidadFinanciera(entidadFinanciera)
        return notificacion
    }

    def loadAvailableOptionsTemplate(){
        return PlantillaSolicitud.metaClass.properties*.name.findAll{ it != 'class'}
    }

    def saveTemplate(idEntidadFinanciera, params){
        def idNotificacion = Long.parseLong(params.idNotificacion)
        def contenidoSms = (params.contenidoSms).trim()
        def configuracionNotificaciones

        if(idNotificacion == 0) {
            def entidadFinanciera = EntidadFinanciera.get(idEntidadFinanciera)

            configuracionNotificaciones = new ConfiguracionNotificaciones(entidadFinanciera:entidadFinanciera, contenidoSms:contenidoSms)
            configuracionNotificaciones.save(insert: true, validate: false, flush: true)

            return configuracionNotificaciones
            
        } else if(idNotificacion > 0) {
            configuracionNotificaciones = ConfiguracionNotificaciones.findWhere(id: idNotificacion, "entidadFinanciera.id": idEntidadFinanciera)
            configuracionNotificaciones.contenidoSms = contenidoSms
            configuracionNotificaciones.save(validate: false, flush: true)

            if(configuracionNotificaciones.configCron != null && configuracionNotificaciones.cron != null){
                configuracionNotificacionesService.rescheduleJob(configuracionNotificaciones)
            }

            return configuracionNotificaciones
        }
    }

    def deleteTemplate(params){
        def idNotificacion = Long.parseLong(params.idNotificacion)
        def notificacion = ConfiguracionNotificaciones.get(idNotificacion)

        //delete scheduled job
        configuracionNotificacionesService.stopScheduler(idNotificacion)

        notificacion.delete()

        return Boolean.TRUE
    }

    def loadCronInformation(){
        def options = []
        for (sh in Constants.CronConfig.values()) {
            options <<  sh.name()
        }
        return options
    }

    def loadDaysInformation(){
        def daysweek = [:]
        for (sh in Constants.DaysWeek.values()) {
            Constants.DaysWeek dayAsEnum = sh
            daysweek << [ (dayAsEnum.value) : dayAsEnum]
        }
        return daysweek
    }

    def scheduleExecution(idEntidadFinanciera, params){
        def idNotificacion = Long.parseLong(params.idNotificacionCron)
        def notificacion = ConfiguracionNotificaciones.findWhere(id: idNotificacion, "entidadFinanciera.id": idEntidadFinanciera)

        if(notificacion == null) {
            throw new RuntimeException("Operacion invalida para el envio de mansajes")
        }

        def operation

        if(notificacion.configCron == null && notificacion.cron == null){
            operation = INSERT
        } else {
            operation = UPDATE
        }

        this.saveUpdateCron (params, notificacion)

        if (operation == INSERT) {
            //scheduling job
            configuracionNotificacionesService.addJob(notificacion)
        } else {
            //rescheduling job
            configuracionNotificacionesService.rescheduleJob(notificacion)
        }

        return this.loadCronContent(notificacion)
    }

    private void saveUpdateCron (params, notificacion){
        def option = params.cronOptions
        def expression = new CronExpression()
        def configCron

        switch(option) {
        case Constants.CronConfig.MINUTO.name():
            expression.minutes = "0/1"
            expression.dayMonth = "1/1"
            configCron = Constants.CronConfig.MINUTO

            break;
        case Constants.CronConfig.HORA.name():
            expression.minutes = "0"
            expression.getHour(params.hour, expression)
            expression.dayMonth = "1/1"
            configCron = Constants.CronConfig.HORA
            break;
        case Constants.CronConfig.DIA.name():
            expression.getTime(params.dayTime, expression)
            expression.dayMonth = "?"
            expression.dayWeek = "MON-SUN"
            configCron = Constants.CronConfig.DIA
            break;
        case Constants.CronConfig.SEMANA.name():
            expression.getTime(params.weekTime, expression)
            expression.dayMonth = "?"
            expression.dayWeek = params.weekDay
            configCron = Constants.CronConfig.SEMANA
            break;
        case Constants.CronConfig.MES.name():
            expression.getTime(params.monthTime, expression)
            expression.dayMonth = params.dayMonth
            expression.month = "1/1"
            configCron = Constants.CronConfig.MES
            break;
        }

        notificacion.configCron = configCron
        notificacion.cron = expression.getCronExpression()
        notificacion.save(validate: false, flush: true)
    }

    def loadCronContent(notificacion) {
        if(notificacion == null || notificacion.configCron == null) {
            return null
        }

        def content = "Enviar mensajes \${frequency}\${day}\${time}"
        def frecuencia = ""
        def dia = ""
        def horaMin = ""
        def map = [:]
        def (second, minute, hour, dayOfMonth, month, dayOfWeek, year) = notificacion.cron.tokenize(' ')

        switch(notificacion.configCron.ordinal()) {
        case Constants.CronConfig.MINUTO.ordinal():
            frecuencia = "cada " + Constants.CronConfig.MINUTO.name()

            break;
        case Constants.CronConfig.HORA.ordinal():
            horaMin = " a las " + this.formatDateTime(Long.parseLong(hour)) + " horas"

            break;
        case Constants.CronConfig.DIA.ordinal():
            frecuencia = "diariamente"
            horaMin = " a las " + this.formatDateTime(Long.parseLong(hour)) + ":" + this.formatDateTime(Long.parseLong(minute)) + " horas"

            break;
        case Constants.CronConfig.SEMANA.ordinal():
            def weekDay = Constants.DaysWeek.findByValue(dayOfWeek)

            frecuencia = "cada " + Constants.CronConfig.SEMANA.name()
            dia = " el día " + weekDay
            horaMin = " a las " + this.formatDateTime(Long.parseLong(hour)) + ":" + this.formatDateTime(Long.parseLong(minute)) + " horas"

            break;
        case Constants.CronConfig.MES.ordinal():
            def formatDay = this.formatDateTime(Long.parseLong(dayOfMonth))
            frecuencia = "cada " +Constants.CronConfig.MES.name()
            dia = " el día " + formatDay
            horaMin = " a las " + this.formatDateTime(Long.parseLong(hour)) + ":" + this.formatDateTime(Long.parseLong(minute)) + " horas"

            break;
        }

        map << ["frequency" : frecuencia.toLowerCase()]
        map << ["day" : dia.toLowerCase()]
        map << ["time" : horaMin.toLowerCase()]

        StrSubstitutor sub = new StrSubstitutor(map);
        return sub.replace(content);
    }

    def loadCronConfiguration(params) {
        def notificacion = ConfiguracionNotificaciones.get(params.notification)
        def envioNotificaciones = new EnvioNotificaciones()
        envioNotificaciones.weekDay = Constants.DaysWeek.LUNES.value
        envioNotificaciones.dayMonth = Constants.DAY_MONTH

        if(notificacion.cron == null || notificacion.cron == "") {
            return envioNotificaciones
        }

        def (second, minute, hour, dayOfMonth, month, dayOfWeek, year) = notificacion.cron.tokenize(' ')

        switch(notificacion.configCron.ordinal()) {
        case Constants.CronConfig.MINUTO.ordinal():
            envioNotificaciones.frequency = Constants.CronConfig.MINUTO.name()

            break;
        case Constants.CronConfig.HORA.ordinal():
            envioNotificaciones.frequency = Constants.CronConfig.HORA.name()
            envioNotificaciones.hour = hour

            break;
        case Constants.CronConfig.DIA.ordinal():
            envioNotificaciones.frequency = Constants.CronConfig.DIA.name()
            envioNotificaciones.dayTime = hour + ":" + this.formatDateTime(Long.parseLong(minute))

            break;
        case Constants.CronConfig.SEMANA.ordinal():
            envioNotificaciones.frequency = Constants.CronConfig.SEMANA.name()
            envioNotificaciones.weekDay = dayOfWeek
            envioNotificaciones.weekTime =  hour + ":" + this.formatDateTime(Long.parseLong(minute))

            break;
        case Constants.CronConfig.MES.ordinal():
            envioNotificaciones.frequency = Constants.CronConfig.MES.name()
            envioNotificaciones.dayMonth = dayOfMonth
            envioNotificaciones.monthTime = hour + ":" + this.formatDateTime(Long.parseLong(minute))

            break;
        }

        return envioNotificaciones
    }

    private String formatDateTime(Long number){
        // zero-pad to 2 characters
        return String.format('%02d',number)
    }

    def deleteCron(params, idEntidadFinanciera){
        def idNotificacion = Long.parseLong(params.idNotificacion)
        def notificacion = ConfiguracionNotificaciones.findWhere(id: idNotificacion, "entidadFinanciera.id": idEntidadFinanciera)
        
        notificacion.configCron = null
        notificacion.cron = null
        if (notificacion.save(validate: false, flush: true)){
            //delete scheduled job
            configuracionNotificacionesService.stopScheduler(idNotificacion)
        }
        
        return Boolean.TRUE
    }
}
