package service;

import java.util.List;
import java.util.UUID;

import model.Course;

public interface CourseServiceInterface {
	public List<Course> findAllCourses();
	public void saveCourse(Course course);
	public void deleteCourseById(UUID course);
	public Course findCourseById(UUID courseId);
	public void updateCourse(Course course);
}
