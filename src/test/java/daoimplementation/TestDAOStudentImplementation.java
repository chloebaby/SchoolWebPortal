package daoimplementation;

import java.util.List;

import dao.SchoolDAO;
import dao.StudentDAO;
import dao.UserDAO;
import dao.UserRoleDAO;
import junit.framework.TestCase;
import model.Student;
import model.User;
import model.UserRole;

public class TestDAOStudentImplementation extends TestCase {
	private Student student;
	private User user;
	private UserRole userRole;
	
	private String firstName;
	private String lastName;
	private String email;
	private String rolename;
	private int roleId;
	private String username;
	private String password;
	
	private SchoolDAO<Student> daoSchoolImplementation;
	private StudentDAO daoStudentImplementation;
	private UserDAO daoUserImplementation;
	private UserRoleDAO daoUserRoleImplementation;
	
	private List<Student> allStudents;
	
	public TestDAOStudentImplementation(String name) {
		super(name);
	}
	
	protected void setUp() {
		firstName = "Morty";
		lastName = "Smith";
		email = "morty.smith@hotmail.ca";
		rolename = "student";
		roleId = 2;
		username = "morty.smith";
		password = "school1";
		
		student = new Student();
		user = new User();
		userRole = new UserRole();
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setRoleId(roleId);
		
		user.setPassword(password);
		user.setUsername(username);
		
		userRole.setRolename(rolename);
		userRole.setUsername(username);
		
		daoSchoolImplementation = new DAOStudentImplementation();
		daoStudentImplementation = new DAOStudentImplementation();
		daoUserImplementation = new DAOUserImplementation();
		daoUserRoleImplementation = new DAOUserRoleImplementation();
		

	}
	
	protected void tearDown() {
		allStudents.clear();
	}
	
	public void testDeleteStudent() {
		daoUserImplementation.insertUser(user);
		daoUserRoleImplementation.insertUserRole(userRole);
		student.setUserId(daoUserImplementation.selectUserIdByName(user));
		daoStudentImplementation.insertStudent(student);
		
		allStudents = daoSchoolImplementation.select();
		
		Student studentById = daoSchoolImplementation.selectById(allStudents.get(0).getStudentId());
		
		daoStudentImplementation.deleteStudent(studentById.getStudentId());
		daoUserImplementation.deleteUserById(studentById.getUserId());
		daoUserRoleImplementation.deleteUserRoleByUsername(studentById.getUsername());
		
		allStudents = daoSchoolImplementation.select();
		
		assertEquals(0, allStudents.size());
		
	}
	
	public void testInsertStudent() {
		daoUserImplementation.insertUser(user);
		daoUserRoleImplementation.insertUserRole(userRole);
		student.setUserId(daoUserImplementation.selectUserIdByName(user));
		daoStudentImplementation.insertStudent(student);
		
		allStudents = daoSchoolImplementation.select();
		
		Student studentById = daoSchoolImplementation.selectById(allStudents.get(0).getStudentId());
		
		assertEquals(studentById.getStudentId(), allStudents.get(0).getStudentId());
		assertEquals(studentById.getUsername(), allStudents.get(0).getUsername());
		assertEquals(studentById.getUserId(), allStudents.get(0).getUserId());
		assertEquals(studentById.getRoleId(), allStudents.get(0).getRoleId());
		assertEquals(studentById.getPassword(), allStudents.get(0).getPassword());
		assertEquals(studentById.getRolename(), allStudents.get(0).getRolename());
		assertEquals(studentById.getFirstName(), allStudents.get(0).getFirstName());
		assertEquals(studentById.getLastName(), allStudents.get(0).getLastName());
		assertEquals(studentById.getEmail(), allStudents.get(0).getEmail());
		
		daoStudentImplementation.deleteStudent(studentById.getStudentId());
		daoUserImplementation.deleteUserById(studentById.getUserId());
		daoUserRoleImplementation.deleteUserRoleByUsername(studentById.getUsername());

	}
	
	public void testSelect() {
		daoUserImplementation.insertUser(user);
		daoUserRoleImplementation.insertUserRole(userRole);
		student.setUserId(daoUserImplementation.selectUserIdByName(user));
		daoStudentImplementation.insertStudent(student);
		
		daoUserImplementation.insertUser(user);
		daoUserRoleImplementation.insertUserRole(userRole);
		student.setUserId(daoUserImplementation.selectUserIdByName(user));
		daoStudentImplementation.insertStudent(student);
		
		allStudents = daoSchoolImplementation.select();
		assertNotNull(allStudents);
		
		Student studentById = daoSchoolImplementation.selectById(allStudents.get(0).getStudentId());
		
		daoStudentImplementation.deleteStudent(studentById.getStudentId());
		daoUserImplementation.deleteUserById(studentById.getUserId());
		daoUserRoleImplementation.deleteUserRoleByUsername(studentById.getUsername());
		
	}
	
	public void testSelectById() {
		daoUserImplementation.insertUser(user);
		daoUserRoleImplementation.insertUserRole(userRole);
		student.setUserId(daoUserImplementation.selectUserIdByName(user));
		daoStudentImplementation.insertStudent(student);
		
		allStudents = daoSchoolImplementation.select();
	
		Student studentById = daoSchoolImplementation.selectById(allStudents.get(0).getStudentId());
		
		assertEquals(studentById.getStudentId(), allStudents.get(0).getStudentId());
		assertEquals(studentById.getUsername(), allStudents.get(0).getUsername());
		assertEquals(studentById.getUserId(), allStudents.get(0).getUserId());
		assertEquals(studentById.getRoleId(), allStudents.get(0).getRoleId());
		assertEquals(studentById.getPassword(), allStudents.get(0).getPassword());
		assertEquals(studentById.getRolename(), allStudents.get(0).getRolename());
		assertEquals(studentById.getFirstName(), allStudents.get(0).getFirstName());
		assertEquals(studentById.getLastName(), allStudents.get(0).getLastName());
		assertEquals(studentById.getEmail(), allStudents.get(0).getEmail());
		
		daoStudentImplementation.deleteStudent(studentById.getStudentId());
		daoUserImplementation.deleteUserById(studentById.getUserId());
		daoUserRoleImplementation.deleteUserRoleByUsername(studentById.getUsername());
	}
}
