package la.kosmos.app
import grails.converters.JSON
import groovy.json.*
import org.apache.commons.codec.binary.Base64
import java.nio.file.Files;
import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder

class RubroDeAplicacionDeCreditoController {
    def springSecurityService

    def obtenerDetalleRubroDeAplicacionDeCredito(RubroDeAplicacionDeCredito rubroDeAplicacionDeCredito){
        def faltan = []
        def faltanVistas = []
        def rubroDeAplicacionTipoDeDocumento = RubroDeAplicacionTipoDeDocumento.findAllWhere(rubro:rubroDeAplicacionDeCredito)
        def rubroDeAplicacionPasoCotizador = RubroDeAplicacionPasoCotizador.findAllWhere(rubro:rubroDeAplicacionDeCredito)
        def pasoCotizadorEntidadFinanciera = PasoCotizadorEntidadFinanciera.findAllWhere(entidadFinanciera:springSecurityService.currentUser.entidadFinanciera)
       
        def tipoDeDocumento = TipoDeDocumento.findAll()
        def nombres = tipoDeDocumento.nombre
        tipoDeDocumento.each{
           if (!rubroDeAplicacionTipoDeDocumento.tipoDeDocumento.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltan << respuesta
           }
         }
        pasoCotizadorEntidadFinanciera.each{
           if (!rubroDeAplicacionPasoCotizador.paso.id.contains(it.id)){
               def respuesta = [:]
                respuesta.tituloResumen = it.tituloResumen
                respuesta.id = it.id
                faltanVistas << respuesta
           }
         }
        def urlImagen = obtenerBase64Imagenes(rubroDeAplicacionDeCredito.urlImagen)
       render(template: "/dashboard/configuracion/rubroDeAplicacionDeCredito/detalleRubroDeAplicacionDeCredito", model: [rubroDeAplicacionDeCredito: rubroDeAplicacionDeCredito,rubroDeAplicacionTipoDeDocumento:rubroDeAplicacionTipoDeDocumento,rubroDeAplicacionPasoCotizador:rubroDeAplicacionPasoCotizador,documentosFaltantes:faltan,faltanVistas:faltanVistas,urlImagen:urlImagen])
    }
    
    def obtenerBase64Imagenes(def ruta){
        def respuesta = [:]
        if(ruta){
            byte[] array = Files.readAllBytes(new File(ruta).toPath());
            respuesta.base64 = Base64.encodeBase64String(array)
            respuesta.extension =  ruta.split("\\.")[-1]
        }
        return respuesta
    }
    
    
    def update(){
        println params
        def respuesta = [:]
        def rubroDeAplicacionDeCredito = RubroDeAplicacionDeCredito.get(params.idRubroDeAplicacionDeCredito)
        def rubroDeAplicacionTipoDeDocumento = RubroDeAplicacionTipoDeDocumento.findAllWhere(rubro:rubroDeAplicacionDeCredito)
        def rubroDeAplicacionPasoCotizador = RubroDeAplicacionPasoCotizador.findAllWhere(rubro:rubroDeAplicacionDeCredito)
         if (params.tooltip.toBoolean() == false){
             params.textoTooltip = null
         }
         rubroDeAplicacionDeCredito.nombre = params.nombreRubro
         rubroDeAplicacionDeCredito.posicion = params.posicion as long
         rubroDeAplicacionDeCredito.descripcion = params.descripcion
         rubroDeAplicacionDeCredito.claseIconoPaso = params.claseIconoPaso
         //rubroDeAplicacionDeCredito.urlImagen = params.urlImagen
         rubroDeAplicacionDeCredito.tooltip = params.tooltip.toBoolean()
         rubroDeAplicacionDeCredito.textoTooltip = params.textoTooltip.toString()
         rubroDeAplicacionDeCredito.activo = params.activo.toBoolean()
         
        if(rubroDeAplicacionDeCredito.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "El Rubro se ha Actualizado Correctamente"
        }else {
            respuesta.error = true
            respuesta.mensaje = "No se pudo Actualizar Intentar más tarde."
        }
        
        rubroDeAplicacionTipoDeDocumento.each{
            it.delete(flush:true)
        }
        rubroDeAplicacionPasoCotizador.each{
            it.delete(flush:true)
        }
        params.tipoDeDocumentoId.each{
            def tipoDeDocumento = TipoDeDocumento.get(it)
            def rubroDeAplicacionTipoDeDocumentoTemp = new RubroDeAplicacionTipoDeDocumento ()
            rubroDeAplicacionTipoDeDocumentoTemp.tipoDeDocumento = tipoDeDocumento
            rubroDeAplicacionTipoDeDocumentoTemp.rubro = rubroDeAplicacionDeCredito
            if (rubroDeAplicacionTipoDeDocumentoTemp.save(flush:true)){
            }
        }
        params.pasoId.each{
            def pasoCotizadorEntidadFinanciera = PasoCotizadorEntidadFinanciera.get(it)
            def rubroDeAplicacionPasoCotizadorTemp = new RubroDeAplicacionPasoCotizador ()
            rubroDeAplicacionPasoCotizadorTemp.paso = pasoCotizadorEntidadFinanciera
            rubroDeAplicacionPasoCotizadorTemp.rubro = rubroDeAplicacionDeCredito
            if (rubroDeAplicacionPasoCotizadorTemp.save(flush:true)){
            }
        }
        println respuesta 
        render respuesta as JSON

    }
    
    
    
    
    
    
}