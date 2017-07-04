package la.kosmos.app

class PasoOpcionalEntidadFinanciera implements Serializable{

    String titulo
    String subtitulo
    TipoDePasoSolicitud tipoDePaso
    int numeroDePaso
    int pasoAnterior
    int pasoSiguiente
    EntidadFinanciera entidadFinanciera
    boolean mostrarEnBarra = true
    boolean activo = true
    boolean dependeDelMotor = false
    boolean ultimoPaso = false
    String rutaTemplate
    String recursosCss
    String recursosJavascript
    
    static constraints = {
        titulo (blank: false)
        subtitulo (blank: false)
        tipoDePaso (nullable: false)
        numeroDePaso (range: 1..10)
        entidadFinanciera (nullable: false)
        rutaTemplate (nullable: false)
        recursosCss (nullable: false)
        recursosJavascript (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_paso_opcional_entidad_financiera', params:[sequence:'paso_opcional_entidad_financiera_id_seq']
    }
    
    String toString () {
        "${numeroDePaso} - ${titulo}"
    }
}
