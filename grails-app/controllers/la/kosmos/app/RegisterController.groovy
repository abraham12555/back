package la.kosmos.app

import grails.converters.JSON
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import la.kosmos.app.exception.BusinessException
import la.kosmos.app.exception.ConcurrentRequestException
import la.kosmos.app.exception.MultipleSelectionException
import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.Authentication


@Secured('permitAll')
class RegisterController extends grails.plugin.springsecurity.ui.RegisterController  {

    RegistrationCodeStrategy uiRegistrationCodeStrategy

    def userService
    def authenticationSuccessHandler

    def validEmail(){
        def response = [:]
        response.estatus = userService.validEmail(params)
        render response as JSON
    }

    def forgotPassword() {
        if (!request.post) {
            return
        }

        Usuario user = Usuario.findByEmail(params.email)
        if(user == null){
            flash.message = "Error. Los datos del usuario son invalidos"
            return
        }

        String email = user.email
        def response = [:]

        try {
            RegistrationCode registrationCode = uiRegistrationCodeStrategy.sendForgotPasswordMail(
                user.username, email) { String registrationCodeToken ->

                String url = generateLink('resetPassword', [t: registrationCodeToken])
                String body = userService.passwordRecoveryMessage()

                if (body.contains('$')) {
                    body = evaluate(body, [user: user, url: url])
                }

                body
            }

            response.message = "Se ha enviado un mensaje a su cuenta de correo electrónico con las instrucciones que debe seguir para continuar con el proceso de restablecimiento de contraseña."
        } catch (Exception e){
            log.error ("Ocurrio un error al enviar el correo electronico", e)
            response.error = Boolean.TRUE
            response.message = "Ha ocurrido un error al enviar el correo electrónico. Por favor inténtalo más tarde."
        }

        render response as JSON
    }

    def resetPassword() {
        String token = params.t
        def respuesta = userService.validatePasswordRecoveryRequest(token)
        if(respuesta != null && respuesta.error) {
            flash.message = respuesta.message
            redirect(controller: "login", action: "auth")
            return
        }

        if (!request.post) {
            return [token: token]
        }

        String username = respuesta.registrationCode.username
        String password = params.password

        try {
            Authentication auth = userService.passwordRecovery(username, password)

            if(auth != null){
                userService.deleteRegistrationCode(token)

                //Adding CustomLoginSuccessHandler functionality
                authenticationSuccessHandler.onAuthenticationSuccess(request, response, auth)
            } else {
                //Rendering view to let users choose the workspace
                redirect(controller: "login", action: "selectWorkspace", params:[t: token])
            }
        } catch (AccountExpiredException | CredentialsExpiredException | DisabledException | LockedException | ConcurrentRequestException | BusinessException e){
            [token: token, message: e.getMessage()]
        }
    }
}
