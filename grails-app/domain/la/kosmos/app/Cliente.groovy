package la.kosmos.app

class Cliente implements Serializable{
    
    String nombre
    String apellidoPaterno
    String apellidoMaterno
    Nacionalidad nacionalidad
    Date fechaDeNacimiento
    Estado lugarDeNacimiento
    String curp
    Genero genero
    String rfc
    EstadoCivil estadoCivil
    int dependientesEconomicos = 0
    String nombreDelConyugue
    Date fechaDeRegistro = new Date()
    
    static constraints = {
        nombre (blank: false)
        apellidoPaterno (blank: false)
        apellidoMaterno (blank: false)
        nacionalidad (nullable: false)
        fechaDeNacimiento (nullable: false)
        lugarDeNacimiento (nullable: false)
        curp (blank: false, maxSize: 18, unique: true)
        genero (nullable: false)
        rfc (blank: false, maxSize: 13, unique: true)
        estadoCivil (nullable: false)
        nombreDelConyugue (nullable: true)
        fechaDeRegistro (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_cliente', params:[sequence:'cliente_id_seq']
    }
}
