package la.kosmos.app

import grails.transaction.Transactional
import org.springframework.transaction.annotation.*
import grails.gorm.DetachedCriteria

@Transactional(propagation=Propagation.REQUIRES_NEW)
class BitacoraMitekService {

    def registrarBitacoraMitek(def folio, def tuvoExito, def tuvoError, def descripcionError, def estatus) {
        this.eliminarBitacoraMitek(folio)
        def bitacoraMitek = new BitacoraMitek()
        bitacoraMitek.folio = folio
        bitacoraMitek.fechaConsulta = new Date ()
        bitacoraMitek.tuvoExito = tuvoExito
        bitacoraMitek.tuvoError = tuvoError
        bitacoraMitek.descripcionError = descripcionError
        bitacoraMitek.estatus = estatus
        bitacoraMitek.tiempoEnContestar = 0
        if(bitacoraMitek.save(flush:true)){
            println "Bitacora Mitek Save"
        }else{
            if (bitacoraMitek.hasErrors()) {
                bitacoraMitek.errors.allErrors.each {
                    println it
                }
            }
        }
    }
      def registrarBitacoraMitek(def folio, def tuvoExito, def tuvoError, def descripcionError, def estatus, def tiempoEnContestar) {
        this.eliminarBitacoraMitek(folio)
        def bitacoraMitek = new BitacoraMitek()
        bitacoraMitek.folio = folio
        bitacoraMitek.fechaConsulta = new Date ()
        bitacoraMitek.tuvoExito = tuvoExito
        bitacoraMitek.tuvoError = tuvoError
        bitacoraMitek.descripcionError = descripcionError
        bitacoraMitek.estatus = estatus
        bitacoraMitek.tiempoEnContestar = tiempoEnContestar
        if(bitacoraMitek.save(flush:true)){
            println "Bitacora Mitek Save"
        }else{
            if (bitacoraMitek.hasErrors()) {
                bitacoraMitek.errors.allErrors.each {
                    println it
                }
            }
        }
    }
    def eliminarBitacoraMitek(def folio){
        def criteria = new DetachedCriteria(BitacoraMitek).build {
            eq ('folio', folio)
        }
        criteria.deleteAll()
    }
}
