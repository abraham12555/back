package la.kosmos.app

import grails.transaction.Transactional
import java.util.Calendar
import org.apache.commons.logging.LogFactory
import org.springframework.transaction.annotation.Propagation

@Transactional
class UserSessionService {

    private static final log = LogFactory.getLog(this)

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    def saveUserSession(Usuario usuario, String clientIP){
        Usuario user = Usuario.findByUsername(usuario.username)
        Date fecha = Calendar.getInstance().getTime()

        SesionUsuario userSession = new SesionUsuario(user, fecha)
        if(!userSession.save(insert: true)){
            throw new RuntimeException("Error al insertar los datos de la sesion del usuario")
        }

        BitacoraSesionesUsuario bitacora = new BitacoraSesionesUsuario(user, usuario.entidadFinanciera, clientIP, fecha)
        if(!bitacora.save(insert: true)){
            throw new RuntimeException("Error al registrar el historial de sesion del usuario")
        }
    }

    def deleteUserSession(Usuario usuario){
        Usuario u = Usuario.get(usuario.id)
        if(u != null) {
            u.sesionUsuario.delete()
            u.sesionUsuario = null
        }
    }

    def deleteAllUserSessions(){
        SesionUsuario.executeUpdate("DELETE FROM SesionUsuario")
    }
}
