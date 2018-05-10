package la.kosmos.perfilador.gbeans
import la.kosmos.app.TasaDinamicaProducto
import la.kosmos.app.GarantiaProducto

class PerfiladorDAO {
	
    def getTasaDinamicaDelProducto (def idProducto, def probabilidadDeMora  ){		
        def tasaDinamica = TasaDinamicaProducto.executeQuery("Select tdp From TasaDinamicaProducto tdp "+
										" Where tdp.producto.id = :idProducto And :probabilidadDeMora >= tdp.probabilidadDeIncumplimientoMinima " +
										" And :probabilidadDeMora <= tdp.probabilidadDeIncumplimientoMaxima ",
            [idProducto: idProducto , probabilidadDeMora: ((probabilidadDeMora * 100) as float)])
        return tasaDinamica		
    }
    def getGarantias(def idProducto, def montoMaximo ){
        def garantias = GarantiaProducto.executeQuery("Select gp From GarantiaProducto gp " +
											   		" Where gp.producto.id = :productoId And ( :monto >= gp.cantidadMinima And :monto <= gp.cantidadMaxima) order by gp.id",
            [productoId: idProducto, monto: (montoMaximo as float)])
        return garantias
    }

}
