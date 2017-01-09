package kg.goent.bean;

import kg.goent.dbc.DbConnection;

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
	private String password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setLogged(int logged) {
		this.logged = logged;
	}

	public int getLogged() {

		return logged != 0 ? 1:0;
	}
	public String toString(){
		String ans = "";
		if(getId()!=-1)
		ans = "ID\t= "+getId()+"\n";
		if(getLogin() != null)
		ans += "login\t= "+getLogin()+"\n";
		if(getFname()!=null)
			ans += "first name\t= "+getFname()+"\n";
		if(getLname()!=null)
			ans += "last name\t= "+getLname()+"\n";
		if(getEmail()!=null)
			ans += "email\t= "+getEmail()+"\n";
		if(getPhone()!=null)
			ans += "phone\t= "+getPhone()+"\n\n";
		return ans;
	}
	public void setFromSet(ResultSet set){
		DbConnection dbConnection = new DbConnection();
		try {
			if (dbConnection.hasColumn(set, "id")) {
				id = set.getInt("id");
			}
			if (dbConnection.hasColumn(set, "first_name")) {
				fname = set.getString("first_name");
			}

			if(dbConnection.hasColumn(set,"last_name")){
				lname = set.getString("last_name");
			}
			if(dbConnection.hasColumn(set,"phone")){
				phone = set.getString("phone");
			}
			if(dbConnection.hasColumn(set,"email")){
				email = set.getString("email");
			}
			if(dbConnection.hasColumn(set,"login")){
				login = set.getString("login");
			}
		}catch (Exception ex){
			System.out.println("some errors ocurred");
			ex.printStackTrace();
		}
	}

	public String getInsertColumns(){
		String cols = "";
		if(fname!=null) cols += "first_name";

		if(cols.length()!=0) cols += ",";
		if(lname!=null) cols += "last_name";

		if(cols.length()!=0) cols += ",";
		if(phone!=null) cols += "phone";

		if(cols.length()!=0) cols += ",";
		if(email!=null) cols += "email";

		if(cols.length()!=0) cols += ",";
		if(login!=null) cols += "login";

		if(cols.length()!=0) cols += ",";
		if(password!=null) cols += "password";

		return cols;
	}
	public String getInsertValues(){
		String values = "";
		if(fname!=null)
			values += "'"+fname+"'";

		if(values.length()!=0)
			values +=",";
		if(lname != null)
			values += "'"+lname+"'";

		if(values.length()!=0)
			values +=",";
		if(phone != null)
			values += "'"+phone+"'";

		if(values.length()!=0)
			values +=",";
		if(email != null)
			values += "'"+email+"'";

		if(values.length()!=0)
			values +=",";
		if(login != null)
			values += "'"+login+"'";

		if(values.length()!=0) values += ",";
		if(password!=null) values += "'"+password+"'";

		return values;
	}
}
