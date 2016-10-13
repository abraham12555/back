package la.kosmos.app

import java.io.Serializable;

class ConsultasBuroCredito implements Serializable{

	String fechaConsulta
	String claveDelUsuario
	String nombreDelUsuario
	String numeroDeTelefono
	String tipoDeContrato
	String monedaDelCredito
	String importeDelContrato
	String tipoResponsabilidadCuenta
	String indicadorDeClienteNuevo
	ReporteBuroCredito reporteBuroCredito
	
    static constraints = {
		fechaConsulta nullable: true
		claveDelUsuario nullable: true
		nombreDelUsuario nullable: true
		numeroDeTelefono nullable: true
		tipoDeContrato nullable: true
		monedaDelCredito nullable: true
		importeDelContrato nullable: true
		tipoResponsabilidadCuenta nullable: true
		indicadorDeClienteNuevo nullable: true
    }
	
	String toString(){
		"${fechaConsulta}"
	}
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_consultasBuroCredito', params:[sequence:'consultasBuroCredito_id_seq']
	}
}
