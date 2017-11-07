package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.UserRoleDAO;
import model.UserRole;
import sqlconnection.SQLConnection;

public class DAOUserRoleImplementation extends SQLConnection implements UserRoleDAO {
	
	public DAOUserRoleImplementation() {
		super();
	}
	
	public void insertUserRole(UserRole userRole) {
		String insertUserRole = "insert into UserRoles (userrole_id, username, rolename, last_modified) values (?, ?, ?, ?)";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertUserRole);
			
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, userRole.getUsername());
			ps.setString(3, userRole.getRolename());
			ps.setDate(4,  date);
			
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
