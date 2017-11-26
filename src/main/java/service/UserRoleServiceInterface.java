package service;

import model.UserRole;

public interface UserRoleServiceInterface {
	public void saveUserRole(UserRole userRole);
	public void updateUserRoleByUsername(UserRole userRole);
	public void deleteUserRolebyUsername(String username);
}
