package la.kosmos.app

class ResumenBuroCredito implements Serializable{

	String fechaIntegracion
	String numeroCuentaMop00
	String numeroCuentaMop01
	String numeroCuentaMop02
	String numeroCuentaMop03
	String numeroCuentaMop04
	String numeroCuentaMop05
	String numeroCuentaMop06
	String numeroCuentaMop07
	String numeroCuentaMop96
	String numeroCuentaMop97
	String numeroCuentaMop99
	String numeroCuentaMopUR
	
	String numeroCuentas
	String cuentasPagosFijosHipotecarios
	String noCuentasRevolventes
	String noCuentasCerradas
	String noCuentasConMorosidadActual
	String noCuentasConHistorialMorosidad
	String noCuentasEnAclaracion
	String noSolicitudConsultas
	String nuevaDireccionEn60Dias
	String mensajesAlerta
	String declarativa
	String monedaCredito
	String totalCreditoMaximosCuentasRevol
	String totalLimiteCreditoCuentasRevol
	String totalSaldoActualCuentasRevol
	String totalSaldosVencidosCuentasRevol
	String totalImportePagoCuentasRevol
	String porcentajeLimiteCreditoCuentasRevol
	String totalCreditoMaximoCuentasPagosFijosHipo
	String totalSaldosActualesCuentasPagosFijosHipo
	String totalSaldoVencidoCuentasPagosFijosHipo
	String totalImporteCuentasPagosFijosHipo
	
	String fechaAperturaCuentaMasAntigua
	String fechaAperturaCuentaMasReciente
	String numeroSolicitudesInformeBuro
	String fechaConsultaMasReciente
	String numeroCuentasEnDespachoCobranza
	String fechaAperturaMasRecienteDespachoCobranza
	String numeroSolicitudesInformeBuroPorDespachoCobranza
	String fechaConsultaMasRecientePorDespachoCobranza
	ReporteBuroCredito reporteBuroCredito
	
    static constraints = {
		 fechaIntegracion nullable: true
		 numeroCuentaMop00 nullable: true
		 numeroCuentaMop01 nullable: true
		 numeroCuentaMop02 nullable: true
		 numeroCuentaMop03 nullable: true
		 numeroCuentaMop04 nullable: true
		 numeroCuentaMop05 nullable: true
		 numeroCuentaMop06 nullable: true
		 numeroCuentaMop07 nullable: true
		 numeroCuentaMop96 nullable: true
		 numeroCuentaMop97 nullable: true
		 numeroCuentaMop99 nullable: true
		 numeroCuentaMopUR nullable: true
		
		 numeroCuentas nullable: true
		 cuentasPagosFijosHipotecarios nullable: true
		 noCuentasRevolventes nullable: true
		 noCuentasCerradas nullable: true
		 noCuentasConMorosidadActual nullable: true
		 noCuentasConHistorialMorosidad nullable: true
		 noCuentasEnAclaracion nullable: true
		 noSolicitudConsultas nullable: true
		 nuevaDireccionEn60Dias nullable: true
		 mensajesAlerta nullable: true
		 declarativa nullable: true
		 monedaCredito nullable: true
		 totalCreditoMaximosCuentasRevol nullable: true
		 totalLimiteCreditoCuentasRevol nullable: true
		 totalSaldoActualCuentasRevol nullable: true
		 totalSaldosVencidosCuentasRevol nullable: true
		 totalImportePagoCuentasRevol nullable: true
		 porcentajeLimiteCreditoCuentasRevol nullable: true
		 totalCreditoMaximoCuentasPagosFijosHipo nullable: true
		 totalSaldosActualesCuentasPagosFijosHipo nullable: true
		 totalSaldoVencidoCuentasPagosFijosHipo nullable: true
		 totalImporteCuentasPagosFijosHipo nullable: true
		
		 fechaAperturaCuentaMasAntigua nullable: true
		 fechaAperturaCuentaMasReciente nullable: true
		 numeroSolicitudesInformeBuro nullable: true
		 fechaConsultaMasReciente nullable: true
		 numeroCuentasEnDespachoCobranza nullable: true
		 fechaAperturaMasRecienteDespachoCobranza nullable: true
		 numeroSolicitudesInformeBuroPorDespachoCobranza nullable: true
		 fechaConsultaMasRecientePorDespachoCobranza nullable: true
    }
	
	String toString(){
		"${numeroCuentaMop00} ${numeroCuentas} ${fechaAperturaCuentaMasAntigua} ${fechaConsultaMasReciente} ${fechaConsultaMasRecientePorDespachoCobranza}"
	}
	
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_resumenBuroCredito', params:[sequence:'resumenBuroCredito_id_seq']
	}
	
}
