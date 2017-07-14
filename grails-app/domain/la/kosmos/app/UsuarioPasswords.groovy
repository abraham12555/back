package la.kosmos.app

class UsuarioPasswords implements Serializable {

    String password
    Date fecha

    UsuarioPasswords(){

    }

    UsuarioPasswords(Usuario usuario, Date fecha){
        this.usuario = usuario
        this.password = usuario.password
        this.fecha = fecha
    }

    static constraints = {
        password (blank: false, nullable: false, maxSize:255)
        fecha (nullable: false)
    }

    static mapping = {
        id composite:['usuario','password'], generator:'assigned'
    }

    static belongsTo = [usuario: Usuario]
}