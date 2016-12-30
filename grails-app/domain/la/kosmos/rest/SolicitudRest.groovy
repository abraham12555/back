package la.kosmos.rest

import grails.rest.Resource

@Resource(uri='/rest', formats=['json','xml'])
class SolicitudRest {
    
    static mapWith = "none"
    
    HashMap solicitud

    static constraints = {
        solicitud (nullable: true)
    }
}
