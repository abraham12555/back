package la.kosmos.app

import grails.transaction.Transactional
import org.springframework.transaction.annotation.*

@Transactional(propagation=Propagation.REQUIRES_NEW)
class BitacoraOfertasService {

    def registrarBitacora(def solicitud, def motivo, def error) {

        BitacoraOfertas bitacoraOfertas = new BitacoraOfertas()
        bitacoraOfertas.solicitud = solicitud
        bitacoraOfertas.motivo = motivo
        bitacoraOfertas.error = error
        bitacoraOfertas.fecha =  new Date()
        bitacoraOfertas.save(flush: true)
    }
}
