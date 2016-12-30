package la.kosmos.app

class FotografiaSolicitud implements Serializable{

    SolicitudDeCredito solicitud
    Date fechaDeSubida
    String rutaDelArchivo
    String nombreDelArchivo
    TipoDeFotografia tipoDeFotografia
    Usuario usuario
    
    static constraints = {
        solicitud (nullable:false)
        fechaDeSubida (nullable:false)
        rutaDelArchivo (blank:false)
        nombreDelArchivo  (blank:false)
        tipoDeFotografia (nullable:false)
        usuario (nullable:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_fotografia_solicitud', params:[sequence:'fotografia_solicitud_id_seq']
    }
}
