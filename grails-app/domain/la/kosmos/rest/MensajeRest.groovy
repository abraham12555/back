package la.kosmos.rest

class MensajeRest implements Serializable{
    
    static mapWith = "none"
    
    String codigoDeError
    String mensajeError

    static constraints = {
        codigoDeError (nullable:true)
        mensajeError (nullable:true)
    }
}
