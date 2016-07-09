package la.kosmos.app

class CotizadorController {

    def index() {         
        [ productos : Producto.findAll() ]
    }
}
