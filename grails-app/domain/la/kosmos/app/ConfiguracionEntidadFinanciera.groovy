package la.kosmos.app

class ConfiguracionEntidadFinanciera implements Serializable{

    String nombreComercial
    String rutaLogotipo
    String slogan
    String razonSocial
    EntidadFinanciera entidadFinanciera
    String tituloCotizador
    String textoProductoDefault
    String textoMontoDefault
    String textoDescripcionDefault
    String imagenDefault
    String colorBordeSuperior
    String colorEncabezado
    String colorFondo
    String colorTitulos
    String colorGradienteInferior
    String colorGradienteSuperior
    String terminosCondiciones
    String rutaCss
    String wsdlMotorDeDecision
    String wsdlMotorDeDecisionCE
    String urlDominio
    String htmlTitle
    int ejecutarMotorEnPaso
    boolean aplicacionVariable = false
    boolean navegacionLibre = false
    boolean subirDocumentosOpcional = false
    int pasoLimiteNavegacionLibre
    boolean enviarNotificacionesPorCorreo = true
    boolean usarTwilio = false
    String mensajeConfirmacionCelular
    String mensajeEnvioShortUrl
    int registroSesiones
    int registroPasswords
    int expirarPasswords
    String emailHost
    String emailFrom
    String emailPort
    String emailUsername
    String emailPassword
    String rutaFotoPerfilUsuario
    int vigenciaSolicitudTemporal
    int vigenciaSolicitudFormal
    String  rutaLogotipoFormatoBuro
    String  nombreEntidadFinancieraFormatoBuro
    String  codigoCalidad
//    boolean usarEmailsCalixta 
//    boolean usarEmailsCalixtaTemplate

    static constraints = {
        nombreComercial (blank:false)
        rutaLogotipo (blank: false)
        slogan (blank: false)
        razonSocial (blank: false)
        entidadFinanciera (nullable: false, unique: true)
        textoProductoDefault (blank: false)
        textoMontoDefault (blank: false)
        textoDescripcionDefault (blank: false)
        imagenDefault (blank: false)
        colorBordeSuperior (blank: false)
        colorEncabezado (blank: false)
        colorFondo (blank: false)
        colorTitulos (blank: false)
        colorGradienteInferior (blank: false)
        colorGradienteSuperior (blank: false)
        terminosCondiciones (blank: false)
        rutaCss (nullable: true)
        wsdlMotorDeDecision (nullable: true)
        wsdlMotorDeDecisionCE (nullable: true)
        htmlTitle (nullable: false)
        mensajeConfirmacionCelular (nullable: true)
        mensajeEnvioShortUrl (nullable: true)
        emailHost (nullable: true)
        emailFrom (nullable: true)
        emailPort (nullable: true)
        emailUsername (nullable: true)
        emailPassword (nullable: true)
        rutaFotoPerfilUsuario (nullable: true)
        rutaLogotipoFormatoBuro (nullable: true)
        nombreEntidadFinancieraFormatoBuro (nullable: true)
        codigoCalidad (nullable: true)
//        usarEmailsCalixta (nullable: true)
//        usarEmailsCalixtaTemplate (nullable: true)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_configuracion_entidad_financiera', params:[sequence:'configuracion_entidad_financiera_id_seq']
    }
}
