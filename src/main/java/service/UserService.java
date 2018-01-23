package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserDAO;
import model.User;

@Service
public class UserService implements UserServiceInterface {
	private UserDAO daoUserImplementation;
	
	public UserService() {}
	
	public UserService(UserDAO daoUserImplementation) {
		this.daoUserImplementation = daoUserImplementation;
	}

	@Override
	@Transactional
	public User findUserByUsername(String username) {
		return daoUserImplementation.selectUserByUserName(username);
	}
	
	@Override
	@Transactional
	public void saveUser(User user) {
		daoUserImplementation.saveUser(user);
	}

	public UserDAO getDaoUserImplementation() {
		return daoUserImplementation;
	}

	public void setDaoUserImplementation(UserDAO daoUserImplementation) {
		this.daoUserImplementation = daoUserImplementation;
	}

}
