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
		
		String selectAllStudents = "select * from Students";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectAllStudents);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Student student = new Student();
				student.setStudentId(rs.getInt("student_id"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
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
		
		String selectStudentsById = "select * from Students where student_id = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectStudentsById);
			
			ps.setInt(1, (int)id);
			
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
	
	public void insertStudent(Student student) {
		String insertStudent = "insert into Students(student_id, first_name, last_name, email, last_modified) values (?, ?, ?, ?, ?)";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertStudent);

			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, student.getFirstName());
			ps.setString(3, student.getLastName());
			ps.setString(4, student.getEmail());
			ps.setDate(5, date);
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void deleteStudent(int studentId) {
		String deleteStudent = "delete from Students where student_id = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(deleteStudent);
			
			ps.setInt(1, studentId);
			
			ps.executeUpdate();
			
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
}
