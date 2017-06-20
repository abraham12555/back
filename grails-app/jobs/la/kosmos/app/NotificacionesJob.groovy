package la.kosmos.app



class NotificacionesJob {

    def notificacionesService

    def execute(context) {
        def id = context.getTrigger().getKey().getName()
        this.notificacionesService.buildNotification(id)
    }
}