package la.kosmos.app

class ConfiguracionKosmos implements Serializable{

    String urlEphesoft
    String batchClassEphesoft
    String usuarioEphesoft
    String passwdEphesoft
    String pathDocumentos
    
    static constraints = {
        urlEphesoft (nullable: false)
        batchClassEphesoft (nullable: false)
        usuarioEphesoft (nullable: false)
        passwdEphesoft (nullable: false)
        pathDocumentos (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_configuracion', params:[sequence:'configuracion_id_seq']
    }
}
