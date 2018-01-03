package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import junit.framework.TestCase;
import model.Result;
import model.Role;
import model.Student;
import model.User;
import model.UserRole;

public class TestStudentService extends TestCase {

	private StudentServiceInterface studentService;
	private RoleServiceInterface roleService;
	private UserRoleServiceInterface userRoleService;
	private ResultServiceInterface resultService;
	private Student student;
	private Role role;
	private User user;
	private UserRole userRole;
	private java.util.Date today;
	private java.sql.Date date;
	private String firstName = "Morty";
	private String lastName = "Smith";
	private String email = "morty.smith@hotmail.ca";
	private String rolename = null;
	private UUID roleId = null;
	
	public TestStudentService(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		today = new java.util.Date();
		date = new java.sql.Date(today.getTime());
		
		roleService = new RoleService();
		studentService = new StudentService();
		userRoleService = new UserRoleService();
		resultService = new ResultService();
		
		List<Role> actualRoles = roleService.findAllRoles();
		
		for(Role actualRole : actualRoles) {
			if(actualRole.getRolename().equals("student")) {
				roleId = actualRole.getRoleId();
				rolename = actualRole.getRolename();
			}
		}
		
		String username = "morty.smith";
		String password = "school1";
		
		student = new Student();
		user = new User();
		userRole = new UserRole();
		role = new Role();
		
		role.setRolename(rolename);
		role.setRoleId(roleId);
		
		user.setPassword(password);
		user.setUsername(username);
		user.setLastModified(date);
		
		userRole.setRolename(rolename);
		userRole.setUsername(username);
		userRole.setLastModified(date);
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setUser(user);
		student.setRole(role);
		student.setLastModified(date);
		student.setEmail(email);
		
		studentService.saveStudent(student);
		userRoleService.saveUserRole(userRole);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		studentService.deleteStudentById(student.getStudentId());
	}
	
	public void testFindAllStudents() throws Exception{
		assertNotNull(studentService.findAllStudents());
	}
	
	public void testFindStudentById() throws Exception{
		assertNotNull(studentService.findStudentById(student.getStudentId()));
	}
	
	public void testUpdateStudent() throws Exception{
		String actualFirstName = firstName;
		String updatedFirstName = "Beth";
		String expectedFirstName = updatedFirstName;
		
		student.setFirstName("Beth");
		studentService.updateStudent(student);
		
		Student updatedStudent = studentService.findStudentById(student.getStudentId());
		
		assertEquals(expectedFirstName, student.getFirstName());
	}
	
	public void testAssociateStudentToResult() throws Exception{
		Result result = new Result();
		result.setLastModified(date);
		
		Set<Result> studentResult = student.getListOfResults();
		studentResult.add(result);
		student.setListOfResults(studentResult);
		student.setLastModified(date);
		result.setStudent(student);
		
		resultService.saveResult(result);
	}
}
