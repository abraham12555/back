package la.kosmos.app

class Producto {

    String nombre
    String descripcion
    
    static constraints = {
        nombre blank: false
        descripcion blank:false
    }
}
