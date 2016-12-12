package la.kosmos.app

class PasoCotizadorEntidadFinanciera implements Serializable{

    EntidadFinanciera entidadFinanciera
    String tituloDelPaso
    int numeroDePaso
    String tituloResumen
    String idListaAjax
    String variableValorSeleccionado
    TipoDePasoCotizador tipoDePaso
    boolean cargaInicial = true
    boolean tieneAyuda = false
    String textoAyuda
    
    static constraints = {
        entidadFinanciera (nullable: false)
        tituloDelPaso (blank: false)
        tituloResumen  (blank: false)
        idListaAjax (blank: false)
        variableValorSeleccionado (blank: false)
        numeroDePaso (min: 1)
        tipoDePaso (nullable: false)
        textoAyuda (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_paso_cotizador_entidad_financiera', params:[sequence:'paso_cotizador_entidad_financiera_id_seq']
    }
    
    String toString () {
        "${numeroDePaso} - ${tituloDelPaso}"
    }
}
