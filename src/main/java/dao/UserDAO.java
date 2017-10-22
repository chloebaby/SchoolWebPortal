package dao;

import model.User;

public interface UserDAO {
	public User selectUsernamePasswordByUsername(String username);
}
