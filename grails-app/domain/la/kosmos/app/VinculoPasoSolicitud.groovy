package la.kosmos.app

class VinculoPasoSolicitud implements Serializable{

    PasoSolicitudEntidadFinanciera pasoSolicitud
    TipoDeVinculo vinculo
    String textoVinculo
    String textoBoton
    int numeroDeVinculo
    boolean activo = true
    
    static constraints = {
        pasoSolicitud (nullable: false)
        vinculo (nullable: false)
        textoVinculo (blank: false)
        textoBoton (blank: false)
        numeroDeVinculo (range: 1..3)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_vinculo_paso_solicitud', params:[sequence:'vinculo_paso_solicitud_id_seq']
    }
    
    String toString () {
        "${numeroDeVinculo} - ${textoVinculo}"
    }
}
