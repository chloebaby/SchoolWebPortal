package service;

import java.util.List;

public interface CourseServiceInterface<T> {
	public List<T> findAllCourses();
	public List<T> saveCourse(T entity);
}
