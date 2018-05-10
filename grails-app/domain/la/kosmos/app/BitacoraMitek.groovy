package la.kosmos.app

class BitacoraMitek {
    String folio
    Date fechaConsulta 
    Boolean tuvoExito
    Boolean tuvoError
    String descripcionError
    String estatus
    double tiempoEnContestar
    static constraints = {
         tuvoExito nullable: true
         tuvoError nullable : true
         descripcionError nullable : true
         folio nullable: true
         estatus nullable : true
         tiempoEnContestar nullable: true
         fechaConsulta nullable : true
    }
    static mapping = {
        version false
        id generator: 'sequence', column: 'id_bitacoramitek', params:[sequence:'bitacoramitek_id_seq']
    }
}
