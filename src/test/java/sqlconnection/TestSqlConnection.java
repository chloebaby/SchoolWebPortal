package sqlconnection;

import java.sql.Connection;
import java.sql.SQLException;

import junit.framework.TestCase;

public class TestSqlConnection extends TestCase {
	private SQLConnection sqlConnection;
	private Connection connection;
	
	public TestSqlConnection(String name) {
		super(name);
	}
	
	protected void setUp() {
		sqlConnection = new SQLConnection();
		connection = sqlConnection.getConnection();
	}
	
	protected void tearDown() {
	}
	
	public void testConnectionIsOpen() throws Throwable {
		assertFalse(connection.isClosed());
	}
	
	public void testConnectionIsClosed() throws Throwable {
		connection.close();
		assertTrue(connection.isClosed());
	}
}
