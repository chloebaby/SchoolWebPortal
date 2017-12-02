package dao;

import java.util.List;
import java.util.UUID;

import model.Course;

public interface CourseDAO {
	public void insertCourse(Course course);
	public void deleteCourse(Course course);
	public void updateCourse(Course course);
	public List<Course> selectAllCourses();
	public Course selectCourseById(UUID courseId);
}
