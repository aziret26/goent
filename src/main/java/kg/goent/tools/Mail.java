package kg.goent.tools;

import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import org.hibernate.Session;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by timur on 02-May-17.
 */

public class Mail {
    @Resource(name="java:jboss/mail/gmail")
    private Session session;

    public Mail(){
    }
    public void send(String to,String subject, String body){
        try{
            Message message=new MimeMessage.RecipientType(session);
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);
            message.setText(body);

            javax.mail.Transport.send(message);

        }catch(MessagingException e){
            Logger.getLogger(Mail.class.getName()).log(Level.WARNING,"Cannot send mail",e);
        }
    }
}
