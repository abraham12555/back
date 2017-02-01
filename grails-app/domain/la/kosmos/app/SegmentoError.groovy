package la.kosmos.app

class SegmentoError implements Serializable{
	
	String segmentoBuroCredito
	String campoBuroCredito
	String descripcion
	String pasoPlataforma
	String campoPlataforma

    static constraints = {
		segmentoBuroCredito nullable: false
		campoBuroCredito nullable: false
		descripcion nullable: true
		pasoPlataforma nullable: true
		campoPlataforma nullable: true
		
    }
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_segmentoError', params:[sequence:'segmentoError_id_seq']
	}
		
}
