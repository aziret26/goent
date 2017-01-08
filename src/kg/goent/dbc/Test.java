package kg.goent.dbc;

import kg.goent.bean.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.*;

/**
 * Created by Aziret on 06.01.2017.
 */
@ManagedBean(name="test")
@RequestScoped
public class Test {

	public static void main(String[] args) {
		DbConnection connection = new DbConnection();
		ResultSet set = connection.getResult("first_name,login","last_name='Dell'" , "user");
		User u;
		try {

			while (set.next()) {
				u = new User();
				u.setFromSet(set);

				System.out.println(u);

//				System.out.printf("fname:\t%s\nlname:\t%s\nlogin:\t%s\n\n",u.getFname(),u.getLname(),u.getLogin());
			}
		}catch (Exception ex){
			System.out.println("some errors occured");
			ex.printStackTrace();
		}
	}


	public static void mains(String args[]) {
		Connection connection;
		final String dbURL = "jdbc:mysql://localhost:3306/goent";
		final String username = "root";
		final String pswd = "";
		try {
			DriverManager.getDrivers();
			connection = DriverManager.getConnection(dbURL, username, pswd);
			Statement statement = connection.createStatement();


			User u = new User();

			u.setFname("Inspiron");
			u.setLname("Dell");
			u.setEmail("info@dell.com");
			u.setPhone("1234");

			String rquery = "INSERT INTO user(first_name,last_name,email,phone) " +
				"values(\'"+
				u.getFname()+"\',\'"+u.getLname()+"\',\'"+
				u.getEmail()+"\',\'"+u.getPhone()+"\')";

			String query = "SELECT * FROM user";

			statement.executeUpdate(rquery);
			/*
			while (result.next()) {
				u.setId(result.getInt("id"));
				u.setFname(result.getString("first_name"));
				u.setLname(result.getString("last_name"));
				u.setEmail(result.getString("email"));
				u.setPhone(result.getString("phone"));
				System.out.print(u);
			}*/
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		}


	public boolean reg(User u) {
		Connection connection;
		final String dbURL = "jdbc:mysql://localhost:3306/goent";
		final String username = "goent";
		final String pswd = "goent";
		int result;
		try {
			String myDriver = "org.gjt.mm.mysql.Driver";
			DriverManager.getDrivers();
			Class.forName(myDriver);
			connection = DriverManager.getConnection(dbURL, username, pswd);
			Statement statement = connection.createStatement();



			String rquery = "INSERT INTO user(first_name,last_name,email,phone,login) " +
				"values(\'"+
				u.getFname()+"\',\'"+u.getLname()+"\',\'"+
				u.getEmail()+"\',\'"+u.getPhone()+"\',\'"+u.getLogin()+"')";

			String query = "SELECT * FROM user";

			result = statement.executeUpdate(rquery);
			/*
			while (result.next()) {
				u.setId(result.getInt("id"));
				u.setFname(result.getString("first_name"));
				u.setLname(result.getString("last_name"));
				u.setEmail(result.getString("email"));
				u.setPhone(result.getString("phone"));
				System.out.print(u);
			}*/
			return result == 1? true: false;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}

/*
	u.setId(result.getInt("ID"));
	u.setFname(result.getString("first_name"));
	u.setLname(result.getString("last_name"));
	u.setEmail(result.getString("email"));
	u.setPhone(result.getString("phone"));
	System.out.print(u);
 */