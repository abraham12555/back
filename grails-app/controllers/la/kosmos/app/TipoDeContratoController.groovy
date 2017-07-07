package la.kosmos.app

class TipoDeContratoController {

def obtenerDetalleTipoDeContrato(TipoDeContrato tipoDeContrato) { 
    
    render(template: "/dashboard/configuracion/tipoDeContrato/detalleTipoDeContrato", model: [tipoDeContrato: tipoDeContrato])

    }

}
