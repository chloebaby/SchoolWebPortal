package dao;

import model.Course;

public interface CourseDAO {
	public void insertCourse(Course course);
	public void deleteCourse(int courseId);
}
