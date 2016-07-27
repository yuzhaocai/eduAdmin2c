package com.class8.eduAdmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.class8.eduAdmin.dao.OrganizationDao;
import com.class8.eduAdmin.dao.UserDao;
import com.class8.eduAdmin.exception.ServiceException;
import com.class8.eduAdmin.model.Organization;
import com.class8.eduAdmin.model.User;
import com.class8.eduAdmin.service.IOrganizationService;

@Service
@Transactional
public class OrganizationServiceImpl implements IOrganizationService {
	
	@Autowired
	private OrganizationDao organizationDao;
	
	@Autowired
	private UserDao userDao;
	
	@Transactional(readOnly=true)
	public Organization getOrganization(Integer organizationId) {
		return organizationDao.getOrganization(organizationId);
	}

	public void saveOrganization(Organization organization) {
		organizationDao.saveOrganization(organization);
	}

	public List<Organization> findAllOrganization() {
		return organizationDao.findAllOrganization();
	}

	public void updateOrganization(Organization organization) {
		organizationDao.updateOrganization(organization);
	}

	public void deleteOrganizations(List<Integer> organizationIds) throws ServiceException {
		//检查该组织机构下是否有用户
		for (Integer organizationId : organizationIds) {
			List<User> users = userDao.findUsersByOrganizationId(organizationId);
			if(users != null && users.size()>0){
				throw new ServiceException("删除失败，您要删除的机构下用户不为空！");
			}
		}
		organizationDao.deleteOrganizations(organizationIds);
	}

}
