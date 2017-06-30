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

            MimeMessage msg = mailSender.createMimeMessage()
            MimeMessageHelper helper = new MimeMessageHelper(msg)
            helper.setSubject(subject)
            helper.setTo(email)
            helper.setText(text, Boolean.TRUE)

            mailSender.send(msg)

            return Boolean.TRUE
        } catch (MessagingException | MailException e) {
            throw e
        }
    }
}