package la.kosmos.app

import grails.converters.JSON

import org.springframework.security.access.annotation.Secured
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.web.WebAttributes

import grails.plugin.springsecurity.SpringSecurityUtils
import la.kosmos.app.exception.BusinessException
import la.kosmos.app.exception.ConcurrentRequestException
import la.kosmos.app.exception.MultipleSelectionException
import org.springframework.security.core.Authentication


@Secured('permitAll')
class LoginController extends grails.plugin.springsecurity.LoginController {

    def userService
    def authenticationSuccessHandler


    /**
     * Callback after a failed login. Redirects to the auth page with a warning message.
     */
    def authfail() {

        String msg = ''
        def exception = session[WebAttributes.AUTHENTICATION_EXCEPTION]
        boolean changePassword
        boolean multipleSelection

        if (exception) {
            if (exception instanceof AccountExpiredException) {
                msg = g.message(code: "springSecurity.errors.login.expired")
            }
            else if (exception instanceof CredentialsExpiredException) {
                changePassword = Boolean.TRUE
            }
            else if (exception instanceof DisabledException) {
                msg = g.message(code: "springSecurity.errors.login.disabled")
            }
            else if (exception instanceof LockedException) {
                msg = g.message(code: "springSecurity.errors.login.locked")
            }
            else if (exception instanceof ConcurrentRequestException) {
                msg = g.message(code: "springSecurity.errors.login.concurrentrequest")
            } else if (exception instanceof MultipleSelectionException){
                multipleSelection = Boolean.TRUE
            }
            else {
                msg = g.message(code: "springSecurity.errors.login.fail")
            }
        }

        if (changePassword){
            render(view: 'changePassword')
        } else if (multipleSelection){
            redirect action: 'selectWorkspace'
        } else if (springSecurityService.isAjax(request)) {
            render([error: msg] as JSON)
        } else {
            flash.message = msg
            redirect action: 'auth', params: params
        }
    }

    //Updating expired passwords
    def updateUserPassword(){
        String username = session[SpringSecurityUtils.SPRING_SECURITY_LAST_USERNAME_KEY]
        String password = params.password

        try {
            Authentication auth = userService.changePassword(username, password)

            if(auth != null) {
                //Adding CustomLoginSuccessHandler functionality
                authenticationSuccessHandler.onAuthenticationSuccess(request, response, auth)
            } else {
                //Rendering view to let users choose the workspace
                redirect action: 'selectWorkspace'
            }
        } catch (AccountExpiredException | CredentialsExpiredException | DisabledException | LockedException | ConcurrentRequestException | BusinessException e){
            render(view: 'changePassword', model: [message: e.getMessage()])
        } catch (Exception e){
            flash.message = e.getMessage()
            redirect action: 'auth'
        }
    }

    def selectWorkspace() {
        def entidadesFinancieras = userService.getEntidadesFinancieras()
        [entidadesList : entidadesFinancieras, t: params.t]
    }

    //Workspace configuration
    def customizeSelection () {
        String username = session[SpringSecurityUtils.SPRING_SECURITY_LAST_USERNAME_KEY]

        try {
            Authentication auth = userService.addWorkspace(username, params)

            //Adding CustomLoginSuccessHandler functionality
            authenticationSuccessHandler.onAuthenticationSuccess(request, response, auth)
        } catch (AccountExpiredException | CredentialsExpiredException | DisabledException | LockedException | ConcurrentRequestException | BusinessException e){
            render(view: 'selectWorkspace', model: [message: e.getMessage()])
        } catch (Exception e){
            log.error(e.getMessage())
            redirect action: 'auth'
        }
    }

    def keepSessionAlive(){
        def response = [:]
        response.confirm = Boolean.TRUE
        render response as JSON
    }
}
