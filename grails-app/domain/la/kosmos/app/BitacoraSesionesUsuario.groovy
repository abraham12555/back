package la.kosmos.app

class BitacoraSesionesUsuario implements Serializable {

    Usuario usuario
    EntidadFinanciera entidadFinanciera
    String ip
    Date fecha

    BitacoraSesionesUsuario(){

    }

    BitacoraSesionesUsuario(Usuario usuario, EntidadFinanciera entidadFinanciera, String ip, Date fecha){
        this.usuario = usuario
        this.entidadFinanciera = entidadFinanciera
        this.ip = ip
        this.fecha = fecha
    }

    static constraints = {
        usuario (nullable: false)
        entidadFinanciera (nullable: false)
        ip (nullable: false)
        fecha (nullable: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_bitacora_sesiones_usuario', params:[sequence:'bitacora_sesiones_usuario_id_seq']
    }

    static belongsTo = [usuario: Usuario]
}
