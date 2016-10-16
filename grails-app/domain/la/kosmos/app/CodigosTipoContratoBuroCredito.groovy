package la.kosmos.app

import java.io.Serializable;

class CodigosTipoContratoBuroCredito implements Serializable{

	String codigo
	String descripcion
	
    static constraints = {
        codigo (nullable : false)
        descripcion (nullable: false)
    }
    
    static mapping = {
		version false
        id generator: 'sequence', column: 'id_codigos_tipo_contrato_buro_credito', params:[sequence:'codigos_tipo_contrato_buro_credito_id_seq']
    }
    
    String toString () {
        "${codigo}"
    }
}
