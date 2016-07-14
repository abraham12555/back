package la.kosmos.app

class CotizadorController {

    def index() {         
        [ productos : Producto.findAll() ]
    }

    def procesar () {

        println params.productElement
        println params.modelElement
        println params.colorElement
        println params.financiamientoElement
        println params.periodoElement
        println params.plazoElement
        println params.seguroElement

    }
}