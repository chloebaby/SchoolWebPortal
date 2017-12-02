package service;

import java.util.List;
import java.util.UUID;

import dao.CourseDAO;
import dao.SchoolDAO;
import daoimplementation.DAOCourseImplementation;
import daoimplementation.DAOStudentImplementation;
import model.Course;

public class CourseService implements CourseServiceInterface{
	private CourseDAO daoCourseImplementation;
	
	public CourseService() {
		daoCourseImplementation = new DAOCourseImplementation();
	}
	
	public List<Course> findAllCourses(){
		return daoCourseImplementation.selectAllCourses();
	}
	
	public void saveCourse(Course course){
		daoCourseImplementation.insertCourse(course);
	}
	
	public void deleteCourseById(UUID courseId) {
		Course course = daoCourseImplementation.selectCourseById(courseId);
		daoCourseImplementation.deleteCourse(course);
	}
	
	public Course findCourseById(UUID courseId) {
		return daoCourseImplementation.selectCourseById(courseId);
	}
	
	public void updateCourse(Course course) {
		daoCourseImplementation.updateCourse(course);
	}

}
