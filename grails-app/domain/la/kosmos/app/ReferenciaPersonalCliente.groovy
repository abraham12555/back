package la.kosmos.app

class ReferenciaPersonalCliente implements Serializable{
    
    String nombreCompleto
    String telefonoParticular
    String telefonoCelular
    String emailPersonal
    Cliente cliente
    TipoDeReferenciaPersonal tipoDeReferencia
    
    static constraints = {
        nombreCompleto (blank: false)
        telefonoParticular (blank: false, maxSize: 10)
        telefonoCelular (blank: false, maxSize: 10)
        emailPersonal (blank: false)
        cliente (nullable: false)
        tipoDeReferencia (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_referencia_personal', params:[sequence:'referencia_personal_id_seq']
    }
}
