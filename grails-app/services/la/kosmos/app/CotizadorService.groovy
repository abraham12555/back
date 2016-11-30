package la.kosmos.app

import grails.transaction.Transactional

@Transactional
class CotizadorService {

    def cargarCatalogos(def params) {
        def productos
        def tiposDeProducto
        def documentos
        def x = 0
        def entidadFinanciera = EntidadFinanciera.get(((params.ef) ? (params.ef as long) : 13)) //TODO Recibir parametro por POST/GET para buscar dinamicamente la entidad financiera a conectarse
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
        def pasosCotizador = PasoCotizadorEntidadFinanciera.findAllWhere(entidadFinanciera: entidadFinanciera, cargaInicial: true)
        def tiposDePaso = (pasosCotizador*.tipoDePaso as Set)
        while(x < tiposDePaso.size()){
            if(tiposDePaso.getAt(x).nombre == "stepTipoDeProducto"){
                x = tiposDePaso.size()
                tiposDeProducto = TipoDeProducto.executeQuery("Select p.tipoDeProducto From Producto p Where p.activo = true And p.entidadFinanciera.id = :idEntidadFinanciera Order by p.tipoDeProducto", [idEntidadFinanciera: entidadFinanciera.id])
            } else if(productos == null){
                if(configuracion.aplicacionVariable){
                    def rubros = []
                    def listaTmp = RubroDeAplicacionDeCredito.findAllWhere(entidadFinanciera: entidadFinanciera)
                    listaTmp.each{
                        def mapa = [:]
                        mapa.id = it.id
                        mapa.nombreDelProducto = it.nombre
                        mapa.descripcion = it.descripcion
                        rubros << mapa
                    }
                    productos = rubros
                    documentos =  TipoDeDocumento.findAllWhere(usoEnCotizador: true)
                } else {
                    productos = Producto.findAllWhere(activo: true, entidadFinanciera: entidadFinanciera)
                }
            }
            x++
        }
        pasosCotizador = pasosCotizador.sort { it.numeroDePaso }
        [productos: productos, documentos: documentos, tiposDeProducto: tiposDeProducto, entidadFinanciera: entidadFinanciera, pasosCotizador: pasosCotizador, periodicidadList: Periodicidad.list(), configuracion: configuracion]
    }
}
