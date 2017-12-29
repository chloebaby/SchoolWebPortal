package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;

import dao.ResultDAO;
import model.Result;
import model.Student;
import sqlconnection.SQLConnection;

public class DAOResultImplementation extends SQLConnection implements ResultDAO {

	public DAOResultImplementation() {
		super();
	}
	
	@Override
	public List<Result> selectAllResults() {
		openCurrentSession();
		Query query = getCurrentSession().createQuery("FROM Result");
		List<Result> allResults = query.list();
		closeCurrentSession();
		return allResults;
	}
	
	@Override
	public void insertResult(Result result) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().save(result);
		commitTransaction();
		closeCurrentSession();
	}
	
	@Override
	public void updateResult(Result result) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().update(result);
		commitTransaction();
		closeCurrentSession();
	}
	
	@Override
	public Result selectResultById(UUID resultId) {
		openCurrentSession();
		Result result = getCurrentSession().get(Result.class, resultId);
		closeCurrentSession();
		return result;
	}

}
