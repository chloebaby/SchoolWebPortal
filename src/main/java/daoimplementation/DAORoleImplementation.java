package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.Query;

import dao.RoleDAO;
import model.Role;
import model.Student;
import sqlconnection.SQLConnection;

public class DAORoleImplementation extends SQLConnection implements RoleDAO {
	
	public DAORoleImplementation() {
		super();
	}
	
	public List<Role> selectRoles(){
		openCurrentSession();
		Query query = getCurrentSession().createQuery("FROM Role");
		List<Role> allRoles = query.list();
		closeCurrentSession();
		
		return allRoles;
	}
}
