package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter("option");
		String courseName = request.getParameter("courseName");
		String courseCode = request.getParameter("courseCode");
		
		Course course = new Course();
		course.setCourseName(courseName);
		course.setCourseCode(courseCode);
		
		List<Course> allCourses = new ArrayList<Course>();
		
		if(option.equalsIgnoreCase("save")) {
			allCourses = courseService.saveCourse(course);
		}else {
			allCourses = courseService.findAllCourses();
		}
		
		request.setAttribute("allCourses", allCourses);
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			int courseId = Integer.valueOf(request.getParameter("courseId"));
			courseService.deleteCourseById(courseId);
		}
		
		request.setAttribute("allCourses", courseService.findAllCourses());
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
	}
}
