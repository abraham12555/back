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
    boolean aplicacionVariable = false
    
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
    }

    static mapping = {
        id generator: 'sequence', column: 'id_configuracion_entidad_financiera', params:[sequence:'configuracion_entidad_financiera_id_seq']
    }
}
