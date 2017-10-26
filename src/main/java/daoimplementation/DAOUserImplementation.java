package daoimplementation;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.UserDAO;
import model.User;
import sqlconnection.SQLConnection;

public class DAOUserImplementation extends SQLConnection implements UserDAO{
	
	public DAOUserImplementation() {
		super();
	}
	
	public User selectUsernamePasswordByUsername(String username) {
		String selectUserPass = "select user_name, user_password, first_name, last_name from Users where user_name = ?";
		User user = new User();
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectUserPass);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("user_password"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return user;
	}
}
