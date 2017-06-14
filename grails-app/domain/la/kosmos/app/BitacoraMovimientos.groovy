package la.kosmos.app

class BitacoraMovimientos {

    String movimiento

    static Integer AGREGAR_PERFIL = 1
    static Integer ELIMINAR_PERFIL = 2

    BitacoraMovimientos(){

    }

    BitacoraMovimientos(Integer id, String movimiento){
        this.id = id
        this.movimiento = movimiento
    }

    static constraints = {
        movimiento (blank: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_bitacora_movimientos', params:[sequence:'bitacora_movimientos_id_seq']
    }
}
