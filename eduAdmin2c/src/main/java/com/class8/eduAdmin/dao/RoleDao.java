package com.class8.eduAdmin.dao;


import java.util.List;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.model.Role;

public interface RoleDao {

	public Role getRole(Integer roleId);
	
	public void deleteRole(Integer roleId);

	public int countOfRole();

	public List<Role> findRole(Page page);

	public void createRole(Role role);

	public void updateRole(Role role);

	public List<Role> findAllRoles();

	public List<Role> findRoles(List<Integer> roleIds);

		
}
