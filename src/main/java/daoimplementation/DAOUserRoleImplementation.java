package daoimplementation;

import java.util.UUID;

import org.hibernate.query.Query;

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
	public void updateUserRole(UserRole userRole) {
		Session session = sessionFactory.getCurrentSession();
		session.update(userRole);
	}
	
	@Override
	public void deleteUserRoleByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "delete from UserRole where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		query.executeUpdate();
	}
	
	@Override
	public UserRole selectUserRoleByUsername(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM UserRole where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		UserRole userRole = (UserRole) query.uniqueResult();
		return userRole;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
