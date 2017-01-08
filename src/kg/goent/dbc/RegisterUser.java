package kg.goent.dbc;

import kg.goent.bean.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.sql.*;
@ManagedBean(name = "registerUser")
@ViewScoped
/**
 * Created by Aziret on 06.01.2017.
 */
public class RegisterUser {

	public void register(User u){
		Connection connection;
		final String dbURL = "jdbc:mysql://localhost:3306/goent";
		final String username = "root";
		final String pswd = "";
		try {
			DriverManager.getDrivers();
			connection = DriverManager.getConnection(dbURL, username, pswd);
			Statement statement = connection.createStatement();

			String query = "SELECT * FROM city WHERE Name LIKE 'New%'";
			String rquery = "INSERT INTO user(first_name,last_name,email,phone) " +
				"values("+
				u.getFname()+","+u.getLname()+","+
				u.getEmail()+","+u.getPhone()+")";
			//ResultSet result = statement.executeQuery(query);
			if(statement.executeUpdate(rquery) == 1){
				System.out.println("it's done");
			}else{
				System.out.println("There are some problems");
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
