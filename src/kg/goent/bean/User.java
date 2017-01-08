package kg.goent.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.*;

/**
 * Created by Azriret on 01.01.2017.
 */
@ManagedBean(name = "user")
@ViewScoped
public class User implements Serializable{
	private int id = -1;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String login;
	private int logged;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFname() {
		return fname;
	}
	
	public void setFname(String name) {
		this.fname = name;
	}
	
	public String getEmail(){
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLname() {
		return lname;
	}
	
	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getPhone() {
		return phone;
	}

	public String getPhoneFromatted() {
		String temp = "";
		if(phone != null)
		if(phone.length() == 13) {
			temp = phone.substring(0,4);
			temp += "-"+phone.substring(4,7);
			temp += "-"+phone.substring(7,10);
			temp += "-"+phone.substring(10,13);
		}else
		if(phone.length() == 10){
			temp = phone.substring(0,4);
			temp += "-"+phone.substring(4,7);
			temp += "-"+phone.substring(7,10);
		}else{
			temp = phone;
		}
		return temp;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String toString(){
		String ans;
		ans = "ID\t= "+getId()+"\n";
		ans += "login\t="+getLogin()+"\n";
		ans += "first name\t= "+getFname()+"\n";
		ans += "last name\t= "+getLname()+"\n";
		ans += "email\t= "+getEmail()+"\n";
		ans += "phone\t= "+getPhone()+"\n\n";
		return ans;
	}

	public void setLogged(int logged) {
		this.logged = logged;
	}

	public int getLogged() {

		return logged != 0 ? 1:0;
	}

	public void setFromSet(ResultSet set){
		try {
			if (hasColumn(set, "id")) {
				id = set.getInt("id");
			}
			if (hasColumn(set, "first_name")) {
				fname = set.getString("first_name");
			}

			if(hasColumn(set,"last_name")){
				lname = set.getString("last_name");
			}
			if(hasColumn(set,"phone")){
				phone = set.getString("phone");
			}
			if(hasColumn(set,"email")){
				email = set.getString("email");
			}
			if(hasColumn(set,"login")){
				login = set.getString("login");
			}
		}catch (Exception ex){
			System.out.println("some errors ocurred");
			ex.printStackTrace();
		}
	}

	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columns = rsmd.getColumnCount();
		for (int x = 1; x <= columns; x++) {
			if (columnName.equals(rsmd.getColumnName(x))) {
				return true;
			}
		}
		return false;
	}
}
