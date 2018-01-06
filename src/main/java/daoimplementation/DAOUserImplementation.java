package daoimplementation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.UserDAO;
import model.Student;
import model.User;
import sqlconnection.SQLConnection;

public class DAOUserImplementation implements UserDAO {
	private SessionFactory sessionFactory;
	
	public DAOUserImplementation() {}
	
	public DAOUserImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	@Override
	public User selectUserByUserName(String username) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "FROM User where username = :username";
		Query query = session.createQuery(hql);
		query.setParameter("username", username);
		User user = (User)query.uniqueResult();
		return user;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
