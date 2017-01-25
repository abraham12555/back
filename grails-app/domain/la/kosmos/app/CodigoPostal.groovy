package la.kosmos.app

class CodigoPostal implements Serializable{

    String codigo
    Municipio municipio
    String asentamiento
    TipoDeAsentamiento tipoDeAsentamiento
    
    static constraints = {
        codigo (blank: false, maxSize: 5)
        municipio (nullable: false)
        asentamiento (nullable: false)
        tipoDeAsentamiento (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_codigo_postal', params:[sequence:'codigo_postal_id_seq']
    }
    
    String toString () {
        "${codigo}"
    }
}
