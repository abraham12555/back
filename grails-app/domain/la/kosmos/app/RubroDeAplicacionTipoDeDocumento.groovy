package la.kosmos.app

class RubroDeAplicacionTipoDeDocumento implements Serializable{
    
    RubroDeAplicacionDeCredito rubro
    TipoDeDocumento tipoDeDocumento
    
    static constraints = {
        rubro (nullable: false)
        tipoDeDocumento (nullable: false)
    }
    
    static mapping = {
        id composite : ['rubro', 'tipoDeDocumento']
    }
}
