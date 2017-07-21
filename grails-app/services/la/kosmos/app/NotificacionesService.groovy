package la.kosmos.app

import grails.transaction.Transactional
import java.lang.reflect.Field
import java.sql.Timestamp
import java.util.Calendar
import java.util.HashSet
import java.util.concurrent.Callable
import java.util.concurrent.Future
import la.kosmos.app.bo.Cron
import la.kosmos.app.bo.CronExpression
import la.kosmos.app.bo.EnvioNotificaciones
import la.kosmos.app.bo.PlantillaSolicitud
import la.kosmos.app.vo.Constants
import org.apache.commons.lang.text.StrSubstitutor
import org.hibernate.criterion.CriteriaSpecification
import org.hibernate.transform.Transformers


@Transactional
class NotificacionesService {
    def configuracionNotificacionesService
    def smsService
    def executorService
    def emailService

    def buildNotification(id) {
        NotificacionesCron notificacionCron = NotificacionesCron.get(id)
        //Envia notificaciones de solicitudes temporales
        this.notificacionesSolicitudTemporal(notificacionCron)
        //Envia notificaciones de solicitudes de credito
        this.notificacionesSmsSolicitudCredito(notificacionCron)

        this.notificacionesEmailSolicitudCredito(notificacionCron)
    }

    private void notificacionesSolicitudTemporal(NotificacionesCron notificacionCron){
        def configuracion = notificacionCron.configuracionEntidadFinanciera
        EntidadFinanciera entidadFinanciera = configuracion.entidadFinanciera

        notificacionCron.templates.each {
            def notificacion = it

            if(notificacion.tipoPlantilla == Constants.TipoPlantilla.EMAIL &&
                (configuracion.emailHost == null || configuracion.emailHost == "" ||
                    configuracion.emailFrom == null || configuracion.emailFrom == "" ||
                    configuracion.emailPort == null || configuracion.emailPort == "" ||
                    configuracion.emailUsername == null || configuracion.emailUsername == "" ||
                    configuracion.emailPassword == null || configuracion.emailPassword == "")) {
                throw new Exception("Error al enviar las solicitudes temporales. No se ha configurado el remitente de correo electronico")
            }

            def criteria = SolicitudTemporal.createCriteria()
            def solicitudesTemporales = criteria.list{
                eq ('ultimoPaso', notificacion.status)
                eq ('entidadFinanciera', entidadFinanciera)
                eq ('solicitudVigente', Boolean.TRUE)
            }

            if (solicitudesTemporales != null && !solicitudesTemporales?.empty) {
                def map = [:]
                def smsExitosos = 0
                def smsErroneos = 0
                def smsPendientes = 0
                def emailExitosos = 0
                def emailErroneos = 0
                def emailPendientes = 0

                for(SolicitudTemporal solicitudTemporal : solicitudesTemporales){
                    def origen = new PlantillaSolicitud(solicitudTemporal)
                    String message = this.buildMessage(origen, map, notificacion.plantilla)

                    switch(notificacion.tipoPlantilla){
                    case Constants.TipoPlantilla.SMS:
                        if(message != null) {
                            def phoneNumber = solicitudTemporal.telefonoCliente
                            try {
                                (this.sendMessage(phoneNumber, message, configuracion)) ? smsExitosos ++ : smsErroneos ++
                            } catch (Exception ex){
                                smsErroneos ++
                                log.error("Ocurrio un error durante el envio de notificaciones de solicitudes temporales", ex)
                                break
                            }
                        }
                        break;
                    case Constants.TipoPlantilla.EMAIL:
                        if(message != null) {
                            String email = solicitudTemporal.emailCliente

                            try {
                                (this.sendEmailMessage(notificacion.asunto, email, message, configuracion)) ? emailExitosos ++ : emailErroneos ++
                            } catch (Exception ex){
                                emailErroneos ++
                                log.error("Ocurrio un error durante el envio de notificaciones de solicitudes temporales", ex)
                                break
                            }
                        }
                        break;
                    }
                }

                def total = solicitudesTemporales.size()

                switch(notificacion.tipoPlantilla){
                case Constants.TipoPlantilla.SMS:
                    if(total != smsExitosos){
                        smsPendientes = total - smsExitosos - smsErroneos
                        log.error ("Envio de notificaciones SMS de solicitudes temporales. Entidad Financiera: " + entidadFinanciera.id + ". Estatus: "+ notificacion.status + ". Exitosos: " + smsExitosos + ". Erroneos: " + smsErroneos + ". Pendientes: " + smsPendientes)
                    }
                    break;
                case Constants.TipoPlantilla.EMAIL:
                    if(total != emailExitosos){
                        emailPendientes = total - emailExitosos - emailErroneos
                        log.error ("Envio de notificaciones de solicitudes temporales por correo electronico. Entidad Financiera: " + entidadFinanciera.id + " Estatus: "+notificacion.status + ". Exitosos: " + emailExitosos + ". Erroneos: " + emailErroneos + ". Pendientes: " + emailPendientes)
                    }

                    break;
                }
            }
        }
    }

