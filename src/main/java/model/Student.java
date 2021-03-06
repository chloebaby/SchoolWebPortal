package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

@Entity
@Table(name = "Students")
public class Student {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@Type(type = "uuid-char")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "student_id", updatable = false, nullable = false)
	private UUID studentId;
	

	
	@Column(name = "first_name")
	private String firstName;
	
	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email")
	private String email;

	@Column(name = "last_modified")
	private Date lastModified;
	
	@OneToOne(cascade = CascadeType.ALL)
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinColumn(name = "role_id")
	private Role role;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy ="student")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Result> listOfResults = new HashSet<Result>();
	
	@ManyToMany(mappedBy = "listOfStudents")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Course> listOfCourses = new HashSet<Course>();
	
	@ManyToMany(mappedBy = "listOfStudents")
	@LazyCollection(LazyCollectionOption.FALSE)
	private Set<Semester> listOfSemesters = new HashSet<Semester>();
	
	public Student() {}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public Role getRole() {
		return role;
	}


	public void setRole(Role role) {
		this.role = role;
	}


	public UUID getStudentId() {
		return studentId;
	}
	
	public void setStudentId(UUID studentId) {
		this.studentId = studentId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Set<Course> getListOfCourses() {
		return listOfCourses;
	}


	public void setListOfCourses(Set<Course> listOfCourses) {
		this.listOfCourses = listOfCourses;
	}


	public Set<Result> getListOfResults() {
		return listOfResults;
	}

	public void setListOfResults(Set<Result> listOfResults) {
		this.listOfResults = listOfResults;
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
