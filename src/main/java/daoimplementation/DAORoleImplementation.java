package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.RoleDAO;
import model.Role;
import sqlconnection.SQLConnection;

public class DAORoleImplementation extends SQLConnection implements RoleDAO {
	
	public DAORoleImplementation() {
		super();
	}
	
	public List<Role> selectRoles(){
		List<Role> allRoles = new ArrayList<Role>();
		
		String selectAllRoles = "select * from Roles";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectAllRoles);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Role role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setRolename(rs.getString("rolename"));
				allRoles.add(role);
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return allRoles;
	}
}
