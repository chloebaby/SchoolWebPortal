package controller;

import java.io.IOException;

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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("allStudents", studentService.findAll());
		getServletContext().getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
		
	}
}
