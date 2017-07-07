package la.kosmos.app

class TipoDeCampoController {


    def obtenerDetalleTipoDeCampo(TipoDeCampo tipoDeCampo) { 
    
    render(template: "/dashboard/configuracion/tipoDeCampo/detalleTipoDeCampo", model: [tipoDeCampo: tipoDeCampo])

    }

}
