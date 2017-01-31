package kg.goent.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import java.util.Properties;

/**
 * Created by Aziret on 13.01.2017.
 */

public class Mail {
	private String mailTo;
	private String mailSubject;
	private String mailText;

	public Mail(){
		mailTo = "";
		mailSubject = "";
		mailText = "";
	}


	public void register(User u,String url){
		mailTo = u.getEmail();
		mailSubject = "Accout activation";

		mailText = "Please activate your account by entering following activation key " + u.getActivationKey();
		String link = "http://localhost:8080/account/activate.xhtml?"+url;
		mailText += ".\n\nOr follow given link bellow "+link+"\nto activate your account.";

	}

	public String getMailTo() {
		if(mailTo.length() == 0){
			HttpSession ses = SessionTools.getSession();
			java.lang.Object o = ses.getAttribute("user");
			User u = (kg.goent.bean.User) o;
			return u.getEmail();
		}
		return mailTo;
	}

	public void setMailTo(String mailTo) {
		this.mailTo = mailTo;
	}

	public String getMailSubject() {
		return mailSubject;
	}

	public void setMailSubject(String mailSubject) {
		this.mailSubject = mailSubject;
	}

	public String getMailText() {
		return mailText;
	}

	public void setMailText(String mailText) {
		this.mailText = mailText;
	}

	public int sendMail(){
		final String username = "goentrservice@gmail.com";
		final String password = "serviceinfo";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("goentrservice@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(mailTo));
			message.setSubject(mailSubject);
			message.setText(mailText);

			Transport.send(message);
			return 1;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}

