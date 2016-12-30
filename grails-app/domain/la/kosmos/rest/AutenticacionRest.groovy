package la.kosmos.rest

import la.kosmos.app.EntidadFinanciera

class AutenticacionRest implements Serializable{

    EntidadFinanciera entidadFinanciera
    Date fechaDeAlta
    Date fechaDeBaja
    String nombreDelUsuario
    String username
    String base64Auth
    boolean activo = true
    
    static constraints = {
        entidadFinanciera (nullable: false)
        nombreDelUsuario (blank: false)
        username (blank: false)
        base64Auth (blank: false)
        fechaDeAlta (nullable: false)
        fechaDeBaja (nullable: true)
    }
    
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_autenticacion_rest', params:[sequence:'autenticacion_rest_id_seq']
    }
}
