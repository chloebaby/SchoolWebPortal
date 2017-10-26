package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;
import dao.SchoolDAO;
import daoimplementation.DAOStudentImplementation;
import model.Student;

public class StudentController extends HttpServlet{
	private StudentDAO daoStudentImplementation;
	private SchoolDAO<Student> daoSchoolImplementation;
	
	public StudentController(){
		super();
		daoStudentImplementation = new DAOStudentImplementation();
		daoSchoolImplementation = new DAOStudentImplementation();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		List<Student> allStudents = daoSchoolImplementation.select();
		request.setAttribute("allStudents", allStudents);
		getServletContext().getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
		
	}
}
