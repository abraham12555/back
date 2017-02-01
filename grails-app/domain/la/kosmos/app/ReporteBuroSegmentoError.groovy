package la.kosmos.app

class ReporteBuroSegmentoError implements Serializable{

	ReporteBuroCredito reporteBuroCredito
	SegmentoError	segmentoError
	
	
	static constraints = {
		reporteBuroCredito nullable: true
		segmentoError nullable: true
		
	}
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_reporteBuroSegmentoError', params:[sequence:'reporteBuroSegmentoError_id_seq']
	}

	
}
