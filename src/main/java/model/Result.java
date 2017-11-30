package model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
	UUID resultId;
	
	@Column(name = "marks")
	int marks;
	
	@Column(name = "last_modified")
	Date lastModified;
	
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
	
	public Date getLastModified() {
		return lastModified;
	}
	
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
