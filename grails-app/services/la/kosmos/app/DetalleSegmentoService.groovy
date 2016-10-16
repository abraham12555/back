package la.kosmos.app

import grails.transaction.Transactional
import grails.converters.JSON
import groovy.json.*

import java.text.SimpleDateFormat

import grails.transaction.Transactional

@Transactional
class DetalleSegmentoService {
    def historialDeCredito(def solicitud_id){
		def segmentoHistorialDeCredito = [:]
		def reporteBuro = [:]
		SolicitudDeCredito solicitud = SolicitudDeCredito.get(solicitud_id)
		ResumenBuroCredito resumenBuroCredito = ResumenBuroCredito.findByReporteBuroCredito(solicitud?.reporteBuroCredito)
		ScoreBuroCredito score = ScoreBuroCredito.findByReporteBuroCredito(solicitud?.reporteBuroCredito)
		List<AlertaBuroCredito> alertas = AlertaBuroCredito.findAllByReporteBuroCreditoAndTipoUsuario(solicitud?.reporteBuroCredito,"INF DE JUICIOS   ")
		List<CreditoClienteBuroCredito> creditos = CreditoClienteBuroCredito.findAllByReporteBuroCredito(solicitud?.reporteBuroCredito)
		
		reporteBuro.juicios=alertas.size()
		//Score
		reporteBuro.score = score?.valorScore
		//MOP
		reporteBuro.totalSaldoActual = calcularValor(resumenBuroCredito.totalSaldosActualesCuentasPagosFijosHipo) + calcularValor(resumenBuroCredito.totalSaldoActualCuentasRevol)
		reporteBuro.totalSaldosVencidos = calcularValor(resumenBuroCredito.totalSaldoVencidoCuentasPagosFijosHipo) + calcularValor(resumenBuroCredito.totalSaldosVencidosCuentasRevol)
		reporteBuro.numeroSolicitudesInformeBuro = resumenBuroCredito.numeroSolicitudesInformeBuro
		reporteBuro.numeroCuentas = resumenBuroCredito.numeroCuentas
		reporteBuro.pagoARealizar = calcularValor(resumenBuroCredito.totalImporteCuentasPagosFijosHipo) + calcularValor(resumenBuroCredito.totalImportePagoCuentasRevol)
		
		if(calcularValor(resumenBuroCredito.totalCreditoMaximoCuentasPagosFijosHipo) > calcularValor(resumenBuroCredito.totalCreditoMaximosCuentasRevol)){
			reporteBuro.creditoMasAlto = resumenBuroCredito.totalCreditoMaximoCuentasPagosFijosHipo
		}else{
			reporteBuro.creditoMasAlto = resumenBuroCredito.totalCreditoMaximosCuentasRevol
		}
		
		def prestamos = []
		def prestamosPorTipoCredito = []
		creditos.each { credito ->
			def datosPrestamo = [:]
			def datosPrestamoPorTipoCredito = [:]
			datosPrestamo.name = credito.nombreUsuario
			
			CodigosTipoContratoBuroCredito codigo = CodigosTipoContratoBuroCredito.findByCodigo(credito?.tipoContratoProducto?.trim())
			datosPrestamoPorTipoCredito.name = codigo.descripcion
			
			//datosPrestamo.tipoDeCuenta = credito.tipoDeCuenta
			if(reporteBuro.totalSaldoActual > 0){
				datosPrestamo.y = (calcularValor(credito.saldoActual) / reporteBuro.totalSaldoActual) * 100
				datosPrestamoPorTipoCredito.y = datosPrestamo.y
			}else{
				datosPrestamo.y = 0
				datosPrestamoPorTipoCredito.y = 0
			}
			prestamos.add(datosPrestamo)
			prestamosPorTipoCredito.add(datosPrestamoPorTipoCredito)
			
			if(credito.totalPagosConMop05oMayor != null){
				if(reporteBuro.mopMasAlto <= 5){
					reporteBuro.mopMasAltoDesc = "Mop5oMayor"
					reporteBuro.mopMasAlto = 5
					reporteBuro.credito=credito.id
					reporteBuro.destino = credito.nombreUsuario
					
				}
			}else if(credito.totalPagosConMop04 != null){
				if(reporteBuro.mopMasAlto <= 4){
					reporteBuro.mopMasAltoDesc = "Mop04"
					reporteBuro.mopMasAlto = 4
					reporteBuro.credito=credito.id
					reporteBuro.destino = credito.nombreUsuario
				}
			}else if(credito.totalPagosConMop03 != null){
				if(reporteBuro.mopMasAlto <= 3){
					reporteBuro.mopMasAltoDesc = "Mop03"
					reporteBuro.mopMasAlto = 3
					reporteBuro.credito=credito.id
					reporteBuro.destino = credito.nombreUsuario
				}
			}else if(credito.totalPagosConMop02 != null){
				if(reporteBuro.mopMasAlto <= 2){
					reporteBuro.mopMasAltoDesc = "Mop02"
					reporteBuro.mopMasAlto = 2
					reporteBuro.credito=credito.id
					reporteBuro.destino = credito.nombreUsuario
				}
			}
		}
		
		
		/*
		MOP-00 ó símplemente 0 = Cuenta muy reciente para ser calificada.
		MOP-01 ó símplemente 1 = Cuenta con pago Puntual y adecuado
		MOP-02 o símplemente 2 = Cuenta con Atraso de 1 a 29 días.
		MOP-03 ó símplemente 3 = Cuenta con Atraso de 30 a 59 días.
		MOP-04 o símplemente 4 = Cuenta con Atraso de 60 a 89 días.
		MOP-05 ó símplemente 5 = Cuenta con Atraso de 90 a 119 días.
		MOP-06 o símplemente 6 = Cuenta con Atraso de 120 a 149 días.
		MOP-07 o símplemente 7 = Cuenta con Atraso de 150 a 365 días.
		MOP-96 o símplemente 96= Cuenta con Atraso de más de 12 meses.
		*/
		
		//int cuentaMop00 = Integer.parseInt(resumenBuroCredito.numeroCuentaMop00)
		def graficaMop = [:]
		int cuentaMop01 = calcularValor(resumenBuroCredito.numeroCuentaMop01)
		int cuentaMop02 = calcularValor(resumenBuroCredito.numeroCuentaMop02)
		int cuentaMop03 = calcularValor(resumenBuroCredito.numeroCuentaMop03)
		int cuentaMop04 = calcularValor(resumenBuroCredito.numeroCuentaMop04)
		int cuentaMop05 = calcularValor(resumenBuroCredito.numeroCuentaMop05)
		int cuentaMop06 = calcularValor(resumenBuroCredito.numeroCuentaMop06)
		int cuentaMop07 = calcularValor(resumenBuroCredito.numeroCuentaMop07)
		int cuentaMop96 = calcularValor(resumenBuroCredito.numeroCuentaMop96)
		int cuentaMop97 = calcularValor(resumenBuroCredito.numeroCuentaMop97)
		int cuentaMop99 = calcularValor(resumenBuroCredito.numeroCuentaMop99)
		//int cuentaMopUR = Integer.parseInt(resumenBuroCredito.numeroCuentaMopUR)
		int totalMops = cuentaMop01 + cuentaMop02+ cuentaMop03+ cuentaMop04+ cuentaMop05+ cuentaMop06+ cuentaMop07+ cuentaMop96+ cuentaMop97+ cuentaMop99
		if(totalMops != 0){
			graficaMop.totalMops = totalMops
			graficaMop.cuentaMop01 = cuentaMop01
			graficaMop.porcMop01 = (cuentaMop01 / totalMops)*100
			
			graficaMop.cuentaMop02 = cuentaMop02
			graficaMop.porcMop02 = (cuentaMop02 / totalMops)*100
			
			graficaMop.cuentaMop03 = cuentaMop03
			graficaMop.porcMop03 = (cuentaMop03 / totalMops)*100
			
			graficaMop.cuentaMop04 = cuentaMop04
			graficaMop.porcMop04 = (cuentaMop04 / totalMops)*100
			
			graficaMop.cuentaMop05 = cuentaMop05
			graficaMop.porcMop05 = (cuentaMop05 / totalMops)*100
			
			graficaMop.cuentaMop06 = cuentaMop06
			graficaMop.porcMop06 = (cuentaMop06 / totalMops)*100
			
			graficaMop.cuentaMop07 = cuentaMop07
			graficaMop.porcMop07 = (cuentaMop07 / totalMops)*100
			
			graficaMop.cuentaMop96 = cuentaMop96
			graficaMop.porcMop96 = (cuentaMop96 / totalMops)*100
			
			graficaMop.cuentaMop97 = cuentaMop97
			graficaMop.porcMop97 = (cuentaMop97 / totalMops)*100
			
			graficaMop.cuentaMop99 = cuentaMop99
			graficaMop.porcMop99 = (cuentaMop99 * totalMops)*100
		}else{
			graficaMop.totalMops = totalMops
			graficaMop.cuentaMop01 = cuentaMop01
			graficaMop.porcMop01 = 0
			
			graficaMop.cuentaMop02 = cuentaMop02
			graficaMop.porcMop02 = 0
			
			graficaMop.cuentaMop03 = cuentaMop03
			graficaMop.porcMop03 = 0
			
			graficaMop.cuentaMop04 = cuentaMop04
			graficaMop.porcMop04 = 0
			
			graficaMop.cuentaMop05 = cuentaMop05
			graficaMop.porcMop05 = 0
			
			graficaMop.cuentaMop06 = cuentaMop06
			graficaMop.porcMop06 = 0
			
			graficaMop.cuentaMop07 = cuentaMop07
			graficaMop.porcMop07 = 0
			
			graficaMop.cuentaMop96 = cuentaMop96
			graficaMop.porcMop96 = 0
			
			graficaMop.cuentaMop97 = cuentaMop97
			graficaMop.porcMop97 = 0
			
			graficaMop.cuentaMop99 = cuentaMop99
			graficaMop.porcMop99 = 0
		}
		
		println "GRAFICA MOP +"+ graficaMop
		reporteBuro.graficaMop = graficaMop
		
		//String graficaMopJ = new JsonBuilder(graficaMop).toPrettyString()
		
		def graficaIngvsPagosBuro = [:]
		
		List<CuentaSaltEdge> cuentas = CuentaSaltEdge.findAllByVinculacion(solicitud?.vinculacionBanco)
		graficaIngvsPagosBuro.totalIngreso = new Double("0")
		if(cuentas.size()>0){
			//Se tienen cuentas vinculadas en saltedge
			cuentas.each{ cuenta ->
				if(cuenta.nature == "account" || cuenta.nature == "checking"){
					List<TransaccionSaltEdge> transacciones = TransaccionSaltEdge.findAllByAccount(cuenta)
					if(transacciones.size()>0){
						transacciones.each{ transaccion ->
							if(transaccion.amount > 0){
								graficaIngvsPagosBuro.totalIngreso += transaccion.amount
								
							}
						}
					}
				}
			}
		}else{
			//Se debe tomar el dato que proporcione el solicitante
		}
		
		graficaIngvsPagosBuro.totalPago = Integer.parseInt(resumenBuroCredito.totalImporteCuentasPagosFijosHipo.trim()) + Integer.parseInt(resumenBuroCredito.totalImportePagoCuentasRevol.trim())
		//String graficaIngvsPagosBuroJ = new JsonBuilder(graficaIngvsPagosBuro).toPrettyString()
		
		//String graficaDesglocePrestamosJ = new JsonBuilder(prestamos).toPrettyString()
		//String graficaDesglocePrestamosPorTipoCredito = new JsonBuilder(prestamosPorTipoCredito).toPrettyString()
		
		
		segmentoHistorialDeCredito.reporteBuro = reporteBuro
		segmentoHistorialDeCredito.graficaMopJ = new JsonBuilder(graficaMop).toPrettyString()
		segmentoHistorialDeCredito.graficaIngvsPagosBuroJ = new JsonBuilder(graficaIngvsPagosBuro).toPrettyString()
		segmentoHistorialDeCredito.graficaDesglocePrestamosJ = new JsonBuilder(prestamos).toPrettyString()
		segmentoHistorialDeCredito.graficaDesglocePrestamosPorTipoCreditoJ = new JsonBuilder(prestamosPorTipoCredito).toPrettyString()
		
		return segmentoHistorialDeCredito
	}
	
	def capacidadPago(def solicitud_id){
		def segmentoCapacidadDePago = [:]
		def reporteBuro = [:]
		SolicitudDeCredito solicitud = SolicitudDeCredito.get(solicitud_id)
		
		//PENDIENTE FALTA VERIFICAR COMO TOMAR LOS DATOS.
		
		return segmentoCapacidadDePago
		
	}
	
	int calcularValor(String dato){
		int valor=0
		if(dato != null){
			String valorCadena = dato.trim()
			try{
				if(valorCadena.contains('+') ){
					String temp = valorCadena.substring(0,valorCadena.length()-1)
					valor = Integer.parseInt(temp)
				}else if(valorCadena.contains('-')){
					String temp = valorCadena.substring(0,valorCadena.length()-1)
					valor = Integer.parseInt(temp) * -1
				}else{
					valor = Integer.parseInt(valorCadena)
				
				}
			}catch(Exception e){
				println "Exception. DashboardController.CalcularValor:"+e
			}
		}
		return valor
	}
}
