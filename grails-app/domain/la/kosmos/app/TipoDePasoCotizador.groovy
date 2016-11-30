package la.kosmos.app

class TipoDePasoCotizador implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_paso_cotizador', params:[sequence:'tipo_de_paso_cotizador_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
