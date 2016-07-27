package com.class8.eduAdmin.dao;

import java.util.List;

import com.class8.eduAdmin.model.Resource;

public interface ResourceDao {

	public Resource getResource(Integer resourceId);

	public List<Resource> findAllResources();

	public List<Resource> findMenuResources();

	public void saveResource(Resource resource);

	public void updateResource(Resource resource);

	public void deleteResources(List<Integer> resourceIds);

	public List<Resource> findResources(List<Integer> resourceIds);

}
