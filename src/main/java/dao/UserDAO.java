package dao;

import model.User;

public interface UserDAO {
	public User selectUserByUserName(String username);
	public void saveUser(User user);
}
