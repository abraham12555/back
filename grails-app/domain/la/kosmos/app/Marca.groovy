package la.kosmos.app

class Marca implements Serializable{

    String nombre
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_marca', params:[sequence:'marca_id_seq']
    }
}
