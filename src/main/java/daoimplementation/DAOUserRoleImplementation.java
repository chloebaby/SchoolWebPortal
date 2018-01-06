package daoimplementation;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dao.UserRoleDAO;
import model.UserRole;

public class DAOUserRoleImplementation  implements UserRoleDAO {
	private SessionFactory sessionFactory;
	
	public DAOUserRoleImplementation() {}
	
	public DAOUserRoleImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void insertUserRole(UserRole userRole) {
		Session session = sessionFactory.getCurrentSession();
		session.save(userRole);
	}
	
	@Override
	public void updateUserRoleByUsername(UserRole userRole) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "update UserRole set rolename = :rolename, lastModified = :lastModified where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("rolename", userRole.getRolename());
		query.setParameter("lastModified", userRole.getLastModified());
		query.setParameter("username", userRole.getUsername());
		int result = query.executeUpdate();
	}
	
	@Override
	public void deleteUserRoleByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from UserRole where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.executeUpdate();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
