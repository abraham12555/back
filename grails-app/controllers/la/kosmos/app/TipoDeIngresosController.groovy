package la.kosmos.app

class TipoDeIngresosController {

def obtenerDetalleTipoDeIngresos(TipoDeIngresos tipoDeIngresos) { 
    
    render(template: "/dashboard/configuracion/tipoDeIngresos/detalleTipoDeIngresos", model: [tipoDeIngresos: tipoDeIngresos])

    }

}
