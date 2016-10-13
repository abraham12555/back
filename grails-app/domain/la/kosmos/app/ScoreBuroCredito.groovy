package la.kosmos.app

class ScoreBuroCredito  implements Serializable{

	String nombreScore
	String codigoScore
	String valorScore
	String codigoRazonPrimera
	String codigoRazonSegunda
	String codigoRazonTercera
	String codigoError
	ReporteBuroCredito reporteBuroCredito
	
	String toString(){
		"${nombreScore} ${codigoScore} ${valorScore}"
	}
	
	static constraints = {
		 nombreScore nullable: true
		 codigoScore nullable: true
		 valorScore nullable: true
		 codigoRazonPrimera nullable: true
		 codigoRazonSegunda nullable: true
		 codigoRazonTercera nullable: true
		 codigoError nullable: true	
    }
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_scoreBuroCredito', params:[sequence:'scoreBuroCredito_id_seq']
	}
	
	
}
