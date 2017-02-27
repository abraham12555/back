package la.kosmos.app

class ServicioDeAsistencia implements Serializable{

    EntidadFinanciera entidadFinanciera
    float montoInicial
    float montoFinal
    float importeAsistencia
    int plazoAnual
    int plazoQuincenal
    int plasoSemanal
    
    static constraints = {
        entidadFinanciera (nullable: false)
    }
    
    static mapping = {
        id generator: 'sequence', column: 'id_servicio_de_asistencia', params:[sequence:'servicio_de_asistencia_id_seq']
    }
}
