package kg.goent.controllers;

import kg.goent.tools.Mail;
import kg.goent.tools.Tools;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by azire on 5/11/2017.
 */
@ManagedBean
@ViewScoped
public class MailController {
    private Mail mail = new Mail();

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }

    public void sendMail(){
        mail.sendTestMail(userSession.getUser().getEmail());
        Tools.faceMessageWarn("Message has been sent.","See your email.");
    }
}
