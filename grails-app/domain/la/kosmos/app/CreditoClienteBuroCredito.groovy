package la.kosmos.app

import java.io.Serializable;

class CreditoClienteBuroCredito implements Serializable{

	String fechaActualizacion
	String registroImpugnado
	String claveUsuario
	String nombreUsuario
	String numeroTelefonoUsuario
	String numeroDeCuenta
	String tipoResponsabilidadCuenta
	String tipoDeCuenta
	String tipoContratoProducto
	String monedaCredito
	String importeDelAvaluo
	String numeroPagos
	String frecuenciaDePagos
	String montoAPagar
	String fechaAperturaDeCuenta
	String fechaUltimoPago
	String fechaUltimaCompra
	String fechaCierre
	String fechaDeReporteDeInformacion
	String montoAReportar
	String ultimaFechaConSaldoEnCero
	String garantia
	String creditoMaximoAutorizado
	String saldoActual
	String limiteCredito
	String saldoVencido
	String numeroDePagosVencidos
	String clasificacionPuntialidadPago
	String historicoPagos
	String fechaMasRecienteHistoricoDePagos //MOP
	String fechaMasAntiguaHistoricoDePagos 
	String claveDeObservacion
	String totalPagosReportados
	String totalPagosConMop02
	String totalPagosConMop03
	String totalPagosConMop04
	String totalPagosConMop05oMayor
	String saldoEnlaMorosidadHistoricaMasAlta
	String fechaEnlaMorosidadHistoricaMasAlta
	String clasificacionPuntualidadPagoMopMorosidadMasAlta
	String fechaInicioRestructura
	String montoUltimoPago
	ReporteBuroCredito reporteBuroCredito
	
    static constraints = {
		 fechaActualizacion nullable: true
		 registroImpugnado nullable: true
		 claveUsuario nullable: true
		 nombreUsuario nullable: true
		 numeroTelefonoUsuario nullable: true
		 numeroDeCuenta nullable: true
		 tipoResponsabilidadCuenta nullable: true
		 tipoDeCuenta nullable: true
		 tipoContratoProducto nullable: true
		 monedaCredito nullable: true
		 importeDelAvaluo nullable: true
		 numeroPagos nullable: true
		 frecuenciaDePagos nullable: true
		 montoAPagar nullable: true
		 fechaAperturaDeCuenta nullable: true
		 fechaUltimoPago nullable: true
		 fechaUltimaCompra nullable: true
		 fechaCierre nullable: true
		 fechaDeReporteDeInformacion nullable: true
		 montoAReportar nullable: true
		 ultimaFechaConSaldoEnCero nullable: true
		 garantia nullable: true
		 creditoMaximoAutorizado nullable: true
		 saldoActual nullable: true
		 limiteCredito nullable: true
		 saldoVencido nullable: true
		 numeroDePagosVencidos nullable: true
		 clasificacionPuntialidadPago nullable: true
		 historicoPagos nullable: true
		 fechaMasRecienteHistoricoDePagos nullable: true
		 fechaMasAntiguaHistoricoDePagos nullable: true
		 claveDeObservacion nullable: true
		 totalPagosReportados nullable: true
		 totalPagosConMop02 nullable: true
		 totalPagosConMop03 nullable: true
		 totalPagosConMop04 nullable: true
		 totalPagosConMop05oMayor nullable: true
		 saldoEnlaMorosidadHistoricaMasAlta nullable: true
		 fechaEnlaMorosidadHistoricaMasAlta nullable: true
		 clasificacionPuntualidadPagoMopMorosidadMasAlta nullable: true
		 fechaInicioRestructura nullable: true
		 montoUltimoPago nullable: true

    }
	
	String toString(){
		"${monedaCredito} ${tipoDeCuenta}"
	}
	static mapping = {
		version false
		id generator: 'sequence', column: 'id_creditoBuroCredito', params:[sequence:'creditoBuroCredito_id_seq']
	}
	
}
