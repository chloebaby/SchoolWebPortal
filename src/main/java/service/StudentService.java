package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;

import dao.SchoolDAO;
import dao.StudentDAO;
import dao.UserDAO;
import dao.UserRoleDAO;
import daoimplementation.DAOStudentImplementation;
import daoimplementation.DAOUserImplementation;
import daoimplementation.DAOUserRoleImplementation;
import model.Student;
import model.User;
import model.UserRole;

public class StudentService implements StudentServiceInterface{
	private StudentDAO daoStudentImplementation;
	
	public StudentService() {
		daoStudentImplementation = new DAOStudentImplementation();
	}
	
	@Override
	public List<Student> findAllStudents(){
		List<Student> allStudents = daoStudentImplementation.selectAllStudents();
		return allStudents;
	}
	
	@Override
	public void saveStudent(Student student) {
		daoStudentImplementation.insertStudent(student);
	}
	
	@Override
	public Student findStudentById(UUID studentId) {
		return daoStudentImplementation.selectStudentById(studentId);
	}
	
	@Override
	public void updateStudent(Student student) {
		daoStudentImplementation.updateStudent(student);
	}
	
	@Override
	public void deleteStudentById(UUID studentId) {
		Student studentToDelete = daoStudentImplementation.selectStudentById(studentId);
		daoStudentImplementation.deleteStudent(studentToDelete);
	}
}
