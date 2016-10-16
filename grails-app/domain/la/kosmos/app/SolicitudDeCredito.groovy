package la.kosmos.app

class SolicitudDeCredito implements Serializable{

    Date fechaDeSolicitud
    Cliente cliente
    StatusDeSolicitud statusDeSolicitud
    String folio
    EntidadFinanciera entidadFinanciera
    PuntoDeVenta puntoDeVenta
	ReporteBuroCredito reporteBuroCredito
	VinculacionBanco vinculacionBanco 
    
    static constraints = {
        fechaDeSolicitud (nullable: false)
        cliente (nullable: false)
        statusDeSolicitud (nullable: false)
        folio (nullable: false)
        entidadFinanciera (nullable: false)
        puntoDeVenta (nullable: true)
		reporteBuroCredito (nullable: true)
		vinculacionBanco (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_status_de_solicitud', params:[sequence:'status_de_solicitud_id_seq']
    }
}
