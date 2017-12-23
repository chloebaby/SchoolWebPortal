package service;

import model.User;

public interface UserServiceInterface {
	public User findUserByUsername(String username);
}
