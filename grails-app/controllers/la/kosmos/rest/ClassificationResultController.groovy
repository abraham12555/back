package la.kosmos.rest

import groovy.json.JsonParser
import groovy.json.JsonSlurper
import java.text.SimpleDateFormat
import grails.converters.JSON
import org.apache.tools.bzip2.CBZip2InputStream.Data
import org.codehaus.groovy.grails.web.json.JSONElement
import org.codehaus.groovy.grails.web.json.JSONObject
import grails.transaction.*
import grails.rest.RestfulController
import org.springframework.http.HttpStatus.*
import org.springframework.http.HttpMethod.*
import org.apache.http.client.ClientProtocolException

@Transactional(readOnly = false)
class ClassificationResultController {
    
    def save(){        
        def mapaRespuesta = [:]
        
        if(request?.JSON?.DocumentType == "Passport"){
            def apellidos = separarApellidos(request?.JSON?.MachineReadableZone?.Name)
            mapaRespuesta = [givenname:request?.JSON?.MachineReadableZone?.GivenNames,
                surname:request?.JSON?.MachineReadableZone?.Name,
                name: (request?.JSON?.MachineReadableZone?.GivenNames + " " + request?.JSON?.MachineReadableZone?.Name),
                dateOfExpiry: request?.JSON?.MachineReadableZone?.DateOfExpiry,
                address: null,
                address1: null,
                address2: null,
                gender: request?.JSON?.MachineReadableZone?.Gender,
                clave: request?.JSON?.MachineReadableZone?.DocumentNumber,
                dateOfBirth: request?.JSON?.MachineReadableZone?.DateOfBirth,
                issuingYear: null,
                yearOfExpiry: null,
                districtCode: null,
                curp: null,
                parentNameFather: apellidos?.apellidoPaterno,
                parentNameMother: apellidos?.apellidoMaterno, 
                documentId: request?.JSON?.DocumentId,
                dossierId: request?.JSON?.DossierId,
                reference: request?.JSON?.ClientReference]
        } else {
            mapaRespuesta = [givenname:request?.JSON?.Data?.Givenname,
                surname:request?.JSON?.Data?.Surname,
                name:request?.JSON?.Data?.Name,
                dateOfExpiry:request?.JSON?.Data?.DateOfExpiry,
                address:request?.JSON?.Data?.Address,
                address1:request?.JSON?.Data?.Address1,
                address2:request?.JSON?.Data?.Address2,
                gender:request?.JSON?.Data?.Gender,
                clave:request?.JSON?.Data?.Clave, dateOfBirth:request?.JSON?.Data?.DateOfBirth,
                issuingYear:request?.JSON?.Data?.IssuingYear,
                yearOfExpiry:request?.JSON?.DynamicData?.YearOfExpiry,
                districtCode:request?.JSON?.DynamicData?.DistrictCode,
                curp:request?.JSON?.DynamicData?.Curp,
                parentNameFather:request?.JSON?.DynamicData?.ParentNameFather,
                parentNameMother:request?.JSON?.DynamicData?.ParentNameMother, 
                documentId:request?.JSON?.DocumentId, dossierId:request?.JSON?.DossierId,
                reference:request?.JSON?.ClientReference]
        }
        
        def cambios = new ClassificationResult()
        cambios.givenname = mapaRespuesta.givenname
        cambios.surname = mapaRespuesta.surname
        cambios.name = mapaRespuesta.name
        cambios.dateOfBirth = mapaRespuesta.dateOfBirth
        cambios.dateOfExpiry = mapaRespuesta.dateOfExpiry
        cambios.address = mapaRespuesta.address
        cambios.address1 = mapaRespuesta.address1
        cambios.address2 = mapaRespuesta.address2
        cambios.gender = mapaRespuesta.gender
        cambios.clave = mapaRespuesta.clave
        cambios.issuingYear = mapaRespuesta.issuingYear
        cambios.yearOfExpiry = mapaRespuesta.yearOfExpiry
        cambios.districtCode = mapaRespuesta.districtCode
        cambios.curp = mapaRespuesta.curp
        cambios.parentNameFather = mapaRespuesta.parentNameFather
        cambios.parentNameMother = mapaRespuesta.parentNameMother
        cambios.reference = mapaRespuesta.reference
        cambios.documentId = mapaRespuesta.documentId
        cambios.dossierId = mapaRespuesta.dossierId
        cambios.respuestaOriginal = request?.JSON
        if(cambios.save(flush:true)){
            if(request?.JSON?.Data?.PassPhoto){
                def imagenRecuperada = new ImagenRecuperada()
                imagenRecuperada.classificationResult = cambios
                imagenRecuperada.documentoBase64 = request?.JSON?.Data?.PassPhoto
                imagenRecuperada.tipoDeImagen = "ROSTRO"
                imagenRecuperada.save(flush:true)
            }
            if(request?.JSON?.ImageData){
                request?.JSON?.ImageData?.each {
                    def imagenRecuperada = new ImagenRecuperada()
                    imagenRecuperada.classificationResult = cambios
                    imagenRecuperada.documentoBase64 = it
                    imagenRecuperada.tipoDeImagen = "DOCUMENTO"
                    imagenRecuperada.save(flush:true)
                }
            }
            if(request?.JSON?.AdditionalImages){
                println("Trae imagenes adicionales, la firma?")
            }
        }
        
        def respuesta = [statusCode:'200', success:'true']
        render respuesta as JSON
    }
    
    def separarApellidos(def apellidosJuntos){
        def resultado = []
        def apellidos = [:]
        def apellido = ""
        def tokens = apellidosJuntos?.tokenize()
        def longitud
        def particulas = ["DE", "LA", "DEL", "LOS", "LAS", "SAN", "Y", "-"]
        tokens?.each {
            if(particulas.contains(it)){
                apellido += (it + " ")
            } else {
                apellido += it
                resultado << apellido
                apellido = ""
            }
        }
        def x = 0
        apellidos.apellidoPaterno = ""
        apellidos.apellidoMaterno = ""
        resultado.each {
            if(x == 0){
                apellidos.apellidoPaterno = it
            } else if (x >= 1){
                apellidos.apellidoMaterno += it
                if((x+1) < resultado.size()){
                    apellidos.apellidoMaterno += " "
                }
            }
            x++
        }
        println apellidos
        apellidos
    }
}
