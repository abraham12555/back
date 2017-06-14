/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.security

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import grails.plugin.springsecurity.userdetails.NoStackUsernameNotFoundException
import grails.transaction.Transactional
import la.kosmos.app.EntidadFinanciera
import la.kosmos.app.Rol
import la.kosmos.app.Usuario
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 *
 * @author elizabeth
 */
class UserLoginDetailsService implements GrailsUserDetailsService {

    /**
     * Some Spring Security classes (e.g. RoleHierarchyVoter) expect at least
     * one role, so we give a user with no granted roles this one which gets
     * past that restriction but doesn't grant anything.
     */
    static final List NO_ROLES = [new SimpleGrantedAuthority(SpringSecurityUtils.NO_ROLE)]

    UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
        return loadUserByUsername(username)
    }

    @Transactional(readOnly=true, noRollbackFor=[IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario user = Usuario.findByUsername(username)
        if (!user) {
            throw new NoStackUsernameNotFoundException()
        }

        def authorities = this.addAuthorities (user)

        Rol rol = Rol.get(Rol.ROLE_ADMIN)
        boolean multipleSelection = authorities.contains(new SimpleGrantedAuthority(rol.authority))

        return this.buildUserForAuthentication(user, authorities, multipleSelection)
    }

    @Transactional(readOnly=true, noRollbackFor=[IllegalArgumentException, UsernameNotFoundException])
    UserDetails loadUserByUsername(Usuario user, EntidadFinanciera entidadFinanciera) throws UsernameNotFoundException {
        user.entidadTemporal = entidadFinanciera

        def authorities = this.addAuthorities (user)
        return this.buildUserForAuthentication(user, authorities, Boolean.FALSE)
    }

    private Collection addAuthorities(Usuario user){
        //recovering roles
        def roles = user.authorities
        def authorities = roles.collect {
            new SimpleGrantedAuthority(it.authority)
        }

        return authorities ?: NO_ROLES
    }

    //Adding attributes to UserDetails
    private CustomUserDetails buildUserForAuthentication(Usuario user, Collection<GrantedAuthority> authorities, boolean multipleSelection) throws NullPointerException {
        return new CustomUserDetails(user.username, user.password, user.enabled, !user.accountExpired, !user.passwordExpired, !user.accountLocked, authorities, user.id, user.sesionUsuario, multipleSelection)
    }
}