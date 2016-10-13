package la.kosmos.app

class DeclaConsBuroCredito implements Serializable{

	String tipoSegmento
	String declarativaCliente
	ReporteBuroCredito reporteBuroCredito
	
    static constraints = {
		 tipoSegmento nullable: true
		 declarativaCliente nullable: true
    }
	
	String toString(){
		"${tipoSegmento} ${declarativaCliente}"
	}
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_declaconsBuroCredito', params:[sequence:'declaconsBuroCredito_id_seq']
	}
}
