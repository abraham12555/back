package la.kosmos.app

import grails.transaction.Transactional

@Transactional
class SolicitudService {

    def construirDatosTemporales(def params, def pasoEnviado) {
        println "PARAMETROS ENVIADOS:::"+ params +" PASO ENVIADO..." +pasoEnviado
		
        def datosPaso = [:]
        if(pasoEnviado == 1){
            datosPaso.nombre = params.nombre
            datosPaso.apellidoPaterno = params.apellidoPaterno
            datosPaso.apellidoMaterno = params.apellidoMaterno
            datosPaso.sexo =  (params.sexo ? params.sexo as long : null)
            datosPaso.dia = params.dia
            datosPaso.mes = params.mes
            datosPaso.anio = params.anio
            datosPaso.entidad = (params.estado ? params.estado as long : null)
            datosPaso.estadoCivil = (params.estadoCivil ? params.estadoCivil as long : null)
            datosPaso.nivelEscolar = (params.nivelEscolar ? params.nivelEscolar as long : null)
            datosPaso.numeroCasa = params.telefono
            datosPaso.numeroCelular = params.celular
            datosPaso.rfc = params.rfc
            datosPaso.curp = params.curp
            datosPaso.conyugue = params.nombreConyugue
            datosPaso.dependientes = (params.dependientes ? params.dependientes as int : null)
        } else if (pasoEnviado == 2){
            datosPaso.calle = params.calle
            datosPaso.noExterior = params.noExterior
            datosPaso.noInterior = params.noInterior
            datosPaso.colonia = params.colonia
            datosPaso.codigoPostal = params.codigoPostal
            datosPaso.tipoDelegacion = params.tipoDelegacion
            datosPaso.municipio = (params.municipio ? params.municipio as long : null)
            datosPaso.estado = (params.estado ? params.estado as long : null)
            datosPaso.tipoDeVivienda = (params.tipoDeVivienda ?  params.tipoDeVivienda as long : null)
            datosPaso.tiempo = params.tiempo
            datosPaso.temporalidad = (params.temporalidad ? params.temporalidad as long : null)
        } else if (pasoEnviado == 3){
            datosPaso.empresa = params.empresa
            datosPaso.puesto = params.puesto
            datosPaso.periodo = (params.noPeriodo ? params.noPeriodo : null)
            datosPaso.plazo = (params.plazo ? params.plazo : null)
            datosPaso.contrato = (params.contrato ? params.contrato : null)
        } else if (pasoEnviado == 4){
            datosPaso.depositoPromedio = (params.depositos ? params.depositos :null)
            datosPaso.retiroPromedio = (params.retiros ? params.retiros :null)
            datosPaso.saldoPromedio = (params.saldo ? params.saldo :null)
            datosPaso.login_id = params.login_id
            datosPaso.depositoCorrecto=(params.depositoCorrecto ? params.depositoCorrecto:null)
            datosPaso.retiroCorrecto=(params.retiroCorrecto ? params.retiroCorrecto:null)
            datosPaso.saldoCorrecto=(params.saldoCorrecto ? params.saldoCorrecto:null)
	    } else if (pasoEnviado == 5){
			datosPaso.tCredito=(params.tCredito ? params.tCredito:null)
			datosPaso.creditoA=(params.creditoA ? params.creditoA:null)
			datosPaso.creditoH=(params.creditoH ? params.creditoH:null)
        } else if (pasoEnviado == 6){
            
        }
        return datosPaso
    }
    
    def controlDeDocumentos(listaDeDoctos, tipoDeDocumentoEnviado){
        if(!listaDeDoctos){
            listaDeDoctos = [:]
            listaDeDoctos.comprobanteDeDomicilio = false
            listaDeDoctos.identificacion = false
        }
        if(tipoDeDocumentoEnviado == "UtilityBill"){
            listaDeDoctos.comprobanteDeDomicilio = true
        } else if (tipoDeDocumentoEnviado == "Pasaportes" || tipoDeDocumentoEnviado == "Identicaciones"){
            listaDeDoctos.identificacion = true
        }
        return listaDeDoctos
    }
}
