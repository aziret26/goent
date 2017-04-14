package kg.infocom.archive.controllers;

import kg.infocom.archive.models.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * Created by timur on 13-Apr-17.
 */
@ManagedBean
@ViewScoped
public class UserController {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


}
