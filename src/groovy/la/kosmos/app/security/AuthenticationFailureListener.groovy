/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.security

import la.kosmos.app.LoginAttemptCacheService
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent

/**
 *
 * @author elizabeth
 */
class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    LoginAttemptCacheService loginAttemptCacheService

    @Override
    void onApplicationEvent(AuthenticationFailureBadCredentialsEvent e) {
        loginAttemptCacheService.failLogin(e.authentication.name)
    }
}