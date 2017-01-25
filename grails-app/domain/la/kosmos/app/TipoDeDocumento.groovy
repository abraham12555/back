package la.kosmos.app

class TipoDeDocumento implements Serializable{
    
    String nombre
    String formatosPermitidos
    boolean activo
    boolean usoEnCotizador
    boolean usoEnSolicitud
    TipoDeIngresos tipoDeIngresos
    String nombreMapeo

    static constraints = {
        nombre (blank: false)
        formatosPermitidos (nullable: false)
        tipoDeIngresos (nullable: true)
        nombreMapeo (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_documento', params:[sequence:'tipo_de_documento_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
