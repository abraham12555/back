package la.kosmos.app

import la.kosmos.app.vo.Constants.TipoConsulta

class BitacoraBuroCredito implements Serializable{

    Date fechaPeticion = new Date()
    Date fechaRespuesta
    String peticion
    String respuesta
    SolicitudDeCredito solicitud
    TipoConsulta tipoConsulta

    static constraints = {
        fechaPeticion nullable: false
        fechaRespuesta nullable: true
        peticion nullable:false
        respuesta nullable:true
        solicitud nullable :false
        tipoConsulta nullable: false
		
    }
	
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_bitacoraBuroCredito', params:[sequence:'bitacoraBuroCredito_id_seq']
        tipoConsulta (enumType:"ordinal")
    }
	
}
