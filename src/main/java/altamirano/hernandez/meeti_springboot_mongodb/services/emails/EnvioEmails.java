package altamirano.hernandez.meeti_springboot_mongodb.services.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


@Service
public class EnvioEmails {
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${URI.Backend}")
    private String URL_BACKEND;

    public void emailConfirmacionCuenta(String to, String subject, String nombre, String tokenURL){
        Context context = new Context();
        context.setVariable("nombre", nombre);
        String url = URL_BACKEND + "/confirmar-cuenta/" + tokenURL;
        context.setVariable("url", url);

        String htmlContent = templateEngine.process("emails/ConfirmacionCuenta", context);
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            javaMailSender.send(message);
        } catch (RuntimeException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void emailCuentaConfirmada(String to, String subject, String nombre){
        Context context = new Context();
        context.setVariable("nombre", nombre);

        String cuerpoEmail = templateEngine.process("emails/CuentaConfirmada", context);
        MimeMessage message = javaMailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(cuerpoEmail, true);
            javaMailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
