package la.kosmos.app

class PasoSolicitudEntidadFinanciera implements Serializable{

    String titulo
    String subtitulo
    String modoDeDespliegue
    TipoDePasoSolicitud tipoDePaso
    int numeroDePaso
    int ponderacion
    EntidadFinanciera entidadFinanciera
    boolean requiereSubirIdentificacion = false
    boolean requiereSubirComprobante = false
    boolean ultimoPaso = false
    boolean mostrarEnBarra = true
    
    static constraints = {
        titulo (blank: false)
        subtitulo (blank: false)
        modoDeDespliegue (blank: false, inList: ['NARRATIVA','FORMULARIO'])
        tipoDePaso (nullable: false)
        numeroDePaso (range: 1..10)
        ponderacion (range: 1..100)
        entidadFinanciera (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_paso_solicitud_entidad_financiera', params:[sequence:'paso_solicitud_entidad_financiera_id_seq']
    }
    
    String toString () {
        "${numeroDePaso} - ${titulo}"
    }
}
