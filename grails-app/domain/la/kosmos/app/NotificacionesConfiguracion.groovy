package la.kosmos.app
import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class NotificacionesConfiguracion implements Serializable {

    private static final long serialVersionUID = 1

    NotificacionesCron notificacionesCron
    NotificacionesPlantilla notificacionesPlantilla

    @Override
    boolean equals(other) {
        if (!(other instanceof NotificacionesConfiguracion)) {
            return false
        }

        other.notificacionesCron?.id == notificacionesCron?.id && other.notificacionesPlantilla?.id == notificacionesPlantilla?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (notificacionesCron) builder.append(notificacionesCron.id)
        if (notificacionesPlantilla) builder.append(notificacionesPlantilla.id)
        builder.toHashCode()
    }

    static NotificacionesConfiguracion get(long notificacionesCronId, long notificacionesPlantillaId) {
        criteriaFor(notificacionesCronId, notificacionesPlantillaId).get()
    }

    static boolean exists(long notificacionesCronId, long notificacionesPlantillaId) {
        criteriaFor(notificacionesCronId, notificacionesPlantillaId).count()
    }

    private static DetachedCriteria criteriaFor(long notificacionesCronId, long notificacionesPlantillaId) {
        NotificacionesConfiguracion.where {
            notificacionesCron == NotificacionesCron.load(notificacionesCronId) &&
            notificacionesPlantilla == NotificacionesPlantilla.load(notificacionesPlantillaId)
        }
    }

    static NotificacionesConfiguracion create(NotificacionesCron notificacionesCron, NotificacionesPlantilla notificacionesPlantilla, boolean flush = false) {
        def instance = new NotificacionesConfiguracion(notificacionesCron: notificacionesCron, notificacionesPlantilla: notificacionesPlantilla)
        instance.save(flush: flush, insert: true)
        instance
    }

    static boolean remove(NotificacionesCron nc, NotificacionesPlantilla np, boolean flush = false) {
        if (nc == null || np == null) return false

        int rowCount = NotificacionesConfiguracion.where { notificacionesCron == nc && notificacionesPlantilla == np }.deleteAll()

        if (flush) { NotificacionesConfiguracion.withSession { it.flush() } }

        rowCount
    }

    static void removeAll(NotificacionesCron nc, boolean flush = false) {
        if (nc == null) return

        NotificacionesConfiguracion.where { notificacionesCron == nc }.deleteAll()

        if (flush) { NotificacionesConfiguracion.withSession { it.flush() } }
    }

    static void removeAll(NotificacionesPlantilla np, boolean flush = false) {
        if (np == null) return

        NotificacionesConfiguracion.where { notificacionesPlantilla == np }.deleteAll()

        if (flush) { NotificacionesConfiguracion.withSession { it.flush() } }
    }

    static constraints = {
        notificacionesPlantilla validator: { NotificacionesPlantilla np, NotificacionesConfiguracion ur ->
            if (ur.notificacionesCron == null || ur.notificacionesCron.id == null) return
            boolean existing = false
            NotificacionesConfiguracion.withNewSession {
                existing = NotificacionesConfiguracion.exists(ur.notificacionesCron.id, np.id)
            }
            if (existing) {
                return 'notificacionesConfig.exists'
            }
        }
    }

    static mapping = {
        id composite: ['notificacionesCron', 'notificacionesPlantilla']
        version false
    }
}
