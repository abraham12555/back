package la.kosmos.app

class NotificacionesConfiguracion implements Serializable {

    NotificacionesPlantilla notificacionesPlantilla
    NotificacionesCron notificacionesCron

    NotificacionesConfiguracion() {

    }

    NotificacionesConfiguracion(NotificacionesPlantilla notificacionesPlantilla, NotificacionesCron notificacionesCron) {
        this.notificacionesPlantilla = notificacionesPlantilla
        this.notificacionesCron = notificacionesCron
    }

    static constraints = {
        notificacionesPlantilla (nullable: false)
        notificacionesCron (nullable: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_notificaciones_configuracion', params:[sequence:'notificaciones_configuracion_id_seq']
    }
}
