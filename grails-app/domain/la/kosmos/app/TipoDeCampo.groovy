package la.kosmos.app

class TipoDeCampo implements Serializable{

    String elementoDeEntrada
    String expresionRegular
    String nombreComun
    boolean requiereValidacion = true
    
    static constraints = {
        elementoDeEntrada (blank: false)
        expresionRegular (blank:false)
        nombreComun (blank:false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_campo', params:[sequence:'tipo_de_campo_id_seq']
    }
    
    String toString () {
        "${elementoDeEntrada}"
    }
}
