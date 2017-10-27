package service;

import java.util.List;

import dao.SchoolDAO;
import daoimplementation.DAOStudentImplementation;
import model.Student;

public class StudentService implements StudentServiceInterface<Student> {
	private SchoolDAO daoSchoolImplementation;
	
	
	public StudentService() {
		daoSchoolImplementation = new DAOStudentImplementation();
	}
	
	@Override
	public List<Student> findAll(){
		return daoSchoolImplementation.select();
	}
}
