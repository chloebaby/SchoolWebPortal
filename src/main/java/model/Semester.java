package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Semesters")
public class Semester {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "semester_id", updatable = false, nullable = false)
	private UUID semesterId;
	
	@Column(name = "semester", updatable = false, nullable = false)
	private String semester;
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="SEMESTER_RESULT", joinColumns={@JoinColumn(name="semester_id")},inverseJoinColumns={@JoinColumn(name="result_id")})
	private Set<Result> listOfResults = new HashSet<Result>();
	
	@ManyToMany(mappedBy="listOfSemesters")
	@LazyCollection(LazyCollectionOption.FALSE)
	private List<Course> listOfCourses = new ArrayList<Course>();
	
	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name="STUDENT_SEMESTER", joinColumns={@JoinColumn(name="semester_id")},inverseJoinColumns={@JoinColumn(name="student_id")})
	private List<Student> listOfStudents = new LinkedList<Student>();
	
	
	public Semester() {}
	
	public UUID getSemesterId() {
		return semesterId;
	}
	
	public void setSemesterId(UUID semesterId) {
		this.semesterId = semesterId;
	}
	
	public String getSemester() {
		return semester;
	}
	
	public void setSemester(String semester) {
		this.semester = semester;
	}

	public Set<Result> getListOfResults() {
		return listOfResults;
	}

	public void setListOfResults(Set<Result> listOfResults) {
		this.listOfResults = listOfResults;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(List<Course> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}

	public List<Student> getListOfStudents() {
		return listOfStudents;
	}

	public void setListOfStudents(List<Student> listOfStudents) {
		this.listOfStudents = listOfStudents;
	}
}
