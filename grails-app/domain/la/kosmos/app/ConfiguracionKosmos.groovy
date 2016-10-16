package la.kosmos.app

class ConfiguracionKosmos implements Serializable{

    String urlEphesoft
    String batchClassEphesoft
    String usuarioEphesoft
    String passwdEphesoft
    String pathDocumentos
	
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
	boolean habilitarMockBuroCredito
	boolean habilitarMockBuroCreditoSuccess
	
    static constraints = {
        urlEphesoft (nullable: false)
        batchClassEphesoft (nullable: false)
        usuarioEphesoft (nullable: false)
        passwdEphesoft (nullable: false)
        pathDocumentos (nullable: false)
		urlBuroCredito (nullable: true)
		encabezadoVersion (nullable: true)
		encabezadoProductoRequerido (nullable: true)
		encabezadoClavePais (nullable: true)
		encabezadoIdentificadorBuro (nullable: true)
		encabezadoClaveUsuario (nullable: true)
		encabezadoPassword (nullable: true)
		encabezadoTipoConsulta (nullable: true)
		encabezadoTipoContrato (nullable: true)
		encabezadoClaveUnidadMonetaria (nullable: true)
		encabezadoIdioma (nullable: true)
		encabezadoTipoSalida (nullable: true)
		autenticaTipoReporte (nullable: true)
		autenticaTipoSalidaAU (nullable: true)
		habilitarMockBuroCredito (nullable: true)
		habilitarMockBuroCreditoSuccess (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_configuracion', params:[sequence:'configuracion_id_seq']
    }
}
