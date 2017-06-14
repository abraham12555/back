package la.kosmos.app



class NotificacionesJob {

    def notificacionesService

    def execute(context) {
        def id = context.getTrigger().getKey().getName()
        def idEntidadFinanciera = context.mergedJobDataMap.get("idEntidadFinanciera")
        def template = context.mergedJobDataMap.get("smsTemplate")

        this.notificacionesService.buildNotification(idEntidadFinanciera, template)
    }
}
