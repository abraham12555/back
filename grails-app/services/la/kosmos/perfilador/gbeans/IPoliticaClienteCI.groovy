package la.kosmos.perfilador.gbeans


import org.springframework.beans.factory.annotation.Autowired;

import la.kosmos.app.LimitePlazoProducto
import la.kosmos.app.Producto;

abstract class IPoliticaClienteCI {
	/*
	 * Esta clase tiene por objetivo aplicar las tres politicas definidas para los clientes con Capacidad de pago Insuficiente   
	 * de ahi el nombre de la Clase 
	 * 
	 * IPolitica --> se define como clase abstracta de tal forma que los metodos que aqui se definen puedan ser utilizados como
	 * base para aplicar las politicas de Capacidad de Pago Insuficiente  
	 * 
	 * 
	 * *
	 *
	 * Aplica la politica 1 para Clientes con capacidad de pago insuficiente,  hace uso de 7 parametros 
	 * creditoVigente: es un bojeto de tipo String
	 * banderaRenovacion: Objeto de tipo String 
	 * mesesLibroCreditoActivo: Objeto de tipo Integer 
	 * experienciaCrediticia: Objeto de tipo String
	 * montoMaxmimoExpPositiva: Objeto de tipo Double 
	 * montoCreditoActivo: Objeto de tipo Double
	 * productoActual: Objeto de tipo String 
	 * 
	 * Si se cumple la regla aplicada de la condicion el Producto a  ofertar será  la variable productoActual
	 * con el monto montoCreditoActivo 
	 *  
	 * **/


