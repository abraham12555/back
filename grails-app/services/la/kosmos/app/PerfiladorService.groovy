package la.kosmos.app

import grails.converters.JSON
import groovy.json.*
import grails.transaction.Transactional
import groovy.sql.Sql
import la.kosmos.rest.SolicitudRest
import java.nio.file.Files
import org.apache.commons.codec.binary.Base64
import java.time.*
import la.kosmos.app.exception.BusinessException

@Transactional
class PerfiladorService {

    def dataSource
    def buroDeCreditoService
    def motorDeDecisionService

    def vxc = 2.03 //constante que al multiplicarse * cuota, se obtine el pago mensual ¿varia segun el tipo de pago ?
    def sal_min = 80.04  // $$ salario minimo activo al 2017/marzo
    def ssmm = (sal_min * 30.4 ) //.round(2)
    def iva = 1.16

    def calculoCuota (resp) {
        def respuesta = [:]
        if(resp){
            respuesta = resp
        }
        def montoSeguro = importes(respuesta,respuesta.montoDelCredito,1)
        def montoAsistencia = importes(respuesta,respuesta.montoDelCredito,0)
        def periodicidadId = respuesta.periodicidadId
        def periodicidad = Periodicidad.get( periodicidadId as long)
        def tasaConI = respuesta.tasaDeInteres * 1.16
        def n = respuesta.plazos
        def c = (respuesta.montoDelCredito + montoSeguro + montoAsistencia)
        def i = (tasaConI/periodicidad.periodosAnuales)
        def renta =  (c / ((1-((1+i)**(-n)))/i))
        def cuota = renta * vxc
        return cuota
    }

    def importes (resp,monto,x){
        def respuesta = [:]
        if(resp){
            respuesta = resp
        }
        def montoSeguro = 0 as float
        def montoAsistencia = 0 as float
        def periodicidadId = respuesta.periodicidadId
        def periodicidad = Periodicidad.get( periodicidadId as long)
        def plazoAnual = (((respuesta.plazos as int)/(periodicidad.periodosAnuales)) as float).round()
        if(x == 1){
            def seguro = SeguroSobreDeuda.executeQuery('Select s from SeguroSobreDeuda s Where s.entidadFinanciera.id = :entidadFinancieraId and ((s.montoInicial <= :monto and s.montoFinal >= :monto) or (s.montoInicial <= :monto and s.montoFinal = 0)) and s.plazoAnual = :plazoAnual',[entidadFinancieraId: respuesta.entidadFinancieraId , monto: monto, plazoAnual: plazoAnual])
            if(seguro && seguro?.size() > 0){
                def seguroAplicable = seguro[0]
                if(seguroAplicable.porcentaje){
                    montoSeguro = (respuesta.montoDelCredito * seguroAplicable.porcentajeSeguro)
                } else {
                    montoSeguro = seguroAplicable.importeSeguro
                }
                montoSeguro = montoSeguro.round(3)
            }
            return montoSeguro
        }else{
            def asistencia = ServicioDeAsistencia.executeQuery('Select s from ServicioDeAsistencia s Where s.entidadFinanciera.id = :entidadFinancieraId and ((s.montoInicial <= :monto and s.montoFinal >= :monto) or (s.montoInicial <= :monto and s.montoFinal = 0)) and s.plazoAnual = :plazoAnual',[entidadFinancieraId: respuesta.entidadFinancieraId, monto: monto, plazoAnual: plazoAnual])
            if(asistencia && asistencia?.size() > 0){
                def asistenciaAplicable = asistencia[0]
                montoAsistencia = asistenciaAplicable.importeAsistencia
            }
            return montoAsistencia
        }
    }

    def maxCapacidadPago (def datos,x){
        def respuesta = [:]
        def porc_alq = 0.18 //valor constante
        def pto_corte = 15.00 as float
        def val_min_g_gral = 0.2 as float //Valor Minimo de Gastos General
        def val_max_g_gral = 0.5 as float //Valor Maximo de Gastos General
        def corr_ing_gastgral = 0.02 as float //Valor por Correccion de Gastos Generales
        def val_min_g_fliar = 0.14 as float //Valor Minimo de Gastos Familiares
        def corr_sum_gastfliar = 0.1717 as float //Valor por Correccion de sumatoria de Gastos Familiares
        def val_max_g_fliar = 0.183 as float //Valor Maximo de Gastos Familiares
        def corr_ing_g_fliar = 0.0028667 as float  //Valor de Correcion de Ingresos de Gastos Familiares
        def gast_fij = 0
        def gast_flia = 0
        if(datos){
            respuesta = datos
        }
        def ajuvar = respuesta.ingresosVariables * 0.9
        def ing_total = ajuvar + respuesta.ingresosFijos
        def ing_ssmm = (ing_total/ssmm )
        def egresorenta = (respuesta.montoDeLaRenta/ssmm)
        def alq = 0
        def egresohip = 0
        def grupofam = (respuesta.dependientesEconomicos + 1)
        def tipov = String.valueOf(respuesta.tipoDeVivienda)
        if(tipov == 'Rentada') {
            if((ing_ssmm * porc_alq) < egresorenta){
                alq = respuesta.montoDeLaRenta
            }else{
                alq = (ing_ssmm * porc_alq)
            }
        }else {
            alq = 0.00
        }
        def gastos1 = ((respuesta.gastos + egresohip)/ssmm)

        if(ing_ssmm >= pto_corte){
            gast_fij = (ing_ssmm*val_min_g_gral)
            gast_flia = (ing_ssmm*val_min_g_fliar*Math.exp(corr_sum_gastfliar*grupofam))
        }
        else {
            gast_fij = ((val_max_g_gral-corr_ing_gastgral*ing_ssmm) * ing_ssmm )
            gast_flia = (ing_ssmm*(val_max_g_fliar-corr_ing_g_fliar*ing_ssmm)*Math.exp(corr_sum_gastfliar*grupofam))
        }
        def gastos_totales = (alq + gast_flia + gast_fij + gastos1)
        def balcaj = (ing_ssmm - gastos_totales)
        def max_cap_pag = (balcaj * ssmm)//.round(2)
        if(x == 0){
            return max_cap_pag
        }else{
            return balcaj
        }
    }

    def montoMaximo (proc , resp ){
        def respuesta = [:]
        def proceso = [:]
        if(resp){
            respuesta = resp
        }
        if(proc){
            proceso = proc
        }
        def interes = respuesta.tasaDeInteres/respuesta.periodosAnuales
        def dcapag = 2.1 //se ocupa para conocer el monto maximo del credito
        def cap_de_pag = proceso.capacidaddepago/dcapag
        def x= (1-(Math.pow((1+interes),-(respuesta.plazos))))
        def monto_max_cred =(cap_de_pag * (x/interes)).round(2)
        return monto_max_cred
    }

