package la.kosmos.app

import grails.plugin.springsecurity.ui.strategy.MailStrategy


class MailStrategyService implements MailStrategy {

    def mailService

    def sendVerifyRegistrationMail(Map params) {
        sendMail params, 'sendVerifyRegistrationMail'
    }

    def sendForgotPasswordMail(Map params) {
        sendMail params, 'sendForgotPasswordMail'
    }

    protected sendMail(Map params, String methodName) {
        if (!mailService) {
            log.error "Cannot send mail: this implementation of MailStrategy depends " +
				"on the mail plugin's 'mailService' bean but it was not found; install the mail " +
				"plugin or register your own 'uiMailStrategy' bean to replace this one"

            throw new Exception("Plugin not configured")
        }

        try {
            mailService.sendMail {
                to      params.to
                from    params.from
                subject params.subject
                html    params.html
            }
        }
        catch (e) {
            throw e
        }
    }
}
