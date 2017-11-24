package service;

import java.util.List;

import dao.StudentDAO;
import daoimplementation.DAOStudentImplementation;
import junit.framework.TestCase;
import model.Student;

public class TestStudentService extends TestCase {

	private StudentDAO daoStudentImplementation;
	
	public TestStudentService(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		daoStudentImplementation = new DAOStudentImplementation();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testFindAllStudents() {
		List<Student> allStudents = daoStudentImplementation.select();
	}

}
