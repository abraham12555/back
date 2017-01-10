package la.kosmos.rest

import grails.converters.JSON
import org.apache.tools.bzip2.CBZip2InputStream.Data
import grails.transaction.*
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

@Transactional(readOnly = false)
class DossierSummaryController {
    
    def save(){
        print "prueba: " + params + " - request: " + request?.JSON
         def respuestaMitek = [status:request?.JSON?.Status, 
            value:request?.JSON.DocumentValidations?.Value, 
            source:request?.JSON.DocumentValidations?.Source,
            documentId:request?.JSON?.DocumentValidations?.DocumentId, 
            nameOfCallback:request?.JSON?.NameOfCallback, 
            dossierId:request?.JSON?.DossierId]
        
        def registros = new DossierSummary()
        registros.status = respuestaMitek.status
        registros.value = respuestaMitek.value
        registros.source = respuestaMitek.source
        registros.documentId = respuestaMitek.documentId
        registros.nameOfCallback = respuestaMitek.nameOfCallback
        registros.dossierId = respuestaMitek.dossierId
        registros.save(flush: true)
        
        println "@@@@@@@@@@@Registro del campo Value: " + registros.value
        println "@@@@@@@@@@@Registro del campo nameOfCallback: " + registros.nameOfCallback
        println "@@@@@@@@@@@Registro del campo dossierId: " + registros.dossierId 
        
                
        def respuesta = [statusCode:'200', success:'true']
        render respuesta as JSON
    }
}