	static Map  politica1CCIProductoActual(def creditoVigente, def banderaRenovacion, def mesesLibroCreditoActivo,
			def experienciaCrediticia, def montoMaxmimoCreditosExpPositiva, def montoCreditoActivo, def productoActual
	){
		def derechoAProducto = [:]
		def montoPrestamo;
		if(creditoVigente =="S" && banderaRenovacion == "S"  && mesesLibroCreditoActivo >= 12
		&& experienciaCrediticia == "EP"	 &&  montoMaxmimoCreditosExpPositiva < montoCreditoActivo) {
			montoPrestamo = this.getMontoMaxmimoPrestamo(montoCreditoActivo, productoActual);
			derechoAProducto.put('Producto',productoActual)
			derechoAProducto.put('Monto',montoPrestamo.cantidadPrestmo)
                        derechoAProducto.put('Periodicidad',montoPrestamo.periodicidad)
			derechoAProducto.put('Plazo',montoPrestamo.plazo)
		}
		return  derechoAProducto;
	}

static Map politica2CCIProducto6096_6095_6088_6116(def creditoVigente, def banderaRenovacion,def experienciaCrediticia,
			def montoMaxmimoCreditosExpPositiva, def montoCreditoActivo, def numCreditLiquidExp) {
		def derechoAProducto = [:]
		def montoPrestamo;
		if( creditoVigente == "S" && banderaRenovacion == "S"
		&&  experienciaCrediticia == "EP" && montoMaxmimoCreditosExpPositiva >= montoCreditoActivo) {
                
			if (numCreditLiquidExp >= 1) {
				montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6096")
				derechoAProducto.put('Producto','6096')
				derechoAProducto.put('Monto',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo',montoPrestamo.plazo)

			}
			
			if(numCreditLiquidExp >= 2) {
				derechoAProducto.put('Producto1','6095')
                                montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6095")
				derechoAProducto.put('Monto1',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad1',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo1',montoPrestamo.plazo)
                                
				derechoAProducto.put('Producto2','6088')
                                montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6088")
				derechoAProducto.put('Monto2',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad2',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo2',montoPrestamo.plazo)
			}
			
			if(numCreditLiquidExp >= 3) {
				montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6116")
				derechoAProducto.put('Producto3','6116')
				derechoAProducto.put('Monto3',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad3',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo3',montoPrestamo.plazo)
			}
		}
		return derechoAProducto
	}
static  Map  politica3CCIProducto6096_6095_6088_6116(def creditoVigente,def experienciaCrediticia,
			def numCreditLiquidExp, def montoMaxmimoCreditosExpPositiva ){
		def derechoAProducto = [:]
		def montoPrestamo;
		if(creditoVigente.equals("N")	&& experienciaCrediticia.equals("EP") ) {
			if (numCreditLiquidExp >= 1) {
				derechoAProducto.put('Producto','6096')
				montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6096")
				derechoAProducto.put('Monto',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo',montoPrestamo.plazo)
			}
			
			if(numCreditLiquidExp >= 2) {
				derechoAProducto.put('Producto1','6095')
				montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6095")
				derechoAProducto.put('Monto1',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad1',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo1',montoPrestamo.plazo)
                                
				derechoAProducto.put('Producto2','6088')
				montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6088")
				derechoAProducto.put('Monto2',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad2',montoPrestamo.periodicidad)
				derechoAProducto.put('Plazo2',montoPrestamo.plazo)
			}
			
			if(numCreditLiquidExp >= 3) {
				derechoAProducto.put('Producto3','6116')
				montoPrestamo = this.getMontoMaxmimoPrestamo(montoMaxmimoCreditosExpPositiva, "6116")
				derechoAProducto.put('Monto3',montoPrestamo.cantidadPrestmo)
                                derechoAProducto.put('Periodicidad3',montoPrestamo.periodicidad)
                                derechoAProducto.put('Plazo3',montoPrestamo.plazo)		
                            }
		}
		return derechoAProducto
	}
/**
 * Metodo para obtener el monto maximo de prestamo para el producto, 
 * se aplica regla de negocio en donde se establece que si el monto de credito activo es mayor 
 * al limite a ofertar por el producto se oferta el limite maximo del producto
 * 
 * */	
static Map     getMontoMaxmimoPrestamo(def  montoCreditoActual,def  producto){
        def respuesta = [:];
        StringBuilder sql = new StringBuilder();
        sql.append("Select montoMaximo From Producto p where p.claveDeProducto = '").append(producto).append("' ");
        def  montoMaximoProducto  =  Producto.findByClaveDeProducto(producto);
        def results 
        if(montoCreditoActual > montoMaximoProducto.getMontoMaximo()) {
            respuesta.put('cantidadPrestmo',montoMaximoProducto.getMontoMaximo())
            results = (LimitePlazoProducto.executeQuery("Select max(lp.plazo),lp.periodicidad.id From LimitePlazoProducto lp where lp.producto.id = :productoId And (:cantidad  >= lp.limiteMinimo And :cantidad <= lp.limiteMaximo) group by lp.periodicidad.id", [productoId: montoMaximoProducto.id,cantidad: montoMaximoProducto.getMontoMaximo()])) as Set
            if (results.size() != 0) {
                respuesta.put('plazo',results[0][0])
                if(results[0][1] == 1){
                    respuesta.put('periodicidad',"M")
                }
                if(results[0][1] == 2){
                    respuesta.put('periodicidad',"Q")
                }
                if(results[0][1] == 3){
                    respuesta.put('periodicidad',"S")
                }
            }else{
                respuesta.put('periodicidad',null)
                respuesta.put('plazo',null)
            }
        }
        else {
            results = (LimitePlazoProducto.executeQuery("Select max(lp.plazo),lp.periodicidad.id From LimitePlazoProducto lp where lp.producto.id = :productoId And (:cantidad  >= lp.limiteMinimo And :cantidad <= lp.limiteMaximo) group by lp.periodicidad.id", [productoId: montoMaximoProducto.id,cantidad: montoCreditoActual.floatValue()])) as Set
            respuesta.put('cantidadPrestmo',montoCreditoActual)
            if (results.size() != 0) {
                respuesta.put('plazo',results[0][0])
                if(results[0][1] == 1){
                    respuesta.put('periodicidad',"M")
                }
                if(results[0][1] == 2){
                    respuesta.put('periodicidad',"Q")
                }
                if(results[0][1] == 3){
                    respuesta.put('periodicidad',"S")
                }
            }else{
                respuesta.put('periodicidad',null)
                respuesta.put('plazo',null)
            }
        }
        return respuesta;
    }
}
