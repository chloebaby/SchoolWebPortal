package dao;

public interface StudentDAO {
	public void insertStudent(String firstName, String lastName, String email);
	public void deleteStudent(String email);
}
