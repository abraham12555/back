package la.kosmos.app

import grails.transaction.Transactional

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.entity.ByteArrayEntity;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.InputSource

import java.io.File;
//import java.text.BreakDictionary;

//@Transactional
class BuroDeCreditoService {

	static transactional = false
	
	//ConfiguracionKosmos configuracion =  ConfiguracionKosmos.get(1)
	//def URL_BURO_DE_CREDITO = "http://128.9.55.102:9080/WSConsultaBCC/WSConsultaService"  //Enviroment QA
	//def URL_BURO_DE_CREDITO = configuracion.urlBuroCredito
	/*
	def config_xml = [
		'encabezadoVersion'                      : configuracion.encabezadoVersion,
		'encabezadoProductoRequerido'            : configuracion.encabezadoProductoRequerido,
		'encabezadoClavePais'            		 : configuracion.encabezadoClavePais,
		'encabezadoIdentificadorBuro'            : configuracion.encabezadoIdentificadorBuro,
		'encabezadoClaveUsuario'                 : configuracion.encabezadoClaveUsuario,
		'encabezadoPassword'                     : configuracion.encabezadoPassword,
		'encabezadoTipoConsulta'                 : configuracion.encabezadoTipoConsulta,
		'encabezadoTipoContrato'                 : configuracion.encabezadoTipoContrato,
		'encabezadoClaveUnidadMonetaria'         : configuracion.encabezadoClaveUnidadMonetaria,
		'encabezadoIdioma'                       : configuracion.encabezadoIdioma,
		'encabezadoTipoSalida'                   : configuracion.encabezadoTipoSalida,
		'autenticaTipoReporte'                   : configuracion.autenticaTipoReporte,
		'autenticaTipoSalidaAU'                  : configuracion.autenticaTipoSalidaAU
	] 
	*/
	
