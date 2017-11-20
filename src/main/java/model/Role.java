package model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ROLES")
public class Role {
	
	@Id
	@Column(name = "role_id")
	private int roleId;
	
	@Column(name = "role_name")
	private String rolename;
	
	@Column(name = "last_modified")
	private Date lastModified;
	
	public Role() {}
	
	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	public String getRolename() {
		return rolename;
	}
	
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
}
