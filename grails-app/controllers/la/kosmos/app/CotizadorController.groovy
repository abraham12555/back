package la.kosmos.app

import grails.converters.JSON

class CotizadorController {

    def index() {
        [productos: Producto.findAllByActivo(true)]
    }

    def obtenerProducto() {
        Producto producto = Producto.get(params.id as long)
        JSON.use('deep') {
            render(producto as JSON)
        }
    }

    def obtenerModelos () {
        def modelos = Modelo.findAllByProductoAndActivo(Producto.get(params.productoId as long), true)
        render ( template: 'modelosList', model: [modelosList: modelos])
    }

    def obtenerColores() {
        def colores = ColorModelo.findAllByModeloAndActivo(Modelo.get(params.modeloId as long), true)
        render ( template: 'coloresList', model: [coloresList: colores])
    }

    def procesar() {

        println params.productElement
        println params.modelElement
        println params.colorElement
        println params.financiamientoElement
        println params.periodoElement
        println params.plazoElement
        println params.seguroElement

        redirect(controller: "solicitud", action: "login")
    }
}