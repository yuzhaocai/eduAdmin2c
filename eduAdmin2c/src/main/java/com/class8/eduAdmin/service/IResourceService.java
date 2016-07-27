package com.class8.eduAdmin.service;

import java.util.List;
import com.class8.eduAdmin.model.Resource;

public interface IResourceService {

	public Resource getResource(Integer resourceId);

	public List<Resource> findAllResources();

	public void savaResource(Resource resource);

	public void updateResource(Resource resource);

	public void deleteResources(List<Integer> resourceIds);

	public List<Resource> findActiveResources();
	
	public List<Resource> findMenuResources();

	public List<Resource> findResources(List<Integer> resourceIds);

}
