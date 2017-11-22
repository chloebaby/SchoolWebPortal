package sqlconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import util.HibernateUtil;

public class SQLConnection {
	private Session currentSession;
	private Transaction currentTransaction;
	private static SessionFactory sessionFactory;
	
	protected void openCurrentSession() {
		currentSession = SQLConnection.getSessionFactory().openSession();
	}
	
	protected void openCurrentTransaction() {
		currentTransaction = currentSession.beginTransaction();
	}
	
	protected Session getCurrentSession() {
		return currentSession;
	}
	
	protected Transaction getCurrentTransaction() {
		return currentTransaction;
	}
	
	protected void closeCurrentSession() {
		currentSession.close();
	}
	
	protected void commitTransaction() {
		currentTransaction.commit();
	}
	
	protected void closeSessionFactory() {
		sessionFactory.close();
	}
	
	private static SessionFactory getSessionFactory() {
		sessionFactory = HibernateUtil.getSessionFactory();
		return sessionFactory;
	}
}
