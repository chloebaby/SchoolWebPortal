package daoimplementation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dao.SemesterDAO;
import model.Semester;
import model.Student;

@Repository
public class DAOSemesterImplementation implements SemesterDAO{
	private SessionFactory sessionFactory;
	
	public DAOSemesterImplementation() {}
	
	public DAOSemesterImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public Set<Semester> selectAllSemesters(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("From Semester");
		List<Semester> allSemesters = query.list();
		Set<Semester> setAllSemesters = new HashSet<Semester>(allSemesters);
		
		return setAllSemesters;
	}
	
	@Override
	public UUID selectUUIDBySemesterName(String semester) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select semesterId from Semester where semester = :semester";
		Query query = session.createQuery(hql);
		query.setParameter("semester", semester);
		UUID semesterId = (UUID)query.uniqueResult();
		
		return semesterId;
	}
	
	@Override
	public Semester selectSemesterByUUID(UUID semesterId) {
		Session session = sessionFactory.getCurrentSession();
		Semester semester = session.get(Semester.class, semesterId);
		return semester;
	}
	
	@Override
	public void updateSemester(Semester semester) {
		Session session = sessionFactory.getCurrentSession();
		session.update(semester);
	}
	
	@Override
	public void saveSemester(Semester semester) {
		Session session = sessionFactory.getCurrentSession();
		session.save(semester);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
