package la.kosmos.app

import grails.converters.JSON

class ProductoController {

    //static scaffold = Producto

    def obtenerDetalleProducto(Producto producto){
        println params
        def documentosProducto
        def rubrosDeAplicacion
        def plazoProducto
        def garantiaProducto
        def modelos
        def colores
        if(session.configuracion?.aplicacionVariable){
            documentosProducto = DocumentoProducto.findAllWhere(producto: producto)
            rubrosDeAplicacion = RubroDeAplicacionProducto.findAllWhere(producto: producto)
            plazoProducto = PlazoProducto.findAllWhere(producto: producto)
            garantiaProducto = GarantiaProducto.findAllWhere(producto: producto)
        } else {
            modelos = Modelo.findAllWhere(producto: producto)
            colores = ColorModelo.findAllByModeloInList(modelos)
        }
        render(template: "/dashboard/configuracion/producto/detalleProducto", model: [producto: producto, documentosProducto: documentosProducto, rubrosDeAplicacion: rubrosDeAplicacion, plazoProducto: plazoProducto, garantiaProducto: garantiaProducto, modelos: modelos, colores: colores])
    }
}
