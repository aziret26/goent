package kg.goent.tools;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class Mail {

    @Resource(name = "java:jboss/mail/gmail")
    private Session session;

    public void send(String addresses, String topic, String textMessage) {

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(addresses));
            message.setSubject(topic);
            message.setText(textMessage);

            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }
    public void sendActivationMail(String receiver,String activationCode){

        String mailSubject = "Accout activation";

        String mailText = "Please activate your account by entering following activation key " + activationCode;
        //String link = "http://localhost:8080/account/activate.xhtml?"+url;
        mailText += ".\n\nOr follow given link bellow \nto activate your account.";

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
            message.setSubject(mailSubject);
            message.setText(mailText);

            Transport.send(message);

        } catch (MessagingException e) {
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING, "Cannot send mail", e);
        }
    }

}