package la.kosmos.app

class TasaDinamicaProducto implements Serializable {

    Producto producto
    float probabilidadDeIncumplimientoMinima
    float probabilidadDeIncumplimientoMaxima
    float tasaOrdinariaMensual
    
    static constraints = {
        producto (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tasa_dinamica_producto', params:[sequence:'tasa_dinamica_producto_id_seq']
    }
}
