package service;

import java.util.List;

public interface CourseServiceInterface<T> {
	public List<T> findAllCourses();
	public void saveCourse(T entity);
	public void deleteCourseById(int courseId);
	public T findCourseById(int courseId);
	public void updateCourse(T entity);
}
