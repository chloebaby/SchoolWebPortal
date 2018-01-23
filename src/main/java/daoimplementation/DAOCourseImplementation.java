package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import dao.CourseDAO;
import model.Course;

@Repository
public class DAOCourseImplementation implements CourseDAO{
	private SessionFactory sessionFactory;
	
	public DAOCourseImplementation() {}
	
	public DAOCourseImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Course> selectAllCourses(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Course");
		List<Course> allCourses = query.list();
		return allCourses;
	}
	
	@Override
	public Course selectCourseById(UUID courseId){
		Session session = sessionFactory.getCurrentSession();
		Course course = session.get(Course.class, courseId);
		return course;
	}
	
	@Override
	public void insertCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.save(course);
	}
	
	@Override
	public void deleteCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(course);
	}
	
	@Override
	public void updateCourse(Course course) {
		Session session = sessionFactory.getCurrentSession();
		session.update(course);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
