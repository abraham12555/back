package la.kosmos.app.logging.service.impl

import org.springframework.transaction.annotation.Propagation;

import grails.transaction.Transactional
import la.kosmos.app.EmailService
import la.kosmos.app.Logging

/** 
 * @author Mike Martinez 
 *   Servicio para logueo de erres con BussinesException y Exception para los 
 *   servicios de Buro de Crédito, EngineDesicion y Calixta
 *   Los metodos para tratar excepciones tiene el prefijo loggingException y su terminacion en con las letras en mayusculas 
 *   que identifican el nombre del servicio que origino el error 
 *   BC -> Buro de Credito 
 *   EN -> EngneDesicion
 *   CX-> CALIXTA
 *   Los metodos que generan BussinesException tiene el prefijo loggingBusiness y la terminacion a dos letras en mayusculas del servicio que genero el error 
 * 
 * */

@Transactional
class LoggingService {

	
    public void loggingExceptionBC(Exception   ex , String   idSolicitud, def dataUser ) {
        String errorCodeBC = GeneradorCodigoError.getCodigoErrorBC()  
        log.error("Codigo Error: "+errorCodeBC+" Se generó un error de tipo: " 
            + ex.getStackTrace().toString())
        if(dataUser == null){
            saveErrorDB(errorCodeBC, " Se generó un error de tipo: " 
                + ex.getStackTrace().toString(), Integer.parseInt(idSolicitud),null)
        }else{                                           
            saveErrorDB(errorCodeBC, " Se generó un error de tipo: " 
                + ex.getStackTrace().toString(), Integer.parseInt(idSolicitud), dataUser.id)
        }
        
    }
  
    public void loggingExceptionEN(Exception   ex , String   idSolicitud, def dataUser ) {
        String errorCodeEN = GeneradorCodigoError.getCodigoErrorEN()  
        log.error("Codigo Error: "+errorCodeEN+" Se generó un error de tipo: " 
        + ex.getStackTrace())	
        if(dataUser == null){
            saveErrorDB(errorCodeEN, ex.getMessage().toString(), Integer.parseInt(idSolicitud),null)
        }else{      
            saveErrorDB(errorCodeEN, ex.getMessage().toString(), Integer.parseInt(idSolicitud), dataUser.id)				  					
        }
    }
   
    public void loggingExceptionCX(Exception ex, String movil){
        String errorCodeCX = GeneradorCodigoError.getCodigoErrorCX()  
        log.error("Codigo Error: "+errorCodeCX+" Se generó un error de tipo: " 
            + ex.getStackTrace().toString())				 				
        saveErrorDB(errorCodeCX, "No se pudo enviar SMS al numero de telefono: "+movil)
    }
   
   
    public void loggingBusinessBC(String idSolicitud, def dataUser, String message){	   
        String errorCode = GeneradorCodigoError.getBusinessErrorBC()
        saveErrorDB(errorCode, message, Integer.parseInt(idSolicitud), dataUser.id)
    }
   
   
    public void loggingBusinessEN(String idSolictud, def dataUser, String message){	   
        String errorCode = GeneradorCodigoError.getBusinessErrorEN()
        saveErrorDB(errorCode, message, Integer.parseInt(idSolicitud), dataUser.id)
    }
   
    public void loggingBusinessCX(String message){
        String errorCode = GeneradorCodigoError.getBusinessErrorCX()
        saveErrorDB(errorCode, message)
    }
   
   
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    protected  void  saveErrorDB(String errorCode, String message, Integer  idSolicitud, def  idUser){
        Logging loggingData = new Logging ()	   
        loggingData.codigoError = errorCode
        loggingData.descripcionError = message
        loggingData.idSolicitud = idSolicitud
        loggingData.fechaError = new  Date();
        loggingData.idUser = idUser
        loggingData.save(flush:true, failOnError: true)				   				 
    } 
    @Transactional(propagation =Propagation.REQUIRES_NEW)
    protected void  saveErrorDB(String errorCode, String message){
        Logging loggingData = new Logging ()	   
        loggingData.codigoError = errorCode
        loggingData.descripcionError = message
        loggingData.fechaError = new  Date();	   
        loggingData.save(flush:true, failOnError: true)
	   
    }
}
