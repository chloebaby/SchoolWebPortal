package daoimplementation;

import sqlconnection.SQLConnection;

import dao.SchoolDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.query.Query;

import dao.CourseDAO;
import model.Course;
import model.Student;

public class DAOCourseImplementation extends SQLConnection implements CourseDAO{
	
	public DAOCourseImplementation() {
		super();
	}
	
	@Override
	public List<Course> selectAllCourses(){
		openCurrentSession();
		Query query = getCurrentSession().createQuery("FROM Course");
		List<Course> allCourses = query.list();
		closeCurrentSession();
		
		return allCourses;
	}
	
	public Course selectCourseById(UUID courseId){
		openCurrentSession();
		Course course = getCurrentSession().get(Course.class, courseId);
		closeCurrentSession();
		return course;
	}
	
	public void insertCourse(Course course) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().save(course);
		commitTransaction();
		closeCurrentSession();
	}
	
	public void deleteCourse(Course course) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().delete(course);
		commitTransaction();
		closeCurrentSession();
	}
	
	public void updateCourse(Course course) {
		openCurrentSession();
		openCurrentTransaction();
		getCurrentSession().update(course);
		commitTransaction();
		closeCurrentSession();
	}
}