    private void notificacionesSmsSolicitudCredito(NotificacionesCron notificacionCron){
        def configuracion = notificacionCron.configuracionEntidadFinanciera
        EntidadFinanciera entidadFinanciera = configuracion.entidadFinanciera
        notificacionCron.templates.each {
            if(it.tipoPlantilla == Constants.TipoPlantilla.SMS){
                def notificacion = it
                def criteria = SolicitudDeCredito.createCriteria()
                def solicitudesCredito = criteria.list{
                    createAlias('entidadFinanciera', 'e')
                    createAlias('cliente', 'c')
                    createAlias('c.telefonosCliente', 'tc')
                    createAlias('tc.tipoDeTelefono', 't')
                    createAlias('sucursal', 'suc', CriteriaSpecification.LEFT_JOIN)

                    eq ('ultimoPaso', notificacion.status)
                    eq ('entidadFinanciera', entidadFinanciera)
                    eq ('solicitudVigente', Boolean.TRUE)
                    eq ('tc.vigente', Boolean.TRUE)
                    eq ('t.id', Constants.TipoTelefono.CELULAR.value)

                    projections {
                        property('id', 'id')
                        property('fechaDeSolicitud', 'fechaDeSolicitud')
                        property('c.nombre', 'cliente.nombre')
                        property('c.apellidoPaterno', 'cliente.apellidoPaterno')
                        property('c.apellidoMaterno', 'cliente.apellidoMaterno')
                        property('e.nombre', 'entidadFinanciera.nombre')
                        property('tc.numeroTelefonico', 'cliente.telefonoCliente.numeroTelefonico')
                        property('suc.nombre', 'sucursal.nombre')
                    }

                    resultTransformer(new AliasToBeanNestedMultiLevelResultTransformer(SolicitudDeCredito.class))
                }

                if (solicitudesCredito != null && !solicitudesCredito?.empty) {
                    def map = [:]
                    def exitosos = 0
                    def erroneos = 0
                    def pendientes = 0

                    for (SolicitudDeCredito solicitudDeCredito : solicitudesCredito ) {
                        criteria = ProductoSolicitud.createCriteria()
                        def list = criteria.listDistinct {
                            createAlias('solicitud', 's')
                            createAlias('producto', 'p')
                            eq ('solicitud.id', solicitudDeCredito.id)
                        }
                        ProductoSolicitud productoSolicitud = list[0]
                        def origen
                        if(productoSolicitud != null){
                            origen = new PlantillaSolicitud(productoSolicitud)
                        } else {
                            origen = new PlantillaSolicitud(solicitudDeCredito)
                        }

                        def message = this.buildMessage(origen, map, notificacion.plantilla)
                        if(message != null) {

                            def phoneNumber = solicitudDeCredito.cliente.telefonoCliente.numeroTelefonico

                            try {
                                (this.sendMessage(phoneNumber, message, notificacion.configuracionEntidadFinanciera)) ? exitosos ++ : erroneos ++
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
                        log.error ("Envio de notificaciones SMS de solicitudes formales. Entidad Financiera: " + entidadFinanciera.id + ". Estatus: "+ notificacion.status + ". Exitosos: " + exitosos + ". Erroneos: " + erroneos + ". Pendientes: " + pendientes)
                    }
                }
            }
        }
    }

    private void notificacionesEmailSolicitudCredito(NotificacionesCron notificacionCron){
        def configuracion = notificacionCron.configuracionEntidadFinanciera
        EntidadFinanciera entidadFinanciera = configuracion.entidadFinanciera

        notificacionCron.templates.each {
            if(it.tipoPlantilla == Constants.TipoPlantilla.EMAIL){
                def notificacion = it

                if(notificacion.tipoPlantilla == Constants.TipoPlantilla.EMAIL &&
                    (configuracion.emailHost == null || configuracion.emailHost == "" ||
                        configuracion.emailFrom == null || configuracion.emailFrom == "" ||
                        configuracion.emailPort == null || configuracion.emailPort == "" ||
                        configuracion.emailUsername == null || configuracion.emailUsername == "" ||
                        configuracion.emailPassword == null || configuracion.emailPassword == "")) {
                    throw new Exception("Error al enviar las solicitudes formales. No se ha configurado el remitente de correo electronico")
                }

                def criteria = SolicitudDeCredito.createCriteria()
                def solicitudesCredito = criteria.list{
                    createAlias('entidadFinanciera', 'e')
                    createAlias('cliente', 'c')
                    createAlias('c.emailsCliente', 'ec')
                    createAlias('ec.tipoDeEmail', 't')
                    createAlias('sucursal', 'suc', CriteriaSpecification.LEFT_JOIN)

                    eq ('ultimoPaso', notificacion.status)
                    eq ('entidadFinanciera', entidadFinanciera)
                    eq ('solicitudVigente', Boolean.TRUE)
                    eq ('ec.vigente', Boolean.TRUE)
                    eq ('t.id', Constants.TipoEmail.PERSONAL.value)
                    eq ('t.activo', Boolean.TRUE)

                    projections {
                        property('id', 'id')
                        property('fechaDeSolicitud', 'fechaDeSolicitud')
                        property('c.nombre', 'cliente.nombre')
                        property('c.apellidoPaterno', 'cliente.apellidoPaterno')
                        property('c.apellidoMaterno', 'cliente.apellidoMaterno')
                        property('e.nombre', 'entidadFinanciera.nombre')
                        property('ec.direccionDeCorreo', 'cliente.emailCliente.direccionDeCorreo')
                        property('suc.nombre', 'sucursal.nombre')
                    }

                    resultTransformer(new AliasToBeanNestedMultiLevelResultTransformer(SolicitudDeCredito.class))
                }

                if (solicitudesCredito != null && !solicitudesCredito?.empty) {
                    def map = [:]
                    def exitosos = 0
                    def erroneos = 0
                    def pendientes = 0

                    for (SolicitudDeCredito solicitudDeCredito : solicitudesCredito ) {
                        criteria = ProductoSolicitud.createCriteria()
                        def list = criteria.listDistinct {
                            createAlias('solicitud', 's')
                            createAlias('producto', 'p')
                            eq ('solicitud.id', solicitudDeCredito.id)
                        }

                        ProductoSolicitud productoSolicitud = list[0]
                        def origen

                        if(productoSolicitud != null){
                            origen = new PlantillaSolicitud(productoSolicitud)
                        } else {
                            origen = new PlantillaSolicitud(solicitudDeCredito)
                        }
                        String message = this.buildMessage(origen, map, notificacion.plantilla)

                        if(message != null) {
                            String email = solicitudDeCredito.cliente.emailCliente.direccionDeCorreo

                            try {

                                (this.sendEmailMessage(notificacion.asunto, email, message, configuracion)) ? exitosos ++ : erroneos ++
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
                        log.error ("Envio de notificaciones de solicitudes formales por correo electronico. Entidad Financiera: " + entidadFinanciera.id + ". Estatus: "+notificacion.status + ". Exitosos: " + exitosos + ". Erroneos: " + erroneos + ". Pendientes: " + pendientes)
                    }
                }
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
                } else {
                    map.put(field.getName(), "-");
                }
            }

            char esc = '{'
            StrSubstitutor sub = new StrSubstitutor(map, "{", "}", esc);
            return sub.replace(template);
        } catch(Exception e){
            log.error("Ocurrio un error al construir la plantilla", e)
            return null
        }
    }

    private boolean sendMessage(String phoneNumber, String message, ConfiguracionEntidadFinanciera configuracion) throws Exception {
        phoneNumber = phoneNumber.replaceAll('-', '')

        Future future = executorService.submit([call: {
                    boolean value = this.smsService.sendSMS(phoneNumber, message, configuracion)
                    return value
                }] as Callable)
        boolean response = future.get()

        return response
    }

    private boolean sendEmailMessage(String asunto, String email, String message, ConfiguracionEntidadFinanciera configuracion) throws Exception {
        Future future = executorService.submit([call: {
                    boolean value = emailService.sendPlainText(configuracion, asunto, email, message)
                    return value
                }] as Callable)
        boolean response = future.get()

        return response
    }

    def getSmsTemplates(EntidadFinanciera entidadFinanciera){

        return this.getTemplatesByType(entidadFinanciera, Constants.TipoPlantilla.SMS)
    }

    private List<NotificacionesPlantilla> getTemplatesByType(EntidadFinanciera entidadFinanciera, Constants.TipoPlantilla tipo){
        def criteria = NotificacionesPlantilla.createCriteria()
        def list = criteria.list {
            createAlias('configuracionEntidadFinanciera', 'cef')
            eq ('cef.entidadFinanciera', entidadFinanciera)
            eq ('tipoPlantilla', tipo)
            order("status", "asc")
        }

        return list
    }

    def loadAvailableOptionsTemplate(){
        return PlantillaSolicitud.metaClass.properties*.name.sort().findAll{ it != 'class'}
    }

    def loadAvailableSmsStatus(EntidadFinanciera entidadFinanciera){
        return this.loadAvailableStatusByType(entidadFinanciera, Constants.TipoPlantilla.SMS)
    }

    private HashSet<Integer> loadAvailableStatusByType(EntidadFinanciera entidadFinanciera, Constants.TipoPlantilla tipo){
        def criteria = NotificacionesPlantilla.createCriteria()
        def list = criteria.listDistinct {
            createAlias('configuracionEntidadFinanciera', 'cef')
            eq ('cef.entidadFinanciera', entidadFinanciera)
            eq ('tipoPlantilla', tipo)

            projections {
                property('status', 'status')
            }

            resultTransformer(Transformers.aliasToBean(NotificacionesPlantilla.class))
        }

        def currentStatus = []
        list.each {
            currentStatus << it.status
        }

        def allStatus = []
        for(int x = 1; x <= Constants.STATUS_SOLICITUD_NOTIFICACION; x++) {
            allStatus << x
        }

        def all = allStatus as Set
        def current = currentStatus as Set
        all.removeAll(current)

        return all
    }

    def saveSmsTemplate(EntidadFinanciera entidadFinanciera, params){
        def status = (params.status != null) ? Integer.parseInt(params.status) : null
        return this.saveTemplate(params, entidadFinanciera, Constants.TipoPlantilla.SMS, status, null)
    }

    private Map saveTemplate(params, EntidadFinanciera entidadFinanciera, Constants.TipoPlantilla tipo, Integer status, String asunto){
        def respuesta = [:]
        def idTemplate = Long.parseLong(params.idTemplate)
        def template = (params.contenido).trim()
        NotificacionesPlantilla plantilla

        if(idTemplate == 0) {
            ConfiguracionEntidadFinanciera configuracion = ConfiguracionEntidadFinanciera.findByEntidadFinanciera(entidadFinanciera)
            plantilla = new NotificacionesPlantilla()
            plantilla.configuracionEntidadFinanciera = configuracion
            plantilla.plantilla = template
            plantilla.tipoPlantilla = tipo
            plantilla.status = status
            plantilla.asunto = asunto

            plantilla.save(insert: Boolean.TRUE, validate: Boolean.FALSE, flush: Boolean.FALSE)

        } else if(idTemplate > 0) {
            plantilla = NotificacionesPlantilla.get(idTemplate)

            if (plantilla.configuracionEntidadFinanciera.entidadFinanciera.id != entidadFinanciera.id){
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema. No tiene permisos para modificar la información del usuario"
                return respuesta
            }

            plantilla.plantilla = template
            plantilla.asunto = asunto

            plantilla.save(validate: Boolean.FALSE, flush: Boolean.FALSE)
        }

        return respuesta
    }

    def getTemplate(params){
        return NotificacionesPlantilla.get(params.idTemplate)
    }

    def deleteSmsTemplate(EntidadFinanciera entidadFinanciera, params){
        return deleteTemplate(entidadFinanciera, params, Constants.TipoPlantilla.SMS)
    }

    def deleteTemplate(EntidadFinanciera entidadFinanciera, params, Constants.TipoPlantilla tipo) {
        def respuesta = [:]
        def idTemplate = Long.parseLong(params.idTemplate)

        NotificacionesPlantilla plantilla = NotificacionesPlantilla.get(idTemplate)

        if (plantilla.configuracionEntidadFinanciera.entidadFinanciera.id != entidadFinanciera.id){
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = "Ocurrió un problema. No tiene permisos para modificar la información del usuario"
            return respuesta
        }

        NotificacionesConfiguracion.removeAll(plantilla)

        plantilla.delete()

        return respuesta
    }

    def getCronList(EntidadFinanciera entidadFinanciera){
        def criteria = NotificacionesCron.createCriteria()
        def list = criteria.list {
            createAlias('configuracionEntidadFinanciera', 'cef')
            eq ('cef.entidadFinanciera', entidadFinanciera)
            order("id", "asc")
        }

        def cronList = []
        list.each {
            Cron c = new Cron(it)
            this.loadCronContent(c)
            cronList << c
        }

        return cronList
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

    def saveCron(EntidadFinanciera entidadFinanciera, params){
        def respuesta = [:]
        def idCron = Long.parseLong(params.idCron)
        NotificacionesCron notificacionCron

        EnvioNotificaciones envio = new EnvioNotificaciones(params)

        if(idCron == 0) {
            notificacionCron = new NotificacionesCron()
            notificacionCron.configuracionEntidadFinanciera = ConfiguracionEntidadFinanciera.findByEntidadFinanciera(entidadFinanciera)
            notificacionCron.cron = this.buildCronExpression(envio)
            notificacionCron.configCron = this.buildCronConfiguration(params)

            if(notificacionCron.save(insert: Boolean.TRUE, validate: Boolean.FALSE, flush: Boolean.FALSE)){
                envio.templateOptions.each {
                    NotificacionesPlantilla np = NotificacionesPlantilla.get(it)
                    def data = NotificacionesConfiguracion.create(notificacionCron, np)
                    if(data.notificacionesPlantilla == null) {
                        throw new Exception("Ocurrió un error al guardar las plantillas")
                    }
                }
            }

            //scheduling job
            configuracionNotificacionesService.addJob(notificacionCron)

        } else if(idCron > 0) {
            notificacionCron = NotificacionesCron.get(idCron)

            if (notificacionCron.configuracionEntidadFinanciera.entidadFinanciera.id != entidadFinanciera.id){
                respuesta.error = Boolean.TRUE
                respuesta.mensaje = "Ocurrió un problema. No tiene permisos para modificar la información"
                return respuesta
            }

            notificacionCron.cron = this.buildCronExpression(envio)
            notificacionCron.configCron = this.buildCronConfiguration(params)

            NotificacionesConfiguracion.removeAll(notificacionCron)
            envio.templateOptions.each {
                NotificacionesPlantilla np = NotificacionesPlantilla.get(it)
                def data = NotificacionesConfiguracion.create(notificacionCron, np)
                if(data.notificacionesPlantilla == null) {
                    throw new Exception("Ocurrió un error al actualizar las plantillas")
                }
            }

            notificacionCron.save(validate: Boolean.FALSE, flush: Boolean.FALSE)

            //updating job
            configuracionNotificacionesService.rescheduleJob(notificacionCron)
        }
        return respuesta
    }

    private String buildCronExpression (EnvioNotificaciones envio){
        def option = envio.cronOptions
        def expression = new CronExpression()

        switch(option) {
        case Constants.CronConfig.MINUTO.name():
            expression.minutes = "0/1"
            expression.dayMonth = "1/1"
            break;
        case Constants.CronConfig.HORA.name():
            expression.minutes = "0"
            expression.getHour(envio.hour, expression)
            expression.dayMonth = "1/1"
            break;
        case Constants.CronConfig.DIA.name():
            expression.getTime(envio.dayTime, expression)
            expression.dayMonth = "?"
            expression.dayWeek = "MON-SUN"
            break;
        case Constants.CronConfig.SEMANA.name():
            expression.getTime(envio.weekTime, expression)
            expression.dayMonth = "?"
            expression.dayWeek = envio.weekDay
            break;
        case Constants.CronConfig.MES.name():
            expression.getTime(envio.monthTime, expression)
            expression.dayMonth = envio.dayMonth
            expression.month = "1/1"
            break;
        }

        return expression.getCronExpression()
    }

    private Constants.CronConfig buildCronConfiguration (params){
        def option = params.cronOptions
        def configCron

        switch(option) {
        case Constants.CronConfig.MINUTO.name():
            configCron = Constants.CronConfig.MINUTO
            break;
        case Constants.CronConfig.HORA.name():
            configCron = Constants.CronConfig.HORA
            break;
        case Constants.CronConfig.DIA.name():
            configCron = Constants.CronConfig.DIA
            break;
        case Constants.CronConfig.SEMANA.name():
            configCron = Constants.CronConfig.SEMANA
            break;
        case Constants.CronConfig.MES.name():
            configCron = Constants.CronConfig.MES
            break;
        }

        return configCron
    }

    private void loadCronContent(Cron notificacionCron) {
        def content = "Enviar mensajes \${frequency}\${day}\${time}"
        def frecuencia = ""
        def dia = ""
        def horaMin = ""
        def map = [:]
        def (second, minute, hour, dayOfMonth, month, dayOfWeek, year) = notificacionCron.cron.tokenize(' ')

        switch(notificacionCron.configCron.ordinal()) {
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
        notificacionCron.cronExpression = sub.replace(content);
    }

    def loadCronConfiguration(params) {
        def idCron = Long.parseLong(params.idCron)
        def envioNotificaciones = new EnvioNotificaciones()
        envioNotificaciones.weekDay = Constants.DaysWeek.LUNES.value
        envioNotificaciones.dayMonth = Constants.DAY_MONTH

        if(idCron == 0) {
            return envioNotificaciones
        }

        def notificacion = NotificacionesCron.get(idCron)

        def (second, minute, hour, dayOfMonth, month, dayOfWeek, year) = notificacion.cron.tokenize(' ')

        switch(notificacion.configCron.ordinal()) {
        case Constants.CronConfig.MINUTO.ordinal():
            envioNotificaciones.cronOptions = Constants.CronConfig.MINUTO.name()

            break;
        case Constants.CronConfig.HORA.ordinal():
            envioNotificaciones.cronOptions = Constants.CronConfig.HORA.name()
            envioNotificaciones.hour = hour

            break;
        case Constants.CronConfig.DIA.ordinal():
            envioNotificaciones.cronOptions = Constants.CronConfig.DIA.name()
            envioNotificaciones.dayTime = hour + ":" + this.formatDateTime(Long.parseLong(minute))

            break;
        case Constants.CronConfig.SEMANA.ordinal():
            envioNotificaciones.cronOptions = Constants.CronConfig.SEMANA.name()
            envioNotificaciones.weekDay = dayOfWeek
            envioNotificaciones.weekTime =  hour + ":" + this.formatDateTime(Long.parseLong(minute))

            break;
        case Constants.CronConfig.MES.ordinal():
            envioNotificaciones.cronOptions = Constants.CronConfig.MES.name()
            envioNotificaciones.dayMonth = dayOfMonth
            envioNotificaciones.monthTime = hour + ":" + this.formatDateTime(Long.parseLong(minute))

            break;
        }

        envioNotificaciones.templateOptions = []
        notificacion.templates.each {
            envioNotificaciones.templateOptions << it.id
        }

        return envioNotificaciones
    }

    private String formatDateTime(Long number){
        // zero-pad to 2 characters
        return String.format('%02d',number)
    }

    def deleteCron(params, entidadFinanciera){
        def respuesta = [:]
        def idCron = Long.parseLong(params.idCron)

        NotificacionesCron notificacionCron = NotificacionesCron.get(idCron)

        if (notificacionCron.configuracionEntidadFinanciera.entidadFinanciera.id != entidadFinanciera.id){
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = "Ocurrió un problema. No tiene permisos para modificar la información"
            return respuesta
        }

        NotificacionesConfiguracion.removeAll(notificacionCron)

        //delete scheduled job
        configuracionNotificacionesService.stopScheduler(notificacionCron)

        notificacionCron.delete()

        return respuesta
    }

    def getEmailTemplates(EntidadFinanciera entidadFinanciera){
        return this.getTemplatesByType(entidadFinanciera, Constants.TipoPlantilla.EMAIL)
    }

    def loadAvailableEmailStatus(EntidadFinanciera entidadFinanciera){
        return this.loadAvailableStatusByType(entidadFinanciera, Constants.TipoPlantilla.EMAIL)
    }

    def saveEmailTemplate(EntidadFinanciera entidadFinanciera, params){
        def respuesta = [:]
        def status = (params.statusEmail != null) ? Integer.parseInt(params.statusEmail) : null
        def asunto = params.asunto

        respuesta = this.saveTemplate(params, entidadFinanciera, Constants.TipoPlantilla.EMAIL, status, asunto)
        return respuesta
    }

    def deleteEmailTemplate(EntidadFinanciera entidadFinanciera, params){
        return deleteTemplate(entidadFinanciera, params, Constants.TipoPlantilla.EMAIL)
    }

    def enableSmsTemplate(EntidadFinanciera entidadFinanciera){
        return (this.loadAvailableStatusByType(entidadFinanciera, Constants.TipoPlantilla.SMS)).size() > 0
    }

    def enableEmailTemplate(EntidadFinanciera entidadFinanciera){
        return (this.loadAvailableStatusByType(entidadFinanciera, Constants.TipoPlantilla.EMAIL)).size() > 0
    }
}