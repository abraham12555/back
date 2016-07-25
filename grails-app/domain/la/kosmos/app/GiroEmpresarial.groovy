package la.kosmos.app

class GiroEmpresarial implements Serializable{

    String nombre
    
    static constraints = {
        nombre (blank: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_giro_empresarial', params:[sequence:'giro_empresarial_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
