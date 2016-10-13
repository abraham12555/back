package la.kosmos.app

import java.io.Serializable;

class EmpleoBuroDeCredito implements Serializable{
	
	String razonSocial
	String direccionLinea1
	String direccionLinea2
	String colonia
	String municipio
	String ciudad
	String estado
	String codigoPostal
	String numeroTelefonico
	String extensionTelefonica
	String numeroFax
	String cargo
	String fechaContratacion
	String claveMonedaPago
	String sueldo
	String periodoDePago
	String numeroEmpleado
	String fechaUltimoDiaEmpleo
	String fechaReporteEmpleo
	String fechaVerificacionEmpleo
	String modoVerificacion
	ReporteBuroCredito reporteBuroCredito

    static constraints = {
		 razonSocial nullable: true
		 direccionLinea1 nullable: true
		 direccionLinea2 nullable: true
		 colonia nullable: true
		 municipio nullable: true
		 ciudad nullable: true
		 estado nullable: true
		 codigoPostal nullable: true
		 numeroTelefonico nullable: true
		 extensionTelefonica nullable: true
		 numeroFax nullable: true
		 cargo nullable: true
		 fechaContratacion nullable: true
		 claveMonedaPago nullable: true
		 sueldo nullable: true
		 periodoDePago nullable: true
		 numeroEmpleado nullable: true
		 fechaUltimoDiaEmpleo nullable: true
		 fechaReporteEmpleo nullable: true
		 fechaVerificacionEmpleo nullable: true
		 modoVerificacion nullable: true
    }
	String toString(){
		"${razonSocial} ${direccionLinea1}"
	}
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_empleoBuroCredito', params:[sequence:'empleoBuroCredito_id_seq']
	}
	
}
