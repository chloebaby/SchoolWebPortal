package dao;

import java.util.List;
import java.util.UUID;

import model.Role;

public interface RoleDAO {
	public List<Role> selectRoles();
	public UUID selectUUIDByRoleName(String rolename);
}
