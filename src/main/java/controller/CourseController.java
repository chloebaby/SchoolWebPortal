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
		int courseId = Integer.valueOf(request.getParameter("courseId"));
		String courseName = request.getParameter("courseName");
		String courseCode = request.getParameter("courseCode");
		
		Course course = new Course();
		course.setCourseId(courseId);
		course.setCourseName(courseName);
		course.setCourseCode(courseCode);
		
		if(option.equalsIgnoreCase("save")) {
			courseService.saveCourse(course);
		}else if(option.equalsIgnoreCase("update")) {
			courseService.updateCourse(course);
		}
		
		request.setAttribute("allCourses", courseService.findAllCourses());
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			int courseId = Integer.valueOf(request.getParameter("courseId"));
			courseService.deleteCourseById(courseId);
			request.setAttribute("allCourses", courseService.findAllCourses());
			getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			int courseId = Integer.valueOf(request.getParameter("courseId"));
			request.setAttribute("course", courseService.findCourseById(courseId));
			getServletContext().getRequestDispatcher("/jsp/modify-pages/course-modified-page.jsp").forward(request, response);
		}else {
			request.setAttribute("allCourses", courseService.findAllCourses());
			getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
		}
	}
}
