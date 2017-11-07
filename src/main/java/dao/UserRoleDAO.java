package dao;

import model.UserRole;

public interface UserRoleDAO {
	public void insertUserRole(UserRole userRole);
	public void deletUserRoleByUsername(String username);
}
