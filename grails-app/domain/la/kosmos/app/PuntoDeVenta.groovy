package la.kosmos.app

class PuntoDeVenta implements Serializable{

    String nombre
    Date fechaDeRegistro = new Date()
    EntidadFinanciera entidadFinanciera
    boolean activa = true
    
    static constraints = {
        nombre (blank:false)
        fechaDeRegistro (nullable: false)
        entidadFinanciera (nullable: false)
    }

    static mapping = {
        id generator: 'sequence', column: 'id_punto_de_venta', params:[sequence:'punto_de_venta_id_seq']
    }
    
    String toString () {
        "${nombre}"
    }
}
