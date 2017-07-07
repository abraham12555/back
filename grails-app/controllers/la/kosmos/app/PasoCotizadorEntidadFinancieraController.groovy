package la.kosmos.app
import grails.converters.JSON

class PasoCotizadorEntidadFinancieraController {
    def springSecurityService

    def index() { }
    
    def obtenerDetallePasoCotizador(PasoCotizadorEntidadFinanciera pasoCotizadorEntidadFinanciera) {
        def faltanTipos = []
        def tipoDePasoCotizador = TipoDePasoCotizador.findAll()
         tipoDePasoCotizador.each{
           if (!pasoCotizadorEntidadFinanciera.tipoDePaso.nombre.contains(it.nombre)){
               def respuesta = [:]
                respuesta.nombre = it.nombre
                respuesta.id = it.id
                faltanTipos << respuesta
           }
         }
        render(template: "/dashboard/configuracion/pasoCotizadorEntidadFinanciera/detallePasoCotizador", model: [pasoCotizadorEntidadFinanciera: pasoCotizadorEntidadFinanciera,faltanTipos:faltanTipos])
    }
    
    def update (){
          println params
        def respuesta = [:]
        def pasoCotizadorEntidadFinanciera = PasoCotizadorEntidadFinanciera.get(params.idPasoCotizadorEntidadFinanciera as long)
        pasoCotizadorEntidadFinanciera.tituloDelPaso = params.tituloDelPaso
        pasoCotizadorEntidadFinanciera.tituloResumen = params.tituloResumen
        pasoCotizadorEntidadFinanciera.numeroDePaso = params.numeroDePaso as long
        pasoCotizadorEntidadFinanciera.tieneAyuda  = params.tieneAyuda.toBoolean()
        pasoCotizadorEntidadFinanciera.textoAyuda = params.textoAyuda
        pasoCotizadorEntidadFinanciera.cargaInicial = params.cargaInicial.toBoolean()
        pasoCotizadorEntidadFinanciera.entidadFinanciera = springSecurityService.currentUser.entidadFinanciera

        def tipoDePasoCotizador = TipoDePasoCotizador.get(params.tipoDePasoId as long)

        if (tipoDePasoCotizador.nombre == 'stepProducto'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepPlazos'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'periodicidadList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'pagoElegido'
            
        }
       / if (tipoDePasoCotizador.nombre == 'stepModelo'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepColor'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }/
        if (tipoDePasoCotizador.nombre == 'stepEnganche'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'engancheBar'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'engancheElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepSeguro'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'productosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'productoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepMontoCredito'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'montoCreditoBar'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'montoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepDocumentos'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'documentosList'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'documentoElegido'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepDecision'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'atrasoOption'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'tieneAtraso'
            
        }
        if (tipoDePasoCotizador.nombre == 'stepRegistro'){
            pasoCotizadorEntidadFinanciera.idListaAjax = 'registroCliente'
            pasoCotizadorEntidadFinanciera.variableValorSeleccionado = 'datosRegistro'
            
        }
        
        pasoCotizadorEntidadFinanciera.tipoDePaso = tipoDePasoCotizador
        if(pasoCotizadorEntidadFinanciera.save(flush:true)){
            respuesta.ok = true
            respuesta.mensaje = "Paso Guardado Correctamente."
            respuesta.pasoCotizadorEntidadFinanciera = pasoCotizadorEntidadFinanciera
            
        }
         else {
                    if (pasoCotizadorEntidadFinanciera.hasErrors()) {
                        pasoCotizadorEntidadFinanciera.errors.allErrors.each {
                            println it
                        }
                    }
                    respuesta.error = true
                    respuesta.mensaje = "Ocurrio un problema al asignar pasoCotizadorEntidadFinanciera. Intente nuevamente más tarde."
                }
        render respuesta as JSON
       
      
    }
}
