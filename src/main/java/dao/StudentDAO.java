package dao;

import java.util.List;

import model.Student;

public interface StudentDAO extends HibernateDAO {
	//public void insertStudent(Student student);
	//public void deleteStudent(int studentId);
	//public void updateStudent(Student student);
	public List<Student> select();
	//public Student selectById(int id);
}
