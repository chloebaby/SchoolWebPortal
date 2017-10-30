package service;

import java.util.List;

public interface StudentServiceInterface<T> {
	public List<T> findAllStudents();
	public List<T> saveStudent(T entity);
	public void deleteStudentById(int studentId);

}
