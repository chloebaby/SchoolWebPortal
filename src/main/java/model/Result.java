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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Results")
public class Result {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "result_id", updatable = false, nullable = false)
	private UUID resultId;
	
	@Column(name = "marks", nullable = true)
	private int marks;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="RESULT_STUDENT", joinColumns={@JoinColumn(name="result_id")},inverseJoinColumns={@JoinColumn(name="student_id")})
	private Student student;
	
	@ManyToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="RESULT_COURSE", joinColumns={@JoinColumn(name="result_id")},inverseJoinColumns={@JoinColumn(name="course_id")})
	private Course course;
	
	@ManyToMany(mappedBy = "listOfResults")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Semester> listOfSemesters = new HashSet<Semester>();
	
	public Result() {}
	
	public UUID getResultId() {
		return resultId;
	}
	
	public void setResultId(UUID resultId) {
		this.resultId = resultId;
	}
	
	public int getMarks() {
		return marks;
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Set<Semester> getListOfSemesters() {
		return listOfSemesters;
	}

	public void setListOfSemesters(Set<Semester> listOfSemesters) {
		this.listOfSemesters = listOfSemesters;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
