package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import service.CourseServiceInterface;
import service.SemesterServiceInterface;
import service.StudentServiceInterface;
import util.Constants;
import model.Course;
import model.Semester;

@SuppressWarnings("serial")
public class CourseController extends HttpServlet{
	private CourseServiceInterface courseService;
	private StudentServiceInterface studentService;
	private SemesterServiceInterface semesterService;
	private ClassPathXmlApplicationContext ctx;
	
	public CourseController() {
		super();
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		courseService = (CourseServiceInterface)ctx.getBean(Constants.SPRING_BEAN_COURSESERVICE);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		semesterService = (SemesterServiceInterface)ctx.getBean(Constants.SPRING_BEAN_SEMESTERSERVICE);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String option = request.getParameter(Constants.REQUEST_PARAMETER_OPTION);
		Course course = null;
		
		if(!option.equalsIgnoreCase(Constants.REQUEST_ACTION_CANCEL)) {
			course = buildCourse(request);
		}

		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_SAVE)) {
			courseService.saveCourse(course);
		}else if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_UPDATE)) {
			course.setCourseId(UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID)));
			courseService.updateCourse(course);
		}
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, semesterService.findAllSemesters());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEPAGE).forward(request, response);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter(Constants.REQUEST_PARAMETER_ACTION);
		
		if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_DELETE)) {
			doDel(request, response);
		}else if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_EDIT)) {
			doEdit(request, response);
		}else if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_ASSIGN)) {
			doAssign(request, response);
		}else {
			List<Course> allCourses = courseService.findAllCourses();
			List<Semester> allSemester = semesterService.findAllSemesters();
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, allCourses);
			request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, allSemester);
			getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEPAGE).forward(request, response);
		}
	}
	
	private Course buildCourse(HttpServletRequest request) throws ServletException {
		String courseName = request.getParameter(Constants.REQUEST_PARAMETER_COURSENAME);
		String courseCode = request.getParameter(Constants.REQUEST_PARAMETER_COURSECODE);
		String[] availableSemesters = request.getParameterValues(Constants.REQUEST_PARAMETER_ACTIVESEMESTERS);
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
		UUID courseId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID));
		courseService.deleteCourseById(courseId);
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, semesterService.findAllSemesters());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEPAGE).forward(request, response);
	}
	
	private void doEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID));
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
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, semesters);
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEMODIFIYPAGE).forward(request, response);
	}
	
	private void doAssign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEASSIGNPAGE).forward(request, response);
	}
}
