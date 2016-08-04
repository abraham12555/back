package la.kosmos.app

class TipoDeContrato implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_contrato', params:[sequence:'tipo_de_contrato_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
