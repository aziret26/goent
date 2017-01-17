package kg.goent.bean;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
