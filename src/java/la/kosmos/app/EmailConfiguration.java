/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package la.kosmos.app;

import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

/**
 *
 * @author elizabeth
 */
public class EmailConfiguration {

    Properties props;
    JavaMailSenderImpl mailSender;

    public EmailConfiguration() {

    }

    public EmailConfiguration(String host, String from, String port, String username, String password) {

        props = new Properties();
        mailSender = new JavaMailSenderImpl();

        props.put("mail.smtp.host", host);
        props.put("mail.from", from);
        props.put("mail.smtp.socketFactory.port", port);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        mailSender.setJavaMailProperties(props);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
    }

    public boolean sendEmail(String subject, String email, String message) throws Exception {
        try {
            MimeMessage msg = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(msg);
            helper.setSubject(subject);
            helper.setTo(email);
            helper.setText("<p>" + message + "</p>", true);
            mailSender.send(msg);
            return Boolean.TRUE;
        } catch (MessagingException | MailException e) {
            throw e;
        }
    }
}