	def post(String xml) throws Exception{
		def URL_BURO_DE_CREDITO =  ConfiguracionKosmos.get(1).urlBuroCredito
		HttpClient client = new DefaultHttpClient();
		HttpPost post = new HttpPost("${URL_BURO_DE_CREDITO}");
		HttpEntity entity = new ByteArrayEntity(xml.getBytes("UTF-8"));
		post.setEntity(entity);
		HttpResponse response = client.execute(post);
		String result = EntityUtils.toString(response.getEntity());
		println "Respuesta BURO DE CREDITO ::"+result
		return result
	}
	
	
	def callWebServicePersonasFisicas(def datosBancarios, def datosPersonales, def direccion,SolicitudDeCredito solicitud){
		ConfiguracionKosmos config_xml =  ConfiguracionKosmos.get(1)
		StringBuilder soap = new StringBuilder()
		soap.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:bean=\"http://bean.consulta.ws.bc.com/\">")
		soap.append("<soapenv:Header/>")
		soap.append("<soapenv:Body>")
		   soap.append("<bean:consultaXML>")
				soap.append("<Consulta>")
				 soap.append("<Personas>")
					soap.append("<Persona>")
					   soap.append("<Encabezado>")
					   	  soap.append("<Version>${config_xml.encabezadoVersion}</Version>")
						  soap.append("<NumeroReferenciaOperador>1234567890     1234567890</NumeroReferenciaOperador>")
						  soap.append("<ProductoRequerido>${config_xml.encabezadoProductoRequerido}</ProductoRequerido>")
						  soap.append("<ClavePais>${config_xml.encabezadoClavePais}</ClavePais>")
						  soap.append("<IdentificadorBuro>${config_xml.encabezadoIdentificadorBuro}</IdentificadorBuro>")
						  soap.append("<ClaveUsuario>${config_xml.encabezadoClaveUsuario}</ClaveUsuario>")
						  soap.append("<Password>${config_xml.encabezadoPassword}</Password>")
						  soap.append("<TipoConsulta>${config_xml.encabezadoTipoConsulta}</TipoConsulta>")
						  soap.append("<TipoContrato>${config_xml.encabezadoTipoContrato}</TipoContrato>")
						  soap.append("<ClaveUnidadMonetaria>${config_xml.encabezadoClaveUnidadMonetaria}</ClaveUnidadMonetaria>")
						  soap.append("<ImporteContrato></ImporteContrato>")
						  soap.append("<Idioma>${config_xml.encabezadoIdioma}</Idioma>")
						  soap.append("<TipoSalida>${config_xml.encabezadoTipoSalida}</TipoSalida>")
					   soap.append("</Encabezado>")
					   soap.append("<Nombre>")
						  soap.append("<ApellidoPaterno>"+datosPersonales?.apellidoPaterno.toUpperCase()+"</ApellidoPaterno>")
						  soap.append("<ApellidoMaterno>"+datosPersonales?.apellidoMaterno.toUpperCase()+"</ApellidoMaterno>")
						  soap.append("<ApellidoAdicional></ApellidoAdicional>")
						  soap.append("<PrimerNombre>"+datosPersonales?.nombre.toUpperCase()+"</PrimerNombre>")
						  soap.append("<SegundoNombre></SegundoNombre>")
						  soap.append("<FechaNacimiento>"+obtenerFecha("${datosPersonales?.dia}","${datosPersonales?.mes}","${datosPersonales?.anio}")+"</FechaNacimiento>")
						  soap.append("<RFC>${datosPersonales?.rfc}</RFC>")
						  soap.append("<Prefijo></Prefijo>")
						  soap.append("<Sufijo></Sufijo>")
						  soap.append("<Nacionalidad>MX</Nacionalidad>")
						  soap.append("<Residencia>1</Residencia>")
						  soap.append("<NumeroLicenciaConducir></NumeroLicenciaConducir>")
						  if(datosPersonales?.estadoCivil == 1){
							soap.append("<EstadoCivil>S</EstadoCivil>")  
						  }else{
						  	soap.append("<EstadoCivil>C</EstadoCivil>")
						  }
						  if(datosPersonales?.sexo == 1){
							 soap.append("<Sexo>M</Sexo>")  
						  }else{
						  soap.append("<Sexo>F</Sexo>")
						  }
						  soap.append("<NumeroCedulaProfesional></NumeroCedulaProfesional>")
						  soap.append("<NumeroRegistroElectoral></NumeroRegistroElectoral>")
						  soap.append("<ClaveImpuestosOtroPais></ClaveImpuestosOtroPais>")
						  soap.append("<ClaveOtroPais></ClaveOtroPais>")
						  soap.append("<NumeroDependientes></NumeroDependientes>")
						  soap.append("<EdadesDependientes></EdadesDependientes>")
					   soap.append("</Nombre>")
					   soap.append("<Domicilios>")
						  soap.append("<Domicilio>")
							 soap.append("<Direccion1>"+direccion?.calle.toUpperCase()+" "+direccion?.noExterior.toUpperCase()+" "+direccion?.noInterior.toUpperCase()+"</Direccion1>")
							 soap.append("<Direccion2></Direccion2>")
							 soap.append("<ColoniaPoblacion>"+direccion?.colonia.toUpperCase()+"</ColoniaPoblacion>")
							 Municipio municipio = Municipio.findById(direccion?.municipio)
							 soap.append("<DelegacionMunicipio>"+municipio.nombre.toUpperCase()+"</DelegacionMunicipio>")
							 soap.append("<Ciudad></Ciudad>")
							 Estado estado = Estado.findById(direccion?.estado)
							 soap.append("<Estado>"+estado.siglasrenapo.toUpperCase()+"</Estado>")
							 soap.append("<CP>${direccion?.codigoPostal}</CP>")
							 soap.append("<FechaResidencia></FechaResidencia>")
							 soap.append("<NumeroTelefono></NumeroTelefono>")
							 soap.append("<Extension></Extension>")
							 soap.append("<Fax></Fax>")
							 soap.append("<TipoDomicilio></TipoDomicilio>")
							 soap.append("<IndicadorEspecialDomicilio></IndicadorEspecialDomicilio>")
						  soap.append("</Domicilio>")
					   soap.append("</Domicilios>")
					   soap.append("<Empleos>")
						  soap.append("<Empleo>")
							 soap.append("<NombreEmpresa></NombreEmpresa>")
							 soap.append("<Direccion1></Direccion1>")
							 soap.append("<Direccion2></Direccion2>")
							 soap.append("<ColoniaPoblacion></ColoniaPoblacion>")
							 soap.append("<DelegacionMunicipio></DelegacionMunicipio>")
							 soap.append("<Ciudad></Ciudad>")
							 soap.append("<Estado></Estado>")
							 soap.append("<CP></CP>")
							 soap.append("<NumeroTelefono></NumeroTelefono>")
							 soap.append("<Extension></Extension>")
							 soap.append("<Fax></Fax>")
							 soap.append("<Cargo></Cargo>")
							 soap.append("<FechaContratacion></FechaContratacion>")
							 soap.append("<ClaveMonedaSalario></ClaveMonedaSalario>")
							 soap.append("<Salario></Salario>")
							 soap.append("<BaseSalarial></BaseSalarial>")
							 soap.append("<NumeroEmpleado></NumeroEmpleado>")
							 soap.append("<FechaUltimoDiaEmpleo></FechaUltimoDiaEmpleo>")
						  soap.append("</Empleo>")
					   soap.append("</Empleos>")
					   soap.append("<CuentaC>")
						  soap.append("<NumeroCuenta></NumeroCuenta>")
						  soap.append("<ClaveOtorgante></ClaveOtorgante>")
						  soap.append("<NombreOtorgante></NombreOtorgante>")
					   soap.append("</CuentaC>")
					   soap.append("<Autentica>")
						  soap.append("<TipoReporte>${config_xml.autenticaTipoReporte}</TipoReporte>")
						  soap.append("<TipoSalidaAU>${config_xml.autenticaTipoSalidaAU}</TipoSalidaAU>")
						  soap.append("<ReferenciaOperador>GAMR550127HW4            </ReferenciaOperador>")
						  if(datosBancarios.tarjeta.equalsIgnoreCase("SI")){
							soap.append("<TarjetaCredito>V</TarjetaCredito>")  
							soap.append("<UltimosCuatroDigitos>${datosBancarios.numeroTarjeta}</UltimosCuatroDigitos>")
						  }else{
						  	soap.append("<TarjetaCredito>F</TarjetaCredito>")
						  }
						  if(datosBancarios.hipoteca.equalsIgnoreCase("SI")){
							soap.append("<EjercidoCreditoHipotecario>V</EjercidoCreditoHipotecario>")
						  }else{
						  	soap.append("<EjercidoCreditoHipotecario>F</EjercidoCreditoHipotecario>")
						  }
						  if(datosBancarios.creditoAutomotriz.equalsIgnoreCase("SI")){
							soap.append("<EjercidoCreditoAutomotriz>V</EjercidoCreditoAutomotriz>")
						  }else{
						    soap.append("<EjercidoCreditoAutomotriz>F</EjercidoCreditoAutomotriz>")
						  }
					   soap.append("</Autentica>")
					   soap.append("<Caracteristicas>")
						  soap.append("<Plantilla></Plantilla>")
					   soap.append("</Caracteristicas>")
					soap.append("</Persona>")
				 soap.append("</Personas>")
			  soap.append("</Consulta>")
		   soap.append("</bean:consultaXML>")
		soap.append("</soapenv:Body>")
	 soap.append("</soapenv:Envelope>")
	 
	 println "Request BuroCredito::> "+ soap.toString()
	 String response =null
	 def respuesta = [:]
	 
