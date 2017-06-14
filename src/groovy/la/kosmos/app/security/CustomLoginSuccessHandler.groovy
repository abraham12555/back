/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.security

import grails.plugin.springsecurity.web.authentication.AjaxAwareAuthenticationSuccessHandler
import grails.transaction.Transactional
import grails.util.Holders
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession
import la.kosmos.app.UserSessionService
import la.kosmos.app.Usuario
import la.kosmos.app.vo.Constants
import org.apache.commons.logging.LogFactory
import org.springframework.security.core.Authentication

/**
 *
 * @author elizabeth
 */
class CustomLoginSuccessHandler extends AjaxAwareAuthenticationSuccessHandler {

    private static final log = LogFactory.getLog(this)

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response) {
        return super.determineTargetUrl(request, response)
    }

    @Override
    @Transactional(readOnly=Boolean.TRUE)
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response, final Authentication authentication) throws ServletException, IOException {
        try {
            handle(request,response,authentication)
            super.clearAuthenticationAttributes(request)

            Usuario user = Usuario.findByUsername(authentication.getName())
            String clientIP = this.getClientIp(request)

            //Add temporary data
            if(user.entidadTemporal) {
                user.entidadFinanciera = user.entidadTemporal
            }

            //Saving user information
            UserSessionService userSessionService = (UserSessionService)Holders.getGrailsApplication().getMainContext().getBean('userSessionService')
            userSessionService.saveUserSession(user, clientIP)

            //Store user information to control active sessions
            request.getSession().setAttribute(Constants.CURRENT_USER, user);

        } finally {
            // always remove the saved request
            requestCache.removeRequest(request, response)
        }
    }

    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        String targetUrl = determineTargetUrl(request, response)

        if (response.isCommitted()) {
            log.debug("Response has already been committed. Unable to redirect to " + targetUrl)
            return
        }

        redirectStrategy.sendRedirect(request, response, targetUrl)
    }

    private String getClientIp(HttpServletRequest request) {
        String remoteAddress = ""

        if (request != null) {
            remoteAddress = request.getHeader("X-FORWARDED-FOR")
            if (remoteAddress == null || "".equals(remoteAddress)) {
                remoteAddress = request.getRemoteAddr()
            }
        }

        return remoteAddress
    }
}