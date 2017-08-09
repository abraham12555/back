package la.kosmos.app

import la.kosmos.app.vo.Constants.TipoConsulta
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
    ConfiguracionEntidadFinanciera configuracionEntidadFinanciera
    TipoConsulta tipoConsulta
    String ipBuroCredito
    String portBuroCredito


    static constraints = {
        urlBuroCredito (nullable: true)
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
        configuracionEntidadFinanciera(nullable: false)
        tipoConsulta (nullable: false)
        ipBuroCredito (nullable: true)
        portBuroCredito (nullable: true)
    }

    static mapping = {
        version false
        id generator: 'sequence', column: 'id_configuracionBuroCredito', params:[sequence:'configuracionBuroCredito_id_seq']
        tipoConsulta (enumType:"ordinal")
    }

}
