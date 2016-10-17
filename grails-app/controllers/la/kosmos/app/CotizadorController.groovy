package la.kosmos.app

import grails.converters.JSON

class CotizadorController {

    def index() {
        session.cotizador = null
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

        println params
        session.cotizador = [:]
        session.cotizador.modelo = params.txtModelo as long
        session.cotizador.color = params.txtColor as long
        session.cotizador.enganche = params.txtEnganche as float
        session.cotizador.plazo = params.txtPlazo as long
        session.cotizador.periodo = params.txtPeriodo as long
        redirect(controller: "solicitud", action: "formulario")
    }
}