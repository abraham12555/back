package la.kosmos.app

import grails.transaction.Transactional

@Transactional
class SolicitudService {

    def construirDatosTemporales(def params, def pasoEnviado) {
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
            datosPaso.delegacion = (params.delegacion ? params.delegacion as long : null)
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
            
        } else if (pasoEnviado == 5){
            
        } else if (pasoEnviado == 6){
            
        }
        return datosPaso
    }
}
