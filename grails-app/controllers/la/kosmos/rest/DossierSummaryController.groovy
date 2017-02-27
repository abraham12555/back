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
            clientReference: request?.JSON?.ClientReference,
            dossierId:request?.JSON?.DossierId]
        
        def registros = new DossierSummary()
        registros.status = respuestaMitek.status
        registros.value = respuestaMitek.value
        registros.source = respuestaMitek.source
        registros.documentId = respuestaMitek.documentId
        registros.nameOfCallback = respuestaMitek.nameOfCallback
        registros.reference = respuestaMitek.clientReference
        registros.dossierId = respuestaMitek.dossierId
        registros.respuestaOriginal = request?.JSON
        if(registros.save(flush: true)){
            println ("[DossierSumary] Se ha registrado correctamente")
            if(registros.status == "Rejected"){
                request?.JSON?.RejectReasons?.each { key, value ->
                    println ("[DossierSumary] Revisando motivo de rechazo no. " + key)
                    def dossierRejectIssue = new DossierRejectIssue()
                    dossierRejectIssue.dossierSummary = registros
                    dossierRejectIssue.dossierRejectReason = DossierRejectReason.findByEtiqueta(value)
                    dossierRejectIssue.save(flush:true)
                }
            } else if(registros.status == "CompletedWithWarnings"){
                println ("[DossierSumary] Trae warnings")
                request?.JSON?.Warnings?.each {
                    println ("[DossierSumary] Revisando warnings. " + it)
                    def dossierWarningIssue = new DossierWarningIssue()
                    dossierWarningIssue.dossierSummary = registros
                    dossierWarningIssue.dossierProcessingWarning = DossierProcessingWarning.findByEtiqueta(it)
                    dossierWarningIssue.save(flush:true)
                }
            }
        } else {
            println ("[DossierSummary] No se registró la respuesta.")
        }       
                
        def respuesta = [statusCode:'200', success:'true']
        render respuesta as JSON
    }
}
