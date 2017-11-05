package service;

import java.util.List;

import dao.RoleDAO;
import daoimplementation.DAORoleImplementation;
import model.Role;

public class RoleService implements RoleServiceInterface{
	private RoleDAO daoRoleImplementation;
	
	public RoleService() {
		daoRoleImplementation = new DAORoleImplementation();
	}
	
	public List<Role> findAllRoles(){
		return daoRoleImplementation.selectRoles();
	}

}
