package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
	private Connection connection = null;
	private String url;
	private String username;
	private String password;
	
	public SQLConnection() {
		url = "jdbc:mysql://localhost:3306/School";
		username = "school";
		password = "mysql";
	}
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, username, password);
			connection.setAutoCommit(true);
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		}
		
		return connection;
	}
	
	/*
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			}catch(SQLException sqle) {
				sqle.printStackTrace();
			}
		}
	}
	*/
}
