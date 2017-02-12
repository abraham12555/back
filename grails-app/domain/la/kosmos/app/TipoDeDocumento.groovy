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
}
