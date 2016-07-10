package la.kosmos.app

class TipoDeTelefono implements Serializable{

    String nombre
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_tipo_de_telefono', params:[sequence:'tipo_de_telefono_id_seq']
    }
}
