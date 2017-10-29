package daoimplementation;

import sqlconnection.SQLConnection;

import dao.SchoolDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.CourseDAO;
import model.Course;

public class DAOCourseImplementation extends SQLConnection implements SchoolDAO<Course>, CourseDAO{
	
	public DAOCourseImplementation() {
		super();
	}
	
	public List<Course> select(){
		List<Course> allCourses = new ArrayList<Course>();
		
		String selectAllCourses = "select * from Courses";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectAllCourses);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
			}
			
			rs.close();
			ps.close();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return allCourses;
	}
	
	public List<Course> selectById(Object id){
		List<Course> courseById = new ArrayList<Course>();
		
		String selectCourseById = "select * from Courses where course_id = ?";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectCourseById);
			
			ps.setInt(1, (int)id);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Course course = new Course();
				course.setCourseId(rs.getInt("course_id"));
				course.setCourseName(rs.getString("course_name"));
				courseById.add(course);
			}
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return courseById;
	}
	
	public void insertCourse(Course course) {
		String insertCourse = "insert into Courses(course_id, course_name, last_modified) values (?, ?, ?)";
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertCourse);
			
			ps.setNull(1, java.sql.Types.INTEGER);
			ps.setString(2, course.getCourseName());
			ps.setDate(3, date);
			
			ps.executeUpdate();
			ps.close();
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
