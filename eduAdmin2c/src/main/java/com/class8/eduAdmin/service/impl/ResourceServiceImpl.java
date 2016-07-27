package com.class8.eduAdmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.class8.eduAdmin.constant.ResourceTypeConst;
import com.class8.eduAdmin.constant.StatusConst;
import com.class8.eduAdmin.dao.ResourceDao;
import com.class8.eduAdmin.dao.RoleResourceDao;
import com.class8.eduAdmin.model.Resource;
import com.class8.eduAdmin.service.IResourceService;

@Service
@Transactional
public class ResourceServiceImpl implements IResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Transactional(readOnly=true)
	public Resource getResource(Integer resourceId) {
		return resourceDao.getResource(resourceId);
	}
	
	@Transactional(readOnly=true)
	public List<Resource> findAllResources() {
		return resourceDao.findAllResources();
	}

	public void savaResource(Resource resource) {
		resourceDao.saveResource(resource);
	}

	public void updateResource(Resource resource) {
		resourceDao.updateResource(resource);
	}

	public void deleteResources(List<Integer> resourceIds) {
		resourceDao.deleteResources(resourceIds);
		roleResourceDao.deleteRoleResourcesByResourceIds(resourceIds);
	}
	
	@Transactional(readOnly=true)
	public List<Resource> findActiveResources() {
		List<Resource> resources = resourceDao.findAllResources();
		if(resources != null){
			for (Resource resource : resources) {
				if(StatusConst.FROZEN == resource.getStatus()){
					resources.remove(resource);
				}
			}
		}
		return resources;
	}
	
	@Transactional(readOnly=true)
	public List<Resource> findMenuResources(){
		List<Resource> resources = resourceDao.findAllResources();
		if(resources != null){
			for (Resource resource : resources) {
				if(StatusConst.FROZEN == resource.getStatus() || ResourceTypeConst.FUNC == resource.getType()){
					resources.remove(resource);
				}
			}
		}
		return resources;
	}
	
	@Transactional(readOnly=true)
	public List<Resource> findResources(List<Integer> resourceIds) {
		return resourceDao.findResources(resourceIds);
	}
}
