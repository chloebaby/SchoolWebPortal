package daoimplementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDAO;
import model.User;
import sqlconnection.SQLConnection;

public class DAOUserImplementation extends SQLConnection implements UserDAO{
	private final String USER_NAME = "user_name";
	private final String PASSWORD = "password";
	
	public DAOUserImplementation() {
		super();
	}
	
	public User selectUsernamePasswordByUsername(String username) {
		String selectUserPass = "select user_name, password from Users where usern_name = ?";
		User user = new User();
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectUserPass);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setUserName(rs.getString(USER_NAME));
			user.setPassword(rs.getString(PASSWORD));
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return user;
	}
}
