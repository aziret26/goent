package kg.goent.bean;

import kg.goent.dao.UserDao;
import kg.goent.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by azire on 3/16/2017.
 */
@ManagedBean
@SessionScoped
public class UserSession {
    User user = new User();
    boolean logged = false;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isLogged() {
        return logged;
    }

    public void setLogged(boolean logged) {
        this.logged = logged;
    }

}
