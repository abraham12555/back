package la.kosmos.app

class PasoCotizadorEntidadFinanciera implements Serializable{

    EntidadFinanciera entidadFinanciera
    String tituloDelPaso
    String claseIconoPaso
    int numeroDePaso
    String tituloResumen
    String idListaAjax
    String variableValorSeleccionado
    TipoDePasoCotizador tipoDePaso
    boolean cargaInicial = true
    
    static constraints = {
        entidadFinanciera (nullable: false)
        tituloDelPaso (blank: false)
        tituloResumen  (blank: false)
        idListaAjax (blank: false)
        variableValorSeleccionado (blank: false)
        claseIconoPaso (nullable: true)
        numeroDePaso (min: 1)
        tipoDePaso (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_paso_cotizador_entidad_financiera', params:[sequence:'paso_cotizador_entidad_financiera_id_seq']
    }
    
    String toString () {
        "${numeroDePaso} - ${tituloDelPaso}"
    }
}
