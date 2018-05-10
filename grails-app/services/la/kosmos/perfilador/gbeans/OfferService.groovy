package la.kosmos.perfilador.gbeans
import grails.transaction.Transactional
import la.kosmos.app.Producto
import la.kosmos.perfilador.gbeans.Evaluador
import la.kosmos.perfilador.gbeans.PerfiladorDAO
import la.kosmos.app.PlazoProducto
@Transactional
class OfferService {
    def motorDeDecisionService;
    def perfiladorService;

	
    def getOferta(def productoOfertar, def montoMaximoOfertar,  def  ofertarP1Producto1Actual, def perfil, def datosSolicitud, def identificadores, def entidadFinanciera  ){
        def objProducto = [:]
        Evaluador eval = new Evaluador();
        PerfiladorDAO perfDAO = new PerfiladorDAO();
        def ofertaProducto = [:]
        ofertaProducto.listaDeOpciones = []
        ofertaProducto.listaDePlazos = []
        def offerService
        def respuestaDictamenDePerfil;
        objProducto.claveDeProducto = productoOfertar;
        def datosProducto =  Producto.findByClaveDeProducto(objProducto.claveDeProducto)
        ofertaProducto.producto = datosProducto
        /*
         * Se obtiene el id de la periodicidad a partir del LayOut
         * */
        def periodicidad = eval.getIdPeriodicidad(perfil.getPeriodoCredMaxEp());
        def plazoProd = PlazoProducto.findWhere(producto: datosProducto, periodicidad:periodicidad,  usarEnPerfilador: true)
        def tasaDeInteres = datosProducto.getTasaDeInteresAnual();
        offerService = perfiladorService.calcularCuota(montoMaximoOfertar, periodicidad, perfil.plazoCredMaxEp
            ,tasaDeInteres,  datosSolicitud.entidadFinancieraId)
        offerService.tasaDeInteres = tasaDeInteres;
        offerService.montoMaximo = montoMaximoOfertar;//Se asigna monto maximo por politica
        offerService.montoMinimo = eval.getLimiteMontoMinimoProducto(datosProducto, perfil.plazoCredMaxEp,
            periodicidad, montoMaximoOfertar );
        offerService.periodicidad = plazoProd.periodicidad
        datos = construirDatosMotorDeDecisionClienteExistente(identificadores, objProducto, perfil, offerService)
        datos.asalariado = (datosSolicitud.documento.tipoDeIngresos.id == 1 ? true : false)
        respuestaDictamenDePerfil = motorDeDecisionService.obtenerDictamenteDePerfilClienteExistente(entidadFinanciera, datos)
  
        if(eval.getEvaluacionPerfilador(respuestaDictamenDePerfil)== "A"){
		   
            def tasaAplicable = perfDAO.getTasaDinamicaDelProducto(datosProducto.id, respuestaDictamenDePerfil.probabilidadDeMora)
            if(tasaAplicable) {
                datosSolicitud.tasaDeInteres = (tasaAplicable[0].tasaOrdinariaAnual)
                def ofertaTasaDinamica =calcularCuota(montoMaximoOfertar, periodicidad, perfil.plazoCredMaxEp
                    ,datosSolicitud.tasaDeInteres,  datosSolicitud.entidadFinancieraId)
                offerService.montoAsistencia = ofertaTasaDinamica.montoAsistencia
                offerService.montoSeguro = ofertaTasaDinamica.montoSeguro
                offerService.cuota = ofertaTasaDinamica.cuota
                offerService.tasaDeInteres = datosSolicitud.tasaDeInteres;
            }
            offerService.probabilidadDeMora = (respuestaDictamenDePerfil.probabilidadDeMora as float)
            offerService.dictamenDePerfil = respuestaDictamenDePerfil?.dictamen
            offerService.dictamenDePoliticas = dictamenProducto
            //quitar linea
            offerService.dictamenDePoliticas = "A"
            offerService.ratio= 0
            //fin quitar lineas
            offerService.garantias = perfDAO.getGarantias(datosProducto.id, offerService.montoMaximo)
            offerService.asalariado = (datosSolicitud.documento.tipoDeIngresos.id == 1 ? true : false)
    
        }
    }
 
}
