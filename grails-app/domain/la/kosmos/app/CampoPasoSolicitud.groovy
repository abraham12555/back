package la.kosmos.app

class CampoPasoSolicitud implements Serializable{

    PasoSolicitudEntidadFinanciera pasoSolicitud
    CampoFormulario campo
    String placeholder
    String longitudDelCampo
    String tituloDelCampo
    String textoAnterior
    String textoPosterior
    int numeroDeCampo
    boolean saltoDeLineaAlFinal = false
    boolean obligatorio = true
    boolean mostrarAlInicio = false
    int parrafo = 1
    String dependeDe
    String valorDeDependencia
    boolean tieneAyuda = false
    String textoAyuda
    
    static constraints = {
        pasoSolicitud (nullable: false)
        campo (nullable: false)
        placeholder (blank: false)
        longitudDelCampo (blank: false)
        tituloDelCampo (blank: false)
        textoAnterior (blank: false)
        textoPosterior (blank: false)
        numeroDeCampo (range: 1..50)
        dependeDe (nullable: true)
        valorDeDependencia (nullable: true)
        textoAyuda (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_campo_paso_solicitud', params:[sequence:'campo_paso_solicitud_id_seq']
    }
    
    String toString () {
        "${numeroDeCampo} - ${tituloDelCampo}"
    }
}

