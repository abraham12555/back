import la.kosmos.app.EntidadFinanciera
import la.kosmos.app.Marca
import la.kosmos.app.Producto
import la.kosmos.app.TipoDeProducto
import la.kosmos.app.security.CustomSessionListener

class BootStrap {

    def sequenceGeneratorService
    def configuracionNotificacionesService
    def userSessionService

    def init = { servletContext ->
        sequenceGeneratorService.initSequence('CUSTOMER', null, null, 200, 'KD-%05d')
        sequenceGeneratorService.initSequence('REFERENCIA_LIB', null, null, 200, 'LIB-%021d')

        configuracionNotificacionesService.startScheduler();

        servletContext.addListener(CustomSessionListener)

        userSessionService.deleteAllUserSessions()
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
