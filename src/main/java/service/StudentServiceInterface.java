package service;

import java.util.List;
import java.util.UUID;

import model.Student;

public interface StudentServiceInterface {
	public List<Student> findAllStudents();
	public void saveStudent(Student student);
	//public void deleteStudentById(int studentId, int userId, String username);
	public Student findStudentById(UUID studentId);
	public void updateStudent(Student student);
}
