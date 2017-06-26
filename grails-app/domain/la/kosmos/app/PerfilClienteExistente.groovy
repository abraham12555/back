package la.kosmos.app

class PerfilClienteExistente {

    String rfc
    String fechaNacimiento
    String experienciaCrediticia
    int numCredLiqdExp
    String claveProductoAnterior
    String claveProductoNuevo
    String fecAntigCliCred
    char reactivacion
    char renovacion
    String ultimaFechaCaptura
    String ultimaFechaPago
    float ultimoMontoLiberado
    float ultimoPago1
    float ultimoPago2
    float ultimoPago3
    float vectorAhorro1
    float vectorAhorro2
    float vectorAhorro3
    float vectorAhorro4
    float vectorAhorro5
    float vectorAhorro6
    float vectorAhorro7
    float vectorAhorro8
    float vectorAhorro9
    float vectorAhorro10
    float vectorAhorro11
    float vectorAhorro12
    float vectorInver1
    float vectorInver2
    float vectorInver3
    float vectorInver4
    float vectorInver5
    float vectorInver6
    float vectorInver7
    float vectorInver8
    float vectorInver9
    float vectorInver10
    float vectorInver11
    float vectorInver12
    char sexo
    Date fechaActualizacion
    
    static constraints = {
        rfc (nullable: true)
        fechaNacimiento (nullable: true)
        experienciaCrediticia (nullable: true)
        fecAntigCliCred (nullable: true)
        ultimaFechaCaptura (nullable: true)
        ultimaFechaPago (nullable: true)
    }
    
    static mapping = {
        version false
        table 'lsf_perfil_ce'
        id column: 'numero_cliente'
        rfc column: 'rfc'
        fechaNacimiento column: 'fecha_nacimiento'
        experienciaCrediticia column: 'experiencia_crediticia'
        numCredLiqdExp column: 'num_cred_liqd_exp'
        claveProductoAnterior column: 'clave_producto_anterior'
        claveProductoNuevo column: 'clave_producto_nuevo'
        fecAntigCliCred column: 'fec_antig_clicred'
        reactivacion column: 'reactivacion'
        renovacion column: 'renovacion'
        ultimaFechaCaptura column: 'ultima_fecha_captura'
        ultimaFechaPago column: 'ultima_fecha_pago'
        ultimoMontoLiberado column: 'ultimo_monto_liberado'
        ultimoPago1 column: 'ultimo_pago_1'
        ultimoPago2 column: 'ultimo_pago_2'
        ultimoPago3 column: 'ultimo_pago_3'
        vectorAhorro1 column: 'vector_ahorro_1'
        vectorAhorro2 column: 'vector_ahorro_2'
        vectorAhorro3 column: 'vector_ahorro_3'
        vectorAhorro4 column: 'vector_ahorro_4'
        vectorAhorro5 column: 'vector_ahorro_5'
        vectorAhorro6 column: 'vector_ahorro_6'
        vectorAhorro7 column: 'vector_ahorro_7'
        vectorAhorro8 column: 'vector_ahorro_8'
        vectorAhorro9 column: 'vector_ahorro_9'
        vectorAhorro10 column: 'vector_ahorro_10'
        vectorAhorro11 column: 'vector_ahorro_11'
        vectorAhorro12 column: 'vector_ahorro_12'
        vectorInver1 column: 'vector_inver_1'
        vectorInver2 column: 'vector_inver_2'
        vectorInver3 column: 'vector_inver_3'
        vectorInver4 column: 'vector_inver_4'
        vectorInver5 column: 'vector_inver_5'
        vectorInver6 column: 'vector_inver_6'
        vectorInver7 column: 'vector_inver_7'
        vectorInver8 column: 'vector_inver_8'
        vectorInver9 column: 'vector_inver_9'
        vectorInver10 column: 'vector_inver_10'
        vectorInver11 column: 'vector_inver_11'
        vectorInver12 column: 'vector_inver_12'
        sexo column: 'sexo'
        fechaActualizacion column: 'fecha_actualizacion'
        cache usage: 'read-only'
    }
}
