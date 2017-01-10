package kg.goent.controller;

import kg.goent.bean.SessionTools;
import kg.goent.bean.User;
import kg.goent.dbc.DbConnection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.ResultSet;

/**
 * Created by root on 1/10/17.
 */
@ManagedBean
@SessionScoped
public class Login implements Serializable{
	private User user;

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

	public String login(){
		DbConnection db = new DbConnection();
		String clause = "login='"+user.getLogin()+"' AND password='"+user.getPassword()+"'";
		ResultSet rs = db.getResult("*",clause,"user");
		boolean login = false;
		if(user.getLogin().length() > 3
			|| user.getPassword().length()>3)
		try {
			while (rs.next()) {
				user.setFromSet(rs);
				login = true;
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		if(login) {
			HttpSession session = SessionTools.getSession();
			session.setAttribute("user", user);
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

}
