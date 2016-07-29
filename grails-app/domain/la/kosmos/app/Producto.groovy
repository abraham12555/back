package la.kosmos.app

class Producto implements Serializable {

    String nombreDelProducto
    Marca marca
    EntidadFinanciera entidadFinanciera
    TipoDeProducto tipoDeProducto
    String rutaImagenDefault
    String descripcion = ""

    boolean activo = true

    static constraints = {
        nombreDelProducto(blank: false)
        marca(nullable: false)
        entidadFinanciera(blank: false)
        tipoDeProducto(nullable: false)
        rutaImagenDefault(blank: false)
        descripcion(blank: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_producto', params: [sequence: 'producto_id_seq']
    }

    String toString() {
        "${nombreDelProducto}"
    }
}
