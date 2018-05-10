package la.kosmos.app

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@EqualsAndHashCode(includes='username')
@ToString(includes='username', includeNames=true, includePackage=false)
class Usuario implements Serializable {

    private static final long serialVersionUID = 1

    transient springSecurityService
    transient entidadTemporal

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired
    EntidadFinanciera entidadFinanciera
    SucursalEntidadFinanciera sucursal
    String nombre
    String apellidoPaterno
    String apellidoMaterno
    String email
    Date fechaPassword
    String numeroDeEmpleado
    String idDispositivo

    Usuario(String username, String password) {
        this()
        this.username = username
        this.password = password
    }

    Set<Rol> getAuthorities() {
        UsuarioRol.findAllByUsuario(this)*.rol
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
    
    static transients = ['springSecurityService', 'entidadTemporal']

    static constraints = {
        username blank: false, unique: true
        password blank: false
        entidadFinanciera nullable: false
        nombre nullable: false
        apellidoPaterno nullable: false
        apellidoMaterno nullable: false
        email nullable: false
        sucursal nullable: true
        numeroDeEmpleado nullable: true
        fechaPassword nullable: false
        sesionUsuario nullable: true
        fotoPerfilUsuario nullable: true
        idDispositivo nullable: true
    }

    static mapping = {
        password column: '`password`'
        sesionUsuario cascade: 'all-delete-orphan'
    }
    
    static hasOne = [sesionUsuario:SesionUsuario, fotoPerfilUsuario:FotoPerfilUsuario]
    
    static hasMany = [userPasswords: UsuarioPasswords]
        
    String toString () {
        "${nombre} ${apellidoPaterno} ${apellidoMaterno}"
    }
}
