package la.kosmos.app

class TipoDeTasaDeInteresController {

    def obtenerDetalleTipoDeTasaDeInteres(TipoDeTasaDeInteres tipoDeTasaDeInteres) { 
    
    render(template: "/dashboard/configuracion/tipoDeTasaDeInteres/detalleDeTipoDeTasaDeInteres", model: [tipoDeTasaDeInteres: tipoDeTasaDeInteres])

    }


}
