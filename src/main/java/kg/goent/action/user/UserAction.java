package kg.goent.action.user;


import kg.goent.bean.Tools;
import kg.goent.bean.UserSession;
import kg.goent.dao.UserDao;
import kg.goent.facade.UserFacade;
import kg.goent.model.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class UserAction implements Serializable {

    private User user = new User();

    private UserFacade userFacade = new UserFacade();

    private String activationKey;

    @ManagedProperty(value = "#{userSession}")
    private UserSession userSession;

    public void setUserSession(UserSession userSession) {
        this.userSession = userSession;
    }

    public User getUserById() {
        return userFacade.findById(this.user.getUserId());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getActivationKey() {
        return activationKey;
    }

    public String createUser() {
        user.setActivationKey(Tools.generateRandomKey());
        userFacade.createUser(user);
        return "index";
    }


    public String login(){
        UserDao userDao = new UserDao();

        userDao.beginTransaction();
        EntityManager em =  userDao.getEntityManager();
        User tempUser;
        try {
            tempUser = em.createNamedQuery("User.findByLogin", User.class).
                    setParameter("login", user.getLogin()).getSingleResult();
        }catch (NoResultException ex){
            Tools.faceMessageWarn("Incorrect login password","Please check if capslock is on or off.");
            return "";
        }

        userDao.commitAndCloseTransaction();
        if(tempUser != null && tempUser.getPassword().equals(user.getPassword())){
            userSession.setUser(tempUser);
            userSession.setLogged(true);
            return "index.xhtml";
        }

        return "";
    }

    public void logout(){
        userSession.setUser(new User());
        userSession.setLogged(false);
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<User>();
        users = userFacade.getAll();
        return users;
    }

    public String activateByKey(){
        if(activationKey == null || activationKey.equals("")){
            Tools.faceMessageWarn("Please provide activation key.","");
            return "";
        }
        if(activationKey.equals(userSession.getUser().getActivationKey())){
            userSession.getUser().setActivationKey("");
            userFacade.updateUser(userSession.getUser());
            return "index";
        }else{
            Tools.faceMessageWarn("Wrong activation key","");
            return "";
        }
    }
}
