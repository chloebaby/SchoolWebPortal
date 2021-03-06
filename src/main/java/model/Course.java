package model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
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

	@Column(name = "last_modified")
	private Date lastModified;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Result> listOfResults = new HashSet<Result>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="COURSE_STUDENT", joinColumns={@JoinColumn(name="course_id")},inverseJoinColumns={@JoinColumn(name="student_id")})
	private Set<Student> listOfStudents = new HashSet<Student>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="COURSE_SEMESTER", joinColumns={@JoinColumn(name="course_id")},inverseJoinColumns={@JoinColumn(name="semester_id")})
	private Set<Semester> listOfSemesters = new HashSet<Semester>();
	
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
	
	public Set<Result> getListOfResults() {
		return listOfResults;
	}

	public void setListOfResults(Set<Result> listOfResults) {
		this.listOfResults = listOfResults;
	}

	public Set<Student> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(Set<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Set<Semester> getListOfSemesters() {
		return listOfSemesters;
	}

	public void setListOfSemesters(Set<Semester> listOfSemesters) {
		this.listOfSemesters = listOfSemesters;
	}
	
}
