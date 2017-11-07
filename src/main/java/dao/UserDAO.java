package dao;

import model.User;

public interface UserDAO {
	public void insertUser(User user);
	public int selectUserIdByName(User user);
	public void deleteUserById(int userId);
	
}
