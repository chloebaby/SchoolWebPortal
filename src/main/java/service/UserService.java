package service;

import dao.UserDAO;
import daoimplementation.DAOUserImplementation;
import model.User;

public class UserService implements UserServiceInterface {
	private UserDAO daoUserImplementation;
	
	public UserService() {
		daoUserImplementation = new DAOUserImplementation();
	}

	@Override
	public User findUserByUsername(String username) {
		return daoUserImplementation.selectUserByUserName(username);
	}

}
