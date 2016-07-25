package la.kosmos.app

class Producto implements Serializable{

    String nombreDelProducto
    Marca marca
    EntidadFinanciera entidadFinanciera
    TipoDeProducto tipoDeProducto
    String rutaImagenDefault
    boolean activo = true
    
    static constraints = {
        nombreDelProducto (blank: false)
        marca (nullable: false)
        entidadFinanciera (blank: false)
        tipoDeProducto (nullable: false)
        rutaImagenDefault (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_producto', params:[sequence:'producto_id_seq']
    }
    
    String toString () {
        "${nombreDelProducto}"
    }
}
