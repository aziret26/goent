package kg.goent.bean;

import kg.goent.dbc.DbConnection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.sql.*;

/**
 * Created by Azriret on 01.01.2017.
 */
@ManagedBean(name = "userBean")
public class User implements Serializable{
	private int id = -1;
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String login;
	private String password;
	private int type = 3;
	private String activationKey;

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

	public String phoneF() {
		String temp = "";
		if(phone != null)
			if(phone.length() == 13) {
				temp = phone.substring(0,4);
				temp += " "+phone.substring(4,7);
				temp += " "+phone.substring(7,10);
				temp += " "+phone.substring(10,13);
			}else
			if(phone.length() == 10){
				temp = phone.substring(0,4);
				temp += " "+phone.substring(4,7);
				temp += " "+phone.substring(7,10);
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
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
			ans += "phone\t= "+getPhone()+"\n";
		if(getActivationKey() != null)
			ans += "activation_key\t= "+getActivationKey()+"\n\n";
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
			if(dbConnection.hasColumn(set,"activation_key")){
				activationKey = set.getString("activation_key");
			}
			if(dbConnection.hasColumn(set,"type")){
				type = set.getInt("type");
			}
		}catch (Exception ex){
			System.out.println("some errors ocurred");
			ex.printStackTrace();
		}
	}

	public String getInsertColumns(){
		String cols = "";
		if(fname!=null){
			cols += "first_name";
		}

		if(lname!=null){
			if(cols.length()!=0) cols += ",";
			cols += "last_name";
		}

		if(phone!=null){
			if(cols.length()!=0) cols += ",";
			cols += "phone";
		}

		if(email!=null){
			if(cols.length()!=0) cols += ",";
			cols += "email";
		}

		if(login!=null){
			if(cols.length()!=0) cols += ",";
			cols += "login";
		}

		if(password!=null) {
			if(cols.length()!=0) cols += ",";
			cols += "password";
		}
		if(getType() != -1){
			if(cols.length()!=0) cols += ",";
			cols +="type";
		}
		if(getActivationKey() != null){
			if(cols.length()!=0) cols += ",";
			cols +="activation_key";
		}

		return cols;
	}

	public String getInsertValues(){
		String values = "";
		if(fname!=null) {
			values += "'" + fname + "'";
		}
		if(lname != null) {
			if (values.length() != 0)
				values += ",";
			values += "'" + lname + "'";
		}

		if(phone != null) {
			if(values.length()!=0)
				values +=",";
			values += "'" + phone + "'";
		}

		if(email != null) {
			if (values.length() != 0)
				values += ",";
			values += "'" + email + "'";
		}

		if(login != null){
			if(values.length()!=0)
				values +=",";
			values += "'"+login+"'";
		}

		if(password!=null) {
			if (values.length() != 0)
				values += ",";
			values += "'" + password + "'";
		}
		if(getType()!=-1) {
			if (values.length() != 0)
				values += ",";
			values +="'"+getType()+"'";
		}
		if(getActivationKey()!= null){
			if (values.length() != 0)
				values += ",";
			values +="'"+getActivationKey()+"'";
		}
		return values;
	}
	public boolean isActive(){
		if(getActivationKey().length() == 0)
			return true;
		return false;
	}
}

