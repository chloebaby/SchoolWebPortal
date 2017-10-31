package dao;

import java.util.List;

public interface SchoolDAO<T> {
	public List<T> select();
	public T selectById(int id);
}
