package la.kosmos.app

import grails.gorm.DetachedCriteria
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.SpringSecurityUiService
import grails.plugin.springsecurity.ui.strategy.MailStrategy
import grails.transaction.Transactional
import java.util.Calendar
import java.util.HashSet
import la.kosmos.app.bo.Pager
import la.kosmos.app.bo.User
import la.kosmos.app.exception.BusinessException
import la.kosmos.app.exception.MultipleSelectionException
import la.kosmos.app.vo.Constants
import org.hibernate.transform.Transformers
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication
import org.springframework.transaction.annotation.Propagation

@Transactional
class UserService {

    def userDetailsService
    def preAuthenticationChecks
    def postAuthenticationChecks
    def passwordEncoder
    def grailsApplication
    def springSecurityUiService
    def uiMailStrategy
    def uiRegistrationCodeStrategy

    def changePassword(String username, String password){
        if(username == null){
            throw new Exception("Operación inválida. Los datos del usuario han expirado")
        }

        Usuario user = Usuario.findByUsername(username)

        if(!user.passwordExpired){
            throw new BusinessException("Operación inválida. La contraseña ya ha sido cambiada")
        }

        return updatePassword(user, password)
    }

    private Authentication updatePassword(Usuario user, String password){
        //Validating new password
        checkPasswordRecord(user, password)

        //Saving current password
        UsuarioPasswords userPassword = new UsuarioPasswords(user, Calendar.getInstance().getTime());

        //Updating password
        user.password = password
        user.passwordExpired = Boolean.FALSE
        user.fechaPassword = Calendar.getInstance().getTime()
        user.addToUserPasswords(userPassword);

        if(!user.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
            throw new BusinessException("Ocurrió un error. Inténtalo más tarde")
        }

        //Adding UserLoginDetailsService functionality
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.username)

        //Adding PreAuthenticationChecks functionality
        preAuthenticationChecks.check(userDetails)

