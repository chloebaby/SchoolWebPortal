package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
		String option = request.getParameter("option");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String roleReq = request.getParameter("rolename");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		String[] roleDetails = roleReq.split(Character.toString((char)194) + Character.toString((char)160));
		UUID roleId = UUID.fromString(roleDetails[0]);
		String roleName = roleDetails[1];
		
		Student student = new Student();
		User user = new User();
		UserRole userRole = new UserRole();
		Role role = new Role();
		
		role.setRolename(roleName);
		role.setRoleId(roleId);
		
		user.setPassword(password);
		user.setUsername(username);
		user.setLastModified(date);
		
		userRole.setRolename(roleName);
		userRole.setUsername(username);
		userRole.setLastModified(date);
		
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setUser(user);
		student.setRole(role);
		student.setLastModified(date);
		
		
		if(option.equalsIgnoreCase("save")) {
			studentService.saveStudent(student);
			userRoleService.saveUserRole(userRole);
			
		}else if(option.equalsIgnoreCase("update")) {
			//student.setStudentId(UUID.fromString(request.getParameter("studentId")));
			//studentService.updateStudent(student, user, userRole);
		}
		
		request.setAttribute("allStudents", studentService.findAllStudents());
		request.setAttribute("allRoles", roleService.findAllRoles());
		getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		
		request.setAttribute("allStudents", studentService.findAllStudents());
		request.setAttribute("allRoles", roleService.findAllRoles());
		getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		
			
/*		if(action.equalsIgnoreCase("delete")) {
			int studentId = Integer.valueOf(request.getParameter("studentId"));
			int userId = Integer.valueOf(request.getParameter("userId"));
			String username = request.getParameter("username");
			studentService.deleteStudentById(studentId, userId, username);
			request.setAttribute("allStudents", studentService.findAllStudents());
			request.setAttribute("allRoles", roleService.findAllRoles());
			getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			int studentId = Integer.valueOf(request.getParameter("studentId"));
			request.setAttribute("student", studentService.findStudentById(studentId));
			request.setAttribute("allRoles", roleService.findAllRoles());
			getServletContext().getRequestDispatcher("/jsp/modify-pages/student-modified-page.jsp").forward(request, response);
		}else {
			request.setAttribute("allStudents", studentService.findAllStudents());
			request.setAttribute("allRoles", roleService.findAllRoles());
			getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		}*/
		
	}
}
