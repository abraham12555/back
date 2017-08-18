package la.kosmos.app

class TipoDeDocumento implements Serializable{
    
    String nombre
    String nombreEnPlural
    String formatosPermitidos
    boolean activo
    boolean usoEnCotizador
    boolean usoEnSolicitud
    TipoDeIngresos tipoDeIngresos
    String nombreMapeo
    int cantidadSolicitada = 1
    String codigo

    static constraints = {
        nombre (blank: false)
        formatosPermitidos (nullable: false)
        tipoDeIngresos (nullable: true)
        nombreMapeo (blank: false)
        codigo (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_documento', params:[sequence:'tipo_de_documento_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
    
    static final Integer RECIBOLUZ = 1
    static final Integer INE = 2
    static final Integer ESTADODECUENTA = 3 
    static final Integer RECIBONOMINA = 4 
    static final Integer DECLARACIONSAT = 5
    static final Integer RECIBOHONORARIOS = 6
    static final Integer NOTA = 7
    static final Integer TICKET = 8
    static final Integer REMISION = 9
    static final Integer RECIBOTELEFONICO = 10
    static final Integer PASAPORTE = 11
    static final Integer FORMATOACBC = 12
}
