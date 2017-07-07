package la.kosmos.app
import grails.converters.JSON

class ConfiguracionEntidadFinancieraController {

    def update() {
        println params
        def respuesta = [:]
               
        if(session.configuracion.entidadFinanciera && params.colorBordeSuperior 
            && params.colorEncabezado && params.colorFondo && params.colorGradienteSuperior
            && params.colorGradienteInferior && params.colorTitulos)
        {
            if(!params.textoDescripcionDefault ){
                def var = """\''"""
                params.textoDescripcionDefault = """\''"""
            }
            if(!params.textoMontoDefault){
                def var2 = "\''"
                params.textoMontoDefault = """\''"""
            }
           println params.textoDescripcionDefault.toString()
           println params.textoMontoDefault.toString()
            def configuracionEntidadFinanciera = ConfiguracionEntidadFinanciera.get(params.configuracionEntidadFinancieraId as long)
            configuracionEntidadFinanciera.colorBordeSuperior = params.colorBordeSuperior
            configuracionEntidadFinanciera.colorEncabezado = params.colorEncabezado
            configuracionEntidadFinanciera.colorFondo = params.colorFondo
            configuracionEntidadFinanciera.colorGradienteSuperior = params.colorGradienteSuperior
            configuracionEntidadFinanciera.colorGradienteInferior = params.colorGradienteInferior
            configuracionEntidadFinanciera.colorTitulos = params.colorTitulos
            configuracionEntidadFinanciera.colorTitulos = params.colorTitulos
            configuracionEntidadFinanciera.razonSocial = params.razonSocial
            configuracionEntidadFinanciera.nombreComercial = params.nombreComercial
            configuracionEntidadFinanciera.textoProductoDefault = params.textoProductoDefault
            configuracionEntidadFinanciera.tituloCotizador = params.tituloCotizador
            configuracionEntidadFinanciera.htmlTitle = params.htmlTitle
            configuracionEntidadFinanciera.mensajeConfirmacionCelular = params.mensajeConfirmacionCelular
            configuracionEntidadFinanciera.mensajeEnvioShortUrl = params.mensajeEnvioShortUrl
            configuracionEntidadFinanciera.textoDescripcionDefault = params.textoDescripcionDefault.toString()
            configuracionEntidadFinanciera.textoMontoDefault = params.textoMontoDefault.toString()
            configuracionEntidadFinanciera.slogan = params.slogan
            configuracionEntidadFinanciera.terminosCondiciones = params.terminosCondiciones
            if(configuracionEntidadFinanciera.save(flush:true)){
                respuesta.exito = true
                respuesta.mensaje = "La Configuración fue guardada correctamente."
                respuesta.entidad = configuracionEntidadFinanciera
            } else {
                if (configuracionEntidadFinanciera.hasErrors()) {
                    configuracionEntidadFinanciera.errors.allErrors.each {
                        println it
                    }
                }
            }
        } else {
            respuesta.error = true
            respuesta.mensaje = "Favor de Verificar, todos los campos son requeridos."
        }
        render respuesta as JSON
    }
    
}
