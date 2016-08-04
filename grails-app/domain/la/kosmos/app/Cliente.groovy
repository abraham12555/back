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
    NivelEducativo nivelEducativo
    int dependientesEconomicos = 0
    String nombreDelConyugue
    Date fechaDeRegistro = new Date()
    
    static constraints = {

        nombre (blank: false,)
        apellidoPaterno (nullable: true)
        apellidoMaterno (nullable: true)
        nacionalidad (nullable: false)
        fechaDeNacimiento (nullable: false)
        lugarDeNacimiento (nullable: true)
        curp ( maxSize: 18, unique: true, nullable: true)
        genero (nullable: true)
        rfc ( maxSize: 13, unique: true, nullable: true)
        estadoCivil (nullable: false)
        nivelEducativo (nullable: false)
        nombreDelConyugue (nullable: true)
        fechaDeRegistro (nullable: false)

    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_cliente', params:[sequence:'cliente_id_seq']
    }
}
