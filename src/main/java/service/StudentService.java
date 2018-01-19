package service;

import java.util.List;
import java.util.UUID;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.SchoolDAO;
import dao.StudentDAO;
import dao.UserRoleDAO;
import daoimplementation.DAOStudentImplementation;
import daoimplementation.DAOUserRoleImplementation;
import model.Student;
import model.User;
import model.UserRole;

@Service
public class StudentService implements StudentServiceInterface{
	private StudentDAO daoStudentImplementation;
	
	public StudentService() {}
	
	public StudentService(StudentDAO daoStudentImplementation) {
		this.daoStudentImplementation = daoStudentImplementation;
	}
	
	@Override
	@Transactional
	public List<Student> findAllStudents(){
		List<Student> allStudents = daoStudentImplementation.selectAllStudents();
		return allStudents;
	}
	
	@Override
	@Transactional
	public void saveStudent(Student student) {
		daoStudentImplementation.insertStudent(student);
	}
	
	@Override
	@Transactional
	public Student findStudentById(UUID studentId) {
		return daoStudentImplementation.selectStudentById(studentId);
	}
	
	@Override
	@Transactional
	public void updateStudent(Student student) {
		daoStudentImplementation.updateStudent(student);
	}
	
	@Override
	@Transactional
	public void deleteStudentById(UUID studentId) {
		Student studentToDelete = daoStudentImplementation.selectStudentById(studentId);
		daoStudentImplementation.deleteStudent(studentToDelete);
	}

	public StudentDAO getDaoStudentImplementation() {
		return daoStudentImplementation;
	}

	public void setDaoStudentImplementation(StudentDAO daoStudentImplementation) {
		this.daoStudentImplementation = daoStudentImplementation;
	}
}