    def obtenerRatio (proc) {
        def proceso = [:]
        if(proc){
            proceso = proc
        }
        def cuota_SSMM = proceso.cuota/ssmm
        def ratio = (proceso.balancecaja/cuota_SSMM).round(3)
        return ratio
    }

    def creaProceso(resp){
        def respuesta = [:]
        if(resp){
            respuesta = resp
        }
        def proceso = [:]
        proceso.capacidaddepago = maxCapacidadPago (respuesta,0)
        proceso.balancecaja = maxCapacidadPago (respuesta,1)
        proceso.cuota =  calculoCuota(respuesta)
        proceso.montomax = montoMaximo(proceso,respuesta)
        if (proceso.montomax > respuesta.montoMaximo ){
            proceso.montomax = respuesta.montoMaximo
        }
        proceso.ratio = obtenerRatio(proceso)
        return proceso.ratio

    }

    def plazoViable (resp){
        def b = 0
        def respuesta = [:]
        if(resp){
            respuesta = resp
        }
        def plazo = PlazoProducto.executeQuery('Select p from PlazoProducto p Where p.producto.id = :productoId and p.importeMinimo <= :monto and p.importeMaximo >= :monto ', [productoId: respuesta.productoId as long,  monto: respuesta.montoDelCredito])
        if(plazo){
            respuesta.plazosPermitidos = ((plazo[0].plazosPermitidos ? (plazo[0].plazosPermitidos.tokenize(',')) : null))
            for(def i = 0 ; i < respuesta.plazosPermitidos.size ; i++){
                int result = Integer.parseInt(respuesta.plazosPermitidos[i])
                if(respuesta.plazos == result){
                    i = i + 1
                    respuesta.plazos = respuesta.plazosPermitidos [i]
                    if(respuesta.plazos == null){
                        i = i - 1
                        respuesta.plazos = respuesta.plazosPermitidos [i]
                        b = 0
                    }else{
                        b = 1
                    }
                }
            }
        }
        if(b == 0){
            return 0
        }else{
            respuesta.plazos = Float.parseFloat(String.valueOf(respuesta.plazos))
            return respuesta.plazos
        }
    }

    def obtenerDatos(idSolicitud){
        def respuesta = [:]
        def b = 0
        respuesta.solicitud = idSolicitud
        def prodsol = ProductoSolicitud.executeQuery("SELECT ps FROM ProductoSolicitud ps WHERE ps.solicitud.id = "+ respuesta.solicitud)
        if (prodsol){
            respuesta.plazos = prodsol[0].plazos
            respuesta.montoDelCredito = prodsol[0].montoDelCredito
            respuesta.periodicidad = prodsol[0].periodicidad
            respuesta.productoId = prodsol[0].productoId
            respuesta.montoDelPago = prodsol[0].montoDelPago
            respuesta.periodicidadId = prodsol[0].periodicidadId
            respuesta.documento = prodsol[0].documentoElegidoId
            def periodos = Periodicidad.executeQuery("SELECT p FROM Periodicidad p WHERE p.nombre='" + respuesta.periodicidad + "'")
            if(periodos){
                respuesta.periodosAnuales = periodos[0].periodosAnuales
            }
            def producto = Producto.get(respuesta.productoId as long)
            respuesta.tasaDeInteres = (producto.tasaDeInteres*12)*1.16
            respuesta.entidadFinancieraId = producto.entidadFinancieraId
            respuesta.montoMaximo = producto.montoMaximo
            def tipoing = TipoDeDocumento.get(respuesta.documento as long)
            if(tipoing){
                respuesta.tipoingresos = tipoing.tipoDeIngresos
            }
            b = 1
        }
        def solcred = SolicitudDeCredito.get(idSolicitud as long)
        if (solcred){
            respuesta.idCliente = solcred.clienteId
            b = 2
        }
        def empleoCliente = EmpleoCliente.executeQuery("SELECT ec FROM EmpleoCliente ec WHERE ec.cliente.id = " + respuesta.idCliente )
        if (empleoCliente) {
            respuesta.gastos = empleoCliente[0].gastos
            respuesta.ingresosFijos = empleoCliente[0].ingresosFijos
            respuesta.ingresosVariables = empleoCliente[0].ingresosVariables
            b = 3
        }
        def cliente = Cliente.get(respuesta.idCliente as long )
        if (cliente){
            respuesta.dependientesEconomicos = cliente.dependientesEconomicos
            b = 4
        }
        def direccion = DireccionCliente.executeQuery("SELECT dc FROM DireccionCliente dc WHERE dc.cliente.id = " + respuesta.idCliente )
        if (direccion){
            respuesta.tipoDeVivienda = direccion[0].tipoDeVivienda
            respuesta.montoDeLaRenta = direccion[0].montoDeLaRenta
            b = 5
        }
        if (b == 5 )    {
            return respuesta
        }else{
            return null
        }
    }

    def perfilarProducto (idSolicitud){
        def respuesta = [:]
        def b = 0
        def resp = obtenerDatos(idSolicitud)
        if(resp){
            respuesta = resp
        }
        def ratio = creaProceso(respuesta)
        if(ratio > 1 ){

        }else{
            def oxp = ofertaPlazo(respuesta)
            def r = [:]
            if(oxp){
                r = oxp
            }
            if(r.ratio > 1 ){

            }
            def oxm = ofertaMonto(respuesta.solicitud)
            def rr = [:]
            if(oxm){
                rr = oxm
            }
            if (rr.ratio > 1 ){

            }
        }
        def res = obtenerDatos(respuesta.solicitud)
        if(res){
            respuesta = res
        }
        def productos = DocumentoProducto.executeQuery("SELECT dp FROM DocumentoProducto dp WHERE dp.tipoDeDocumento.id =" + respuesta.documento)
        def ofertas = []
        productos?.each {
            def producto = [:]
            producto.productoId = it.productoId
            def infprod = Producto.get( producto.productoId as long)
            producto.tasaDeInteres = infprod.tasaDeInteres
            if(infprod.entidadFinancieraId == respuesta.entidadFinancieraId ){
                def plazosr = PlazoProducto.executeQuery('Select p from PlazoProducto p Where p.producto.id = :productoId  order by plazoMaximo desc', [productoId: producto.productoId ] )
                plazosr?.each{
                    def yy = [:]
                    yy.plazoMaximo = it.plazoMaximo
                    yy.importeMAximo = it.importeMaximo
                    yy.periodicidad = it.periodicidadId
                    def od = obtenerDatos(respuesta.solicitud)
                    if(od){
                        respuesta = od
                    }
                    respuesta.plazos = yy.plazoMaximo
                    respuesta.montoDelCredito = yy.importeMAximo  as float
                    respuesta.periodicidadId = yy.periodicidad
                    respuesta.tasaDeInteres =  (producto.tasaDeInteres*12*1.16)
                    def rxp = creaProceso(respuesta)
                    if (rxp > 1 ){
                        def oferta = [:]
                        oferta.ratio = rxp
                        oferta.plazos = respuesta.plazos
                        oferta.montoDelCredito = respuesta.montoDelCredito
                        oferta.producto = producto.productoId
                        oferta.periodicidad = respuesta.periodicidadId
                        oferta.interesMensual = producto.tasaDeInteres
                        ofertas << oferta
                    }
                }
            }
        }
        return ofertas
    }

