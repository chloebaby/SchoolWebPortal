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
	
	public void testConnectionIsOpen() {
		try {
			assertFalse(connection.isClosed());
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
	
	public void testConnectionIsClosed() {
		try {
			connection.close();
			assertTrue(connection.isClosed());
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}
}
