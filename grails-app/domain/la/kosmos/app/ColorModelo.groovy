package la.kosmos.app

class ColorModelo implements Serializable{

    String nombre
    Modelo modelo
    String rutaImagen
    boolean activo

    static constraints = {
        nombre (blank:false)
        modelo (nullable:false)
        rutaImagen (nullable:false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_color_modelo', params:[sequence:'color_modelo_id_seq']
    }
}
