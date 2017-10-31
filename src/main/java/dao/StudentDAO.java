package dao;

import model.Student;

public interface StudentDAO {
	public void insertStudent(Student student);
	public void deleteStudent(int studentId);
	public void updateStudent(Student student);
}
