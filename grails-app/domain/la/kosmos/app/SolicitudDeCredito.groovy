package la.kosmos.app

class SolicitudDeCredito implements Serializable{

    Date fechaDeSolicitud
    String usuarioQueSolicita//TODO cambiar por el id de usuario resultante 
    StatusDeSolicitud statusDeSolicitud//del logueo con facebook, linkedin y google
	ReporteBuroCredito reporteBuroCredito
    
    static constraints = {
        fechaDeSolicitud (nullable: false)
        usuarioQueSolicita (nullable: false)
        statusDeSolicitud (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_status_de_solicitud', params:[sequence:'status_de_solicitud_id_seq']
    }
}
