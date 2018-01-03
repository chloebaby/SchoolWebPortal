package service;

import junit.framework.TestCase;
import model.Course;

public class TestCourseService extends TestCase {
	private java.util.Date today;
	private java.sql.Date date;
	private CourseServiceInterface courseService;
	private Course course;
	private String courseCode = "JAVA01";
	private String courseName = "Java Programming";
	
	public TestCourseService(String name) {
		super(name);
	}
	
	
	protected void setUp() throws Exception {
		super.setUp();
		today = new java.util.Date();
		date = new java.sql.Date(today.getTime());
		
		courseService = new CourseService();
		course = new Course();
		
		course.setCourseCode(courseCode);
		course.setCourseName(courseName);
		course.setLastModified(date);
		
		courseService.saveCourse(course);
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		courseService.deleteCourseById(course.getCourseId());
	}
	
	public void testFindAllCourses() {
		assertNotNull(courseService.findAllCourses());
	}
	
	public void testFindCourseById() {
		assertNotNull(courseService.findCourseById(course.getCourseId()));
	}
	
	public void testUpdateCourse() {
		String updatedCourseName = "Jaba";
		String actualCourseName = courseName;
		String expectedCourseName = updatedCourseName;
		
		course.setCourseName(expectedCourseName);
		
		courseService.updateCourse(course);
		
		Course updatedCourse = courseService.findCourseById(course.getCourseId());
		
		assertEquals(expectedCourseName, updatedCourse.getCourseName());
	}

}
