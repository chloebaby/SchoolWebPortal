package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.ResultDAO;
import model.Result;
import model.Student;
import sqlconnection.SQLConnection;

public class DAOResultImplementation implements ResultDAO {
	private SessionFactory sessionFactory;
	
	public DAOResultImplementation() {}
	
	public DAOResultImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Result> selectAllResults() {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Result");
		List<Result> allResults = query.list();
		return allResults;
	}
	
	@Override
	public void insertResult(Result result) {
		Session session = sessionFactory.getCurrentSession();
		session.save(result);
	}
	
	@Override
	public void updateResult(Result result) {
		Session session = sessionFactory.getCurrentSession();
		session.update(result);
	}
	
	@Override
	public Result selectResultById(UUID resultId) {
		Session session = sessionFactory.getCurrentSession();
		Result result = session.get(Result.class, resultId);
		return result;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
