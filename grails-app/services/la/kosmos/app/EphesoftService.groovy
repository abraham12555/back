package la.kosmos.app

import grails.transaction.Transactional
import groovy.util.XmlParser
import groovy.util.XmlSlurper
import groovy.xml.XmlUtil
import org.apache.commons.httpclient.Header
import org.apache.commons.httpclient.HttpClient
import org.apache.commons.httpclient.HttpException
import org.apache.commons.httpclient.methods.PostMethod
import org.apache.commons.httpclient.methods.multipart.Part
import org.apache.commons.httpclient.methods.multipart.FilePart
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity
import org.apache.commons.httpclient.methods.multipart.StringPart

import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import org.apache.commons.codec.binary.Base64

import com.itextpdf.text.Document
import mx.com.kosmos.connections.Rotate
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.Rectangle;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

@Transactional
class EphesoftService {
    
    File finalImage;

    def ocrClassifyExtract(def listaDeArchivos, def docType) {
        def datosArchivo = listaDeArchivos?.getAt(0);
        PostMethod mPost
        def responseBody = [:]
        File file0
        File file1
        if (datosArchivo.extension == ".pdf") {
            file0 = new File("/tmp/BCC_Doc0.pdf")
        } else {
            file0 = new File("/tmp/BCC_Doc0" + datosArchivo.extension)
        }
        println("#######Ruta del archivo0: " + file0.absolutePath)
        file0.withOutputStream { fos ->
            fos << datosArchivo.archivo
        }
        println("#######Ruta del archivo1: " + file0.absolutePath)
        String ruta = file0.absolutePath.toString();
        if (datosArchivo.extension == ".pdf") {
            file1 = file0
        } else {
            String namePDF = convertPDF(ruta);
            file1 = new File(namePDF)
        }
        Part[] parts = new Part[3];
        try {
            def configuracion = ConfiguracionKosmos.get(1)
            HttpClient client = new HttpClient();
            mPost = new PostMethod(configuracion.urlEphesoft);
            List<Header> headers = new ArrayList<Header>();
            headers.add(new Header("Authorization", "Basic " + (new String(Base64.encodeBase64((configuracion.usuarioEphesoft + ":" + configuracion.passwdEphesoft).getBytes())))));
            client.getHostConfiguration().getParams().setParameter("http.default-headers", headers);
            parts[0] = new FilePart(file1.getName(), file1);
            parts[1] = new StringPart("batchClassIdentifier", configuracion.batchClassEphesoft);
            parts[2] = new StringPart("docType", docType);
            MultipartRequestEntity entity = new MultipartRequestEntity(parts, mPost.getParams());
            mPost.setRequestEntity(entity);
            int statusCode = client.executeMethod(mPost);
            if (statusCode == 200) {
                println("Web service executed successfully..")
                //responseBody = mPost.getResponseBodyAsString();
                def respuesta = mPost.getResponseBodyAsString()
                respuesta = respuesta.replaceAll('Ephesoft','Kosmos')
                respuesta = respuesta.replaceAll('ephesoft','kosmos')
                responseBody = verificarRespuestaEphesoft(respuesta)
                //println(statusCode + " *** " + responseBody);
                responseBody.statusCode = statusCode
                responseBody.respuestaXml = XmlUtil.serialize(respuesta)
            } else if (statusCode == 403) {
                println("Invalid username/password..");
                responseBody.error = "Invalid username/password.."
            } else {
                println(mPost.getResponseBodyAsString());
                //responseBody = mPost.getResponseBodyAsString();
                def respuesta = mPost.getResponseBodyAsString()
                respuesta = respuesta.replaceAll('Ephesoft', 'Kosmos')
                respuesta = respuesta.replaceAll('ephesoft', 'kosmos')
                responseBody = verificarRespuestaEphesoft(respuesta)
                responseBody.statusCode = statusCode
                responseBody.respuestaXml = XmlUtil.serialize(respuesta)
            }
        } catch (FileNotFoundException e) {
            println("Archivo no encontrado..");
            responseBody.error = "File not found for processing.."
        } catch (HttpException e) {
            println("Error al comunicarse..");
            e.printStackTrace();
            responseBody.error = e.getMessage()
        } catch (IOException e) {
            println("Otro cosa..");
            e.printStackTrace();
            responseBody.error = e.getMessage()
        } catch (Exception e) {
            println("Excepcion general..");
            e.printStackTrace();
            responseBody.error = e.getMessage()
        } finally {
            println("Cerrando conexion..");
            if (mPost != null) {
                mPost.releaseConnection();
            }
            return responseBody;
        }
    }

