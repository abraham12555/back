package la.kosmos.app

class ResultadoMotorDeDecision {

    SolicitudDeCredito solicitud
    BigDecimal probabilidadDeMora;
    BigDecimal razonDeCobertura;
    String dictamenDePerfil;
    String dictamenCapacidadDePago;
    String dictamenConjunto;
    String dictamenDePoliticas;
    String dictamenFinal;
    String log;
    
    static constraints = {
        solicitud (nullable: false)
        probabilidadDeMora (nullable: true)
        razonDeCobertura (nullable: true)
        dictamenDePerfil (nullable: true)
        dictamenCapacidadDePago  (nullable: true)
        dictamenConjunto (nullable: true)
        dictamenDePoliticas (nullable: true)
        dictamenFinal (nullable: true)
        log (nullable: true)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_resultado_motor_de_decision', params:[sequence:'resultado_motor_de_decision_id_seq']
    }
}
