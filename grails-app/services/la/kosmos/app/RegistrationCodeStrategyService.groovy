package la.kosmos.app

import grails.plugin.springsecurity.ui.strategy.RegistrationCodeStrategy
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import grails.plugin.springsecurity.ui.ResetPasswordCommand
import grails.plugin.springsecurity.ui.SpringSecurityUiService
import la.kosmos.app.UserService


class RegistrationCodeStrategyService implements RegistrationCodeStrategy {

    SpringSecurityUiService springSecurityUiService
    UserService userService

    void updateRegistrationCode(Map properties, RegistrationCode registrationCode) {
        springSecurityUiService.updateRegistrationCode properties, registrationCode
    }

    void deleteRegistrationCode(RegistrationCode registrationCode) {
        springSecurityUiService.deleteRegistrationCode registrationCode
    }

    RegistrationCode register(user, String password, salt) {
        springSecurityUiService.register user, password, salt
    }

    def createUser(RegisterCommand command) {
        springSecurityUiService.createUser command
    }

    def finishRegistration(RegistrationCode registrationCode) {
        springSecurityUiService.finishRegistration registrationCode
    }

    RegistrationCode sendForgotPasswordMail(String username, String emailAddress, Closure emailBodyGenerator) {
        userService.sendForgotPasswordMail username, emailAddress, emailBodyGenerator
    }

    def resetPassword(ResetPasswordCommand command, RegistrationCode registrationCode) {
        springSecurityUiService.resetPassword command, registrationCode
    }
}
