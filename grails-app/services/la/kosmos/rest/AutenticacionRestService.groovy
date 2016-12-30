package la.kosmos.rest

import grails.transaction.Transactional
import la.kosmos.rest.AutenticacionRest

@Transactional
class AutenticacionRestService {

    def autenticar(def base64) {
        if(base64){
            def tokens = base64.tokenize()
            if(tokens.size() > 1){
                def usuario = AutenticacionRest.findWhere(base64Auth: tokens.getAt(1))
                if(usuario){
                    return usuario
                } else {
                    return false
                }
            } else {
                return false
            }
        } else {
            return false
        }
    }
}
