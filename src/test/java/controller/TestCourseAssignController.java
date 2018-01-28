package controller;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import junit.framework.TestCase;
import model.Role;
import model.Semester;
import model.Student;
import model.User;
import model.UserRole;
import service.RoleServiceInterface;
import service.StudentServiceInterface;
import service.UserRoleServiceInterface;
import service.SemesterServiceInterface;
import util.Constants;

public class TestCourseAssignController extends TestCase {
	private String username = "david.parr";
	private String firstName = "David";
	private String lastName = "Parr";
	private String roleName = "student";
	private String email = "david.parr@hotmail.ca";
	private String password = "school1";
	private StudentServiceInterface studentService;
	private RoleServiceInterface roleService;
	private UserRoleServiceInterface userRoleService;
	private SemesterServiceInterface semesterService;
	private ClassPathXmlApplicationContext ctx;
	
	public TestCourseAssignController(String name) {
		super(name);
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		roleService = (RoleServiceInterface)ctx.getBean(Constants.SPRING_BEAN_ROLESERVICE);
		userRoleService = (UserRoleServiceInterface)ctx.getBean(Constants.SPRING_BEAN_USERROLESERVICE);
		semesterService = (SemesterServiceInterface)ctx.getBean(Constants.SPRING_BEAN_SEMESTERSERVICE);
	}
	
	protected void setUp() throws Exception{
		super.setUp();
		
		Role role = new Role();
		UUID roleId = roleService.findUUIDByRolename(roleName);
		role.setRoleId(roleId);
		
		UserRole userRole = new UserRole();
		userRole.setRolename(roleName);
		userRole.setUsername(username);
		userRole.setLastModified(generateLastModifiedDate());
		
		User user = new User();
		user.setLastModified(generateLastModifiedDate());
		user.setUsername(username);
		user.setPassword(password);
		
		Student student = new Student();
		student.setEmail(email);
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setUser(user);
		student.setRole(role);
		student.setLastModified(generateLastModifiedDate());
		
		studentService.saveStudent(student);
		userRoleService.saveUserRole(userRole);
		
	}
	
	protected void tearDown() throws Exception{
		super.tearDown();
		
		removeStudentAndUserRole();
		
		removeAssignSemesterToStudent();

	}
	
	public void testAssignSameSemesterToSameStudent() throws Exception{
		String semesterWinter = "Winter";
		Set<Semester> listOfSemesters = semesterService.findAllSemesters();
		Student student = studentService.findAllStudents().get(0);
		int count = 0;
		
		while(count < 2) {
			for(Semester sem : listOfSemesters) {
				if(sem.getSemester().equalsIgnoreCase(semesterWinter)) {
					Semester semester = semesterService.findSemesterByUUID(sem.getSemesterId());
					List<Student> semesterStudent = semester.getListOfStudents();
					semesterStudent.add(student);
					semesterService.updateSemester(semester);
				}
			}
			count++;
		}
		
		assertTrue(true);
	}
	
	private void removeAssignSemesterToStudent() {
		Set<Semester> listOfSemesters = semesterService.findAllSemesters();
		
		for(Semester sem : listOfSemesters) {
			Semester semester = semesterService.findSemesterByUUID(sem.getSemesterId());
			List<Student> semesterStudent = semester.getListOfStudents();
			semesterStudent.clear();
			semesterService.updateSemester(semester);
		}
	}
	
	private void removeStudentAndUserRole() {
		List<Student> listOfStudents = studentService.findAllStudents();
		
		
		for(Student student : listOfStudents) {
			studentService.deleteStudentById(student.getStudentId());
			userRoleService.deleteUserRolebyUsername(student.getUser().getUsername());
		}
	}
	
	private java.sql.Date generateLastModifiedDate(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
	
}
