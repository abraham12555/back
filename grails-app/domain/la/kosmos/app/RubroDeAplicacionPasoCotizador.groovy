package la.kosmos.app

class RubroDeAplicacionPasoCotizador {
    
    RubroDeAplicacionDeCredito rubro
    PasoCotizadorEntidadFinanciera paso

    static constraints = {
        rubro (nullable: false)
        paso (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_rubro_paso_cotizador', params:[sequence:'rubro_paso_cotizador_id_seq']
    }
}
