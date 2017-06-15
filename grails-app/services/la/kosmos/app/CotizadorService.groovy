package la.kosmos.app

import grails.transaction.Transactional
import org.apache.commons.codec.binary.Base64
import java.nio.file.Files;

@Transactional
class CotizadorService {

    def solicitudService
    
    def cargarCatalogos(def params) {
        def productos
        def tiposDeProducto
        def documentos
        def x = 0
        def entidadFinanciera = EntidadFinanciera.get(((params.ef) ? (params.ef as long) : 6)) //TODO Recibir parametro por POST/GET para buscar dinamicamente la entidad financiera a conectarse
        def configuracion = ConfiguracionEntidadFinanciera.findWhere(entidadFinanciera: entidadFinanciera)
        def landingImage = obtenerBase64Imagenes(configuracion.imagenDefault)
        def pasosCotizador = PasoCotizadorEntidadFinanciera.findAllWhere(entidadFinanciera: entidadFinanciera, cargaInicial: true)
        def tiposDePaso = (pasosCotizador*.tipoDePaso as Set)
        while(x < tiposDePaso.size()){
            if(tiposDePaso.getAt(x).nombre == "stepTipoDeProducto"){
                x = tiposDePaso.size()
                tiposDeProducto = TipoDeProducto.executeQuery("Select p.tipoDeProducto From Producto p Where p.activo = true And p.entidadFinanciera.id = :idEntidadFinanciera Order by p.tipoDeProducto", [idEntidadFinanciera: entidadFinanciera.id])
            } else if(productos == null){
                if(configuracion.aplicacionVariable){
                    def rubros = []
                    def listaTmp = RubroDeAplicacionDeCredito.findAllWhere(entidadFinanciera: entidadFinanciera, activo: true)
                    
                    listaTmp.each{
                        def mapa = [:]
                        mapa.id = it.id
                        mapa.nombreDelProducto = it.nombre
                        mapa.descripcion = it.descripcion
                        mapa.claseIconoPaso = it.claseIconoPaso
                        mapa.tooltip = it.tooltip
                        mapa.textoTooltip = it.textoTooltip
                        mapa.posicion = it.posicion
                        rubros << mapa
                    }
                    productos = rubros
                    if(params.rubroId){
                        documentos = RubroDeAplicacionTipoDeDocumento.executeQuery("Select ratd.tipoDeDocumento from RubroDeAplicacionTipoDeDocumento ratd Where ratd.rubro.id = :rubroId", [rubroId: (params.rubroId as long)])
                    } else {
                        documentos =  TipoDeDocumento.findAllWhere(usoEnCotizador: true, activo: true)
                    }
                    documentos = documentos.sort { it.id }
                    productos = productos.sort { it.posicion }
                } else {
                    productos = Producto.findAllWhere(activo: true, entidadFinanciera: entidadFinanciera)
                    productos = productos.sort { it.id }
                }
            }
            x++
        }
        pasosCotizador = pasosCotizador.sort { it.numeroDePaso }
        [productos: productos, documentos: documentos, tiposDeProducto: tiposDeProducto, entidadFinanciera: entidadFinanciera, pasosCotizador: pasosCotizador, periodicidadList: Periodicidad.list(), configuracion: configuracion, landingImage: landingImage]
    }
    
    def obtenerBase64Imagenes(def ruta){
        def respuesta = [:]
        if(ruta){
            byte[] array = Files.readAllBytes(new File(ruta).toPath());
            respuesta.base64 = Base64.encodeBase64String(array)
            respuesta.extension =  ruta.split("\\.")[-1]
        }
        return respuesta
    }
    
    def verificarSolicitudExistente(def telefono, def nombreCompleto, def email) {
        def respuesta = [:]
        respuesta = solicitudService.verificarSolicitudExistente(telefono, nombreCompleto, email)
        respuesta
    }

}
