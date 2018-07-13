/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.security

import la.kosmos.app.exception.ConcurrentRequestException
import la.kosmos.app.exception.MultipleSelectionException
import org.springframework.context.support.MessageSourceAccessor
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.core.SpringSecurityMessageSource
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsChecker

/**
 *
 * @author elizabeth
 */
class CustomPostAuthenticationChecks implements UserDetailsChecker {

    protected MessageSourceAccessor messages = SpringSecurityMessageSource.getAccessor();

    public void check(UserDetails user) {
        if(user.userSession != null) {
            throw new ConcurrentRequestException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.concurrentrequest", "User has already logged in"));
        }else if(!user.isCredentialsNonExpired()){
            throw new CredentialsExpiredException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.credentialsExpired", "User credentials have expired"));
        }else if (user.multipleSelection) {
            throw new MultipleSelectionException(messages.getMessage("AbstractUserDetailsAuthenticationProvider.multipleselection", "User has to choose an specific workspace"));
        }
    }
}
