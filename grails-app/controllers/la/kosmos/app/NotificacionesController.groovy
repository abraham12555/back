package la.kosmos.app

import grails.converters.JSON

class NotificacionesController {
    def notificacionesService

    def loadDataTemplate() {
        def listData = notificacionesService.loadAvailableOptionsTemplate()
        render listData as JSON
    }

    def saveTemplate(){
        def response = [:]
        def configuracion = session.configuracion
        def data = notificacionesService.saveTemplate(configuracion.entidadFinanciera.id, params)

        response.templateSms = data.contenidoSms
        response.notificacion = data.id
        render response as JSON
    }

    def searchDataTemplate() {
        def response = [:]
        def configuracion = session.configuracion
        def notificacion = notificacionesService.loadSMSTemplate(configuracion.entidadFinanciera)
        response.templateSms = notificacion.contenidoSms

        def listData = notificacionesService.loadAvailableOptionsTemplate()
        response.availableOptions = listData
        render response as JSON
    }

    def deleteDataTemplate(){
        def response = [:]
        response.confirm = notificacionesService.deleteTemplate(params)
        render response as JSON
    }
    
    def loadDataCron(){
        def response = [:]
        response.notificacionEnvio = notificacionesService.loadCronConfiguration(params)
        render response as JSON
    }
    
    def saveCron() {
        def response = [:]
        def configuracion = session.configuracion
        response.content = notificacionesService.scheduleExecution(configuracion.entidadFinanciera.id, params)
        render response as JSON
    }
    
    def deleteCron(){
        def response = [:]
        def configuracion = session.configuracion
        response.confirm = notificacionesService.deleteCron(params, configuracion.entidadFinanciera.id)
        render response as JSON
    }
}
