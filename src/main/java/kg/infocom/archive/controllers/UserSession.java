package kg.infocom.archive.controllers;

import kg.infocom.archive.models.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Created by timur on 14-Apr-17.
 */
@ManagedBean
@SessionScoped
public class UserSession {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
