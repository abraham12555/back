package la.kosmos.app

import grails.converters.JSON
import la.kosmos.app.bo.EnvioNotificaciones

class NotificacionesController {
    def notificacionesService

    def getSmsTemplates() {
        def entidadFinanciera = session.usuario.entidadFinanciera
        def templates = notificacionesService.getSmsTemplates(entidadFinanciera)

        def response = [:]
        response.templates = templates
        render response as JSON
    }

    def loadDataSmsTemplate() {
        def response = [:]
        def entidadFinanciera = session.usuario.entidadFinanciera

        response.fields = notificacionesService.loadAvailableOptionsTemplate()
        response.status = notificacionesService.loadAvailableSmsStatus(entidadFinanciera)
        render response as JSON
    }

    def saveSmsTemplate(){
        def entidadFinanciera = session.usuario.entidadFinanciera

        def response = notificacionesService.saveSmsTemplate(entidadFinanciera, params)
        render response as JSON
    }

    def viewTemplateDetails() {
        def response = [:]

        response.fields = notificacionesService.loadAvailableOptionsTemplate()
        response.template = notificacionesService.getTemplate(params)
        render response as JSON
    }

    def deleteSmsTemplate(){
        def entidadFinanciera = session.usuario.entidadFinanciera

        def response = notificacionesService.deleteSmsTemplate(entidadFinanciera, params)
        render response as JSON
    }

    def getCronList() {
        def entidadFinanciera = session.usuario.entidadFinanciera
        def cronList = notificacionesService.getCronList(entidadFinanciera)

        def response = [:]
        response.cronList = cronList
        render response as JSON
    }

    def loadCronInformation(){
        def response = [:]
        response.listCronOptions = notificacionesService.loadCronInformation()
        response.listweekDayOptions = notificacionesService.loadDaysInformation()
        render response as JSON
    }

    def loadDataCron(){
        def response = [:]
        response.notificacionEnvio = notificacionesService.loadCronConfiguration(params)
        render response as JSON
    }

    def saveCron() {
        def response = [:]
        def entidadFinanciera = session.usuario.entidadFinanciera

        response.content = notificacionesService.scheduleExecution(entidadFinanciera, params)
        render response as JSON
    }

    def deleteCron(){
        def response = [:]
        def entidadFinanciera = session.usuario.entidadFinanciera

        response.confirm = notificacionesService.deleteCron(params, entidadFinanciera)
        render response as JSON
    }

    def validCronConfig(){
        EnvioNotificaciones envio = new EnvioNotificaciones(request.JSON)
        def entidadFinanciera = session.usuario.entidadFinanciera

        def response = [:]
        response.estatus = notificacionesService.validCronConfig(envio, entidadFinanciera)
        render response as JSON
    }

    def getEmailTemplates() {
        def entidadFinanciera = session.usuario.entidadFinanciera
        def templates = notificacionesService.getEmailTemplates(entidadFinanciera)

        def response = [:]
        response.templates = templates
        render response as JSON
    }

    def loadDataEmailTemplate() {
        def response = [:]
        def entidadFinanciera = session.usuario.entidadFinanciera

        response.fields = notificacionesService.loadAvailableOptionsTemplate()
        response.status = notificacionesService.loadAvailableEmailStatus(entidadFinanciera)
        render response as JSON
    }

    def saveEmailTemplate(){
        def entidadFinanciera = session.usuario.entidadFinanciera

        def response = notificacionesService.saveEmailTemplate(entidadFinanciera, params)
        render response as JSON
    }

    def deleteEmailTemplate(){
        def entidadFinanciera = session.usuario.entidadFinanciera

        def response = notificacionesService.deleteEmailTemplate(entidadFinanciera, params)
        render response as JSON
    }
}
