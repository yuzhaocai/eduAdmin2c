package com.class8.eduAdmin.service;

import java.util.List;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.exception.ServiceException;
import com.class8.eduAdmin.model.Role;

public interface IRoleService {

	public Role getRole(Integer roleId);
	
	public void deleteRole(Integer roleId) throws ServiceException;

	public int countOfRole();

	public List<Role> findRole(Page page);

	public void createRole(Role role);

	public void updateRole(Role role);

	public List<Role> findAllRoles();

	public List<Role> findRoles(List<Integer> roleIds);

}
