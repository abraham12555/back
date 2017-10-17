package la.kosmos.app

class TipoErrorBuroCredito implements Serializable {
	
	String tipo;
	String nombre;
	String numeroCampo;
	String descripcion;
        boolean visible = false;

    static constraints = {
		tipo nullable: false
		nombre nullable: false
		numeroCampo nullable: false
		descripcion nullable: true
	}
	
	String toString(){
		"${tipo} ${nombre}"
	}
	
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_tipoErrorBuroCredito', params:[sequence:'tipoErrorBuroCredito_id_seq']
	}
}