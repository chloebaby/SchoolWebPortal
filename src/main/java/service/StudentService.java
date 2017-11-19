package service;

import java.util.List;

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
	//private SchoolDAO<Student> daoSchoolImplementation;
	private StudentDAO daoStudentImplementation;
	//private UserDAO doaUserImplementation;
	//private UserRoleDAO daoUserRoleImplementation;
	
	public StudentService() {
		//daoSchoolImplementation = new DAOStudentImplementation();
		daoStudentImplementation = new DAOStudentImplementation();
		//doaUserImplementation = new DAOUserImplementation();
		//daoUserRoleImplementation = new DAOUserRoleImplementation();
	}
	
	@Override
	public List<Student> findAllStudents(){
		daoStudentImplementation.openSession();
		List<Student> allStudents = daoStudentImplementation.select();
		daoStudentImplementation.closeSession();
		return allStudents;
	}
	
	@Override
	public void close() {
		daoStudentImplementation.close();
	}
	
/*	@Override
	public void saveStudent(Student student, User user, UserRole userRole){
		doaUserImplementation.insertUser(user);
		daoUserRoleImplementation.insertUserRole(userRole);
		
		student.setUserId(doaUserImplementation.selectUserIdByName(user));
		
		daoStudentImplementation.insertStudent(student);
	}
	
	@Override
	public void deleteStudentById(int studentId, int userId, String username) {
		daoStudentImplementation.deleteStudent(studentId);
		doaUserImplementation.deleteUserById(userId);
		daoUserRoleImplementation.deleteUserRoleByUsername(username);
	}
	
	@Override
	public Student findStudentById(int studentId) {
		return daoSchoolImplementation.selectById(studentId);
	}
	
	@Override
	public void updateStudent(Student student, User user, UserRole userRole) {
		daoStudentImplementation.updateStudent(student);
		doaUserImplementation.updateUser(user);
		daoUserRoleImplementation.updateUserRole(userRole);
	}*/
}
