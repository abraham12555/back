package la.kosmos.app

class Producto implements Serializable {

    String nombreDelProducto
    Marca marca
    EntidadFinanciera entidadFinanciera
    TipoDeProducto tipoDeProducto
    String rutaImagenDefault
    String descripcion = ""
    String tituloEnCotizador
    float montoMinimo = 0
    float montoMaximo = 0
    float tasaDeInteres  = 0
    TipoDeTasaDeInteres tipoDeTasa
    String claveDeProducto
    Esquema esquema
    String claseIconoPaso
    float cat = 0

    boolean activo = true

    static constraints = {
        nombreDelProducto(blank: false)
        marca(nullable: false)
        entidadFinanciera(blank: false)
        tipoDeProducto(nullable: false)
        rutaImagenDefault(blank: false)
        descripcion (blank: false)
        tituloEnCotizador (blank: false)
        tipoDeTasa(nullable: true)
        claveDeProducto(nullable: true)
        esquema(nullable: true)
        claseIconoPaso (nullable: true)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_producto', params: [sequence: 'producto_id_seq']
    }

    String toString() {
        "${nombreDelProducto}"
    }
}