        try {
            //Adding PostAuthenticationChecks functionality
            postAuthenticationChecks.check(userDetails)

            SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, password == null ? userDetails.getPassword() : password, userDetails.getAuthorities()));

            return SecurityContextHolder.context.authentication
        }   catch(MultipleSelectionException e){
            return null
        }
    }

    private void checkPasswordRecord(Usuario user, String password){
        //Validating new password
        if(passwordEncoder.isPasswordValid(user.password, password, null)){
            throw new BusinessException("Operación inválida. La contraseña ya ha sido usada con anterioridad")
        } else {
            if(!user.userPasswords?.empty){
                user.userPasswords.each {
                    if(passwordEncoder.isPasswordValid(it.password, password, null)){
                        throw new BusinessException("Operación inválida. La contraseña ya ha sido usada con anterioridad")
                    }
                }
            }
        }
    }

    def passwordExpiration() {
        def criteria = ConfiguracionEntidadFinanciera.createCriteria()
        def list = criteria.list {
            createAlias('entidadFinanciera', 'ef')
            eq ('ef.activa', Boolean.TRUE)
            isNotNull("expirarPasswords")

            projections {
                property('entidadFinanciera', 'entidadFinanciera')
                property('expirarPasswords', 'expirarPasswords')
            }

            resultTransformer(Transformers.aliasToBean(ConfiguracionEntidadFinanciera.class))
        }

        list.each {
            def entidad = it.entidadFinanciera
            def days = (it.expirarPasswords) * -1

            Calendar calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, days)
            calendar.set(Calendar.HOUR_OF_DAY, 23)
            calendar.set(Calendar.MINUTE, 59)
            calendar.set(Calendar.SECOND, 59)

            Date fecha = calendar.getTime()
            criteria = new DetachedCriteria(Usuario).build {
                eq ('entidadFinanciera', entidad)
                eq ('passwordExpired', Boolean.FALSE)
                lt ('fechaPassword', fecha)
            }
            criteria.updateAll(passwordExpired:Boolean.TRUE)
        }
    }

    def getUsers(EntidadFinanciera entidadFinanciera, Pager pager){
        pager.rowsPerPage = Constants.TOTAL_ROWS
        def userList = []

        def criteria = Usuario.createCriteria()
        def results = criteria.list (max: pager.rowsPerPage, offset: pager.firstRow) {
            eq ('entidadFinanciera', entidadFinanciera)
            order("username", "asc")
        }

        pager.totalRows = results.totalCount

        results.each {
            User u = new User()
            u.id = it.id
            u.username = it.username
            u.fullName = it.toString()
            u.email = it.email
            u.authorities = it.authorities
            userList << u
        }

        return userList
    }

    def getUserDetails(id){
        Usuario user = Usuario.get(id)
        return new User(user)
    }

    def validEmail(params){
        Usuario user = Usuario.findByEmail(params.email)
        return (user != null)
    }

    def validatePasswordRecoveryRequest(String token){
        def response = [:]

        def registrationCode = token ? RegistrationCode.findByToken(token) : null
        if (!registrationCode) {
            response.error = Boolean.TRUE
            response.message = "La solicitud de restablecimiento de contraseña es inválida"
            return response
        }

        Calendar requestDate = Calendar.getInstance();
        requestDate.setTime(registrationCode.dateCreated);
        requestDate.add(Calendar.MINUTE, grailsApplication.config.password.recovery);

        Calendar now = new GregorianCalendar();
        if (requestDate.compareTo(now) < 0) {
            deleteRegistrationCode(token)

            response.error = Boolean.TRUE
            response.message = "Ha excedido el tiempo permitido para hacer el cambio de contraseña"
            return response;
        }

        response.registrationCode = registrationCode
        return response
    }

    def passwordRecovery(String username, String password){
        Usuario user = Usuario.findByUsername(username)

        if(user == null){
            throw new BusinessException("Error. Los datos del usuario son inválidos")
        }

        return updatePassword(user, password)
    }

    def deleteRegistrationCode(String token){
        def registrationCode = RegistrationCode.findByToken(token)
        if (registrationCode != null){
            uiRegistrationCodeStrategy.deleteRegistrationCode(registrationCode)
        }
    }

    def validUsername(User usuario){
        Usuario user = Usuario.findByUsername(usuario.username)
        if(user == null) {
            return Boolean.TRUE
        } else {
            def id = (usuario.id).toBigInteger()
            if (BigInteger.valueOf(user.id).compareTo(id) == 0) {
                return Boolean.TRUE
            } else {
                return Boolean.FALSE
            }
        }
    }

    def validEmail(User usuario){
        Usuario user = Usuario.findByEmail(usuario.email)
        if(user == null) {
            return Boolean.TRUE
        } else {
            def id = (usuario.id).toBigInteger()
            if (BigInteger.valueOf(user.id).compareTo(id) == 0) {
                return Boolean.TRUE
            } else {
                return Boolean.FALSE
            }
        }
    }

    def userAuthentication(Usuario usuario, params){
        return (passwordEncoder.isPasswordValid(usuario.password, params.password, null))
    }

    def passwordRecoveryMessage() {
        def builder = new StringBuilder();
        builder.append("Estimado usuario").append(" ").append("\$user.username").append("<br/>").append("<br/>");
        builder.append("Se ha registrado una solicitud de \"Restablecimiento de contraseña\" de su cuenta de usuario. ");
        builder.append("Para continuar con el proceso de click").append(" ")
        builder.append("<a href=\"\$url\">aquí</a> y proporcione una nueva contraseña de acceso.").append("<br/>").append("<br/>");
        builder.append("Por razones de seguridad tiene que proporcionar una nueva contraseña en los próximos 30 minutos.").append("<br/>");

        return builder.toString();
    }

    @Transactional(propagation = Propagation.MANDATORY)
    def addUserInformationLog(Usuario usuario, Usuario targetUser, BigInteger movimiento, String descripcion){
        BitacoraAdministracionUsuarios bitacora = new BitacoraAdministracionUsuarios()
        bitacora.usuario = usuario
        bitacora.fechaHora = Calendar.getInstance().getTime()
        bitacora.targetUser = targetUser
        bitacora.bitacoraMovimientos = new BitacoraMovimientos(movimiento, null)
        bitacora.descripcion = descripcion

        bitacora.save(insert:Boolean.TRUE)
    }

    def getEntidadesFinancieras(){
        def criteria = EntidadFinanciera.createCriteria()
        def list = criteria.list {
            ne ('id', Constants.ENTIDAD_FINANCIERA_ROOT)
            eq ('activa', Boolean.TRUE)
        }
        return list
    }

    def addWorkspace (String username, params){
        String token = params.t

        if(username == null && token == null && token == "") {
            throw new Exception("Operación inválida. Los datos del usuario han expirado")
        } else if(token != null && token != "") {
            def registrationCode = RegistrationCode.findByToken(token)
            if (!registrationCode) {
                throw new Exception("Operación no permitida. Los datos de la operación han expirado")
            }

            username = registrationCode.username
            this.deleteRegistrationCode(token)
        }

        if(username == null) {
            throw new Exception("Operación no permitida. Los datos del usuario son inválidos")
        }

        Usuario user = Usuario.findByUsername(username)

        if(user == null){
            throw new BusinessException("Error. Los datos del usuario son inválidos")
        }

        def idEntidadFinanciera = (params.id).toLong()
        EntidadFinanciera entidadFinanciera = EntidadFinanciera.get(idEntidadFinanciera)

        if(entidadFinanciera == null){
            throw new BusinessException("Error. Los datos de la entidad financiera son inválidos")
        }

        //Adding UserLoginDetailsService functionality
        UserDetails userDetails = userDetailsService.loadUserByUsername(user, entidadFinanciera)

        //Adding PreAuthenticationChecks functionality
        preAuthenticationChecks.check(userDetails)
        //Adding PostAuthenticationChecks functionality
        postAuthenticationChecks.check(userDetails)

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities()));

        return SecurityContextHolder.context.authentication
    }

    def deleteUserSessions(){
        def criteria = ConfiguracionEntidadFinanciera.createCriteria()
        def list = criteria.list {
            createAlias('entidadFinanciera', 'ef')
            eq ('ef.activa', Boolean.TRUE)
            isNotNull("registroSesiones")

            projections {
                property('entidadFinanciera', 'entidadFinanciera')
                property('registroSesiones', 'registroSesiones')
            }

            resultTransformer(Transformers.aliasToBean(ConfiguracionEntidadFinanciera.class))
        }

        list.each {
            def entidad = it.entidadFinanciera
            def days = (it.registroSesiones) * -1

            Calendar calendar = Calendar.getInstance()
            calendar.add(Calendar.DAY_OF_MONTH, days)
            calendar.set(Calendar.HOUR_OF_DAY, 0)
            calendar.set(Calendar.MINUTE, 0)
            calendar.set(Calendar.SECOND, 0)
            calendar.set(Calendar.MILLISECOND, 0)

            Date fecha = calendar.getTime()
            criteria = new DetachedCriteria(BitacoraSesionesUsuario).build {
                eq ('entidadFinanciera', entidad)
                lt ('fecha', fecha)
            }
            criteria.deleteAll()
        }
    }

    def validNoEmpleado(User usuario, EntidadFinanciera entidadFinanciera){
        Usuario user = Usuario.where{numeroDeEmpleado == usuario.noEmpleado && entidadFinanciera == entidadFinanciera}.get()
        if(user == null) {
            return Boolean.TRUE
        } else {
            def id = (usuario.id).toBigInteger()
            if (BigInteger.valueOf(user.id).compareTo(id) == 0) {
                return Boolean.TRUE
            } else {
                return Boolean.FALSE
            }
        }
    }

    RegistrationCode sendForgotPasswordMail(String username, String emailAddress, Closure emailBodyGenerator) {

        RegistrationCode registrationCode = springSecurityUiService.save(username: username, RegistrationCode, 'sendForgotPasswordMail', transactionStatus)
        if (!registrationCode.hasErrors()) {
            String body = emailBodyGenerator(registrationCode.token)

            uiMailStrategy.sendForgotPasswordMail(to: emailAddress, from: springSecurityUiService.forgotPasswordEmailFrom,
                subject: springSecurityUiService.forgotPasswordEmailSubject, html: body)
        }

        registrationCode
    }

    def saveProfile(Usuario usuario, params){
        def respuesta = [:]

        Usuario u = Usuario.get(usuario.id)
        u.username = params.username
        u.nombre = params.nombre
        u.apellidoPaterno = params.apellidoPaterno
        u.apellidoMaterno = params.apellidoMaterno
        u.email = params.email

        if(!u.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = "Ocurrió un error al actualizar la información. Intente nuevamente más tarde."
            return respuesta
        }

        return respuesta
    }

    def updateProfilePassword(Usuario usuario, params){
        def respuesta = [:]
        String password = params.password

        Usuario user = Usuario.get(usuario.id)

        try {
            checkPasswordRecord(user, password)
        } catch (BusinessException ex){
            respuesta.error = Boolean.TRUE
            respuesta.mensaje = ex.getMessage()
            return respuesta
        }

        //Saving current password
        UsuarioPasswords userPassword = new UsuarioPasswords(user, Calendar.getInstance().getTime());

        //Updating password
        user.password = password
        user.fechaPassword = Calendar.getInstance().getTime()
        user.addToUserPasswords(userPassword);

        if(!user.save(validate: Boolean.FALSE, flush: Boolean.TRUE, failOnError: Boolean.TRUE)) {
            throw new BusinessException("Ocurrió un error. Inténtalo más tarde")
        }
    }
}
