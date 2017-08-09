// Place your Spring DSL code here

import grails.plugin.springsecurity.SpringSecurityUtils
import la.kosmos.app.MailStrategyService
import la.kosmos.app.mock.ConexionBCServiceMock
import la.kosmos.app.mock.EmailServiceMock
import la.kosmos.app.RegistrationCodeStrategyService
import la.kosmos.app.security.AuthenticationFailureListener
import la.kosmos.app.security.AuthenticationSuccessEventListener
import la.kosmos.app.security.UserLoginDetailsService
import la.kosmos.rest.SmsMockService
import la.kosmos.rest.SmsService

import la.kosmos.app.security.CustomLoginSuccessHandler
import la.kosmos.app.security.CustomPreAuthenticationChecks
import la.kosmos.app.security.CustomPostAuthenticationChecks
import la.kosmos.app.security.CustomPostAuthenticationChecks
import grails.util.Environment;

beans = {
    authenticationSuccessHandler(CustomLoginSuccessHandler) {
        /* Reusing the security configuration */
        def conf = SpringSecurityUtils.securityConfig
        /* Configuring the bean */
        requestCache = ref('requestCache')
        redirectStrategy = ref('redirectStrategy')
        defaultTargetUrl = conf.successHandler.defaultTargetUrl
        alwaysUseDefaultTargetUrl = conf.successHandler.alwaysUseDefault
        targetUrlParameter = conf.successHandler.targetUrlParameter
        ajaxSuccessUrl = conf.successHandler.ajaxSuccessUrl
        useReferer = conf.successHandler.useReferer
    }

    userDetailsService(UserLoginDetailsService)

    preAuthenticationChecks(CustomPreAuthenticationChecks)

    postAuthenticationChecks(CustomPostAuthenticationChecks)

    authenticationFailureListener(AuthenticationFailureListener) {
        loginAttemptCacheService = ref('loginAttemptCacheService')
    }

    authenticationSuccessEventListener(AuthenticationSuccessEventListener) {
        loginAttemptCacheService = ref('loginAttemptCacheService')
    }

    uiRegistrationCodeStrategy(RegistrationCodeStrategyService) {
        springSecurityUiService = ref('springSecurityUiService')
        userService = ref('userService')
    }

    uiMailStrategy(MailStrategyService){
        mailService = ref('mailService')
    }

    switch(Environment.current) {
    case Environment.PRODUCTION:
        smsService(SmsService) {
        }
        break

    case Environment.DEVELOPMENT:
        smsService(SmsMockService) {
        }
        emailService(EmailServiceMock)
        conexionBCService(ConexionBCServiceMock)
        break

    case Environment.TEST:
        smsService(SmsMockService) {
        }
        conexionBCService(ConexionBCServiceMock)
        break
    }
}
