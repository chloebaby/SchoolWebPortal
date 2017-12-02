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
}
