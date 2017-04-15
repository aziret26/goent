package kg.infocom.archive.controllers;

import kg.infocom.archive.dao.UserDao;
import kg.infocom.archive.facade.UserFacade;
import kg.infocom.archive.models.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

/**
 * Created by timur on 13-Apr-17.
 */
@ManagedBean
@ViewScoped
public class UserController {
    private User user;

    @ManagedProperty(value = "userSession")
    private UserSession userSession;

    public UserSession getUserSession() {
        return userSession;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String signin(){
        String email=user.getEmail();
        String password=user.getPassword();

        UserFacade uf = new UserFacade();
        User tempUser = uf.findByEmailPass(email,password);

        if(tempUser != null){
            userSession.setUser(tempUser);
        }
        return "index";
    }
    public String signup(){
        UserFacade uf=new UserFacade();
        uf.createUser(user);
        return "signin";
    }



}
