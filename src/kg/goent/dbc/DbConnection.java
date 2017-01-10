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
	public ResultSet getResult(String selectingColumns,String clause,String table){
		String query = "SELECT " + selectingColumns + " FROM " + table + " WHERE "+clause;

		return __getResult(query);
	}

	public ResultSet getResult(String rows,String table){
		String query = "SELECT " + rows+ " FROM " + table;
		return __getResult(query);
	}

	private ResultSet __getResult(String query){
		try {
			DriverManager.getDrivers();
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL,username,pswd);
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery(query);
			return result;
		}catch (Exception ex){
			//ex.getMessage();
			ex.printStackTrace();
		}
		return null;
	}

	public boolean insertToTable(String table,String cols,String vals){
		String query="INSERT INTO " + table + "("+cols+") values("+vals+")";

		return __insertToTable(query) == 1?true:false;
	}

	private int __insertToTable(String query){
		try {
			DriverManager.getDrivers();
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dbURL,username,pswd);
			Statement statement = connection.createStatement();
			return statement.executeUpdate(query);

		}catch (Exception ex){
			ex.getMessage();
			ex.printStackTrace();
		}
		return 0;
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

	public boolean hasValue(ResultSet rs,String col,String val){
		try {
			if (hasColumn(rs, col)) {
				int c=0;
				while (rs.next()){
					c++;
				}
				System.out.println("c = "+c);
				return !(c == 0);
			}
		}catch (Exception ex){
			ex.printStackTrace();
			return false;
		}
		return false;
	}
	/*
	private String mactchValues(String columns,String values){
		String[] cols = columns.split(",");
		String[] vals = values.split(",");
		if(cols.length != vals.length){
			System.out.println("length don't match");
			return null;
		}
		String out = "";
		for(int i = 0;i< columns.length();i++){
			out
		}
		return "";
	}
	*/

}
