package la.kosmos.app

class ParametroConsultaBuro implements Serializable{
    
    SolicitudDeCredito solicitud
    String numeroDeTarjeta
    boolean tieneTarjeta = false
    boolean tieneCreditoAutomotriz = false
    boolean tieneCreditoHipotecario = false
    
    static constraints = {
        solicitud (nullable: false)
        numeroDeTarjeta (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_parametro_consulta_buro', params:[sequence:'parametro_consulta_buro_id_seq']
    }
}
