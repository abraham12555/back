package la.kosmos.rest

import org.springframework.security.core.userdetails.UserDetails
import grails.converters.JSON
import la.kosmos.app.Usuario

class LoginRestController {
    def userService
    def index() {
        println params
        println params.idDispositivo
        def respuesta 
        try{
            if(params.username && params.password && params.idDispositivo){
                 respuesta = userService.loginRest(params)
            }else if(params.idDispositivo){
                 respuesta = userService.checarDispositivo(params.idDispositivo)
            }
        }
        catch(Exception e){
            e.printStackTrace();
            respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al intentar hacer login."]
        }
        render respuesta as JSON
    }
    def updatePassword(){
        
    }
    def recuperarPassword(){
        println params
        def respuesta = [:]
        try{
            def estatus  = userService.validEmail(params)
            if(estatus){
                respuesta  = userService.forgotPassword(params)
            }else{
              respuesta = [error: Boolean.TRUE ,mensajeError: "La dirección de correo electrónico no ha sido registrada."]
            }
        }
        catch(Exception e){
            e.printStackTrace();
            respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al intentar hacer recuperar el password."]
        }
        render respuesta as JSON
    }
    def verificarCodigo (){
       def respuesta = [:]
        try{
            respuesta  = userService.verificarCodigo(params)
        }
        catch(Exception e){
            e.printStackTrace();
            respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al verificar el código."]
        }
        render respuesta as JSON
    }
    
    def passwordRecovery (){
        def respuesta = [:]
        try{
            //ver si cachar el usuario nuevamente o el de la session
            respuesta  = userService.passwordRecoveryRest(params.username,params.password)
        }catch(Exception e){
            e.printStackTrace();
            respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al restaurar el password."]
        }
        render respuesta as JSON
    }
    

}
