package la.kosmos.app

class Modelo implements Serializable{

    String nombre
    String descripcion
    String rutaImagen
    float precio
    boolean activo

    Producto producto

    static constraints = {
        nombre (blank:false)
        descripcion (blank:false)
        producto (nullable:false)
        rutaImagen (nullable:false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_modelo', params:[sequence:'modelo_id_seq']
    }
    
    String toString () {
        "${producto.nombreDelProducto} - ${nombre}"
    }
}
