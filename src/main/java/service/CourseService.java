package service;

import java.util.List;

import dao.CourseDAO;
import dao.SchoolDAO;
import daoimplementation.DAOCourseImplementation;
import daoimplementation.DAOStudentImplementation;
import model.Course;

public class CourseService implements CourseServiceInterface<Course>{
	private SchoolDAO<Course> daoSchoolImplementation;
	private CourseDAO daoCourseImplementation;
	
	public CourseService() {
		daoSchoolImplementation = new DAOCourseImplementation();
		daoCourseImplementation = new DAOCourseImplementation();
	}
	
	public List<Course> findAllCourses(){
		return daoSchoolImplementation.select();
	}
	
	public void saveCourse(Course course){
		daoCourseImplementation.insertCourse(course);
	}
	
	public void deleteCourseById(int courseId) {
		daoCourseImplementation.deleteCourse(courseId);
	}
	
	public Course findCourseById(int courseId) {
		return daoSchoolImplementation.selectById(courseId);
	}
	
	public void updateCourse(Course course) {
		daoCourseImplementation.updateCourse(course);
	}

}
