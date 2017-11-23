package model;

import java.sql.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "USERROLES")
public class UserRole {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(name = "userrole_id", updatable = false, nullable = false)
	private UUID userRoleId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "rolename")
	private String rolename;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	public UserRole() {}
	
	public UUID getUserRoleId() {
		return userRoleId;
	}
	
	public void setUserRoleId(UUID userRoleId) {
		this.userRoleId = userRoleId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
}
