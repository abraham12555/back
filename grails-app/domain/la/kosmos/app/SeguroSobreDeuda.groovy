package la.kosmos.app

class SeguroSobreDeuda implements Serializable{

    EntidadFinanciera entidadFinanciera
    float montoInicial
    float montoFinal
    int plazoAnual
    float importeSeguro
    boolean porcentaje = false
    float porcentajeSeguro = 0
    
    static constraints = {
        entidadFinanciera (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_seguro_sobre_deuda', params:[sequence:'seguro_sobre_deuda_id_seq']
    }
}
