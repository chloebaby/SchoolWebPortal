package service;

import dao.UserRoleDAO;
import daoimplementation.DAOUserRoleImplementation;
import model.UserRole;

public class UserRoleService implements UserRoleServiceInterface{

	private UserRoleDAO daoUserImplementation;
	
	public UserRoleService() {
		daoUserImplementation = new DAOUserRoleImplementation();
	}
	
	public void saveUserRole(UserRole userRole) {
		daoUserImplementation.insertUserRole(userRole);
	}
	
	public void updateUserRoleByUsername(UserRole userRole) {
		daoUserImplementation.updateUserRoleByUsername(userRole);
	}
}
