package service;

import java.util.List;
import java.util.UUID;

import org.springframework.transaction.annotation.Transactional;

import dao.RoleDAO;
import model.Role;

public class RoleService implements RoleServiceInterface{
	private RoleDAO daoRoleImplementation;
	
	public RoleService() {}
	
	public RoleService(RoleDAO daoRoleImplementation) {
		this.daoRoleImplementation = daoRoleImplementation;
	}
	
	@Override
	@Transactional
	public void saveRole(Role role) {
		daoRoleImplementation.saveRole(role);
	}
	
	@Override
	@Transactional
	public List<Role> findAllRoles(){
		return daoRoleImplementation.selectRoles();
	}
	
	@Override
	@Transactional
	public UUID findUUIDByRolename(String rolename) {
		return daoRoleImplementation.selectUUIDByRoleName(rolename);
	}

	public RoleDAO getDaoRoleImplementation() {
		return daoRoleImplementation;
	}

	public void setDaoRoleImplementation(RoleDAO daoRoleImplementation) {
		this.daoRoleImplementation = daoRoleImplementation;
	}

}
