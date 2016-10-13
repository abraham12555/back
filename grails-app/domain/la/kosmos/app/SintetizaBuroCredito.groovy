package la.kosmos.app

class SintetizaBuroCredito implements Serializable{

	String plantillaSolicitada
	String identificador
	String numeroCaracteristica
	String valorCaracteristica
	String codigoError
	ReporteBuroCredito reporteBuroCredito
	
    static constraints = {
		plantillaSolicitada nullable: true
	    identificador nullable: true
		numeroCaracteristica nullable: true
		valorCaracteristica nullable: true
		codigoError nullable: true	
    }
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_sintetizaBuroCredito', params:[sequence:'sintetizaBuroCredito_id_seq']
	}
}
