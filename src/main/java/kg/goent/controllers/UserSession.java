package kg.goent.controllers;

import kg.goent.models.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by timur on 14-Apr-17.
 */
@ManagedBean
@SessionScoped
public class UserSession {
    private User user = new User();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
