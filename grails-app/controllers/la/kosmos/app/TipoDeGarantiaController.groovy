package la.kosmos.app

class TipoDeGarantiaController {

def obtenerDetalleTipoDeGarantia(TipoDeGarantia tipoDeGarantia) { 
    
    render(template: "/dashboard/configuracion/tipoDeGarantia/detalleTipoDeGarantia", model: [tipoDeGarantia: tipoDeGarantia])

    }

}
