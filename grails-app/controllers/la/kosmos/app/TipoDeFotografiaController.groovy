package la.kosmos.app

class TipoDeFotografiaController {



def obtenerDetalleTipoDeFotografia(TipoDeFotografia tipoDeFotografia) { 
    
    render(template: "/dashboard/configuracion/tipoDeFotografia/detalleTipoDeFotografia", model: [tipoDeFotografia: tipoDeFotografia])

    }
}
