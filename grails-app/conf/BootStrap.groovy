import la.kosmos.app.EntidadFinanciera
import la.kosmos.app.Marca
import la.kosmos.app.Producto
import la.kosmos.app.TipoDeProducto

class BootStrap {

    def init = { servletContext ->
        /*def marca = new Marca(nombre:"Nissan").save(flush: true)
        def entidadFinanciera = new EntidadFinanciera(nombre: "CrediNissan").save(flush: true)
        def tipoDeProducto = new TipoDeProducto(nombre: "Automóviles").save(flush: true);

        new Producto(nombreDelProducto: "Maxima", marca: marca, entidadFinanciera: entidadFinanciera, tipoDeProducto: tipoDeProducto, activo: true, rutaImagenDefault: "/").save(flush: true);
        new Producto(nombreDelProducto: "Altima", marca: marca, entidadFinanciera: entidadFinanciera, tipoDeProducto: tipoDeProducto, activo: true, rutaImagenDefault: "/").save(flush: true);
        new Producto(nombreDelProducto: "Sentra", marca: marca, entidadFinanciera: entidadFinanciera, tipoDeProducto: tipoDeProducto, activo: true, rutaImagenDefault: "/").save(flush: true);
        new Producto(nombreDelProducto: "March", marca: marca, entidadFinanciera: entidadFinanciera, tipoDeProducto: tipoDeProducto, activo: true, rutaImagenDefault: "/").save(flush: true);
        new Producto(nombreDelProducto: "X-Trail", marca: marca, entidadFinanciera: entidadFinanciera, tipoDeProducto: tipoDeProducto, activo: true, rutaImagenDefault: "/").save(flush: true);
        */
    }
    def destroy = {

    }
}
