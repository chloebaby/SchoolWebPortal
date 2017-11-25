package service;

import java.util.List;

import model.Student;

public interface StudentServiceInterface {
	public List<Student> findAllStudents();
	public void saveStudent(Student student);
	//public void deleteStudentById(int studentId, int userId, String username);
	public Student findStudentById(int studentId);
	//public void updateStudent(T entity1, U entity2, V entity3);
}
