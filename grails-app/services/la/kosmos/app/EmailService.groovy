package la.kosmos.app

import grails.transaction.Transactional
import java.lang.RuntimeException
import java.util.Properties
import javax.mail.internet.MimeMessage
import javax.mail.MessagingException
import la.kosmos.app.vo.Constants
import org.springframework.mail.javamail.JavaMailSenderImpl
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.MailException
import org.apache.velocity.app.Velocity
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader
import org.apache.velocity.runtime.RuntimeConstants
import org.apache.velocity.Template
import org.apache.velocity.VelocityContext


@Transactional
class EmailService {
    public static final String EMAIL_CALIXTA = "jsasson@kosmos.la"
    public static final String CTE_CALIXTA = "45919"
    public static final String EMAIL_FROM_CALIXTA = "confirmaciones@micreditolibertad.com"
    public static final String PASS_CALIXTA = "0515b817283f121baa96df319443e740e6cc03990dbdbfae998c5286892f7648"

    private JavaMailSenderImpl setEmailConfiguration(ConfiguracionEntidadFinanciera configuracion){
        Properties props = new Properties()
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl()

        props.put("mail.smtp.host", configuracion.emailHost)
        props.put("mail.from", configuracion.emailFrom)
        props.put("mail.smtp.socketFactory.port", configuracion.emailPort)
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory")
        props.put("mail.smtp.socketFactory.fallback", "false")
        props.put("mail.smtp.auth", "true")
        props.put("mail.smtp.port", configuracion.emailPort)

        mailSender.setJavaMailProperties(props)
        mailSender.setUsername(configuracion.emailUsername)
        mailSender.setPassword(configuracion.emailPassword)

        return mailSender
    }

    public boolean sendPlainText(ConfiguracionEntidadFinanciera configuracion, String subject, String email, String message) throws Exception {
        JavaMailSenderImpl mailSender = this.setEmailConfiguration(configuracion)

        try {
            MimeMessage msg = mailSender.createMimeMessage()
            MimeMessageHelper helper = new MimeMessageHelper(msg)
            helper.setSubject(subject)
            helper.setTo(email)
            helper.setText("<p>" + message + "</p>", Boolean.TRUE)
            mailSender.send(msg)
            return Boolean.TRUE
        } catch (MessagingException | MailException e) {
            throw e
        }
    }

    public boolean sendTemplate(ConfiguracionEntidadFinanciera configuracion, String subject, String email, Map map) throws Exception {
        JavaMailSenderImpl mailSender = this.setEmailConfiguration(configuracion)

        try {
            Properties p = new Properties()
            p.setProperty(RuntimeConstants.RESOURCE_LOADER, "class")
            p.setProperty("class.resource.loader.class",  ClasspathResourceLoader.class.getName())

            Velocity.init(p)
            VelocityContext context = new VelocityContext(map)

            Template template = Velocity.getTemplate(Constants.CONFIRMATION_TEMPLATE)
            StringWriter writer = new StringWriter()

            template.merge( context, writer )
            String text = writer.toString()
            if(configuracion.usarEmailsCalixtaTemplate){
                this.sendHtmlTextCalixta(configuracion,subject, email, text)
            }else{
                MimeMessage msg = mailSender.createMimeMessage()
                MimeMessageHelper helper = new MimeMessageHelper(msg)
                helper.setSubject(subject)
                helper.setTo(email)
                helper.setText(text, Boolean.TRUE)
                
                mailSender.send(msg)
                return Boolean.TRUE
            }

        } catch (MessagingException | MailException e) {
            throw e
        }
    }
    public boolean sendHtmlTextCalixta(ConfiguracionEntidadFinanciera configuracion, String subject, String email, String message) throws Exception {

        def respuesta = [:]
        try{
        StringBuilder soap = new StringBuilder()
        soap.append("<soapenv:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:cal=\"http://www.calixtaondemand.com\">")
        soap.append("<soapenv:Header/>")
        soap.append("<soapenv:Body>")
        soap.append("<cal:EnviaEmail soapenv:encodingStyle=\"http://schemas.xmlsoap.org/soap/encoding/\">")
        soap.append("<cte xsi:type=\"xsd:int\">"+CTE_CALIXTA+"</cte>")
        soap.append("<email xsi:type=\"xsd:string\">"+EMAIL_CALIXTA+"</email>")
        soap.append("<password xsi:type=\"xsd:string\">"+PASS_CALIXTA+"</password>")
        soap.append("<nombreCamp xsi:type=\"xsd:string\">"+subject+"</nombreCamp>")
        soap.append("<to xsi:type=\"xsd:string\">"+email+"</to>")
        soap.append("<from xsi:type=\"xsd:string\">"+EMAIL_FROM_CALIXTA+"</from>")
        soap.append("<fromName xsi:type=\"xsd:string\">Caja Libertad</fromName>")
        soap.append("<replyTo xsi:type=\"xsd:string\">"+EMAIL_FROM_CALIXTA+"</replyTo>")
        soap.append("<subject xsi:type=\"xsd:string\">"+subject+"</subject>")
        soap.append("<incrustarImagen xsi:type=\"xsd:int\"></incrustarImagen>")
        soap.append("<textEmail xsi:type=\"xsd:string\"></textEmail>")
        soap.append("<htmlEmail xsi:type=\"xsd:string\"><![CDATA["+message+"]]></htmlEmail>")
        soap.append("<seleccionaAdjuntos xsi:type=\"xsd:int\"></seleccionaAdjuntos>")
        soap.append("<fileBase64 xsi:type=\"xsd:base64Binary\"></fileBase64>")
        soap.append("<fileNameBase64 xsi:type=\"xsd:string\"></fileNameBase64>")
        soap.append("<nombreArchivoPersonalizado xsi:type=\"xsd:string\"></nombreArchivoPersonalizado>")
        soap.append("<envioSinArchivo xsi:type=\"xsd:int\"></envioSinArchivo>")
        soap.append("<fechaInicio xsi:type=\"xsd:string\"></fechaInicio>")
        soap.append("<horaInicio xsi:type=\"xsd:int\"></horaInicio>")
        soap.append("<minutoInicio xsi:type=\"xsd:int\"></minutoInicio>")
        soap.append("<listasNegras xsi:type=\"xsd:string\"></listasNegras>")
        soap.append("<referencia xsi:type=\"xsd:string\"></referencia>")
        soap.append("<campoAuxiliar xsi:type=\"xsd:string\"></campoAuxiliar>")
        soap.append("</cal:EnviaEmail>")
        soap.append("</soapenv:Body>")
        soap.append("</soapenv:Envelope>")
        String response =null
        response = postCallSendEmailCalixta(soap.toString())
        return Boolean.TRUE
        } catch (MessagingException | MailException e) {
            throw e
        }
    }
    
    def postCallSendEmailCalixta(String xml) throws Exception{
        URL url = new URL("http://www.calixtaondemand.com/ws/webServiceV8.php");
        URLConnection  conn =  url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        conn.setRequestProperty("SOAPAction", "http://www.calixtaondemand.com#EnviaEmail");
        
        // Send the request XML
        OutputStream outputStream = conn.getOutputStream();
        outputStream.write(xml.getBytes());
        outputStream.close();
        
        // Read the response XML
        InputStream inputStream = conn.getInputStream();
        Scanner sc = new Scanner(inputStream, "UTF-8");
        sc.useDelimiter("\\A");
        if (sc.hasNext()) {
            println sc.next()
        }
        sc.close();
        inputStream.close();
    }
}
