package la.kosmos.app

class CampoFormulario implements Serializable{

    String nombreDelCampo
    Clase claseAsociada
    TipoDeCampo tipoDeCampo
    boolean catalogo = false
    String claseCatalogo
    
    static constraints = {
        nombreDelCampo (blank: false)
        claseAsociada (nullable:false)
        tipoDeCampo (nullable:false)
        claseCatalogo (nullable:true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_campo_formulario', params:[sequence:'campo_formulario_id_seq']
    }
    
    String toString () {
        "${nombreDelCampo}"
    }
}