    def ofertaPlazo(respuesta){
        def rat = 0
        def b = [:]
        while (true){
            respuesta.plazos = plazoViable(respuesta) as float
            if (respuesta.plazos > 0){
                rat = creaProceso(respuesta)
            }
            if(rat > 1 || respuesta.plazos == 0  ) break
        }
        b.ratio = rat
        b.plazos = respuesta.plazos
        return b
    }

    def ofertaMonto (respuesta){
        def res = obtenerDatos(respuesta)
        def r = [:]
        if(res){
            respuesta = res
        }
        def proceso = [:]
        proceso.capacidaddepago = maxCapacidadPago (respuesta,0)
        def monto =  montoMaximo(proceso , respuesta) as float
        def montoSeguro = importes(respuesta,monto,1)
        def montoAsistencia = importes(respuesta,monto,0)
        respuesta.montoDelCredito = (monto - montoSeguro - montoAsistencia) as float
        def newrat = creaProceso(respuesta)
        r.ratio = newrat
        r.montoDelCredito = respuesta.montoDelCredito
        r.plazos = respuesta.plazos
        return r
    }

    // Implementación Mike

    def obtenerPropuestas(def origen, def identificadores, def idTipoDeDocumento, def clienteExistente, def perfil){
        def ofertas = []
        def oferta
        def datosSolicitud = consultarSolicitud(origen, identificadores.idSolicitud)
        def entidadFinanciera
        if(datosSolicitud) {
            entidadFinanciera = EntidadFinanciera.get(datosSolicitud.entidadFinancieraId as long)
            /*if(origen == "cotizador") {
            oferta = calcularOferta(datosSolicitud)
            println "RATIO = " + oferta.ratio
            if(oferta.ratio > 1 ){
            println "RATIO APROBADO"
            }else{
            println "RATIO MENOR A UNO"
            }
            println "NUEVA OFERTA p/solicitudid " + datosSolicitud.idSolicitud
            ofertas << oferta
            }*/
            def tipoDeDocumento = (datosSolicitud.documento ?: TipoDeDocumento.get(idTipoDeDocumento as long)) //Falta tomar en cuenta clienteExistente
            //Primer Filtro
            def productosAplicables = (DocumentoProducto.executeQuery("Select dp.producto From DocumentoProducto dp Where dp.producto.usoEnPerfilador = true " +  ( (clienteExistente == "SI") ? "And dp.producto.segundoCredito = true" : "And dp.producto.primerCredito = true" ) + " And dp.producto.entidadFinanciera.id = :entidadFinancieraId And dp.tipoDeDocumento.tipoDeIngresos.id = :tipoDeIngresos And (:edad  >= dp.producto.edadMinima And :edad <= dp.producto.edadMaxima)", [entidadFinancieraId: datosSolicitud.entidadFinancieraId, tipoDeIngresos: tipoDeDocumento.tipoDeIngresos.id, edad: datosSolicitud.edad])) as Set
            if (productosAplicables.size() == 0) {
                log.error("ERRPROD" + (aleatorio()) + ". No hay productos que apliquen para este perfil. Solicitud: " + datosSolicitud.idSolicitud)
                throw new BusinessException("No hay productos que apliquen para este perfil");
            }

            //Segundo Filtro
            def bitacoraDeBuro  = BitacoraBuroCredito.executeQuery("Select b from BitacoraBuroCredito b Where b.solicitud.id = " + identificadores.idSolicitud + "  Order by b.fechaRespuesta desc")
            if(bitacoraDeBuro == null || bitacoraDeBuro.empty) {
                log.error("ERRBC" + (aleatorio()) + ". No hay informacion de buro de credito. Solicitud: " + datosSolicitud.idSolicitud)
                throw new BusinessException("No se pudo recuperar la información. Inténtelo más tarde.");
            }

            def datos = [:]
            datos.solicitudId = identificadores.idSolicitud
            datos.listaDeServicios = (productosAplicables*.claveDeProducto)?.join(',')
            datos.edad = datosSolicitud.edad 
            datos.cadenaBuroDeCredito = buroDeCreditoService.generarCadenaBC(bitacoraDeBuro.getAt(0))

            boolean respuestaEnvioCadenaBC
            def respuestaDictameneDePoliticas
            if(datos.cadenaBuroDeCredito) {
                if(clienteExistente == "SI") {
                    datos.experienciaCrediticia = (perfil?.experienciaCrediticia == "EP" ? true : false)
                    datos.creditosLiquidados = (perfil?.numCredLiqdExp ?: 0)
                    respuestaEnvioCadenaBC = motorDeDecisionService.enviarCadenaDeBuroClienteExistente(entidadFinanciera,datos)
                    if(respuestaEnvioCadenaBC) {
                        respuestaDictameneDePoliticas = motorDeDecisionService.obtenerDictamenteDePoliticasClienteExistente(entidadFinanciera, datos)
                    } else {
                        log.error("ERRCLEX" + (aleatorio()) + ". Se detectaron errores al enviar la cadena de buro de credito. Solicitud: " + datos.solicitudId)
                        throw new BusinessException("No se pudo enviar la información de buró de crédito. Inténtelo más tarde.");
                    }
                } else {
                    respuestaEnvioCadenaBC = motorDeDecisionService.enviarCadenaDeBuro(entidadFinanciera, datos)
                    if(respuestaEnvioCadenaBC) {
                        respuestaDictameneDePoliticas = motorDeDecisionService.obtenerDictamenteDePoliticas(entidadFinanciera, datos)
                    } else {
                        log.error("ERRCLN" + (aleatorio()) + ". Se detectaron errores al enviar la cadena de buro de credito. Solicitud: " + datos.solicitudId)
                        throw new BusinessException("No se pudo enviar la información de buró de crédito. Inténtelo más tarde.");
                    }
                }

                if(respuestaDictameneDePoliticas != null && respuestaDictameneDePoliticas.empty) {
                    log.error("ERRDP" + (aleatorio()) + ". Se detectaron errores al obtener el dictamen de políticas. Solicitud: " + datos.solicitudId)
                    throw new BusinessException("No se pudo obtener el dictamen de políticas. Inténtelo más tarde.");
                }
            }

            def listaTemporal = []
            productosAplicables?.each { producto ->
                if(respuestaDictameneDePoliticas) {
                    def dictamen = respuestaDictameneDePoliticas.find { (it."$producto.claveDeProducto" == "A" || it."$producto.claveDeProducto" == "D") }
                    if(dictamen) {
                        listaTemporal << producto
                    }
                }
            }

            if (listaTemporal.empty) {
                log.error("ERRDPR" + (aleatorio()) + ". El historial crediticio no cumple las politicas. Solicitud: " + datos.solicitudId)
                throw new BusinessException("El historial crediticio no cumple las políticas");
            }

            productosAplicables = listaTemporal
            boolean errorCapacidadPago = Boolean.TRUE
            boolean errorDictamenPerfil = Boolean.TRUE
            boolean errorDictamenPerfilRechazado = Boolean.TRUE
            //Revisión de plazos por producto
            productosAplicables?.each { producto ->
                def ofertaProducto = [:]
                ofertaProducto.producto = producto
                ofertaProducto.listaDeOpciones = []
                ofertaProducto.listaDePlazos = []
                // println "Perfilando: " + producto
                def plazosPosibles = PlazoProducto.findWhere(producto: producto, usarEnPerfilador: true)
                def plazosPermitidos = ((plazosPosibles.plazosPermitidos ? (plazosPosibles.plazosPermitidos.tokenize(',')) : null))
                plazosPermitidos = plazosPermitidos?.reverse()
                //println "Plazos Permitidos: " + plazosPermitidos
                plazosPermitidos?.each { plazo ->
                    ///  println "Calculando usando plazo: " + plazo + " " + plazosPosibles.periodicidad
                    datosSolicitud.plazos = plazo
                    datosSolicitud.periodicidad = plazosPosibles.periodicidad
                    datosSolicitud.producto = producto
                    datosSolicitud.documento = tipoDeDocumento
                    datosSolicitud.tasaDeInteres = (producto.tasaDeInteresAnual) //* 1.16
                    datosSolicitud.montoMaximo = producto.montoMaximo
                    oferta = calcularOferta(datosSolicitud)
                    if(oferta.ratio > 1){
                        errorCapacidadPago = Boolean.FALSE

                        def respuestaDictamenDePerfil
                        if(clienteExistente == "SI") {
                            datos = construirDatosMotorDeDecisionClienteExistente(identificadores, producto, perfil, oferta)
                            respuestaDictamenDePerfil = motorDeDecisionService.obtenerDictamenteDePerfilClienteExistente(entidadFinanciera, datos)
                        } else {
                            datos = construirDatosMotorDeDecisionClienteNuevo(identificadores, producto, oferta)
                            respuestaDictamenDePerfil = motorDeDecisionService.obtenerDictamenteDePerfil(entidadFinanciera, datos)
                        }

                        if (respuestaDictamenDePerfil != null) {
                            errorDictamenPerfil = Boolean.FALSE
                            
                            if(respuestaDictamenDePerfil?.dictamen == 'A') {
                                errorDictamenPerfilRechazado = Boolean.FALSE
                                
                                def tasaAplicable = TasaDinamicaProducto.executeQuery("Select tdp From TasaDinamicaProducto tdp Where tdp.producto.id = :idProducto And :probabilidadDeMora >= tdp.probabilidadDeIncumplimientoMinima And :probabilidadDeMora <= tdp.probabilidadDeIncumplimientoMaxima ", [idProducto: producto.id, probabilidadDeMora: ((respuestaDictamenDePerfil.probabilidadDeMora * 100) as float)])
                                if(tasaAplicable) {
                                    datosSolicitud.tasaDeInteres = (tasaAplicable[0].tasaOrdinariaAnual)
                                    oferta = calcularOferta(datosSolicitud)
                                }
                                oferta.probabilidadDeMora = (respuestaDictamenDePerfil.probabilidadDeMora as float)
                                oferta.dictamenDePerfil = respuestaDictamenDePerfil?.dictamen
                                oferta.dictamenDePoliticas = ((respuestaDictameneDePoliticas.find { (it."$producto.claveDeProducto" == "A" || it."$producto.claveDeProducto" == "D" || it."$producto.claveDeProducto" == "R") })?."$producto.claveDeProducto")
                                println ((respuestaDictameneDePoliticas.find { (it."$producto.claveDeProducto" == "A" || it."$producto.claveDeProducto" == "D" || it."$producto.claveDeProducto" == "R") })?."$producto.claveDeProducto")
                                ofertaProducto.listaDeOpciones << oferta
                                def mapaPlazo = [:]
                                mapaPlazo.plazos = (plazo as int)
                                mapaPlazo.periodicidadId = plazosPosibles.periodicidad.id
                                mapaPlazo.periodicidad = plazosPosibles.periodicidad.nomenclatura
                                ofertaProducto.listaDePlazos << mapaPlazo
                            }
                        }
                    }
                }
                if(ofertaProducto.listaDeOpciones?.size() > 0) {
                    ofertas << ofertaProducto
                }
            }

            if (errorCapacidadPago) {
                throw new BusinessException("Falta de capacidad de pago");
            } else if (errorDictamenPerfil) {
                log.error("ERRDPER" + (aleatorio()) + ". No se pudo obtener el dictamen de perfil. Solicitud: " + datos.solicitudId)
                throw new BusinessException("No se pudo obtener el dictamen de perfil. Inténtelo más tarde.");
            } else if (errorDictamenPerfilRechazado) {
                throw new BusinessException("No cumple con el dictamen de perfil");
            }
        }

        if (ofertas.size() > 0) {
            return ofertas
        } else {
            log.error("ERROP. No se generaron ofertas. Solicitud: " + datos.solicitudId);
            throw new BusinessException("Error desconocido. Favor de reportar al administrador del sistema");
        }
    }

