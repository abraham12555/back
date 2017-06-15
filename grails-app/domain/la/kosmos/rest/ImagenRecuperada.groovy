package la.kosmos.rest

class ImagenRecuperada implements Serializable {

    ClassificationResult classificationResult
    String documentoBase64
    String tipoDeImagen
    
    static constraints = {
        classificationResult (nullable: false)
        documentoBase64 (nullable: false)
    }
    
    static mapping = {
        id generator : 'sequence', column : 'id_imagen_recuperada', params:[sequence:'imagen_recuperada_id_seq']
    }
}
