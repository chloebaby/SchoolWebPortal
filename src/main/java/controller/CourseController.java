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
import model.Semester;

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
		Course course = null;
		
		if(!option.equalsIgnoreCase("cancel")) {
			course = buildCourse(request);
		}

		if(option.equalsIgnoreCase("save")) {
			courseService.saveCourse(course);
		}else if(option.equalsIgnoreCase("update")) {
			course.setCourseId(UUID.fromString(request.getParameter("courseId")));
			courseService.updateCourse(course);
		}
		
		request.setAttribute("allCourses", courseService.findAllCourses());
		request.setAttribute("allSemesters", semesterService.findAllSemesters());
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("delete")) {
			doDel(request, response);
		}else if(action.equalsIgnoreCase("edit")) {
			doEdit(request, response);
		}else if(action.equalsIgnoreCase("assign")) {
			doAssign(request, response);
		}else {
			List<Course> allCourses = courseService.findAllCourses();
			List<Semester> allSemester = semesterService.findAllSemesters();
			request.setAttribute("allCourses", allCourses);
			request.setAttribute("allSemesters", allSemester);
			getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
		}
	}
	
	private Course buildCourse(HttpServletRequest request) throws ServletException {
		String courseName = request.getParameter("courseName");
		String courseCode = request.getParameter("courseCode");
		String[] availableSemesters = request.getParameterValues("activeSemesters");
		List<Semester> listOfSemesters = new ArrayList<Semester>();
		
		for(String semesterName : availableSemesters) {
			Semester semester = new Semester();
			UUID semesterId = semesterService.findUUIDBySemester(semesterName);
			semester.setSemester(semesterName);
			semester.setSemesterId(semesterId);
			listOfSemesters.add(semester);
		}
		
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		Course course = new Course();
		course.setCourseName(courseName);
		course.setCourseCode(courseCode);
		course.setLastModified(date);
		course.setListOfSemesters(listOfSemesters);
		
		return course;
		
	}
	
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter("courseId"));
		courseService.deleteCourseById(courseId);
		request.setAttribute("allCourses", courseService.findAllCourses());
		request.setAttribute("allSemesters", semesterService.findAllSemesters());
		getServletContext().getRequestDispatcher("/jsp/course-page.jsp").forward(request, response);
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter("courseId"));
		Course course = courseService.findCourseById(courseId);
		List<Semester> semesters = semesterService.findAllSemesters();
		
		List<Semester> courseAssignedSemesters = course.getListOfSemesters();
		
		outerForLoop:
		for(int i = 0; i < semesters.size();) {
			boolean isItemRemoved = false;
			for(int j = 0; j < courseAssignedSemesters.size(); j++) {
				if(semesters.size() == 0) {
					break outerForLoop;
				}else if(semesters.get(i).getSemester().equals(courseAssignedSemesters.get(j).getSemester())){
					semesters.remove(i);
					isItemRemoved = true;
				}
			}
			
			if(!isItemRemoved) {
				i++;
			}
		}
		
		request.setAttribute("course", courseService.findCourseById(courseId));
		request.setAttribute("allSemesters", semesters);
		getServletContext().getRequestDispatcher("/jsp/modify-pages/course-modified-page.jsp").forward(request, response);
	}
	
	private void doAssign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter("courseId"));
		request.setAttribute("course", courseService.findCourseById(courseId));
		request.setAttribute("allStudents", studentService.findAllStudents());
		getServletContext().getRequestDispatcher("/jsp/assignment-pages/course-assign-page.jsp").forward(request, response);
	}
}
