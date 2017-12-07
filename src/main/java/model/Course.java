package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Courses")
public class Course {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "course_id", updatable = false, nullable = false)
	private UUID courseId;
	
	@Column(name = "course_name")
	private String courseName;
	
	@Column(name = "course_code")
	private String courseCode;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name="COURSE_RESULT", joinColumns={@JoinColumn(name="course_id")},inverseJoinColumns={@JoinColumn(name="result_id")})
	private List<Result> listOfResults = new ArrayList<Result>();
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinTable(name="COURSE_SEMESTER", joinColumns={@JoinColumn(name="course_id")},inverseJoinColumns={@JoinColumn(name="semester_id")})
	private List<Semester> listOfSemesters = new ArrayList<Semester>();
	
	public Course() {}
	
	public UUID getCourseId() {
		return courseId;
	}
	
	public void setCourseId(UUID courseId) {
		this.courseId = courseId;
	}
	
	public String getCourseName() {
		return courseName;
	}
	
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	public String getCourseCode() {
		return courseCode;
	}
	
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}
	
	public List<Result> getListOfResults() {
		return listOfResults;
	}

	public void setListOfResults(List<Result> listOfResults) {
		this.listOfResults = listOfResults;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public List<Semester> getListOfSemesters() {
		return listOfSemesters;
	}

	public void setListOfSemesters(List<Semester> listOfSemesters) {
		this.listOfSemesters = listOfSemesters;
	}
	
}
