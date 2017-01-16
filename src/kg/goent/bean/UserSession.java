package kg.goent.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created by Aziret on 16.01.2017.
 */
@ManagedBean(name = "user")
@SessionScoped
public class UserSession implements Serializable {
	private User user = null;
	private int logged = 0;

//	@PostConstruct
//	public void postConstruct(){
//		user = new User();
//	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getLogged() {
		if(user == null){
			return 0;
		}
		return 1;
	}

	public void setLogged(int logged) {
		this.logged = logged;
	}
}
