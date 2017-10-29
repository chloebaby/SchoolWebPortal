package model;

import java.sql.Date;

public class Result {
	int resultId;
	Student student;
	Course course;
	int marks;
	Semester semester;
	Date lastModified;
	
	public Result() {}
	
	public int getResultId() {
		return resultId;
	}
	
	public void setResultId(int resultId) {
		this.resultId = resultId;
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
	
	public int getMarks() {
		return marks;
	}
	
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public Semester getSemester() {
		return semester;
	}
	
	public void setSemester(Semester semester) {
		this.semester = semester;
	}
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
