package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Course;
import model.Result;
import model.Semester;
import model.Student;
import model.User;
import service.CourseService;
import service.CourseServiceInterface;
import service.SemesterService;
import service.SemesterServiceInterface;
import service.StudentService;
import service.StudentServiceInterface;
import service.UserService;
import service.UserServiceInterface;
import util.Constants;

public class CourseAssignController extends HttpServlet{
	private CourseServiceInterface courseService;
	private SemesterServiceInterface semesterService;
	private StudentServiceInterface studentService;
	private UserServiceInterface userService;
	
	public CourseAssignController() {
		super();
		courseService = new CourseService();
		semesterService = new SemesterService();
		studentService = new StudentService();
		userService = new UserService();
	}
	
	@Override 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter(Constants.REQUEST_PARAMETER_OPTION);
		
		if(option.equals(Constants.REQUEST_ACTION_SAVE)) {
			makeAssignment(request, response);
		}else{
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, semesterService.findAllSemesters());
			getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEPAGE).forward(request, response);
		}
	}
	
	@Override 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
	private void makeAssignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID));
		String courseName = request.getParameter(Constants.REQUEST_PARAMETER_COURSENAME);
		String[] selectedStudents = request.getParameterValues(Constants.REQUEST_PAREMETER_ACTIVESTUDENTS);
		String semesterName = request.getParameter(Constants.REQUEST_PARAMETER_SEMESTER);
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		List<Result> results = new ArrayList<Result>();
		String[] usernames = new String[selectedStudents.length];
		Course course = courseService.findCourseById(courseId);
		List<Semester> listOfSemesters = course.getListOfSemesters();
		
		for(int i = 0; i < selectedStudents.length; i++) {
			Result result = new Result();
			//result.setMarks(Integer.valueOf(null));
			result.setLastModified(date);
			results.add(result);
			usernames[i] = selectedStudents[i].substring(0, selectedStudents[i].indexOf(Constants.MISC_VALUE_OPENROUNDBRACKET));
			usernames[i].trim();
			
			User user = userService.findUserByUsername(usernames[i]);
			Student student = user.getStudent();
			List<Result> studentResult = student.getListOfResults();
			studentResult.add(result);
			student.setListOfResults(studentResult);
			studentService.updateStudent(student);
			
			
			List<Result> courseResult = course.getListOfResults();
			courseResult.add(result);
			course.setListOfResults(courseResult);
			courseService.updateCourse(course);
			
			for(Semester sem : listOfSemesters) {
				if(sem.getSemester().equals(semesterName)) {
					Semester semester = semesterService.findSemesterByUUID(sem.getSemesterId());
					List<Result> semesterResults = semester.getListOfResults();
					semesterResults.add(result);
					semester.setListOfResults(semesterResults);
					semesterService.updateSemester(semester);
				}
			}
			
			
		}
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEASSIGNPAGE).forward(request, response);
		
	}
}
