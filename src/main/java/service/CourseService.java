package service;

import java.util.List;

import dao.CourseDAO;
import dao.SchoolDAO;
import daoimplementation.DAOCourseImplementation;
import daoimplementation.DAOStudentImplementation;
import model.Course;

public class CourseService implements CourseServiceInterface<Course>{
	private SchoolDAO daoSchoolImplementation;
	private CourseDAO daoCourseImplementation;
	
	public CourseService() {
		daoSchoolImplementation = new DAOCourseImplementation();
		daoCourseImplementation = new DAOCourseImplementation();
	}
	
	public List<Course> findAllCourses(){
		return daoSchoolImplementation.select();
	}
	
	public List<Course> saveCourse(Course course){
		daoCourseImplementation.insertCourse(course);
		return findAllCourses();
	}
	
	public void deleteCourseById(int courseId) {
		daoCourseImplementation.deleteCourse(courseId);
	}

}
