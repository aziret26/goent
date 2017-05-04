package kg.goent.controllers;

import kg.goent.facade.UserFacade;
import kg.goent.facade.UserRoleFacade;
import kg.goent.facade.UserStatusFacade;
import kg.goent.models.User;
import kg.goent.models.UserStatus;
import kg.goent.tools.Mail;
import kg.goent.tools.Tools;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by timur on 13-Apr-17.
 */
@ManagedBean
@ViewScoped
public class UserController {
    private User user;
    private String activationKey;

    @PostConstruct
    void init(){
        user = new User();
    }

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    public UserSession getUserSession() {
        return userSession;
    }

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public List<User> getAllUsers(){
        return new UserFacade().findAll();
    }

    public String signin(){
        String email=user.getEmail();
        String password=user.getPassword();

        UserFacade uf = new UserFacade();
        User tempUser = uf.findByEmailPass(email,password);

        if(tempUser == null){
            Tools.faceMessageWarn("Wrong email or password.","Please, check if data are correct.");
            return "signin";
        }
        userSession.setUser(tempUser);
        userSession.signin();
        return "index?faces-redirect=true";
    }

    public String signout(){
        userSession.signout();
        return "index";
    }

    public String signup(){
        user.setRegDate(new Date());
        if(user.getUserRole() == null){
            user.setUserRole(new UserRoleFacade().findById(3));
        }
        if(user.getUserStatus() == null){
            user.setUserStatus(new UserStatusFacade().findById(4));
        }
        user.setActivationKey(Tools.generateRandomKey());
        UserFacade uf=new UserFacade();
        uf.createUser(user);
        Mail m = new Mail();
        m.sendActivationMail(user.getEmail(),user.getActivationKey());
        //System.out.println("successfully registered");

        return "signin?faces-redirect=true";
    }

    public String activateByKey(){
        /*
        * method for account activation by providing activation key from web page
        * */
        if(userSession.getUser().getActivationKey().equals(activationKey)){
            userSession.getUser().setActivationKey("");
            userSession.getUser().setUserStatus(new UserStatusFacade().findByStatus("activated"));
            new UserFacade().updateUser(userSession.getUser());
        }
        return "index?faces-redirect=true";
    }

    public String activateAutomaticaly(){
        /*
        * Method for account activation by following link from email
                * */
        return "infopage?faces-redirect=true";
    }

    public List<User> searchByEmailTop5(String email){
        if(email.length() > 3){
            return new ArrayList<User>();
        }
        List<User> userList;
        userList = new UserFacade().searchByEmailBy5(email);
        //System.out.println("Searching for user: "+email);
        return userList;
    }
}
