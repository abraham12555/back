// Place your Spring DSL code here

import grails.plugin.springsecurity.SpringSecurityUtils
import la.kosmos.app.security.AuthenticationFailureListener
import la.kosmos.app.security.AuthenticationSuccessEventListener
import la.kosmos.app.security.UserLoginDetailsService
import la.kosmos.app.security.CustomLoginSuccessHandler
import la.kosmos.app.security.CustomPreAuthenticationChecks
import la.kosmos.app.security.CustomPostAuthenticationChecks

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

}