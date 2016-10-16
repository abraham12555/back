package la.kosmos.app

import java.io.Serializable;

class VinculacionBanco implements Serializable{
	
	String banco
	String secuenciaSe
	Date fechaVinculacion = new Date()
	String login_id_SE
	String customer_id_SE
	StatusSaltEdge status

    static constraints = {
        banco (nullable: false)
        secuenciaSe (nullable: false)
        fechaVinculacion (nullable: false)
        login_id_SE (nullable: true)
        status (nullable: false)
	}
    
    static mapping = {
		version false
        id generator: 'sequence', column: 'id_vinculacion_banco', params:[sequence:'vinculacion_banco_id_seq']
    }
}
