package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Role;
import model.Student;
import service.RoleService;
import service.RoleServiceInterface;
import service.StudentService;
import service.StudentServiceInterface;

@SuppressWarnings("serial")
public class StudentController extends HttpServlet{
	private StudentServiceInterface<Student> studentService;
	private RoleServiceInterface roleService;
	
	public StudentController(){
		super();
		studentService = new StudentService();
		roleService = new RoleService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter("option");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		String role = request.getParameter("rolename");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		String[] roleDetails = role.split(Character.toString((char)194) + Character.toString((char)160));
		int roleId = Integer.valueOf(roleDetails[0]);
		String roleName = roleDetails[1];
		
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		student.setRoleId(roleId);
		student.setRolename(roleName);
		student.setUsername(username);
		student.setPassword(password);
		
		//TODO: need to insert the new student into the database
		
		List<Student> allStudents = new ArrayList<Student>();
		
		if(option.equalsIgnoreCase("save")) {
			allStudents = studentService.saveStudent(student);
		}else if(option.equalsIgnoreCase("update")) {
			student.setStudentId(Integer.valueOf(request.getParameter("studentId")));
			studentService.updateStudent(student);
			allStudents = studentService.findAllStudents();
		}else if(option.equalsIgnoreCase("cancel")){
			allStudents = studentService.findAllStudents();
		}else {
			allStudents = studentService.findAllStudents();
		}
		
		request.setAttribute("allStudents", allStudents);
		getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
			
		if(action.equalsIgnoreCase("delete")) {
			int studentId = Integer.valueOf(request.getParameter("studentId"));
			studentService.deleteStudentById(studentId);
			request.setAttribute("allStudents", studentService.findAllStudents());
			request.setAttribute("allRoles", roleService.findAllRoles());
			getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			int studentId = Integer.valueOf(request.getParameter("studentId"));
			request.setAttribute("student", studentService.findStudentById(studentId));
			getServletContext().getRequestDispatcher("/jsp/modify-pages/student-modified-page.jsp").forward(request, response);
		}else {
			request.setAttribute("allStudents", studentService.findAllStudents());
			request.setAttribute("allRoles", roleService.findAllRoles());
			getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		}
		
	}
}