    def aleatorio (){
        def umeroAleatorio = Math.abs(new Random().nextInt() % 988) + 1
    }

    def consultarSolicitud(def origen, def idSolicitud){
        def datosSolicitud = [:]
        def b = 0
        def solicitudDeCredito = SolicitudDeCredito.get(idSolicitud as long)
        if(solicitudDeCredito) {
            datosSolicitud.idSolicitud = idSolicitud
            datosSolicitud.origen = origen
            datosSolicitud.dependientesEconomicos = solicitudDeCredito.cliente.dependientesEconomicos
            datosSolicitud.entidadFinancieraId = solicitudDeCredito.entidadFinanciera.id
            datosSolicitud.edad = calcularTiempoTranscurrido(solicitudDeCredito.cliente.fechaDeNacimiento)
            def empleoCliente = EmpleoCliente.findWhere(cliente: solicitudDeCredito.cliente, vigente: Boolean.TRUE)
            if(empleoCliente) {
                datosSolicitud.gastos = empleoCliente.gastos
                datosSolicitud.ingresosFijos = empleoCliente.ingresosFijos
                datosSolicitud.ingresosVariables = empleoCliente.ingresosVariables
            }
            def direccionCliente = DireccionCliente.findWhere(cliente: solicitudDeCredito.cliente, vigente: Boolean.TRUE)
            if(direccionCliente) {
                datosSolicitud.tipoDeVivienda = direccionCliente.tipoDeVivienda
                datosSolicitud.montoDeLaRenta = direccionCliente.montoDeLaRenta
            }
            def productoSolicitud = ProductoSolicitud.findWhere(solicitud: solicitudDeCredito)
            if(productoSolicitud){
                datosSolicitud.plazos = productoSolicitud.plazos
                datosSolicitud.montoDelCredito = productoSolicitud.montoDelCredito
                datosSolicitud.periodicidad = productoSolicitud.periodicidad
                datosSolicitud.producto = productoSolicitud.producto
                datosSolicitud.montoDelPago = productoSolicitud.montoDelPago
                datosSolicitud.documento = productoSolicitud.documentoElegido
                datosSolicitud.tasaDeInteres = (productoSolicitud.producto.tasaDeInteresAnual)
                datosSolicitud.montoMaximo = productoSolicitud.producto.montoMaximo
            }

            return datosSolicitud
        } else {
            return null
        }
    }

