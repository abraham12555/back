package la.kosmos.app

class SegmentoError implements Serializable{
	
    String segmentoBuroCredito
    String campoBuroCredito
    String descripcion
    String pasoPlataforma
    CampoFormulario campoFormulario
    PasoSolicitudEntidadFinanciera pasoSolicitud

    static constraints = {
        segmentoBuroCredito nullable: false
        campoBuroCredito nullable: false
        descripcion nullable: true
        pasoPlataforma nullable: true
        campoFormulario nullable: true
        pasoSolicitud nullable: true
    }
	
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_segmentoError', params:[sequence:'segmentoError_id_seq']
    }
		
}
