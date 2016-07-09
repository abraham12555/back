package la.kosmos.app

class Modelo {

    String nombre
    String descripcion
    Producto producto

    static constraints = {
        nombre blank:false
        descripcion blank:false
        producto nullable:false
    }

    static mapping
}
