package la.kosmos.app

class ColorModelo implements Serializable{

    String nombre
    String rutaImagen
    boolean activo

    Modelo modelo

    static constraints = {
        nombre (blank:false)
        rutaImagen (nullable:false)
        modelo (nullable:false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_color_modelo', params:[sequence:'color_modelo_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
