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
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Image
import com.itextpdf.text.pdf.PdfWriter
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;

@Transactional
class EphesoftService {
    
    File finalImage;

    def ocrClassifyExtract(def listaDeArchivos, def docType) {
        def datosArchivo = listaDeArchivos?.getAt(0);
        PostMethod mPost
        def responseBody = [:]
        File file0
        File file1
        if(datosArchivo.extension.equals(".pdf")){
            file0 = new File("/tmp/BCC_Doc0.pdf")
        } else {
            file0 = new File("/tmp/BCC_Doc0.jpg")
        }
        println ("#######Ruta del archivo0: " + file0.absolutePath)
        file0.withOutputStream{fos->
            fos << datosArchivo.archivo
        }
        println ("#######Ruta del archivo1: " + file0.absolutePath)
        String ruta = file0.absolutePath.toString();
        if(datosArchivo.extension.equals(".pdf")){
            file1 = file0
        } else {
            String namePDF = convertPDF(ruta);
            file1  = new File(namePDF)
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
            } else if (statusCode == 403) {
                println("Invalid username/password..");
                responseBody.error = "Invalid username/password.."
            } else {
                println(mPost.getResponseBodyAsString());
                //responseBody = mPost.getResponseBodyAsString();
                def respuesta = mPost.getResponseBodyAsString()
                respuesta = respuesta.replaceAll('Ephesoft','Kosmos')
                respuesta = respuesta.replaceAll('ephesoft','kosmos')
                responseBody.error = XmlUtil.serialize(respuesta)
            }
        } catch (FileNotFoundException e) {
            println("File not found for processing..");
            responseBody.error = "File not found for processing.."
        } catch (HttpException e) {
            e.printStackTrace();
            responseBody.error = e.getMessage()
        } catch (IOException e) {
            e.printStackTrace();
            responseBody.error = e.getMessage()
        } finally {
            if (mPost != null) {
                mPost.releaseConnection();
            }
            return responseBody;
        }
    }

    public String convertPDF(String path) {
        String nombrePDF;
        Document document = new Document();
        try {
            nombrePDF="/tmp/BCC_Doc0.pdf";
            PdfWriter.getInstance(document, new FileOutputStream(nombrePDF));
            document.open();
            println ("//////////SETEANDO RUTA A NUEVO ARCHIVO")
            String filename = path;
            Image image = Image.getInstance(filename);
            float scaler = ((document.getPageSize().getWidth() - document.leftMargin() - document.rightMargin()) / image.getWidth()) * 100;
            image.scalePercent(scaler);
            document.add(image);
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        } finally {
            println ("///////////CERRANDO DOCUMENTO NUEVO")
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
                }
                if(campo.Name.text()?.equals("CustomerName")){
                    mapa.nombrePersona = campo.Value.text()
                }
                if(campo.Name.text()?.equals("Date")){
                    mapa.fechaRecibo = campo.Value.text()
                }
            }
        } else {
            mapa.error = respuestaEphesoft.Error.Cause.text()
        }
        return mapa
    }
}