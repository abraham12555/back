package la.kosmos.app

class ConfiguracionBuroCredito implements Serializable {
	
	String urlBuroCredito
	String encabezadoVersion
	String encabezadoProductoRequerido
	String encabezadoClavePais
	String encabezadoIdentificadorBuro
	String encabezadoClaveUsuario
	String encabezadoPassword
	String encabezadoTipoConsulta
	String encabezadoTipoContrato
	String encabezadoClaveUnidadMonetaria
	String encabezadoIdioma
	String encabezadoTipoSalida
	String autenticaTipoReporte
	String autenticaTipoSalidaAU
	int reintentos
	Date fechaActualizacion = new Date()
	boolean activo=true
	boolean habilitarMockBuroCredito=false
	boolean habilitarMockBuroCreditoSuccess=false
	

    static constraints = {
		urlBuroCredito (nullable: false)
		encabezadoVersion (nullable: false)
		encabezadoProductoRequerido (nullable: false)
		encabezadoClavePais (nullable: false)
		encabezadoIdentificadorBuro (nullable: false)
		encabezadoClaveUsuario (nullable: false)
		encabezadoPassword (nullable: false)
		encabezadoTipoConsulta (nullable: false)
		encabezadoTipoContrato (nullable: false)
		encabezadoClaveUnidadMonetaria (nullable: false)
		encabezadoIdioma (nullable: false)
		encabezadoTipoSalida (nullable: false)
		autenticaTipoReporte (nullable: false)
		autenticaTipoSalidaAU (nullable: false)
		habilitarMockBuroCredito (nullable: false)
		habilitarMockBuroCreditoSuccess (nullable: false)
		reintentos (nullable: false)
		fechaActualizacion (nullable: false)
		activo (nullable: true)
    }
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_configuracionBuroCredito', params:[sequence:'configuracionBuroCredito_id_seq']
	}
	
}
