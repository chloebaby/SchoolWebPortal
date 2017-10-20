package dao;

import java.util.List;

public interface SchoolDAO<T> {
	public List<T> select();
	public List<T> selectById(Object id);
}
