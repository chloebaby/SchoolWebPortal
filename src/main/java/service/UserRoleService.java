package service;

import org.springframework.transaction.annotation.Transactional;

import dao.UserRoleDAO;
import model.UserRole;

public class UserRoleService implements UserRoleServiceInterface{
	private UserRoleDAO daoUserRoleImplementation;
	
	public UserRoleService() {}
	
	public UserRoleService(UserRoleDAO daoUserRoleImplementation) {
		this.daoUserRoleImplementation = daoUserRoleImplementation;
	}
	
	@Override
	@Transactional
	public void saveUserRole(UserRole userRole) {
		daoUserRoleImplementation.insertUserRole(userRole);
	}
	
	@Override
	@Transactional
	public void updateUserRoleByUsername(UserRole userRole) {
		daoUserRoleImplementation.updateUserRoleByUsername(userRole);
	}
	
	@Override
	@Transactional
	public void deleteUserRolebyUsername(String username) {
		daoUserRoleImplementation.deleteUserRoleByUsername(username);
	}

	public UserRoleDAO getDaoUserRoleImplementation() {
		return daoUserRoleImplementation;
	}

	public void setDaoUserRoleImplementation(UserRoleDAO daoUserRoleImplementation) {
		this.daoUserRoleImplementation = daoUserRoleImplementation;
	}
}
