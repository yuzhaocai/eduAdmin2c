package com.class8.eduAdmin.dao;

import java.util.List;

import com.class8.eduAdmin.model.Organization;

public interface OrganizationDao {
	
	public Organization getOrganization(Integer organizationId);

	public void saveOrganization(Organization organization);

	public List<Organization> findAllOrganization();

	public void updateOrganization(Organization organization);

	public void deleteOrganizations(List<Integer> organizationIds);
}