    def calcularCuota(def montoDelCredito, def periodicidad, def plazos, def tasaDeInteres, def entidadFinancieraId) {
        def respuesta = [:]
        //println "Parametros de entrada ->  monto" + montoDelCredito + ", periodicidad: " + periodicidad + ", plazo: " + plazos + ", tasa de interes: " + tasaDeInteres
        respuesta.montoAsistencia = obtenerSeguroAsistencia(1, periodicidad, plazos, entidadFinancieraId, montoDelCredito)
        respuesta.montoSeguro = obtenerSeguroAsistencia(0, periodicidad, plazos, entidadFinancieraId, (montoDelCredito + respuesta.montoAsistencia))
        //println "Seguro: " + respuesta.montoSeguro + " -  Asistencia: " + respuesta.montoAsistencia
        def tasaConI = tasaDeInteres * 1.16
        //println "tasa con i: " + tasaConI
        def n = plazos as int
        def c = (montoDelCredito + respuesta.montoSeguro + respuesta.montoAsistencia)
        //println "C: " + c
        def i = (tasaConI/periodicidad.periodosAnuales)
        //println "i: " + i
        def renta =  (c / ((1-((1+i)**(-n)))/i))
        respuesta.cuota = renta //* vxc Solo se ocupa para mensualizar el pago
        return respuesta
    }

    def calcularMaximaCapacidadDePago(def ingresosFijos, def ingresosVariables, def gastos, def montoDeLaRenta, def dependientesEconomicos, def tipoDeVivienda, def solicitudId, def asalariado) {
        def respuesta = [:]
        def porc_alq = 0.18 //valor constante
        def pto_corte = 15.00 as float
        def val_min_g_gral = 0.2 as float //Valor Minimo de Gastos General
        def val_max_g_gral = 0.5 as float //Valor Maximo de Gastos General
        def corr_ing_gastgral = 0.02 as float //Valor por Correccion de Gastos Generales
        def val_min_g_fliar = 0.14 as float //Valor Minimo de Gastos Familiares
        def corr_sum_gastfliar = 0.1717 as float //Valor por Correccion de sumatoria de Gastos Familiares
        def val_max_g_fliar = 0.183 as float //Valor Maximo de Gastos Familiares
        def corr_ing_g_fliar = 0.0028667 as float  //Valor de Correcion de Ingresos de Gastos Familiares
        def gast_fij = 0
        def gast_flia = 0
        def ajuvar = ingresosVariables * 0.9
        def ing_total = ajuvar + ingresosFijos
        def ing_ssmm = (ing_total/ssmm )
        def egresorenta = (montoDeLaRenta/ssmm)
        def alq = 0
        def egresohip = 0
        def grupofam = (dependientesEconomicos + 1)
        def tipov = String.valueOf(tipoDeVivienda)
        def montoAPagar = buroDeCreditoService.calcularMontoAPagar(solicitudId, asalariado)
        gastos = montoAPagar
        if(tipov == 'Rentada') {
            if((ing_ssmm * porc_alq) < egresorenta){ 
                alq = egresorenta
            } else {
                alq = (ing_ssmm * porc_alq)
            }
        } else {
            alq = 0.00
        }
        def gastos1 = ((gastos + egresohip)/ssmm)

        if(ing_ssmm >= pto_corte){
            gast_fij = (ing_ssmm*val_min_g_gral)
            gast_flia = (ing_ssmm * val_min_g_fliar * Math.exp(corr_sum_gastfliar * grupofam ))
        } else {
            gast_fij = ((val_max_g_gral - corr_ing_gastgral * ing_ssmm) * ing_ssmm )
            gast_flia = (ing_ssmm * (val_max_g_fliar - corr_ing_g_fliar * ing_ssmm) * Math.exp(corr_sum_gastfliar * grupofam))
        }
        def gastos_totales = (alq + gast_flia + gast_fij + gastos1)
        def balcaj = (ing_ssmm - gastos_totales )
        def max_cap_pag = (balcaj * ssmm)
        respuesta.montoAPagar = montoAPagar
        respuesta.maximaCapacidadDePago = max_cap_pag
        respuesta.balanceDeCaja = balcaj
        return respuesta
    }

    def calcularMontoMaximo(def tasaDeInteres, def periodosAnuales, def plazos, def capacidadDePago) {
        def interes = ((tasaDeInteres * 1.16)/periodosAnuales)
        def dcapag = 2.1
        def cap_de_pag = (capacidadDePago/dcapag)
        def x= (1-(Math.pow((1+interes),-(plazos as int))))
        def monto_max_cred =(cap_de_pag * (x/interes)).round(2)
        return monto_max_cred
    }

