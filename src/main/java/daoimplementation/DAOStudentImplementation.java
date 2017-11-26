package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;

import dao.StudentDAO;
import sqlconnection.SQLConnection;
import model.Student;

public class DAOStudentImplementation extends SQLConnection implements StudentDAO {
	
	public DAOStudentImplementation() {
		super();
	}
	
	@Override
	public List<Student> selectAllStudents(){
		openCurrentSession();
		Query query = getCurrentSession().createQuery("FROM Student");
		List<Student> allStudents = query.list();
		closeCurrentSession();
		
		return allStudents;
	}
	
	@Override
	public void insertStudent(Student student){
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().save(student);
		commitTransaction();
		closeCurrentSession();
	}
	
	@Override
	public Student selectStudentById(UUID studentId) {
		openCurrentSession();
		Student student = getCurrentSession().get(Student.class, studentId);
		closeCurrentSession();
		return student;
	}
	
	@Override
	public void updateStudent(Student student) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().update(student);
		commitTransaction();
		closeCurrentSession();
	}
	
	@Override
	public void deleteStudent(Student student) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().delete(student);
		commitTransaction();
		closeCurrentSession();
	}
}
