/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package la.kosmos.app.bo

import la.kosmos.app.Usuario

/**
 *
 * @author elizabeth
 */
class User {
    def id
    def username
    def fullName
    def email
    def authorities
    def nombre
    def apellidoPaterno
    def apellidoMaterno
    def enabled
    def accountLocked
    def sucursal
    def noEmpleado
    
    User(){
        
    }
    
    User(Usuario usuario){
        this.id = usuario.id
        this.username = usuario.username
        this.email = usuario.email
        this.authorities = usuario.authorities
        this.nombre = usuario.nombre
        this.apellidoPaterno = usuario.apellidoPaterno
        this.apellidoMaterno = usuario.apellidoMaterno
        this.enabled = usuario.enabled
        this.accountLocked = usuario.accountLocked
        this.sucursal = usuario.sucursal
        this.noEmpleado = usuario.numeroDeEmpleado
    }
}

