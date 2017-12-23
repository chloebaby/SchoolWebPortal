package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;

import dao.SemesterDAO;
import model.Semester;
import model.Student;
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
	
	@Override
	public UUID selectUUIDBySemesterName(String semester) {
		String hql = "select semesterId from Semester where semester = :semester";
		openCurrentSession();
		Query query = getCurrentSession().createQuery(hql);
		query.setParameter("semester", semester);
		UUID semesterId = (UUID)query.uniqueResult();
		closeCurrentSession();
		
		return semesterId;
	}
	
	@Override
	public Semester selectSemesterByUUID(UUID semesterId) {
		openCurrentSession();
		Semester semester = getCurrentSession().get(Semester.class, semesterId);
		closeCurrentSession();
		return semester;
	}
	
	@Override
	public void updateSemester(Semester semester) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().update(semester);
		commitTransaction();
		closeCurrentSession();
	}
}
