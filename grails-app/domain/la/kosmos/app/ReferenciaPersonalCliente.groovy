package la.kosmos.app

class ReferenciaPersonalCliente implements Serializable{
    
    String nombre
    String apellidoPaterno
    String apellidoMaterno
    Genero genero
    EstadoCivil estadoCivil
    String telefonoParticular
    String telefonoCelular
    String emailPersonal
    Cliente cliente
    
    static constraints = {
        nombre (blank: false)
        apellidoPaterno (blank: false)
        apellidoMaterno (blank: false)
        genero (nullable: false)
        estadoCivil (nullable: false)
        telefonoParticular (blank: false, maxSize: 10)
        telefonoCelular (blank: false, maxSize: 10, unique: true)
        emailPersonal (blank: false, unique: true)
        cliente (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_referencia_personal', params:[sequence:'referencia_personal_id_seq']
    }
}