    def calcularRatio(def cuota, def balanceDeCaja, def periodicidadId){
        def cuotaSSMM = (mensualizarCuota(cuota, periodicidadId))/ssmm
        def ratio = (balanceDeCaja/cuotaSSMM).round(3)
        return ratio
    }

    def obtenerSeguroAsistencia(def opcion, def periodicidad, def plazos, def entidadFinancieraId, def montoDelCredito) {
        def montoSeguro = 0 as float
        def montoAsistencia = 0 as float
        def plazoAnual = (((plazos as int)/(periodicidad.periodosAnuales)) as float).round()
        if((((plazos as int)/(periodicidad.periodosAnuales)) as float) > plazoAnual) {
            //println "PAF " + (((plazos as int)/(periodicidad.periodosAnuales)) as float) + " vs PAInt" + plazoAnual
            plazoAnual++
        }
        if(opcion == 0){
            def seguro = SeguroSobreDeuda.executeQuery('Select s from SeguroSobreDeuda s Where s.entidadFinanciera.id = :entidadFinancieraId and ((s.montoInicial <= :monto and s.montoFinal >= :monto) or (s.montoInicial <= :monto and s.montoFinal = 0)) and s.plazoAnual = :plazoAnual',[entidadFinancieraId: entidadFinancieraId , monto: montoDelCredito as float, plazoAnual: plazoAnual])
            if(seguro && seguro?.size() > 0){
                def seguroAplicable = seguro[0]
                if(seguroAplicable.porcentaje){
                    montoSeguro = (montoDelCredito * seguroAplicable.porcentajeSeguro)
                } else {
                    montoSeguro = seguroAplicable.importeSeguro
                }
                montoSeguro = montoSeguro.round(3)
            }
            //println "Seguro Calculado: " + montoSeguro
            return montoSeguro
        }else{
            def asistencia = ServicioDeAsistencia.executeQuery('Select s from ServicioDeAsistencia s Where s.entidadFinanciera.id = :entidadFinancieraId and ((s.montoInicial <= :monto and s.montoFinal >= :monto) or (s.montoInicial <= :monto and s.montoFinal = 0)) and s.plazoAnual = :plazoAnual',[entidadFinancieraId: entidadFinancieraId, monto: montoDelCredito as float, plazoAnual: plazoAnual])
            if(asistencia && asistencia?.size() > 0){
                def asistenciaAplicable = asistencia[0]
                montoAsistencia = asistenciaAplicable.importeAsistencia
            }
            ///println "Asistencia Calculada: " + montoSeguro
            return montoAsistencia
        }
    }

    def calcularOferta(def datosSolicitud) {
        def oferta = calcularMaximaCapacidadDePago(datosSolicitud.ingresosFijos, datosSolicitud.ingresosVariables, datosSolicitud.gastos, datosSolicitud.montoDeLaRenta, datosSolicitud.dependientesEconomicos, datosSolicitud.tipoDeVivienda, datosSolicitud.idSolicitud, (datosSolicitud.documento.tipoDeIngresos.id == 1 ? true : false ))
        oferta.montoMaximo = calcularMontoMaximo(datosSolicitud.tasaDeInteres, datosSolicitud.periodicidad.periodosAnuales, datosSolicitud.plazos, oferta.maximaCapacidadDePago)
        ///println "MONTO MAXIMO CALCULADO: " + oferta.montoMaximo
        oferta.montoAsistencia = obtenerSeguroAsistencia(1, datosSolicitud.periodicidad, datosSolicitud.plazos, datosSolicitud.entidadFinancieraId, oferta.montoMaximo)
        oferta.montoSeguro = obtenerSeguroAsistencia(0, datosSolicitud.periodicidad, datosSolicitud.plazos, datosSolicitud.entidadFinancieraId, (oferta.montoMaximo))
        oferta.montoMaximo = (oferta.montoMaximo - oferta.montoSeguro - oferta.montoAsistencia)
        //println "MONTO MAXIMO CALCULADO (Menos Seguro y Asistencia): " + oferta.montoMaximo
        def limites = LimitePlazoProducto.findWhere(producto: datosSolicitud.producto, plazo: (datosSolicitud.plazos as int), periodicidad: datosSolicitud.periodicidad)
        oferta.montoMinimo = limites.limiteMinimo
        if ((limites.limiteMaximo > 0) && (oferta.montoMaximo > limites.limiteMaximo) ){
            oferta.montoMaximo = limites.limiteMaximo
        } else if (oferta.montoMaximo < 0) {
            oferta.montoMaximo = 0
        }
        if(oferta.montoMinimo > oferta.montoMaximo) {
            oferta.montoMinimo = oferta.montoMaximo
        }
        //println "MONTO MAXIMO FINAL: " + oferta.montoMaximo
        def calculoCuota =  calcularCuota(oferta.montoMaximo, datosSolicitud.periodicidad, datosSolicitud.plazos, datosSolicitud.tasaDeInteres, datosSolicitud.entidadFinancieraId)
        oferta.asalariado = (datosSolicitud.documento.tipoDeIngresos.id == 1 ? true : false)
        oferta.cuota = calculoCuota.cuota
        oferta.montoSeguro = calculoCuota.montoSeguro
        oferta.montoAsistencia = calculoCuota.montoAsistencia
        oferta.ratio = calcularRatio(oferta.cuota, oferta.balanceDeCaja, datosSolicitud.periodicidad.id)
        oferta.plazos = datosSolicitud.plazos
        oferta.periodicidad = datosSolicitud.periodicidad
        oferta.tasaDeInteres = datosSolicitud.tasaDeInteres
        oferta.garantias = GarantiaProducto.executeQuery("Select gp From GarantiaProducto gp Where gp.producto.id = :productoId And ( :monto >= gp.cantidadMinima And :monto <= gp.cantidadMaxima) order by gp.id", [productoId: datosSolicitud.producto.id, monto: (oferta.montoMaximo as float)])
        //println "RATIO " + oferta.ratio+" | CAP PAGO "+ oferta.maximaCapacidadDePago+" | BALANCE DE CAJA "+oferta.balanceDeCaja +" | CUOTA "+oferta.cuota+" | MONTOMAX " + oferta.montoMaximo + " | MONTOMIN: " + oferta.montoMinimo + " | MONTO A PAGAR: " + oferta.montoAPagar
        return oferta
    }

