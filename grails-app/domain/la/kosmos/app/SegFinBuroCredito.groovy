package la.kosmos.app

import java.io.Serializable;

class SegFinBuroCredito implements Serializable{

	String longiudTransmision
	String numeroControlConsulta
	String finRegistroRespuesta
	ReporteBuroCredito reporteBuroCredito
    
	String toString(){
		"${longiudTransmision} ${numeroControlConsulta}"
	}
	
	static constraints = {
		longiudTransmision nullable: true
		numeroControlConsulta nullable: true
		finRegistroRespuesta nullable: true
    }
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_segfinBuroCredito', params:[sequence:'segfinBuroCredito_id_seq']
	}
	
}
