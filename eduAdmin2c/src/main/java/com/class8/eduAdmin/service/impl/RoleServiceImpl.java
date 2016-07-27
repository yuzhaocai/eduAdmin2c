package com.class8.eduAdmin.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.class8.eduAdmin.bean.Page;
import com.class8.eduAdmin.dao.RoleDao;
import com.class8.eduAdmin.dao.RoleResourceDao;
import com.class8.eduAdmin.dao.UserRoleDao;
import com.class8.eduAdmin.exception.ServiceException;
import com.class8.eduAdmin.model.Role;
import com.class8.eduAdmin.service.IRoleService;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private UserRoleDao userRoleDao;
	
	@Autowired
	private RoleResourceDao roleResourceDao;
	
	@Transactional(readOnly=true)
	public Role getRole(Integer roleId) {
		return roleDao.getRole(roleId);
	}
	
	public void deleteRole(Integer roleId) throws ServiceException{
		List<Integer> userIds = userRoleDao.findUserIdByRoleId(roleId);
		if(userIds != null && !userIds.isEmpty()){
			throw new ServiceException("角色删除失败,请先删除该角色下的用户！");
		}
		roleDao.deleteRole(roleId);
		roleResourceDao.deleteRoleResourcesByRoleId(roleId);
	}

	public int countOfRole() {
		return roleDao.countOfRole();
	}

	public List<Role> findRole(Page page) {
		return roleDao.findRole(page);
	}
	
	public void createRole(Role role) {
		roleDao.createRole(role);
	}
	
	public void updateRole(Role role) {
		roleDao.updateRole(role);
	}
	
	public List<Role> findAllRoles() {
		return roleDao.findAllRoles();
	}

	public List<Role> findRoles(List<Integer> roleIds) {
		return roleDao.findRoles(roleIds);
	}

}
