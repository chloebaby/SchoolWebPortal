package service;

import model.UserRole;

public interface UserRoleServiceInterface {
	public void saveUserRole(UserRole userRole);
	public void updateUserRole(UserRole userRole);
	public void deleteUserRolebyUsername(String username);
	public UserRole findUserRoleByUsername(String username);
}
