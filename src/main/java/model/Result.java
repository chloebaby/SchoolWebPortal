package model;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
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
	
	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "listOfResults")
	private List<Semester> listOfSemesters;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
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
	
	public List<Semester> getListOfSemesters() {
		return listOfSemesters;
	}

	public void setListOfSemesters(List<Semester> listOfSemesters) {
		this.listOfSemesters = listOfSemesters;
	}

	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
