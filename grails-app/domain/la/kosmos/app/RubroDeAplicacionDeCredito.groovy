package la.kosmos.app

class RubroDeAplicacionDeCredito implements Serializable{

    EntidadFinanciera entidadFinanciera
    String nombre
    String descripcion
    String claseIconoPaso
    boolean activo = true
    String urlImagen
    int posicion
    
    static constraints = {
        entidadFinanciera (nullable: false)
        nombre (blank: false)
        descripcion (blank: false)
        claseIconoPaso (nullable: true)
        urlImagen (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_rubro_de_aplicacion_de_credito', params:[sequence:'rubro_de_aplicacion_de_credito_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
