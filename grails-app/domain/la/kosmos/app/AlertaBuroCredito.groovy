package la.kosmos.app

import java.io.Serializable;

class AlertaBuroCredito implements Serializable{

	String fechaReporte
	String codigoPrevension
	String tipoUsuario
	String mensaje
	ReporteBuroCredito reporteBuroCredito
	
    static constraints = {
		fechaReporte nullable: true
		codigoPrevension nullable: true
		tipoUsuario nullable: true
		mensaje nullable: true
    }
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_alertaBuroCredito', params:[sequence:'alertaBuroCredito_id_seq']
	}
}