	 if(solicitud.reporteBuroCredito == null || solicitud.reporteBuroCredito.errorConsulta.contains("Exception")){
		  try{
			  if(ConfiguracionKosmos.get(1).habilitarMockBuroCredito){
				  if(ConfiguracionKosmos.get(1).habilitarMockBuroCreditoSuccess){
					  //Response SUCCESS
					  response="<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns2:consultaXMLResponse xmlns:ns2=\"http://bean.consulta.ws.bc.com/\"><return><Personas><Persona><Cuentas><Cuenta/></Cuentas><ConsultasEfectuadas><ConsultaEfectuada/></ConsultasEfectuadas><ReporteImpreso>INTL111234567890     1234567890MX0000ZM7687100110PN06GARCIA0008MARTINEZ0204ROSA0305MARIA0408010119550513GAMR550127HW40802MX090111101M1201FPA16AV REF 450 DEPTO0109PETROLERA0220POZA RICA DE HIDALGO0309POZA RICA0403VER05059329007078210260120830092003PA11MORELIA 2160018CIRCULO MICHOACANO0220POZA RICA DE HIDALGO0403VER050593210060805062000070782475721001H120815012003PA11MORELIA 2160309POZA RICA0403VER05059330507078247572120816122001PA15CALLE REF NO 380109PETROLERA0309POZA RICA0403VER050593290070700102601001H120824101996PE23UNIVERSIDAD VERACRUZANA0000071178278247572110801010001170818072004PE15NOPROPORCIONADO0000TL08060420050216TIENDA COMERCIAL0501I0601R0702CL0802N\$1101Z120410081308180720041408050220051508081220041708310320051801A1908010820042104707922055880+23048000240346826020227091X1111000280828022005290828062004TL08090420050205BANCO0501I0601R0702CC0802N\$1101Z12034831308060420041408150320051508061120041708310320051801A190830042004210510814220610734+230511000260201271111111111111280828022005290828042004TL08090420050205BANCO0501C0601I0702PL0802N\$1002231101M12037881308050320041408010420051708310320051801A210530000220616626+2602012712111111111111280828022005290828032004TL08090420050216TIENDA COMERCIAL0501I0601R0702CC0802N\$1101Z1202301308250920031408160320051508151120041708310320051801A210417942204179+2304300025013260201271843212121432132200028082802200529082809200331030113202063302033402023603611370828022005380204TL08120420050209SERVICIOS0501I0601O0702MI0802N\$1101M120101308020619971408010120001608280720001708310320051801A1908310320052104137022020+24010260201272211111111111111111111112808280220052908280520033002CCTL08090420050205BANCO0501I0601R0702CC0802N\$1101Z12032001308200120031408310320051508280320051708310320051801A19083101200321046685220365+230477002602012724111111111111111111111111280828022005290828012003TL08070420050216TIENDA COMERCIAL0501I0601R0702CL0802N\$1101Z12032711308010119931408150320051508020220051708310320051801A1908301020032104402622051557+2304150026020127241X11221212154X3X111132132808280220052908280419983002PL36010370828032004380205TL08070220000210FINANCIERA0501I0601I0702CL0802N\$090430051101M120101308090119981408130719981608130719981708310120001801A2104300522020+2401026020127201111111111111XXXX111280831121999290801051998IQ08280920160110ZM768710010206KOSMOS0402CC060100701IIQ08010320050210FINANCIERA0402PL060100701IIQ08180720040216TIENDA COMERCIAL0402CC060100701IIQ08220420040205BANCO0402RE060100701IIQ08270320040216TIENDA COMERCIAL0402CC060100701IIQ08040320040205BANCO0402UK060100701IIQ08120220040205BANCO0402UK060100701IIQ08301220030205BANCO0402CC060100701IIQ08250920030216TIENDA COMERCIAL0402CC060100701IIQ08150120030205BANCO0402UK060100701IRS08261119950002000102000202000302000402000502010602070702000802000904000810040002110400061204000213040001140400021502001602001701N1805NNYNY1901N2002MX210530398220531200230618415+240346825041992260259270530000280616626+290103003788310200320200330200340801011993350818072004360209370801032005380200390800000000400200410800000000HI081404201500039900116INF DE JUICIOS  024724-OME-ACD(15-04)-000698/2015-JAL-GDL MERC 2 S2HI082409200900039900116INF DE JUICIOS  024704-ORC-ACD(09-10)-001399/2009-DF-CIV 10 SEC 1  HI082809201600039900116INF DE JUICIOS  024704-USU-ACD(10-07)-000239/2009-EM-NEZA. CIV 2 S1HR082007201100039900116INF DE JUICIOS  024718-EJM-ACD(11-12)-001177/2009-GTO-CELAYA M C6 SHR081004201500039900116INF DE JUICIOS  024724-ORM-APS(15-04)-000088/2015-QR-CANCUN MERC1S2HR080206201100039900116INF DE JUICIOS  024718-EJM-ACD(11-07)-000223/2011-EM-NAU.MC 2 S1   HR082809201600039900116INF DE JUICIOS  024704-EJM-ACD(10-10)-000013/2007-SLP-CERRITOS C1 SHR081808200900039900116INF DE JUICIOS  024719-EJM-ACD(09-08)-000332/2009-BCS-LA PAZ M MX1SHR080706201100039900116INF DE JUICIOS  024718-EJM-ACD(09-10)-000898/2009-GTO-CELAYA M C6 SHR082809201600039900116INF DE JUICIOS  024718-EJM-ACD(09-11)-001177/2009-GTO-CELAYA M C6 SHR082810201100039900116INF DE JUICIOS  024712-ORC-ACD(12-01)-001339/2011-NL-CADEREYTA MX1SHR082809201600039900116INF DE JUICIOS  024704-SUH-ACD(08-02)-000636/2007-BCN-MEXICALI C4 SHR080407201100039900116INF DE JUICIOS  024704-CIV-ACD(11-10)-000721/2011-DF-CIV 83 SEC 1  HR082809201600039900116INF DE JUICIOS  024718-EJM-ACD(11-06)-001473/2008-GTO-CELAYA MN C2SHR082809201600039900116INF DE JUICIOS  024712-ORC-ACD(10-05)-000101/2010-NL-CADEREYTA MX1SHR082809201600039900116INF DE JUICIOS  024704-ESH-ACD(12-01)-001614/2010-DF-CIV 21 SEC 2  HR082809201600039900116INF DE JUICIOS  024704-ORC-ACD(09-09)-000623/2008-GTO-ACAMBARO C3 SSC08BC SCORE00030010104-008ES0504604001010003241250102**</ReporteImpreso></Persona></Personas></return></ns2:consultaXMLResponse></soapenv:Body></soapenv:Envelope>"
				  }else{
				  	  //Response FAIL
				  	  response="<?xml version=\"1.0\" encoding=\"UTF-8\"?><soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"><soapenv:Body><ns2:consultaXMLResponse xmlns:ns2=\"http://bean.consulta.ws.bc.com/\"><return><Personas><Persona><Cuentas><Cuenta/></Cuentas><ConsultasEfectuadas><ConsultaEfectuada/></ConsultasEfectuadas><ReporteImpreso>ERRRUR25                         1101YES05000530002**</ReporteImpreso></Persona></Personas></return></ns2:consultaXMLResponse></soapenv:Body></soapenv:Envelope>"
				  }
			  }else{
				 //Response PROD
				 response = post(soap.toString())
			  }
			  return analyzeReport(response,solicitud)
		  }catch(Exception e){
			 if(solicitud.reporteBuroCredito != null){
				 solicitud.reporteBuroCredito.errorConsulta=e.toString()
				 solicitud.reporteBuroCredito.save(flush:true)
			 }else{
				 ReporteBuroCredito reporteBuro = new ReporteBuroCredito()
				 reporteBuro.errorConsulta=e.toString()
				 reporteBuro.save(flush:true)
			 }
		  }
		  
	 }else{
	 	if(solicitud.reporteBuroCredito.errorConsulta == null){
			 respuesta.status = 200 
		 }else{
		 	respuesta.error = 500
			respuesta.errorDesc = "No se pudo obtener el reporte de buro de credito en un primer intento, En breve recibiras ayuda."
		 }
		return respuesta
	 
	 }
	}
	
	def analyzeReport(String xml,SolicitudDeCredito solicitud){
		def respuesta = [:]
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new InputSource(new StringReader(xml)));
		NodeList reporteImpreso = doc.getElementsByTagName("ReporteImpreso");
		if (reporteImpreso.getLength() > 0) {
			Element  reporte = (Element)reporteImpreso.item(0);
			ReporteBuroCredito reporteBuroCredito = obtenerDatosPersonales(reporte.getTextContent())
			if(reporteBuroCredito != null){
				respuesta.status = 200
				solicitud.reporteBuroCredito = reporteBuroCredito
				solicitud.save(flush:true)
				//respuesta.score   = obtenerScore(reporte.getTextContent())
			}else{
				respuesta.error = 500
				if(solicitud.reporteBuroCredito != null){
					solicitud.reporteBuroCredito.errorConsulta=reporte.getTextContent()
					solicitud.reporteBuroCredito.save(flush:true)
				}else{
					ReporteBuroCredito reporteBuro = new ReporteBuroCredito()
					reporteBuro.errorConsulta=reporte.getTextContent()
					reporteBuro.save(flush:true)
					solicitud.reporteBuroCredito = reporteBuro 
					solicitud.save(flush:true)
				}
				
				
			}
		} else {
			respuesta.error = 500
			respuesta.errorDesc = "Exception No se pudo obtener reporte. Favor de Volver a intentarlo."
			if(solicitud.reporteBuroCredito != null){
				solicitud.reporteBuroCredito.errorConsulta=respuesta.errorDesc
				solicitud.reporteBuroCredito.save(flush:true)
			}else{
				ReporteBuroCredito reporteBuro = new ReporteBuroCredito()
				reporteBuro.errorConsulta=respuesta.errorDesc
				reporteBuro.save(flush:true)
				solicitud.reporteBuroCredito = reporteBuro
				solicitud.save(flush:true)
			}

		}
		println "RESPONSE BURO DE CREDITO::"+ respuesta
		return respuesta
	}
	
	def obtenerScore(String reporte){
		int indice = reporte.indexOf("SCORE")
		def score = reporte.substring(indice+5,indice+8)
		return score
	}
	
	def obtenerDatosPersonales(String reporte){
		/*
		 * PN Nombre del Cliente
		 * PA Direccion del Cliente
		 * PE Empleo del Cliente.
		 * PI Informacion de Cuentas o Creditos.	
		 * CL Criterios Adicionales
		 * TL Cuentas o Creditos.
		 * IQ Consultas realizadas al expediente
		 * RS Resumen de Expediente
		 * HI Coincidencias con los datos de la Consulta
		 * HR Coincidencias con los datos en BD Buro de Credito.
		 * CR Declarativa del Cliente
		 * SC Contiene el BC Score. 
		 * ES Fin del Registro
		 */
	
		String datosPersonales = null
		String etiqueta = "PN"
		String numeroCampo = "PN"
		ReporteBuroCredito reporteBuro = new ReporteBuroCredito()
		SintetizaBuroCredito sintetiza = new SintetizaBuroCredito()
		RefCredBuroCredito referencia = null
		DireccionBuroDeCredito direccion = null 
		EmpleoBuroDeCredito empleo = null
		CreditoClienteBuroCredito credito = null
		ConsultasBuroCredito consulta = null
		AlertaBuroCredito alerta = null
		AlertaBuroCredito alertaHr = null
		DeclaConsBuroCredito declarativa= new DeclaConsBuroCredito()
		ScoreBuroCredito score = new ScoreBuroCredito()
		SegFinBuroCredito segfinal = new SegFinBuroCredito()
		ResumenBuroCredito resumen = new ResumenBuroCredito()
		try{
			if (reporte.contains('INTL')){
				int indiceInicial = reporte.indexOf(etiqueta)
				int indiceFinal = reporte.length()
				String subreporte = reporte.substring(indiceInicial,indiceFinal)
				int saltoNumeroCampo = 2
				datosPersonales = ""
				indiceInicial=0
				while(indiceInicial <= subreporte.length() && etiqueta != "ES"){
					numeroCampo = subreporte.substring(indiceInicial ,indiceInicial + saltoNumeroCampo)
					
					if(numeroCampo.equalsIgnoreCase("PN") || numeroCampo.equalsIgnoreCase("PA") || numeroCampo.equalsIgnoreCase("PE") || numeroCampo.equalsIgnoreCase("PI") || numeroCampo.equalsIgnoreCase("CL")
						|| numeroCampo.equalsIgnoreCase("TL") || numeroCampo.equalsIgnoreCase("IQ") || numeroCampo.equalsIgnoreCase("RS") || numeroCampo.equalsIgnoreCase("HI")
						|| numeroCampo.equalsIgnoreCase("HR") || numeroCampo.equalsIgnoreCase("CR") || numeroCampo.equalsIgnoreCase("SC") || numeroCampo.equalsIgnoreCase("ES")){
						etiqueta = numeroCampo
						println "ETIQUETA::"+ etiqueta
					}
					
					
					println "NumeroDeCampo:"+numeroCampo
					int longitud = Integer.parseInt(subreporte.substring(indiceInicial + saltoNumeroCampo,indiceInicial+saltoNumeroCampo + saltoNumeroCampo))
					println "Longitud:"+longitud
					datosPersonales = subreporte.substring(indiceInicial + saltoNumeroCampo + saltoNumeroCampo ,indiceInicial + saltoNumeroCampo + saltoNumeroCampo+  longitud ) +" "
					println "DATOS:"+subreporte.substring(indiceInicial + saltoNumeroCampo + saltoNumeroCampo ,indiceInicial + saltoNumeroCampo + saltoNumeroCampo+  longitud )
					
					switch(etiqueta){
						case "PN":
							if(numeroCampo.equals("PN")){
								reporteBuro.save()
								reporteBuro.apellidoPaterno = datosPersonales 
							}
							if(numeroCampo.equals("00")){reporteBuro.apellidoMaterno = datosPersonales }
							if(numeroCampo.equals("01")){reporteBuro.apellidoAdicional = datosPersonales }
							if(numeroCampo.equals("02")){reporteBuro.primerNombre = datosPersonales }
							if(numeroCampo.equals("03")){reporteBuro.segundoNombre = datosPersonales }
							if(numeroCampo.equals("04")){reporteBuro.fechaNacimiento = datosPersonales }
							if(numeroCampo.equals("05")){reporteBuro.rfc = datosPersonales }
							if(numeroCampo.equals("06")){reporteBuro.prefijoProfesinal = datosPersonales }
							if(numeroCampo.equals("07")){reporteBuro.sufijoPersonal = datosPersonales }
							if(numeroCampo.equals("08")){reporteBuro.nacionalidad = datosPersonales }
							if(numeroCampo.equals("09")){reporteBuro.tipoResidencia = datosPersonales }
							if(numeroCampo.equals("10")){reporteBuro.numeroLicenciaConducir = datosPersonales }
							if(numeroCampo.equals("11")){reporteBuro.estadoCivil = datosPersonales }
							if(numeroCampo.equals("12")){reporteBuro.genero = datosPersonales }
							if(numeroCampo.equals("13")){reporteBuro.numeroCedulaProfesional = datosPersonales }
							if(numeroCampo.equals("14")){reporteBuro.numeroIFE = datosPersonales }
							if(numeroCampo.equals("15")){reporteBuro.curp = datosPersonales }
							//if(numeroCampo.equals("16")){reporteBuro. = datosPersonales }
							if(numeroCampo.equals("17")){reporteBuro.numeroDependientes = datosPersonales }
							if(numeroCampo.equals("18")){reporteBuro.edadDependientes = datosPersonales }
						break;
						case "PA":
							if(numeroCampo.equals("PA")){
								if(direccion != null){
									reporteBuro.save(flush:true)
									direccion.reporteBuroCredito = reporteBuro
									direccion.save(flush:true)
									//direcciones.add(direccion)
								}
								direccion = new DireccionBuroDeCredito()
								direccion.direccionPrimeraLinea = datosPersonales
							}
							if(numeroCampo.equals("00")){direccion.direccionSegundaLinea = datosPersonales }
							if(numeroCampo.equals("01")){direccion.colonia = datosPersonales }
							if(numeroCampo.equals("02")){direccion.municipio = datosPersonales }
							if(numeroCampo.equals("03")){direccion.ciudad = datosPersonales }
							if(numeroCampo.equals("04")){direccion.estado = datosPersonales }
							if(numeroCampo.equals("05")){direccion.codigoPostal = datosPersonales }
							if(numeroCampo.equals("06")){direccion.fechaResidencia = datosPersonales }
							if(numeroCampo.equals("07")){direccion.numeroTelefono = datosPersonales }
							if(numeroCampo.equals("08")){direccion.extensionTelefono = datosPersonales }
							if(numeroCampo.equals("09")){direccion.numeroFax = datosPersonales }
							if(numeroCampo.equals("10")){direccion.tipoDomicilio = datosPersonales }
							if(numeroCampo.equals("11")){direccion.indicadorEspecialDomicilio = datosPersonales }
						break;
						case "PE":
							if(numeroCampo.equals("PE")){
								if(empleo != null){
									reporteBuro.save(flush:true)
									empleo.reporteBuroCredito = reporteBuro
									empleo.save(flush:true)
									//empleos.add(empleo)
								}
								empleo = new EmpleoBuroDeCredito()
								empleo.razonSocial = datosPersonales
							}
							if(numeroCampo.equals("00")){empleo.direccionLinea1 = datosPersonales }
							if(numeroCampo.equals("01")){empleo.direccionLinea2 = datosPersonales }
							if(numeroCampo.equals("02")){empleo.colonia = datosPersonales }
							if(numeroCampo.equals("03")){empleo.municipio = datosPersonales }
							if(numeroCampo.equals("04")){empleo.ciudad = datosPersonales }
							if(numeroCampo.equals("05")){empleo.estado = datosPersonales }
							if(numeroCampo.equals("06")){empleo.codigoPostal = datosPersonales }
							if(numeroCampo.equals("07")){empleo.numeroTelefonico = datosPersonales }
							if(numeroCampo.equals("08")){empleo.extensionTelefonica = datosPersonales }
							if(numeroCampo.equals("09")){empleo.numeroFax = datosPersonales }
							if(numeroCampo.equals("10")){empleo.cargo = datosPersonales }
							if(numeroCampo.equals("11")){empleo.fechaContratacion = datosPersonales }
							if(numeroCampo.equals("12")){empleo.claveMonedaPago = datosPersonales }
							if(numeroCampo.equals("13")){empleo.sueldo = datosPersonales }
							if(numeroCampo.equals("14")){empleo.periodoDePago = datosPersonales }
							if(numeroCampo.equals("15")){empleo.numeroEmpleado = datosPersonales }
							if(numeroCampo.equals("16")){empleo.fechaUltimoDiaEmpleo = datosPersonales }
						break;
						case "PI":
							if(numeroCampo.equals("PI")){
								if(referencia != null){
									reporteBuro.save(flush:true)
									referencia.reporteBuroCredito = reporteBuro
									referencia.save(flush:true)
									//referencias.add(referencia)
								}
								referencia = new RefCredBuroCredito()
								referencia.numeroCuenta = datosPersonales
							}
							if(numeroCampo.equals("00")){referencia.claveUsuario = datosPersonales }
							if(numeroCampo.equals("01")){referencia.nombreUsuario = datosPersonales }
						break;
						case "CL":
							if(numeroCampo.equals("CL")){
								sintetiza.reporteBuroCredito = reporteBuro
								sintetiza.plantillaSolicitada = datosPersonales 
							}
							if(numeroCampo.equals("00")){sintetiza.identificador = datosPersonales }
							if(numeroCampo.equals("01")){sintetiza.numeroCaracteristica = datosPersonales }
							if(numeroCampo.equals("02")){sintetiza.valorCaracteristica = datosPersonales }
							if(numeroCampo.equals("03")){sintetiza.codigoError = datosPersonales }
						break;
						case "TL":
							if(numeroCampo.equals("TL")){
								if(credito != null){
									reporteBuro.save(flush:true)
									credito.reporteBuroCredito = reporteBuro
									credito.save(flush:true)
									//creditos.add(credito)
								}
								credito = new CreditoClienteBuroCredito()
								credito.fechaActualizacion = datosPersonales
							}
							if(numeroCampo.equals("00")){credito.registroImpugnado = datosPersonales }
							if(numeroCampo.equals("01")){credito.claveUsuario = datosPersonales }
							if(numeroCampo.equals("02")){credito.nombreUsuario = datosPersonales }
							if(numeroCampo.equals("03")){credito.numeroTelefonoUsuario = datosPersonales }
							if(numeroCampo.equals("04")){credito.numeroDeCuenta = datosPersonales }
							if(numeroCampo.equals("05")){credito.tipoResponsabilidadCuenta = datosPersonales }
							if(numeroCampo.equals("06")){credito.tipoDeCuenta = datosPersonales }
							if(numeroCampo.equals("07")){credito.tipoContratoProducto = datosPersonales }
							if(numeroCampo.equals("08")){credito.monedaCredito = datosPersonales }
							if(numeroCampo.equals("09")){credito.importeDelAvaluo = datosPersonales }
							if(numeroCampo.equals("10")){credito.numeroPagos = datosPersonales }
							if(numeroCampo.equals("11")){credito.frecuenciaDePagos = datosPersonales }
							if(numeroCampo.equals("12")){credito.montoAPagar = datosPersonales }
							if(numeroCampo.equals("13")){credito.fechaAperturaDeCuenta = datosPersonales }
							if(numeroCampo.equals("14")){credito.fechaUltimoPago = datosPersonales }
							if(numeroCampo.equals("15")){credito.fechaUltimaCompra = datosPersonales }
							if(numeroCampo.equals("16")){credito.fechaCierre = datosPersonales }
							if(numeroCampo.equals("17")){credito.fechaDeReporteDeInformacion = datosPersonales }
							if(numeroCampo.equals("18")){credito.montoAReportar = datosPersonales }
							if(numeroCampo.equals("19")){credito.ultimaFechaConSaldoEnCero = datosPersonales }
							if(numeroCampo.equals("20")){credito.garantia = datosPersonales }
							if(numeroCampo.equals("21")){credito.creditoMaximoAutorizado = datosPersonales }
							if(numeroCampo.equals("22")){credito.saldoActual = datosPersonales }
							if(numeroCampo.equals("23")){credito.limiteCredito = datosPersonales }
							if(numeroCampo.equals("24")){credito.saldoVencido = datosPersonales }
							if(numeroCampo.equals("25")){credito.numeroDePagosVencidos = datosPersonales }
							if(numeroCampo.equals("26")){credito.clasificacionPuntialidadPago = datosPersonales }
							if(numeroCampo.equals("27")){credito.historicoPagos = datosPersonales }
							if(numeroCampo.equals("28")){credito.fechaMasRecienteHistoricoDePagos = datosPersonales }
							if(numeroCampo.equals("29")){credito.fechaMasAntiguaHistoricoDePagos = datosPersonales }
							if(numeroCampo.equals("30")){credito.claveDeObservacion = datosPersonales }
							if(numeroCampo.equals("31")){credito.totalPagosReportados = datosPersonales }
							if(numeroCampo.equals("32")){credito.totalPagosConMop02 = datosPersonales }
							if(numeroCampo.equals("33")){credito.totalPagosConMop03 = datosPersonales }
							if(numeroCampo.equals("34")){credito.totalPagosConMop04 = datosPersonales }
							if(numeroCampo.equals("35")){credito.totalPagosConMop05oMayor = datosPersonales }
							if(numeroCampo.equals("36")){credito.saldoEnlaMorosidadHistoricaMasAlta = datosPersonales }
							if(numeroCampo.equals("37")){credito.fechaEnlaMorosidadHistoricaMasAlta = datosPersonales }
							if(numeroCampo.equals("38")){credito.clasificacionPuntualidadPagoMopMorosidadMasAlta = datosPersonales }
							if(numeroCampo.equals("42")){credito.fechaInicioRestructura = datosPersonales }
							if(numeroCampo.equals("45")){credito.montoUltimoPago = datosPersonales }
						break;
						case "IQ":
							if(numeroCampo.equals("IQ")){
								if(consulta != null){
									reporteBuro.save(flush:true)
									consulta.reporteBuroCredito = reporteBuro
									consulta.save(flush:true)
									//consultas.add(consulta)
								}
								consulta = new ConsultasBuroCredito()
								consulta.fechaConsulta = datosPersonales
							}
							if(numeroCampo.equals("01")){consulta.claveDelUsuario = datosPersonales }
							if(numeroCampo.equals("02")){consulta.nombreDelUsuario = datosPersonales }
							if(numeroCampo.equals("03")){consulta.numeroDeTelefono = datosPersonales }
							if(numeroCampo.equals("04")){consulta.tipoDeContrato = datosPersonales }
							if(numeroCampo.equals("05")){consulta.monedaDelCredito = datosPersonales }
							if(numeroCampo.equals("06")){consulta.importeDelContrato = datosPersonales }
							if(numeroCampo.equals("07")){consulta.tipoResponsabilidadCuenta = datosPersonales }
							if(numeroCampo.equals("08")){consulta.indicadorDeClienteNuevo = datosPersonales }	
						break;
						case "RS":
							if(numeroCampo.equals("RS")){
								resumen.reporteBuroCredito = reporteBuro
								resumen.fechaIntegracion = datosPersonales 
							}
							if(numeroCampo.equals("00")){resumen.numeroCuentaMop07 = datosPersonales }
							if(numeroCampo.equals("01")){resumen.numeroCuentaMop06 = datosPersonales }
							if(numeroCampo.equals("02")){resumen.numeroCuentaMop05 = datosPersonales }
							if(numeroCampo.equals("03")){resumen.numeroCuentaMop04 = datosPersonales }
							if(numeroCampo.equals("04")){resumen.numeroCuentaMop03 = datosPersonales }
							if(numeroCampo.equals("05")){resumen.numeroCuentaMop02 = datosPersonales }
							if(numeroCampo.equals("06")){resumen.numeroCuentaMop01 = datosPersonales }
							if(numeroCampo.equals("07")){resumen.numeroCuentaMop00 = datosPersonales }
							if(numeroCampo.equals("08")){resumen.numeroCuentaMopUR = datosPersonales }
							if(numeroCampo.equals("09")){resumen.numeroCuentas = datosPersonales }
							if(numeroCampo.equals("10")){resumen.cuentasPagosFijosHipotecarios = datosPersonales }
							if(numeroCampo.equals("11")){resumen.noCuentasRevolventes = datosPersonales }
							if(numeroCampo.equals("12")){resumen.noCuentasCerradas = datosPersonales }
							if(numeroCampo.equals("13")){resumen.noCuentasConMorosidadActual = datosPersonales }
							if(numeroCampo.equals("14")){resumen.noCuentasConHistorialMorosidad = datosPersonales }
							if(numeroCampo.equals("15")){resumen.noCuentasEnAclaracion = datosPersonales }
							if(numeroCampo.equals("16")){resumen.noSolicitudConsultas = datosPersonales }
							if(numeroCampo.equals("17")){resumen.nuevaDireccionEn60Dias = datosPersonales }
							if(numeroCampo.equals("18")){resumen.mensajesAlerta = datosPersonales }
							if(numeroCampo.equals("19")){resumen.declarativa = datosPersonales }
							if(numeroCampo.equals("20")){resumen.monedaCredito = datosPersonales }
							if(numeroCampo.equals("21")){resumen.totalCreditoMaximosCuentasRevol = datosPersonales }
							if(numeroCampo.equals("22")){resumen.totalLimiteCreditoCuentasRevol = datosPersonales }
							if(numeroCampo.equals("23")){resumen.totalSaldoActualCuentasRevol = datosPersonales }
							if(numeroCampo.equals("24")){resumen.totalSaldosVencidosCuentasRevol = datosPersonales }
							if(numeroCampo.equals("25")){resumen.totalImportePagoCuentasRevol = datosPersonales }
							if(numeroCampo.equals("26")){resumen.porcentajeLimiteCreditoCuentasRevol = datosPersonales }
							if(numeroCampo.equals("27")){resumen.totalCreditoMaximoCuentasPagosFijosHipo = datosPersonales }
							if(numeroCampo.equals("28")){resumen.totalSaldosActualesCuentasPagosFijosHipo = datosPersonales }
							if(numeroCampo.equals("29")){resumen.totalSaldoVencidoCuentasPagosFijosHipo = datosPersonales }
							if(numeroCampo.equals("30")){resumen.totalImporteCuentasPagosFijosHipo = datosPersonales }
							if(numeroCampo.equals("31")){resumen.numeroCuentaMop96 = datosPersonales }
							if(numeroCampo.equals("32")){resumen.numeroCuentaMop97 = datosPersonales }
							if(numeroCampo.equals("33")){resumen.numeroCuentaMop99 = datosPersonales }
							if(numeroCampo.equals("34")){resumen.fechaAperturaCuentaMasAntigua = datosPersonales }
							if(numeroCampo.equals("35")){resumen.fechaAperturaCuentaMasReciente = datosPersonales }
							if(numeroCampo.equals("36")){resumen.numeroSolicitudesInformeBuro = datosPersonales }
							if(numeroCampo.equals("37")){resumen.fechaConsultaMasReciente = datosPersonales }
							if(numeroCampo.equals("38")){resumen.numeroCuentasEnDespachoCobranza = datosPersonales }
							if(numeroCampo.equals("39")){resumen.fechaAperturaMasRecienteDespachoCobranza = datosPersonales }
							if(numeroCampo.equals("40")){resumen.numeroSolicitudesInformeBuroPorDespachoCobranza = datosPersonales }
							if(numeroCampo.equals("41")){resumen.fechaConsultaMasRecientePorDespachoCobranza = datosPersonales }
						break;
						case "HI":
							if(numeroCampo.equals("HI")){
								if(alerta != null){
									reporteBuro.save(flush:true)
									alerta.reporteBuroCredito = reporteBuro
									alerta.save(flush:true)
									//alertas.add(alerta)
								}
								alerta = new AlertaBuroCredito()
								alerta.fechaReporte = datosPersonales
							}
							if(numeroCampo.equals("00")){alerta.codigoPrevension = datosPersonales }
							if(numeroCampo.equals("01")){alerta.tipoUsuario = datosPersonales }
							if(numeroCampo.equals("02")){alerta.mensaje = datosPersonales }
						break;
						case "HR":
							if(numeroCampo.equals("HR")){
								if(alertaHr != null){
									reporteBuro.save(flush:true)
									alertaHr.reporteBuroCredito = reporteBuro
									alertaHr.save(flush:true)
									//alertasHr.add(alertaHr)
								}
								alertaHr = new AlertaBuroCredito()
								alertaHr.fechaReporte = datosPersonales
							}
							if(numeroCampo.equals("00")){alertaHr.codigoPrevension = datosPersonales }
							if(numeroCampo.equals("01")){alertaHr.tipoUsuario = datosPersonales }
							if(numeroCampo.equals("02")){alertaHr.mensaje = datosPersonales }
						break;
						case "CR":
							if(numeroCampo.equals("CR")){
								declarativa.reporteBuroCredito = reporteBuro
								declarativa.tipoSegmento = datosPersonales 
							}
							if(numeroCampo.equals("00")){declarativa.declarativaCliente = datosPersonales }
						break;
						case "SC":
							if(numeroCampo.equals("SC")){
								score.reporteBuroCredito = reporteBuro
								score.nombreScore = datosPersonales 
							}
							if(numeroCampo.equals("00")){score.codigoScore = datosPersonales }
							if(numeroCampo.equals("01")){score.valorScore = datosPersonales }
							if(numeroCampo.equals("02")){score.codigoRazonPrimera = datosPersonales }
							if(numeroCampo.equals("03")){score.codigoRazonSegunda = datosPersonales }
							if(numeroCampo.equals("04")){score.codigoRazonTercera = datosPersonales }
							if(numeroCampo.equals("06")){score.codigoError = datosPersonales }
						break;
						case "ES":
							if(numeroCampo.equals("ES")){
								segfinal.reporteBuroCredito = reporteBuro
								segfinal.longiudTransmision = datosPersonales 
							}
							if(numeroCampo.equals("00")){segfinal.numeroControlConsulta = datosPersonales }
							if(numeroCampo.equals("01")){segfinal.finRegistroRespuesta = datosPersonales }
						break;
					}
					indiceInicial = indiceInicial + longitud + saltoNumeroCampo  + saltoNumeroCampo
				}
				
				if(direccion != null){
					direccion.reporteBuroCredito = reporteBuro
					direccion.save(flush:true)
					//direcciones.add(direccion)
				}
				if(empleo != null){
					empleo.reporteBuroCredito = reporteBuro
					empleo.save()
					//empleos.add(empleo)
				}
				if(referencia != null){
					referencia.reporteBuroCredito = reporteBuro
					referencia.save(flush:true)
					//referencias.add(referencia)
				}
				if(credito != null){
					credito.reporteBuroCredito = reporteBuro
					credito.save(flush:true)
					//creditos.add(credito)
				}
				if(consulta != null){
					consulta.reporteBuroCredito = reporteBuro
					consulta.save(flush:true)
					//consultas.add(consulta)
				}
				if(alerta != null){
					alerta.reporteBuroCredito = reporteBuro
					alerta.save(flush:true)
					//alertas.add(alerta)
				}
				if(alertaHr != null){
					alertaHr.reporteBuroCredito = reporteBuro
					alertaHr.save(flush:true)
					//alertasHr.add(alertaHr)
				}
				reporteBuro.save(flush:true)
				resumen.save(flush:true)
				declarativa.save(flush:true)
				score.save(flush:true)
				segfinal.save(flush:true)
				/*println "RESUMEN :" +reporteBuro.toString()+ " DIRECCIONES: " +direcciones.size() +" EMPLEOS:"+ empleos.size() + " CREDITOS: "+ creditos.size()
				println "RERENCIAS: " +referencias.size()+ " consultas: " +consultas.size() + "RESUMEN-BURO: "+ resumen.toString() + "ALERTAS:"+alertas.size()
				println "ALERTAS HI : " +alertasHr.size()+ " declarativa: " +declarativa.toString() + " SCORE: "+ score.toString() + "FINAL: "+segfinal.toString()*/
				
			}	
		}catch(Exception e){
			println "Exception obtenerDatosPersonales: "+ e
		}
		return reporteBuro
	}
	
	def obtenerFecha(String dia, String mes, String anio ){
		
		def diaN = "01"
		def mesN = "01"
		if(dia.length()<2){
			diaN = "0"+dia
		}else{
			  diaN = dia
		}
		if(mes.equalsIgnoreCase("Enero")){mesN = "01"  }else if(mes.equalsIgnoreCase("Febrero")){mesN = "02"}else if(mes.equalsIgnoreCase("Marzo")){mesN = "03"}
		else if(mes.equalsIgnoreCase("Abril")){mesN = "04"}else if(mes.equalsIgnoreCase("Mayo")){mesN = "05"}else if(mes.equalsIgnoreCase("Junio")){mesN = "06"}
		else if(mes.equalsIgnoreCase("Julio")){mesN = "07"}else if(mes.equalsIgnoreCase("Agosto")){mesN = "08"}else if(mes.equalsIgnoreCase("Septiembre")){mesN = "09"}
		else if(mes.equalsIgnoreCase("Octubre")){mesN = "10"}else if(mes.equalsIgnoreCase("Noviembre")){mesN = "11"}else if(mes.equalsIgnoreCase("Diciembre")){mesN = "12"}
		return diaN+mesN+anio
	}
	
	
}
