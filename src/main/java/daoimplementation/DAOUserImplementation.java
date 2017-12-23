package daoimplementation;

import org.hibernate.query.Query;

import dao.UserDAO;
import model.Student;
import model.User;
import sqlconnection.SQLConnection;

public class DAOUserImplementation extends SQLConnection implements UserDAO {

	public DAOUserImplementation() {
		super();
	}
	
	@Override
	public User selectUserByUserName(String username) {
		String hql = "FROM User where username = :username";
		openCurrentSession();
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("username", username);
		User user = (User)query.uniqueResult();
		closeCurrentSession();
		return user;
	}

}
