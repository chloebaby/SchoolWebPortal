package service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.CourseDAO;
import dao.SchoolDAO;
import daoimplementation.DAOCourseImplementation;
import daoimplementation.DAOStudentImplementation;
import model.Course;

@Service
public class CourseService implements CourseServiceInterface{
	private CourseDAO daoCourseImplementation;
	
	public CourseService() {}
	
	public CourseService(CourseDAO daoCourseImplementation) {
		this.daoCourseImplementation = daoCourseImplementation;
	}
	
	@Override
	@Transactional
	public List<Course> findAllCourses(){
		return daoCourseImplementation.selectAllCourses();
	}
	
	@Override
	@Transactional
	public void saveCourse(Course course){
		daoCourseImplementation.insertCourse(course);
	}
	
	@Override
	@Transactional
	public void deleteCourseById(UUID courseId) {
		Course course = daoCourseImplementation.selectCourseById(courseId);
		daoCourseImplementation.deleteCourse(course);
	}
	
	@Override
	@Transactional
	public Course findCourseById(UUID courseId) {
		return daoCourseImplementation.selectCourseById(courseId);
	}
	
	@Override
	@Transactional
	public void updateCourse(Course course) {
		daoCourseImplementation.updateCourse(course);
	}

	public CourseDAO getDaoCourseImplementation() {
		return daoCourseImplementation;
	}

	public void setDaoCourseImplementation(CourseDAO daoCourseImplementation) {
		this.daoCourseImplementation = daoCourseImplementation;
	}

}
