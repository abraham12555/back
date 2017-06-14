package la.kosmos.app

class TelefonoCliente implements Serializable{

    String numeroTelefonico
    TipoDeTelefono tipoDeTelefono
    Cliente cliente
    boolean vigente = true
    
    static constraints = {
        numeroTelefonico (blank: false, maxSize: 14)
        tipoDeTelefono (nullable: false)
        cliente (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_telefono', params:[sequence:'telefono_id_seq']
    }
    
    static belongsTo = [cliente: Cliente]
}
