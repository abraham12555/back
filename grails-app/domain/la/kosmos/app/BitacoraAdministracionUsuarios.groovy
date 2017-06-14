package la.kosmos.app

class BitacoraAdministracionUsuarios {

    Usuario usuario
    Date fechaHora
    Usuario targetUser
    BitacoraMovimientos bitacoraMovimientos
    String descripcion

    static constraints = {
        usuario nullable: false
        fechaHora nullable: false
        targetUser nullable: false
        bitacoraMovimientos nullable: false
        descripcion nullable: true
    }

    static mapping = {
        id generator: 'sequence', column: 'id_bitacora_administracion_usuarios', params:[sequence:'bitacora_administracion_usuarios_id_seq']
    }

    static belongsTo = [usuario: Usuario, targetUser: Usuario]
}
