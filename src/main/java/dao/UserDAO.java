package dao;

import model.User;

public interface UserDAO {
	public User selectUserByUserName(String username);
}
