package la.kosmos.app.logging.service.impl
/**@author Mike Martínez 
 * Definicion: Clase de utileria para generar codigos de Error  
 */
import java.text.SimpleDateFormat
import java.util.concurrent.ThreadLocalRandom

class GeneradorCodigoError {
	public static final String EX_BURO_CREDITO="EX_BC_";
	public static final String EX_MOTOR="EX_EN_";
	public static final String EX_CALIXTA="EX_CX_";
	public static final String BS_BURO_CREDITO="BS_BC_";
	public static final String BS_MOTOR="BS_EN_";
	public static final String BS_CALIXTA="BS_CX_";
	public static final Integer MINIMO = 0 ;
	public static final Integer MAXIMO = 1000;

	public static def getCodigoErrorBC(){
		StringBuilder codigoErrorBC = new StringBuilder();
		codigoErrorBC.append(EX_BURO_CREDITO).append(getFormatFecha()).append("_").append(getNumeroRandom())
		return 	codigoErrorBC.toString()
	}
	public static def getBusinessErrorBC(){
		StringBuilder codigoErrorBC = new StringBuilder();
		codigoErrorBC.append(BS_BURO_CREDITO).append(getFormatFecha()).append("_").append(getNumeroRandom())
		return 	codigoErrorBC.toString()
	}
	
    public static def getCodigoErrorEN(){
		StringBuilder codigoErrorEN = new StringBuilder();
		codigoErrorEN.append(EX_MOTOR).append(getFormatFecha()).append("_").append(getNumeroRandom())
		return 	codigoErrorEN.toString()
	}
	public static def getBusinessErrorEN(){
		StringBuilder codigoErrorEN = new StringBuilder();
		codigoErrorEN.append(BS_MOTOR).append(getFormatFecha()).append("_").append(getNumeroRandom())
		return 	codigoErrorEN.toString()
	}
	
    public static def getCodigoErrorCX(){
		StringBuilder codigoErrorCX = new StringBuilder();
		codigoErrorCX.append(EX_CALIXTA).append(getFormatFecha()).append("_").append(getNumeroRandom())
		return 	codigoErrorCX.toString()
	}
	public static def getBusinessErrorCX(){
		StringBuilder codigoErrorCX = new StringBuilder();
		codigoErrorCX.append(BS_CALIXTA).append(getFormatFecha()).append("_").append(getNumeroRandom())
		return 	codigoErrorCX.toString()
	}

	private static String getFormatFecha(){
		StringBuilder codigoFecha = new StringBuilder();
		Date date = new Date(); // your date
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		Integer anio = Integer.parseInt(String.valueOf(cal.get(Calendar.YEAR)).substring(2,4));
		codigoFecha.append(anio)
		Integer mes = (cal.get(Calendar.MONTH)+1);
		Integer dia = cal.get(Calendar.DAY_OF_MONTH);

		if(mes < 10)
		{
			codigoFecha.append("0").append(mes)
		}
		else{
			codigoFecha.append(mes)
		}
		if(dia < 10)
		{
			codigoFecha.append("0").append(dia)
		}
		else{
			codigoFecha.append(dia)
		}
		return codigoFecha.toString()
	}
	private static String  getNumeroRandom(){
		StringBuilder formatNumber = new StringBuilder();
		Integer numero =  ThreadLocalRandom.current().nextInt(MINIMO, MAXIMO);
		if(numero < 10 ){
			formatNumber.append("00").append(numero);
		}
		else if(numero >= 10 &&numero < 100 ){
			formatNumber.append("0").append(numero);
		}
		else{
			formatNumber.append(numero);
		}
		return 		formatNumber.toString();
	}

}