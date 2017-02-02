package la.kosmos.app

class RegimenMatrimonial implements Serializable{

    String nombre
    char clave
    boolean activo = true
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_regimen_matrimonial', params:[sequence:'regimen_matrimonial_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
