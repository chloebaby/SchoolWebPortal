package controller;

import java.util.ArrayList;
import java.util.HashSet;
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

import service.CourseServiceInterface;
import service.SemesterServiceInterface;
import service.StudentServiceInterface;
import util.Constants;
import model.Course;
import model.Semester;

@Controller
public class CourseController{
	private CourseServiceInterface courseService;
	private StudentServiceInterface studentService;
	private SemesterServiceInterface semesterService;
	private ClassPathXmlApplicationContext ctx;
	
	public CourseController() {
		ctx = new ClassPathXmlApplicationContext(Constants.SPRING_BEAN_CONTEXT);
		courseService = (CourseServiceInterface)ctx.getBean(Constants.SPRING_BEAN_COURSESERVICE);
		studentService = (StudentServiceInterface)ctx.getBean(Constants.SPRING_BEAN_STUDENTSERVICE);
		semesterService = (SemesterServiceInterface)ctx.getBean(Constants.SPRING_BEAN_SEMESTERSERVICE);
	}
	
	@RequestMapping(value="/course", method=RequestMethod.GET)
	public ModelAndView getCoursePage() {
		return getCoursesModel();
	}
	
	@RequestMapping(value="/course/create", method=RequestMethod.POST)
	public ModelAndView createCourse(@ModelAttribute(Constants.REQUEST_MODEL_ATTRIBUTE_COURSE) Course course, @RequestParam(Constants.REQUEST_PARAMETER_ACTIVESEMESTERS) String[] activeSemesters) {
		course = buildCourse(course, activeSemesters);
		courseService.saveCourse(course);
		
		return getCoursesModel();
	}
	
	@RequestMapping(value="/course/edit/{courseId}", method=RequestMethod.GET)
	public ModelAndView getCourseUpdatePage(@PathVariable(Constants.REQUEST_PATH_VARIABLE_COURSEID) String id) {
		UUID courseId = UUID.fromString(id);
		return getCourseUpdateModel(courseId);
	}
	
	@RequestMapping(value="/course/update", method=RequestMethod.POST)
	public ModelAndView updateCourse(@ModelAttribute(Constants.REQUEST_MODEL_ATTRIBUTE_COURSE) Course course, @RequestParam(Constants.REQUEST_PARAMETER_ACTIVESEMESTERS) String[] activeSemesters, @RequestParam(Constants.REQUEST_PARAMETER_OPTION) String optionSubmit) {
		String option = optionSubmit;
		if(option.equalsIgnoreCase(Constants.REQUEST_ACTION_UPDATE)){
			course = buildCourse(course, activeSemesters);
			courseService.updateCourse(course);
		}
		
		return getCoursesModel();
	}
	
	@RequestMapping(value="/course/delete/{courseId}", method=RequestMethod.GET)
	public ModelAndView deleteCourse(@PathVariable(Constants.REQUEST_PATH_VARIABLE_COURSEID) String id) {
		UUID courseId = UUID.fromString(id);
		courseService.deleteCourseById(courseId);
		return getCoursesModel();
	}
	
	@RequestMapping(value="/course/assign/{courseId}", method=RequestMethod.GET)
	public ModelAndView getAssignCoursePage(@PathVariable(Constants.REQUEST_PATH_VARIABLE_COURSEID) String id){
		UUID courseId = UUID.fromString(id);
		
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_COURSEASSIGNPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSTUDENTS, studentService.findAllStudents());
		
		return model;
	}
	
	private Course buildCourse(Course course, String[] activeSemesters) {
		Set<Semester> listOfSemesters = new HashSet<Semester>();
		
		for(String semesterName : activeSemesters) {
			Semester semester = new Semester();
			UUID semesterId = semesterService.findUUIDBySemester(semesterName);
			semester.setSemesterId(semesterId);
			semester.setSemester(semesterName);
			listOfSemesters.add(semester);
		}
		course.setLastModified(generateLastModifiedDate());
		course.setListOfSemesters(listOfSemesters);
		
		return course;
	}
	
	private ModelAndView getCourseUpdateModel(UUID courseId) {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_COURSEMODIFIYPAGE);
		
		Course course = courseService.findCourseById(courseId);
		
		Set<Semester> courseSemester = course.getListOfSemesters();
		Set<Semester> semesters = semesterService.findAllSemesters();
		
		List<Semester> listCourseSemester = new LinkedList<Semester>(courseSemester);
		List<Semester> listSemesters = new LinkedList<Semester>(semesters);
		
		try {
		outerForLoop:
		for(int i = 0; i < listSemesters.size();) {
			boolean isItemRemoved = false;
			for(int j = 0; j < listCourseSemester.size(); j++) {
				if(semesters.size() == 0) {
					break outerForLoop;
				}else if(listSemesters.get(i).getSemester().equals(listCourseSemester.get(j).getSemester())){
					listSemesters.remove(i);
					isItemRemoved = true;
				}
			}
			
			if(!isItemRemoved) {
				i++;
			}
		}
		}catch(Exception e) {
			return getCoursesModel();
		}
		
		model.addObject(Constants.REQUEST_ATTRIBUTE_COURSE, courseService.findCourseById(courseId));
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, listSemesters);
		
		return model;
	}
	
	private ModelAndView getCoursesModel() {
		ModelAndView model = new ModelAndView(Constants.REQUEST_DISPATCHER_COURSEPAGE);
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLCOURSES, courseService.findAllCourses());
		model.addObject(Constants.REQUEST_ATTRIBUTE_ALLSEMESTERS, semesterService.findAllSemesters());
		return model;
	}
	
	private java.sql.Date generateLastModifiedDate(){
		java.util.Date today = new java.util.Date();
		return new java.sql.Date(today.getTime());
	}
}
