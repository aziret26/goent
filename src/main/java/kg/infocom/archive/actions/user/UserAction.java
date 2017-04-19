package kg.infocom.archive.actions.user;


import kg.infocom.archive.domain.User;
import kg.infocom.archive.facade.UserFacade;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class UserAction implements Serializable {

    private User user = new User();

    private UserFacade userFacade = new UserFacade();

    public String createUser() {
        userFacade.createUser(user);
        return "result.xhtml?faces-redirect=true;";
    }

    public User getUserById() {
        return userFacade.findById(this.user.getId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
