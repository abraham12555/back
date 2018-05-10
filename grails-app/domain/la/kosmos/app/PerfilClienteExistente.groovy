package la.kosmos.app

class PerfilClienteExistente implements Serializable{
   
    String rfc
    String fechaNacimiento
    String experienciaCrediticia
    int numCredLiqdExp
    String claveProductoAnterior
    String claveProductoNuevo
    String fecAntigCliCred
    String dictamenRenovacion1
    String dictamenRenovacion2
    String producto1
    String producto2
    Double montoOtorgado1
    Double montoOtorgado2
    Double avanceCapital1
    Double avanceCapital2
    String periodoCredMaxEp
    Integer plazoCredMaxEp
    Integer mesesLibrosCredito_1
    Integer mesesLibrosCredito_2
    Float montoMaxExpPositiva
    String clienteCredVigente
    String antiguedadUltimoCredito
    String atrasoPago
    String clienteRenovacion
    String malaFe
    String reactivacion
    String renovacion
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
     /* Fin Cambio*/
    /**@author Mike Martinez 
     *  15 de  enero  2018
     * Columnas para automatizacion de lineamientos 
     * */
    
    static constraints = {
        rfc (nullable: true)
        fechaNacimiento (nullable: true)
        experienciaCrediticia (nullable: true)
        fecAntigCliCred (nullable: true)
        ultimaFechaCaptura (nullable: true)
        ultimaFechaPago (nullable: true)
        antiguedadUltimoCredito(nullable: true)
        clienteCredVigente(nullable: true)
        periodoCredMaxEp(nullable: true)  
        plazoCredMaxEp(nullable: true)
    }
    
    static mapping = {
        version false
        table 'lsf_perfil_ce'
        id column: 'numero_cliente'
        cache usage: 'read-only'
    }
} 