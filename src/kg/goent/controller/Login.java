package kg.goent.controller;

import kg.goent.bean.SessionTools;
import kg.goent.bean.Tools;
import kg.goent.bean.User;
import kg.goent.bean.UserSession;
import kg.goent.dbc.DbConnection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 * Created by root on 1/10/17.
 */
@ManagedBean
@ViewScoped
public class Login implements Serializable{
	private User user;

	@ManagedProperty(value="#{user}")
	private UserSession userSession;

	@PostConstruct
	public void init(){
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String login(){
		DbConnection db = new DbConnection();
		String clause = "login='"+user.getLogin()+"' AND password='"+user.getPassword()+"'";
		ResultSet rs = db.getResult("*",clause,"user");
		boolean login = false;
		if(user.getLogin().length() > 3
			&& user.getPassword().length()>3)
		try {
			while (rs.next()) {
				user.setFromSet(rs);
				login = true;
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		if(login) {
			userSession.setUser(user);
			SessionTools.setSession("userLogged","1");
			return "index";
		}else{
			FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_WARN,
					"Incorrect Username and Password",
					"Please enter correct username and Password"));

			return "login";
		}
	}

	public void logout(){
		userSession.setUser(null);
		SessionTools.setSession("userLogged","0");
		Tools.killInfoSession();

	}
}
