package com.class8.eduAdmin.service;

import java.util.List;

public interface IRoleResourceService {

	public List<Integer> findResourceIdsByRoleId(Integer roleId);

	public void assignResource(Integer roleId, List<Integer> resourceIds);

}