    def recalcularOferta(def ofertas, def params) {
        def respuesta = [:]
        //println "Buscar producto..."
        def producto = ofertas.find { it.producto.id == (params.productoId as long)}
        //println "Producto encontrado: " + producto
        //        println "Buscar oferta..."
        def oferta = producto.listaDeOpciones.find { it.plazos == (params.plazo) && it.periodicidad.id == (params.periodicidadId as long) }
        //println "Oferta encontrada: " + oferta
        respuesta.cuota =  calcularCuota((params.montoDeCredito as float), oferta.periodicidad, oferta.plazos, oferta.tasaDeInteres, producto.producto.entidadFinanciera.id)
        respuesta.tasaDeInteres = oferta.tasaDeInteres
        respuesta.periodicidad = oferta.periodicidad.nombre.toUpperCase()
        respuesta.garantias = GarantiaProducto.executeQuery("Select gp From GarantiaProducto gp Where gp.producto.id = :productoId And ( :monto >= gp.cantidadMinima And :monto <= gp.cantidadMaxima) order by gp.id", [productoId: producto.producto.id, monto: (params.montoDeCredito as float)])
        return respuesta
    }

    def guardarOferta(def ofertas, def datosSolicitud, def identificadores, def params) {
        def respuesta = [:]
        def producto = ofertas.find { it.producto.id == (params.productoId as long)}
        def oferta = producto.listaDeOpciones.find { it.plazos == (params.plazo) && it.periodicidad.id == (params.periodicidadId as long) }
        
        if(!oferta || !identificadores.idSolicitud) {
            throw new BusinessException("Operacion invalida. Los datos son incompletos")
        }
        
        def solicitud = SolicitudDeCredito.get(identificadores.idSolicitud as long)
        solicitud.montoPagoBuro = oferta.montoAPagar as float
        solicitud.save(flush:Boolean.TRUE, failOnError: Boolean.TRUE)
        ProductoSolicitud.executeUpdate("delete ProductoSolicitud ps where ps.solicitud.id = :idSolicitud",[idSolicitud: solicitud.id])
        def productoSolicitud =  new ProductoSolicitud()
        productoSolicitud.producto = Producto.get(params.productoId as long)
        productoSolicitud.documentoElegido = TipoDeDocumento.get(datosSolicitud.cliente.tipoDeDocumento as long);
        productoSolicitud.montoDelCredito = params.montoDeCredito as float
        productoSolicitud.montoDelSeguroDeDeuda = oferta.montoSeguro as float
        productoSolicitud.montoDelPago = params.montoPago as float
        productoSolicitud.haTenidoAtrasos = false
        productoSolicitud.seguroFinanciado = true
        productoSolicitud.enganche = 0 as float
        productoSolicitud.periodicidad = oferta.periodicidad
        productoSolicitud.plazos = params.plazo as int
        productoSolicitud.tasaDeInteres = oferta.tasaDeInteres
        productoSolicitud.montoDeServicioDeAsistencia = oferta.montoAsistencia
        productoSolicitud.solicitud = solicitud
        if(productoSolicitud.save(flush:true)){
            ///println("El producto se ha registrado correctamente")
            def resultadoMotorDeDecision = new ResultadoMotorDeDecision()
            resultadoMotorDeDecision.solicitud = solicitud
            resultadoMotorDeDecision.probabilidadDeMora = oferta.probabilidadDeMora
            resultadoMotorDeDecision.razonDeCobertura = oferta.ratio
            resultadoMotorDeDecision.dictamenDePerfil = oferta.dictamenDePerfil
            resultadoMotorDeDecision.dictamenCapacidadDePago = "-"
            resultadoMotorDeDecision.dictamenConjunto = "-"
            resultadoMotorDeDecision.dictamenDePoliticas = oferta.dictamenDePoliticas
            resultadoMotorDeDecision.dictamenFinal = "A"
            resultadoMotorDeDecision.log = "NO DISPONIBLE"
            ResultadoMotorDeDecision.executeUpdate("Delete From ResultadoMotorDeDecision r Where r.solicitud.id = :solicitudId", [solicitudId: solicitud.id])
            if(resultadoMotorDeDecision.save(flush: true)) {
                solicitud.statusDeSolicitud = StatusDeSolicitud.get(4)
                solicitud.ultimoPaso = SolicitudDeCredito.ULTIMO_PASO
                solicitud.save(flush: true)
                respuesta.oferta = oferta
                respuesta.productoSolicitud = productoSolicitud
            } else {
                if (resultadoMotorDeDecision.hasErrors()) {
                    resultadoMotorDeDecision.errors.allErrors.each {
                        log.error("DESC" + solicitud.id + ". " + it)
                    }
                }
                throw new BusinessException("ERR" + solicitud.id + ". Error al guardar la informacion del motor de decision")
            }
        } else {
            if (productoSolicitud.hasErrors()) {
                productoSolicitud.errors.allErrors.each {
                    log.error("DESC" + solicitud.id + ". " + it)
                }
            }
            throw new BusinessException("ERR" + solicitud.id + ". Error al guardar la informacion del producto")
        }
        respuesta
    }

    def construirDatosMotorDeDecisionClienteNuevo(def identificadores, def producto, def oferta){
        def datos = [:]
        //println ("Identificadores: " + identificadores)
        def solicitud = SolicitudDeCredito.get(identificadores.idSolicitud)
        def direccion = DireccionCliente.get(identificadores.idDireccion)
        def empleo = EmpleoCliente.get(identificadores.idEmpleo)
        //def bitacoraDeBuro = BitacoraBuroCredito.executeQuery("Select b from BitacoraBuroCredito b Where b.solicitud.id = " + solicitud.id + "  Order by b.fechaRespuesta desc")
        datos.solicitudId = solicitud.id
        datos.riesgoGeografico = solicitud.sucursal?.riesgoGeografico?.nombre?.replaceAll("\\s+", "")?.toUpperCase()
        datos.plazo = oferta.plazos as int
        datos.periodicidad = oferta.periodicidad.nombre.toUpperCase()
        datos.riesgoOcupacion = empleo.ocupacion.riesgoDeOcupacion?.nombre?.replaceAll("\\s+", "")?.toUpperCase()
        datos.edad = calcularTiempoTranscurrido(solicitud.cliente.fechaDeNacimiento)
        datos.estadoCivil = solicitud.cliente.estadoCivil.nombre.replaceAll("\\s+", "").toUpperCase()
        datos.productoServicio = producto.claveDeProducto
        datos.antiguedadVivienda = calcularTiempoTranscurrido(new Date().parse("dd/MM/yyyy", ("01/" + direccion.tiempoDeVivienda)))
        datos.ingresosFijosMensuales = new Double(empleo.ingresosFijos)
        datos.ingresosVariablesMensuales = new Double(empleo.ingresosVariables)
        datos.otrosIngresos = new Double(0)
        datos.dependientesEconomicos = (solicitud.cliente.dependientesEconomicos + 1)
        datos.gastoRenta = new Double(direccion.montoDeLaRenta)
        datos.cuotaMensualCredito = new Double(oferta.cuota)
        datos.tipoDeVivienda = direccion.tipoDeVivienda.id.intValue()
        datos.asalariado = oferta.asalariado
        /*if(bitacoraDeBuro){
        datos.cadenaBuroDeCredito = buroDeCreditoService.generarCadenaBC(bitacoraDeBuro.getAt(0))
        } else {
        datos.cadenaBuroDeCredito = ""
        }*/
        //println "Regresando: " + datos
        datos
    }

