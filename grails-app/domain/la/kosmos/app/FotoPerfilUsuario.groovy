package la.kosmos.app

class FotoPerfilUsuario implements Serializable {

    String nombre
    String tipo
    String path

    static constraints = {
        nombre nullable: false
        tipo nullable: false
        path nullable: false
        usuario nullable:false
    }

    static belongsTo = [usuario: Usuario]

    static mapping = {
        id generator: 'sequence', column: 'id_foto_perfil_usuario', params:[sequence:'foto_perfil_usuario_id_seq']
    }
}
