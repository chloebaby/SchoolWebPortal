package dao;

import model.UserRole;

public interface UserRoleDAO {
	public void insertUserRole(UserRole userRole);
	public void deleteUserRoleByUsername(String username);
	public void updateUserRole(UserRole userRole);
	public UserRole selectUserRoleByUsername(String username);
}
