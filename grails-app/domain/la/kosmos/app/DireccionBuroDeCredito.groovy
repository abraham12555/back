package la.kosmos.app

import java.io.Serializable;

class DireccionBuroDeCredito implements Serializable{
	
	String direccionPrimeraLinea
	String direccionSegundaLinea
	String colonia
	String municipio
	String ciudad
	String estado
	String codigoPostal
	String fechaResidencia
	String numeroTelefono
	String extensionTelefono
	String numeroFax
	String tipoDomicilio
	String indicadorEspecialDomicilio
	//String fechaDeReporteDireccion
	ReporteBuroCredito reporteBuroCredito

    static constraints = {
		 direccionPrimeraLinea  nullable: true
		 direccionSegundaLinea  nullable: true
		 colonia  nullable: true
		 municipio  nullable: true
		 ciudad  nullable: true
		 estado  nullable: true
		 codigoPostal  nullable: true
		 fechaResidencia  nullable: true
		 numeroTelefono  nullable: true
		 extensionTelefono  nullable: true
		 numeroFax  nullable: true
		 tipoDomicilio  nullable: true
		 indicadorEspecialDomicilio  nullable: true
    }
	
	String toString(){
		"${direccionPrimeraLinea} ${direccionSegundaLinea}"
	}
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_direccionBuroCredito', params:[sequence:'direccionBuroCredito_id_seq']
	}
	
}
