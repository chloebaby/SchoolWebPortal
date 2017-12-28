package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CourseService;
import service.CourseServiceInterface;
import service.ResultService;
import service.ResultServiceInterface;
import service.StudentService;
import service.StudentServiceInterface;
import util.Constants;

public class ResultController extends HttpServlet{
	private ResultServiceInterface resultService;
	private StudentServiceInterface studentService;
	private CourseServiceInterface courseService;
	
	public ResultController() {
		super();
		resultService = new ResultService();
		studentService = new StudentService();
		courseService = new CourseService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter(Constants.REQUEST_PARAMETER_ACTION);
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLRESULTS, resultService.findAllResults());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_RESULTPAGE).forward(request, response);
		
	}
}
