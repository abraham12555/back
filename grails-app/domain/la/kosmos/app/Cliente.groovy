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
    String apellidoPaternoDelConyugue
    String apellidoMaternoDelConyugue
    Date fechaDeNacimientoDelConyugue
    Estado lugarDeNacimientoDelConyugue
    Nacionalidad nacionalidadDelConyugue
    String rfcDelConyugue
    String curpDelConyugue
    Date fechaDeRegistro = new Date()
    RegimenMatrimonial regimenMatrimonial
    
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
        estadoCivil (nullable: true)
        nivelEducativo (nullable: true)
        nombreDelConyugue (nullable: true)
        apellidoPaternoDelConyugue (nullable: true)
        apellidoMaternoDelConyugue (nullable: true)
        fechaDeNacimientoDelConyugue (nullable: true)
        lugarDeNacimientoDelConyugue (nullable: true)
        nacionalidadDelConyugue (nullable: true)
        rfcDelConyugue (nullable: true)
        curpDelConyugue (nullable: true)
        fechaDeRegistro (nullable: false)
        regimenMatrimonial (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_cliente', params:[sequence:'cliente_id_seq']
    }
    
    String toString(){
        "${nombre} ${apellidoPaterno} ${apellidoMaterno}"
    }
}
