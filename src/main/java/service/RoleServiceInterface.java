package service;

import java.util.List;
import java.util.UUID;

import model.Role;

public interface RoleServiceInterface {
	public List<Role> findAllRoles();
	public UUID findUUIDByRolename(String rolename);
	public void saveRole(Role role);
}
