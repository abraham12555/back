package la.kosmos.app
import org.apache.commons.codec.binary.Base64
import java.nio.file.Files;
import grails.converters.JSON

import groovy.json.*
import java.net.URL
import java.text.SimpleDateFormat

import grails.plugin.springsecurity.authentication.encoding.BCryptPasswordEncoder


class EntidadFinancieraController {
    def springSecurityService
    def grailsApplication
    def save() {
        println params
        def respuesta = [:]
        if((params.nombreEntidadFinanciera) && (params.imagenDefault) && (params.rutaLogotipo)) {
            def entidadFinanciera = new EntidadFinanciera()
            entidadFinanciera.activa = true
            entidadFinanciera.fechaDeRegistro = new Date()
            entidadFinanciera.nombre = params.nombreEntidadFinanciera
            if(entidadFinanciera.save(flush:true)){
                def configuracionEntidadFinanciera = new ConfiguracionEntidadFinanciera()
                configuracionEntidadFinanciera.entidadFinanciera = entidadFinanciera
                configuracionEntidadFinanciera.colorBordeSuperior = "__ "
                configuracionEntidadFinanciera.colorEncabezado = "__ "
                configuracionEntidadFinanciera.colorFondo = "#FFF"
                configuracionEntidadFinanciera.colorGradienteInferior = "__ "
                configuracionEntidadFinanciera.colorGradienteSuperior = "__ "
                configuracionEntidadFinanciera.colorTitulos = "__ "
                configuracionEntidadFinanciera.ejecutarMotorEnPaso = 0
                configuracionEntidadFinanciera.enviarNotificacionesPorCorreo = true
                configuracionEntidadFinanciera.htmlTitle = "__"
                configuracionEntidadFinanciera.mensajeConfirmacionCelular = "__ "
                configuracionEntidadFinanciera.mensajeEnvioShortUrl = "__ "
                configuracionEntidadFinanciera.navegacionLibre = false
                configuracionEntidadFinanciera.nombreComercial = "__ "
                configuracionEntidadFinanciera.pasoLimiteNavegacionLibre = 4
                configuracionEntidadFinanciera.razonSocial = "__ "
                configuracionEntidadFinanciera.rutaCss = "http://localhost:8080/css/libertad.css"
                configuracionEntidadFinanciera.rutaLogotipo = "__ "
                configuracionEntidadFinanciera.slogan = "__ "
                configuracionEntidadFinanciera.subirDocumentosOpcional = true
                configuracionEntidadFinanciera.terminosCondiciones = "__ "
                configuracionEntidadFinanciera.textoDescripcionDefault = "__ "
                configuracionEntidadFinanciera.textoMontoDefault = "__ "
                configuracionEntidadFinanciera.textoProductoDefault = "__ "
                configuracionEntidadFinanciera.urlDominio = "__ "
                configuracionEntidadFinanciera.tituloCotizador = " Configura tu Skin" 
                configuracionEntidadFinanciera.wsdlMotorDeDecision = "__ "
                configuracionEntidadFinanciera.aplicacionVariable = true
                def uploadedFile = params.imagenDefault
                def fileLabel = ".${uploadedFile.originalFilename.split("\\.")[-1]}"
                InputStream inputStream = uploadedFile.inputStream
                println respuesta.archivo = inputStream
                println respuesta.nombreDelArchivo = uploadedFile.originalFilename
                println respuesta.extension = fileLabel.toLowerCase()
                def configuracionRutaLogotipoUrl
                def uploadedFile2 = params.rutaLogotipo
                def fileLabel2 = ".${uploadedFile2.originalFilename.split("\\.")[-1]}"
                InputStream inputStream2 = uploadedFile2.inputStream
                println respuesta.archivo2 = inputStream2
                println respuesta.nombreDelArchivo2 = uploadedFile2.originalFilename
                println respuesta.extension2 = fileLabel2.toLowerCase()
                entidadFinanciera.nombre  = entidadFinanciera.nombre .replaceAll("[^a-zA-Z0-9]+","")
                def ruta = "/var/uploads/kosmos/config/" +entidadFinanciera.nombre +"/imagenesCotizador"
                def ruta2  = grailsApplication.mainContext.getResource("/images/" + entidadFinanciera.nombre +"").file.toString()
                configuracionEntidadFinanciera.imagenDefault = ruta + "/" + respuesta.nombreDelArchivo
                configuracionEntidadFinanciera.rutaLogotipo =  entidadFinanciera.nombre +"/" + respuesta.nombreDelArchivo2
                configuracionRutaLogotipoUrl = ruta2 + "/" + respuesta.nombreDelArchivo2
                println respuesta.archivo
                println respuesta.archivo2
                if (configuracionEntidadFinanciera.save(flush:true)){
                    def subdir = new File(ruta)
                    def subdir2 = new File(ruta2)

                    if (!subdir.exists()){
                        subdir.mkdirs()
                    }
                    if (!subdir2.exists()){
                        subdir2.mkdirs()
                    }
                    File file = new File(configuracionEntidadFinanciera.imagenDefault)
                    if (file.exists() || file.createNewFile()) {
                        file.withOutputStream{fos->
                            fos << respuesta.archivo
                        }
                    }
                    File file2 = new File(configuracionRutaLogotipoUrl)
                    if (file2.exists() || file2.createNewFile()) {
                        file2.withOutputStream{fos->
                            fos << respuesta.archivo2
                        }
                    }
                            respuesta.exito = true
                            respuesta.mensaje = "La entidad financiera fue registrada correctamente."
                            respuesta.entidad = entidadFinanciera 
                }
                else {
                    if (configuracionEntidadFinanciera.hasErrors()) {
                        configuracionEntidadFinanciera.errors.allErrors.each {
                            println it
                        }
                    }
                }
              
            } else {
                if (entidadFinanciera.hasErrors()) {
                    entidadFinanciera.errors.allErrors.each {
                        println it
                    }
                }
            }
        } else {
            respuesta.error = true
            respuesta.mensaje = "No se recibio el nombre de la Entidad Financiera a registrar."
        }
        render respuesta as JSON
    }
    
    def edit() {
        
    }
    
    def update() {
        
    }
}
