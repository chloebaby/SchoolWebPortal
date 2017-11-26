package dao;

import java.util.List;
import java.util.UUID;

import model.Student;

public interface StudentDAO {
	public void insertStudent(Student student);
	public void deleteStudent(Student student);
	public void updateStudent(Student student);
	public List<Student> selectAllStudents();
	public Student selectStudentById(UUID studentId);
}
