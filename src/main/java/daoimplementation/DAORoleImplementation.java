package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import dao.RoleDAO;
import model.Role;
import model.Student;
import sqlconnection.SQLConnection;

public class DAORoleImplementation implements RoleDAO {
	private SessionFactory sessionFactory;
	
	
	public DAORoleImplementation() {}
	
	public DAORoleImplementation(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	@Override
	public void saveRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		session.save(role);
	}
	
	@Override
	public List<Role> selectRoles(){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM Role");
		List<Role> allRoles = query.list();
		
		return allRoles;
	}
	
	@Override
	public UUID selectUUIDByRoleName(String rolename) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select roleId from Role where rolename = :rolename";
		Query query = session.createQuery(hql);
		query.setParameter("rolename", rolename);
		UUID roleId = (UUID) query.uniqueResult();
		
		return roleId;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
