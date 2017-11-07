package service;

import java.util.List;

public interface StudentServiceInterface<T, U, V> {
	public List<T> findAllStudents();
	public void saveStudent(T entity1, U entity2, V entity3);
	public void deleteStudentById(int studentId);
	public T findStudentById(int studentId);
	public void updateStudent(T entity);
}
