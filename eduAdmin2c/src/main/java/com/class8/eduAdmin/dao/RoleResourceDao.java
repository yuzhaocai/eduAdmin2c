package com.class8.eduAdmin.dao;

import java.util.List;

import com.class8.eduAdmin.model.RoleResource;

public interface RoleResourceDao {

	public void deleteRoleResourcesByRoleId(Integer roleId);

	public void deleteRoleResourcesByResourceIds(List<Integer> resourceIds);

	public List<Integer> findResourceIdsByRoleId(Integer roleId);

	public void saveRoleResources(List<RoleResource> roleResources);
	
}
