package la.kosmos.app

class TipoDeProducto implements Serializable{

    String nombre
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_producto', params:[sequence:'tipo_de_producto_id_seq']
    }
}
