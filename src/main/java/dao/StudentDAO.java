package dao;

import java.util.List;

import model.Student;

public interface StudentDAO {
	public void insertStudent(Student student);
	//public void deleteStudent(int studentId);
	//public void updateStudent(Student student);
	public List<Student> selectAllStudents();
	public Student selectStudentById(UUID studentId);
}