    def construirDatosMotorDeDecisionClienteExistente(def identificadores, def producto, def perfil, def oferta){
        def datos = [:]
        //println "****** Inicio Clientes Existentes *********"
        //println ("Identificadores: " + identificadores)
        def solicitud = SolicitudDeCredito.get(identificadores.idSolicitud)
        def direccion = DireccionCliente.get(identificadores.idDireccion)
        def empleo = EmpleoCliente.get(identificadores.idEmpleo)
        datos.solicitudId = solicitud.id
        datos.riesgoGeografico = solicitud.sucursal?.riesgoGeografico?.nombre?.replaceAll("\\s+", "")?.toUpperCase()
        datos.riesgoOcupacion = empleo.ocupacion.riesgoDeOcupacion?.nombre?.replaceAll("\\s+", "")?.toUpperCase()
        datos.edad = calcularTiempoTranscurrido(solicitud.cliente.fechaDeNacimiento)
        datos.estadoCivil = solicitud.cliente.estadoCivil.nombre.replaceAll("\\s+", "").toUpperCase()
        datos.renovacion = perfil.renovacion.toString()
        datos.productoServicio = producto.claveDeProducto
        datos.experienciaCrediticia = (perfil.experienciaCrediticia == "EP" ? true : false)
        datos.creditosLiquidados = (perfil.numCredLiqdExp ?: 0)
        def mesesTranscurridos = (((perfil.ultimaFechaCaptura != null && perfil.ultimaFechaCaptura.length() == 8)) ? calcularMesesTranscurridos(new Date().parse("yyyyMMdd", (perfil.ultimaFechaCaptura))) : 0 )
        datos.rdifmspwultcpt12 = new Double( (mesesTranscurridos > 0) ? ( (oferta.montoMaximo - perfil.ultimoMontoLiberado)/mesesTranscurridos) : 0 )
        def sumatoriaUltimosPagos = (perfil.ultimoPago1 + perfil.ultimoPago2 + perfil.ultimoPago3)
        datos.revoPagos3 = new Double( (sumatoriaUltimosPagos > 0) ? (perfil.ultimoPago1/(sumatoriaUltimosPagos/3)) : 0 )
        datos.propMontoLiberado = new Double( (perfil.ultimoMontoLiberado > 0) ? (oferta.montoMaximo/perfil.ultimoMontoLiberado) : 0 )
        datos.fecAntigCliCred = (((perfil.fecAntigCliCred != null && perfil.fecAntigCliCred.length() == 8)) ? calcularTiempoTranscurrido(new Date().parse("yyyyMMdd", (perfil.fecAntigCliCred))) : 0 )
        datos.antiguedadEmpleo = calcularTiempoTranscurrido(new Date().parse("dd/MM/yyyy", ("01/" + empleo.fechaIngreso)))
        def promedioVecAhorro =  ((perfil.vectorAhorro1 + perfil.vectorAhorro2 + perfil.vectorAhorro3 + perfil.vectorAhorro4 + perfil.vectorAhorro5 + perfil.vectorAhorro6 + perfil.vectorAhorro7 + perfil.vectorAhorro8 +perfil.vectorAhorro9 + perfil.vectorAhorro10 +perfil.vectorAhorro11 + perfil.vectorAhorro12)/12)
        def promedioVecInversion = ((perfil.vectorInver1 + perfil.vectorInver2 + perfil.vectorInver3 + perfil.vectorInver4 + perfil.vectorInver5 + perfil.vectorInver6 + perfil.vectorInver7 + perfil.vectorInver8 + perfil.vectorInver9 + perfil.vectorInver10 + perfil.vectorInver11 + perfil.vectorInver12)/12)
        def cuotaMensualizada = mensualizarCuota(oferta.cuota, oferta.periodicidad.id)
        datos.rcobsldpas12 = ((promedioVecAhorro + (promedioVecInversion * 0.1))/ cuotaMensualizada)
        datos.asalariado = oferta.asalariado
        //println "Regresando: " + datos
        //println "****** Fin Clientes Existentes *********"
        datos
    }

    def calcularTiempoTranscurrido(def fechaAComparar){
        ///println "Fecha a Comparar " + fechaAComparar
        def aniosTranscurridos = 0
        /*use(groovy.time.TimeCategory) {
        def duration = (new Date()) - fechaAComparar
        println "Duration : " + duration
        println("Han transcurrido ${duration.years}...")
        aniosTranscurridos = duration.years
        }*/
        Period diff = LocalDate.now().with { now ->
            Period.between(new java.sql.Date(fechaAComparar.getTime()).toLocalDate(), now)
        }
        //println "Han transcurrido " + diff.getYears() + " años"
        aniosTranscurridos = diff.getYears()
        aniosTranscurridos
    }

    def calcularMesesTranscurridos(def fechaAComparar){
        //println "Fecha a Comparar " + fechaAComparar
        def mesesTranscurridos = 0
        /*use(groovy.time.TimeCategory) {
        def duration = (new Date()) - fechaAComparar
        println "Duration : " + duration
        println("Han transcurrido ${duration.months}...")
        mesesTranscurridos = duration.months
        }*/
        Period diff = LocalDate.now().with { now ->
            Period.between((new java.sql.Date(fechaAComparar.getTime())).toLocalDate(), now)
        }
        ///println "Han transcurrido " + (diff.getMonths() + (diff.getYears() * 12))  + " meses - Full: " + diff
        mesesTranscurridos = (diff.getMonths() + (diff.getYears() * 12))
        mesesTranscurridos
    }

    def buscarPerfilExistente(def rfc){
        def respuesta = [:]
        def perfil = PerfilClienteExistente.findWhere(rfc: rfc)
        if(perfil){
            respuesta.perfil = perfil
            respuesta.encontrado = true
        } else {
            respuesta.encontrado = false
        }
        respuesta
    }

    def mensualizarCuota(def cuota, def periodicidad){
        def factor
        if(periodicidad == 1){
            factor = 1
        } else if (periodicidad == 2) {
            factor = 2.03
        } else if (periodicidad == 3) {
            factor = 4.35
        } 
        def cuotaMensualizada = cuota * factor
        cuotaMensualizada
    }

}