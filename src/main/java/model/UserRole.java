package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USERROLES")
public class UserRole {
	@Id
	@Column(name = "userrole_id")
	private int userRoleId;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "rolename")
	private String rolename;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	public UserRole() {}
	
	public int getUserRoleId() {
		return userRoleId;
	}
	
	public void setUserRoleId(int userRoleId) {
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
