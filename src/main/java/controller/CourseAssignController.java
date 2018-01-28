package controller;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

@Controller
public class CourseAssignController{
	private CourseServiceInterface courseService;
	private SemesterServiceInterface semesterService;
	private StudentServiceInterface studentService;
	private UserServiceInterface userService;
	private ResultServiceInterface resultService;
	private ClassPathXmlApplicationContext ctx;
	
	public CourseAssignController() {
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		courseService = (CourseServiceInterface)ctx.getBean(Constants.SPRING_BEAN_COURSESERVICE);
		semesterService = (SemesterServiceInterface)ctx.getBean(Constants.SPRING_BEAN_SEMESTERSERVICE);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		userService = (UserServiceInterface)ctx.getBean(Constants.SPRING_BEAN_USERSERVICE);
		resultService = (ResultServiceInterface)ctx.getBean(Constants.SPRING_BEAN_RESULTSERVICE);
	}
	
	@RequestMapping(value="/course/assign", method=RequestMethod.POST)
	public ModelAndView makeCourseAssignment(@ModelAttribute("course") Course course, @RequestParam(Constants.REQUEST_PAREMETER_ACTIVESTUDENTS) String[] selectedStudents, @RequestParam(Constants.REQUEST_PARAMETER_SEMESTER) String selectedSemester, @RequestParam(Constants.REQUEST_PARAMETER_OPTION) String optionSubmit) {
		ModelAndView model = null;
		String option = optionSubmit;
		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_SAVE)) {
			model = makeAssignment(course, selectedStudents, selectedSemester);
		}else {
			model = getCoursesModel();
		}
		
		return model;
	}
	
	@RequestMapping(value="/course/assign/delete/{studentId}/{courseId}/{semesterId}", method=RequestMethod.GET)
	public ModelAndView deleteCourseAssignment(@PathVariable(Constants.REQUEST_PATH_VARIABLE_STUDENTID) String stdId, @PathVariable(Constants.REQUEST_PATH_VARIABLE_COURSEID) String cId, @PathVariable(Constants.REQUEST_PATH_VARIABLE_SEMESTERID) String sId) {
		UUID studentId = UUID.fromString(stdId);
		UUID courseId = UUID.fromString(cId);
		UUID semesterId = UUID.fromString(sId);
		
		Student student = studentService.findStudentById(studentId);
		Course course = courseService.findCourseById(courseId);
		Semester semester = semesterService.findSemesterByUUID(semesterId);
		
		removeStudentFromSemesterAssociation(student, semester);
		removeStudentFromCourseAssociation(student, course);
		
		return getCourseAssignmentModel(courseId);
	}
	
	private ModelAndView makeAssignment(Course course, String[] selectedStudents, String selectedSemester) {
		String[] usernames = new String[selectedStudents.length];
		UUID courseId = course.getCourseId();
		course = courseService.findCourseById(courseId);
		Set<Semester> listOfSemesters = course.getListOfSemesters();
		java.sql.Date date = generateLastModifiedDate();
		
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
				if(sem.getSemester().equals(selectedSemester)) {
					Semester semester = semesterService.findSemesterByUUID(sem.getSemesterId());
					Set<Result> semesterResults = semester.getListOfResults();
					List<Student> semesterStudent = semester.getListOfStudents();
					semesterResults.add(result);
					semesterStudent.add(student);
					semester.setListOfResults(semesterResults);
					semester.setListOfStudents(semesterStudent);
					semesterService.updateSemester(semester);
				}
			}
		}
		
		return getCourseAssignmentModel(courseId);
	}
	
	private ModelAndView getCourseAssignmentModel(UUID courseId) {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_COURSEASSIGNPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		return model;
		
	}
	
	private ModelAndView getCoursesModel() {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_COURSEPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, semesterService.findAllSemesters());
		return model;
	}
	
	private void removeStudentFromSemesterAssociation(Student student, Semester semester) {
		List<Student> semesterStudent = semester.getListOfStudents();
		Collection<Student> removeSemesterStudent = new LinkedList<Student>(semesterStudent);
		
		Iterator<Student> removeSemesterStudentIterator = removeSemesterStudent.iterator();
		
		while(removeSemesterStudentIterator.hasNext()) {
			Student stu = removeSemesterStudentIterator.next();
			if(stu.getStudentId().toString().equals(student.getStudentId().toString())) {
				removeSemesterStudentIterator.remove();
			}
		}
		
		semester.setListOfStudents(new LinkedList<Student>(removeSemesterStudent));
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
	
	private java.sql.Date generateLastModifiedDate(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
}
