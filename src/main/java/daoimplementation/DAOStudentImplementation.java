package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.StudentDAO;
import model.Student;

public class DAOStudentImplementation implements StudentDAO {
	private SessionFactory sessionFactory;
	
	public DAOStudentImplementation() {}
	
	public DAOStudentImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public List<Student> selectAllStudents(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Student");
		List<Student> allStudents = query.list();
		return allStudents;
	}
	
	@Override
	public void insertStudent(Student student){
		Session session = sessionFactory.getCurrentSession();
		session.save(student);
	}
	
	@Override
	public Student selectStudentById(UUID studentId) {
		Session session = sessionFactory.getCurrentSession();
		Student student = (Student)session.get(Student.class, studentId);
		return student;
	}
	
	@Override
	public void updateStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.update(student);
	}
	
	@Override
	public void deleteStudent(Student student) {
		Session session = sessionFactory.getCurrentSession();
		session.delete(student);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
