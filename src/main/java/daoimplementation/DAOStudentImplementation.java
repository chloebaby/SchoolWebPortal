package daoimplementation;

import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;

import dao.StudentDAO;
import sqlconnection.SQLConnection;
import model.Student;

public class DAOStudentImplementation extends SQLConnection implements StudentDAO {
	
	public DAOStudentImplementation() {
		super();
	}
	
	@Override
	public List<Student> selectAllStudents(){
		openCurrentSession();
		Query query = getCurrentSession().createQuery("FROM Student");
		List<Student> allStudents = query.list();
		closeCurrentSession();
		
		return allStudents;
	}
	
	@Override
	public void insertStudent(Student student){
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().save(student);
		commitTransaction();
		closeCurrentSession();
	}
	
	@Override
	public Student selectStudentById(UUID studentId) {
		openCurrentSession();
		Student student = getCurrentSession().get(Student.class, studentId);
		closeCurrentSession();
		return student;
	}
	
	@Override
	public void updateStudent(Student student) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().update(student);
		commitTransaction();
		closeCurrentSession();
	}
	
/*	
	public Student selectById(int id){
		Student student = new Student();
		
		String selectStudentsById = "select * from studentview where student_id = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectStudentsById);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				student.setStudentId(rs.getInt("student_id"));
				student.setUsername(rs.getString("username"));
				student.setUserId(rs.getInt("user_id"));
				student.setRoleId(rs.getInt("role_id"));
				student.setPassword(rs.getString("password"));
				student.setRolename(rs.getString("rolename"));
				student.setFirstName(rs.getString("first_name"));
				student.setLastName(rs.getString("last_name"));
				student.setEmail(rs.getString("email"));
			}
			
			rs.close();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return student;
	}
	
	public void insertStudent(Student student) {
		String insertStudent = "insert into Students(student_id, user_id, role_id, first_name, last_name, email, last_modified) values (?, ?, ?, ?, ?, ?, ?)";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertStudent);

			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setInt(2, student.getUserId());
			ps.setInt(3, student.getRoleId());
			ps.setString(4, student.getFirstName());
			ps.setString(5, student.getLastName());
			ps.setString(6, student.getEmail());
			ps.setDate(7, date);
			
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
	
	public void updateStudent(Student student) {
		String updatePerson = "update Students set first_name = ?, last_name = ?, email = ?, role_id = ?, last_modified = ? where student_id = ?";
		
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(updatePerson);
			
			ps.setString(1, student.getFirstName());
			ps.setString(2, student.getLastName());
			ps.setString(3, student.getEmail());
			ps.setInt(4, student.getRoleId());
			ps.setDate(5, date);
			ps.setInt(6, student.getStudentId());
			
			ps.executeUpdate();
			ps.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}*/
	
}
