package la.kosmos.app

import java.io.Serializable;

class CuentaSaltEdge implements Serializable{

	String nature
	String name
	String currency_code
	Double balance
	VinculacionBanco vinculacion
    static constraints = {
        nature (nullable: true)
        name (nullable: true)
        currency_code (nullable: true)
        balance (nullable: true)
		vinculacion (nullable: false)
		
	}
    
    static mapping = {
		version false
        id generator: 'sequence', column: 'id_cuenta_salt_edge', params:[sequence:'cuenta_salt_edge_id_seq']
    }
}
