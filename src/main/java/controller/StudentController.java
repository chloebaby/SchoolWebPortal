package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.Student;
import service.StudentService;
import service.StudentServiceInterface;

@SuppressWarnings("serial")
public class StudentController extends HttpServlet{
	private StudentServiceInterface<Student> studentService;
	
	public StudentController(){
		super();
		studentService = new StudentService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter("option");
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		
		Student student = new Student();
		student.setFirstName(firstName);
		student.setLastName(lastName);
		student.setEmail(email);
		
		List<Student> allStudents = new ArrayList<Student>();
		
		if(option.equalsIgnoreCase("save")) {
			allStudents = studentService.saveStudent(student);
		}else {
			allStudents = studentService.findAllStudents();
		}
		
		request.setAttribute("allStudents", allStudents);
		//getServletContext().getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
		getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("allStudents", studentService.findAllStudents());
		//getServletContext().getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
		getServletContext().getRequestDispatcher("/jsp/student-page.jsp").forward(request, response);
		
	}
}
