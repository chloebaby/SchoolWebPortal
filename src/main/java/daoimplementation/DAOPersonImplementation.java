package daoimplementation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import dao.PersonDAO;
import sqlconnection.SQLConnection;
import model.Person;

public class DAOPersonImplementation extends SQLConnection implements PersonDAO {
	
	public DAOPersonImplementation() {
		super();
	}
	
	@Override
	public List<Person> select(){
		List<Person> allPersons = new ArrayList<Person>();
		String selectAllPersons = "select * from Person";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(selectAllPersons);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Person person = new Person();
				person.setId(rs.getInt("person_id"));
				person.setName(rs.getString("person_name"));
				allPersons.add(person);
			}
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
		
		return allPersons;
	}
	
	@Override
	public void insertPerson(int id, String name) {
		String insertPerson = "insert into Person values (?, ?)";
		
		try(Connection connection = getConnection()){
			PreparedStatement ps = connection.prepareStatement(insertPerson);
			
			ps.setInt(1,  id);
			ps.setString(2, name);
			
			ps.executeUpdate();
			
		}catch(SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