    public String convertPDF(String path) {
        String nombrePDF
        Document document = new Document();
        try {
            String filename = path;
            Image image = Image.getInstance(filename);
            def height = image.getHeight()
            def width = image.getWidth()
            nombrePDF = "/tmp/BCC_Doc0.pdf";
            println("//////////INICIALIZANDO....")
            Rectangle size = new Rectangle(width,height);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(nombrePDF));
            Rotate event = new Rotate();
            println("//////////AGREGANDO EVENTO PARA CAMBIAR ORIENTACION")
            writer.setPageEvent(event);
            document.setPageSize(size);
            document.setMargins(0, 0, 0, 0);
            println("//////////ABRIENDO DOCUMENTO...")
            document.open();
            println("//////////SETEANDO RUTA A NUEVO ARCHIVO")
            //float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / image.getWidth()) * 100;
            //image.scalePercent(scaler);
            document.add(image);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            println("///////////CERRANDO DOCUMENTO NUEVO")
            document.close();
        }
        return nombrePDF;
    }
    
    def verificarRespuestaEphesoft(String respuesta){
        def mapa = [:]
        def respuestaEphesoft = new XmlParser().parseText(respuesta)
        def codigoRespuesta = respuestaEphesoft.Response_Code.HTTP_Code.text()
        if(codigoRespuesta.equals("200")){
            respuestaEphesoft.Result.Batch.Documents.Document.DocumentLevelFields.DocumentLevelField.each { campo ->
                if(campo.Name.text()?.equals("CustomerAddress")){
                    mapa.direccion = campo.Value.text()
                } else if(campo.Name.text()?.equals("CustomerName")){
                    mapa.nombrePersona = campo.Value.text()
                } else if (campo.Name.text()?.equals("Date")) {
                    mapa.fechaRecibo = campo.Value.text()
                    mapa.vigente = esDocumentoVigente(mapa.fechaRecibo, "fecha")
                } else if (campo.Name.text()?.equals("numeroDoc")) {
                    mapa.numeroDocumento = campo.Value.text()
                    def listaNumDocAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("numeroDoc")){
                            listaNumDocAlternos << it.Value.text()
                        }
                    }
                    mapa.numDocAlternos = listaNumDocAlternos
                } else if (campo.Name.text()?.equals("pais")) {
                    mapa.pais = campo.Value.text()
                } else if (campo.Name.text()?.equals("nacimiento")) {
                    mapa.nacimiento = campo.Value.text()
                    def listaFechNacAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("nacimiento")){
                            listaFechNacAlternos << it.Value.text()
                        }
                    }
                    mapa.fechaNacAlternos = listaFechNacAlternos
                } else if (campo.Name.text()?.equals("vigencia")) {
                    mapa.vigencia = campo.Value.text()
                    mapa.vigente = esDocumentoVigente(mapa.vigencia, "anio")
                    def listaVigenciaAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("vigencia")){
                            listaVigenciaAlternos << it.Value.text()
                        }
                    }
                    mapa.vigenciaAlternos = listaVigenciaAlternos
                } else if (campo.Name.text()?.equals("apellidoPaterno")) {
                    mapa.apellidoPaterno = campo.Value.text()
                    def listaApPaternoAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("apellidoPaterno")){
                            listaApPaternoAlternos << it.Value.text()
                        }
                    }
                    mapa.apPaternoAlternos = listaApPaternoAlternos
                } else if (campo.Name.text()?.equals("sexo")) {
                    mapa.sexo = campo.Value.text()
                    def listaSexoAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("sexo")){
                            listaSexoAlternos << it.Value.text()
                        }
                    }
                    mapa.sexoValorAlternos = listaSexoAlternos
                } else if (campo.Name.text()?.equals("seccion")) {
                    mapa.seccion = campo.Value.text()
                    def listaSeccionAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("seccion")){
                            listaSeccionAlternos << it.Value.text()
                        }
                    }
                    mapa.seccionAlternos = listaSeccionAlternos
                } else if (campo.Name.text()?.equals("colonia")) {
                    mapa.colonia = campo.Value.text()
                    def listaColoniaAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("colonia")){
                            listaColoniaAlternos << it.Value.text()
                        }
                    }
                    mapa.coloniaAlternos = listaColoniaAlternos
                } else if (campo.Name.text()?.equals("estado")) {
                    mapa.estado = campo.Value.text()
                    def listaEstadoAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("estado")){
                            listaEstadoAlternos << it.Value.text()
                        }
                    }
                    mapa.estadosAlternos = listaEstadoAlternos
                } else if (campo.Name.text()?.equals("municipio")) {
                    mapa.municipio = campo.Value.text()
                    def listaMunicipioAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("municipio")){
                            listaMunicipioAlternos << it.Value.text()
                        }
                    }
                    mapa.municipioAlternos = listaMunicipioAlternos
                } else if (campo.Name.text()?.equals("apellidoMaterno")) {
                    mapa.apellidoMaterno = campo.Value.text()
                    def listaApMaternoAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("apellidoMaterno")){
                            listaApMaternoAlternos << it.Value.text()
                        }
                    }
                    mapa.apMaternoAlternos = listaApMaternoAlternos
                } else if (campo.Name.text()?.equals("nombre")) {
                    mapa.nombre = campo.Value.text()
                    def listaValoresAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("nombre")){
                            listaValoresAlternos << it.Value.text()
                        }
                    }
                    mapa.nombresAlternos = listaValoresAlternos
                } else if (campo.Name.text()?.equals("curp")) {
                    mapa.curp = campo.Value.text()
                    def listaCurpAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("curp")){
                            listaCurpAlternos << it.Value.text()
                        }
                    }
                    mapa.curpAlternos = listaCurpAlternos
                } else if (campo.Name.text()?.equals("emision")) {
                    mapa.emision = campo.Value.text()
                    def listaEmisionAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("emision")){
                            listaEmisionAlternos << it.Value.text()
                        }
                    }
                    mapa.emisionAlternos = listaEmisionAlternos
                } else if (campo.Name.text()?.equals("calle")) {
                    mapa.calle = campo.Value.text()
                    def listaCalleAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("calle")){
                            listaCalleAlternos << it.Value.text()
                        }
                    }
                    mapa.calleAlternos = listaCalleAlternos
                } else if (campo.Name.text()?.equals("ClaveDeAvaluo")) {
                    mapa.claveDeAvaluo = campo.Value.text()
                } else if (campo.Name.text()?.equals("CodigoPostal")) {
                    mapa.codigoPostal = campo.Value.text()
                } else if (campo.Name.text()?.equals("FechaDelAvaluo")) {
                    mapa.fechaDelAvaluo = campo.Value.text()
                } else if (campo.Name.text()?.equals("Colonia")) {
                    mapa.colonia = campo.Value.text()
                } else if (campo.Name.text()?.equals("ClaveEntidadFederativaINEGI")) {
                    mapa.claveEntidadFederativaINEGI = campo.Value.text()
                } else if (campo.Name.text()?.equals("CalleNumero")) {
                    mapa.calleNumero = campo.Value.text()
                } else if (campo.Name.text()?.equals("ColoniaAvaluo")) {
                    mapa.coloniaAvaluo = campo.Value.text()
                } else if (campo.Name.text()?.equals("NumeroDeCuentaPredial")) {
                    mapa.numeroDeCuentaPredial = campo.Value.text()
                } else if (campo.Name.text()?.equals("ProximidadUrbana")) {
                    mapa.proximidadUrbana = campo.Value.text()
                } else if (campo.Name.text()?.equals("PropositoDelAval")) {
                    mapa.propositoDelAval = campo.Value.text()
                } else if (campo.Name.text()?.equals("ClaveDelValuadorProfesional")) {
                    mapa.claveDelValuadorProfesional = campo.Value.text()
                } else if (campo.Name.text()?.equals("ClaveDelControlador")) {
                    mapa.claveDelControlador = campo.Value.text()
                } else if (campo.Name.text()?.equals("ClaveDeLaEntidadQueOtorgaElCredito")) {
                    mapa.claveDeLaEntidadQueOtorgaElCredito = campo.Value.text()
                } else if (campo.Name.text()?.equals("ClaveDelegacion")) {
                    mapa.claveDelegacion = campo.Value.text()
                } else if (campo.Name.text()?.equals("TipoDeInmueble")) {
                    mapa.tipoDeInmueble = campo.Value.text()
                } else if (campo.Name.text()?.equals("NivelDeInfraestructura")) {
                    mapa.nivelDeInfraestructura = campo.Value.text()
                } else if (campo.Name.text()?.equals("ClaseDelInmueble")) {
                    mapa.claseDelInmueble = campo.Value.text()
                } else if (campo.Name.text()?.equals("VidaUtilRemanente")) {
                    mapa.vidaUtilRemanente = campo.Value.text()
                } else if (campo.Name.text()?.equals("AnioDeTerminacionDeLaObra")) {
                    mapa.anioDeTerminacionDeLaObra = campo.Value.text()
                } else if (campo.Name.text()?.equals("UnidadesRentablesGenerales")) {
                    mapa.unidadesRentablesGenerales = campo.Value.text()
                } else if (campo.Name.text()?.equals("UnidadesRentables")) {
                    mapa.unidadesRentables = campo.Value.text()
                } else if (campo.Name.text()?.equals("SuperficieDeTerreno")) {
                    mapa.superficieDeTerreno = campo.Value.text()
                } else if (campo.Name.text()?.equals("SuperficieConstruida")) {
                    mapa.superficieConstruida = campo.Value.text()
                } else if (campo.Name.text()?.equals("SuperficieAccesoria")) {
                    mapa.superficieAccesoria = campo.Value.text()
                } else if (campo.Name.text()?.equals("SuperficieVendible")) {
                    mapa.superficieVendible = campo.Value.text()
                } else if (campo.Name.text()?.equals("ValorComparativoDeMercado")) {
                    mapa.valorComparativoDeMercado = campo.Value.text()
                } else if (campo.Name.text()?.equals("ValorFisicoDelTerreno")) {
                    mapa.valorFisicoDelTerreno = campo.Value.text()
                } else if (campo.Name.text()?.equals("ValorFisicoDeLaConstruccion")) {
                    mapa.valorFisicoDeLaConstruccion = campo.Value.text()
                } else if (campo.Name.text()?.equals("ValorFisicoElementosComunes")) {
                    mapa.valorFisicoElementosComunes = campo.Value.text()
                } else if (campo.Name.text()?.equals("ImporteDelValorConcluido")) {
                    mapa.importeDelValorConcluido = campo.Value.text()
                } else if (campo.Name.text()?.equals("NumeroDeRecamaras")) {
                    mapa.numeroDeRecamaras = campo.Value.text()
                } else if (campo.Name.text()?.equals("NumeroDeBanios")) {
                    mapa.numeroDeBanios = campo.Value.text()
                } else if (campo.Name.text()?.equals("NumeroDeMediosBanios")) {
                    mapa.numeroDeMediosBanios = campo.Value.text()
                } else if (campo.Name.text()?.equals("NumeroDeNivelesDeLaUnidadValuada")) {
                    mapa.numeroDeNivelesDeLaUnidadValuada = campo.Value.text()
                } else if (campo.Name.text()?.equals("NumeroDeEspaciosDeEstacionamiento")) {
                    mapa.numeroDeEspaciosDeEstacionamiento = campo.Value.text()
                } else if (campo.Name.text()?.equals("SuministroTelefonico")) {
                    mapa.suministroTelefonico = campo.Value.text()
                } else if (campo.Name.text()?.equals("NivelDeEquipamientoUrbano")) {
                    mapa.nivelDeEquipamientoUrbano = campo.Value.text()
                } else if (campo.Name.text()?.equals("Elevador")) {
                    mapa.elevador = campo.Value.text()
                } else if (campo.Name.text()?.equals("Georeferencia")) {
                    mapa.georeferencia = campo.Value.text()
                } else if (campo.Name.text()?.equals("apellidos")){
                    mapa.apellidos = campo.Value.text()
                    def listaApellidosAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("apellidos")){
                            listaApellidosAlternos << it.Value.text()
                        }
                    }
                    mapa.apellidosAlternos = listaApellidosAlternos
                } else if (campo.Name.text()?.equals("noDocumento")){
                    mapa.noDocumento = campo.Value.text()
                    def listaDocumentosAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("noDocumento")){
                            listaDocumentosAlternos << it.Value.text()
                        }
                    }
                    mapa.noDocumentosAlternos = listaDocumentosAlternos
                } else if (campo.Name.text()?.equals("fechaNacimiento")){
                    mapa.fechaNacimiento = campo.Value.text()
                    def listaNacimientoAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("fechaNacimiento")){
                            listaNacimientoAlternos << it.Value.text()
                        }
                    }
                    mapa.fechaNacimientoAlterno = listaNacimientoAlternos
                } else if (campo.Name.text()?.equals("fechaExpedicion")){
                    mapa.fechaExpedicion = campo.Value.text()
                    def listaExpedicionAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("fechaExpedicion")){
                            listaExpedicionAlternos << it.Value.text()
                        }
                    }
                    mapa.fechaExpedicionAlternos = listaExpedicionAlternos
                } else if (campo.Name.text()?.equals("nacionalidad")){
                    mapa.nacionalidad = campo.Value.text()
                    def listaNacionalidadAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("nacionalidad")){
                            listaNacionalidadAlternos << it.Value.text()
                        }
                    }
                    mapa.nacionalidadAlternos = listaNacionalidadAlternos
                } else if (campo.Name.text()?.equals("fechaCaducidad")){
                    mapa.fechaCaducidad = campo.Value.text()
                    def listaCaducidadAlternos = []
                    campo.AlternateValues?.AlternateValue?.each {
                        if(it.Name.text()?.equals("fechaCaducidad")){
                            listaCaducidadAlternos << it.Value.text()
                        }
                    }
                    mapa.fechaCaducidadAlternos = listaCaducidadAlternos
                } 
            }   
            
        }else {
            mapa.error = respuestaEphesoft.Error.Cause.text()
        }
        return mapa
     
    }

    def generarBase64() {
        def base64
        byte[] array = Files.readAllBytes(new File("/tmp/BCC_Doc0.pdf").toPath());
        base64 = Base64.encodeBase64String(array)
        return base64
    }

    def esDocumentoVigente(aValidar, tipoDeDato) {
        def resultado
        def separador
        def meses = ["ENE", "FEB", "MAR", "ABR", "MAY", "JUN", "JUL", "AGO", "SEP", "OCT", "NOV", "DIC"]
        try {
            if (tipoDeDato == "fecha") {
                if (aValidar.contains("-")) {
                    separador = '-'
                } else if (aValidar.contains("/")) {
                    separador = '/'
                } else {
                    separador = ' '
                }
                def dia
                def mes
                def anio
                def hoy = new Date()
                def fechaLimite
                def fechaRecibo
                def tokens = aValidar.tokenize(separador)
                dia = tokens[0] as int
                mes = meses.findIndexOf { it == tokens[1].toUpperCase() }
                anio = tokens[2] as int
                fechaLimite = hoy - 90
                fechaRecibo = new Date().copyWith(year: anio, month: mes, dayOfMonth: dia)
                if (fechaRecibo >= fechaLimite) {
                    resultado = true
                } else {
                    resultado = false
                }
            } else if (tipoDeDato == "anio") {
                def anio = aValidar as int
                int anioActual = Calendar.getInstance().get(Calendar.YEAR);
                if (anio >= anioActual) {
                    resultado = true
                } else {
                    resultado = false
                }
            }
        } catch (Exception e) {
            e.printStackTrace()
            resultado = "No fue posible determinar la vigencia del documento"
        } finally {
            return resultado
        }
    }
}