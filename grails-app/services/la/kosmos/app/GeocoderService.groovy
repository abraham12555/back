package la.kosmos.app

import grails.transaction.Transactional
import groovy.json.JsonSlurper

@Transactional
class GeocoderService {

    def obtenerCoordenadas(def coordenadas, def direccion) {
        if(direccion){
            String base = 'https://maps.googleapis.com/maps/api/geocode/json?'
            String encoded = URLEncoder.encode(direccion, 'UTF-8')
            String qs = "address=$encoded"
            println "Direccion: " + direccion
            def respuesta = new JsonSlurper().parse(("$base$qs".toURL()))
            println "Respuesta obtenida: " + respuesta
            if(respuesta.status == "ZERO_RESULTS"){
                println "No encontro la direccion..."
            } else {
                def location = respuesta.results[0]?.geometry?.location
                coordenadas.lat = location?.lat?.toDouble()
                coordenadas.lng = location?.lng?.toDouble()
            }
            println "Coordenadas a regresar: " + coordenadas
        }
        coordenadas
    }
}
