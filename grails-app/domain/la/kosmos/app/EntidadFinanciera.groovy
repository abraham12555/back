package la.kosmos.app

class EntidadFinanciera implements Serializable{

    String nombre
    Date fechaDeRegistro = new Date()
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
        fechaDeRegistro (nullable: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_entidad_financiera', params:[sequence:'entidad_financiera_id_seq']
    }    
}
