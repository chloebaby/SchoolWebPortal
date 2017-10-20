package daoimplementation;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.SchoolDAO;
import dao.StudentDAO;
import sqlconnection.SQLConnection;
import model.Student;

public class DAOStudentImplementation extends SQLConnection implements SchoolDAO<Student>, StudentDAO {

	public DAOStudentImplementation() {
		super();
	}
	
	public List<Student> select(){
		List<Student> allStudents = new ArrayList<Student>();
		
		String selectAllStudents = "select * from person";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectAllStudents);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
				student.setLastModified(rs.getDate("last_modified"));
				allStudents.add(student);
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		return allStudents;
	}
	
	public List<Student> selectById(Object id){
		List<Student> studentById = new ArrayList<Student>();
		
		String selectStudentsById = "select * from students where student_id = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectStudentsById);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
				student.setLastModified(rs.getDate("last_modified"));
				studentById.add(student);
			}
			
			rs.close();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return studentById;
	}
	
	public void insertStudent(String firstName, String lastName, String email) {
		String insertStudent = "insert into Students(student_id, first_name, last_name, email, last_modified) values (?, ?, ?, ?, ?)";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertStudent);

			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, firstName);
			ps.setString(3, lastName);
			ps.setString(4, email);
			ps.setDate(5, date);
			
			ps.executeQuery();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void deleteStudent(String email) {
		String deleteStudent = "delete from Students where email = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(deleteStudent);
			
			ps.setString(1, email);
			
			ps.executeQuery();
			
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
}
