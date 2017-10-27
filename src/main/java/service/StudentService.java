package service;

import java.util.List;

import dao.SchoolDAO;
import dao.StudentDAO;
import daoimplementation.DAOStudentImplementation;
import model.Student;

public class StudentService implements StudentServiceInterface<Student> {
	private SchoolDAO daoSchoolImplementation;
	private StudentDAO daoStudentImplementation;
	
	
	public StudentService() {
		daoSchoolImplementation = new DAOStudentImplementation();
		daoStudentImplementation = new DAOStudentImplementation();
	}
	
	@Override
	public List<Student> findAllStudents(){
		return daoSchoolImplementation.select();
	}
	
	@Override
	public List<Student> saveStudent(Student entity){
		daoStudentImplementation.insertStudent(entity);
		return findAllStudents();
	}
}
