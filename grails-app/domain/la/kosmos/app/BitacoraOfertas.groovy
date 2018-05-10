package la.kosmos.app

class BitacoraOfertas {

    Date fecha 
    String error
    String motivo
    SolicitudDeCredito solicitud
    static constraints = {
    }
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_bitacoraofertas', params:[sequence:'bitacoraofertas_id_seq']
    }
}
