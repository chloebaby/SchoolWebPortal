package service;

import java.util.List;
import java.util.UUID;

import junit.framework.TestCase;
import model.Role;
import model.Student;
import model.User;
import model.UserRole;

public class TestStudentService extends TestCase {

	private StudentServiceInterface studentService;
	private RoleServiceInterface roleService;
	private UserRoleServiceInterface userRoleService;
	private Student student;
	private Role role;
	private User user;
	private UserRole userRole;
	private java.util.Date today;
	private java.sql.Date date;
	
	public TestStudentService(String name) {
		super(name);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		String firstName = "Morty";
		String lastName = "Smith";
		String email = "morty.smith@hotmail.ca";
		String rolename = null;
		UUID roleId = null;
		today = new java.util.Date();
		date = new java.sql.Date(today.getTime());
		
		roleService = new RoleService();
		
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
		
		studentService.saveStudent(student);
		userRoleService.saveUserRole(userRole);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testFindAllStudents() {
		List<Student> allStudents = daoStudentImplementation.select();
	}

}
