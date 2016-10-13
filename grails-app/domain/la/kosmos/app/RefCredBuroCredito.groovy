package la.kosmos.app

import java.io.Serializable;

class RefCredBuroCredito implements Serializable {
	
	String numeroCuenta
	String claveUsuario
	String nombreUsuario
	ReporteBuroCredito reporteBuroCredito

    static constraints = {
		 numeroCuenta nullable: true
		 claveUsuario nullable: true
		 nombreUsuario nullable: true
    }
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_refcredBuroCredito', params:[sequence:'refcredBuroCredito_id_seq']
	}
}
