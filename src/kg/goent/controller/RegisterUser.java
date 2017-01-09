package kg.goent.controller;

import kg.goent.bean.User;
import kg.goent.dbc.DbConnection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.*;

/**
 * Created by Aziret on 09.01.2017.
 */
@ManagedBean
@ViewScoped
public class RegisterUser {
	private User user;
	private DbConnection dbConnection;

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

	public DbConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}

	public Boolean register(){
		if(user.getLogin()==null){
			System.out.println("login can't be empty.");
			return false;
		}
		dbConnection = new DbConnection();
		ResultSet set = dbConnection.getResult("login","login='"+user.getLogin()+"'	","user");

		if(!dbConnection.hasValue(set,"login",user.getLogin())){
			if(dbConnection.insertToTable("user",user.getInsertColumns(),user.getInsertValues())){
				System.out.println("Successfully registered.");
				return true;
			}
		}else{
			System.out.println("user with login " + user.getLogin()+" already exists.");
		}
		System.out.println("Failed registartion.");
		return false;
	}

}
