package dao;

import java.util.List;

import model.Person;

public interface PersonDAO {
	public List<Person> select();
	public void insertPerson(int id, String name);
	public void deleteById(int id);
}
