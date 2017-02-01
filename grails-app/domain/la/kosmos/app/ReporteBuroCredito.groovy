package la.kosmos.app

import java.io.Serializable;

class ReporteBuroCredito implements Serializable{

	
	String apellidoPaterno
	String apellidoMaterno
	String apellidoAdicional
	String primerNombre
	String segundoNombre
	String fechaNacimiento
	String rfc
	String prefijoProfesinal
	String sufijoPersonal
	String nacionalidad
	String tipoResidencia
	String numeroLicenciaConducir
	String estadoCivil
	String genero
	String numeroCedulaProfesional
	String numeroIFE
	String curp
	String numeroDependientes
	String edadDependientes
	String fechaDefuncionCliente
	Date fechaConsulta = new Date()
	String errorConsulta
	String referenciaOperadorUR
	String referenciaOperadorAR
	TipoErrorBuroCredito tipoErrorBuroCredito
	static constraints = {
		 apellidoPaterno nullable: true
		 apellidoPaterno nullable: true
		 apellidoMaterno nullable: true
		 apellidoAdicional nullable: true
		 primerNombre nullable: true
		 segundoNombre nullable: true
		 fechaNacimiento nullable: true
		 rfc nullable: true
		 prefijoProfesinal nullable: true
		 sufijoPersonal nullable: true
		 nacionalidad nullable: true
		 tipoResidencia nullable: true
		 numeroLicenciaConducir nullable: true
		 estadoCivil nullable: true
		 genero nullable: true
		 numeroCedulaProfesional nullable: true
		 numeroIFE nullable: true
		 curp nullable: true
		 numeroDependientes nullable: true
		 edadDependientes nullable: true
		 fechaDefuncionCliente nullable: true
		 errorConsulta nullable: true
		 referenciaOperadorUR nullable: true
		 referenciaOperadorAR nullable: true
		 tipoErrorBuroCredito nullable: true
	}
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_reporteBuroCredito', params:[sequence:'reporteBuroCredito_id_seq']
	}
	

	String toString(){
		"${apellidoPaterno} ${apellidoMaterno} ${apellidoAdicional} ${primerNombre} ${segundoNombre} ${fechaNacimiento} ${rfc} ${prefijoProfesinal} ${sufijoPersonal}"
		//return "ReporteBuroDeCredito: ApellidoPaterno "+ this.apellidoPaterno+	"  ApellidoMaterno "+ this.apellidoMaterno+	"  ApellidoAdicional "+ this.apellidoAdicional+	"  PrimerNombre "+ this.primerNombre+	"  SegundoNombre "+ this.segundoNombre+	"  RFC "+ this.rfc+	"  PrefijoProfesinal "+ this.prefijoProfesinal+	"  SufijoPersonal "+ this.sufijoPersonal+	"  Nacionalidad "+ this.nacionalidad+	"  TipoResidencia "+ this.tipoResidencia+	"  NumeroLicenciaConducir "+ this.numeroLicenciaConducir+	"  EstadoCivil "+ this.estadoCivil+	"  Genero "+ this.genero+	"  NumeroCedulaProfesional "+ this.numeroCedulaProfesional+	"  NumeroIFE "+ this.numeroIFE+	"  CURP "+ this.curp+	"  NumeroDependientes "+ this.numeroDependientes+	"  EdadDependientes "+ this.edadDependientes+	"  FechaDefuncionCliente "+ this.fechaDefuncionCliente
	}
}
