package com.class8.eduAdmin.service;

import java.util.List;

import com.class8.eduAdmin.exception.ServiceException;
import com.class8.eduAdmin.model.Organization;

public interface IOrganizationService {

	public Organization getOrganization(Integer organizationId);

	public void saveOrganization(Organization organization);

	public List<Organization> findAllOrganization();

	public void updateOrganization(Organization organization);

	public void deleteOrganizations(List<Integer> organizationIds) throws ServiceException;

}
