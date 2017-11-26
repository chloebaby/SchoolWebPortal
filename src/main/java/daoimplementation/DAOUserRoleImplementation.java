package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Query;

import dao.UserRoleDAO;
import model.UserRole;
import sqlconnection.SQLConnection;

public class DAOUserRoleImplementation extends SQLConnection implements UserRoleDAO {
	
	public DAOUserRoleImplementation() {
		super();
	}
	
	@Override
	public void insertUserRole(UserRole userRole) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().save(userRole);
		commitTransaction();
		closeCurrentSession();
	}
	
	@Override
	public void updateUserRoleByUsername(UserRole userRole) {
		String hql = "update UserRole set rolename = :rolename, lastModified = :lastModified where username = :username";
		openCurrentSession();
		openCurrentTransaction();
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("rolename", userRole.getRolename());
		query.setParameter("lastModified", userRole.getLastModified());
		query.setParameter("username", userRole.getUsername());
		int result = query.executeUpdate();
		commitTransaction();
		closeCurrentSession();
	}
	
	public void deleteUserRoleByUsername(String username) {
		String hql = "delete from UserRole where username = :username";
		openCurrentSession();
		openCurrentTransaction();
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		query.executeUpdate();
		commitTransaction();
		closeCurrentSession();
	}
	
/*	public void insertUserRole(UserRole userRole) {
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
	
	public void deleteUserRoleByUsername(String username) {
		String deleteUserRoleByUsername = "delete from UserRoles where username like ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(deleteUserRoleByUsername);
			
			ps.setString(1, username);
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void updateUserRole(UserRole userRole) {
		String updateUserRole = "update UserRoles set rolename = ?, last_modified = ? where username like ?";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(updateUserRole);
			
			ps.setString(1, userRole.getRolename());
			ps.setDate(2, date);
			ps.setString(3, userRole.getUsername());
			
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}*/
}
