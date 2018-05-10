package la.kosmos.app

class Logging {
		String     codigoError
		String     descripcionError;
		Date       fechaError = new Date();
		Long       idSolicitud
		Long       idUser;
   
    static constraints = {
			 codigoError nullable:true
			 descripcionError nullable:true
			 fechaError nullable:true
			 idSolicitud nullable:true
			 idUser nullable:true
		 }
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_logging', params:[sequence:'id_logging_seq']
			
	}
	
}
