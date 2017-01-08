package kg.goent.dbc;

import java.sql.*;
import java.util.Objects;

/**
 * Created by root on 1/8/17.
 */
public class DbConnection {
	Connection connection;
	final String dbURL = "jdbc:mysql://localhost:3306/goent";
	final String username = "goent";
	final String pswd = "goent";

	//ResultSet result = statement.executeQuery(query);
	public ResultSet getResult(String rows,String clause,String table){
		String query = "SELECT " + rows+ " FROM " + table + " WHERE "+clause;
		return __getResult(query);
	}

	public ResultSet getResult(String rows,String table){
		String query = "SELECT " + rows+ " FROM " + table;
		return __getResult(query);
	}

	private ResultSet __getResult(String query){
		Statement statement;
		try {
			DriverManager.getDrivers();
			connection = DriverManager.getConnection(dbURL,username,pswd);
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return null;
	}

	private int insertToTable(String table,String cols,String vals){
		String query="INSERT INTO " + table + "("+cols+" values("+vals+")";


		return 0;
	}

	private int __insertToTable(String query){
		return 0;
	}
}
