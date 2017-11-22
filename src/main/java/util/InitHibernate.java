package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import model.Role;
import model.User;
import model.UserRole;

public class InitHibernate {
	private static SessionFactory sessionFactory;
	public static void main(String[] args) {
		java.util.Date today = new java.util.Date();
		java.sql.Date date = new java.sql.Date(today.getTime());
		Session session;
		
		UserRole userRole = new UserRole();
		User user = new User();
		
		Role roleSchoolAdmin = new Role();
		Role roleStudent = new Role();
		
		roleSchoolAdmin.setRolename("school-admin");
		roleSchoolAdmin.setLastModified(date);
		
		roleStudent.setRolename("student");
		roleStudent.setLastModified(date);
		
		user.setUsername("tomcat");
		user.setPassword("tomcat");
		user.setLastModified(date);
		
		userRole.setUsername("tomcat");
		userRole.setRolename("school-admin");
		userRole.setLastModified(date);
		
		sessionFactory = HibernateUtil.getSessionFactory();
		
		//Insert the userRole
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(userRole);
		session.getTransaction().commit();
		session.close();
		
		//Insert the roles
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(roleSchoolAdmin);
		session.save(roleStudent);
		session.getTransaction().commit();
		session.close();
		
		//Insert the users
		session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(user);
		session.getTransaction().commit();
		session.close();
		
		
		sessionFactory.close();
	}

}
