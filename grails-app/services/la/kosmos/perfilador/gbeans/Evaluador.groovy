package la.kosmos.perfilador.gbeans

import la.kosmos.app.Periodicidad
import la.kosmos.app.LimitePlazoProducto

class Evaluador {
	
    static String getEvaluacionPerfilador(def respuestaDictamenDePerfil)
    {
        String message;
        if(respuestaDictamenDePerfil == null )
        {
            message = "Se detectaron errores al obtener el dictamen de perfil del cliente";
        }
        else if(respuestaDictamenDePerfil.dictamen == 'R' ){
            message = "No cumple con el dictamen de perfil";
        }
        else if(respuestaDictamenDePerfil.dictamen =='A'){
            message = "A";
        }
        return message;   
    }
	   
    def   getIdPeriodicidad(String periodicidad ){
        String  nombrePeriodicidad;
        if (periodicidad == "M" || periodicidad =="Mensual"){
            nombrePeriodicidad = "'Mensual";	 
        }
        if (periodicidad == "Q" || periodicidad =="Quincenal"){
            nombrePeriodicidad = "Quincenal";
        }
        if (periodicidad == "S" || periodicidad =="Semanal"){
            nombrePeriodicidad = "Semanal";
        }
        def   idPeriodicidad =	Periodicidad.findByNombre(nombrePeriodicidad);
        return idPeriodicidad
    }
	 
    def getLimiteMontoMinimoProducto(def producto, Integer plazo, def periodicidad, Double montoMaximo){
        def limites = LimitePlazoProducto.findWhere(producto: producto,
            plazo: plazo, periodicidad: periodicidad)
        Double montoMinimo = limites.limiteMinimo
        if(montoMinimo > montoMaximo) {
            montoMinimo = montoMaximo
        }
        return montoMinimo;
    }
	
    String getDictamenProducto(String producto, def respuestaDictamenDePoliticas)
    {
        String dictamen =   ((
                respuestaDictamenDePoliticas.find {
                    (it."$producto" == "A" ||
                        it."$producto" == "D" ||
                        it."$producto" == "R")
                })?."$producto")
        return dictamen;
    }
	
}
