package kg.goent.test;

import kg.goent.bean.User;
import kg.goent.dbc.DbConnection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by root on 1/10/17.
 */
@ManagedBean
@ViewScoped
public class TestBean {
	private ArrayList<User> uList = new ArrayList<>();
	@PostConstruct
	public void init(){
		DbConnection db = new DbConnection();
		ResultSet rs = db.getResult("*","user");
		try {
			while (rs.next()) {
				User u = new User();
				u.setFromSet(rs);
				uList.add(u);
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
	}

	public ArrayList<User> getuList() {
		return uList;
	}

	public void setuList(ArrayList<User> uList) {
		this.uList = uList;
	}
}
