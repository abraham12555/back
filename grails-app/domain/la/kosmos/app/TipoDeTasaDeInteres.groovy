package la.kosmos.app

class TipoDeTasaDeInteres implements Serializable{

    String nombre
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_tasa_de_interes', params:[sequence:'tipo_de_tasa_de_interes_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
