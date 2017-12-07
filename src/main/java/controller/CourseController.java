package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.CourseService;
import service.CourseServiceInterface;
import service.SemesterService;
import service.SemesterServiceInterface;
import service.StudentService;
import service.StudentServiceInterface;
import model.Course;

public class CourseController extends HttpServlet{
	private CourseServiceInterface courseService;
	private StudentServiceInterface studentService;
	private SemesterServiceInterface semesterService;
	
	public CourseController() {
		super();
		courseService = new CourseService();
		studentService = new StudentService();
		semesterService = new SemesterService();
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter("option");
		String courseName = request.getParameter("courseName");
		String courseCode = request.getParameter("courseCode");
		String[] availableSemesters = request.getParameterValues("activeSemesters");
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		Course course = new Course();
		course.setCourseName(courseName);
		course.setCourseCode(courseCode);
		course.setLastModified(date);
		
		
		if(option.equalsIgnoreCase("save")) {
			courseService.saveCourse(course);
		}else if(option.equalsIgnoreCase("update")) {
			course.setCourseId(UUID.fromString(request.getParameter("courseId")));
			courseService.updateCourse(course);
		}
		
		request.setAttribute("allCourses", courseService.findAllCourses());
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			UUID courseId = UUID.fromString(request.getParameter("courseId"));
			courseService.deleteCourseById(courseId);
			request.setAttribute("allCourses", courseService.findAllCourses());
			getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			UUID courseId = UUID.fromString(request.getParameter("courseId"));
			request.setAttribute("course", courseService.findCourseById(courseId));
			getServletContext().getRequestDispatcher("/jsp/modify-pages/course-modified-page.jsp").forward(request, response);
		}else if(action.equalsIgnoreCase("assign")) {
			UUID courseId = UUID.fromString(request.getParameter("courseId"));
			request.setAttribute("course", courseService.findCourseById(courseId));
			request.setAttribute("allStudents", studentService.findAllStudents());
			request.setAttribute("allSemesters", semesterService.findAllSemesters());
			getServletContext().getRequestDispatcher("/jsp/assignment-pages/course-assign-page.jsp").forward(request, response);
		}else {
			request.setAttribute("allCourses", courseService.findAllCourses());
			request.setAttribute("allSemesters", semesterService.findAllSemesters());
			getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
		}
	}
}
