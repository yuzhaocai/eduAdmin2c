package com.class8.eduAdmin.service.impl;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.class8.eduAdmin.dao.RoleResourceDao;
import com.class8.eduAdmin.model.RoleResource;
import com.class8.eduAdmin.service.IRoleResourceService;

@Service
@Transactional
public class RoleResourceServiceImpl implements IRoleResourceService {
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Transactional(readOnly=true)
	public List<Integer> findResourceIdsByRoleId(Integer roleId) {
		return roleResourceDao.findResourceIdsByRoleId(roleId);
	}

	public void assignResource(Integer roleId, List<Integer> resourceIds) {
		roleResourceDao.deleteRoleResourcesByRoleId(roleId);
		if(resourceIds != null && resourceIds.size() > 0){
			List<RoleResource> roleResources = new ArrayList<RoleResource>();
			for (Integer resourceId : resourceIds) {
				RoleResource roleResoruce = new RoleResource();
				roleResoruce.setRoleId(roleId);
				roleResoruce.setResourceId(resourceId);
				roleResources.add(roleResoruce);
			}
			roleResourceDao.saveRoleResources(roleResources);
		}
	}

}
