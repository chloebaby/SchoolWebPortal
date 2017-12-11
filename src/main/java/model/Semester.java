package model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinTable(name="SEMESTER_RESULTS", joinColumns={@JoinColumn(name="semester_id")},inverseJoinColumns={@JoinColumn(name="result_id")})
	private List<Result> listOfResults = new ArrayList<Result>();
	
	@ManyToMany(mappedBy="listOfSemesters")
	private List<Course> listOfCourses;
	
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

	public List<Result> getListOfResults() {
		return listOfResults;
	}

	public void setListOfResults(List<Result> listOfResults) {
		this.listOfResults = listOfResults;
	}

	public List<Course> getListOfCourses() {
		return listOfCourses;
	}

	public void setListOfCourses(List<Course> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}
}
