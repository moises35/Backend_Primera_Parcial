
package py.com.prueba.laboratorio.utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Mail {
    public static void enviarCorreoOutlook(String destinatario, String asunto, String cuerpo) {
        String remitente = "dd";
        String password = "dd";
        String host = "smtp.office365.com";
        String port = "587";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(remitente));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);
            Transport.send(message);
            System.out.println("Correo electrónico enviado correctamente.");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
