package controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
import model.Student;
import model.User;
import model.UserRole;
import service.RoleService;
import service.RoleServiceInterface;
import service.StudentService;
import service.StudentServiceInterface;
import service.UserRoleService;
import service.UserRoleServiceInterface;
import util.Constants;

@SuppressWarnings("serial")
public class StudentController extends HttpServlet{
	private StudentServiceInterface studentService;
	private RoleServiceInterface roleService;
	private UserRoleServiceInterface userRoleService;
	
	public StudentController(){
		super();
		studentService = new StudentService();
		roleService = new RoleService();
		userRoleService = new UserRoleService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter(Constants.REQUEST_PARAMETER_OPTION);

		Student student = null;
		UserRole userRole = null;
		
		if(!option.equalsIgnoreCase(Constants.REQUEST_ACTION_CANCEL)) {
			student = buildStudent(request);
			userRole = buildUserRole(request);
		}
		
		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_SAVE)) {
			studentService.saveStudent(student);
			userRoleService.saveUserRole(userRole);
			
		}else if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_UPDATE)) {
			String studentId = request.getParameter(Constants.REQUEST_PARAMETER_STUDENTID);
			student.setStudentId(UUID.fromString(studentId));
			studentService.updateStudent(student);
			userRoleService.updateUserRoleByUsername(userRole);
		}
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLROLES, roleService.findAllRoles());
		getServletContext().getRequestDispatcher(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS).forward(request, response);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter(Constants.REQUEST_PARAMETER_ACTION);
	
		if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_DELETE)) {
			doDel(request, response);
		}else if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_EDIT)) {
			doEdit(request, response);
		}else {
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLROLES, roleService.findAllRoles());
			getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_STUDENTPAGE).forward(request, response);
		}
		
	}
	
	private Student buildStudent(HttpServletRequest request) throws ServletException{
		String firstName = request.getParameter(Constants.REQUEST_PARAMETER_FIRSTNAME);
		String lastName = request.getParameter(Constants.REQUEST_PARAMETER_LASTNAME);
		String email = request.getParameter(Constants.REQUEST_PARAMETER_EMAIL);
		String rolename = request.getParameter(Constants.REQUEST_PARAMETER_ROLENAME);
		String username = request.getParameter(Constants.REQUEST_PARAMETER_USERNAME);
		String password = request.getParameter(Constants.REQUEST_PARAMETER_PASSWORD);
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		Student student = new Student();
		User user = new User();
		Role role = new Role();
		
		role.setRolename(rolename);
		
		UUID roleId = roleService.findUUIDByRolename(rolename);
		
		role.setRoleId(roleId);
		
		user.setPassword(password);
		user.setUsername(username);
		user.setLastModified(date);
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setUser(user);
		student.setRole(role);
		student.setLastModified(date);
		
		return student;
	}
	
	private UserRole buildUserRole(HttpServletRequest request) throws ServletException{
		String rolename = request.getParameter(Constants.REQUEST_PARAMETER_ROLENAME);
		String username = request.getParameter(Constants.REQUEST_PARAMETER_USERNAME);
		
		UserRole userRole = new UserRole();
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		userRole.setRolename(rolename);
		userRole.setUsername(username);
		userRole.setLastModified(date);
		
		return userRole;
	}
	
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID studentId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_STUDENTID));
		String username = request.getParameter(Constants.REQUEST_PARAMETER_USERNAME);
		userRoleService.deleteUserRolebyUsername(username);
		studentService.deleteStudentById(studentId);
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS , studentService.findAllStudents());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLROLES, roleService.findAllRoles());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_STUDENTPAGE).forward(request, response);
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID studentId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_STUDENTID));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_STUDENT, studentService.findStudentById(studentId));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLROLES, roleService.findAllRoles());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_STUDENTMODIFYPAGE).forward(request, response);
	}
}
