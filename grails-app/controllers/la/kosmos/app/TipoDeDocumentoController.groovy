package la.kosmos.app
import grails.converters.JSON

class TipoDeDocumentoController {


def obtenerDetalleTipoDeDocumento(TipoDeDocumento tipoDeDocumento){

        render(template: "/dashboard/configuracion/tipoDeDocumento/detalleTipoDeDocumento", model: [tipoDeDocumento: tipoDeDocumento])

    }
}
