package kg.goent.controller;

import kg.goent.bean.*;
import kg.goent.dbc.DbConnection;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.Base64;
import java.util.Random;

/**
 * Created by Aziret on 09.01.2017.
 */
@ManagedBean
@ViewScoped
public class RegisterUser {
	private User user;
	private DbConnection dbConnection;
	private String activationKey;

	@ManagedProperty(value = "#{user}")
	UserSession userSession;

	@PostConstruct
	public void initialize(){
		user = new User();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setUserSession(UserSession u){
		this.userSession = u;
	}

	public DbConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public String register(){
		if(user.getLogin()==null){
			System.out.println("login can't be empty.");
			return "index";
		}
		dbConnection = new DbConnection();
		ResultSet set = dbConnection.getResult("login","login='"+user.getLogin()+"'	","user");

		if(!dbConnection.hasValue(set,"login",user.getLogin())){
			String activationURL = encode("activation_key/"+user.getLogin()+user.getEmail());
			user.setActivationKey(generateRandomKey());
			if(dbConnection.insertToTable("user",user.getInsertColumns(),user.getInsertValues())){
				Mail m = new Mail(user,activationURL);
				m.sendMail();
				return "index";
			}
		}else{FacesContext.getCurrentInstance().addMessage(
			null,
			new FacesMessage(FacesMessage.SEVERITY_WARN,
				"User with login '"+user.getLogin()+"' already exists.",
				"Please choose another login."));
		}
		return "registration";
	}

	public String encode(String s){
		try{
			return Base64.getUrlEncoder().encodeToString(s.getBytes("utf-8"));
		}catch (UnsupportedEncodingException ex){
			System.out.println(ex.getMessage());
		}
		return null;
	}

	public String decode(String s){
		return new String(Base64.getUrlDecoder().decode(s));
	}

	public String getActivationKey() {
		return activationKey;
	}

	public void setActivationKey(String activationKey) {
		this.activationKey = activationKey;
	}

	private String generateRandomKey(){
		int size = 6;
		String key="";
		String symbols="ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
		Random rand = new Random();
		for(int i = 0;i < size;i++){
			key += symbols.charAt(rand.nextInt(36));
		}
		return key;
	}

	public String activate(){

		return "";
	}
	public String activateByKey(){
		dbConnection = new DbConnection();
		ResultSet set = dbConnection.getResult("*","id='"+userSession.getUser().getId()+"'","user");
		User u = new User();
		boolean updated = false;
		try {
			while (set.next()) {
				u.setFromSet(set);
				if(u.getActivationKey().equals(activationKey)){
					updated = dbConnection.updateTable(
						"user","activation_key=\"\"","id="+userSession.getUser().getId());

					SessionTools.setSession("infoMessageSession","Activated Successfully");
					userSession.getUser().setActivationKey("");
					break;
				}
			}
			if(updated){
				return "index";
			}else{
				FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
						"Wrong activation key",
						"Please check try again."));
				return "";
			}
		}catch (Exception ex){
			System.out.println("RegisterUser.activationByKey\n"+ex.getMessage());
		}
		return "";
	}
}
