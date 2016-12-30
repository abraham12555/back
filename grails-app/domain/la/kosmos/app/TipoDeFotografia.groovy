package la.kosmos.app

class TipoDeFotografia implements Serializable{

    String nombre
    boolean activa = true
    String referencia
    
    static constraints = {
        nombre (blank:false)
        referencia (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_fotografia', params:[sequence:'tipo_de_fotografia_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
