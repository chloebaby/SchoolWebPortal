package controller;

import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import model.Course;
import model.Result;
import model.Semester;
import model.Student;
import model.User;
import service.CourseServiceInterface;
import service.ResultServiceInterface;
import service.SemesterServiceInterface;
import service.StudentServiceInterface;
import service.UserServiceInterface;
import util.Constants;

public class CourseAssignController extends HttpServlet{
	private CourseServiceInterface courseService;
	private SemesterServiceInterface semesterService;
	private StudentServiceInterface studentService;
	private UserServiceInterface userService;
	private ResultServiceInterface resultService;
	private ClassPathXmlApplicationContext ctx;
	
	public CourseAssignController() {
		super();
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		courseService = (CourseServiceInterface)ctx.getBean(Constants.SPRING_BEAN_COURSESERVICE);
		semesterService = (SemesterServiceInterface)ctx.getBean(Constants.SPRING_BEAN_SEMESTERSERVICE);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		userService = (UserServiceInterface)ctx.getBean(Constants.SPRING_BEAN_USERSERVICE);
		resultService = (ResultServiceInterface)ctx.getBean(Constants.SPRING_BEAN_RESULTSERVICE);
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
		String action = request.getParameter(Constants.REQUEST_PARAMETER_ACTION);
		
		if(action.equalsIgnoreCase(Constants.REQUEST_ACTION_DELETE)) {
			doDel(request, response);
		}
	}
	
	private void makeAssignment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID courseId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID));
		String[] selectedStudents = request.getParameterValues(Constants.REQUEST_PAREMETER_ACTIVESTUDENTS);
		String semesterName = request.getParameter(Constants.REQUEST_PARAMETER_SEMESTER);
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		String[] usernames = new String[selectedStudents.length];
		Course course = courseService.findCourseById(courseId);
		List<Semester> listOfSemesters = course.getListOfSemesters();
		
		for(int i = 0; i < selectedStudents.length; i++) {
			Result result = new Result();
			result.setLastModified(date);
			usernames[i] = selectedStudents[i].substring(0, selectedStudents[i].indexOf(Constants.MISC_VALUE_OPENROUNDBRACKET));
			usernames[i].trim();
			
			User user = userService.findUserByUsername(usernames[i]);
			Student student = user.getStudent();
			Set<Result> studentResult = student.getListOfResults();
			studentResult.add(result);
			student.setListOfResults(studentResult);
			student.setLastModified(date);
			result.setStudent(student);
			resultService.saveResult(result);
			
			
			Set<Result> courseResult = course.getListOfResults();
			courseResult.add(result);
			course.setListOfResults(courseResult);
			Set<Student> courseStudent = course.getListOfStudents();
			courseStudent.add(student);
			course.setListOfStudents(courseStudent);
			course.setLastModified(date);
			courseService.updateCourse(course);
			
			result.setCourse(course);
			resultService.updateResult(result);
			
			for(Semester sem : listOfSemesters) {
				if(sem.getSemester().equals(semesterName)) {
					Semester semester = semesterService.findSemesterByUUID(sem.getSemesterId());
					Set<Result> semesterResults = semester.getListOfResults();
					Set<Student> semesterStudent = semester.getListOfStudents();
					Set<Semester> studentSemester = student.getListOfSemesters();
					semesterResults.add(result);
					semesterStudent.add(student);
					studentSemester.add(semester);
					semester.setListOfResults(semesterResults);
					semester.setListOfStudents(semesterStudent);
					student.setListOfSemesters(studentSemester);
					semesterService.updateSemester(semester);
				}
			}
		}
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEASSIGNPAGE).forward(request, response);
		
	}
	
	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		UUID studentId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_STUDENTID));
		UUID courseId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_COURSEID));
		UUID semesterId = UUID.fromString(request.getParameter(Constants.REQUEST_PARAMETER_SEMETERID));
		
		Student student = studentService.findStudentById(studentId);
		Course course = courseService.findCourseById(courseId);
		Semester semester = semesterService.findSemesterByUUID(semesterId);
		
		removeStudentFromSemesterAssociation(student, semester);
		removeStudentFromCourseAssociation(student, course);
		
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		request.setAttribute(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		getServletContext().getRequestDispatcher(Constants.REQUEST_DISPATCHER_COURSEASSIGNPAGE).forward(request, response);
	}
	
	private void removeStudentFromSemesterAssociation(Student student, Semester semester) {
		Set<Student> semesterStudent = semester.getListOfStudents();
		Collection<Student> removeSemesterStudent = new LinkedList<Student>(semesterStudent);
		
		Iterator<Student> removeSemesterStudentIterator = removeSemesterStudent.iterator();
		
		while(removeSemesterStudentIterator.hasNext()) {
			Student stu = removeSemesterStudentIterator.next();
			if(stu.getStudentId().toString().equals(student.getStudentId().toString())) {
				removeSemesterStudentIterator.remove();
			}
		}
		
		semester.setListOfStudents(new HashSet<Student>(removeSemesterStudent));
		semesterService.updateSemester(semester);
	}
	
	private void removeStudentFromCourseAssociation(Student student, Course course) {
		Set<Student> courseStudent = course.getListOfStudents();
		Collection<Student> removeCourseStudent = new LinkedList<Student>(courseStudent);
		
		Iterator<Student> removeCourseStudentIterator = removeCourseStudent.iterator();
		
		while(removeCourseStudentIterator.hasNext()) {
			Student stu = removeCourseStudentIterator.next();
			if(stu.getStudentId().toString().equals(student.getStudentId().toString())){
				removeCourseStudentIterator.remove();
			}
		}
		
		course.setListOfStudents(new HashSet<Student>(removeCourseStudent));
		courseService.updateCourse(course);
	}
	
}
