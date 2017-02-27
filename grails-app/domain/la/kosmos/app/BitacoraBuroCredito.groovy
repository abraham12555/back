package la.kosmos.app

class BitacoraBuroCredito implements Serializable{

    Date fechaPeticion = new Date()
    Date fechaRespuesta
    String peticion
    String respuesta
    SolicitudDeCredito solicitud
	
    static constraints = {
        fechaPeticion nullable: false
        fechaRespuesta nullable: true
        peticion nullable:false
        respuesta nullable:true
        solicitud nullable :false
		
    }
	
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_bitacoraBuroCredito', params:[sequence:'bitacoraBuroCredito_id_seq']
    }
	
}
