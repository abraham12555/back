/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.security

import grails.plugin.springsecurity.userdetails.GrailsUser
import la.kosmos.app.EntidadFinanciera
import la.kosmos.app.SesionUsuario
import org.springframework.security.core.GrantedAuthority

/**
 *
 * @author elizabeth
 */
class CustomUserDetails extends GrailsUser {

    final SesionUsuario userSession
    final boolean multipleSelection

    CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities, long id, SesionUsuario userSession, boolean multipleSelection) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, id)

        this.userSession = userSession
        this.multipleSelection = multipleSelection
    }
}
