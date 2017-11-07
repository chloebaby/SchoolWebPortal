package daoimplementation;

import dao.UserDAO;
import model.User;
import sqlconnection.SQLConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOUserImplementation extends SQLConnection implements UserDAO{
	
	public DAOUserImplementation() {
		super();
	}
	
	public void insertUser(User user) {
		String insertUser = "insert into Users (user_id, username, password, last_modified) values(?, ?, ?, ?)";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertUser);
			
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, user.getUsername());
			ps.setString(3,user.getPassword());
			ps.setDate(4, date);
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
	}
	
	public int selectUserIdByName(User user) {
		String selectUserIdByName = "select user_id from Users where username = ?";
		int userId = 0;
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectUserIdByName);
			
			ps.setString(1, user.getUsername());
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				userId = rs.getInt("user_id");
			}
			
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
			
		}
		
		return userId;
	}
	
	public void deleteUserById(int userId) {
		String deleteUserById = "delete from Users where user_id = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(deleteUserById);
			
			ps.setInt(1, userId);
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
