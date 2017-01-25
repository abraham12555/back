package la.kosmos.rest

import grails.converters.JSON
import grails.converters.XML
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = true)
class SolicitudRestController {

    def solicitudService
    def autenticacionRestService
    
    def index() {
        print "Entrando a obtenerUltimasSolicitudes: [prueba: " + params + ", request: " + request.JSON + "]"
        def respuesta
        def autorizado = autenticacionRestService.autenticar(request.getHeader('authorization'))
        if(autorizado){
            if(params.tipoDeConsulta){
                try{
                    respuesta = solicitudService.consultaSolicitudes(autorizado, 1, params.tipoDeConsulta, null, null, null)
                }catch(Exception e){
                    e.printStackTrace();
                    respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al obtener la lista de solicitudes."]
                }
            } else {
                respuesta = [codigoDeError: 405, mensajeError: "El tipo de consulta de no ha sido especificado."]
            }
        } else {
            respuesta = [codigoDeError: 403, mensajeError: "Usuario y contraseña incorrectos. Verifique sus datos."]
        }
        render respuesta as JSON
    }
       
    def list() {
        print "Entrando a obtenerSolicitudesPorFecha: [prueba: " + params + ", request: " + request.JSON + "]"
        def respuesta
        def autorizado = autenticacionRestService.autenticar(request.getHeader('authorization'))
        if(autorizado){
            if(params.tipoDeConsulta){
                if(params.fechaInicial && params.fechaFinal){
                    try{
                        respuesta = solicitudService.consultaSolicitudes(autorizado, 2, params.tipoDeConsulta, params.fechaInicial, params.fechaFinal, null)
                    }catch(Exception e){
                        e.printStackTrace();
                        respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al obtener la lista de solicitudes."]
                    }
                } else {
                    respuesta = [codigoDeError: 406, mensajeError: "El rango de fechas no se ha especificado correctamente."]
                }
            } else {
                respuesta = [codigoDeError: 405, mensajeError: "El tipo de consulta de no ha sido especificado."]
            }
        } else {
            respuesta = [codigoDeError: 403, mensajeError: "Usuario y contraseña incorrectos. Verifique sus datos."]
        }
        render respuesta as JSON
    }
    
    def show() {
        print "Entrando a obtenerSolicitudPorFolio: [prueba: " + params + ", request: " + request.JSON + "]"
        def respuesta
        def autorizado = autenticacionRestService.autenticar(request.getHeader('authorization'))
        if(autorizado){
            if(params.folioSolicitud){
                try{
                    respuesta = solicitudService.consultaSolicitudes(autorizado, 3, null, null, null, (params.folioSolicitud as int))
                }catch(Exception e){
                    e.printStackTrace();
                    respuesta = [codigoDeError: 500, mensajeError: "Ocurrio un error al obtener la lista de solicitudes."]
                }
            } else {
                respuesta = [codigoDeError: 407, mensajeError: "El folio de la solicitud no ha sido especificado."]
            }
        } else {
            respuesta = [codigoDeError: 403, mensajeError: "Usuario y contraseña incorrectos. Verifique sus datos."]
        }
        render respuesta as JSON
    }
}
