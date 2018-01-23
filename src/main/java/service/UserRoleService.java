package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.UserRoleDAO;
import model.UserRole;

@Service
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
	public void updateUserRole(UserRole userRole) {
		daoUserRoleImplementation.updateUserRole(userRole);
	}
	
	@Override
	@Transactional
	public void deleteUserRolebyUsername(String username) {
		daoUserRoleImplementation.deleteUserRoleByUsername(username);
	}
	
	@Override
	@Transactional
	public UserRole findUserRoleByUsername(String username) {
		return daoUserRoleImplementation.selectUserRoleByUsername(username);
	}

	public UserRoleDAO getDaoUserRoleImplementation() {
		return daoUserRoleImplementation;
	}

	public void setDaoUserRoleImplementation(UserRoleDAO daoUserRoleImplementation) {
		this.daoUserRoleImplementation = daoUserRoleImplementation;
	}
}
