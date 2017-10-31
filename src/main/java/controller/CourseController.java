package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CourseService;
import service.CourseServiceInterface;
import model.Course;

public class CourseController extends HttpServlet{
	private CourseServiceInterface<Course> courseService;
	
	public CourseController() {
		super();
		courseService = new CourseService();
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setAttribute("allCourses", courseService.findAllCourses());
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
		
	}
}
