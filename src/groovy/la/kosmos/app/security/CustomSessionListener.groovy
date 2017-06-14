/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.security

import grails.util.Holders
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener
import la.kosmos.app.UserSessionService
import la.kosmos.app.Usuario
import la.kosmos.app.vo.Constants
import org.apache.commons.logging.LogFactory
import org.codehaus.groovy.grails.commons.GrailsApplication

/**
 *
 * @author elizabeth
 */

class CustomSessionListener implements HttpSessionListener {

    private static final log = LogFactory.getLog(this)

    @Override
    void sessionCreated(HttpSessionEvent httpSessionEvent) {
    }

    @Override
    void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        UserSessionService userSessionService = (UserSessionService)Holders.getGrailsApplication().getMainContext().getBean('userSessionService')

        def user = (Usuario) httpSessionEvent.getSession().getAttribute(Constants.CURRENT_USER);
        if (user != null) {
            userSessionService.deleteUserSession(user)
            httpSessionEvent.getSession().removeAttribute(Constants.CURRENT_USER);
            httpSessionEvent.getSession().invalidate();
        }
    }
}