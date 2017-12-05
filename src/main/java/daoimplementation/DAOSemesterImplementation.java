package daoimplementation;

import java.util.List;

import org.hibernate.query.Query;

import dao.SemesterDAO;
import model.Semester;
import sqlconnection.SQLConnection;

public class DAOSemesterImplementation extends SQLConnection implements SemesterDAO{

	public DAOSemesterImplementation() {
		super();
	}
	
	@Override
	public List<Semester> selectAllSemesters(){
		openCurrentSession();
		Query query = getCurrentSession().createQuery("From Semester");
		List<Semester> allSemesters = query.list();
		closeCurrentSession();
		
		return allSemesters;
	}
}
