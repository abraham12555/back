package la.kosmos.app

class EmailCliente implements Serializable{

    String direccionDeCorreo
    TipoDeEmail tipoDeEmail
    Cliente cliente
    boolean vigente = true
    
    static constraints = {
        direccionDeCorreo (blank: false)//, unique : true)
        tipoDeEmail (nullable: false)
        cliente (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_email', params:[sequence:'email_id_seq']
    }
    
    String toString(){
        "${direccionDeCorreo}"
    }
}
